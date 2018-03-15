package org.bs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.bs.dao.MusicDao;
import org.bs.model.Music;
import org.bs.utils.ConnContext;
import org.bs.utils.DB;
import org.bs.utils.PageContext;
import org.bs.dao.ChannelDao;

public class MusicDaoImpl extends BaseDao implements MusicDao {
	private ChannelDao channelDao = new ChannelDaoImpl();

	public void save(Music music) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "insert into t_music (name,author,descp,img,settime,words,bofang,xihuan,channel,musicpath) values(?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, music.getName());
			pstmt.setString(2, music.getAuthor());
			pstmt.setString(3, music.getDescp());
			pstmt.setString(4, music.getImg());
			pstmt.setTimestamp(5, new java.sql.Timestamp(music.getSettime()
					.getTime()));
			pstmt.setString(6, music.getWords());
			pstmt.setInt(7, music.getBofang());
			pstmt.setInt(8, music.getXihuan());
			pstmt.setInt(9, music.getChannel().getId());
			pstmt.setString(10, music.getMusicpath());
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
		String sql = "delete from t_music where id =?";
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

	public void update(Music music) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "update t_music set name=?,author=?,descp=?,img=?,settime=?,words=?,bofang=?,xihuan=?,channel=?,musicpath=?"
				+ " where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, music.getName());
			pstmt.setString(2, music.getAuthor());
			pstmt.setString(3, music.getDescp());
			pstmt.setString(4, music.getImg());
			pstmt.setTimestamp(5, new java.sql.Timestamp(music.getSettime()
					.getTime()));
			pstmt.setString(6, music.getWords());
			pstmt.setInt(7, music.getBofang());
			pstmt.setInt(8, music.getXihuan());
			pstmt.setInt(9, music.getChannel().getId());
			pstmt.setString(10, music.getMusicpath());
			pstmt.setInt(11, music.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public Music getById(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Music music = new Music();
		String sql = "select * from t_music where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				music.setId(rs.getInt("id"));
				music.setName(rs.getString("name"));
				music.setAuthor(rs.getString("author"));
				music.setDescp(rs.getString("descp"));
				music.setImg(rs.getString("img"));
				music.setSettime(rs.getTimestamp("settime"));
				music.setWords(rs.getString("words"));
				music.setBofang(rs.getInt("bofang"));
				music.setXihuan(rs.getInt("xihuan"));
				music.setChannel(channelDao.getById(rs.getInt("channel")));
				music.setMusicpath(rs.getString("musicpath"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return music;
	}

	public Music getByUserId(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Music music = new Music();
		String sql = "select * from t_music where user = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				music.setId(rs.getInt("id"));
				music.setName(rs.getString("name"));
				music.setAuthor(rs.getString("author"));
				music.setDescp(rs.getString("descp"));
				music.setImg(rs.getString("img"));
				music.setSettime(rs.getTimestamp("settime"));
				music.setWords(rs.getString("words"));
				music.setBofang(rs.getInt("bofang"));
				music.setXihuan(rs.getInt("xihuan"));
				music.setChannel(channelDao.getById(rs.getInt("channel")));
				music.setMusicpath(rs.getString("musicpath"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return music;
	}

	public List<Music> query() {
		List<Music> list = new ArrayList<Music>();
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from t_music order by xihuan desc";
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
				Music music = new Music();
				music.setId(rs.getInt("id"));
				music.setName(rs.getString("name"));
				music.setAuthor(rs.getString("author"));
				music.setDescp(rs.getString("descp"));
				music.setImg(rs.getString("img"));
				music.setSettime(rs.getTimestamp("settime"));
				music.setWords(rs.getString("words"));
				music.setBofang(rs.getInt("bofang"));
				music.setXihuan(rs.getInt("xihuan"));
				music.setChannel(channelDao.getById(rs.getInt("channel")));
				music.setMusicpath(rs.getString("musicpath"));
				list.add(music);
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