package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Paging;
import web.dto.Board;
import web.dto.Boardfile;
import web.dto.Comment;
import web.dto.Member;
import web.dto.Recommend;

public interface BoardService {
	

	/**
	 * 게시글 목록 조회
	 * 
	 * 
	 * @return List - 게시글 목록
	 */
	public List<Board> getList() ;
	/**
	 * 페이징 정보를 활용하여 보여질 게시글 목록만 조회
	 * 
	 * @param paging  - 페이징 정보
	 * @return List - 게시글 목록
	 */
	public List<Board> getList(Paging paging);

	public Board getBoardno(HttpServletRequest req);
	
	
	/**
	 * 상세보기 게시글 조회
	 * 
	 * @param viewBoard - 상세보기할 boardno를 가진 객체
	 * @return Board - 상세보기할 게시글 조회 결과
	 */
	public Board view(Board board);
	
	public Board hitplus(Board board);

	/**
	 * 요청 파라미터 curPage를 파싱한다
	 * Board TB와 curPage값을 이용한 Paging 객체를 생성하고 반환
	 * 
	 * @param req - 요청정보 객체
	 * @return Paging - 페이징 정보
	 */
	public Paging getPaging(HttpServletRequest req);
		
	
	public Board writeParam(HttpServletRequest req );
	
	public void write(Board board);
	
	public void write(HttpServletRequest req, HttpServletResponse resp);
	
	
	public void update(Board board);
	
	public Board update(HttpServletRequest req, HttpServletResponse resp);
	
	
	
	
	public Boardfile getBoardfileno(HttpServletRequest req);
	
	
	public Boardfile viewfile(Boardfile boardfile);
	
	public Boardfile getFile(HttpServletRequest req);
	
	public void getFile(Boardfile boardfile);
	
	public Boardfile getFileno(HttpServletRequest req);
	
	
	public void delete(Board board);
	
	/**
	 * 게시글 추천 상태 조회
	 * 
	 * @param recommend - 추천 상태 체크 객체
	 * @return boolean - true:추천했음, false:추천하지않았음
	 */
	public boolean isRecommend(Recommend recommend);
	
	public Recommend getRecommend(HttpServletRequest req);
	
	public boolean recommend(Recommend recommendParam);
	
	public int getTotalCntRecommend(Recommend recommendParam);
	
	
	/**
	 * 댓글 전달파라미터 꺼내기
	 */
	public Comment getComment(HttpServletRequest req);
	
	/**
	 * 댓글 입력
	 * 
	 * @param comment - 삽입될 댓글
	 */
	public void insertComment(Comment comment);
	
	/**
	 * 댓글 리스트
	 * 
	 * @param board - 댓글이 조회될 게시글
	 * @return List - 댓글 리스트
	 */
	public List getCommentList(Board board);
	
	/**
	 * 댓글 삭제
	 *  
	 * @param comment - 삭제할 댓글
	 * @return boolean - 삭제 성공 여부
	 */
	public boolean deleteComment(Comment comment);
	
	
	
}
