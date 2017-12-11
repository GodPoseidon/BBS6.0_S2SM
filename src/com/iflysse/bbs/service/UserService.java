package com.iflysse.bbs.service;

import com.iflysse.bbs.po.User;

public interface UserService {
	public void add(User user);
	public User getUser(String email,String password);
	
	public void updatePassword(String password,int id);
	
	public int getEmailCount(String email);
}
