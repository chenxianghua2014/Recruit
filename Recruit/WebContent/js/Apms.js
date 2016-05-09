var pageSize = 10;
var dataCount = 0;
function pageselectCallback(page_index) {
	page_index += 1;
	var strHtml = "";
	$
			.ajax({
				type : 'POST',
				url : "LoadZpglData",
				data : {
					pageNum : page_index,
					pageSize : pageSize,
					currentPageSize : (dataCount < page_index * pageSize ? dataCount
							- (page_index * pageSize - 10)
							: pageSize),
					jtjlkName : $("#jtjlkName").val(),
					jtjlkByyx : $("#jtjlkByyx").val(),
					jtjlkZy : $("#jtjlkZy").val(),
					jtjlkXl : $("#jtjlkXl").val(),
					jtjlkZw : $("#jtjlkZw").val(),
					jtjlkZt : "通过,一面结束,二面结束,已安排面试",
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
						strHtml += "<td class='alignCenter'>"
								+ jtjlks[i].jtjlkCpcj + "</td>";
						strHtml += "<td class='alignCenter'>"
								+ jtjlks[i].jtjlkMszt + "</td>";
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"$('#divControll').show();$('#jtjlkId').val('"
								+ jtjlks[i].jtjlkId + "')\"";
						strHtml += "	 	class='inputButton' value='通知' />";
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
	bindSelect();
	bindMsg();
	loadMsq();
});

function bindSelect() {
	$.ajax({
		type : 'POST',
		url : "LoadBm",
		data : {},
		dataType : "json",
		success : function(bms) {
			$("#bm").empty();
			$("#bm").append("<option value=''></option>");
			if (bms.length > 0) {
				for ( var i = 0; i < bms.length; i++) {
					$("#bm").append(
							"<option value='" + bms[i].txlDepartment + "'>"
									+ bms[i].txlDepartment + "</option>");
				}
			}
		}
	});
	$.ajax({
		type : 'POST',
		url : "LoadZw",
		data : {},
		dataType : "json",
		success : function(zws) {
			$("#zw").empty();
			$("#zw").append("<option value=''></option>");
			if (zws.length > 0) {
				for ( var i = 0; i < zws.length; i++) {
					$("#zw").append(
							"<option value='" + zws[i].txlPosition + "'>"
									+ zws[i].txlPosition + "</option>");
				}
			}

		}
	});
}

function bindMsg() {
	$.ajax({
		type : 'POST',
		url : "LoadMsg",
		data : {
			zw : $("#zw").val(),
			bm : $("#bm").val()
		},
		dataType : "json",
		success : function(msgs) {
			$("#msqglDetailedMsg").empty();
			if (msgs.length > 0) {
				for ( var i = 0; i < msgs.length; i++) {
					$("#msqglDetailedMsg").append(
							"<option value='" + msgs[i].txlId + "'>"
									+ msgs[i].txlName + "</option>");
				}
			}

		}
	});
}

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
			jtjlkZt : "通过,一面结束,二面结束,已安排面试",
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
				for ( var i = 0; i < msqs.length; i++) {
					$("#msqId").append(
							"<option value='" + msqs[i].msqId + "'>"
									+ msqs[i].msqName + "</option>");
				}
			}
		}
	});
}