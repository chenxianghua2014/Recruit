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
					jtjlkZt : "通过,未测评,已测评,已安排面试",
					jtjlkGwlb : $("#jtjlkGwlb").val(),
					star : starPx
				},
				dataType : "json",
				success : function(jtjlks) {
					for (var i = 0; i <= jtjlks.length - 1; i++) {
						strHtml += "<tr>";
						strHtml += "<td><input name='subBox' type='checkbox' value='"
								+ jtjlks[i].jtjlkId
								+ "' uname='"
								+ jtjlks[i].jtjlkName
								+ "' cpcj='"
								+ jtjlks[i].jtjlkCpcj
								+ "' cpjg='"
								+ jtjlks[i].jtjlkCpjg
								+ "' zw='"
								+ jtjlks[i].jtjlkZw
								+ "' zwid='"
								+ jtjlks[i].jtjlkPositionId
								+ "' mszt='"
								+ jtjlks[i].jtjlkMszt + "'/></td>";
						strHtml += "<td>" + jtjlks[i].jtjlkName + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkCsrq + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkSfzh + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkSex + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkByxx + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkZy + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkXl + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkZw + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkHkd + "</td>";
						strHtml += "<td class='alignCenter'>"
								+ jtjlks[i].jtjlkCpcj + "</td>";
						strHtml += "<td class='alignCenter'>"
								+ jtjlks[i].jtjlkMszt + "</td>";
						strHtml += "<td class='alignCenter'><div id=\""
								+ jtjlks[i].jtjlkId
								+ "\">"
								+ starts(jtjlks[i].jtjlkId,
										jtjlks[i].jtjlkstar, jtjlks[i].txt);
						+"</div></td>";
						strHtml += "<td>"
								+ formatLongDate(jtjlks[i].jtjlkAddtime)
								+ "</td>";
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"window.open('YljlHr?resumeId="
								+ jtjlks[i].jtjlkUserid
								+ "&jtjlkId="
								+ jtjlks[i].jtjlkId
								+ "');\" class='inputButton' value='查看' /></td>";
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"_Do('"
								+ jtjlks[i].jtjlkId + "','"
								+ jtjlks[i].jtjlkUserid + "','"
								+ jtjlks[i].jtjlkCpcj + "','"
								+ jtjlks[i].jtjlkMszt + "','"
								+ jtjlks[i].jtjlkCpjg + "','"
								+ jtjlks[i].jtjlkZw + "','"
								+ jtjlks[i].jtjlkPositionId + "','"
								+ jtjlks[i].jtjlkName + "')\"";
						strHtml += "	 	class='inputButton' value='操作' />";
						strHtml += "</td>";
						strHtml += "</tr>";
					}
					$("#tbList").html(strHtml);
				}
			});
	return false;
}
function _Do(jtjlkId, jtjlkUserid, jtjlkCpcj, jtjlkMszt, jtjlkCpjg, jtjlkZw,
		jtjlkPositionId, jtjlkName) {
	$("#divControllBatch").hide();
	$('#divControll').show();
	$('#Id').val(jtjlkId);
	$('#UserId').val(jtjlkUserid);
	$('#jtjlkCpcj').val(jtjlkCpcj);
	$('#jtjlkMszt').val(jtjlkMszt);
	$('#jtjlkCpjg').val(jtjlkCpjg);
	$('#jtjlkCzw').val(jtjlkZw);
	$('#jtjlkCzwId').val(jtjlkPositionId);
	$('#name').html(jtjlkName);
	document.body.scrollTop = document.body.scrollHeight;
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
			jtjlkZt : "通过,未测评,已测评,已安排面试",
			jtjlkGwlb : $("#jtjlkGwlb").val(),
			star : starPx
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

function excute() {
	if (confirm("确定对该简历执行'淘汰'操作吗?")) {
		$.ajax({
			type : 'POST',
			url : "UpdateZpglStatus",
			data : {
				jtjlkId : $("#Id").val(),
				jtjlkJlzt : '淘汰',
				page : "Shdjl"
			},
			dataType : "text",
			success : function(_result) {
				alert('操作成功!');
				reload();
			}
		});
	}
}

function LyBatch(uname, cpjg, mszt, zw, zwid) {
	var msg1 = "", msg2 = "";
	for (var i = 0; i < cpjg.length; i++) {
		if (cpjg[i].indexOf("通过") == -1) {
			msg1 += uname[i] + " 测评结果为 '" + cpjg[i] + "' \r";
			continue;
		}
		if (mszt[i].indexOf("通过") == -1)
			msg2 += uname[i] + " 面试状态为 '" + mszt[i] + "' \r";
	}
	if (msg1.length > 0) {
		alert(msg1 + "不能执行录用操作!");
		return;
	}
	if (msg2.length > 0) {
		alert(msg2 + "不能执行录用操作!");
		return;
	}

	var strHtml = "<form id='frmLy' autocomplete='off'"
			+ " data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true}\">"
			+ "<input type='hidden' id='jtjlkId' name='jtjlkId' />"
			+ "<input type='hidden' id='jtjlkZzlygw' name='jtjlkZzlygw' />"
			+ "<input type='hidden' id='jtjlkZzlygwId' name='jtjlkZzlygwId' />"
			+ "<input type='hidden' id='xxtzType' name='xxtzType' value='录用通知' /><div class='editBlock'><table>"
			+ "<tr><th width='100px'>当前录用职位:</th><td><span id='lygw'></span>&nbsp;&nbsp;"
			+ "</td></tr>"
			// + " <tr>"
			// + " <th>选择模版:</th>"
			// + " <td><select id='selMb' name='selMb'
			// style='width:120px;'><option"
			// + " value=''></option>"
			// + " </select>&nbsp;<input type='button' class='inputButton'
			// value='加载'"
			// + " onclick='loadMbContent()'>"
			// + " </td>"
			// + " </tr>"
			// + " <tr>"
			// + " <th><span class='warning'>*</span>通知内容:</th>"
			// + " <td><textarea id='content' name='content'"
			// + " style='width:200px;height:100px;'
			// data-rule='通知内容:required;'></textarea></select>"
			// + " </td>"
			// + " </tr>"
			+ "<tr><th></th><td><input name='button' type='button' onclick=\"doSubmit('batch');\" class='inputButton' value='录用' /></td></tr></table></div></form>";
	$.jBox.open(strHtml, '录用', 350, 150, {
		buttons : {
			'关闭' : true
		}
	});
	bindMb("录用通知");
	$("#jtjlkId").val($("#Ids").val());
	$("#lygw").html("当前投递职位");
	$("#jtjlkZzlygw").val(zw);
	$("#jtjlkZzlygwId").val(zwid);
}

function Ly() {
	if ($("#jtjlkCpjg").val().indexOf("通过") == -1) {
		alert("该简历测评结果为'" + $("#jtjlkCpjg").val() + "',不能执行录用操作!");
		return;
	}
	if ($("#jtjlkMszt").val().indexOf("通过") == -1) {
		alert("该简历面试状态为'" + $("#jtjlkMszt").val() + "',不能执行录用操作!");
		return;
	}
	if (confirm("确定对该简历执行'录用'操作吗?")) {
		var strHtml = "<form id='frmLy' autocomplete='off'"
				+ " data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true}\">"
				+ "<input type='hidden' id='jtjlkId' name='jtjlkId' />"
				+ "<input type='hidden' id='jtjlkZzlygw' name='jtjlkZzlygw' />"
				+ "<input type='hidden' id='jtjlkZzlygwId' name='jtjlkZzlygwId' />"
				+ "<input type='hidden' id='xxtzType' name='xxtzType' value='录用通知' /><div class='editBlock'><table>"
				+ "<tr><th width='100px'>当前录用职位:</th><td><span id='lygw'></span>&nbsp;&nbsp;"
				+ "<input name='button' type='button' onclick='ChangeZw()' class='inputButton' value='更改' /></td></tr>"
				// + " <tr>"
				// + " <th>选择模版:</th>"
				// + " <td><select id='selMb' name='selMb'
				// style='width:120px;'><option"
				// + " value=''></option>"
				// + " </select>&nbsp;<input type='button' class='inputButton'
				// value='加载'"
				// + " onclick='loadMbContent()'>"
				// + " </td>"
				// + " </tr>"
				// + " <tr>"
				// + " <th><span class='warning'>*</span>通知内容:</th>"
				// + " <td><textarea id='content' name='content'"
				// + " style='width:200px;height:100px;'
				// data-rule='通知内容:required;'></textarea></select>"
				// + " </td>"
				// + " </tr>"
				+ "<tr><th></th><td><input name='button' type='button' onclick=\"doSubmit('');\" class='inputButton' value='录用' /></td></tr></table></div></form>";
		$.jBox.open(strHtml, '录用', 350, 150, {
			buttons : {
				'关闭' : true
			}
		});
		bindMb("录用通知");
		$("#jtjlkId").val($("#Ids").val());
		$("#lygw").html($("#jtjlkCzw").val());
		$("#jtjlkZzlygw").val($("#jtjlkCzw").val());
		$("#jtjlkZzlygwId").val($("#jtjlkCzwId").val());
	}
}
// 批量执行
function excuteBatch(status) {
	var text = new Array();
	var cpjg = new Array();
	var mszt = new Array();
	var uname = new Array();
	var zw = new Array();
	var zwid = new Array();
	if ($("input:checkbox[name='subBox']:checked").length < 1) {
		alert("请至少选择一项");
		return;
	} else {
		$("input[name=subBox]").each(function() {
			if ($(this).attr("checked")) {
				text.push($(this).val());
				cpjg.push($(this).attr("cpjg"));
				mszt.push($(this).attr("mszt"));
				uname.push($(this).attr("uname"));
				zw.push($(this).attr("zw"));
				zwid.push($(this).attr("zwid"));
			}
		});
		$("#Ids").val(text.join(","));
	}
	if (confirm("确定对该简历执行'" + status + "'操作吗?")) {
		if (status == "录用") {
			$("#jtjlkCzw").val();
			$("#jtjlkCzwId").val();
			LyBatch(uname, cpjg, mszt, zw, zwid);
		} else {
			$.ajax({
				type : 'POST',
				url : "UpdateZpglStatus",
				data : {
					jtjlkId : $("#Ids").val(),
					jtjlkJlzt : '淘汰',
					page : "Shdjl"
				},
				dataType : "text",
				success : function(_result) {
					alert('操作成功!');
					reload();
				}
			});
		}
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
			jtjlkZt : "通过,未测评,已测评,已安排面试",
			jtjlkGwlb : $("#jtjlkGwlb").val(),
			star : starPx
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

function loadMsq() {
	$.ajax({
		type : 'POST',
		url : "GetAllMsq",
		data : {},
		dataType : "json",
		success : function(msqs) {
			$("#msqId").empty();
			if (msqs.length > 0) {
				for (var i = 0; i < msqs.length; i++) {
					$("#msqId").append(
							"<option value='" + msqs[i].msqId + "'>"
									+ msqs[i].msqName + "</option>");
				}
				GetMsqInfo(msqs[0].msqId);
			}
		}
	});
}
function GetMsqInfo(_id) {
	$.ajax({
		type : 'POST',
		url : "GetMsqInfo",
		data : {
			id : _id
		},
		dataType : "json",
		success : function(msq) {
			$("#mslb").html(msq.msqMslb);
			$("#mssj").html(msq.msqMssj);
			$("#msdd").html(msq.msqMsdd);
			$("#msg").html(msq.msqMsgNames);
		}
	});
}

function doSubmit(type) {
	if (type == "batch") {
		$('#jtjlkId').val($('#Ids').val());
	} else
		$('#jtjlkId').val($('#Id').val());
	var options = {
		type : 'post',
		url : "XxtzDoSava",
		success : showResponse,
		error : showResponse,
		clearForm : false
	};
	// ajax一步上传表单
	$("#frmLy").ajaxSubmit(options);
}

function showResponse(responseText, statusText) {
	$.jBox.closeTip();
	if (responseText.indexOf("success") != -1) {
		alert("操作成功！");
		$.jBox.close(true);
		reload();
	}
}

function ChangeZw() {
	var strHtml = ""
			+ "<input type='hidden' id='jtjlkId' name='jtjlkId' /><div class='editBlock'><table>"
			+ "<tr><th>录用其他职位:</th><td><select id='selLygw' name='selLygw' style='width:200px;'></select></td></tr>"
			+ "<tr><th></th><td><input name='button' onclick='DoChange()' type='button' class='inputButton' value='确定' />&nbsp;&nbsp;<input name='button' onclick='$.jBox.close();' type='button' class='inputButton' value='返回' /></td></tr></table></div>";
	$.jBox.open(strHtml, '更改录用职位', 350, 165, {
		buttons : {
			'关闭' : true
		}
	});
	loadZwmc();
}

function DoChange() {
	$("#lygw").html($("#selLygw option:selected").text());
	$("#jtjlkZzlygw").val($("#selLygw option:selected").text());
	$("#jtjlkZzlygwId").val($("#selLygw").val());
	$.jBox.close();
}

function loadZwmc() {
	$.ajax({
		type : "POST",
		url : "loadZwmc",
		data : {},
		dataType : "json",
		async : false,
		success : function(_result) {
			$("#selLygw").empty();
			$("#selLygw").append("<option value=''></option>");
			for (var i = 0; i < _result.length; i++) {
				if (_result[i].positionName == $("#lygw").html())
					$("#selLygw").append(
							"<option value='" + _result[i].positionId
									+ "' selected='selected'>"
									+ _result[i].positionName + "</option>");
				else
					$("#selLygw").append(
							"<option value='" + _result[i].positionId + "'>"
									+ _result[i].positionName + "</option>");
			}
		}
	});
}

function Apms(type) {
	var strHtml = "<form id='frmTs' method='post' autocomplete='off' data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true}\">"
			+ "<input type='hidden' id='jtjlkId' name='jtjlkId' /><div class='editBlock'><table>"
			+ "<tr><th width='60'>面试圈:</th><td><select id='msqId' data-rule='required;' name='msqId' onchange='GetMsqInfo(this.value)' style='width:140px;'></select></td></tr>"
			+ "<tr><th>面试类别:</th><td id='mslb'></td></tr>"
			+ "<tr><th>面试时间:</th><td id='mssj'></td></tr>"
			+ "<tr><th>面试地点:</th><td id='msdd'></td></tr>"
			+ "<tr><th>面试官:</th><td id='msg'></td></tr>"
			+ "<tr><th></th><td><input name='button' type='button' onclick=\"doSubmitTs('"
			+ type
			+ "')\" class='inputButton' value='推送到面试圈' /></td></tr></table></div></form>";
	$.jBox.open(strHtml, '安排面试', 350, 260, {
		buttons : {
			'关闭' : true
		}
	});
	loadMsq();
}

function doSubmitTs(type) {
	if (type == "batch") {
		var id = new Array();
		if ($("input:checkbox[name='subBox']:checked").length < 1) {
			alert("请至少选择一项!");
			return;
		} else {
			$("input[name=subBox]").each(function() {
				if ($(this).attr("checked")) {
					id.push($(this).val());
				}
			});
			$("#jtjlkId").val(id.join(","));
		}
	} else
		$('#jtjlkId').val($('#Id').val());
	$.jBox.tip("正在推送到面试圈,请稍等……", 'loading');
	var options = {
		type : 'post',
		url : "Ts",
		success : showResponseTs,
		error : showResponseTs,
		clearForm : false
	};
	// ajax一步上传表单
	$("#frmTs").ajaxSubmit(options);
}

function showResponseTs(responseText, statusText) {
	$.jBox.closeTip();
	if (responseText.message.indexOf("success") != -1) {
		alert("操作成功!");
		$.jBox.close(true);
		reload();
	} else {
		alert(responseText.message);
	}
}

function checkResult() {
	$('#tzcpFrm').isValid(function(v) {
		if (v) {
			if ($("#jtjlkCpcj").val() == "未完成") {
				alert("该简历已被通知过测评!");
				return;
			}
			if ($("#jtjlkCpcj").val() != "未通知") {
				alert("该简历已参加过测评!");
				return;
			}
			$.jBox.tip("正在通知测评,请稍等……", 'loading');
			var options = {
				type : 'post',
				url : "XxtzDoSava",
				success : showResponse,
				error : showResponse,
				clearForm : false
			};
			// ajax一步上传表单
			$("#tzcpFrm").ajaxSubmit(options);
			// $("#CpResult").html(
			// "该人员已经在其他企业完成'" + type + "'测评,测评成绩已更新到本单位!");
		}
	});
}

function Tzcp() {
	var strHtml = "<form id='tzcpFrm' autocomplete='off'"
			+ "data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true} \">"
			+ "<input type='hidden' id='jtjlkId' name='jtjlkId' />"
			+ "<input type='hidden' id='xxtzType' name='xxtzType' value='测评通知' />"
			+ "<div class='editBlock'>"
			+ "<table>"
			+ "	<tr>"
			+ "		<th>选择模版:</th>"
			+ "		<td><select id='selMb' name='selMb' style='width:120px;'><option value=''></option>"
			+ "		</select>&nbsp;<input type='button' class='inputButton' value='加载'"
			+ "			onclick='loadMbContent()'>"
			+ "		</td>"
			+ "	</tr>"
			+ "	<tr>"
			+ "		<th><span class='warning'>*</span>测评类型:</th>"
			+ "		<td><input type='radio' id='glg' name='testType' value='管理岗' data-rule='测评类型:checked;'/>管理岗&nbsp;&nbsp;"
			+ "		<input type='radio' id='jsg' name='testType' value='技术岗' data-rule='测评类型:checked;'/>技术岗&nbsp;&nbsp;"
			+ "		</td>"
			+ "	</tr>"
			+ "	<tr>"
			+ "		<th><span class='warning'>*</span>通知内容:</th>"
			+ "		<td><textarea id='content' name='content'"
			+ "				style='width:200px;height:100px;' data-rule='通知内容:required;'></textarea></select>"
			+ "		</td>"
			+ "	</tr>"
			+ "	<tr><th></th>"
			+ "	<td><input type='button' onclick='checkResult()' class='inputButton'"
			+ "		value='发送通知' /></td>"
			+ "	</tr>"
			+ "<tr><th></th><td id='CpResult' style='font-color:red;'></td></tr>"
			+ "</table>" + "</div>" + "</form>";
	$.jBox.open(strHtml, '通知测评', 450, 300, {
		buttons : {
			'关闭' : true
		}
	});
	$("#jtjlkId").val($("#Id").val());
	bindMb("测评通知");
}

// 批量测评
function TzcpBatch() {
	var id = new Array();
	var uname = new Array();
	var cpcj = new Array();
	if ($("input:checkbox[name='subBox']:checked").length < 1) {
		alert("请至少选择一项!");
		return;
	} else {
		$("input[name=subBox]").each(function() {
			if ($(this).attr("checked")) {
				id.push($(this).val());
				uname.push($(this).attr("uname"));
				cpcj.push($(this).attr("cpcj"));
			}
		});
	}
	var strHtml = "<form id='tzcpFrm' autocomplete='off'"
			+ "data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true} \">"
			+ "<input type='hidden' id='jtjlkId' name='jtjlkId' />"
			+ "<input type='hidden' id='uname' name='uname' />"
			+ "<input type='hidden' id='cpcj' name='cpcj' />"
			+ "<input type='hidden' id='xxtzType' name='xxtzType' value='测评通知' />"
			+ "<div class='editBlock'>"
			+ "<table>"
			+ "	<tr>"
			+ "		<th>选择模版:</th>"
			+ "		<td><select id='selMb' name='selMb' style='width:120px;'><option value=''></option>"
			+ "		</select>&nbsp;<input type='button' class='inputButton' value='加载'"
			+ "			onclick='loadMbContent()'>"
			+ "		</td>"
			+ "	</tr>"
			+ "	<tr>"
			+ "		<th><span class='warning'>*</span>测评类型:</th>"
			+ "		<td><input type='radio' id='glg' name='testType' value='管理岗' data-rule='测评类型:checked;'/>管理岗&nbsp;&nbsp;"
			+ "		<input type='radio' id='jsg' name='testType' value='技术岗' data-rule='测评类型:checked;'/>技术岗&nbsp;&nbsp;"
			+ "		</td>"
			+ "	</tr>"
			+ "	<tr>"
			+ "		<th><span class='warning'>*</span>通知内容:</th>"
			+ "		<td><textarea id='content' name='content'"
			+ "				style='width:200px;height:100px;' data-rule='通知内容:required;'></textarea></select>"
			+ "		</td>"
			+ "	</tr>"
			+ "	<tr><th></th>"
			+ "	<td><input type='button' onclick='checkResultBatch()' class='inputButton'"
			+ "		value='发送通知' /></td>"
			+ "	</tr>"
			+ "<tr><th></th><td id='CpResult' style='font-color:red;'></td></tr>"
			+ "</table>" + "</div>" + "</form>";
	$.jBox.open(strHtml, '通知测评', 450, 300, {
		buttons : {
			'关闭' : true
		}
	});
	bindMb("测评通知");
	$("#jtjlkId").val(id.join(","));
	$("#uname").val(uname.join(","));
	$("#cpcj").val(cpcj.join(","));
}

function checkResultBatch() {
	$('#tzcpFrm').isValid(function(v) {
		if (v) {
			var cpcjs = $("#cpcj").val().split(",");
			var uname = $("#uname").val().split(",");
			var msg1 = "", msg2 = "";
			for (var i = 0; i < cpcjs.length; i++) {
				if (cpcjs[i] == "未完成") {
					msg1 += uname[i] + " ";
					continue;
				}
				if (cpcjs[i] != "未通知") {
					msg2 += uname[i] + " ";
				}
			}
			if (msg1.length > 0) {
				alert(msg1 + "已被通知过测评!");
				return;
			}
			if (msg2.length > 0) {
				alert(msg2 + "已参加过测评!");
				return;
			}
			$.jBox.tip("正在通知测评,请稍等……", 'loading');
			var options = {
				type : 'post',
				url : "XxtzDoSava",
				success : showResponse,
				error : showResponse,
				clearForm : false
			};
			// ajax一步上传表单
			$("#tzcpFrm").ajaxSubmit(options);
			// $("#CpResult").html(
			// "该人员已经在其他企业完成'" + type + "'测评,测评成绩已更新到本单位!");
		}
	});
}

function loadMbContent() {
	if ($("#selMb").val() == "")
		return;
	$.ajax({
		type : "POST",
		url : "loadMbContent",
		data : {
			id : $("#selMb").val()
		},
		dataType : "json",
		async : false,
		success : function(_result) {
			$("#content").val(_result.mbglContent);
		}
	});
}

function bindMb(_type) {
	$("#selMb").empty();
	$.ajax({
		type : "POST",
		url : "LoadMbgl",
		data : {
			type : _type
		},
		dataType : "json",
		async : false,
		success : function(_result) {
			$("#selMb").empty();
			$("#selMb").append("<option value=''></option>");
			for (var i = 0; i < _result.length; i++) {
				$("#selMb").append(
						"<option value='" + _result[i].mbglId + "'>"
								+ _result[i].mbglName + "</option>");
			}
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

function starPxSearch(n) {
	var tmp_select = $('#startpp').attr("checked");
	if (tmp_select == "checked") {
		starPx = n;
	} else {
		starPx = 0;
	}
	loadData();
}

function formatLongDate(d) {
	var date = new Date(d);
	return date.toLocaleString();
}
