
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
		url : "XqqzLxglData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			xqqzlxglLxmc : $("#xqqzlxglLxmc").val()
		},
		dataType : "json",
		success : function(xqqzLxglList) {
			for ( var i = 0; i <= xqqzLxglList.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + (i+1) + "</td>";
				strHtml += "<td>" + xqqzLxglList[i].xqqzlxglLxmc + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"editXqqzLxgl('"
						+ xqqzLxglList[i].xqqzlxglId + "');\"";
				strHtml += "	 	class='inputButton' value='修改' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delXqqzLxgl('"
						+ xqqzLxglList[i].xqqzlxglId + "');\"";
				strHtml += "	 	class='inputButton' value='删除' />";
				strHtml += "</td>";
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
$(document).ready(function() {
	loadData();
});
function loadData() {
	$.ajax({
		type : 'POST',
		url : "XqqzLxglCount",
		data : {
			xqqzlxglLxmc : $("#xqqzlxglLxmc").val()
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

function AddXqqzLxgl() {
	window.location = "xqqzLxglAdd";
}
function editXqqzLxgl(xqqzlxglId) {
	window.location = "xqqzLxglAdd?xqqzlxglId=" + xqqzlxglId;
}
function delXqqzLxgl(xqqzlxglId) {
	$.ajax({
		type : 'GET',
		url : "XqqzCount",
		data : {
			xqqzlxglId : xqqzlxglId
		},
		dataType : "json",
		success : function (result){
			if(result > 0){
				if (confirm("该类型已经被试用，您确定要删除么？")) {
					window.location = "delXqqzLxgl?xqqzlxglId=" + xqqzlxglId;
				}
			}else{
				if (confirm("您确定要删除该条数据么？")) {
					window.location = "delXqqzLxgl?xqqzlxglId=" + xqqzlxglId;
				}
			}
		}
	});
}