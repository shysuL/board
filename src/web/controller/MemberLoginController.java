package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Member;
import web.service.face.MemberService;
import web.service.impl.MemberServiceImpl;


@WebServlet("/member/login")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService memberService = new MemberServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
		.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		
		Member member = new Member();
		
		member = memberService.getLoginMember(req);
		
		
		if(memberService.login(member) == true) {
			String userid = req.getParameter("userid");
			// 로그인 성공
			memberService.getMemberByUserid(member);
			
			String usernick = memberService.getMemberByUserid(member).getUsernick();
	
			
			session.setAttribute("login", "true"); // 세션 정보 저장
			session.setAttribute("userid", userid); // 세션 정보 저장
			session.setAttribute("usernick", usernick); // 세션 정보 저장
			
			//리다이렉트
	 		resp.sendRedirect("/main");
		}else {
			//리다이렉트
			req.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
			.forward(req, resp);
		}
		
		
		


	
	
	}
}
