package com.chanjetpay.garlic.config;

import com.chanjetpay.garlic.controller.OrderService;
import com.chanjetpay.garlic.controller.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
