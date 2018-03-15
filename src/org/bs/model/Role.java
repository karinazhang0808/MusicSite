package org.bs.model;

public class Role {
	private int id;
	private String name;
	private String descp;

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

	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", descp=" + descp + "]";
	}
}