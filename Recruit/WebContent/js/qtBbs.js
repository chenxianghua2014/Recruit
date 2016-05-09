var pageSize = 10;
var dataCount = 0;
var name = "";
var id = "";
var ln = "";
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
				url : "LoadqtBbsData",
				data : {
					pageNum : page_index,
					pageSize : pageSize,
					currentPageSize : currentPageSize,
					bbsNr : $("#bbsNr").val(),
					bbsFbr : $("#bbsFbr").val(),
					bbsBqid : ln,
					ltbqName : name,
					ltbqId : id
				},
				dataType : "json",
				success : function(mav) {
					for (var i = 0; i <= mav.model["bbsList"].length - 1; i++) {
						strHtml += "<div class='comments-item-bd'>";
						strHtml += "<span class='name'>"
								+ mav.model["bbsList"][i].bbsFbr
								+ ":</span><span class='con'><a href='javascript:void(0);'onclick=\"getBbsById('"
								+ mav.model["bbsList"][i].bbsId
								+"');\">"+ mav.model["bbsList"][i].bbsNr + "</a></span>";
						strHtml += "<div style='clear:both;'></div>";
						strHtml += "<div class='time'>"
								+ mav.model["bbsList"][i].fbsj + "</div>";
						strHtml += "</div>";
					}
					$("#tbList").html(strHtml);
				}
			});
	return false;
}
function getUrlParam(name)
{
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}
$(document).ready(function() {
	if(getUrlParam("ltbkName") == null)
	{
		loadData("", "", "");
	}else{
		loadData("", "", getUrlParam("ltbkName"));
	}
//	loadData("", "", "");
		
});

function loadData(ltbqName, ltbqId, ltbkName) {
	setBqid(ltbkName);
	name = ltbqName;
	id = ltbqId;
	ln = ltbkName;
	$.ajax({
		type : 'POST',
		url : "LoadqtBbsDataCount",
		data : {
			bbsNr : $("#bbsNr").val(),
			bbsFbr : $("#bbsFbr").val(),
			bbsBqid : ln,
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
function getBbsById(BbsId) {
	window.location = "getBbsById?bbsId=" + BbsId;
}
