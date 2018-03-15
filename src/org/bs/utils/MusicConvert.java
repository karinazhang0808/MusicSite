package org.bs.utils;

import org.apache.commons.beanutils.Converter;
import org.bs.model.Music;

public class MusicConvert implements Converter {
	public Object convert(Class targetClass, Object value) {
		Music music = null;
		if (targetClass == Music.class) {
			music = new Music();
			music.setId(Integer.parseInt((String) value));
		}
		return music;
	}
}