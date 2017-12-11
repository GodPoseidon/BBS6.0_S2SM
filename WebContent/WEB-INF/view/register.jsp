<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="base.jsp" %>
<script type="text/javascript" src="script/jquery-1.11/jquery-1.11.1.js"></script>
<link rel="stylesheet" href="css/register.css" type="text/css">
<script type="text/javascript" src="script/register.js"></script>
<title>注册</title>
</head>
<body>
	<div class="center">
		<div class="middle">
			<form action="user_register" method="post">
				<div class="title">
					<span>信息发布平台</span>
				</div>
				<div>
					<span>邮&nbsp;&nbsp;箱：</span><input type="text" name="user.email" id="email" class="input"><span id="email_msg"></span>
				</div>
				<div>
					<span>昵&nbsp;&nbsp;称: </span><input type="text" name="user.name" id="name" class="input"><span id="name_msg"></span>
				</div>
				<div>
					<span>密&nbsp;&nbsp;码：</span><input type="password" name="user.password" id="password" class="input"><span id="password_msg"></span>
				</div>
				<div>
					<span>确认密码：</span><input type="password" name="confirpwd" id="password1" class="input"><span id="password1_msg"></span>
				</div>
				<div class="left" >
					<input type="submit" value="注册" id="btnRegister" ><input type="button" value="返回" id="back">
				</div>
			</form>
		</div>
	</div>
</body>
</html>