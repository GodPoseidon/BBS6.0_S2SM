<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../base.jsp" %>
<link rel="stylesheet" href="css/newsadd.css" type="text/css">
<script type="text/javascript" src="script/jquery-1.11/jquery-1.11.1.js"></script>
<script type="text/javascript" src="script/newsadd.js"></script>
<title>信息发布</title>
</head>
<body>
	<div class="center">
		<div id="middle">
			<form action="person/mynews_newsadd" method="post">
				<div class="title">
					<span>标题：</span><input type="text" name="news.title">
				</div>
				<div class="content">
					<div class="left width">
						<span>内容：</span>
					</div>
					<div class="right text">
						<textarea rows="25" cols="82" name="news.content"></textarea>
					</div>
					<div class="clear"></div>
				</div>
				<div class="foot">
					<input type="submit" value="发  布"><input type="button"
						id="btnReturn" value="返回">
				</div>
			</form>
		</div>
	</div>

</body>
</html>