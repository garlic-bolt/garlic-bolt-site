package com.chanjetpay.garlic.website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 门户
 * Created by libaoa on 2017/10/17.
 */
@Controller
public class DashboardController {

	@RequestMapping(value = {"", "/"})
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");

		return mav;
	}

	@RequestMapping(value = "/legal")
	public ModelAndView legal() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("legal");

		return mav;
	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("about");

		return mav;
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
