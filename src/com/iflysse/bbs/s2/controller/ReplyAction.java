package com.iflysse.bbs.s2.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.iflysse.bbs.po.Reply;
import com.iflysse.bbs.po.User;
import com.iflysse.bbs.service.ReplyService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class ReplyAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	
	@Resource(name="replyService")
	private  ReplyService replyService;
	private Map<String, Object> session;
	
	private List<Reply> replylist;
	private Reply reply;
	private String results;
	
	public String getReplys() throws IOException{
	    replylist=replyService.getReplyListByNewsId(reply.getNewsId());
		
//		Object obj=JSONSerializer.toJSON(replylist);
//		String json=obj.toString();
//		PrintWriter out=response.getWriter();
//		out.print(json);
//		out.close();
		return "view";
	}
	
	public void replyDelete() throws IOException{
		replyService.deletebyid(reply.getId());
	}
	
	public String newsReply() throws IOException{
		
		User user=(User)session.get("CurrentUser");
		reply.setReplyDate(new Date());
		reply.setUserId(user.getId());
		int result=replyService.add(reply);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		results= result+";"+user.getName()+"["+sdf.format(new Date())+"]";
		return "add";
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}


	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	public List<Reply> getReplylist() {
		return replylist;
	}

	public void setReplylist(List<Reply> replylist) {
		this.replylist = replylist;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

}
