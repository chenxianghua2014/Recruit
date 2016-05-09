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
		url : "LoadZpglData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			jtjlkName : $("#jtjlkName").val(),
			jtjlkByyx : $("#jtjlkByyx").val(),
			jtjlkZy : $("#jtjlkZy").val(),
			jtjlkXl : $("#jtjlkXl").val(),
			jtjlkZw : $("#jtjlkZw").val(),
			jtjlkZt : "已安排面试,一面结束,二面结束,终面结束",
			jtjlkGwlb : $("#jtjlkGwlb").val()
		},
		dataType : "json",
		success : function(jtjlks) {
			for ( var i = 0; i <= jtjlks.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + jtjlks[i].jtjlkName + "</td>";
				strHtml += "<td>" + jtjlks[i].jtjlkSex + "</td>";
				strHtml += "<td>" + jtjlks[i].jtjlkByxx + "</td>";
				strHtml += "<td>" + jtjlks[i].jtjlkZy + "</td>";
				strHtml += "<td>" + jtjlks[i].jtjlkXl + "</td>";
				strHtml += "<td>" + jtjlks[i].jtjlkZw + "</td>";
				strHtml += "<td>" + jtjlks[i].jtjlkGwlb + "</td>";
				strHtml += "<td class='alignCenter'>" + jtjlks[i].jtjlkMszt
						+ "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"$('#Id').val('"
						+ jtjlks[i].jtjlkId
						+ "');$('#divControll').show();getMsqData('"
						+ jtjlks[i].jtjlkId + "')\"";
				strHtml += "	 	class='inputButton' value='查看' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"showMsjl('"
						+ jtjlks[i].jtjlkId + "','')\"";
				strHtml += "	 	class='inputButton' value='查看' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delLsyh('"
						+ jtjlks[i].jtjlkId + "')\"";
				strHtml += "	 	class='inputButton' value='删除' />";
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
		url : "LoadZpglDataCount",
		data : {
			jtjlkName : $("#jtjlkName").val(),
			jtjlkByyx : $("#jtjlkByyx").val(),
			jtjlkZy : $("#jtjlkZy").val(),
			jtjlkXl : $("#jtjlkXl").val(),
			jtjlkZw : $("#jtjlkZw").val(),
			jtjlkZt : "已安排面试,一面结束,二面结束,终面结束",
			jtjlkGwlb : $("#jtjlkGwlb").val()
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

function ZpxwDel(_id) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "JtjlkDel",
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

function getMsqData(id) {
	var strHtml = "";
	$.ajax({
		type : 'POST',
		url : "LoadMsqData",
		data : {
			id : id
		},
		dataType : "json",
		success : function(msqgls) {
			for ( var i = 0; i <= msqgls.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + (i + 1) + "</td>";
				strHtml += "<td>" + msqgls[i].msqglDetailedMslb + "</td>";
				strHtml += "<td>"
						+ new Date(msqgls[i].msqglDetailedMssj)
								.toLocaleString() + "</td>";
				strHtml += "<td>" + msqgls[i].msqglDetailedMsdd + "</td>";
				strHtml += "<td>" + msqgls[i].msqglDetailedMsg + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"showMsjl('"
						+ msqgls[i].jtjlkId + "','"
						+ msqgls[i].msqglDetailedLszhId + "')\"";
				strHtml += "	 	class='inputButton' value='查看' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delLsyhByMsg('"
						+ msqgls[i].jtjlkId + "','"
						+ msqgls[i].msqglDetailedLszhId + "')\"";
				strHtml += "	 	class='inputButton' value='删除' />";
				strHtml += "</td>";
				strHtml += "</tr>";
			}
			$("#tbList_Msq").html(strHtml);
		}
	});
}

function showMsjl(jlid, msgid) {
	$.jBox.open("iframe:Msjl?jlid=" + jlid + "&msgid=" + msgid, "面试官评论、结论", 600, 400, { buttons: { '关闭': true} });
}

function delLsyh(jlid) {
	if (confirm("确定要删除所有临时帐号吗?")) {
		$.ajax({
			type : 'POST',
			url : "DelLsUser",
			data : {
				jlid : jlid
			},
			dataType : "json",
			success : function(msqgls) {
				alert("删除成功!");
			}
		});
	}
}

function delLsyhByMsg(jlid, msgid) {
	if (confirm("确定要删除该临时帐号吗?")) {
		$.ajax({
			type : 'POST',
			url : "DelLsUser",
			data : {
				jlid : jlid,
				msgid : msgid
			},
			dataType : "json",
			success : function(msqgls) {
				alert("删除成功!");
			}
		});
	}
}

function excute(status) {
	if (confirm("确定对该简历执行'" + status + "'操作吗?")) {
		$.ajax({
			type : 'POST',
			url : "MsjgExcute",
			data : {
				jtjlkId : $("#Id").val(),
				jtjlkJlzt : status
			},
			dataType : "text",
			success : function(_result) {
				alert('操作成功!');
				window.location.reload();
			}
		});
	}
}