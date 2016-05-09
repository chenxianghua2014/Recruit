
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
		url : "LoadArticleData1",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			articleTitle : $("#articleTitle").val(),
			articleType : $("#articleType").val()
		},
		dataType : "json",
		success : function(articleList) {
			for ( var i = 0; i <= articleList.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + articleList[i].articleTitle + "</td>";
				strHtml += "<td>" + articleList[i].articleAuthor + "</td>";
				strHtml += "<td>" + articleList[i].articleType + "</td>";
				strHtml += "<td>" + articleList[i].articleViewer + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"shArticle('"
						+ articleList[i].articleId + "');\"";
				strHtml += "	 	class='inputButton' value='审核' />";
				strHtml += "</td>";
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
		url : "LoadArticleDataCount1",
		data : {
			articleTitle : $("#articleTitle").val(),
			articleType : $("#articleType").val()
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
function shArticle(articleId) {
	window.location = "shArticle?articleId=" + articleId;
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