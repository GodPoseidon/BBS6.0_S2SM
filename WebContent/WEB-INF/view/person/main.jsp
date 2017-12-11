<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../base.jsp" %>
<link rel="stylesheet" href="css/main.css" type="text/css">
<title>主页</title>
</head>
<body>
	<div class="center">
		<div id="header">
		<div class="left header">
		<a href="news_list">首页</a><span class="title">欢迎你：${sessionScope.CurrentUser.name }</span>
		</div>
		<div class="right header">
		<a href="#">我的账号</a>
		<a href="person/user_cancel" class="title">注销</a>
		</div>
		<div class="clear"></div>
		</div>
		
		<div id="middle">
		<div class="left menu">
			<div>
			<ul>
			<li><a href="person/mynews_newslist" target="frame">信息管理</a></li>
			<li><a href="person/view_pwdedit" target="frame">密码修改</a></li>
			<li><a href="http://www.sina.com">新&nbsp;&nbsp;浪</a></li>
			<li><a href="http://www.baidu.com">网&nbsp;&nbsp;易</a></li>
			<li><a href="http://www.baidu.com">百&nbsp;&nbsp;度</a></li>
			</ul>
			</div>
		</div>
		<div class="right frame">
		<iframe frameborder="0" name="frame" src="" id="frame"></iframe>
		</div>
		<div class="clear"></div>
		</div>
		<div id="foot">合肥讯飞教育</div>
	</div>
</body>
</html>