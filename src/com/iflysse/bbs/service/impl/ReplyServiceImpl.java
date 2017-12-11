package com.iflysse.bbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iflysse.bbs.dao.ReplyDao;
import com.iflysse.bbs.po.Reply;
import com.iflysse.bbs.service.ReplyService;
@Service("replyService")
public class ReplyServiceImpl implements ReplyService {
	@Resource(name="replyDao")
	private ReplyDao replyDao; 
	
	
	@Override
	public List<Reply> getReplyListByNewsId(int newsId) {
		return replyDao.getReplyListByNewsId(newsId);
	}

	@Override
	public int add(Reply reply) {
		 replyDao.add(reply);
		 return reply.getId();
	}

	@Override
	public void deletebyid(int id) {
		replyDao.deletebyId(id);
	}

}
