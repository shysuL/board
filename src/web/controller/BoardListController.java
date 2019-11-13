package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Board;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;


@WebServlet("/board/list")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BoardService boardservice = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Board> boardList = boardservice.getList();
		
		req.setAttribute("list", boardList);
		
		req.getRequestDispatcher("/WEB-INF/views/list.jsp")
		.forward(req, resp);
	}
	
}
