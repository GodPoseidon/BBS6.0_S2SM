<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="base.jsp"%>
<script type="text/javascript" src="script/jquery-1.11/jquery-1.11.1.js"></script>
<script type="text/javascript" src="script/newslist.js"></script>
<link rel="stylesheet" href="css/index.css" type="text/css">
<title>信息发布平台</title>
</head>
<body>
	<div class="center">
		<c:if test="${sessionScope.CurrentUser==null}">
			<div class="title">
				<div class="left header">欢迎您</div>
				<div class="right header">
					<a href="view_register">注册</a> <a href="view_login">登录</a>
				</div>
				<div class="clear"></div>
			</div>
		</c:if>


		<c:if test="${sessionScope.CurrentUser != null }">
			<div id="header">
				<div class="left header">欢迎你:${sessionScope.CurrentUser.name}</div>
				<div class="right header">
					<a href="person/view_main">我的账号</a> <a href="person/user_cancel">注销</a>
				</div>
				<div class="clear"></div>
			</div>
		</c:if>


		<div id="middle">
			<div class="date">
				<span></span>
			</div>
			<div class="middle">
				<table width="100%" border="0">
					<tr id="tableheader">
						<td width="70px;">序号</td>
						<td>标题</td>
						<td width="150px;">发帖人</td>
						<td width="250px;">发帖时间</td>
						<td width="150px;">浏览次数</td>
					</tr>

					<c:forEach var="item" items="${requestScope.newslist }"
						varStatus="st">

						<tr class='${st.index % 2==0?"trcolcor1":"trcolcor2"}'>
							<td>${st.index+1 }</td>
							<td><a href="news_view?news.id=${item.id}" target="_blank">${item.title }</a></td>
							<td>${item.userName}</td>
							<td><fmt:formatDate value="${item.publishDate }"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${item.browseCount}</td>
						</tr>
					</c:forEach>



				</table>
			</div>
			<div class="totalpage">${requestScope.pageIndex}/${requestScope.totalPage}</div>
			<div id="page">
				<form action="news_list" method="post">
					<input type="hidden" id="pageIndex" name="pageIndex"
						value="${requestScope.pageIndex}"> <input type="hidden"
						id="totalPage" value="${requestScope.totalPage}"> <a
						href="" class="changepage" id="first">首页</a> <a href=""
						class="changepage" id="previou">上页</a> <a href=""
						class="changepage" id="next">下页</a> <a href="" class="changepage"
						id="last">末页</a>

				</form>
			</div>
		</div>
		<div id="foot">
			<span>合肥讯飞教育</span>
		</div>
	</div>
</body>
</html>