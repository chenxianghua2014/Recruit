var pageSize = 10;
var dataCount = 0;
function pageselectCallback(page_index, jq) {
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
		url : "LoadZwglData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			positionName : $("#positionName").val(),
			positionType : $("#positionType").val(),
			positionZsdw : $("#positionZsdw").val(),
			positionZpzy : $("#positionZpzy").val(),
			positionXlyq : $("#positionXlyq").val(),
			positionAddress : $("#positionAddress").val()
		},
		dataType : "json",
		success : function(zwgls) {
			var isExist = "";
			for ( var i = 0; i <= zwgls.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + zwgls[i].positionName + "</td>";
				strHtml += "<td>" + zwgls[i].positionType + "</td>";
				strHtml += "<td>" + zwgls[i].positionZpzy + "</td>";
				strHtml += "<td>" + zwgls[i].positionWorkaddress + "</td>";
				strHtml += "<td>" + zwgls[i].positionXlyq + "</td>";
				strHtml += "<td>" + zwgls[i].positionXqrs + "</td>";
				strHtml += "<td>" + (zwgls[i].positionZpfl == null ? "" : zwgls[i].positionZpfl) + "</td>";
				strHtml += "<td>" + zwgls[i].positionZsdw + "</td>";
				strHtml += "<td>"
						+ new Date(zwgls[i].positionAddtime)
								.toLocaleDateString() + "</td>";
				strHtml += "<td>" + zwgls[i].positionStatus + "</td>";
				if (zwgls[i].positionCanEdit == "yes") {
					strHtml += "<td class='alignCenter'><input name='button'";
					strHtml += "		type='button' onclick=\"editZwgl('"
							+ zwgls[i].positionId + "')\"";
					strHtml += "	 	class='inputButton' value='编辑' />";
					strHtml += "</td>";
					strHtml += "<td class='alignCenter'><input name='button'";
					strHtml += "		type='button' onclick=\"ZwglDel('"
							+ zwgls[i].positionId + "')\"";
					strHtml += "	 	class='inputButton' value='删除' />";
					strHtml += "</td>";
				} else {
					strHtml += "<td class='alignCenter'>不可修改";
					strHtml += "</td>";
					strHtml += "<td class='alignCenter'>不可删除";
					strHtml += "</td>";
				}
				strHtml += "</tr>";
				isExist += zwgls[i].zzjgId + ",";
				if (isExist.indexOf(zwgls[i].zzjgId) == -1)
					$("#zzjgId").append(
							"<option value='" + zwgls[i].zzjgId + "'>"
									+ zwgls[i].positionZsdw + "</option>");
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
		url : "LoadZwglDataCount",
		data : {
			positionName : $("#positionName").val(),
			positionType : $("#positionType").val(),
			positionZsdw : $("#positionZsdw").val(),
			positionZpzy : $("#positionZpzy").val(),
			positionXlyq : $("#positionXlyq").val(),
			positionAddress : $("#positionAddress").val()
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

function AddZwgl() {
	window.location = "ZwglAdd";
}
function editZwgl(_id) {
	window.location = "ZwglAdd?id=" + _id;
}
function ZwglDel(_id) {
	// if (confirm("所有申请该职位的应聘者相关信息将随之删除,您确定要删除该职位吗?")) {
	if (confirm("您确定要删除该职位吗?")) {
		$.ajax({
			type : 'GET',
			url : "ZwglDel",
			data : {
				id : _id
			},
			dataType : "json",
			success : function(_result) {
				if (_result != -1)
					alert('删除成功!');
				else
					alert('该职位已有人员进行投递,无法删除!(若需必须删除,请联系管理员)');
				window.location.reload();
			}
		});
	}
}
