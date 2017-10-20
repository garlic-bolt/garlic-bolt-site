package com.chanjetpay.wx.website;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 个人
 * Created by libaoa on 2017/10/20.
 */
public class PersonalController {

	@RequestMapping(value="/home")
	public ModelAndView home(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dashboard/welcome");

		return mav;
	}
}
