package org.bs.ztest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.TestCase;

public class StringConvert extends TestCase {
	public void test() {
		try {
			StringToFile("D:/test.log", StringsConvert("D:/test.log"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String StringsConvert(String path) throws IOException {
		File file = new File(path);
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String temp = null;
		StringBuffer sb = new StringBuffer();
		temp = br.readLine();
		while (temp != null) {
			sb.append("\"" + temp + "\\n\"+\r\n");
			temp = br.readLine();
		}
		return sb.toString();
	}

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
		out.write(sb.toString().getBytes());
		out.close();
	}
}
