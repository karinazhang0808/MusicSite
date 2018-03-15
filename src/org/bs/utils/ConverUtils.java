package org.bs.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.regexp.internal.recompile;

public class ConverUtils {
	public static Date StringTODate(String s) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = format.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static String DateToString(Date date) {
		String s = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		s = format.format(date);
		return s;
	}

}
