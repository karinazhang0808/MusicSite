package org.bs.model;

import java.util.ArrayList;
import java.util.List;

public class Channel {
	private int id;
	private String name;
	private String descp;
	private List<Music> musics = new ArrayList<Music>();

	
	public List<Music> getMusics() {
		return musics;
	}

	public void setMusics(List<Music> musics) {
		this.musics = musics;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	@Override
	public String toString() {
		return "Channel [id=" + id + ", name=" + name + ", descp=" + descp
				+ ", musics=" + musics + "]";
	}
}