package com.iflysse.bbs.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iflysse.bbs.po.News;
import com.iflysse.bbs.po.User;
import com.iflysse.bbs.service.NewsService;

@Controller
@Scope("prototype")
public class MyNewsController {
	@Resource(name="newsService")
	private NewsService newsService;
	
	
	@RequestMapping(value="/person/main")
	public String main(){
		return "person/main";
	}
	
	@RequestMapping(value="/person/newsadd",method=RequestMethod.GET)
	public String newsadd(){
		return "person/newsadd";
	}
	
	@RequestMapping(value="/person/newsadd",method=RequestMethod.POST)
	public String newsadd(HttpSession session,News news){
		news.setBrowseCount(0);
		news.setPublishDate(new Date());
		User user=(User)session.getAttribute("CurrentUser");
		news.setUserId(user.getId());
		newsService.add(news);
		
		return "redirect:/person/newslist";
	}
	
	@RequestMapping(value="/person/newsdelete",method=RequestMethod.GET)
	public String newsdelete(int newsid){
		newsService.delete(newsid);
		return "redirect:/person/newslist";
	}
	
	@RequestMapping(value="/person/newsedit/{newsid}",method=RequestMethod.GET)
	public ModelAndView newsedit(@PathVariable(value="newsid") int newsid){
		ModelAndView mv=new ModelAndView("person/newsedit");
		News news=(News)newsService.getNews(newsid);
		mv.addObject("news", news);
		return mv;
	}
	
	
	@RequestMapping(value="/person/newsedit",method=RequestMethod.POST)
	public String newsedit(HttpSession session,News news){
		User user=(User)session.getAttribute("CurrentUser");
		news.setPublishDate(new Date());
		news.setBrowseCount(0);
		news.setUserId(user.getId());
		newsService.update(news);
		
		return "redirect:/person/newslist";
	}
	
	
	@RequestMapping(value="/person/newslist",method=RequestMethod.GET)
	public ModelAndView newslist(HttpSession session){
		ModelAndView mv=new ModelAndView("person/newslist");
		User user=(User)session.getAttribute("CurrentUser");
		List<News> newslist=newsService.getMyNewsList(null, user.getId(), 1, 10);
		int totalPage=newsService.getMyPage(user.getId(), null, 10);
		mv.addObject("newslist", newslist);
		mv.addObject("pageIndex", 1);
		mv.addObject("totalPage", totalPage);
		
		return mv;
	}
	
	@RequestMapping(value="/person/newslist",method=RequestMethod.POST)
	public ModelAndView newslist(HttpSession session,int pageIndex,String title){
		ModelAndView mv=new ModelAndView("person/newslist");
		User user=(User)session.getAttribute("CurrentUser");
		List<News> newslist=newsService.getMyNewsList(title, user.getId(),pageIndex, 10);
		int totalPage=newsService.getMyPage(user.getId(), title, 10);
		mv.addObject("newslist", newslist);
		mv.addObject("pageIndex", pageIndex);
		mv.addObject("totalPage", totalPage);
		mv.addObject("title", title);
		return mv;
	}
	
}
