package org.bs.servlet.backend;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bs.model.Memberloves;
import org.bs.model.User;
import org.bs.service.MemberlovesService;
import org.bs.utils.JDBCHandler;
import org.bs.utils.PageContext;
import org.bs.utils.ResultUtils;
import org.bs.model.Member;
import org.bs.service.MemberService;
import org.bs.model.Music;
import org.bs.service.MusicService;

public class MemberlovesServlet extends BaseServlet {
	private MemberlovesService memberlovesService;
	private MemberService memberService;
	private MusicService musicService;

	@Override
	public void addInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("memberList", memberService.search());
		request.setAttribute("musicList", musicService.search());
		request.getRequestDispatcher("/backend/memberloves/add_memberloves.jsp")
				.forward(request, response);
	}

	public void prepare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("memberlovesList",
				memberlovesService.search());
	}

	@Override
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("login_user");
		Member member = memberService.findByUserId(user.getId());
		Memberloves memberloves = ResultUtils.copyParams(Memberloves.class,
				request);
		memberloves.setMember(member);
		memberlovesService.add(memberloves);
		Music music = musicService.findById(memberloves.getMusic().getId());
		music.setXihuan(music.getXihuan()+1);
		musicService.update(music);
		response.sendRedirect("MusicServlet?method=content&id="+memberloves.getMusic().getId());
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		memberlovesService.delete(memberlovesService.findById(id));
		request.getRequestDispatcher("MemberlovesServlet?method=list").forward(
				request, response);
	}

	public void deleteBetch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] memberlovesIds = request
				.getParameterValues("memberlovesCheckbox");
		for (int i = 0; i < memberlovesIds.length; i++) {
			memberlovesService.delete(memberlovesService.findById(Integer
					.parseInt(memberlovesIds[i])));
		}
		request.getRequestDispatcher("MemberlovesServlet?method=list").forward(
				request, response);
	}

	@Override
	public void modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("memberloves", memberlovesService.findById(id));
		request.setAttribute("memberList", memberService.search());
		request.setAttribute("musicList", musicService.search());
		request.getRequestDispatcher(
				"/backend/memberloves/update_memberloves_input.jsp").forward(
				request, response);
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Memberloves memberloves = ResultUtils.copyParams(Memberloves.class,
				request);
		memberlovesService.update(memberloves);
		request.getRequestDispatcher("MemberlovesServlet?method=list").forward(
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
		request.setAttribute("memberlovesList", memberlovesService.search());
		request.getRequestDispatcher(
				"/backend/memberloves/memberloves_list.jsp").forward(request,
				response);
	}

	public void setMemberlovesService(MemberlovesService memberlovesService) {
		this.memberlovesService = new JDBCHandler<MemberlovesService>()
				.createProxy(memberlovesService);
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