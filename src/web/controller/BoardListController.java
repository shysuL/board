package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Paging;
import web.dto.Board;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;


@WebServlet("/board/list")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService boardService = new BoardServiceImpl();
	
	BoardService boardservice = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 요청 파라미터에서 curPage를 구하고 Paging 객체 반환
		Paging paging = boardService.getPasing(req);
		System.out.println("BoardListController : " + paging);
		// Paging 객체를 MODEL값으로 지정
		req.setAttribute("paging", paging);
		
		
		
		// 게시글 리스트
		List<Board> boardList = boardservice.getList(paging);
		
		req.setAttribute("list", boardList);
		
		req.getRequestDispatcher("/WEB-INF/views/board/list.jsp")
		.forward(req, resp);
	}
	
}
