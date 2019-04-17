package jspbook.ch03;

import java.util.Objects;

public class Member {
	private int id;
	private String name;
	private String email;
	private String tel;
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}

	public void createId() {
		if (name != null && email != null && tel != null)
			id = Objects.hash(name, email, tel);
			System.out.println(toString());
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", email=" + email + ", tel=" + tel + "]";
	}

}
