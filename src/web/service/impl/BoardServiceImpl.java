package web.service.impl;

import java.util.List;

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
	
}

