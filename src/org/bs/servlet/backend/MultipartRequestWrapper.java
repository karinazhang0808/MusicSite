package org.bs.servlet.backend;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;

public class MultipartRequestWrapper extends HttpServletRequestWrapper {

	private Map allParams;

	public MultipartRequestWrapper(HttpServletRequest request) {
		super(request);

		// �����ж��Ƿ�multipart��������
		// �����multipart�������ͣ��������request��ȡ�������?��
		// ���?������ͨ�ı?�������ֵȡ�����ŵ�allParams��
		// ���?�����ļ�����
		// 1�����ļ��ȴ洢�����̵�ĳ��Ŀ¼�У�
		// 2�����ļ����й���Ϣ����ƣ����ͣ��ϴ�ʱ�䣩��װ��Attachment[]����
		// 3���Ѱ�װ�õ�Attachment[]���ͣ��ŵ�allParams��

		try {
			// ���ȣ��ж��Ƿ�multipart��������
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (!isMultipart) {
				allParams = request.getParameterMap();
			} else {
				allParams = new HashMap();
				ServletFileUpload upload = new ServletFileUpload();
				FileItemIterator iter = upload.getItemIterator(request);
				while (iter.hasNext()) {
					FileItemStream item = iter.next();
					// �õ��?������
					String name = item.getFieldName();
					// �õ��?���ֵ������һ����������
					InputStream stream = item.openStream();

					// �������ͨ�?��
					if (item.isFormField()) {
						String value = Streams.asString(stream,
								request.getCharacterEncoding());
					} else { // ������ļ�
						if (stream.available() != 0) {// ����ļ���û��ѡ���ļ�������Դ���
							String filename = item.getName(); // �õ��ϴ����ļ����
							if (filename != null) {
								// ��Ϊ��IE���棬�ϴ����ļ������д��ļ��ڿͻ��˻�����·��
								// ���ԣ�Ҫ�����·��ȥ����ֻȡ�ļ���
								filename = FilenameUtils.getName(filename);
							}
							// ���ϴ��ļ�����������������̵��ļ���
							// Streams.copy(stream, new
							// FileOutputStream(Attachment.ATTACHMENT_DIR+filename),
							// true);

						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
