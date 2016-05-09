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
		url : "qtKcglData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			kcglName : $("#kcglName").val()
		},
		dataType : "json",
		success : function(listKcgl) {
			for ( var i = 0; i <= listKcgl.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + listKcgl[i].kcglName + "</td>";
				strHtml += "<td>" + listKcgl[i].kcglDz + "</td>";
				strHtml += "<td>" + listKcgl[i].kcglKsrq + "</td>";
				strHtml += "<td>" + listKcgl[i].kcglKssjStart + "--"
						+ listKcgl[i].kcglKssjEnd + "</td>";
				strHtml += "<td>" + listKcgl[i].kcglKcrl + "</td>";
				strHtml += "<td>" + listKcgl[i].kcglYbms + "</td>";
				strHtml += "<td>" + listKcgl[i].kcglSyrl + "</td>";
//				strHtml += "<td>" + listKcgl[i].kcglKczt + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"addBmgl('"
						+ listKcgl[i].kcglId + "');\"";
				strHtml += "	 	class='inputButton' value='报名' />";
				strHtml += "</td>";
				strHtml += "</tr>";
			}
			$("#tbList").html(strHtml);
		}
	});
	return false;
}
$(document).ready(function() {
	loadData();
});
function addBmgl(kcId) {
	window.location.href = "Registration?kcId=" + kcId + "&id="
			+ getQueryString("id");
}
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = location.search.substr(1).match(reg);
	if (r != null)
		return unescape(decodeURI(r[2]));
	return null;
}
function loadData() {
	$.ajax({
		type : 'POST',
		url : "qtKcglDataCount",
		data : {
			kcglName : $("#kcglName").val()
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
function getOptionsFromForm() {
	var opt = {
		callback : pageselectCallback
	};
	opt.prev_text = "上一页";
	opt.next_text = "下一页";
	return opt;
}
