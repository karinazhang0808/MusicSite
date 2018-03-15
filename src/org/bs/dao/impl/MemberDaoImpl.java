package org.bs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.bs.dao.MemberDao;
import org.bs.model.Member;
import org.bs.utils.ConnContext;
import org.bs.utils.DB;
import org.bs.utils.PageContext;
import org.bs.dao.UserDao;

public class MemberDaoImpl extends BaseDao implements MemberDao {
	private UserDao userDao = new UserDaoImpl();

	public void save(Member member) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "insert into t_member (name,tel,address,email,user) values(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getTel());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getEmail());
			pstmt.setInt(5, member.getUser().getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public void delete(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "delete from t_member where id =?";
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

	public void update(Member member) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "update t_member set name=?,tel=?,address=?,email=?,user=?"
				+ " where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getTel());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getEmail());
			pstmt.setInt(5, member.getUser().getId());
			pstmt.setInt(6, member.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public Member getById(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = new Member();
		String sql = "select * from t_member where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setTel(rs.getString("tel"));
				member.setAddress(rs.getString("address"));
				member.setEmail(rs.getString("email"));
				member.setUser(userDao.getById(rs.getInt("user")));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return member;
	}

	public Member getByUserId(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = new Member();
		String sql = "select * from t_member where user = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setTel(rs.getString("tel"));
				member.setAddress(rs.getString("address"));
				member.setEmail(rs.getString("email"));
				member.setUser(userDao.getById(rs.getInt("user")));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return member;
	}

	public List<Member> query() {
		List<Member> list = new ArrayList<Member>();
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from t_member";
		String sqlRecordsCount = "select count(*)"
				+ sql.substring(sql.indexOf("from"));
		sql += " limit ?,?";
		PageContext.getPage().setRecordsCount(
				getRecordsCount(sqlRecordsCount, new Class[] {},
						new Object[] {}));
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, PageContext.getPage().getPageIndex());
			pstmt.setInt(2, PageContext.getPage().getPageSize());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setTel(rs.getString("tel"));
				member.setAddress(rs.getString("address"));
				member.setEmail(rs.getString("email"));
				member.setUser(userDao.getById(rs.getInt("user")));
				list.add(member);
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