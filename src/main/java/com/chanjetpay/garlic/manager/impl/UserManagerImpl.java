package com.chanjetpay.garlic.manager.impl;

import com.chanjetpay.garlic.dao.UserDao;
import com.chanjetpay.garlic.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by libaoa on 2017/10/16.
 */
@Service
public class UserManagerImpl implements UserManager {

/*	@Value( "${feign.url}")
	private String feignUrl;*/

	@Autowired
	private Environment env;

	@Resource
	private UserDao userDao;

	@Override
	public void createUser() {
/*		System.out.println(feignUrl);*/
		System.out.println(env.getProperty("feign.url"));
		userDao.test();
	}
}
