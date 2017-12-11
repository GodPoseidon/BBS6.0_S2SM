package com.iflysse.bbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.iflysse.bbs.po.News;

@Repository
public interface NewsDao {
	public List<News> getNewsList(@Param("pageIndex")int pageIndex, @Param("pageSize")int pageSize);

	public int getTotalCount();

	public News getNews(@Param("id")int id);

	public List<News> getMyNewsList(@Param("title")String title,@Param("userId") int userId,@Param("pageIndex") int pageIndex,
			@Param("pageSize")int pageSize);

	public int getMyNewsCount(@Param("userId")int userId, @Param("title")String title);

	public void delete(int id);

	public void update(News news);

	public void add(News news);

	public News viewNews(int id);

	public void addBrowseCount(int id);

}
