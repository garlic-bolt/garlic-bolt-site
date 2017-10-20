package com.chanjetpay.wx.website;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 商户
 * Created by libaoa on 2017/10/20.
 */
public class MerchantController {

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
}
