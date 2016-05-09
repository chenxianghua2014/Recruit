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
				url : "LoadArticleData",
				data : {
					pageNum : page_index,
					pageSize : pageSize,
					currentPageSize : currentPageSize,
					articleTitle : $("#articleTitle").val(),
					articleType : $("#articleType").val(),
					ltbqName : name,
					ltbqId : id
				},
				dataType : "json",
				success : function(articleList) {
					for (var i = 0; i <= articleList.length - 1; i++) {
						strHtml += "<tr>";
						strHtml += "<td><a href='javascript:void(0);'onclick=\"htqueryArticleById('"
								+ articleList[i].articleId
								+ "');\">"
								+ articleList[i].articleTitle + "</a></td>";
						strHtml += "<td>" + articleList[i].articleAuthor
								+ "</td>";
						strHtml += "<td>" + articleList[i].articleType
								+ "</td>";
						strHtml += "<td>" + articleList[i].articleViewer
								+ "</td>";
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"queryArticleReviewById('"
								+ articleList[i].articleId + "');\"";
						strHtml += "	 	class='inputButton' value='管理评论' />";
						strHtml += "</td>";
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"checkLtbqArticle('"
								+ articleList[i].articleId + "');\"";
						strHtml += "	 	class='inputButton' value='移动分组' />";
						strHtml += "</td>";
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"editArticle('"
								+ articleList[i].articleId + "');\"";
						strHtml += "	 	class='inputButton' value='编辑' />";
						strHtml += "</td>";
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"delArticle('"
								+ articleList[i].articleId + "');\"";
						strHtml += "	 	class='inputButton' value='删除' />";
						strHtml += "</td>";
						strHtml += "</tr>";
					}
					$("#tbList").html(strHtml);
				}
			});
	return false;
}
function checkLtbqArticle(articleId) {
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
	loadData("", "");
});
function loadData(ltbqName, ltbqId) {
	name = ltbqName;
	id = ltbqId;
	$.ajax({
		type : 'POST',
		url : "LoadArticleDataCount",
		data : {
			articleTitle : $("#articleTitle").val(),
			articleType : $("#articleType").val(),
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

function AddArticle() {
	window.location = "articleAdd";
}
function editArticle(articleId) {
	window.location = "articleAdd?articleId=" + articleId;
}

function queryArticleReviewById(articleId) {
	window.location = "queryArticleReviewById?articleId=" + articleId;
}

function htqueryArticleById(articleId){
	window.location = "htqueryArticleById?articleId="+articleId;
}
function delArticle(articleId) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "delArticle",
			data : {
				articleId : articleId
			},
			dataType : "json",
			success : window.location.reload()
		});
	}
}