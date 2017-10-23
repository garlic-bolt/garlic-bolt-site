package com.chanjetpay.garlic.website;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 营销
 * Created by libaoa on 2017/10/20.
 */
public class MarketController {

	@RequestMapping(value="/ranking")
	public ModelAndView ranking(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dashboard/ranking");

		return mav;
	}


}
