
var pageSize = 10;
var dataCount = 0;
function pageselectCallback(page_index, jq) {
	var strHtml = "";
	page_index += 1; 
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
		url : "LoadLtbkData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			ltbkName : $("#ltbkName").val()
		},
		dataType : "json",
		success : function(listLtbk) {
			for ( var i = 0; i <= listLtbk.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + listLtbk[i].ltbkName + "</td>";
				strHtml += "<td>" + listLtbk[i].tlqName + "</td>";
				strHtml += "<td>" + listLtbk[i].addTime + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"editLtbk('"
						+ listLtbk[i].ltbkId + "');\"";
				strHtml += "	 	class='inputButton' value='修改' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delLtbk('"
						+ listLtbk[i].ltbkId + "');\"";
				strHtml += "	 	class='inputButton' value='删除' />";
				strHtml += "</td>";
				strHtml += "</tr>";
			}
			$("#tbList").html(strHtml);
		}
	});
	return false;
}
function checkLtbqArticle(articleId){
	window.location = "checkLtbqArticle?articleId=" + articleId;
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
		url : "LoadLtbkDataCount",
		data : {
			ltbkName : $("#ltbkName").val()
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

function LtbkAdd() {
	window.location = "LtbkAdd";
}
function editLtbk(ltbkId) {
	window.location = "LtbkAdd?ltbkId=" + ltbkId;
}
function delLtbk(ltbkId) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "delLtbk",
			data : {
				ltbkId : ltbkId
			},
			dataType : "json",
			success : window.location.reload()
		});
	}
}