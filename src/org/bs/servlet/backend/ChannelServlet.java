package org.bs.servlet.backend;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bs.model.Channel;
import org.bs.service.ChannelService;
import org.bs.utils.JDBCHandler;
import org.bs.utils.PageContext;
import org.bs.utils.ResultUtils;

public class ChannelServlet extends BaseServlet {
	private ChannelService channelService;

	@Override
	public void addInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/backend/channel/add_channel.jsp")
				.forward(request, response);
	}

	public void prepare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("channelList",
				channelService.search());
	}

	@Override
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Channel channel = ResultUtils.copyParams(Channel.class, request);
		channelService.add(channel);
		request.getRequestDispatcher("/backend/channel/add_channel_success.jsp")
				.forward(request, response);
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		channelService.delete(channelService.findById(id));
		request.getRequestDispatcher("ChannelServlet?method=list").forward(
				request, response);
	}

	public void deleteBetch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] channelIds = request.getParameterValues("channelCheckbox");
		for (int i = 0; i < channelIds.length; i++) {
			channelService.delete(channelService.findById(Integer
					.parseInt(channelIds[i])));
		}
		request.getRequestDispatcher("ChannelServlet?method=list").forward(
				request, response);
	}

	@Override
	public void modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("channel", channelService.findById(id));
		request.getRequestDispatcher(
				"/backend/channel/update_channel_input.jsp").forward(request,
				response);
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Channel channel = ResultUtils.copyParams(Channel.class, request);
		channelService.update(channel);
		request.getRequestDispatcher("ChannelServlet?method=list").forward(
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
		request.setAttribute("channelList", channelService.search());
		request.getRequestDispatcher("/backend/channel/channel_list.jsp")
				.forward(request, response);
	}

	public void setChannelService(ChannelService channelService) {
		this.channelService = new JDBCHandler<ChannelService>()
				.createProxy(channelService);
	}
}