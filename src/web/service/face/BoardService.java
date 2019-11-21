package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Paging;
import web.dto.Board;
import web.dto.Boardfile;
import web.dto.Member;

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
	public Paging getPasing(HttpServletRequest req);
		
	
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
	
}
