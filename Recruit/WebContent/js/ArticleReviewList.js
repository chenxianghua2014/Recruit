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
			articleId : id,
			articleReviewPlr : $("#articleReviewPlr").val(),
			articleReviewNr : $("#articleReviewNr").val()
		},
		dataType : "json",
		success : function(articleReviewList) {
			for (var i = 0; i <= articleReviewList.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>"+ (i+1) + "</td>";
				strHtml += "<td>"+ articleReviewList[i].articleReviewPlr + "</td>";
				strHtml += "<td>"+ articleReviewList[i].articleReviewNr + "</td>";
				strHtml += "<td>"+ articleReviewList[i].plsj + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delArticleReview('"
						+ articleReviewList[i].articleReviewId + "','"+articleReviewList[i].articleId+"');\"";
				strHtml += "	 	class='inputButton' value='删除' />";
				strHtml += "</td>";
				$.ajax({
					type : 'GET',
					url : "checkPlrSFinUser",
					data : {
						reviewPlr : articleReviewList[i].articleReviewPlr
					},
					dataType : "json",
					async:false,
					success : function(result) {
						if (result > 0) {
							strHtml += "<td class='alignCenter'><input name='button'";
							strHtml += "		type='button' onclick=\"articleReviewJy('"
									+ articleReviewList[i].articleReviewPlr + "');\"";
							strHtml += "	 	class='inputButton' value='禁言' />";
							strHtml += "</td>";
						}
						else{
							strHtml += "<td class='alignCenter'>" + "----" + "</td>";
						}
					}
				});
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
			articleId : id,
			articleReviewPlr : $("#articleReviewPlr").val(),
			articleReviewNr : $("#articleReviewNr").val()
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
function delArticleReview(articleReviewId,articleId) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "delArticleReview",
			data : {
				articleReviewId : articleReviewId,
				articleId:articleId
			},
			dataType : "json",
			success : window.location.reload()
		});
	}
}

function articleReviewJy(articleReviewPlr) {
	if (confirm("您确定要对该用户禁言吗?")) {
		$.ajax({
			type : 'GET',
			url : "articleReviewJy",
			data : {
				articleReviewPlr : articleReviewPlr
			},
			dataType : "json",
			success : function(result){
				if(result>0){
					alert("禁言成功");
				}
				window.location.reload();
			}
		});
	}
}
