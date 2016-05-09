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
		url : "htBbsReviewData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			reviewNr : $("#reviewNr").val(),
			reviewPlr : $("#reviewPlr").val(),
			bbsId : id
		},
		dataType : "json",
		success : function(reviewList) {
			for (var i = 0; i <= reviewList.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>"+(i+1)+ "</td>";
				strHtml += "<td>"+ reviewList[i].reviewPlr + "</td>";
				strHtml += "<td>"+ reviewList[i].reviewNr + "</td>";
				strHtml += "<td>"+ reviewList[i].plsj + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"htBbsReviewReply('"
						+ reviewList[i].reviewId + "');\"";
				strHtml += "	 	class='inputButton' value='管理回复' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delhtBbsReview('"
						+ reviewList[i].reviewId+"','"+ reviewList[i].bbsId+"');\"";
				strHtml += "	 	class='inputButton' value='删除' />";
				strHtml += "</td>";
				$.ajax({
					type : 'GET',
					url : "checkPlrSFinUser",
					data : {
						reviewPlr : reviewList[i].reviewPlr
					},
					dataType : "json",
					async:false,
					success : function(result) {
						if (result > 0) {
							strHtml += "<td class='alignCenter'><input name='button'";
							strHtml += "type='button' onclick=\"userJygl('"
								+ reviewList[i].reviewPlr +"','"+reviewList[i].bbsId+ "');\"";
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
	loadData(getUrlParam("bbsId"));
});
function loadData(bbsId) {
	id = bbsId;
	$.ajax({
		type : 'POST',
		url : "htBbsReviewCount",
		data : {
			reviewNr : $("#reviewNr").val(),
			reviewPlr : $("#reviewPlr").val(),
			bbsId : id
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
function delhtBbsReview(reviewId,bbsId) {
	if (confirm("您确定要删除该条记录吗?")) {
		window.location = "delhtBbsReview?reviewId="+reviewId+"&bbsId="+bbsId;
	}
}
function htBbsReviewReply(reviewId){
	window.location = "htBbsReviewReply?reviewId="+reviewId;
}
function userJygl(reviewPlr) {
	if (confirm("您确定要禁言该用户么?")) {
		$.ajax({
			type : 'GET',
			url : "jyglUser",
			data : {
				reviewPlr : reviewPlr
			},
			dataType : "json",
			success : function(result) {
				if (result > 0) {
					alert("禁言成功");
				}
				window.location.reload();
			}
		});
	}
}
