package com.chanjetpay.wx.config;

import com.chanjetpay.wx.controller.OrderService;
import com.chanjetpay.wx.controller.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by libaoa on 2017/10/16.
 */
@Configuration
public class AppConfig {

	public @Bean(name="orderService", autowire= Autowire.BY_TYPE)
	OrderService orderService() {
		return new OrderServiceImpl();
	}
}
