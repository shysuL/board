package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Board;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;


@WebServlet("/board/view")
public class BoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	BoardService boardservice = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Board board = boardservice.getBoardno(req);
		
		Board viewBoard = boardservice.view(board);
		
		req.setAttribute("board", viewBoard);
		
		req.getRequestDispatcher("/WEB-INF/views/board/view.jsp")
			.forward(req, resp);
	}
}
