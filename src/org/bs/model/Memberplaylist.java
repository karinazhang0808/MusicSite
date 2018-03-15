package org.bs.model;

public class Memberplaylist {
	private int id;
	private Member member;
	private Music music;
	private String state;
	private int playcount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPlaycount() {
		return playcount;
	}

	public void setPlaycount(int playcount) {
		this.playcount = playcount;
	}

	public String toString() {
		return "Memberplaylist [id=" + id + ", member=" + member + ", music="
				+ music + ", state=" + state + ", playcount=" + playcount + "]";
	}
}