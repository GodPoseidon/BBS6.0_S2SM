package com.iflysse.bbs.s2.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.iflysse.bbs.po.News;
import com.iflysse.bbs.service.NewsService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class NewsAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	@Resource(name="newsService")
	private NewsService newsservice;
	private List<News> newslist;
	private int totalPage;
	private int pageIndex=1;
	private News news;
	
	public String list(){
	    newslist = newsservice.getNewsList(pageIndex, 10);
		totalPage=newsservice.getTotalPage(10);
		return "list";
	}

	
	
	public String view(){
		news=newsservice.viewNews(news.getId());
		return "view";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<News> getNewslist() {
		return newslist;
	}

	public void setNewslist(List<News> newslist) {
		this.newslist = newslist;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}



	public News getNews() {
		return news;
	}



	public void setNews(News news) {
		this.news = news;
	}
}
