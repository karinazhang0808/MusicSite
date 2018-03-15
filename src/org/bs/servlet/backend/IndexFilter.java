package org.bs.servlet.backend;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.sun.xml.internal.ws.api.message.Attachment;

public class IndexFilter implements Filter {
	ServletContext context;

	// private Logger logger = Logger.getLogger(AttachmentFilter.class);

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// �ж������URI���Ƿ��"upload_image"
		// ����"upload_image"�����d:/temp/uploadĿ¼�ж�ȡ��Ӧ���ļ������Ұ��ļ��������д��response

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String requestURI = req.getRequestURI();
		if (requestURI.equals("/")) {
			resp.sendRedirect("MusicServlet?method=index");
		} else {
			// �����������ͨ�У�
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		context = filterConfig.getServletContext();
	}

}
