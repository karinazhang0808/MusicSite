package org.bs.ztest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

public class Test extends TestCase {
	public void test() {
		System.out.println("123456789".substring(0, 4));
		System.out.println(firstLetterToLower("AAbb"));
		System.out.println("Jane Campion directed \"The Piano\" in \n1993.");

		try {
			StringToFile("D:/test.log", firstLetterToLower("az") + "����");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void test01() throws IOException {
		File dir = new File("D:/1");
		dir.mkdirs();
	}

	public void test02() throws IOException {
		System.out.println("  PRIMARY KEY (`id`)");
	}

	public void test03() throws IOException {
		String str = "a";
		char[] array = str.toCharArray();
		array[0] = 129;
		System.out.println(String.valueOf(array));
		System.out.println(firstLetterToUpper("AAbb"));
	}

	public static String firstLetterToUpper(String str) {
		char[] array = str.toCharArray();
		if (array[0] >= 65 && array[0] <= 90) {
			return String.valueOf(array);
		} else {
			array[0] -= 32;
		}
		return String.valueOf(array);
	}

	public static String firstLetterToLower(String str) {
		char[] array = str.toCharArray();
		if (array[0] >= 97 && array[0] <= 122) {
			return String.valueOf(array);
		} else {
			array[0] += 32;
		}
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
