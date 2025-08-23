package duy;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username.equals("duy")&& password.equals("123")) {
		out.print("Chào mừng bạn, " + username);
		HttpSession session = request.getSession();
		session.setAttribute("name", username);
		} else {
		out.print("Tài khoản hoặc mật khẩu không chính xác");
		request.getRequestDispatcher("Login.html").include(request,
		response);
		}
	}
}
