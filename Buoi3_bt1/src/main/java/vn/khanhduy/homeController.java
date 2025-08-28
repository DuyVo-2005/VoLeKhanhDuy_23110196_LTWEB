package vn.khanhduy;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/hi","/home","/trangchu"})
public class homeController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		
		String name = req.getParameter("ten");
		
		String holot = req.getParameter("holot");

		
		PrintWriter out = resp.getWriter();
		
		out.println("Hello world!" + name + " " + holot);
		
		out.close();
	}
}
