package com.iflysse.bbs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iflysse.bbs.po.Reply;
import com.iflysse.bbs.service.ReplyService;

public class TestReplyServiceImpl {
 private ReplyService replyService;

	@Before
	public void setUp() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		replyService = ac.getBean("replyService", ReplyService.class);
	}

	@Test
	public void getReplyListByNewsId() {
		List<Reply> list = new ArrayList<Reply>();

		list = replyService.getReplyListByNewsId(3);
		for (Reply r : list) {
			System.out.println(r.getContent());
		}
	}

	@Test
	public void add(){
		Reply reply=new Reply();
		reply.setContent("233");
		reply.setReplyDate(new Date());
		reply.setUserId(1);
		reply.setNewsId(11);
		replyService.add(reply);
	}
}
