<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<%@ include file="base.jsp" %>
<script type="text/javascript" src="script/jquery-1.11/jquery-1.11.1.js"></script>
<script type="text/javascript" src="script/login.js"></script>
<link rel="stylesheet" href="css/login.css" type="text/css"></link>
<script type="text/javascript">
if (window != top) {
	top.location.href = location.href;
}
</script>
</head>
<body>
	<div class="center">
		<div >
			<form action="user_login" method="post" >
				<div class="left">
					<span>邮箱：</span> <input type="text" name="user.email" id="email" value="${requestScope.email}"></div>
					<div class="left">
					<span>密码：</span> <input type="password" name="user.password" id="password">
				</div>
				<div class="left">
					<span>验证码：</span> <input type="text" name="verify" style="width: 100PX;" id="verify"><img alt="" src="user_verify" id="verifycode">
					<input type="checkbox" style="margin-right: 0px;" name="remmber"><span>记住账号</span>	${requestScope.error}
				</div>
				<input type="hidden" value="${requestScope.returnUrl}" name="returnUrl">
				<div class="left">
					<input type="button" id="btnLogin" value="登 录"></input>
				</div>
				<div class="left">
					<input type="button" value="返 回"></input>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
					