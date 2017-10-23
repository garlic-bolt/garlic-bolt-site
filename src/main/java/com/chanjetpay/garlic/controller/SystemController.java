package com.chanjetpay.garlic.controller;

import com.chanjetpay.garlic.api.UserService;
import com.chanjetpay.garlic.dto.UserDto;
import com.chanjetpay.garlic.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by libaoa on 2017/10/11.
 */
@Controller
/*@RequestMapping(value={"", "/"})*/
@RequestMapping(value={"system", "/sys"})
public class SystemController {

	//UserService userService = Feign.builder()
	//		.options(new Request.Options(1000, 3500))
	//		.retryer(new Retryer.Default(5000, 5000, 3))
	//		.target(UserService.class, "http://127.0.0.1:8090");
	//
	//UserService userService2 = Feign.builder()
	//		.encoder(new JacksonEncoder())
	//		.decoder(new JacksonDecoder())
	//		.options(new Request.Options(1000, 3500))
	//		.retryer(new Retryer.Default(5000, 5000, 3))
	//		.target(UserService.class, "http://127.0.0.1:8090");

	@Autowired
	@Qualifier("userService")
	private UserService userService2;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserManager userManager;

	@RequestMapping(value={"/",""},method = {RequestMethod.GET})
	public void index(){
		//orderService.testOrder();

		//AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		//String[] beanNames = ctx.getBeanDefinitionNames();
		//Arrays.sort(beanNames);
		//for (String beanName : beanNames) {
		//	System.out.println(beanName);
		//}
		//
		//OrderService orderService2 = (OrderService)ctx.getBean("orderService");
		//orderService2.testOrder();
		orderService.testOrder();
		userManager.createUser();
		System.out.println(1111111);
	}

	@RequestMapping(value="/testget",method = {RequestMethod.GET})
	public @ResponseBody String testget(){
		List<UserDto> list = userService2.getOwner("scott");
		System.out.println(list);
		return list.get(0).getName();
	}

	@RequestMapping(value="/testpost",method = {RequestMethod.GET})
	public @ResponseBody UserDto testpost() throws InterruptedException{
		UserDto to = new UserDto();
		to.setId(200l);
		to.setName("张三123abv");
		UserDto u = userService2.getOwner(to);
		System.out.println(u);
		return u;
	}

	@RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
	public @ResponseBody String list(@RequestParam String name) throws InterruptedException{
		return name.toUpperCase();
	}


	@RequestMapping(value="/test")
	public @ResponseBody Map<String, Object> test(@RequestBody String str){

		System.out.println(str);


		Map<String,Object> map = new HashMap<String,Object>();

		map.put("message","hello world");
		map.put("id",1001);
		return map;
	}


	@RequestMapping(value="/error",method = {RequestMethod.GET})
	public void error(){
		System.out.println(1111111);
	}

	@ExceptionHandler
	public void exception(Exception e){
		e.printStackTrace();
	}
}
