package com.iflysse.bbs.s2.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iflysse.bbs.po.News;
import com.iflysse.bbs.po.User;
import com.iflysse.bbs.service.NewsService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class MyNewsAction  extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	@Resource(name="newsService")
	private NewsService newsService;
	private Map<String, Object> session;
	private News news;
	private List<News> newslist;
	private int pageIndex=1;
	private int totalPage;
	private String title;
	private String targetUrl;
	private boolean parent=false;
	
	public String newsadd(){
		news.setBrowseCount(0);
		news.setPublishDate(new Date());
		User user=(User)session.get("CurrentUser");
		news.setUserId(user.getId());
		newsService.add(news);
		return "newsadd_success";
	}
	
	public String newsdelete(){
		newsService.delete(news.getId());
		this.targetUrl="person/mynews_newslist";
		return "newsdelete_success";
	}
	
	
	public String newsedit(){
		news=(News)newsService.getNews(news.getId());
		return "newsedit";
	}
	
	public String edit(){
		User user=(User)session.get("CurrentUser");
		news.setPublishDate(new Date());
		news.setBrowseCount(0);
		news.setUserId(user.getId());
		newsService.update(news);
		return "newsedit_success";
	}
	
	
	public String newslist(){
		User user=(User)session.get("CurrentUser");
	    newslist=newsService.getMyNewsList(title, user.getId(), pageIndex, 10);
		totalPage=newsService.getMyPage(user.getId(), title, 10);
		return "newslist_success";
	}
	

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<News> getNewslist() {
		return newslist;
	}

	public void setNewslist(List<News> newslist) {
		this.newslist = newslist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public boolean isParent() {
		return parent;
	}

	public void setParent(boolean parent) {
		this.parent = parent;
	}
	
}

