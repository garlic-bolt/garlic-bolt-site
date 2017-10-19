package com.chanjetpay.wx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by libaoa on 2017/10/17.
 */
@Controller
public class DashboardController {

	@RequestMapping(value={"", "/"})
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");

		return mav;
	}

	@RequestMapping(value="/home")
	public ModelAndView home(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dashboard/welcome");

		return mav;
	}

	@RequestMapping(value="/qrorder")
	public ModelAndView qrpay(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cashier/qrorder");

		return mav;
	}

	@RequestMapping(value="/course")
	public ModelAndView course(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("counterman/course");

		return mav;
	}

	@RequestMapping(value="/ranking")
	public ModelAndView ranking(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dashboard/ranking");

		return mav;
	}

	@RequestMapping(value="/about")
	public ModelAndView about(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("about");

		return mav;
	}

	@RequestMapping(value="/register")
	public ModelAndView responsive(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("register");

		return mav;
	}
}
