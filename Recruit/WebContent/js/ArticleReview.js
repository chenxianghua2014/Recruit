var pageSize = 10;
var dataCount = 0;
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
	$.ajax({
		type : 'POST',
		url : "ArticleReviewData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			articleId : id
		},
		dataType : "json",
		success : function(articleReviewList) {
			for (var i = 0; i <= articleReviewList.length - 1; i++) {
				strHtml += "<div class='reply'>";
				strHtml += "<div class='replyName'>"+ articleReviewList[i].articleReviewPlr+"：</div>";
				strHtml += "<div class='replyCon'>"+ articleReviewList[i].articleReviewNr+"</div>";
				strHtml += "<div class='replyTime'>"+ articleReviewList[i].plsj + "</div>";
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
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}
$(document).ready(function() {
	loadData(getUrlParam("articleId"));
});
function loadData(bbsId) {
	id = bbsId;
	$.ajax({
		type : 'POST',
		url : "ArticleReviewDataCount",
		data : {
			articleId : id
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
