var pageSize = 10;
var dataCount = 0;
function pageselectCallback(page_index) {
	var strHtml = "";
	page_index += 1;
	var currentPageSize = (dataCount < page_index * pageSize ? dataCount
			- (page_index * pageSize - 10) : pageSize);
	$("#count").html(dataCount);
	if (dataCount == 0)
		$("#start").html(0);
	else
		$("#start").html(page_index * 10 - 9);
	$("#end").html(currentPageSize + page_index * 10 - 10);
	$
			.ajax({
				type : 'POST',
				url : "LoadMsqData",
				data : {
					pageNum : page_index,
					pageSize : pageSize,
					currentPageSize : currentPageSize,
					keywords : $("#keywords").val()
				},
				dataType : "json",
				success : function(msqs) {
					for ( var i = 0; i <= msqs.length - 1; i++) {
						strHtml += "<tr>";
						strHtml += "<td>" + (i + 1) + "</td>";
						strHtml += "<td>" + msqs[i].msqName + "</td>";
						strHtml += "<td>" + msqs[i].msqMslb + "</td>";
						strHtml += "<td>" + msqs[i].msqMssj + "</td>";
						strHtml += "<td>" + msqs[i].msqMsdd + "</td>";
						strHtml += "<td>" + msqs[i].msqMsgNames + "</td>";
						if (msqs[i].msqMsgNames.indexOf("已全部删除") == -1) {
							strHtml += "<td>可用</td>";
							strHtml += "<td class='alignCenter'><input name='button'";
							strHtml += "		type='button' onclick=\"editMsq('"
									+ msqs[i].msqId + "');\"";
							strHtml += "	 	class='inputButton' value='编辑' />";
							strHtml += "</td>";
							strHtml += "<td class='alignCenter'><input name='button'";
							strHtml += "		type='button' onclick=\"getMsgAccount('"
									+ msqs[i].msqId + "');\"";
							strHtml += "	 	class='inputButton' value='查看临时账号' />";
							strHtml += "</td>";
							strHtml += "<td class='alignCenter'><input name='button'";
							strHtml += "		type='button' onclick=\"LsyhDel('"
									+ msqs[i].msqId + "');\"";
							strHtml += "	 	class='inputButton' value='删除临时账号' />";
							strHtml += "</td>";
							strHtml += "<td class='alignCenter'><input name='button'";
							strHtml += "		type='button' onclick=\"MsqDel('"
									+ msqs[i].msqId + "');\"";
							strHtml += "	 	class='inputButton' value='删除' />";
							strHtml += "</td>";
							strHtml += "</tr>";
						} else {
							strHtml += "<td>不可用</td>";
							strHtml += "<td class='alignCenter'><input name='button'";
							strHtml += "		type='button' onclick=\"alert('该面试圈状态为不可用!');\"";
							strHtml += "	 	class='inputButton' value='编辑' />";
							strHtml += "</td>";
							strHtml += "<td class='alignCenter'><input name='button'";
							strHtml += "		type='button' onclick=\"alert('该面试圈临时账户已删除!');\"";
							strHtml += "	 	class='inputButton' value='查看临时账号' />";
							strHtml += "</td>";
							strHtml += "<td class='alignCenter'><input name='button'";
							strHtml += "		type='button' onclick=\"alert('该面试圈临时账户已删除!');\"";
							strHtml += "	 	class='inputButton' value='删除临时账号' />";
							strHtml += "</td>";
							strHtml += "<td class='alignCenter'><input name='button'";
							strHtml += "		type='button' onclick=\"MsqDel('"
									+ msqs[i].msqId + "');\"";
							strHtml += "	 	class='inputButton' value='删除' />";
							strHtml += "</td>";
							strHtml += "</tr>";
						}
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
		url : "LoadMsqDataCount",
		data : {
			keywords : $("#keywords").val()
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

function AddMsq() {
	window.location = "MsqAdd";
}
function editMsq(_id) {
	window.location = "MsqAdd?id=" + _id;
}
function getMsgAccount(_id) {
	$.ajax({
		type : 'POST',
		url : "GetUserByMsq",
		data : {
			msqId : _id
		},
		dataType : "json",
		success : function(msgs) {
			var strHtml = "<div class='dataGrid'><table>";
			strHtml += "<tr>";
			strHtml += "<th>面试官";
			strHtml += "</th>";
			strHtml += "<th>登录帐号";
			strHtml += "</th>";
			strHtml += "<th>登录密码";
			strHtml += "</th>";
			strHtml += "</tr>";
			for ( var i = 0; i <= msgs.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + msgs[i].userName;
				strHtml += "</td>";
				strHtml += "<td>" + msgs[i].userIdcard;
				strHtml += "</td>";
				strHtml += "<td>" + msgs[i].userPassword;
				strHtml += "</td>";
				strHtml += "</tr>";
			}
			strHtml += "</table></div>";
			$.jBox.open(strHtml, '临时账号信息', 400, 200, {
				buttons : {
					'关闭' : true
				}
			});
		}
	});
}

function MsqDel(_id) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'POST',
			url : "MsqDel",
			data : {
				id : _id
			},
			dataType : "json",
			success : function(_result) {
				if (_result > 0) {
					alert('删除成功!');
					window.location.reload();
				}
			}
		});
	}
}

function LsyhDel(_id) {
	if (confirm("您确定要删除所有临时账号吗?")) {
		$.ajax({
			type : 'POST',
			url : "LsyhDel",
			data : {
				id : _id
			},
			dataType : "json",
			success : function(_result) {
				if (_result > 0) {
					alert('删除成功!');
					window.location.reload();
				}
			}
		});
	}
}