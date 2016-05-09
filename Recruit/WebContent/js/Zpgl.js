var pageSize = 10;
var dataCount = 0;
var pi = 0;
var starPx = 0;
function pageselectCallback(page_index) {
	pi = page_index;
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
	$
			.ajax({
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
					jtjlkZt : type,
					jtjlkGwlb : $("#jtjlkGwlb").val(),
					star : starPx
				},
				dataType : "json",
				success : function(jtjlks) {
					for (var i = 0; i <= jtjlks.length - 1; i++) {
						strHtml += "<tr>";
						strHtml += "<td><input name='subBox' type='checkbox' value='"
								+ jtjlks[i].jtjlkId + "'/></td>";
						strHtml += "<td>" + jtjlks[i].jtjlkName + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkCsrq + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkSfzh + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkSex + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkByxx + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkZy + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkXl + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkZw + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkHkd + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkCpcj + "</td>";
						strHtml += "<td class='alignCenter'>"
								+ jtjlks[i].jtjlkMszt + "</td>";
						strHtml += "<td class='alignCenter'>"
								+ jtjlks[i].jtjlkCpjg + "</td>";
						if (type == "录用")
							strHtml += "<td class='alignCenter'>"
									+ jtjlks[i].jtjlkZzlygw + "</td>";
						strHtml += "<td class='alignCenter'><div id=\""
								+ jtjlks[i].jtjlkId
								+ "\">"
								+ starts(jtjlks[i].jtjlkId,
										jtjlks[i].jtjlkstar, jtjlks[i].txt);
						+"</div></td>";
						strHtml += "<td>"
								+ formatLongDate(jtjlks[i].jtjlkAddtime)
								+ "</td>";
						// strHtml += "<td class='alignCenter'><input
						// name='button'";
						// strHtml += " type='button' onclick=\"$('#Id').val('"
						// + jtjlks[i].jtjlkId
						// + "');$('#divControll').show();getMsqData('"
						// + jtjlks[i].jtjlkId + "')\"";
						// strHtml += " class='inputButton' value='查看' />";
						// strHtml += "</td>";
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"$('#divControll').show();showMsjl('"
								+ jtjlks[i].jtjlkId
								+ "','');$('#divControllBatch').hide();document.body.scrollTop = document.body.scrollHeight;\"";
						strHtml += "	 	class='inputButton' value='查看' />";
						strHtml += "</td>";
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"$('#divControll').show();showCpjl('"
								+ jtjlks[i].jtjlkSfzh
								+ "');$('#divControllBatch').hide();document.body.scrollTop = document.body.scrollHeight;\"";
						strHtml += "	 	class='inputButton' value='查看' />";
						strHtml += "</td>";
						if (type != "录用") {
							// if (jtjlks[i].jtjlkCpjg == "测评通过") {
							strHtml += "<td class='alignCenter'><input name='button'";
							strHtml += "		type='button' onclick=\"backUp('"
									+ jtjlks[i].jtjlkId + "')\"";
							strHtml += "	 	class='inputButton' value='恢复' />";
							strHtml += "</td>";
							// } else {
							// strHtml += "<td class='alignCenter'>不可用</td>";
							// }
						}
						strHtml += "</tr>";
					}
					$("#tbList").html(strHtml);
				}
			});
	return false;
}

function backUp(id) {
	if (confirm("确定恢复该简历到到招聘流程中作吗?")) {
		$.ajax({
			type : 'POST',
			url : "UpdateZpglStatus",
			data : {
				jtjlkId : id,
				jtjlkJlzt : "恢复",
				page : "Yttjl"
			},
			dataType : "text",
			success : function(_result) {
				alert('操作成功!');
				reload();
			}
		});
	}
}

