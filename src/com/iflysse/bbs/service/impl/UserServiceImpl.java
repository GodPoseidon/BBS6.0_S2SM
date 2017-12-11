package com.iflysse.bbs.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iflysse.bbs.dao.UserDao;
import com.iflysse.bbs.po.User;
import com.iflysse.bbs.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource(name="userDao")
	private UserDao userDao;

	@Override
	public void add(User user) {
		userDao.add(user);
	}

	@Override
	public User getUser(String email, String password) {
		return userDao.getUser(email, password);
	}

	@Override
	public void updatePassword(String password, int id) {
		userDao.updatePassword(password, id);
	}

	@Override
	public int getEmailCount(String email) {
		return userDao.getEmailCount(email);
	}

}
