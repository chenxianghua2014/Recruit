var pageSize = 10;
var dataCount = 0;
function pageselectCallback(page_index, jq) {
	page_index += 1;
	var strHtml = "";
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
		url : "LoadHydjData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			hydjName: $("#hydjName").val()
		},
		dataType : "json",
		success : function(listHydj) { 
			for ( var i = 0; i <= listHydj.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + listHydj[i].hydjName + "</td>";
				strHtml += "<td>" + listHydj[i].hydjFy + "</td>";
				strHtml += "<td>" + listHydj[i].hydjCpcs + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"getHydjById('"
						+ listHydj[i].hydjId + "');\"";
				strHtml += "	 	class='inputButton' value='编辑' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delHydj('"
						+ listHydj[i].hydjId + "');\"";
				strHtml += "	 	class='inputButton' value='删除' />";
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
function loadData() {
	$.ajax({
		type : 'POST',
		url : "LoadHydjDataCount",
		data : {
			hydjName: $("#hydjName").val()
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
function getHydjById(hydjId) {
	window.location = "hydjAdd?hydjId=" + hydjId;
}
function addHydj() {
	window.location = "hydjAdd";
}
function delHydj(hydjId) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "delHydj",
			data : {
				hydjId : hydjId
			},
			dataType : "json",
			success : window.location.reload()
		});
	}
}
