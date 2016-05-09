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
		url : "LoadXxtzData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			userName : $("#userName").val(),
			status : $("#status").val()
		},
		dataType : "json",
		success : function(zpgls) {
			for (var i = 0; i <= zpgls.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + zpgls[i].xxtzUser + "</td>";
				strHtml += "<td>" + zpgls[i].xxtzTelepohne + "</td>";
				strHtml += "<td>" + zpgls[i].xxtzType + "</td>";
				strHtml += "<td>" + zpgls[i].xxtzMessageContent + "</td>";
				strHtml += "<td>"
						+ new Date(zpgls[i].xxtzMessageTime)
								.toLocaleDateString()
						+ " "
						+ new Date(zpgls[i].xxtzMessageTime)
								.toLocaleTimeString() + "</td>";
				strHtml += "<td>" + (zpgls[i].xxtzMessageResult == null ? "发送失败"
						: zpgls[i].xxtzMessageResult.replace("Success", "发送成功")
								.replace("Faild", "发送失败"))
								+ "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"SendAgain('"
						+ zpgls[i].xxtzId + "')\"";
				strHtml += "	 	class='inputButton' value='重发' />";
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
	var status = getQueryString("status");
	if (status != null)
		$("#status").val(status);
	loadData();
});

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

function SendAgain(id) {
	$.ajax({
		type : 'POST',
		url : "SendAgain",
		data : {
			id : id
		},
		dataType : "text",
		success : function(r) {
			r = eval("(" + r + ")");
			if (r.returnstatus == "Success")
				alert("发送成功!");
			else
				alert("发送失败," + r.message + "!");
			loadData();
		}
	});
}

function loadData() {
	$.ajax({
		type : 'POST',
		url : "LoadXxtzDataCount",
		data : {
			userName : $("#userName").val(),
			status : $("#status").val()
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
