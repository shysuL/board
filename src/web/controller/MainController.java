package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		HttpSession session = req.getSession();
		// VIEW
		req.getRequestDispatcher("/WEB-INF/views/main.jsp")
			.forward(req, resp);
		System.out.println("login : " + req.getSession().getAttribute("login"));
		System.out.println("id : " + req.getSession().getAttribute("userid"));
		System.out.println("nick : " + req.getSession().getAttribute("usernick"));
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}

}
