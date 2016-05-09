var pageSize = 10;
var dataCount = 0;
var name = "";
var id = "";
var viewer = "";
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
				url : "LoadZytyData",
				data : {
					pageNum : page_index,
					pageSize : pageSize,
					currentPageSize : currentPageSize,
					articleTitle : $("#articleTitle").val(),
					articleType : $("#articleType").val(),
					articleAuthor : $("#articleAuthor").val(),
					ltbqName : name,
					ltbqId : id,
					articleViewer : viewer
				},
				dataType : "json",
				success : function(articleList) {
					for (var i = 0; i <= articleList.length - 1; i++) {
						strHtml += "<div class='article clr'>";
						strHtml += "<div class='artName'>"
								+ articleList[i].articleAuthor
								+ "：<span><a href='javascript:void(0);' onclick=\"queryArticleById('"
								+ articleList[i].articleId + "');\">"
								+ articleList[i].articleTitle
								+ "</a></span></div>";
						strHtml += "<div class='artRight'>";
						strHtml += "<span>作者：" + articleList[i].articleAuthor
								+ "&nbsp;&nbsp;类型："
								+ articleList[i].articleType
								+ "&nbsp;&nbsp;时间：" + articleList[i].addTime
								+ "</span>";
						strHtml += "<p><a href='javascript:void(0);' onclick=\"queryArticleById('"
								+ articleList[i].articleId
								+ "');\">"
								+ articleList[i].content + "</a></p>";
						strHtml += "<div class='artexamine'><a href='javascript:void(0);' onclick=\"queryArticleById('"
								+ articleList[i].articleId
								+ "');\">评论("
								+ articleList[i].pls
								+ ")</a>&nbsp;&nbsp;<a href='javascript:void(0);' onclick=\"queryArticleById('"
								+ articleList[i].articleId
								+ "');\">查看详情</a></div>";
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
function loadData(ltbqName, ltbqId, viewer) {
	name = ltbqName;
	id = ltbqId;
	$.ajax({
		type : 'POST',
		url : "LoadZytyDataCount",
		data : {
			articleTitle : $("#articleTitle").val(),
			articleType : $("#articleType").val(),
			articleAuthor : $("#articleAuthor").val(),
			ltbqName : name,
			ltbqId : id,
			articleViewer : viewer
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

function queryArticleById(articleId) {
	window.location = "queryZytyById?articleId=" + articleId;
}
