package org.bs.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesBeanFactory<T> implements BeanFactory {

	private static Properties props = new Properties();
	static {
		InputStream inputStream = PropertiesBeanFactory.class.getClassLoader()
				.getResourceAsStream("beans.properties");
		try {
			props.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public T getBean(String beanName) {
		String className = props.getProperty(beanName);
		T bean = null;
		if (beanName.indexOf("Service") > -1) {
			JDBCHandler<T> jdbcHandler = new JDBCHandler<T>();
			try {
				T temp = (T) Class.forName(className).newInstance();
				bean = jdbcHandler.createProxy(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				bean = (T) Class.forName(className).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bean;
	}
}
