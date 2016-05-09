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
					jtjlkZt : "收藏",
					jtjlkGwlb : $("#jtjlkGwlb").val(),
					star : starPx
				},
				dataType : "json",
				success : function(jtjlks) {
					for (var i = 0; i <= jtjlks.length - 1; i++) {
						strHtml += "<tr>";
						strHtml += "<td><input name='subBox' type='checkbox' value='"
								+ jtjlks[i].jtjlkId
								+ "' cpcj='"
								+ jtjlks[i].jtjlkCpcj
								+ "' zwid='"
								+ jtjlks[i].jtjlkPositionId
								+ "' uname='"
								+ jtjlks[i].jtjlkName + "'/></td>";
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
								+ jtjlks[i].jtjlkName + "','"
								+ jtjlks[i].jtjlkPositionId + "','"
								+ jtjlks[i].jtjlkZw + "')\"";
						strHtml += "	 	class='inputButton' value='操作' />";
						strHtml += "</td>";
						strHtml += "</tr>";
					}
					$("#tbList").html(strHtml);
				}
			});

	return false;
}

function Apzw(type) {
	var id = new Array();
	if (type == "batch") {
		if ($("input:checkbox[name='subBox']:checked").length < 1) {
			alert("请至少选择一项!");
			return;
		} else {
			$("input[name=subBox]").each(function() {
				if ($(this).attr("checked")) {
					id.push($(this).val());
				}
			});
		}
	}
	var strHtml = "<form id='frmApzw' autocomplete='off' data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true}\">"
			+ "<input type='hidden' id='jtjlkId' name='jtjlkId' /><div class='editBlock'><table>"
			+ "<tr><th width='60'>安排职位:</th>"
			+ "<td><select id='selZw' data-rule='required;' name='selZw' style='width:140px;'></select></td></tr>"
			+ "<tr><th></th><td><input name='button' type='button' onclick=\"doSubmitApzw('"
			+ type
			+ "');\" class='inputButton' value='确定' /></td></tr></table></div></form>";
	$.jBox.open(strHtml, '安排职位', 350, 150, {
		buttons : {
			'关闭' : true
		}
	});
	loadZw();
	$("#jtjlkId").val(id.join(","));
}

function doSubmitApzw(type) {
	var options = {};
	if (type != 'batch') {
		$('#jtjlkId').val($('#Id').val());
		options = {
			type : 'post',
			url : "Apzw",
			success : showResponseApzw,
			error : showResponseApzw,
			clearForm : false
		};
	} else {
		options = {
			type : 'post',
			url : "Apzw",
			success : showResponseApzwBatch,
			error : showResponseApzwBatch,
			clearForm : false
		};
	}
	// ajax一步上传表单
	$("#frmApzw").ajaxSubmit(options);
}

function showResponseApzw(responseText, statusText) {
	if (responseText.indexOf("success") != -1) {
		alert("安排职位成功！");
		$.jBox.close(true);
		reload(false);
		$("#zw").html($('#selZw').find('option:selected').text());
		$("#tdApzw").hide();
		$("#zw").show();
		$("#jtjlkPositionId").val($("#selZw").val());
	}
}

function showResponseApzwBatch(responseText, statusText) {	
	if (responseText.indexOf("success") != -1) {
		alert("安排职位成功！");
		$.jBox.close(true);
		reload(false);
		$("#zwBatch").html($('#selZw').find('option:selected').text());
		$("#tdApzwBatch").hide();
		$("#zwBatch").show();
		$("#jtjlkPositionId").val($("#selZw").val());
	}
}

function doSubmit() {
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
		$("#zwBatch").hide();
		$("#tdApzwBatch").show();
		$("#divControllBatch").hide();
		$.jBox.close(true);
		reload(true);
	}
}

function reload(flag) {
	if (flag) {
		$('#divControllBatch').hide();
		$('#divControll').hide();
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
			jtjlkZt : "收藏",
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

function loadZw() {
	$.ajax({
		type : "POST",
		url : "loadZwmc",
		data : {},
		dataType : "json",
		async : false,
		success : function(_result) {
			$("#selZw").empty();
			$("#selZw").append("<option value=''></option>");
			for (var i = 0; i < _result.length; i++) {
				$("#selZw").append(
						"<option value='" + _result[i].positionId + "'>"
								+ _result[i].positionName + "</option>");
			}
		}
	});
}

function _Do(jtjlkId, jtjlkUserid, jtjlkCpcj, jtjlkMszt, jtjlkCpjg, jtjlkName,
		jtjlkPositionId, jtjlkZw) {
	$("#divControllBatch").hide();
	$('#divControll').show();
	if (jtjlkZw != "") {
		$('#zw').html(jtjlkZw);
		$("#zw").show();
		$("#tdApzw").hide();
	} else {
		$('#zw').hide();
		$("#tdApzw").show();
	}

	$('#Id').val(jtjlkId);
	$('#UserId').val(jtjlkUserid);
	$('#jtjlkCpcj').val(jtjlkCpcj);
	$('#jtjlkMszt').val(jtjlkMszt);
	$('#jtjlkCpjg').val(jtjlkCpjg);
	$('#name').html(jtjlkName);
	$('#jtjlkPositionId').val(jtjlkPositionId);
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
			jtjlkZt : "收藏",
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
function Apms(type) {
	if (!check(type))
		return;
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
		$("#zwBatch").hide();
		$("#tdApzwBatch").show();
		$("#divControllBatch").hide();
		$.jBox.close(true);
		reload(true);
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

function check(type) {
	var id = new Array();
	var zwid = new Array();
	var uname = new Array();
	if (type != "batch") {
		if ($("#jtjlkPositionId").val() == "") {
			alert("请先对收藏的简历安排职位!");
			return false;
		}
		$("#jtjlkId").val($("#Id").val());
	} else {
		if ($("input:checkbox[name='subBox']:checked").length < 1) {
			alert("请至少选择一项!");
			return false;
		} else {
			$("input[name=subBox]").each(function() {
				if ($(this).attr("checked")) {
					id.push($(this).val());
					zwid.push($(this).attr("zwid"));
					uname.push($(this).attr("uname"));
				}
			});
			var msg = "";
			for (var i = 0; i < zwid.length; i++) {
				if (zwid[i].length == 0)
					msg += uname[i] + " ";
			}
			if (msg.length > 0) {
				alert("请先对 " + msg + "安排职位!");
				return false;
			} else {
				$("#jtjlkId").val(id.join(","));
			}
		}
	}
	return true;
}

function Tzcp() {
	if (!check(''))
		return;
	var strHtml = "<form id='tzcpFrm' autocomplete='off'"
			+ "data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true} \">"
			+ "<input type='hidden' id='jtjlkId' name='jtjlkId' />"
			+ "<input type='hidden' id='xxtzType' name='xxtzType' value='测评通知' />"
			+ "<div class='editBlock'>"
			+ "<table>"
			+ "	<tr>"
			+ "		<th>选择模版:</th>"
			+ "		<td><select id='selMb' name='selMb' style='width:120px;'><option"
			+ "					value=''></option>"
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

function TzcpBatch() {
	if (!check("batch"))
		return;
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
			+ "		<td><select id='selMb' name='selMb' style='width:120px;'><option"
			+ "					value=''></option>"
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