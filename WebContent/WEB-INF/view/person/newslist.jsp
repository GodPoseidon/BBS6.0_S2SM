<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../base.jsp"%>
<script type="text/javascript" src="script/jquery-1.11/jquery-1.11.1.js"></script>
<script type="text/javascript" src="script/newslist.js"></script>
<link rel="stylesheet" href="css/newslist.css" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<div class="center">
		<div>
			<form action="person/mynews_newslist" method="post">
				<div class="header">
					<input type="hidden" id="pageIndex" name="pageIndex"
						value="${requestScope.pageIndex}"> <input type="hidden"
						id="totalPage" value="${requestScope.totalPage}"> <span>标题</span><input
						type="text" name="title" class="title"
						value="${requestScope.title}"><input type="submit"
						value="查  询" class="search" id="search">
				</div>
			</form>
		</div>
		<div class="middle">
			<a href="person/view_newsadd" class="newsadd">发布信息</a>
		</div>
		<div id="content">
			<table border="0" width="100%">
				<tr class="tableheader">
					<td width="70px;"><input type="checkbox"></td>
					<td>标题</td>
					<td>内容</td>
					<td>发帖时间</td>
					<td width="150px;">操作</td>
				</tr>
				<c:forEach items="${requestScope.newslist }" var="item"
					varStatus="st">
					<tr class='${st.index%2==0?"odd":"even" }'>
						<td><input type="checkbox"></td>
						<td>${item.title}</td>
						<td>${item.content.length()>15?item.content.substring(0,15).concat("..."):item.content}</td>
						<td><fmt:formatDate value="${item.publishDate }"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><a href="person/mynews_newsdelete?news.id=${item.id}" class="delete">删除</a><a
							href="person/mynews_newsedit?news.id=${item.id}">修改</a></td>
				</c:forEach>
			</table>
		</div>
		<div>
			<div id="page">${requestScope.pageIndex}/${requestScope.totalPage}</div>
			<div class="page">
				<a href="#" class="page1" id="first">首页</a> <a href="#"
					class="page1" id="previou">上页</a> <a href="#" class="page1"
					id="next">下页</a> <a href="#" class="page1" id="last">末页</a>
			</div>
		</div>

	</div>
</body>
</html>