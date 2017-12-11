package com.iflysse.bbs.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iflysse.bbs.po.News;
import com.iflysse.bbs.service.NewsService;

public class TestNewsServiceImpl {
	private NewsService newsService;
	@Before
	public void setUp() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		newsService=ac.getBean("newsService", NewsService.class);
	}
	@Test
	public void getNewsList(){
		List<News> list=new ArrayList<News>();
		
		list=newsService.getNewsList(1, 10);
		for (News n : list) {
			System.out.println(n.getContent());
		}
	}
	
	@Test
	public void getTotalPage(){
		int page=newsService.getTotalPage(1);
		System.out.println(page);
	}
	
	@Test
	public void getNews(){
		News news=newsService.getNews(10);
		System.out.println(news.getContent()+"\t"+news.getTitle());
	}
	
	@Test
	public void getMyNewsList(){
		List<News> list=newsService.getMyNewsList(null, 8, 1, 30);
		for (News n : list) {
			System.out.println(n.getContent());
		}
	}
	
	@Test
	public void getMyPage(){
		int page=newsService.getMyPage(8, null, 10);
		System.out.println(page);
	}
	
	@Test
	public void delete(){
		newsService.delete(1);
	}
	@Test
	public void update(){
		News news=new News();
		news.setTitle("好好学习");
		news.setContent("好好学习，天天向上");
		news.setPublishDate(new Date());
		news.setBrowseCount(20);
		news.setUserId(4);
		news.setId(11);
		newsService.update(news);
	}
	@Test
	public void add(){
		News news=new News();
		news.setTitle("好好学习");
		news.setContent("好好学习，天天向上");
		news.setPublishDate(new Date());
		news.setBrowseCount(0);
		news.setUserId(3);
		newsService.add(news);
	}

}
