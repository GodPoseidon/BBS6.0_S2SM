$(function() {
	$("input[type='submit']").click(update);
	
	//检查旧密码是否正确
	$("#oldpassword").blur(function() {
		$.ajax({
			type : "post",
			url : "pwdcheck",
			data : {
				oldPassword : $(this).val()
			},
			success : function(data) {
				var result = eval("(" + data + ")");
				if (result) {
					$("#oldpwd_msg").text("旧密码正确");
					$("#btnSubmit").removeAttr("disabled");
				} else {
					$("#oldpwd_msg").text("旧密码错误");
					$("#btnSubmit").attr("disabled", "disabled");
				}
			}
		});
	});

});

function update() {
	var flag = true;
	$(".requierd").each(
			function() {
				var value = $.trim($(this).val());
				if (value == "") {
					var lable = $(this).parents("div.control").find(
							"span:first").text().replace(": ", "");
					lable = trimMiddle(lable);
					$(this).parents("div.control").find(".prompt").text(
							lable + "必填");
					flag = false;
				}
			});
	if (flag) {

		var newpwd = $.trim($("#newpassword").val());
		var confirpassword = $.trim($("#confirpassword").val());
		if (newpwd != confirpassword) {
			flag = false;
			$("#confirpassword_msg").text("两次新密码不一致");
		}
	}

	return flag;
}

function trimMiddle(str) {
	var text = str;
	while (text.indexOf(" ") != -1) {
		text = text.replace(" ", "");
	}
	return text;
}