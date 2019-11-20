package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Board;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;


@WebServlet("/board/update")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Board board = boardService.getBoardno(req);
		
		Board viewBoard = boardService.view(board);
		
		req.setAttribute("board", viewBoard);
		
		// view
		req.getRequestDispatcher("/WEB-INF/views/board/update.jsp")
			.forward( req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		Board board = boardService.writeParam(req);
		boardService.update(board);
		
		//리다이렉트
 		resp.sendRedirect("/board/view");
	
	}
}
