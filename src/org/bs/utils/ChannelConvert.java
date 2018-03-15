package org.bs.utils;

import org.apache.commons.beanutils.Converter;
import org.bs.model.Channel;

public class ChannelConvert implements Converter {
	public Object convert(Class targetClass, Object value) {
		Channel channel = null;
		if (targetClass == Channel.class) {
			channel = new Channel();
			channel.setId(Integer.parseInt((String) value));
		}
		return channel;
	}
}