var pageSize = 10;
var dataCount = 0;
var name = "";
var id = "";
function pageselectCallback(page_index, jq) {
	page_index += 1;
	var strHtml = "";
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
				url : "LoadBbsData",
				data : {
					pageNum : page_index,
					pageSize : pageSize,
					currentPageSize : currentPageSize,
					bbsNr : $("#bbsNr").val(),
					bbsFbr : $("#bbsFbr").val(),
					ltbkId : $("#ltbkId").val(),
					ltbqName : name,
					ltbqId : id
				},
				dataType : "json",
				success : function(mav) {
					for (var i = 0; i <= mav.model["bbsList"].length - 1; i++) {
						strHtml += "<div class='comments-item-bd'>";
						strHtml += "<span class='name'>"
								+ mav.model["bbsList"][i].bbsFbr
								+ ":</span><span class='con'>"
								+ mav.model["bbsList"][i].bbsNr + "</span>";
						strHtml += "<span class='del'>";
						$.ajax({
							type : 'GET',
							url : "checkPlrSFinUser",
							data : {
								reviewPlr : mav.model["bbsList"][i].bbsFbr
							},
							dataType : "json",
							async:false,
							success : function(result) {
								if (result > 0) {
									strHtml += "<a href='javascript:void(0);' onclick=\"userJygl('"
										+ mav.model["bbsList"][i].bbsFbr
										+ "');\">禁言</a>&nbsp;";
								}
							}
						});
						strHtml += "<a href='javascript:void(0);' onclick=\"checkLtbq('"
								+ mav.model["bbsList"][i].bbsId
								+ "');\">移动分组</a>";
						strHtml += "<a href='javascript:void(0);' onclick=\"delBbs('"
								+ mav.model["bbsList"][i].bbsId
								+ "');\"><img src='images/delete.gif'></a></span>";
						strHtml += "<div style='clear:both;'></div>";
						strHtml += "<div class='time'>"
								+ mav.model["bbsList"][i].fbsj + "</div>";
						strHtml += "<div class='comments-list'>";
						for (var j = 0; j <= mav.model["reviewList"].length - 1; j++) {
							if (mav.model["bbsList"][i].bbsId == mav.model["reviewList"][j].bbsId) {
								strHtml += "<ul>";
								strHtml += "<li><span class='name'>"
										+ mav.model["reviewList"][j].reviewPlr
										+ ":</span><span class='con'>"
										+ mav.model["reviewList"][j].reviewNr
										+ "</span>";
								strHtml += "<span class='del'>";
								$.ajax({
									type : 'GET',
									url : "checkPlrSFinUser",
									data : {
										reviewPlr : mav.model["reviewList"][j].reviewPlr
									},
									dataType : "json",
									async:false,
									success : function(result) {
										if (result > 0) {
											strHtml += "<a href='javascript:void(0);' onclick=\"userJygl('"
												+ mav.model["reviewList"][j].reviewPlr
												+ "');\">禁言</a>&nbsp;";
										}
									}
								});
								strHtml += "<a href='javascript:void(0);' onclick=\"delReview('"
										+ mav.model["reviewList"][j].reviewId
										+ "');\"><img src='images/delete.gif'></a></span>";
								strHtml += "<div style='clear:both;'></div>";
								strHtml += "<div class='time'>"
										+ mav.model["reviewList"][j].plsj
										+ "</div></li>";
								strHtml += "</ul>";
							}

						}
						strHtml += "</div>";

						strHtml += "<form action='ReviewSave1?bbsId="
								+ mav.model["bbsList"][i].bbsId
								+ "' method='post'>";
						strHtml += "<div class='huifu'>";
						strHtml += "  <textarea rows='2'cols='80'  name='reviewNr'>";

						strHtml += "  </textarea>";
						strHtml += "  <input  class='inputButton' type='submit' value='评论'>";
						strHtml += " </div>";
						strHtml += " </form>";
						strHtml += " </div>";
					}
					$("#tbList").html(strHtml);
				}
			});
	return false;
}
$(document).ready(function() {
	loadData("", "");
});

function loadData(ltbqName, ltbqId) {
	name = ltbqName;
	id = ltbqId;
	$.ajax({
		type : 'POST',
		url : "LoadBbsDataCount",
		data : {
			bbsNr : $("#bbsNr").val(),
			bbsFbr : $("#bbsFbr").val(),
			ltbkId : $("#ltbkId").val(),
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
function getOptionsFromForm() {
	var opt = {
		callback : pageselectCallback
	};
	opt.prev_text = "上一页";
	opt.next_text = "下一页";
	return opt;
}
function queryBq() {
	window.location = "queryLtbq";
}
function getBbsById(BbsId) {
	window.location = "getBbsById?BbsId=" + BbsId;
}
function queryByBbsId(bbsId) {
	window.location = "queryByBbsId?bbsId=" + bbsId;
}
function delBbs(bbsId) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "delBbs",
			data : {
				bbsId : bbsId
			},
			dataType : "json",
			success : window.location.reload()
		});
	}
}
function delReview(reviewId) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "delReview1",
			data : {
				reviewId : reviewId
			},
			dataType : "json",
			success : window.location.reload()
		});
	}
}
function checkLtbq(bbsId) {
	window.location = "checkLtbq?bbsId=" + bbsId;
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
