package org.bs.dao;

import java.util.List;
import org.bs.model.Channel;

public interface ChannelDao {
	public void save(Channel channel);

	public void delete(int id);

	public void update(Channel channel);

	public Channel getById(int id);

	public List<Channel> query();

	public Channel getByUserId(int id);
}