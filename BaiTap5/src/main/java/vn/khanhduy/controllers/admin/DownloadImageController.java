package vn.khanhduy.controllers.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.khanhduy.utils.Constant;

@WebServlet(urlPatterns = "/image") // ?fname=abc.png
public class DownloadImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("fname");
		if (fileName == null || fileName.isBlank()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "File name is required");
			return;
		}

		File file = new File(Constant.UPLOAD_DIR, fileName);
		if (!file.exists() || !file.getCanonicalPath().startsWith(new File(Constant.UPLOAD_DIR).getCanonicalPath())) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		String mimeType = getServletContext().getMimeType(file.getName());
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		resp.setContentType(mimeType);

		try (FileInputStream fis = new FileInputStream(file)) {
			IOUtils.copy(fis, resp.getOutputStream());
		}
	}
}
