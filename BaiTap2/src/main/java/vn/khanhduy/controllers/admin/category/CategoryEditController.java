package vn.khanhduy.controllers.admin.category;

import java.io.File;
import java.io.IOException;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.khanhduy.services.impl.CategoryServiceImpl;
import vn.khanhduy.utils.Constant;
import vn.khanhduy.models.CategoryModel;
import vn.khanhduy.services.ICategoryService;

@WebServlet(urlPatterns = { "/admin/category/edit" })
@MultipartConfig
public class CategoryEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ICategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("cateid");
		CategoryModel category = cateService.get(Integer.parseInt(id));
		req.setAttribute("category", category);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-category.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    try {
	        resp.setContentType("text/html");
	        resp.setCharacterEncoding("UTF-8");
	        req.setCharacterEncoding("UTF-8");

	        // Lấy id từ form
	        int id = Integer.parseInt(req.getParameter("cateid"));

	        // Lấy category cũ từ DB        
	        CategoryModel category = cateService.get(id);
	        if (category == null) {
	            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
	            return;
	        }

	        // Cập nhật tên mới
	        category.setCatename(req.getParameter("catename"));

	        // Upload file mới nếu có
	        String fileName = "";
	        for (Part part : req.getParts()) {
	            fileName = getFileName(part);
	            if (fileName != null && !fileName.isEmpty()) {
	                String uploadPath = Constant.UPLOAD_DIR + File.separator + "category";
	                File uploadDirCategory = new File(uploadPath);
	                if (!uploadDirCategory.exists()) uploadDirCategory.mkdirs();

	                part.write(uploadPath + File.separator + fileName);
	                category.setIcon("category/" + fileName);
	            }
	        }

	        // Update DB
	        cateService.edit(category);

	        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	private String getFileName(Part part) {
	    return part.getSubmittedFileName();
	}

}
