package web.dao.face;

import java.util.List;

import util.Paging;
import web.dto.Board;

public interface BoardDao {

	/**
	 * 게시글 전체 목록 조회
	 * 
	 * @return - 조회된 게시글 목록
	 */
	public List<Board> selectAll();
	
	/**
	 * 페이징 대상 게시글 목록 조회
	 * 
	 * @param paging - 페이징 정보
	 * @return List - 조회된 게시글 목록
	 */
	public List<Board> selectAll(Paging paging);
	
	public Board selectBoardByBoardno(Board board);
	
	public void updateHit(Board board);

	/**
	 *  총 게시글 수 조회
	 * 
	 * @return int - 총 게시글 수
	 */
	public int selectCntAll();


}
