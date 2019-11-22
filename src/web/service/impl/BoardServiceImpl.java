package web.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import util.Paging;
import web.dao.face.BoardDao;
import web.dao.face.BoardFileDao;
import web.dao.face.CommentDao;
import web.dao.face.RecommendDao;
import web.dao.impl.BoardDaoImpl;
import web.dao.impl.BoardFileDaoImpl;
import web.dao.impl.CommentDaoImpl;
import web.dao.impl.RecommendDaoImpl;
import web.dto.Board;
import web.dto.Boardfile;
import web.dto.Recommend;
import web.service.face.BoardService;
import web.dto.Comment;

public class BoardServiceImpl implements BoardService{

	BoardDao boardDao = new BoardDaoImpl();
	Boardfile boardfile = new Boardfile();
	BoardFileDao boardfileDao = new BoardFileDaoImpl();
	RecommendDao recommendDao = new RecommendDaoImpl();
	CommentDao commentDao = new CommentDaoImpl();
	
	
	@Override
	public List<Board> getList() {
		return boardDao.selectAll();
	}
	@Override
	public Board getBoardno(HttpServletRequest req) {
		String param = req.getParameter("bno");
		
		if(param == null) return null;
		
		int boardno = Integer.parseInt(param);
		
		Board board = new Board();
		board.setBoardno(boardno);
		return board;
	}
	@Override
	public Board view(Board board) {
		boardDao.updateHit(board);
		return boardDao.selectBoardByBoardno(board);
	}
	
	@Override
	public Board hitplus(Board board) {
		
		return null;
	}
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		// * 요청 파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		String search = req.getParameter("search");
		
		int curPage = 0;
		if(param!=null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}
//		System.out.println("curPage : " + curPage);
		
		// * Board TB와 curPage값을 이용한 Paging 객체를 생성하고 반환
		int totalCount = boardDao.selectCntAll(search);
		
