var interval;
$(function() {
	$("#content").on("click", "a.delete", function() {
		var r = confirm("确定要删除吗");
		return r;
	});
	showdate();
	startInterval();

	$(".changepage").click(pageClick);
	$(".page1").click(pageClick);
	$("#search").click(function(){
		$("#pageIndex").val(1);
		
	});

});
// 分页操作
function pageClick() {
	var id = $(this).attr("id");
	var pageIndex = $("#pageIndex").val();
	var totalPage = $("#totalPage").val();

	if (id == "first") {
		$("#pageIndex").val(1);
		$("form").submit();
	} else if (id == "previou") {
		if (pageIndex > 1) {
			$("#pageIndex").val(parseInt(pageIndex) - 1);
			$("form").submit();
		}
	} else if (id == "next") {
		if (pageIndex < totalPage) {
			$("#pageIndex").val(parseInt(pageIndex) + 1);
			$("form").submit();
		}
	} else if (id == "last") {
		$("#pageIndex").val(totalPage);
		$("form").submit();
	}
	return false;
}

function showdate() {
	// 获取时间
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var hour = date.getHours();
	var minute = date.getMinutes();
	var second = date.getSeconds();
	var week = [ "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" ];
	if (second < 10) {
		second = "0" + second;
	}
	if (minute < 10) {
		minute = "0" + minute;
	}
	if (hour < 10) {
		hour = "0" + hour;
	}
	var str = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":"
			+ second + " " + week[date.getDay()];
	$(".date").html(str);
}
function startInterval() {
	interval = window.setInterval("showdate()", 1000);
}
function closeInterval() {
	window.clearInterval(interval);
}