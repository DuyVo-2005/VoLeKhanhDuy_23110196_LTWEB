package vn.khanhduy.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.khanhduy.models.UserModel;
import vn.khanhduy.services.IUserService;
import vn.khanhduy.services.impl.UserServiceImpl;
import vn.khanhduy.utils.Email;

@WebServlet(urlPatterns = "/forgotpassword")
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("uname");
		String email = req.getParameter("email");

		IUserService userService = new UserServiceImpl();//
		UserModel user = userService.get(username);

		if (user != null) {
			if (user.getEmail().equals(email) && user.getUserName().equals(username)) {
				Email em = new Email();
				boolean test = em.sendPasswordToEmail(user);
				if (test) {
					req.setAttribute("message", "Kiểm tra email để nhận mật khẩu");
				} else {
					req.setAttribute("error", "Lỗi khi gửi mail");
				}
			} else {
				req.setAttribute("error", "Email hoặc username không tồn tại trong hệ thống");
			}

		} else {
			req.setAttribute("error", "Không tìm thấy tài khoản: " + username);
		}
		req.getRequestDispatcher("/views/forgotpassword.jsp").forward(req, resp);
	}
}
