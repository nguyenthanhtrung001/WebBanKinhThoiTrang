package management.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestURI = request.getRequestURI();
		
//		if (requestURI.contains("/user/")) {
//			Object user = request.getSession().getAttribute("user");
//			
//			if (user == null) {
//				response.sendRedirect("/WebBanKinh/login");
//				return false; // Dừng xử lý yêu cầu hiện tại
//			}
//		}
//		
		return true; // Tiếp tục xử lý yêu cầu
	}
}