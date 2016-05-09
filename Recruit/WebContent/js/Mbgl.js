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
				url : "LoadMbglData",
				data : {
					pageNum : page_index,
					pageSize : pageSize,
					currentPageSize : currentPageSize,
					keywords : $("#keywords").val(),
					mbType : mbType
				},
				dataType : "json",
				success : function(mbgls) {
					for ( var i = 0; i <= mbgls.length - 1; i++) {
						strHtml += "<tr>";
						strHtml += "<td>" + mbgls[i].mbglName + "</td>";
						strHtml += "<td>" + mbgls[i].mbglType + "</td>";
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"viewMbgl('"
								+ mbgls[i].mbglType + "','" + mbgls[i].mbglId
								+ "');\"";
						strHtml += "	 	class='inputButton' value='查看' />";
						strHtml += "</td>";
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"editMbgl('"
								+ mbgls[i].mbglType + "','" + mbgls[i].mbglId
								+ "');\"";
						strHtml += "	 	class='inputButton' value='编辑' />";
						strHtml += "</td>";
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"MbglDel('"
								+ mbgls[i].mbglId + "');\"";
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
		url : "LoadMbglDataCount",
		data : {
			keywords : $("#keywords").val(),
			mbType : mbType
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

function AddMbgl(type) {
	if (type == "筛选协同")
		window.location.href = "MbglSxxtAdd";
	else if (type == "消息通知")
		window.location.href = "MbglXxtzAdd";
	else
		window.location.href = "MbglZwxxAdd";
}

function editMbgl(type, _id) {
	if (type == "筛选协同")
		window.location.href = "MbglSxxtAdd?id=" + _id;
	else if (type.indexOf("职位") != -1)
		window.location.href = "MbglZwxxAdd?id=" + _id;
	else
		window.location.href = "MbglXxtzAdd?id=" + _id;
}

function viewMbgl(type, _id) {
	if (type == "筛选协同")
		window.location.href = "MbglSxxtView?id=" + _id;
	else if (type.indexOf("职位") != -1)
		window.location.href = "MbglZwxxView?id=" + _id;
	else
		window.location.href = "MbglXxtzView?id=" + _id;
}

function MbglDel(_id) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "MbglDel",
			data : {
				id : _id
			},
			dataType : "json",
			success : function(_result) {
				if (_result > 0) {
					alert('删除成功!');
					window.location.reload();
				}
				else
					alert('您没有权限执行此操作!');
			}
		});
	}
}