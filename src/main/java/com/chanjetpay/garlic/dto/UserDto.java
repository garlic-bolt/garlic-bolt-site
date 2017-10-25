package com.chanjetpay.garlic.dto;

import java.io.Serializable;

/**
 * Created by libaoa on 2017/10/12.
 */
public class UserDto implements Serializable{

	private Long id;
	private String name;

	private Boolean admin;

	public Boolean isAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserDto{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
