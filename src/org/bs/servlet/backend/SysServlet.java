package org.bs.servlet.backend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bs.model.User;

import org.bs.service.UserService;
import org.bs.utils.JDBCHandler;
import org.bs.utils.PageContext;
import org.bs.utils.ResultUtils;

public class SysServlet extends BaseServlet {

	private UserService userService;

	public void checkOld(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setHeader("Cache-Control", "no-cache");
		User user = (User) request.getSession().getAttribute("login_user");
		String s = request.getParameter("password");
		PrintWriter out = response.getWriter();
		if (user.getPassword().equals(s))
			out.write("1");
		else
			out.write("0");
		out.flush();
		out.close();
	}

	public void updatePassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("login_user");
		String password = request.getParameter("new");
		user.setPassword(password);
		userService.updatePassword(user);
		response.sendRedirect(request.getContextPath()
				+ "/backend/sys/update_password_success.jsp");
	}

	public void userInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("login_user");
		String path = "/backend/UserServlet?method=modify&id=" + user.getId();
		response.sendRedirect(request.getContextPath() + path);
	}

	public void setUserService(UserService userService) {
		this.userService = new JDBCHandler<UserService>()
				.createProxy(userService);
	}
}
