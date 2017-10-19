package com.chanjetpay.wx.controller;

import com.chanjetpay.wx.api.UserService;
import com.chanjetpay.wx.dto.UserDto;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by libaoa on 2017/10/12.
 */
@Controller
@RequestMapping(value="users")
public class UserController {

	@RequestMapping(value="/list",method={RequestMethod.GET})
	public @ResponseBody
	List<UserDto> list(HttpServletRequest request, HttpServletResponse response, @RequestParam String name) throws InterruptedException{
		System.out.println(request.getCharacterEncoding());
		System.out.println(response.getCharacterEncoding());

		System.out.println(name);
		UserDto user = new UserDto();
		user.setId(100l);
		user.setName("张三");
		//response.setContentType("application/json;charset=UTF-8");
		List<UserDto> userList = new ArrayList<UserDto>();
		userList.add(user);
		return userList;
	}

	@RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.PUT})
	public @ResponseBody UserDto list(@RequestBody UserDto user) throws InterruptedException{
		System.out.println(user.getName());
		user.setId(100L);
		user.setName(user.getName().toUpperCase());
		return user;
	}

}