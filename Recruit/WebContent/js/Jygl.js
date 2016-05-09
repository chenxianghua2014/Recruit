var pageSize = 10;
var dataCount = 0;
function pageselectCallback(page_index, jq) {
	var strHtml = "";
	page_index += 1; 
	var currentPageSize = (dataCount < page_index * pageSize ? dataCount
			- (page_index * pageSize - 10)
			: pageSize);
	$("#count").html(dataCount);
	if(dataCount == 0)
		$("#start").html(0);
	else
		$("#start").html(page_index * 10 - 9);
	$("#end").html(currentPageSize + page_index * 10 - 10);
	$.ajax({
		type : 'POST',
		url : "LoadJyglData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			zzjgDwmc : $("#zzjgDwmc").val(),
			zzjgDwdm : $("#zzjgDwdm").val(),
			zzjgDwzh : $("#zzjgDwzh").val(),
			zzjgDwfzr : $("#zzjgDwfzr").val()
		},
		dataType : "json",
		success : function(zzjgList) {
			for ( var i = 0; i <= zzjgList.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + zzjgList[i].zzjgDwmc + "</td>";
				strHtml += "<td>" + zzjgList[i].zzjgDwdm + "</td>";
				strHtml += "<td>" + zzjgList[i].zzjgDwzh + "</td>";
				strHtml += "<td>" + zzjgList[i].zzjgDwfzr + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"editJygl('"
						+ zzjgList[i].zzjgId + "');\"";
				strHtml += "	 	class='inputButton' value='取消禁言' />";
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
		url : "LoadJyglDataCount",
		data : {
			zzjgDwmc : $("#zzjgDwmc").val(),
			zzjgDwdm : $("#zzjgDwdm").val(),
			zzjgDwzh : $("#zzjgDwzh").val(),
			zzjgDwfzr : $("#zzjgDwfzr").val()
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

function editJygl(zzjgId) {
	if (confirm("确定要取消对此用户的禁言么?")) {
		$.ajax({
			type : 'GET',
			url : "hfjy",
			data : {
				zzjgId : zzjgId
			},
			dataType : "json",
			success : window.location.reload()
		});
	}
}