// 批量执行
function backUpBatch() {
	var text = new Array();
	if ($("input:checkbox[name='subBox']:checked").length < 1) {
		alert("请至少选择一项");
		return;
	} else {
		$("input[name=subBox]").each(function() {
			if ($(this).attr("checked")) {
				text.push($(this).val());
			}
		});
	}

	if (confirm("确定恢复该简历到到招聘流程中作吗?")) {
		$.ajax({
			type : 'POST',
			url : "UpdateZpglStatus",
			data : {
				jtjlkId : text.join(","),
				jtjlkJlzt : "恢复",
				page : "Yttjl"
			},
			dataType : "text",
			success : function(_result) {
				alert('操作成功!');
				reload();
			}
		});
	}
}

function reload() {
	$('#divControllBatch').hide();
	$('#divControll').hide();
	$.ajax({
		type : 'POST',
		url : "LoadZpglDataCount",
		data : {
			jtjlkName : $("#jtjlkName").val(),
			jtjlkByyx : $("#jtjlkByyx").val(),
			jtjlkZy : $("#jtjlkZy").val(),
			jtjlkXl : $("#jtjlkXl").val(),
			jtjlkZw : $("#jtjlkZw").val(),
			jtjlkZt : type,
			jtjlkGwlb : $("#jtjlkGwlb").val()
		},
		dataType : "json",
		success : function(count) {
			dataCount = count;
			if (pi * pageSize >= dataCount)
				pi -= 1;
			var opt = getOptionsFromForm(pi);
			$("#Pagination").pagination(dataCount, opt);
		}
	});
}

function getOptionsFromForm() {
	var opt = {
		callback : pageselectCallback
	};
	opt.prev_text = "上一页";
	opt.next_text = "下一页";
	opt.current_page = pi;
	return opt;
}

// 复选框全选 2015/7/14 王征宇
$(function() {
	$("#checkAll").click(function() {
		$('input[name="subBox"]').attr("checked", this.checked);
	});
	var $subBox = $("input[name='subBox']");
	$subBox
			.click(function() {
				$("#checkAll")
						.attr(
								"checked",
								$subBox.length == $("input[name='subBox']:checked").length ? true
										: false);
			});
});

$(document).ready(function() {
	loadData();
});

function loadData() {
	var tmp_select = $('#startpp').attr("checked");
	if (tmp_select == "checked") {
		starPx = 6;
	} else {
		starPx = 0;
	}
	$.ajax({
		type : 'POST',
		url : "LoadZpglDataCount",
		data : {
			jtjlkName : $("#jtjlkName").val(),
			jtjlkByyx : $("#jtjlkByyx").val(),
			jtjlkZy : $("#jtjlkZy").val(),
			jtjlkXl : $("#jtjlkXl").val(),
			jtjlkZw : $("#jtjlkZw").val(),
			jtjlkZt : type,
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
					reload();
				}
			}
		});
	}
}

function showMsjl(jlid, msgid) {
	document.getElementById("frmResult").src = "Msjl?jlid=" + jlid + "&msgid="
			+ msgid;
}

