package org.bs.utils;

import org.apache.commons.beanutils.Converter;
import org.bs.model.User;

public class UserConvert implements Converter {
	public Object convert(Class targetClass, Object value) {
		User user = null;
		if (targetClass == User.class) {
			user = new User();
			user.setId(Integer.parseInt((String) value));
		}
		return user;
	}
}