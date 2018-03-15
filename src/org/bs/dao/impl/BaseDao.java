package org.bs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import org.bs.utils.DB;

public class BaseDao {
	protected int getRecordsCount(String sql, Class[] paramsClasses,
			Object[] params) {
		Connection conn = DB.getConn();
		PreparedStatement pstmt = null;
		ResultSet result = null;

		int recordsCount = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < paramsClasses.length; i++) {
				if (paramsClasses[i] == String.class) {
					pstmt.setString(i + 1, (String) params[i]);
				}
				if (paramsClasses[i] == int.class) {
					pstmt.setInt(i + 1, (Integer) params[i]);
				}
				if (paramsClasses[i] == Date.class) {
					pstmt.setDate(i + 1,
							new java.sql.Date(((Date) params[i]).getTime()));
				}
			}
			result = pstmt.executeQuery();
			if (result.next()) {
				recordsCount = result.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn);
			DB.close(pstmt);
			DB.close(result);
		}
		return recordsCount;
	}
}
