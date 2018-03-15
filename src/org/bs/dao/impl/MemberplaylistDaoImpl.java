package org.bs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.bs.dao.MemberplaylistDao;
import org.bs.model.Memberplaylist;
import org.bs.utils.ConnContext;
import org.bs.utils.DB;
import org.bs.utils.PageContext;
import org.bs.dao.MemberDao;
import org.bs.dao.MusicDao;

public class MemberplaylistDaoImpl extends BaseDao implements MemberplaylistDao {
	private MemberDao memberDao = new MemberDaoImpl();
	private MusicDao musicDao = new MusicDaoImpl();

	public void save(Memberplaylist memberplaylist) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "insert into t_memberplaylist (member,music,state,playcount) values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberplaylist.getMember().getId());
			pstmt.setInt(2, memberplaylist.getMusic().getId());
			pstmt.setString(3, memberplaylist.getState());
			pstmt.setInt(4, memberplaylist.getPlaycount());
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
		String sql = "delete from t_memberplaylist where id =?";
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

	public void update(Memberplaylist memberplaylist) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "update t_memberplaylist set member=?,music=?,state=?,playcount=?"
				+ " where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberplaylist.getMember().getId());
			pstmt.setInt(2, memberplaylist.getMusic().getId());
			pstmt.setString(3, memberplaylist.getState());
			pstmt.setInt(4, memberplaylist.getPlaycount());
			pstmt.setInt(5, memberplaylist.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public Memberplaylist getById(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Memberplaylist memberplaylist = new Memberplaylist();
		String sql = "select * from t_memberplaylist where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberplaylist.setId(rs.getInt("id"));
				memberplaylist
						.setMember(memberDao.getById(rs.getInt("member")));
				memberplaylist.setMusic(musicDao.getById(rs.getInt("music")));
				memberplaylist.setState(rs.getString("state"));
				memberplaylist.setPlaycount(rs.getInt("playcount"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return memberplaylist;
	}

	public Memberplaylist getByUserId(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Memberplaylist memberplaylist = new Memberplaylist();
		String sql = "select * from t_memberplaylist where user = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberplaylist.setId(rs.getInt("id"));
				memberplaylist
						.setMember(memberDao.getById(rs.getInt("member")));
				memberplaylist.setMusic(musicDao.getById(rs.getInt("music")));
				memberplaylist.setState(rs.getString("state"));
				memberplaylist.setPlaycount(rs.getInt("playcount"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return memberplaylist;
	}

	public List<Memberplaylist> query() {
		List<Memberplaylist> list = new ArrayList<Memberplaylist>();
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from t_memberplaylist";
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
				Memberplaylist memberplaylist = new Memberplaylist();
				memberplaylist.setId(rs.getInt("id"));
				memberplaylist
						.setMember(memberDao.getById(rs.getInt("member")));
				memberplaylist.setMusic(musicDao.getById(rs.getInt("music")));
				memberplaylist.setState(rs.getString("state"));
				memberplaylist.setPlaycount(rs.getInt("playcount"));
				list.add(memberplaylist);
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