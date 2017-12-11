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
<link rel="stylesheet" href="css/viewnews.css" type="text/css">
<script type="text/javascript" src="script/viewnews.js"></script>
<title>信息发布平台</title>
</head>
<body>
	<div class="center">

		<div class="title">
			<span>${requestScope.news.title}</span>
		</div>
		<div class="header">
			<span>发帖人：${requestScope.news.userName} </span> <span>发帖时间：</span><span>
				<fmt:formatDate value="${requestScope.news.publishDate }"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</span>
		</div>
		<div class="content">
			<span>&nbsp;&nbsp;&nbsp;&nbsp;${requestScope.news.content }</span>
		</div>
		<div class="middle"></div>
		<div id="cent">
			<div class="pl">
				<div class="left">
					<a href="#" id="getReply"><span>查看评论</span></a>
				</div>
				<div class="right">
					<a href="#" id="close"><span>关闭</span></a>
				</div>
				<div class="clear"></div>
			</div>
			<div id="replylist">
				<%-- <c:forEach var="item" items="${requestScope.replylist }" varStatus="st">
					<div class="list">
						${item.userName}[<fmt:formatDate value="${item.replyDate }" pattern="yyyy-MM-dd" />]：${item.content}
						<c:if test="${sessionScope.CurrentUser!=null}">
							<c:if test="${sessionScope.CurrentUser.id==requestScope.news.userId||sessionScope.CurrentUser.id==item.userId }">
								<a href="#" id="${item.id }" class="delete"><img src="images/dele.png" ></a>
							</c:if>
						</c:if>
					</div>
				</c:forEach> --%>
			</div>
		</div>
		<input type="hidden" name="reply.newsId" value="${requestScope.news.id }" id="newsid">
		<input type="hidden" name="newsuserid" value="${requestScope.news.userId }" id="newsuserid">
		<input type="hidden" name="userid" value="${sessionScope.CurrentUser!=null ? sessionScope.CurrentUser.id:''}" id="userid">
		<c:if test="${sessionScope.CurrentUser != null }">
			<div class="reply">
				<span>回复：</span> <input type="text" name="reply" id="reply">
				<input type="button" value="回复" id="replybtn">
			</div>

		</c:if>

		<c:if test="${sessionScope.CurrentUser==null }">
			<div class="replya">
				<span>登录后才可评论</span><a href="view_login" id="login">登录</a>
			</div>
		</c:if>
	</div>
</body>
</html>