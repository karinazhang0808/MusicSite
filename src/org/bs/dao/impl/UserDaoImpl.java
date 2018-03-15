package org.bs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bs.dao.RoleDao;
import org.bs.dao.UserDao;
import org.bs.model.User;
import org.bs.utils.ConnContext;
import org.bs.utils.DB;
import org.bs.utils.PageContext;

public class UserDaoImpl extends BaseDao implements UserDao {
	private RoleDao roleDao = new RoleDaoImpl();

	public User login(String username, String password) {
		User user = getUserByUsername(username);
		if (user == null) {
			throw new RuntimeException("用户名不存在");
		} else {
			if (!password.equals(user.getPassword())) {
				throw new RuntimeException("密码错误");
			}
		}
		return user;
	}

	public User getById(int id) {
		Connection conn = DB.getConn();
		String sql = "select * from t_user where id = ?";
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(roleDao.getById(rs.getInt("role")));
			}
		} catch (SQLException e) {
		} finally {
			DB.close(conn);
			DB.close(pstmt);
			DB.close(rs);
		}
		return user;
	}

	public User getUserByUsername(String username) {
		Connection conn = DB.getConn();
		String sql = "select * from t_user where username = ?";
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(roleDao.getById(rs.getInt("role")));
			}
		} catch (SQLException e) {
		} finally {
			DB.close(conn);
			DB.close(pstmt);
			DB.close(rs);
		}
		return user;
	}

	public void save(User user) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "insert into t_user" + "(username,password,role)";
		sql += "values(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getRole().getId());
			pstmt.executeUpdate();
			ResultSet results = pstmt.getGeneratedKeys();
			int num = -1;
			if (results.next()) {
				num = results.getInt(1);
			}
			user.setId(num);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public void delete(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "delete from t_user where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public void update(User user) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "update t_user set " + "username=?,password=?,role=? "
				+ " where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getRole().getId());
			pstmt.setInt(4, user.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public void present(User user) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "update t_user set " + "present=?" + " where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			// pstmt.setInt(1, user.getPresent());
			pstmt.setInt(2, user.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}

	}

	public void updatePassword(User user) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "update t_user set " + "password=?" + " where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setInt(2, user.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}

	}

	public List<User> query() {
		String name = "";
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<User> list = new ArrayList<User>();

		String sql = "select * from t_user where username like ? order by id desc";
		String sqlRecordsCount = "select count(*)"
				+ sql.substring(sql.indexOf("from"));
		String pagingSql = sql + " limit ?,?";
		PageContext.getPage().setRecordsCount(
				this.getRecordsCount(sqlRecordsCount,
						new Class[] { String.class }, new Object[] { "%" + name
								+ "%" }));
		try {
			pstmt = conn.prepareStatement(pagingSql);
			pstmt.setString(1, "%" + name + "%");
			pstmt.setInt(2, PageContext.getPage().getPageIndex());
			pstmt.setInt(3, PageContext.getPage().getPageSize());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(roleDao.getById(rs.getInt("role")));
				list.add(user);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return list;
	}

	public List<User> query(String name) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<User> list = new ArrayList<User>();

		String sql = "select * from t_user where username like ? order by id desc";
		String sqlRecordsCount = "select count(*)"
				+ sql.substring(sql.indexOf("from"));
		String pagingSql = sql + " limit ?,?";
		PageContext.getPage().setRecordsCount(
				this.getRecordsCount(sqlRecordsCount,
						new Class[] { String.class }, new Object[] { "%" + name
								+ "%" }));
		try {
			pstmt = conn.prepareStatement(pagingSql);
			pstmt.setString(1, "%" + name + "%");
			pstmt.setInt(2, PageContext.getPage().getPageIndex());
			pstmt.setInt(3, PageContext.getPage().getPageSize());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(roleDao.getById(rs.getInt("role")));
				list.add(user);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return list;
	}

	public List<User> queryPresent(String name) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<User> list = new ArrayList<User>();

		String sql = "select * from t_user where username like ? and present!=0 order by id desc ";
		String sqlRecordsCount = "select count(*)"
				+ sql.substring(sql.indexOf("from"));
		String pagingSql = sql + " limit ?,?";
		PageContext.getPage().setRecordsCount(
				this.getRecordsCount(sqlRecordsCount,
						new Class[] { String.class }, new Object[] { "%" + name
								+ "%" }));
		try {
			pstmt = conn.prepareStatement(pagingSql);
			pstmt.setString(1, "%" + name + "%");
			pstmt.setInt(2, PageContext.getPage().getPageIndex());
			pstmt.setInt(3, PageContext.getPage().getPageSize());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(roleDao.getById(rs.getInt("role")));
				list.add(user);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return list;
	}

}
