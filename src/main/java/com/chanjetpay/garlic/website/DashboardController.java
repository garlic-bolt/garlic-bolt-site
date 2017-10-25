package com.chanjetpay.garlic.website;

import com.chanjetpay.garlic.dto.UserDto;
import com.chanjetpay.garlic.pojo.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.*;

/**
 * 门户
 * Created by libaoa on 2017/10/17.
 */
@Controller
public class DashboardController {

	@RequestMapping(value = {"", "/"})
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("comment","c俄式123");
		mav.addObject("welcome","c俄式123");

		UserDto u1 = new UserDto();
		u1.setId(100l);
		u1.setName("咯辉煌");
		mav.addObject("u",u1);
		mav.setViewName("index");

		mav.addObject("welcome","Welcome to our <b>fantastic</b> grocery store!");

		List<UserDto> list = new ArrayList<>();
		list.add(u1);
		mav.addObject("list",list);

		String s="123";
		mav.addObject("submit","submit");
		return mav;
	}

	@RequestMapping(value = "/legal")
	public ModelAndView legal() {
		ModelAndView mav = new ModelAndView();
		String[] list = {"123","345"};
		List<String> ol = new ArrayList<>();
		ol.add("测试1");
		ol.add("测试2");
		mav.addObject("opts",ol);
		mav.setViewName("例子页面");

		return mav;
	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("webody");

		return mav;
	}

	@RequestMapping("/ddd")
	String ddd(Principal principal) {
		return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
	}

	@RequestMapping(value = "/demo")
	public ModelAndView demo() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("demo");

		return mav;
	}

	@RequestMapping(value = "/help")
	public ModelAndView help() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("help");

		return mav;
	}

	@RequestMapping(value = "/register")
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("register");

		return mav;
	}
}
