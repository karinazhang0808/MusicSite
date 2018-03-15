package org.bs.service;

import java.util.List;
import org.bs.model.Channel;

public interface ChannelService {
	public void add(Channel channel);

	public void delete(Channel channel);

	public void update(Channel channel);

	public Channel findById(int id);

	public Channel findByUserId(int id);

	public List<Channel> search();
}