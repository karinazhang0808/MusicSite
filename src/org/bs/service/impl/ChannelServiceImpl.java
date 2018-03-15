package org.bs.service.impl;

import java.util.List;
import org.bs.dao.ChannelDao;
import org.bs.model.Channel;
import org.bs.service.ChannelService;

public class ChannelServiceImpl implements ChannelService {
	ChannelDao channelDao;

	public void add(Channel channel) {
		channelDao.save(channel);
	}

	public void delete(Channel channel) {
		channelDao.delete(channel.getId());
	}

	public void update(Channel channel) {
		channelDao.update(channel);
	}

	public Channel findById(int id) {
		return channelDao.getById(id);
	}

	public Channel findByUserId(int id) {
		return channelDao.getByUserId(id);
	}

	public List<Channel> search() {
		return channelDao.query();
	}

	public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}
}