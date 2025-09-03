package vn.khanhduy.controllers.admin.category;

import java.io.IOException;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.khanhduy.models.CategoryModel;
import vn.khanhduy.services.ICategoryService;
import vn.khanhduy.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/admin/category/add" })
public class CategoryAddController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ICategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-category.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * CategoryModel category = new CategoryModel(); DiskFileItemFactory
		 * diskFileItemFactory = new DiskFileItemFactory(); ServletFileUpload
		 * servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		 * servletFileUpload.setHeaderEncoding("UTF-8"); try {
		 * resp.setContentType("text/html"); resp.setCharacterEncoding("UTF-8");
		 * req.setCharacterEncoding("UTF-8"); List<FileItem> items =
		 * servletFileUpload.parseRequest(req); for (FileItem item : items) { if
		 * (item.getFieldName().equals("name")) {
		 * category.setName(item.getString("UTF-8")); } else if
		 * (item.getFieldName().equals("icon")) { String originalFileName =
		 * item.getName(); int index = originalFileName.lastIndexOf("."); String ext =
		 * originalFileName.substring(index + 1); String fileName =
		 * System.currentTimeMillis() + "." + ext; File file = new File(Constant.DIR +
		 * "/category/" + fileName); item.write(file); category.setIcon("category/" +
		 * fileName); } } cateService.insert(category);
		 * resp.sendRedirect(req.getContextPath() + "/admin/category/list"); } catch
		 * (FileUploadException e) { e.printStackTrace(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
	}
}
