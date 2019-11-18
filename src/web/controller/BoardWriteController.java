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
import web.dto.Member;
import web.service.face.BoardService;
import web.service.face.MemberService;
import web.service.impl.BoardServiceImpl;
import web.service.impl.MemberServiceImpl;


@WebServlet("/board/write")
public class BoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardService = new BoardServiceImpl();
	MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//view
		req.getRequestDispatcher("/WEB-INF/views/board/write.jsp")
			.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		HttpSession session = req.getSession();
		Board board = boardService.writeParam(req);
		
//		Member member = new Member();
//		member = (Member) session.getAttribute("userid");
		
//		memberService.getMemberByUserid(member);
		
		
		boardService.write(board);
		
		//리다이렉트
 		resp.sendRedirect("/board/list");
	}
}
