package org.bs.utils;

import org.apache.commons.beanutils.Converter;
import org.bs.model.Member;

public class MemberConvert implements Converter {
	public Object convert(Class targetClass, Object value) {
		Member member = null;
		if (targetClass == Member.class) {
			member = new Member();
			member.setId(Integer.parseInt((String) value));
		}
		return member;
	}
}