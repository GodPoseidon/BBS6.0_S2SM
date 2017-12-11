$(function(){
	$("#btnLogin").click(btnLogin);
	
	$("#verifycode").click(function(){
		$(this).attr("src","user_verify?"+Math.random());
	});
});

function btnLogin(){
	var email=$.trim($("#email").val());
	var password=$.trim($("#password").val());
	var verify=$.trim($("#verify").val());
	if(email==""||password==""||verify==""){
		alert("邮箱和密码和验证码不能为空");
		return;
	}
	$("form").submit();
}

