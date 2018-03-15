package org.bs.servlet.backend;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bs.utils.BeanFactory;
import org.bs.utils.PageContext;

public abstract class BaseServlet extends HttpServlet {
	private boolean DiInited = false;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String methodName = request.getParameter("method") == null ? "list"
				: request.getParameter("method");

		PageContext.getPage().setPageNo(
				Integer.parseInt(request.getParameter("pageNo") == null
						|| "".equals(request.getParameter("pageNo")) ? "1"
						: request.getParameter("pageNo")));
		PageContext.getPage().setPageSize(
				Integer.parseInt(request.getParameter("pageSize") == null
						|| "".equals(request.getParameter("pageSize")) ? "10"
						: request.getParameter("pageSize")));

		if (!DiInited) {
			BeanFactory factory = (BeanFactory) this.getServletContext()
					.getAttribute("beanFactory");
			Method[] methods = this.getClass().getMethods();
			for (int i = 0; i < methods.length; i++) {
				String DIMethodName = methods[i].getName();
				if (DIMethodName.startsWith("set")) {
					String beanName = DIMethodName.substring(3, 4)
							.toLowerCase() + DIMethodName.substring(4);
					try {
						methods[i].invoke(this, factory.getBean(beanName));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		DiInited = true;

		try {
			Method method = this.getClass().getMethod(
					methodName,
					new Class[] { HttpServletRequest.class,
							HttpServletResponse.class });
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		PageContext.remove();
	}

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void addInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void get(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
