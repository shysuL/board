package web.dao.face;

import java.util.List;

import util.Paging;
import web.dto.Board;
import web.dto.Boardfile;

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
	
	/**
	 * 상세보기 게시글 조회
	 * 
	 * @param viewBoard - 조회 대상
	 * @return Board - 게시글 조회 결과
	 */
	public Board selectBoardByBoardno(Board board);

	/**
	 * 조회되는 게시글의 조회수 +1
	 * 
	 * @param viewBoard - 조회 대상
	 */
	public void updateHit(Board board);

	/**
	 *  총 게시글 수 조회
	 * 
	 * @return int - 총 게시글 수
	 */
	public int selectCntAll();

	
	public void insert(Board board);
	
	
	
	
	public int selectBoardno();
	
	
	public void insertFile(Boardfile boardFile);
	
	public Board selectByBoardno(Board board);
	
	public void update(Board board);
	
	
	public Boardfile selectBoardfileByBoardno(Boardfile boardfile);

	public void selectByFileno(Boardfile boardfile);

	public void delete(Board board);
	
	
	
	public void insertRecommend(Board board);
	
	public void deleteRecommend(Board board);

}
