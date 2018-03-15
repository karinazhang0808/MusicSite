package org.bs.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class JDBCHandler<T> implements InvocationHandler {

	private T target = null;

	public T createProxy(Object target) {
		this.target = (T) target;
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Connection conn = ConnContext.getConn();
		Object result = null;
		try {
			result = method.invoke(target, args);
			DB.commit(conn);
		} catch (Exception e) {
			DB.rollback(conn);
			e.printStackTrace();
		} finally {
			DB.close(conn);
		}
		ConnContext.remove();
		return result;
	}
}
