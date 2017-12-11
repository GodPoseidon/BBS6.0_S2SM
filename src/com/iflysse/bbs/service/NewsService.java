package com.iflysse.bbs.service;

import java.util.List;

import com.iflysse.bbs.po.News;

public interface NewsService {

	public List<News> getNewsList(int pageIndex,int pageSize);
	public int getTotalPage(int pageSize);
	
	public News getNews(int id);
	

	public List<News> getMyNewsList(String title,int userId,int pageIndex,int pageSize);
	public int getMyPage(int userId,String title,int pageSize);
	
	public void delete(int id);
	public void update(News news);
	public News viewNews(int id);
	public void add(News news);
}
