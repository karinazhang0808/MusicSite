package org.bs.model;

import java.util.ArrayList;
import java.util.List;

public class Member {
	private int id;
	private Role roles;
	private String username;
	private String password;
	private String name;
	private String tel;
	private String address;
	private String email;
	private User user;
	private List<Music> musics = new ArrayList<Music>();
	private Double simvalue;
	
	


	public Double getSimvalue() {
		return simvalue;
	}

	public void setSimvalue(Double simvalue) {
		this.simvalue = simvalue;
	}

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

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", roles=" + roles + ", username="
				+ username + ", password=" + password + ", name=" + name
				+ ", tel=" + tel + ", address=" + address + ", email=" + email
				+ ", user=" + user + ", musics=" + musics + ", simvalue="
				+ simvalue + "]";
	}
}