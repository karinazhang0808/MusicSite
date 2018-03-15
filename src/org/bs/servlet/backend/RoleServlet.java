package org.bs.servlet.backend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bs.model.Role;
import org.bs.service.RoleService;
import org.bs.utils.JDBCHandler;
import org.bs.utils.PageContext;
import org.bs.utils.ResultUtils;

public class RoleServlet extends BaseServlet {
	private RoleService roleService;

	@Override
	public void addInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/backend/role/add_role.jsp").forward(
				request, response);
	}

	public void prepare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("roleList", roleService.search());
	}

	@Override
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Role role = ResultUtils.copyParams(Role.class, request);
		roleService.add(role);
		request.getRequestDispatcher("/backend/role/add_role_success.jsp")
				.forward(request, response);
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		roleService.delete(roleService.findById(id));
		request.getRequestDispatcher("RoleServlet?method=list").forward(
				request, response);
	}

	public void deleteBetch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] roleIds = request.getParameterValues("roleCheckbox");
		for (int i = 0; i < roleIds.length; i++) {
			roleService.delete(roleService.findById(Integer
					.parseInt(roleIds[i])));
		}
		request.getRequestDispatcher("RoleServlet?method=list").forward(
				request, response);
	}

	@Override
	public void modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("role", roleService.findById(id));
		request.getRequestDispatcher("/backend/role/update_role_input.jsp")
				.forward(request, response);
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Role role = ResultUtils.copyParams(Role.class, request);
		roleService.update(role);
		request.getRequestDispatcher("RoleServlet?method=list").forward(
				request, response);
	}

	@Override
	public void get(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.get(request, response);
	}

	@Override
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("page", PageContext.getPage());
		request.setAttribute("roleList", roleService.search());
		request.getRequestDispatcher("/backend/role/role_list.jsp").forward(
				request, response);
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = new JDBCHandler<RoleService>()
				.createProxy(roleService);
	}
}