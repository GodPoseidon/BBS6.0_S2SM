package com.iflysse.bbs.service.impl;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iflysse.bbs.po.User;
import com.iflysse.bbs.service.UserService;

public class TestUserServiceImpl {
	private UserService userService;
	@Before
	public void setUp() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		 userService=ac.getBean("userService", UserService.class);
	}
	@Test
	public void add(){
		User user=new User();
		user.setName("yy");
		user.setEmail("yy");
		user.setPassword("1111");
		userService.add(user);
	}
	
	@Test
	public void getUser(){
		User user=userService.getUser("qq@qq.com", "11111");
		System.out.println(user.getId()+"\t"+user.getName()+"\t"+user.getPassword());
	}
	@Test
	public void updatePassword(){
		userService.updatePassword("111", 1);
		
	}

}
