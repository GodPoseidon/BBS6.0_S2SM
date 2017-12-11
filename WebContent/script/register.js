$(function() {
	$("#btnRegister").click(register);
	$(".input").blur(function() {
		var v = $(this).val();
		var id = $(this).attr("id");
		if (v != "") {
			$("#" + id + "_msg").text("");
		} else {
			$("#" + id + "_msg").text("必填");
			$("#" + id + "_msg").css("color", "red");
		}
	});

	// 检查邮箱是否存在
	$("#email").blur(checkEmail);

});
function register() {
	var email = $.trim($("#email").val());
	var name = $.trim($("#name").val());
	var password = $.trim($("#password").val());
	var password1 = $.trim($("#password1").val());
	var flag = true;
	if (email == "") {
		$("#email_msg").text("邮箱不能为空");
		flag = false;
	}
	if (name == "") {
		$("#name_msg").text("昵称不能为空");
		flag = false;
	}
	if (password == "") {
		$("#password_msg").text("密码不能为空");
		flag = false;
	}

	if (password1 == "") {
		$("#password1_msg").text("确认密码不能为空");
		flag = false;
	}
	if (password != password1) {
		$("#password_msg").text("两次密码不一致");
		$("#password1_msg").text("");
		flag = false;
	}
	return flag;
}

function checkEmail() {
	var email = $.trim($("#email").val());
	if (email != "") {
		$.ajax({
			type : "post",
			url : "user_checkEmail",
			data : {email : email},
			success : function(data) {
				var result=eval('('+data+')');
				if(result==false){
					$("#email_msg").text("该邮箱可用");
					$("#email_msg").css("color","green");
					$("#btnRegister").removeAttr("disabled");
				}else{
					$("#email_msg").text("该邮箱已存在");
					$("#email_msg").css("color","red");
					$("#btnRegister").attr("disabled","disabled");
				}
			}
		});
	}else{
		$("#email_msg").text("必填");
		$("#email_msg").css("color","red");
	}

}