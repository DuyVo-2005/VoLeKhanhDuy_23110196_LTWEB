package vn.khanhduy.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.khanhduy.models.UserModel;
import vn.khanhduy.services.IUserService;
import vn.khanhduy.services.impl.UserServiceImpl;
import vn.khanhduy.utils.Constant;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// lay toan bo ham trong service
	IUserService service = new UserServiceImpl();// co the dung lai dc

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lay form login
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);

		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			resp.sendRedirect(req.getContextPath() + "/waiting");
			return;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// post du lieu

		// ma hoa tieng viet
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		// lay tham so tu view
		String username = req.getParameter("uname");
		String password = req.getParameter("psw");
		String remember = req.getParameter("remember");

		// kiem tra tham so
		boolean isRememberMe = false;
		if ("on".equals(remember)) {
			isRememberMe = true;
		}
		String alertMsg = "";// kt dang nhap thanh cong hay that bai -> bao loi neu co
		if (username.isEmpty() || password.isEmpty()) {
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
			return;
		}

		// xu ly bai toan
		UserModel user = service.login(username, password);
		if (user != null) {
			HttpSession session = req.getSession(true);
			session.setAttribute("account", user);
			if (isRememberMe) {
				saveRemeberMe(resp, username);
			}
			resp.sendRedirect(req.getContextPath() + "/waiting");// controller waiting phan chia giao dien (user,
																	// admin,...)
		} else {
			System.out.println(username+password);
			alertMsg = "Tài khoản hoặc mật khẩu không đúng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		}
	}

	private void saveRemeberMe(HttpServletResponse response, String username) {
		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
		cookie.setMaxAge(30 * 60);
		response.addCookie(cookie);
	}
}
