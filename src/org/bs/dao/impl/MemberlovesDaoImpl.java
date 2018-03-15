package org.bs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.bs.dao.MemberlovesDao;
import org.bs.model.Memberloves;
import org.bs.utils.ConnContext;
import org.bs.utils.DB;
import org.bs.utils.PageContext;
import org.bs.dao.MemberDao;
import org.bs.dao.MusicDao;

public class MemberlovesDaoImpl extends BaseDao implements MemberlovesDao {
	private MemberDao memberDao = new MemberDaoImpl();
	private MusicDao musicDao = new MusicDaoImpl();

	public void save(Memberloves memberloves) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "insert into t_memberloves (member,music) values(?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberloves.getMember().getId());
			pstmt.setInt(2, memberloves.getMusic().getId());
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
		String sql = "delete from t_memberloves where id =?";
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

	public void update(Memberloves memberloves) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "update t_memberloves set member=?,music=?"
				+ " where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberloves.getMember().getId());
			pstmt.setInt(2, memberloves.getMusic().getId());
			pstmt.setInt(3, memberloves.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public Memberloves getById(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Memberloves memberloves = new Memberloves();
		String sql = "select * from t_memberloves where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberloves.setId(rs.getInt("id"));
				memberloves.setMember(memberDao.getById(rs.getInt("member")));
				memberloves.setMusic(musicDao.getById(rs.getInt("music")));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return memberloves;
	}

	public Memberloves getByUserId(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Memberloves memberloves = new Memberloves();
		String sql = "select * from t_memberloves where user = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberloves.setId(rs.getInt("id"));
				memberloves.setMember(memberDao.getById(rs.getInt("member")));
				memberloves.setMusic(musicDao.getById(rs.getInt("music")));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return memberloves;
	}

	public List<Memberloves> query() {
		List<Memberloves> list = new ArrayList<Memberloves>();
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from t_memberloves";
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
				Memberloves memberloves = new Memberloves();
				memberloves.setId(rs.getInt("id"));
				memberloves.setMember(memberDao.getById(rs.getInt("member")));
				memberloves.setMusic(musicDao.getById(rs.getInt("music")));
				list.add(memberloves);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return list;
	}
	public List<Memberloves> query(String str) {
		List<Memberloves> list = new ArrayList<Memberloves>();
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from t_memberloves";
		if(str!=null&&!"".equals(str)){
			if(str.contains("喜欢")){
				str = str.replace("喜欢", "");
				sql+=" where music = '"+str.split("-")[0]+"' and member = "+str.split("-")[1];
			}
		}
		if(str!=null&&!"".equals(str)){
			if(str.contains("会员")){
				str = str.replace("会员", "");
				sql+=" where  member = "+str;
			}
		}
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
				Memberloves memberloves = new Memberloves();
				memberloves.setId(rs.getInt("id"));
				memberloves.setMember(memberDao.getById(rs.getInt("member")));
				memberloves.setMusic(musicDao.getById(rs.getInt("music")));
				list.add(memberloves);
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