package vn.khanhduy.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/home"}) /* , "/login", "/logout, "/register", "/forgotpass"}) */
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/home.jsp").forward(req, resp);

		 //String url = req.getRequestURL().toString();
		 //if(url.contains("home")) {
		 //req.getRequestDispatcher("/views/home.jsp").forward(req, resp); } else
		//if(url.contains("logout")) { getLogout(req, resp); }
		  //else if (url.contains("register")) { getRegister(req, resp); }
		 
		/*
		 * } else if (url.contains("logout")) { getLogout(req, resp); }
		 */

	}

	/*
	 * protected void getLogin(HttpServletRequest req, HttpServletResponse resp)
	 * throws ServletException, IOException { // lay form login
	 * req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	 * 
	 * HttpSession session = req.getSession(false); if (session != null &&
	 * session.getAttribute("account") != null) {
	 * resp.sendRedirect(req.getContextPath() + "/waiting"); return; } }
	 */

	/*
	 * protected void getLogout(HttpServletRequest req, HttpServletResponse resp)
	 * throws ServletException, IOException { //Huy Session HttpSession session =
	 * req.getSession(); session.removeAttribute("account");// or
	 * session.invalidate();
	 * 
	 * Cookie[] cookies = req.getCookies(); for(Cookie cookie: cookies) {
	 * if(Constant.COOKIE_REMEMBER.equals(cookie.getName())) { cookie.setMaxAge(0);
	 * // remove cookie resp.addCookie(cookie); //add again return; } }
	 * //req.getRequestDispatcher("Login.html").include(req,resp);
	 * resp.sendRedirect("./views/login.jsp"); }
	 */

	/*
	 * protected void getRegister(HttpServletRequest req, HttpServletResponse resp)
	 * throws ServletException, IOException { // lay form register
	 * req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
	 * 
	 * HttpSession session = req.getSession(false); if (session != null &&
	 * session.getAttribute("account") != null) {
	 * resp.sendRedirect(req.getContextPath() + "/waiting"); return; } }
	 */
}
