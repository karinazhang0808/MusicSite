package org.bs.servlet.backend;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CheckCodeServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		final int width = 40;
		final int height = 20;
		// 创建一张图片
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		// 背景
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		// 画1000个点
		Random r = new Random();
		for (int i = 0; i < 250 * (width / 75); i++) {
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			g.fillOval(r.nextInt(width), r.nextInt(height), 2, 2);
		}
		// 画随机旋转，随机选取的字符串
		String charSet = "abcdefghijklmnopqrstuvwxyz0123456789";
		String code = "";
		g.setFont(new Font("Courier", Font.BOLD, width / 3));
		double theta = 0;// 旋转角度
		for (int i = 0; i < 4; i++) {
			// 选取字符串
			String c = "" + charSet.charAt(r.nextInt(charSet.length()));
			code += c;
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			// 字符旋转
			g.rotate(theta, width / 2 + (i - 3) * width / 4 + width / 8,
					height / 2);
			theta = r.nextInt((int) (Math.PI * 25)) / 100.0 - Math.PI / 8.0;
			g.rotate(theta, width / 2 + (i - 2) * width / 4 + width / 8,
					height / 2);
			theta = -theta;

			g.drawString(c, width / 2 + (i - 2) * width / 4, height / 2
					+ height / 4);
		}

		request.getSession().setAttribute("check_code", code);

		OutputStream out = response.getOutputStream();
		// 将图片转换为JPEG类型
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image);
		out.flush();
		out.close();

	}

}
