var pageSize = 10;
var dataCount = 0;
var name = "";
var id = "";
function pageselectCallback(page_index, jq) {
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
				url : "LoadqtXqqzData",
				data : {
					pageNum : page_index,
					pageSize : pageSize,
					currentPageSize : currentPageSize,
					xqqzTitle : $("#xqqzTitle").val(),
					xqqzFbr : $("#xqqzFbr").val(),
					xqqzType : $("#xqqzType").val(),
					ltbqName : name,
					ltbqId : id
				},
				dataType : "json",
				success : function(xqqzList) {
					for (var i = 0; i <= xqqzList.length - 1; i++) {
						strHtml += "<div class='article clr'>";
						strHtml += "<div class='artName'>"
								+ xqqzList[i].xqqzFbr
								+ "：<span><a href='javascript:void(0);' onclick=\"queryXqqzById('"
								+ xqqzList[i].xqqzId + "');\">"
								+ xqqzList[i].xqqzTitle + "</a></span></div>";
						strHtml += "<div class='artRight'>";
						strHtml += "<span>作者：" + xqqzList[i].xqqzFbr
								+ "&nbsp;&nbsp;时间：" + xqqzList[i].fbsj
								+ "</span>";
						strHtml += "<p><a href='javascript:void(0);' onclick=\"queryXqqzById('"
								+ xqqzList[i].xqqzId
								+ "');\">"
								+ xqqzList[i].nr + "</a></p>";
						strHtml += "<div class='artexamine'><a href='javascript:void(0);' onclick=\"queryXqqzById('"
								+ xqqzList[i].xqqzId + "');\">评论("
								+ xqqzList[i].pls
								+ ")</a>&nbsp;&nbsp;<a href='javascript:void(0);' onclick=\"queryXqqzById('"
								+ xqqzList[i].xqqzId + "');\">查看详情</a></div>";
						strHtml += "</div>";
						strHtml += "</div>";
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
	loadData("", "");
});
function loadData(ltbqName, ltbqId) {
	name = ltbqName;
	id = ltbqId;
	$.ajax({
		type : 'POST',
		url : "LoadqtXqqzDataCount",
		data : {
			xqqzTitle : $("#xqqzTitle").val(),
			xqqzFbr : $("#xqqzFbr").val(),
			xqqzType : $("#xqqzType").val(),
			ltbqName : name,
			ltbqId : id
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

function queryXqqzById(xqqzId) {
	window.location = "queryXqqzById?xqqzId=" + xqqzId;
}
