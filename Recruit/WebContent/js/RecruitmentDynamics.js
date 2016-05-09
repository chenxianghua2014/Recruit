var pageSize = 10;
var dataCount = 0;

function pageselectCallback(page_index) {
	page_index += 1;
	var currentPageSize = (dataCount < page_index * pageSize ? dataCount
			- (page_index * pageSize - 10) : pageSize);
	$("#count").html(dataCount);
	if (dataCount == 0)
		$("#start").html(0);
	else
		$("#start").html(page_index * 10 - 9);
	$("#end").html(currentPageSize + page_index * 10 - 10);
	var strHtml = "";
	$.ajax({
		type : 'POST',
		url : "InformationCenterData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			ZPXW_CONTENT : $("#zpnr").val(),
			ZZJG_ID : dwid,
			SFZPRC : '否'
		},
		dataType : "json",
		success : function(jtjlks) {
			for ( var i = 0; i < jtjlks.length; i++) {
				var date = new Date(jtjlks[i].zpxwAddtime * 1);
				var theDate = date.Format("yyyy-MM-dd");
				strHtml += "<tr><td width=\"300\"><a href='RecruitmentDynamicsDetails?id="
						+ getQueryString("id")
						+ "&newsId="
						+ jtjlks[i].zpxwId
						+ "'>"
						+ jtjlks[i].zpxwTitle
						+ "</a></td><td width=\"220\">"
						+ jtjlks[i].zpxwAddcompany
						+ "</td><td width=\"100\">"
						+ theDate
						+ "</td></tr>";
			}
			$("#tbList").html(strHtml);
		}
	});
	return false;
}

Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

function getOptionsFromForm() {
	var opt = {
		callback : pageselectCallback
	};
	opt.prev_text = "上一页";
	opt.next_text = "下一页";
	return opt;
}
var dwid = null;
// 页面加载
$(document).ready(function() {
	dwid = getQueryString("id");
	loadData();
});
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
function loadData() {
	$.post("InformationCenterCount", {
		currentPageSize : pageSize,
		pageNum : 0,
		pageSize : pageSize,
		ZPXW_CONTENT : $("#zpnr").val(),
		ZZJG_ID : dwid,
		SFZPRC : '否'
	}, function(data) {
		dataCount = data;
		var optInit = getOptionsFromForm();
		$("#Pagination").pagination(dataCount, optInit);
		$("#setoptions").click(function() {
			var opt = getOptionsFromForm();
			$("#Pagination").pagination(dataCount, opt);
		});
	});
}