package com.chanjetpay.wx.dao.impl;

import com.chanjetpay.wx.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * Created by libaoa on 2017/10/16.
 */
@Repository
public class UserDaoImpl implements UserDao {
	@Override
	public void test() {
		System.out.println("dao impl");
	}
}
