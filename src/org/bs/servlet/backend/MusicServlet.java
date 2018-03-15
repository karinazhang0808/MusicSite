package org.bs.servlet.backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bs.model.Channel;
import org.bs.model.Member;
import org.bs.model.Memberloves;
import org.bs.model.Music;
import org.bs.model.User;
import org.bs.service.ChannelService;
import org.bs.service.MemberService;
import org.bs.service.MemberlovesService;
import org.bs.service.MusicService;
import org.bs.utils.JDBCHandler;
import org.bs.utils.PageContext;
import org.bs.utils.ResultUtils;

public class MusicServlet extends BaseServlet {
	private MusicService musicService;
	private ChannelService channelService;
	private MemberlovesService memberlovesService;
	private MemberService memberService;
	@Override
	public void addInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("channelList", channelService.search());
		request.getRequestDispatcher("/backend/music/add_music.jsp").forward(
				request, response);
	}

	public void prepare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("musicList", musicService.search());
	}

	@Override
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Music music = ResultUtils.copyParams(Music.class, request);
		musicService.add(music);
		request.getRequestDispatcher("/backend/music/add_music_success.jsp")
				.forward(request, response);
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		musicService.delete(musicService.findById(id));
		request.getRequestDispatcher("MusicServlet?method=list").forward(
				request, response);
	}

	public void deleteBetch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] musicIds = request.getParameterValues("musicCheckbox");
		for (int i = 0; i < musicIds.length; i++) {
			musicService.delete(musicService.findById(Integer
					.parseInt(musicIds[i])));
		}
		request.getRequestDispatcher("MusicServlet?method=list").forward(
				request, response);
	}

	@Override
	public void modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("music", musicService.findById(id));
		request.setAttribute("channelList", channelService.search());
		request.getRequestDispatcher("/backend/music/update_music_input.jsp")
				.forward(request, response);
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Music music = ResultUtils.copyParams(Music.class, request);
		musicService.update(music);
		request.getRequestDispatcher("MusicServlet?method=list").forward(
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
		request.setAttribute("musicList", musicService.search());
		request.getRequestDispatcher("/backend/music/music_list.jsp").forward(
				request, response);
	}
	public void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageContext.getPage().setPageSize(100000);
		request.setAttribute("page", PageContext.getPage());

		List<Channel> channels = channelService.search();
		List<Music> musics = musicService.search();
		for (Music m : musics) {
			for (Channel c : channels) {
				if (m.getChannel().getId() == c.getId()) {
					c.getMusics().add(m);
				}
			}
		}

		User user = (User) request.getSession().getAttribute("login_user");
		List<Music> musicstuijian = new ArrayList<Music>();
		List<Music> musicslist = new ArrayList<Music>();
		
		if(user!=null){
			Member member = memberService.findByUserId(user.getId());
			Member memberself = new Member();
			PageContext.getPage().setPageSize(100000);
			List<Member> members = memberService.search();
			
			List<Music> simmusic = new ArrayList<Music>();
			
			for(Member m:members){
				List<Music> musicsself = new ArrayList<Music>();
				List<Memberloves>  memberloves = memberlovesService.search("会员"+m.getId());
				for(Memberloves ml:memberloves){
					musicsself.add(ml.getMusic());
				}
				m.setMusics(musicsself);
 			}
			//去掉自己
			for(Member m:members){
				if(member.getId()==m.getId()){
					memberself = m;
				}
			}
			member.setMusics(memberself.getMusics());
			members.remove(memberself);
			
			if(members.size()==0){
				PageContext.getPage().setPageSize(10);
				request.setAttribute("musicstuijian", musicService.search());
			}else{
				//计算相似度
				for(Member m:members){
					int count = 0;
					for(Music mymusic:member.getMusics()){
						for(Music othermusic:m.getMusics()){
							if(mymusic.getId()==othermusic.getId()){
								count+=1;
							}
						}
					}
					m.setSimvalue(1.0*count/(member.getMusics().size()+m.getMusics().size()-count));
				}
				//去掉相似度为0的用户
				List<Member> removemembers = new ArrayList<Member>();
				for(Member m:members){
					if(m.getSimvalue()==0){
						removemembers.add(m);
					}
				}
				for(Member m:removemembers){
					members.remove(m);
				}
				//判定相似用户群是否为空
				if(members.size()==0){
					PageContext.getPage().setPageSize(10);
					request.setAttribute("musicstuijian", musicService.search());
				}else{
					//计算推荐音乐的推荐度
					for(Member m:members){
						for(Music music : m.getMusics()){
							int f=0;
							for(Music musictuijian : musicstuijian){
								if(music.getId() == musictuijian.getId()){
									musictuijian.setSimvalue(musictuijian.getSimvalue()+ m.getSimvalue()) ;
									f=1;
								}
								if(f==0){
									music.setSimvalue(m.getSimvalue());
									musicstuijian.add(music);
								}
							}
						}
					}
					//判定推荐音乐的数量是否为0
					if(musicstuijian.size()==0){
						PageContext.getPage().setPageSize(10);
						request.setAttribute("musicstuijian", musicService.search());
					}else{
						//按推荐度排序取前10个音乐
						if(musicstuijian.size()<10){
							int size = musicstuijian.size();
							for(int i=1;i<=size;i++){
								int max = 0;
								for(int j=2;j<=musicstuijian.size();j++){
									if(musicstuijian.get(max).getSimvalue()<musicstuijian.get(j-1).getSimvalue()){
										max=j-1;
									}
								}
								musicslist.add(musicstuijian.get(max));
								musicstuijian.remove(max);
								
							}
						}
						if(musicstuijian.size()>=10){
							for(int i=1;i<=10;i++){
								int max = 0;
								for(int j=2;j<=musicstuijian.size();j++){
									if(musicstuijian.get(max).getSimvalue()<musicstuijian.get(j-1).getSimvalue()){
										max=j-1;
									}
								}
								musicslist.add(musicstuijian.get(max));
								musicstuijian.remove(max);
							}
						}
						request.setAttribute("musicstuijian", musicslist);
					}
				}
			}
			
			
		}else{
			PageContext.getPage().setPageSize(10);
			request.setAttribute("musicstuijian", musicService.search());
		}
		
		
		
		request.setAttribute("channels", channels);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	public void content(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Music music = musicService.findById(id);
		request.setAttribute("music", music);
		User user = (User) request.getSession().getAttribute("login_user");
		
		if(user!=null){
			Member member = memberService.findByUserId(user.getId());
			if(memberlovesService.search("喜欢"+music.getId()+"-"+member.getId()).size()!=0){
				System.out.println(memberlovesService.search("喜欢"+music.getId()+"-"+member.getId()).size());
				request.setAttribute("memberloves", "1");
			}
			else
				request.setAttribute("memberloves", "2");
		}
		music.setBofang(music.getBofang() + 1);
		musicService.update(music);
			request.getRequestDispatcher("/music.jsp").forward(request, response);
	}

	public void setMusicService(MusicService musicService) {
		this.musicService = new JDBCHandler<MusicService>()
				.createProxy(musicService);
	}

	public void setChannelService(ChannelService channelService) {
		this.channelService = new JDBCHandler<ChannelService>()
				.createProxy(channelService);
	}
	public void setMemberlovesService(MemberlovesService memberlovesService) {
		this.memberlovesService = new JDBCHandler<MemberlovesService>()
				.createProxy(memberlovesService);
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = new JDBCHandler<MemberService>()
				.createProxy(memberService);
	}
}