package web.dao.face;

import java.util.List;

import web.dto.Board;

public interface BoardDao {

	public List<Board> selectAll();
	
	public Board selectBoardByBoardno(Board board);
	
	public void updateHit(Board board);

}
