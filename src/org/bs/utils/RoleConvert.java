package org.bs.utils;

import org.apache.commons.beanutils.Converter;
import org.bs.model.Role;

public class RoleConvert implements Converter {
	public Object convert(Class targetClass, Object value) {
		Role role = null;
		if (targetClass == Role.class) {
			role = new Role();
			role.setId(Integer.parseInt((String) value));
		}
		return role;
	}
}