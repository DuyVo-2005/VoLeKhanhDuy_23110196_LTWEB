package vn.khanhduy.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.khanhduy.entities.Users;
import vn.khanhduy.services.IUserService;
import vn.khanhduy.services.impl.UserServiceImpl;
import vn.khanhduy.utils.Constant;
import vn.khanhduy.utils.SessionUtil;

@WebServlet(urlPatterns = { "/home", "/login", "/logout" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// lay toan bo ham trong service
	IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath(); // lấy /home or /login, /logout

		switch (path) {
		case "/login":
			doGetLogin(req, resp);
			break;
		case "/logout":
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + "/login");
			break;
		default:
			req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		switch (path) {
		case "/login": {
			doPostLogin(req, resp);
			break;
		}
		case "/logout": {

			break;
		}
		}
	}

	protected void doGetLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lay form login
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);

		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			resp.sendRedirect(req.getContextPath() + "/waiting");
			return;
		}

	}

	protected void doPostLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		Users user = userService.login(username, password);
		Users loginUser = userService.findByUsername(username);
		if (user != null) {
			HttpSession session = req.getSession(true);
			// sẽ lấy ra đối tượng HttpSession gắn với request hiện tại
			// Nếu chưa có session:
			// - true - tạo mới một session rồi trả về
			// - false -> không tạo mới, mà trả về null
			//SessionUtil.getInstance().setValue(req, "USERMODEL", loginUser);
			session.setAttribute("USERMODEL", loginUser);
			session.setAttribute("account", user);
			
			
			if (isRememberMe) {
				saveRemeberMe(resp, username);
			}
			resp.sendRedirect(req.getContextPath() + "/waiting");// controller waiting phan chia giao dien (user,
																	// admin,...)
		} else {
			System.out.println(username + password);
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
