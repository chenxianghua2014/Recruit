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
		url : "queryLtbqByPage",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			ltbqName: $("#ltbqName").val(),
			ltbqType :$("#ltbqType").val()
		},
		dataType : "json",
		success : function(listLtbq) { 
			for ( var i = 0; i <= listLtbq.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + listLtbq[i].ltbqName + "</td>";
				strHtml += "<td>" + listLtbq[i].ltbqType + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"getLtbqById('"
						+ listLtbq[i].ltbqId + "');\"";
				strHtml += "	 	class='inputButton' value='编辑' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delLtbq('"
						+ listLtbq[i].ltbqId + "');\"";
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
		url : "queryLtbqByPageCount",
		data : {
			ltbqName: $("#ltbqName").val(),
			ltbqType :$("#ltbqType").val()
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
function getLtbqById(ltbqId) {
	window.location = "getLtbqById?ltbqId=" + ltbqId;
}

function delLtbq(ltbqId) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "delLtbq",
			data : {
				ltbqId : ltbqId
			},
			dataType : "json",
			success : window.location.reload()
		});
	}
}
