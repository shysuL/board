package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import web.dto.Board;
import web.dto.Boardfile;
import web.dto.Member;
import web.service.face.BoardService;
import web.service.face.MemberService;
import web.service.impl.BoardServiceImpl;
import web.service.impl.MemberServiceImpl;


@WebServlet("/board/write")
public class BoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//view
		req.getRequestDispatcher("/WEB-INF/views/board/write.jsp")
			.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		HttpSession session = req.getSession();
		
	
		
//		Board board = boardService.writeParam(req);
//		boardService.write(board);
		
		// 서비스를 이용한 파일 업로드 처리
		boardService.write(req, resp);
		
		
		System.out.println("title : " + req.getParameter("title"));
		System.out.println("upfile : " + req.getParameter("upfile"));
		//리다이렉트
 		resp.sendRedirect("/board/list");
	}
}
