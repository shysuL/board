package web.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.BoardDao;
import web.dao.impl.BoardDaoImpl;
import web.dto.Board;
import web.service.face.BoardService;

public class BoardServiceImpl implements BoardService{

	BoardDao boardDao = new BoardDaoImpl();
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
	
}

