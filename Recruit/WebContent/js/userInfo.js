var pageSize = 10;
var dataCount = 0;
function pageselectCallback(page_index) {
	page_index += 1;
	var strHtml = "";
	var currentPageSize = (dataCount < page_index * pageSize ? dataCount
			- (page_index * pageSize - 10) : pageSize);
	$("#count").html(dataCount);
	if (dataCount == 0)
		$("#start").html(0);
	else
		$("#start").html(page_index * 10 - 9);
	$("#end").html(currentPageSize + page_index * 10 - 10);
	$.ajax({
		type : 'POST',
		url : "LoadUserInfoData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			userName : $("#userName").val(),
			userSfzh : $("#userSfzh").val(),
			userIdcard : $("#userIdcard").val(),
			userTelephone : $("#userTelephone").val(),
			userEmail : $("#userEmail").val()
		},
		dataType : "json",
		success : function(users) {
			for ( var i = 0; i <= users.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + users[i].userName + "</td>";
				strHtml += "<td>" + users[i].userSex + "</td>";
				strHtml += "<td>" + users[i].userAge + "</td>";
				strHtml += "<td>" + users[i].userIdcard + "</td>";
				strHtml += "<td>" + users[i].userSfzh + "</td>";
				strHtml += "<td>" + users[i].userTelephone + "</td>";
				strHtml += "<td>" + users[i].userEmail + "</td>";
				strHtml += "<td>"
						+ new Date(users[i].userAddtime).toLocaleDateString()
						+ " "
						+ new Date(users[i].userAddtime).toLocaleTimeString()
						+ "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"resetPassword('"
						+ users[i].userId + "'" + ",'" + users[i].userName
						+ "')\"";
				strHtml += "	 	class='inputButton' value='重置' />";
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
		url : "LoadUserInfoDataCount",
		data : {
			userName : $("#userName").val(),
			userSfzh : $("#userSfzh").val(),
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

function resetPassword(_id, _name) {
	if (confirm("您确定要重置 '" + _name + "' 的登录密码吗?")) {
		$.ajax({
			type : 'POST',
			url : "ResetPassword",
			data : {
				userId : _id
			},
			dataType : "text",
			success : function(_result) {
				alert('重置密码成功!');
			}
		});
	}
}

function DoChange() {
	$("#lygw").html($("#selLygw option:selected").text());
	$("#jtjlkZzlygw").val($("#selLygw option:selected").text());
	$("#jtjlkZzlygwId").val($("#selLygw").val());
	$.jBox.close();
}