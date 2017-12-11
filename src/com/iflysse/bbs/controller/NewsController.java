package com.iflysse.bbs.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iflysse.bbs.po.News;
import com.iflysse.bbs.service.NewsService;

@Controller
@Scope("prototype")
public class NewsController {
	@Resource(name="newsService")
	private NewsService service;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv=new ModelAndView("index");
		
		List<News> newslist = service.getNewsList(1, 10);
		int totalPage=service.getTotalPage(10);
		mv.addObject("newslist", newslist);
		mv.addObject("pageIndex", 1);
		mv.addObject("totalPage", totalPage);
		
		return mv;
	}
	
	@RequestMapping(value="/index",method=RequestMethod.POST)
	public ModelAndView index(int pageIndex ){
		ModelAndView mv=new ModelAndView("index");
		
		List<News> newslist=service.getNewsList(pageIndex, 10);
		int totalPage=service.getTotalPage(10);
		
		mv.addObject("newslist", newslist);
		mv.addObject("pageIndex", pageIndex);
		mv.addObject("totalPage", totalPage);
		
		return mv;
	}
	
	
	@RequestMapping(value="/viewnews/{newsid}")
	public ModelAndView viewnews(@PathVariable(value="newsid") int newsid){
		ModelAndView mv=new ModelAndView("viewnews");
		News news=service.viewNews(newsid);
		
		mv.addObject("news", news);
		return mv;
	}
}
