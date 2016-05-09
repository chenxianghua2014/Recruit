var pageSize = 10;
var dataCount = 0;
var bkId = "";
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
				url : "BbsByLtbkIdData",
				data : {
					pageNum : page_index,
					pageSize : pageSize,
					currentPageSize : currentPageSize,
					ltbkId : bkId
				},
				dataType : "json",
				success : function(mav) {
					for (var i = 0; i <= mav.model["bbsList"].length - 1; i++) {
						strHtml += "<div class='bbsReply'>";
						strHtml += "<div class='replyName'>"
								+ mav.model["bbsList"][i].bbsFbr + "：</div>";
						strHtml += "<div class='replyCon'><a href='javascript:void(0);'onclick=\"getBbsById('"
								+ mav.model["bbsList"][i].bbsId
								+ "');\">"
								+ mav.model["bbsList"][i].bbsNr + "</a></div>";
						strHtml += "<div class='bbsReplyTime'>"
								+ mav.model["bbsList"][i].fbsj
								+ "<div class='bbszan'><a id='updBbsZan' href='javascript:void(0);'onclick=\"updZan('"
								+ mav.model["bbsList"][i].bbsId
								+ "');\">赞（"+mav.model["bbsList"][i].zan+"）</a> <a href='javascript:void(0);'onclick=\"getBbsById('"
								+ mav.model["bbsList"][i].bbsId + "');\">评论（"
								+ mav.model["bbsList"][i].rcount
								+ "）</a></div></div>";
						strHtml += "</div>";
					}
					$("#tbList").html(strHtml);
				}
			});
	return false;
}
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}
$(document).ready(function() {
	if (getUrlParam("ltbkId") == null) {
		loadData("");
	} else {
		loadData(getUrlParam("ltbkId"));
	}
});

function loadData(ltbkId) {
	bkId = ltbkId;
	$.ajax({
		type : 'POST',
		url : "BbsByLtbkIdDataCount",
		data : {
			ltbkId : bkId
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
function getBbsById(BbsId) {
	window.location = "getBbsById?bbsId=" + BbsId;
}
function updZan(BbsId){
if($("#username").val() == null || $("#username").val() == ""){
	$.jBox.open("iframe:LoginFrm", "用户登录", 280, 200, {
		buttons : {}
	});
}else{
	$.ajax({
		type : 'POST',
		url : "addZan",
		data : {
			bbsId : BbsId,
			userId:$("#userId").val()
		},
		dataType : "json",
		success : function(count) {
			if(count == true){
				 $.jBox.tip("恭喜您，已成功赞了此条评论！");
				 window.setTimeout(function () {
					 window.location.reload();
					 }, 2000);
			}else{
				 $.jBox.tip("您已经赞了此条评论，不能重复此操作！");
			}
			
		}
	});
}
}
