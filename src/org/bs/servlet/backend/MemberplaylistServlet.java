package org.bs.servlet.backend;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bs.model.Memberplaylist;
import org.bs.service.MemberplaylistService;
import org.bs.utils.JDBCHandler;
import org.bs.utils.PageContext;
import org.bs.utils.ResultUtils;
import org.bs.model.Member;
import org.bs.service.MemberService;
import org.bs.model.Music;
import org.bs.service.MusicService;

public class MemberplaylistServlet extends BaseServlet {
	private MemberplaylistService memberplaylistService;
	private MemberService memberService;
	private MusicService musicService;

	@Override
	public void addInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("memberList", memberService.search());
		request.setAttribute("musicList", musicService.search());
		request.getRequestDispatcher(
				"/backend/memberplaylist/add_memberplaylist.jsp").forward(
				request, response);
	}

	public void prepare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("memberplaylistList",
				memberplaylistService.search());
	}

	@Override
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Memberplaylist memberplaylist = ResultUtils.copyParams(
				Memberplaylist.class, request);
		memberplaylistService.add(memberplaylist);
		request.getRequestDispatcher(
				"/backend/memberplaylist/add_memberplaylist_success.jsp")
				.forward(request, response);
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		memberplaylistService.delete(memberplaylistService.findById(id));
		request.getRequestDispatcher("MemberplaylistServlet?method=list")
				.forward(request, response);
	}

	public void deleteBetch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] memberplaylistIds = request
				.getParameterValues("memberplaylistCheckbox");
		for (int i = 0; i < memberplaylistIds.length; i++) {
			memberplaylistService.delete(memberplaylistService.findById(Integer
					.parseInt(memberplaylistIds[i])));
		}
		request.getRequestDispatcher("MemberplaylistServlet?method=list")
				.forward(request, response);
	}

	@Override
	public void modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("memberplaylist",
				memberplaylistService.findById(id));
		request.setAttribute("memberList", memberService.search());
		request.setAttribute("musicList", musicService.search());
		request.getRequestDispatcher(
				"/backend/memberplaylist/update_memberplaylist_input.jsp")
				.forward(request, response);
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Memberplaylist memberplaylist = ResultUtils.copyParams(
				Memberplaylist.class, request);
		memberplaylistService.update(memberplaylist);
		request.getRequestDispatcher("MemberplaylistServlet?method=list")
				.forward(request, response);
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
		request.setAttribute("memberplaylistList",
				memberplaylistService.search());
		request.getRequestDispatcher(
				"/backend/memberplaylist/memberplaylist_list.jsp").forward(
				request, response);
	}

	public void setMemberplaylistService(
			MemberplaylistService memberplaylistService) {
		this.memberplaylistService = new JDBCHandler<MemberplaylistService>()
				.createProxy(memberplaylistService);
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = new JDBCHandler<MemberService>()
				.createProxy(memberService);
	}

	public void setMusicService(MusicService musicService) {
		this.musicService = new JDBCHandler<MusicService>()
				.createProxy(musicService);
	}
}