package vn.khanhduy.controllers.admin.category;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

//import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.DiskFileItemFactory.Builder;
import org.apache.commons.fileupload2.core.FileItemFactory;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import org.apache.commons.fileupload2.core.FileItem;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.khanhduy.services.impl.CategoryServiceImpl;
import vn.khanhduy.utils.Constant;
import vn.khanhduy.models.CategoryModel;
import vn.khanhduy.services.ICategoryService;

@WebServlet(urlPatterns = { "/admin/category/edit" })
public class CategoryEditController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ICategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		CategoryModel category = cateService.get(Integer.parseInt(id));
		req.setAttribute("category", category);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/editcategory.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/*CategoryModel category = new CategoryModel();

		// --- Factory ---
		FileItemFactory factory = DiskFileItemFactory.builder().setBufferSize(10 * 1024); // 10KB

		// --- Uploader ---
		JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");

		try {
			resp.setContentType("text/html; charset=UTF-8");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");

			List<org.apache.commons.fileupload2.core.FileItem> items = upload.parseRequest(req);

			for (org.apache.commons.fileupload2.core.FileItem item : items) {
				if (item.isFormField()) {
					switch (item.getFieldName()) {
					case "id":
						category.setCateid(Integer.parseInt(item.getString("UTF-8")));
						break;
					case "name":
						category.setCatename(item.getString("UTF-8"));
						break;
					}
				} else {
					if ("icon".equals(item.getFieldName()) && item.getSize() > 0) {
						String originalFileName = new File(item.getName()).getName();
						int dotIndex = originalFileName.lastIndexOf(".");
						String ext = (dotIndex >= 0) ? originalFileName.substring(dotIndex + 1) : "png";

						String fileName = System.currentTimeMillis() + "." + ext;

						File uploadDir = new File(Constant.DIR + "/category");
						if (!uploadDir.exists()) {
							uploadDir.mkdirs();
						}

						File storeFile = new File(uploadDir, fileName);
						item.write(storeFile.toPath()); // 2.x dùng Path

						category.setIcon("category/" + fileName);
					} else {
						category.setIcon(null);
					}
				}
			}

			cateService.edit(category);
			resp.sendRedirect(req.getContextPath() + "/admin/category/list");

		} catch (Exception e) {
			throw new ServletException("Upload error", e);
	}*/
	}
}

/*
 * @Override protected void doPost(HttpServletRequest req, HttpServletResponse
 * resp) throws ServletException, IOException { CategoryModel category = new
 * CategoryModel(); DiskFileItem diskFileItemFactory = new DiskFileItem(null,
 * null, legacyHeadHandling, null, 0, null, null, null);
 * JakartaServletFileUpload servletFileUpload = new
 * JakartaServletFileUpload(diskFileItemFactory);
 * servletFileUpload.setHeaderEncoding("UTF-8"); try {
 * resp.setContentType("text/html"); resp.setCharacterEncoding("UTF-8");
 * req.setCharacterEncoding("UTF-8"); List<FileItem> items =
 * servletFileUpload.parseRequest(req); for (FileItem item : items) { if
 * (item.getFieldName().equals("id")) {
 * category.setCateid(Integer.parseInt(item.getString())); } else if
 * (item.getFieldName().equals("name")) {
 * category.setCatename(item.getString("UTF-8")); } else if
 * (item.getFieldName().equals("icon")) { if (item.getSize() > 0) {// neu co
 * file d String originalFileName = item.getName(); int index =
 * originalFileName.lastIndexOf("."); String ext =
 * originalFileName.substring(index + 1); String fileName =
 * System.currentTimeMillis() + "." + ext; File file = new File(Constant.DIR +
 * "/category/" + fileName); item.write(file); category.setIcon("category/" +
 * fileName); } else { category.setIcon(null); } } } cateService.edit(category);
 * resp.sendRedirect(req.getContextPath() + "/admin/category/list"); } catch
 * (FileUploadException e) { e.printStackTrace(); } catch (Exception e) {
 * e.printStackTrace(); } }
 */
