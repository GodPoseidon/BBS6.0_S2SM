package com.iflysse.bbs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.iflysse.bbs.po.Reply;
@Repository
public interface ReplyDao {
	
	public List<Reply> getReplyListByNewsId(int newsId);
	
	public int add(Reply reply);
	
	public void delete(int newsId);
	
	public void deletebyId(int id);
}
