var pageSize = 10;
var dataCount = 0;
function pageselectCallback(page_index, jq) {
	var strHtml = "";
	page_index += 1; 
	var currentPageSize = (dataCount < page_index * pageSize ? dataCount
			- (page_index * pageSize - 10)
			: pageSize);
	$("#count").html(dataCount);
	if(dataCount == 0)
		$("#start").html(0);
	else
		$("#start").html(page_index * 10 - 9);
	$("#end").html(currentPageSize + page_index * 10 - 10);
	$.ajax({
		type : 'POST',
		url : "LoadUserinfoData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			userName : $("#userName").val(),
			userIdcard : $("#userIdcard").val(),
			userTelephone : $("#userTelephone").val(),
			userEmail : $("#userEmail").val()
		},
		dataType : "json",
		success : function(userList) {
			for ( var i = 0; i <= userList.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + userList[i].userName + "</td>";
				strHtml += "<td>" + userList[i].userIdcard + "</td>";
				strHtml += "<td>" + userList[i].userTelephone + "</td>";
				strHtml += "<td>" + userList[i].userEmail + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"checkUser('"
						+ userList[i].userId + "');\"";
				strHtml += "	 	class='inputButton' value='选择' />";
				strHtml += "</td>";
				strHtml += "</tr>";
			}
			$("#tbList").html(strHtml);
		}
	});
	return false;
}
function getOptionsFromForm() {
	var opt = {
		callback : pageselectCallback
	};
	opt.prev_text = "上一页";
	opt.next_text = "下一页";
	return opt;
}
$(document).ready(function() {
	loadData();
});
function loadData() {
	$.ajax({
		type : 'POST',
		url : "LoadUserinfoDataCount",
		data : {
			userName : $("#userName").val(),
			userIdcard : $("#userIdcard").val(),
			userTelephone : $("#userTelephone").val(),
			userEmail : $("#userEmail").val()
		},
		dataType : "json",
		success : function(count) {
			dataCount = count;
			var optInit = getOptionsFromForm();
			$("#Pagination").pagination(dataCount, optInit);
			$("#setoptions").click(function() {
				var opt = getOptionsFromForm();
				$("#Pagination").pagination(dataCount, opt);
			});
		}
	});
}

function checkUser(userId) {
	if (confirm("您确定要查询此用户的记录么?")) {
		$.jBox.tip("正在读取用户记录，请耐心等待一下！", 'loading');
		// 模拟2秒后完成操作
		window.setTimeout(function () {
			window.location="queryMarkBbs?userId="+userId;
			$.jBox.tip('读取完成。', 'success'); 
			}, 8000);
	}
}
