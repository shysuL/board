package web.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import util.Paging;
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
	@Override
	public Paging getPasing(HttpServletRequest req) {
		
		// * 요청 파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param!=null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}
//		System.out.println("curPage : " + curPage);
		
		// * Board TB와 curPage값을 이용한 Paging 객체를 생성하고 반환
		int totalCount = boardDao.selectCntAll();
		
		// Paging  객체 생성
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}
	@Override
	public List<Board> getList(Paging paging) {
		return boardDao.selectAll(paging);
	}
	
}

