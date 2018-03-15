package org.bs.servlet.backend;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.bs.model.User;
import org.bs.service.UserService;
import org.bs.utils.JDBCHandler;
import org.bs.utils.PageContext;

public class UploadServlet extends BaseServlet {
	private UserService userService;

	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// int id = Integer.parseInt(request.getParameter("id"));

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			ServletFileUpload upload = new ServletFileUpload();
			upload.setSizeMax(20240 * 20240);
			try {
				FileItemIterator iter = upload.getItemIterator(request);
				while (iter.hasNext()) {
					FileItemStream item = iter.next();
					String filedname = item.getFieldName();
					InputStream stream = item.openStream();
					// if (filedname.equals("photo" + id)) {
					if (item.isFormField()) {
					} else {
						String filename = item.getName();

						String contentType = item.getContentType();
						String realPath = null;
						if (contentType != null) {
							if (contentType.toLowerCase().indexOf("image") > -1) {
								realPath = this.getServletContext()
										.getRealPath("/upload_image");
							} else {
								realPath = this.getServletContext()
										.getRealPath("/upload_file");
							}
						}

						String ext = filename.substring(filename
								.lastIndexOf("."));
						String finalFilename = new SimpleDateFormat(
								"yyyyMMddHHmmssms").format(new Date()) + ext;

						// User user = userService.findById(id);
						// String photo = "upload/" + finalFilename;
						// user.setImg(photo);
						// userService.update(user);

						OutputStream out = new FileOutputStream(realPath + "/"
								+ finalFilename);
						Streams.copy(stream, out, true);
						request.setAttribute("contentType", contentType);
						request.setAttribute("filename", finalFilename);
					}
					// }
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// PrintWriter out = response.getWriter();
		// out.print("<script language='javascript'>alert('��Ƭ����ӳɹ�');window.location.href='UploadServlet?method=list';</script>");
		// out.flush();
		// out.close();
		request.getRequestDispatcher("/backend/upload.jsp").forward(request,
				response);
	}

	@Override
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String key = request.getParameter("key");
		if (key == null) {
			key = "";
		}

		// request.setAttribute("userList", userService.search());

		request.setAttribute("page", PageContext.getPage());
		request.getRequestDispatcher("/backend/DC/user/user_list.jsp").forward(
				request, response);
	}

	public void setUserService(UserService userService) {
		this.userService = new JDBCHandler<UserService>()
				.createProxy(userService);
	}
}
