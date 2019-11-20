package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Board;
import web.dto.Boardfile;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;


@WebServlet("/board/view")
public class BoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	BoardService boardservice = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Board board = boardservice.getBoardno(req);
		Boardfile boardfile = boardservice.getBoardfileno(req);
		
		
		Board viewBoard = boardservice.view(board);
		Boardfile viewFile = boardservice.viewfile(boardfile);
		
		
		req.setAttribute("board", viewBoard);
		req.setAttribute("boardfile", viewFile);
//		req.setAttribute("boardfile", arg1);
		req.getRequestDispatcher("/WEB-INF/views/board/view.jsp")
			.forward(req, resp);
	}
}
