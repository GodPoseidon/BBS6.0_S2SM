package com.iflysse.bbs.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iflysse.bbs.po.Reply;
import com.iflysse.bbs.po.User;
import com.iflysse.bbs.service.ReplyService;

@Controller
@Scope("prototype")
public class ReplyController {
	@Resource(name="replyService")
	private  ReplyService replyService;
	
	@ResponseBody
	@RequestMapping(value="/getreply",method=RequestMethod.POST)
	public List<Reply> getReply(int newsid,HttpServletResponse response) throws IOException{
		List<Reply> replylist=replyService.getReplyListByNewsId(newsid);
		return replylist;
	}
	
	@ResponseBody
	@RequestMapping(value="/replydelete")
	public boolean replyDelete(int replyid){
		replyService.deletebyid(replyid);
		return true;
	}
	
	@ResponseBody
	@RequestMapping(value="/newsreply",produces="text/html; charset=UTF-8")
	public String newsReply(HttpSession session,Reply reply) throws IOException{
		
		User user=(User)session.getAttribute("CurrentUser");
		reply.setReplyDate(new Date());
		reply.setUserId(user.getId());
		int result=replyService.add(reply);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return result+";"+user.getName()+"["+sdf.format(new Date())+"]";
	}

}
