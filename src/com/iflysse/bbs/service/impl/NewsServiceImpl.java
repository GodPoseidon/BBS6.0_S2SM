package com.iflysse.bbs.service.impl;

import java.util.List;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iflysse.bbs.dao.NewsDao;
import com.iflysse.bbs.dao.ReplyDao;
import com.iflysse.bbs.po.News;
import com.iflysse.bbs.service.NewsService;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
	@Resource(name="newsDao")
	private NewsDao newsDao;
	@Resource(name="replyDao")
	private ReplyDao replyDao;
	
	@Override
	public List<News> getNewsList(int pageIndex, int pageSize) {
		return newsDao.getNewsList((pageIndex - 1) * pageSize, pageSize);
	}

	@Override
	public int getTotalPage(int pageSize) {
		int totalCount = newsDao.getTotalCount();
		int totalPage = 1;
		if (totalCount > pageSize) {
			if (totalCount % pageSize == 0) {
				totalPage = totalCount / pageSize;
			} else {
				totalPage = totalCount / pageSize + 1;
			}
		}
		return totalPage;
	}

	@Override
	public News getNews(int id) {
		return newsDao.getNews(id);
	}

	@Override
	public List<News> getMyNewsList(String title, int userId, int pageIndex,
			int pageSize) {
		return newsDao.getMyNewsList(title, userId, (pageIndex - 1) * pageSize,
				pageSize);
	}

	@Override
	public int getMyPage(int userId, String title, int pageSize) {
		int totalCount = newsDao.getMyNewsCount(userId, title);
		int myPage = 1;
		if (totalCount > pageSize) {
			if (totalCount % pageSize == 0) {
				myPage = totalCount / pageSize;
			} else {
				myPage = totalCount / pageSize + 1;
			}
		}
		return myPage;
	}

	@Override
	public void delete(int id) {
		replyDao.delete(id);
		newsDao.delete(id);
	}

	@Override
	public void update(News news) {
		newsDao.update(news);
	}

	@Override
	public void add(News news) {
		newsDao.add(news);
	}

	@Override
	public News viewNews(int id) {
		newsDao.addBrowseCount(id);
		News news = newsDao.getNews(id);
		return news;
	}

}
