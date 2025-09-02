package vn.khanhduy.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.khanhduy.utils.Constant;

@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.removeAttribute("account");// or session.invalidate();
		
		Cookie[] cookies = req.getCookies();
		for(Cookie cookie: cookies) {
			if(Constant.COOKIE_REMEMBER.equals(cookie.getName())) {
				cookie.setMaxAge(0); // remove cookie
				resp.addCookie(cookie); //add again
				break;
			}
		}
		//req.getRequestDispatcher("/views/login.jsp").include(req,resp);
		resp.sendRedirect("./login");
	}
}
