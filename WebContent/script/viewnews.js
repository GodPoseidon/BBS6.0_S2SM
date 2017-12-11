$(function() {
	$("#getReply").click(showReply);
	$("#close").click(function() {
		closeReply();
		return false;
	});
	// 删除按钮的显示和隐藏
	$("#replylist").on("mouseover", "div.list", function() {
		$(this).find(".delete").show();
	});
	$("#replylist").on("mouseout", "div.list", function() {
		$(this).find(".delete").hide();
	});
	// 回复
	$("#replybtn").click(replyInfo);
	// 动态添加按钮的添加事件
	$("#replylist").on("click",".delete",function(){
		var a = $(this);
		$.ajax({
			type: "get",
			url: "reply_replydelete",
			data: {
				"reply.id" : a.attr("id")
			},
			success : function(data) {
				if (data == "true") {
					a.parents("div.list").remove();
				}
			}
		});
		return false;
		
	});

	$("#login").click(function() {
		var url = $(this).attr("href");
		url += "?returnUrl=" + window.location.href;
		$(this).attr("href", url);
	});

});

// 显示
function showReply() {
	$.ajax({	type : "post",
				url : "reply_getReplys",
				data : {
					"reply.newsId" : $("#newsid").val()
				},
				success : function(data) {
					$("#replylist").html("");
					$.each(data,function(index, item) {
										var html = '<div class="list">';
										html += item.userName + '['
												+ item.replyDateString + ']';
										html += ":" + item.content;
										if ($("#userid").val() != "") {
											if ($("#userid").val() == $(
													"#newsuserid").val()
													|| $("#userid").val() == item.userId) {
												html += '<a href="#" id='
														+ item.id
														+ ' class="delete"><img src="images/dele.png" ></a>';
											}
										}
										html += '</div>';
										$("#replylist").append(html);
									});
					if (data.length > 0) {
						$("#replylist").slideDown(500);
						$("#close").show();
					}
				}

			});

	return false;
}
// 隐藏
function closeReply() {
	$("#close").hide();
	$("#replylist").slideUp(500);
}
// 回复按钮
function replyInfo() {
	var content = $.trim($("#reply").val());
	var newsId = $.trim($("#newsid").val());
	if (content != "") {
		$.ajax({
			type : "post",
			url : "reply_newsReply",
			data : {
				"reply.content" : content,
				"reply.newsId" : newsId
			},
			success : function(data) {
				var arr = data.split(';');
				var content = $("#reply").val();
				var html = '<div class="list">' + arr[1] + '：' + content
						+ '<a href="#" class="delete" id="'	 + arr[0]
						+ '"><img src="images/dele.png" ></a></div>';
				$("#replylist").prepend(html);
				$("#reply").val("");

				$("#cent").show();
				$("#replylist").show();
				$("#close").show();
			}
		});
	} else {
		alert("回复内容不能为空");
		return;
	}

}
