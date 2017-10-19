package com.chanjetpay.wx.dto;

import java.io.Serializable;

/**
 * Created by libaoa on 2017/10/12.
 */
public class UserDto implements Serializable{

	private Long id;
	private String name;

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
