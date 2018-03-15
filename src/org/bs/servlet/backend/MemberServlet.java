package org.bs.servlet.backend;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bs.model.Member;
import org.bs.service.MemberService;
import org.bs.utils.JDBCHandler;
import org.bs.utils.PageContext;
import org.bs.utils.ResultUtils;
import org.bs.model.User;
import org.bs.service.UserService;

public class MemberServlet extends BaseServlet {
	private MemberService memberService;
	private UserService userService;

	@Override
	public void addInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("userList", userService.search());
		request.getRequestDispatcher("/backend/member/add_member.jsp").forward(
				request, response);
	}

	public void prepare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("memberList", memberService.search());
	}

	@Override
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Member member = ResultUtils.copyParams(Member.class, request);
		memberService.add(member);
		request.getRequestDispatcher("/backend/member/add_member_success.jsp")
				.forward(request, response);
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		memberService.delete(memberService.findById(id));
		request.getRequestDispatcher("MemberServlet?method=list").forward(
				request, response);
	}

	public void deleteBetch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] memberIds = request.getParameterValues("memberCheckbox");
		for (int i = 0; i < memberIds.length; i++) {
			memberService.delete(memberService.findById(Integer
					.parseInt(memberIds[i])));
		}
		request.getRequestDispatcher("MemberServlet?method=list").forward(
				request, response);
	}

	@Override
	public void modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("member", memberService.findById(id));
		request.setAttribute("userList", userService.search());
		request.getRequestDispatcher("/backend/member/update_member_input.jsp")
				.forward(request, response);
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Member member = ResultUtils.copyParams(Member.class, request);
		memberService.update(member);
		request.getRequestDispatcher("MemberServlet?method=list").forward(
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
		request.setAttribute("memberList", memberService.search());
		request.getRequestDispatcher("/backend/member/member_list.jsp")
				.forward(request, response);
	}

	public void add1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IllegalAccessException,
			InvocationTargetException {
		Member member = ResultUtils.copyParams(Member.class, request);
		User user = new User();
		BeanUtils.copyProperties(user, member);
		user.setRole(member.getRoles());
		String password = request.getParameter("password");
		user.setPassword(password);
		userService.add(user);
		member.setUser(user);
		memberService.add(member);
		request.getRequestDispatcher("/backend/member/add_member_success.jsp")
				.forward(request, response);
	}
	public void add3(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IllegalAccessException,
			InvocationTargetException {
		Member member = ResultUtils.copyParams(Member.class, request);
		User user = new User();
		BeanUtils.copyProperties(user, member);
		user.setRole(member.getRoles());
		String password = request.getParameter("password");
		user.setPassword(password);
		userService.add(user);
		member.setUser(user);
		memberService.add(member);
		response.sendRedirect("/registsuccess.jsp");
	}

	public void modify1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("login_user");
		request.setAttribute("member", memberService.findByUserId(user.getId()));
		request.setAttribute("userList", userService.search());
		request.getRequestDispatcher("/backend/member/update_member_input.jsp")
				.forward(request, response);
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = new JDBCHandler<MemberService>()
				.createProxy(memberService);
	}

	public void setUserService(UserService userService) {
		this.userService = new JDBCHandler<UserService>()
				.createProxy(userService);
	}
}