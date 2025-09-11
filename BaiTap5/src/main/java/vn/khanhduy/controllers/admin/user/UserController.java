package vn.khanhduy.controllers.admin.user;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.khanhduy.entity.Users;
import vn.khanhduy.services.IUserService;
import vn.khanhduy.services.impl.UserServiceImpl;
import vn.khanhduy.utils.Constant;

@WebServlet(urlPatterns = { "/admin/edit" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGetEdit(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPostEdit(req, resp);
	}

	private String getFileName(Part part) {
		return part.getSubmittedFileName();
	}

	protected void doGetEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		Users currentUser = (Users) session.getAttribute("USERMODEL");// user đang login

		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-category.jsp");
		dispatcher.forward(req, resp);
	}

	protected void doPostEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");

			// Lấy id từ form
			int id = Integer.parseInt(req.getParameter("id"));

			// Lấy category cũ từ DB
			Users user = userService.findById(id);
			// Cập nhật tên mới

			// Upload file mới nếu có
			String fileName = "";
			for (Part part : req.getParts()) {
				fileName = getFileName(part);
				if (fileName != null && !fileName.isEmpty()) {
					String uploadPath = Constant.UPLOAD_DIR + File.separator + "user";
					File uploadDirCategory = new File(uploadPath);
					if (!uploadDirCategory.exists())
						uploadDirCategory.mkdirs();

					part.write(uploadPath + File.separator + fileName);
					user.setImage("user/" + fileName);
				}
			}

			// Update DB
			userService.edit(user);

			resp.sendRedirect(req.getContextPath() + "/admin/home");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
