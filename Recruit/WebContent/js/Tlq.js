
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
		url : "TlqData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			tlqName : $("#tlqName").val()
		},
		dataType : "json",
		success : function(listTlq) {
			for ( var i = 0; i <= listTlq.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + (i+1) + "</td>";
				strHtml += "<td>" + listTlq[i].tlqName + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"editTlq('"
						+ listTlq[i].tlqId + "');\"";
				strHtml += "	 	class='inputButton' value='修改' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delTlq('"
						+ listTlq[i].tlqId + "');\"";
				strHtml += "	 	class='inputButton' value='删除' />";
				strHtml += "</td>";
				strHtml += "</tr>";
			}
			$("#tbList").html(strHtml);
		}
	});
	return false;
}
function checkLtbqArticle(articleId){
	window.location = "checkLtbqArticle?articleId=" + articleId;
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
		url : "TlqDataCount",
		data : {
			tlqName : $("#tlqName").val()
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

function tlqAdd() {
	window.location = "tlqAdd";
}
function editTlq(TlqId) {
	window.location = "tlqAdd?tlqId=" + TlqId;
}
function delTlq(TlqId) {
	if (confirm("您确定要删除该条记录吗?")) {
		window.location = "delTlq?tlqId=" + TlqId;
	}
}