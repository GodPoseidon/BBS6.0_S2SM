package com.iflysse.bbs.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.iflysse.bbs.po.User;

@Repository
public interface UserDao {
	public void add(User user);
	public User getUser(@Param("email") String email,@Param("password")String password);
	
	public void updatePassword(@Param("password")String password,@Param("id")int id);
	
	public int getEmailCount(String email);
	
}
