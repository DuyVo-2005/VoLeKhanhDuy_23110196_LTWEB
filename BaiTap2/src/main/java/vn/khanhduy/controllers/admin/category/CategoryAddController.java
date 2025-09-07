package vn.khanhduy.controllers.admin.category;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.khanhduy.models.CategoryModel;
import vn.khanhduy.services.ICategoryService;
import vn.khanhduy.services.impl.CategoryServiceImpl;
import vn.khanhduy.utils.Constant;

@WebServlet(urlPatterns = { "/admin/category/add" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
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
	    CategoryModel category = new CategoryModel();

	    File uploadDir = new File(Constant.UPLOAD_DIR);
	    if (!uploadDir.exists()) uploadDir.mkdirs();

	    try {
	        resp.setContentType("text/html");
	        resp.setCharacterEncoding("UTF-8");
	        req.setCharacterEncoding("UTF-8");

	        // Lấy tên category từ form
	        category.setCatename(req.getParameter("name"));

	        String fileName = "";
	        String newFileName = "";
	        for (Part part : req.getParts()) {
	            fileName = getFileName(part);
	            if (fileName != null && !fileName.isEmpty()) {
	                String uploadPath = Constant.UPLOAD_DIR + File.separator + "category";
	                File uploadDirCategory = new File(uploadPath);
	                if (!uploadDirCategory.exists()) uploadDirCategory.mkdirs();
	                
	                // Tạo tên mới tránh trùng
	                String ext = fileName.substring(fileName.lastIndexOf("."));
	                newFileName = System.currentTimeMillis() + ext;

	                part.write(uploadPath + File.separator + newFileName);
	                category.setIcon("category/" + newFileName);
	            }
	        }

	        req.setAttribute("message", "File " + newFileName + " uploaded successfully!");
	        cateService.insert(category);
	        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
	    } catch (FileNotFoundException fne) {
	        req.setAttribute("message", "There was an error: " + fne.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	private String getFileName(Part part) {
	    return part.getSubmittedFileName();
	}

}
