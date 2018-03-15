package org.bs.utils;

import java.sql.Connection;

public class ConnContext {
	private static ThreadLocal<Connection> connThreadLocal = new ThreadLocal<Connection>();

	public static Connection getConn() {
		Connection conn = connThreadLocal.get();
		if (conn == null) {
			conn = DB.getConn();
			connThreadLocal.set(conn);
		}
		return (Connection) conn;
	}

	public static void remove() {
		connThreadLocal.remove();
	}
}
