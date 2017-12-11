package com.iflysse.bbs.service;

import java.util.List;

import com.iflysse.bbs.po.Reply;

public interface ReplyService {
	public List<Reply> getReplyListByNewsId(int newsId);

	public int add(Reply reply);
	
	public void deletebyid(int id);

}
