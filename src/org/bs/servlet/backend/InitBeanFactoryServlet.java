package org.bs.servlet.backend;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.bs.utils.BeanFactory;
import org.bs.utils.ClassPathXmlBeanFactory;

public class InitBeanFactoryServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		long begin = System.currentTimeMillis();
		BeanFactory factory = new ClassPathXmlBeanFactory();
		this.getServletContext().setAttribute("beanFactory", factory);
		long end = System.currentTimeMillis();
		System.out.println("��ʼ����� ʹ�õ�ʱ�䣺" + (end - begin) + "ms");
	}

}
