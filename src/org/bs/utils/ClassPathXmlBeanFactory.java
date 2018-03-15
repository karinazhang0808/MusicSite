package org.bs.utils;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class ClassPathXmlBeanFactory implements BeanFactory {

	private Map<String, Object> beans = new HashMap<String, Object>();

	public ClassPathXmlBeanFactory() {
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("beans.xml");
		SAXBuilder builder = new SAXBuilder();
		Document document = null;
		try {
			document = builder.build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		List elements = root.getChildren();
		for (int i = 0; i < elements.size(); i++) {
			Element element = (Element) elements.get(i);
			String id = element.getAttributeValue("id");
			String className = element.getAttributeValue("class");
			Object bean = null;
			try {
				bean = Class.forName(className).newInstance();
				beans.put(id, bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List children = element.getChildren("property");
			if (children != null) {
				for (int j = 0; j < children.size(); j++) {
					Element property = (Element) children.get(j);
					String name = property.getAttributeValue("name");
					String ref = property.getAttributeValue("ref");
					String methodName = "set"
							+ name.substring(0, 1).toUpperCase()
							+ name.substring(1);
					Object refObject = beans.get(ref);
					try {
						Method method = bean.getClass().getMethod(methodName,
								refObject.getClass().getInterfaces());
						method.invoke(bean, refObject);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public Object getBean(String beanName) {
		return beans.get(beanName);
	}

}
