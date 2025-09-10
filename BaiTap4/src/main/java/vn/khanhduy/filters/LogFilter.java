package vn.khanhduy.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.khanhduy.entities.Users;
import vn.khanhduy.utils.SessionUtil;
import vn.khanhduy.utils.Constant;

public class LogFilter implements Filter {
	public LogFilter() {
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("LogFilter init!");
	}

	@Override
	public void destroy() {
		System.out.println("LogFilter destroy!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String url = req.getRequestURI();
		if (url.startsWith("/admin")) {
			Users user = (Users) SessionUtil.getInstance().getValue((HttpServletRequest) request, "user");
			if (user != null) {
				// admin được vào
				if (user.getRole().getRoleId() == Constant.ADMIN) {
					chain.doFilter(request, response);
				} else{
					// không đủ quyền -> đẩy về trang home
					resp.sendRedirect(req.getContextPath() + "/admin/home");
				}
			} else {
				// chưa đăng nhập -> đẩy về login
				resp.sendRedirect(req.getContextPath() + "/login");
			}
			
			if (url.startsWith(req.getContextPath() + "/manager")) {
		        if (user != null) {

		            if (user.getRole().getRoleId() == Constant.MANAGER) {
		                chain.doFilter(request, response);                
		            } else {
		                resp.sendRedirect(req.getContextPath() + "/manager/home");
		            }
		        } else {
		            resp.sendRedirect(req.getContextPath() + "/login");	            
		        }
		    }

		    if (url.startsWith(req.getContextPath() + "/user")) {
		        if (user != null) {
		           if(user.getRole().getRoleId() ==  Constant.USER) {
		        	   chain.doFilter(request, response);
		           }
		           else {
		        	   resp.sendRedirect(req.getContextPath() + "/user/home");
		           }
		        } else {
		           resp.sendRedirect(req.getContextPath() + "/login");
		        }
		    }
		}
		else {
			chain.doFilter(request, response);
		}
	}
}