function showCpjl(jtjlkName) {
	document.getElementById("frmResult").src = "ShowCharts?sfzh=" + jtjlkName;
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
			for (var i = 0; i <= msqgls.length - 1; i++) {
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

function loadDataExp() {
	// 集团简历库ID
	var ids = new Array();
	$("input[name=subBox]").each(function() {
		if ($(this).attr("checked")) {
			ids.push($(this).val());
		}
	});
	if (ids.length < 1) {
		alert("请选择要导出的简历！");
		return;
	}
	$.jBox.tip("正在导出，请耐心等待一会！", 'loading');
	$.ajax({
		type : 'POST',
		url : "expResumes",
		data : {
			"ids" : ids.join(",")
		},
		dataType : "json",
		success : function(rst) {
			if (rst.status == "success") {
				alert("导出成功!");
				$.jBox.closeTip();
				window.open("uppics/" + rst.msgBody);
			} else
				alert("导出失败,请联系管理员!");
			$.jBox.closeTip();
		},
		error : function(rst) {
			alert("导出失败,请联系管理员!");
			$.jBox.closeTip();
		}
	});
}

function starts(id, n, txt) {
	var startHtml = "";
	for (var i = 1; i <= n; i++) {
		if (txt == "" || txt == null || txt == undefined)
			startHtml += "<span><a href=\"javascript:insertStartHtml('"
					+ id
					+ "',"
					+ i
					+ ",'');\"><img style=\"height:11px;width:11px;border:0px;\" src=\"resources/star/Styles/img/star-on.png\"  border=\"0\" /></a></span>";
		else
			startHtml += "<span><a href=\"javascript:insertStartHtml('"
					+ id
					+ "',"
					+ i
					+ ",'"
					+ txt
					+ "');\"><img style=\"height:11px;width:11px;border:0px;\" src=\"resources/star/Styles/img/star-on.png\"  border=\"0\" /></a></span>";
	}
	for (var j = n + 1; j <= 5; j++) {
		if (txt == "" || txt == null || txt == undefined)
			startHtml += "<span><a href=\"javascript:insertStartHtml('"
					+ id
					+ "',"
					+ j
					+ ",'');\"><img style=\"height:11px;width:11px;border:0px;\" src=\"resources/star/Styles/img/star-off.png\"  border=\"0\" /></a></span>";
		else
			startHtml += "<span><a href=\"javascript:insertStartHtml('"
					+ id
					+ "',"
					+ j
					+ ",'"
					+ txt
					+ "');\"><img style=\"height:11px;width:11px;border:0px;\" src=\"resources/star/Styles/img/star-off.png\"  border=\"0\" /></a></span>";
	}
	if (txt == "" || txt == null || txt == undefined)
		startHtml += "<span><a href=\"javascript:insertStartHtml('"
				+ id
				+ "',0);\"><img title=\"删除标注\" style=\"height:16px;width:16px;border:0px;\" src=\"images/red-close-btn.gif\"  border=\"0\" /></a></span>";
	else
		startHtml += "<span><a href=\"javascript:insertStartHtml('"
				+ id
				+ "',0,'"
				+ txt
				+ "');\"><img title=\"删除标注\" style=\"height:16px;width:16px;border:0px;\" src=\"images/red-close-btn.gif\"  border=\"0\" /></a></span>";
	if (txt == "" || txt == null || txt == undefined)
		startHtml += "<span><a href=\"javascript:setTxt('"
				+ id
				+ "',"
				+ n
				+ ",'');\"><img title=\"设置备注\" style=\"height:16px;width:16px;border:0px;\" src=\"images/qipao1.png\"  border=\"0\" /></a></span>";
	else
		startHtml += "<span><a href=\"javascript:setTxt('"
				+ id
				+ "',"
				+ n
				+ ",'"
				+ txt
				+ "');\"><img title=\"设置备注\" style=\"height:16px;width:16px;border:0px;\" src=\"images/qipao.png\"  border=\"0\" /></a></span>";
	return startHtml;
}

function insertStartHtml(id, n, txt) {
	$.ajax({
		type : "POST",
		url : "updateStar",
		data : {
			zzjgId : id,
			star : n,
			jtjlkHkd : txt
		},
		dataType : "json",
		success : function(msg) {
			if (msg.status == "success")
				$("#" + id).html(starts(id, n, txt));
		}
	});
}

function setTxt(id, n, txt) {
	var submit = function(v, h, f) {
		if (v) {
			insertStartHtml(id, n, f.txt);
		} else {
			insertStartHtml(id, n, '');
		}
		return true;
	};

	var htmlStr = "<textarea style=\"height:50px; width:290px;\" id='txt' name='txt'  >"
			+ txt + "</textarea>";

	$.jBox.confirm(htmlStr, "添加备注", submit, {
		buttons : {
			'删除' : false,
			'保存' : true
		}
	});
}

function formatLongDate(d) {
	var date = new Date(d);
	return date.toLocaleString();
}