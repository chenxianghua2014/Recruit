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
		url : "htBbsReviewReplyData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			replyNr : $("#replyNr").val(),
			replyName : $("#replyName").val(),
			reviewId : id
		},
		dataType : "json",
		success : function(replyList) {
			for (var i = 0; i <= replyList.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>"+(i+1)+ "</td>";
				strHtml += "<td>"+ replyList[i].replyName + "</td>";
				strHtml += "<td>"+ replyList[i].replyNr + "</td>";
				strHtml += "<td>"+ replyList[i].hfsj + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delhtBbsReviewReply('"
						+ replyList[i].replyId+"','"+replyList[i].reviewId+"');\"";
				strHtml += "	 	class='inputButton' value='删除' />";
				strHtml += "</td>";
				$.ajax({
					type : 'GET',
					url : "checkPlrSFinUser",
					data : {
						reviewPlr : replyList[i].replyName
					},
					dataType : "json",
					async:false,
					success : function(result) {
						if (result > 0) {
							strHtml += "<td class='alignCenter'><input name='button'";
							strHtml += "type='button' onclick=\"userJygl('"
								+ replyList[i].replyName +"','"+replyList[i].reviewId+ "');\"";
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
	loadData(getUrlParam("reviewId"));
});
function loadData(reviewId) {
	id = reviewId;
	$.ajax({
		type : 'POST',
		url : "htBbsReviewReplyCount",
		data : {
			replyNr : $("#replyNr").val(),
			replyName : $("#replyName").val(),
			reviewId : id
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
function delhtBbsReviewReply(replyId,reviewId) {
	if (confirm("您确定要删除该条记录吗?")) {
		window.location = "delhtBbsReviewReply?replyId="+replyId+"&reviewId="+reviewId;
	}
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