		// Paging  객체 생성
		Paging paging = new Paging(totalCount, curPage);
		paging.setSearch(search);
		return paging;
	}
	@Override
	public List<Board> getList(Paging paging) {
		return boardDao.selectAll(paging);
	}
	@Override
	public Board writeParam(HttpServletRequest req) {
		
		Board board  = new Board();
		HttpSession session = req.getSession();
		
		
		
		
		String param = null;
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		param = req.getParameter("boardno");
		if (param != null && !"".equals(param)) {
			board.setBoardno(Integer.parseInt(param));
		}
		
		param = req.getParameter("title");
		if (param != null && !"".equals(param)) {
			board.setTitle(param);
		}
		
		param = req.getParameter("content");
		if (param != null && !"".equals(param)) {
			board.setContent(param);
		}
		
		board.setId((String)session.getAttribute("userid"));
		
		
		
		return board;
	}

	
	@Override
	public void write(Board board) {
		
		
		 boardDao.insert(board);
	}
	
	@Override
	public void write(HttpServletRequest req, HttpServletResponse resp) {
		
		// 보드 객체 생성
		Board board = new Board();
		// boardno
		int boardno = boardDao.selectBoardno();
		
		// 세션객체 생성
		HttpSession session = req.getSession();
		// 응답 객체 Content-Type 설정
		resp.setContentType("text/html; charset=UTF-8");

		// 응답 객체 출력 스트림 얻기
		PrintWriter out = null;
		
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 1. 파일업로드 형태의 데이터가 맞는지 확인
		// enctype이 multipart/form-data가 맞는지 확인
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);

		// 1-1. multipart/form-data 인코딩으로 전송되지 않았을 경우
		if( !isMultipart) {
			out.append("<h1>enctype이 multipart/form-data가 아님</h1>");
			out.append("<a href = '/commons/fileupload'>이동</a>");

			return;
		}

		// 1-2.여기 이후는 multipart/form-data로 요청된 상황임
		// 파일이 전송되었음
		
		// 2. 업로드된 파일을 처리하는 아이템팩토리 객체 생성

		// ItemFactory : 업로드된 파일을 처리하는 방식을 정하는 클래스

		// FileItem : 클라이언트로부터 전송된 데이터를 객체화시킨 것

		// DiskFileItemFactory class
		//	-> 디스크기반(HDD)의 파일아이템 처리 API
		// 	-> 업로드된 파일을 디스크에 임시 저장하고 후처리한다
		DiskFileItemFactory factory = null;
		factory = new DiskFileItemFactory();
		
		// 3. 업로드된 아이템이 용량이 적당히 작으면 메모리에서  처리
		int maxMem = 1 * 1024 * 1024; // 1MB
		factory.setSizeThreshold(maxMem);
		
		// 4. 용량이 적당히 크면 임시파일을 만들어서 처리(디스크)
		ServletContext context = req.getServletContext();

		String path = context.getRealPath("tmp");

		File repository =  new File(path);
		
		factory.setRepository(repository);
		
		// 5. 업로드 허용 용량 기준을 넘지 않을 경우에만 업로드 처리
		int maxFile = 10 * 1024 * 1024; // 10MB

		// 파일 업로드 객체 생성 - DiskFileItemFactory이용해서 생성함
		ServletFileUpload upload = null;
		upload = new ServletFileUpload(factory);

		// 파일 업로드 용량제한 설정 : 10MB
		upload.setFileSizeMax(maxFile);
		
		// ----- 파일 업로드 준비 완료 -----
		
		// 6. 업로드된 데이터 추출(파싱)
		// 임시 파일 업로드도 같이 수행함
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		// 7. 파싱된 데이터 처리하기
		// items 리스트에 요청파라미터가 파싱되어있음

		// 요청정보의 형태
		//	1 - 빈 파일 (용량이 0인 파일)
		// 	2 - form-data(일반적인 요청 파라미터)
		//	3 - 파일
		
		Iterator<FileItem> iter = items.iterator();
		
		// 모든 요청정보 처리
				while(iter.hasNext()) {

					FileItem item = iter.next();

					// 1 - 빈 파일 처리
					if(item.getSize() <= 0) continue;

					// 2 - 일반적인 요청 데이터 처리
					if(item.isFormField()) {
						// form-data일 경우
						// key:value 쌍으로 전달된 요청 파라미터

						// key - getFieldName()
						// value - getString()

						// 처리 방식
						//				out.println("- - - 폼 필드 - - -<br>");
						//				out.println("키 : " + item.getFieldName() + "<br>");
						//				try {
						//				out.println("값 : " + item.getString("utf-8") + "<br>");
						//			} catch (UnsupportedEncodingException e) {
						//				e.printStackTrace();
						//			}
						// key값에 따라 처리방식 다르게 하기
						String key = item.getFieldName();
						
						
						if("title".equals(key)) {
							out.println("- - - 폼 필드 - - -<br>");
							out.println("키 : " + item.getFieldName() + "<br>");
							try {
								board.setTitle(item.getString("utf-8"));
							} catch (UnsupportedEncodingException e1) {
								e1.printStackTrace();
							}
							try {
								out.println("값 : " + item.getString("utf-8") + "<br>");
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}			
						}else if("content".equals(key)){
							out.println("- - - 폼 필드 - - -<br>");
							out.println("키 : " + item.getFieldName() + "<br>");
							try {
								board.setContent(item.getString("utf-8"));
							} catch (UnsupportedEncodingException e1) {
								e1.printStackTrace();
							}
							try {
								out.println("값 : " + item.getString("utf-8") + "<br>");
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
						}// key값 비교 if

						// 세션에서 아이디 받기
						board.setId((String)session.getAttribute("userid"));
//						System.out.println("[session ID : ]" + (String)session.getAttribute("userid"));
//						System.out.println(boardno);
						board.setBoardno(boardno);
						
						


					}else { // 3 - 파일처리

						// 업로드 파일 처리 방법 2가지
						//	1. 웹 서버의 로컬 디스크에 저장
						//	  파일의 정보는 DB에 기록

						//	2. DB테이블에 컬럼으로 저장

						// ----------------------------------

						// 로컬파일로 저장하고 DB에 기록하는 방식으로 진행

						// --- UUIP 생성 ---
						UUID uuid = UUID.randomUUID(); // 랜덤 UID 생성

						// 12자리 uid 얻기
						String u = uuid.toString().split("-")[4];
						// ------------------------



						// 로컬 파일 저장소에 파일 저장하기

						// 로컬 저장소 파일 객체
						File up = new File(
								context.getRealPath("upload"),
								item.getName() + "_" + u);
						// 파일의 경로는 "/upload"
						// 파일의 이름은 "원본명_uid"

						
						System.out.println(up); // 저장소 출력
						
						// - - - DB에 업로드 정보 기록하기 - - -
						
						// 파일번호	fileno
						// 게시글 번호 	boardno
						// 원본파일명	origin_name
						// 저장파일명	stored_name
						
						// -------------------------------
						
						System.out.println("insertbn : " + boardno);
						
						boardfile.setBoardno(boardno);
						boardfile.setOriginname(item.getName());
						boardfile.setStoredname(item.getName() + "_" + u);
						boardfile.setFilesize((int)item.getSize());
						
						// DAO를 통해 DB에 INSERT
						
						// 게시물 INSERT
						
						
						// --- 처리가 완료된 파일 업로드 하기 ---
						// 로컬 저장소에 실제 저장
						try {
							item.write(up); // 실제 업로드
							item.delete(); // 임시 파일 삭제
						} catch (Exception e) {
							e.printStackTrace();
						}
						// -------------------------------------
						
						
						
					} // 파일처리 if

				} // 요청 파라미터 처리 while
				boardDao.insert(board);
//				System.out.println("[board] " + board);
				// 첨부파일 INSERT
				boardDao.insertFile(boardfile);
				
				
				
			} // fileupload()메소드
	
	
	
	@Override
	public void update(Board board) {
		
		boardDao.update(board);
	}
	
	
	@Override
	public Board update(HttpServletRequest req, HttpServletResponse resp) {
		// 보드 객체 생성
		Board board = new Board();
		// boardno
//		int boardno = boardDao.selectBoardno();
		
		// 세션객체 생성
		HttpSession session = req.getSession();
		// 응답 객체 Content-Type 설정
		resp.setContentType("text/html; charset=UTF-8");

		// 응답 객체 출력 스트림 얻기
		PrintWriter out = null;
		
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 1. 파일업로드 형태의 데이터가 맞는지 확인
		// enctype이 multipart/form-data가 맞는지 확인
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);

		// 1-1. multipart/form-data 인코딩으로 전송되지 않았을 경우
		if( !isMultipart) {
//			out.append("<h1>enctype이 multipart/form-data가 아님</h1>");
//			out.append("<a href = '/commons/fileupload'>이동</a>");

			return null;
		}

		// 1-2.여기 이후는 multipart/form-data로 요청된 상황임
		// 파일이 전송되었음
		
		// 2. 업로드된 파일을 처리하는 아이템팩토리 객체 생성

		// ItemFactory : 업로드된 파일을 처리하는 방식을 정하는 클래스

		// FileItem : 클라이언트로부터 전송된 데이터를 객체화시킨 것

		// DiskFileItemFactory class
		//	-> 디스크기반(HDD)의 파일아이템 처리 API
		// 	-> 업로드된 파일을 디스크에 임시 저장하고 후처리한다
		DiskFileItemFactory factory = null;
		factory = new DiskFileItemFactory();
		
		// 3. 업로드된 아이템이 용량이 적당히 작으면 메모리에서  처리
		int maxMem = 1 * 1024 * 1024; // 1MB
		factory.setSizeThreshold(maxMem);
		
		// 4. 용량이 적당히 크면 임시파일을 만들어서 처리(디스크)
		ServletContext context = req.getServletContext();

		String path = context.getRealPath("tmp");

		File repository =  new File(path);
		
		factory.setRepository(repository);
		
		// 5. 업로드 허용 용량 기준을 넘지 않을 경우에만 업로드 처리
		int maxFile = 10 * 1024 * 1024; // 10MB

		// 파일 업로드 객체 생성 - DiskFileItemFactory이용해서 생성함
		ServletFileUpload upload = null;
		upload = new ServletFileUpload(factory);

		// 파일 업로드 용량제한 설정 : 10MB
		upload.setFileSizeMax(maxFile);
		
		// ----- 파일 업로드 준비 완료 -----
		
		// 6. 업로드된 데이터 추출(파싱)
		// 임시 파일 업로드도 같이 수행함
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		// 7. 파싱된 데이터 처리하기
		// items 리스트에 요청파라미터가 파싱되어있음

		// 요청정보의 형태
		//	1 - 빈 파일 (용량이 0인 파일)
		// 	2 - form-data(일반적인 요청 파라미터)
		//	3 - 파일
		
		Iterator<FileItem> iter = items.iterator();
		
		// 모든 요청정보 처리
		while(iter.hasNext()) {

			FileItem item = iter.next();

			// 1 - 빈 파일 처리
			if(item.getSize() <= 0) continue;

			// 2 - 일반적인 요청 데이터 처리
			if(item.isFormField()) {
				// form-data일 경우
				// key:value 쌍으로 전달된 요청 파라미터

				// key - getFieldName()
				// value - getString()

				// 처리 방식
				//				out.println("- - - 폼 필드 - - -<br>");
				//				out.println("키 : " + item.getFieldName() + "<br>");
				//				try {
				//				out.println("값 : " + item.getString("utf-8") + "<br>");
				//			} catch (UnsupportedEncodingException e) {
				//				e.printStackTrace();
				//			}
				// key값에 따라 처리방식 다르게 하기
				String key = item.getFieldName();


				if("title".equals(key)) {
					out.println("- - - 폼 필드 - - -<br>");
					out.println("키 : " + item.getFieldName() + "<br>");
					try {
						board.setTitle(item.getString("utf-8"));
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
					try {
						out.println("값 : " + item.getString("utf-8") + "<br>");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}			
				}else if("content".equals(key)){
					out.println("- - - 폼 필드 - - -<br>");
					out.println("키 : " + item.getFieldName() + "<br>");
					try {
						board.setContent(item.getString("utf-8"));
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
					try {
						out.println("값 : " + item.getString("utf-8") + "<br>");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}else if("boardno".equals(key)){
					out.println("- - - 폼 필드 - - -<br>");
					out.println("키 : " + item.getFieldName() + "<br>");
					try {
						board.setBoardno(Integer.parseInt(item.getString("utf-8")));
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
					try {
						out.println("값 : " + item.getString("utf-8") + "<br>");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}// key값 비교 if

				// 세션에서 아이디 받기
				board.setId((String)session.getAttribute("userid"));
//				System.out.println("[session ID : ]" + (String)session.getAttribute("userid"));
//				System.out.println(boardno);
				//						board.setBoardno(boardno);




			}else { // 3 - 파일처리

				// 업로드 파일 처리 방법 2가지
				//	1. 웹 서버의 로컬 디스크에 저장
				//	  파일의 정보는 DB에 기록

				//	2. DB테이블에 컬럼으로 저장

				// ----------------------------------

				// 로컬파일로 저장하고 DB에 기록하는 방식으로 진행

				// --- UUIP 생성 ---
				UUID uuid = UUID.randomUUID(); // 랜덤 UID 생성

				// 12자리 uid 얻기
				String u = uuid.toString().split("-")[4];
				// ------------------------



				// 로컬 파일 저장소에 파일 저장하기

				// 로컬 저장소 파일 객체
				File up = new File(
						context.getRealPath("upload"),
						item.getName() + "_" + u);
				// 파일의 경로는 "/upload"
				// 파일의 이름은 "원본명_uid"


				System.out.println(up); // 저장소 출력

				// - - - DB에 업로드 정보 기록하기 - - -

				// 파일번호	fileno
				// 게시글 번호 	boardno
				// 원본파일명	origin_name
				// 저장파일명	stored_name

				// -------------------------------

//				System.out.println("insertbn : " + boardno);

				boardfile.setBoardno(board.getBoardno());
				boardfile.setOriginname(item.getName());
				boardfile.setStoredname(item.getName() + "_" + u);
				boardfile.setFilesize((int)item.getSize());

				// DAO를 통해 DB에 INSERT

				// 게시물 INSERT


				// --- 처리가 완료된 파일 업로드 하기 ---
				// 로컬 저장소에 실제 저장
				try {
					item.write(up); // 실제 업로드
					item.delete(); // 임시 파일 삭제
				} catch (Exception e) {
					e.printStackTrace();
				}
				// -------------------------------------



			} // 파일처리 if

		} // 요청 파라미터 처리 while
		System.out.println("Update : "+board);
		boardDao.update(board);
		System.out.println("[board] " + board);
		// 첨부파일 INSERT

		boardfileDao.delete(boardfile);
		boardDao.insertFile(boardfile);
		
		return board;
	}

	
	
	@Override
	public Boardfile getBoardfileno(HttpServletRequest req) {
		String param = req.getParameter("bno");
		
		if(param == null) return null;
		
		int boardno = Integer.parseInt(param);
		
		Boardfile boardfile = new Boardfile();
		boardfile.setBoardno(boardno);
		return boardfile;
	
	}
	
	
	
	@Override
	public Boardfile viewfile(Boardfile boardfile) {
		
		return boardDao.selectBoardfileByBoardno(boardfile);
	}
	
	
	
	@Override
	public Boardfile getFile(HttpServletRequest req) {
		// 요청파라미터 fileno 얻기
		boardfile = getFileno(req);
		
		// 파일정보 얻기
		getFile(boardfile);
		
		return boardfile;
	}
	public void getFile(Boardfile boardfile) {
		boardDao.selectByFileno(boardfile);
		
	}
	public Boardfile getFileno(HttpServletRequest req) {
		String param = req.getParameter("fileno");

		// int형으로 형변환
		int fileno = 0;
		if(param!=null && !"".equals(param)) {
			fileno = Integer.parseInt(param);
		}

		// DTO에 저장
		Boardfile boardfile = new Boardfile();
		boardfile.setFileno(fileno);

		return boardfile;
	}
	@Override
	public void delete(Board board) {
		Boardfile boardfile = new Boardfile();
		boardfile.setBoardno(board.getBoardno());
		
		boardfileDao.delete(boardfile);
		boardDao.delete(board);
		
	}
	@Override
	public boolean isRecommend(Recommend recommend) {
		int cnt = recommendDao.selectCntRecommend(recommend);
		
		if(cnt > 0) { //추천했음
			return true;
			
		} else { //추천하지 않았음
			return false;
			
		}
	}
	
	@Override
	public Recommend getRecommend(HttpServletRequest req) {
		//전달파라미터 파싱
		int boardno = 0;
		String param = req.getParameter("boardno");
		if( param!=null && !"".equals(param) ) {
			boardno = Integer.parseInt(param);
		}

		//로그인한 아이디
		String userid = (String) req.getSession().getAttribute("userid");

		Recommend recommend = new Recommend();
		recommend.setBoardno(boardno);
		recommend.setUserid(userid);

		return recommend;
	}
	@Override
	public boolean recommend(Recommend recommend) {
		if( isRecommend(recommend) ) { //추천한 상태
			recommendDao.deleteRecommend(recommend);
			
			return false;
			
		} else { //추천하지 않은 상태
			recommendDao.insertRecommend(recommend);
			
			return true;
			
		}
	}
	@Override
	public int getTotalCntRecommend(Recommend recommend) {
		return recommendDao.selectTotalCntRecommend(recommend);
	}
	@Override
	public Comment getComment(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String boardNo = (String) req.getParameter("boardno");
		String userid = (String) req.getParameter("userid");
		String content = (String) req.getParameter("content");
		
		Comment comment = new Comment();
		comment.setBoardNo(Integer.parseInt(boardNo));
		comment.setUserid(userid);
		comment.setContent(content);
		
		return comment;
	}
	@Override
	public void insertComment(Comment comment) {
		commentDao.insertComment(comment);
	}
	@Override
	public List getCommentList(Board board) {
		return commentDao.selectComment(board);
	}
	@Override
	public boolean deleteComment(Comment comment) {
		commentDao.deleteComment(comment); 
		
		if( commentDao.countComment(comment) > 0 ) {
			return false;
		} else {
			return true;
		}
	}
	
	
	
		
	
	
}

