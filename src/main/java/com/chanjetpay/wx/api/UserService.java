package com.chanjetpay.wx.api;

import com.chanjetpay.wx.dto.UserDto;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

/**
 * Created by libaoa on 2017/10/12.
 */
public interface UserService {
	@RequestLine("GET /users/list?name={name}")
	List<UserDto> getOwner(@Param(value = "name") String name);

	@Headers({"Content-Type: application/json","Accept: application/json"})
	@RequestLine("POST /users/list")
	UserDto getOwner(UserDto user);

}
