package org.bs.servlet.backend;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {
	private String[] urlPatterns = null;
	private String[] escapeUrls = null;

	public void destroy() {
	}

	public void doFilter(ServletRequest reqs, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) reqs;
		HttpServletResponse response = (HttpServletResponse) resp;
		String url = request.getRequestURI();
		// if (url.indexOf(".jsp")>-1||url.indexOf("Servlet")>-1){
		// if (url.indexOf("login.jsp")<0&&url.indexOf("UserServlet")<0){
		// if(request.getSession().getAttribute("login_user") == null){
		// response.sendRedirect(request.getContextPath()+"/backend/login.jsp");
		// }
		// }
		if (isIntercept(url)) {
			if (request.getSession().getAttribute("login_user") == null) {
				response.sendRedirect(request.getContextPath()
						+ "/backend/login.jsp");
			}
		}
		chain.doFilter(request, response);
	}

	public boolean isIntercept(String url) {
		boolean urlPatternFlag = false;
		boolean escapeFlag = false;

		for (String s : urlPatterns) {
			if (url.indexOf(s) > -1) {
				urlPatternFlag = true;
				break;
			}
		}
		for (String s : escapeUrls) {
			if (url.indexOf(s) > -1) {
				escapeFlag = false;
				break;
			} else {
				escapeFlag = true;
			}
		}
		return urlPatternFlag && escapeFlag;
	}

	public void init(FilterConfig config) throws ServletException {
		String urlPattern = config.getInitParameter("urlPattern");
		String escape = config.getInitParameter("escape");
		urlPattern.replace('，', ',');
		escape.replace('，', ',');
		urlPatterns = urlPattern.split(",");
		escapeUrls = escape.split(",");
	}

}
