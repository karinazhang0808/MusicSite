package org.bs.model;

import java.util.Date;

public class Music {
	private int id;
	private String name;
	private String author;
	private String descp;
	private String img;
	private Date settime;
	private String words;
	private int bofang;
	private int xihuan;
	private Channel channel;
	private String musicpath;
	private Double simvalue;

	
	public Double getSimvalue() {
		return simvalue;
	}

	public void setSimvalue(Double simvalue) {
		this.simvalue = simvalue;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getSettime() {
		return settime;
	}

	public void setSettime(Date settime) {
		this.settime = settime;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}


	public int getBofang() {
		return bofang;
	}

	public void setBofang(int bofang) {
		this.bofang = bofang;
	}

	public int getXihuan() {
		return xihuan;
	}

	public void setXihuan(int xihuan) {
		this.xihuan = xihuan;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getMusicpath() {
		return musicpath;
	}

	public void setMusicpath(String musicpath) {
		this.musicpath = musicpath;
	}

	@Override
	public String toString() {
		return "Music [id=" + id + ", name=" + name + ", author=" + author
				+ ", descp=" + descp + ", img=" + img + ", settime=" + settime
				+ ", words=" + words + ", bofang=" + bofang + ", xihuan="
				+ xihuan + ", channel=" + channel + ", musicpath=" + musicpath
				+ ", simvalue=" + simvalue + "]";
	}
}