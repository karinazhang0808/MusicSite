package org.bs.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.jsp.PageContext;

public class SystemFunction {
	public static String firstLetterToUpper(String str) {
		char[] array = str.toCharArray();
		array[0] -= 32;
		return String.valueOf(array);
	}

	public static String firstLetterToLower(String str) {
		char[] array = str.toCharArray();
		array[0] += 32;
		return String.valueOf(array);
	}

	public static void StringToFile(String path, String content)
			throws IOException {
		File file = new File(path);
		if (file.exists() && file.isFile())
			file.delete();
		file.createNewFile();
		StringBuffer sb = new StringBuffer();
		sb.append(content);
		FileOutputStream out = new FileOutputStream(file, true);
		out.write(sb.toString().getBytes("utf-8"));
		out.close();
	}
}