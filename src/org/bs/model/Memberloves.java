package org.bs.model;

public class Memberloves {
	private int id;
	private Member member;
	private Music music;

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

	public String toString() {
		return "Memberloves [id=" + id + ", member=" + member + ", music="
				+ music + "]";
	}
}