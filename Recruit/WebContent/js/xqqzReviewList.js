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
		url : "XqqzReviewData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			xqqzId : id,
			xqqzReviewPlr : $("#xqqzReviewPlr").val(),
			xqqzReviewNr : $("#xqqzReviewNr").val()
		},
		dataType : "json",
		success : function(xqqzReviewList) {
			for (var i = 0; i <= xqqzReviewList.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>"+ (i+1) + "</td>";
				strHtml += "<td>"+ xqqzReviewList[i].xqqzReviewPlr + "</td>";
				strHtml += "<td>"+ xqqzReviewList[i].xqqzReviewNr + "</td>";
				strHtml += "<td>"+ xqqzReviewList[i].plsj + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delXqqzReview('"
						+ xqqzReviewList[i].xqqzReviewId + "','"+xqqzReviewList[i].xqqzId+"');\"";
				strHtml += "	 	class='inputButton' value='删除' />";
				strHtml += "</td>";
				$.ajax({
					type : 'GET',
					url : "checkPlrSFinUser",
					data : {
						reviewPlr : xqqzReviewList[i].xqqzReviewPlr
					},
					dataType : "json",
					async:false,
					success : function(result) {
						if (result > 0) {
							strHtml += "<td class='alignCenter'><input name='button'";
							strHtml += "		type='button' onclick=\"xqqzReviewJy('"
									+ xqqzReviewList[i].xqqzReviewPlr + "');\"";
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
	loadData(getUrlParam("xqqzId"));
});
function loadData(xqqzId) {
	id = xqqzId;
	$.ajax({
		type : 'POST',
		url : "XqqzReviewDataCount",
		data : {
			xqqzId : id,
			xqqzReviewPlr : $("#xqqzReviewPlr").val(),
			xqqzReviewNr : $("#xqqzReviewNr").val()
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

function delXqqzReview(xqqzReviewId,xqqzId) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "delXqqzReview",
			data : {
				xqqzReviewId : xqqzReviewId,
				xqqzId:xqqzId
			},
			dataType : "json",
			success : window.location.reload()
		});
	}
}

function xqqzReviewJy(xqqzReviewPlr) {
	if (confirm("您确定要对该用户禁言吗?")) {
		$.ajax({
			type : 'GET',
			url : "xqqzReviewJy",
			data : {
				xqqzReviewPlr : xqqzReviewPlr
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
