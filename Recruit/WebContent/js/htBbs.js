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
				url : "htBbsData",
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
						strHtml += "<tr>";
						strHtml += "<td>"+ (i+1) + "</td>";
						strHtml += "<td>"+mav.model["bbsList"][i].bbsFbr+ "</td>";
						strHtml += "<td>"+mav.model["bbsList"][i].bbsNr+ "</td>";
						strHtml += "<td>"+mav.model["bbsList"][i].ssbk+ "</td>";
						strHtml += "<td>"+mav.model["bbsList"][i].rcount+ "</td>";
						strHtml += "<td>"+mav.model["bbsList"][i].zan+ "</td>";
						strHtml += "<td>"+mav.model["bbsList"][i].fbsj+ "</td>";
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"htBbsReview('"
								+ mav.model["bbsList"][i].bbsId + "');\"";
						strHtml += "	 	class='inputButton' value='管理评论' />";
						strHtml += "</td>";
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"htBbsCheckLtbq('"
								+ mav.model["bbsList"][i].bbsId + "');\"";
						strHtml += "	 	class='inputButton' value='移动分组' />";
						strHtml += "</td>";
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"editBbs('"
								+ mav.model["bbsList"][i].bbsId + "');\"";
						strHtml += "	 	class='inputButton' value='编辑' />";
						strHtml += "</td>";
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"delBbs('"
								+ mav.model["bbsList"][i].bbsId + "');\"";
						strHtml += "	 	class='inputButton' value='删除' />";
						strHtml += "</td>";
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
									strHtml += "<td class='alignCenter'><input name='button'";
									strHtml += "		type='button' onclick=\"userJygl('"
										+ mav.model["bbsList"][i].bbsFbr + "');\"";
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
$(document).ready(function() {
	loadData("", "");
});

function loadData(ltbqName, ltbqId) {
	name = ltbqName;
	id = ltbqId;
	$.ajax({
		type : 'POST',
		url : "htBbsCount",
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
function htBbsAdd(){
	window.location = "htBbsAdd";
}
function editBbs(bbsId){
	window.location = "htBbsAdd?bbsId="+bbsId;
}
function htBbsReview(bbsId){
	window.location = "htBbsReview?bbsId="+bbsId;
}
function delBbs(bbsId) {
	if (confirm("您确定要删除该条记录吗?")) {
		window.location = "delBbs?bbsId="+bbsId;
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
function htBbsCheckLtbq(bbsId) {
	window.location = "htBbsCheckLtbq?bbsId=" + bbsId;
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
