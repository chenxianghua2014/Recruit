var pageSize = 10;
var dataCount = 0;
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
	$.ajax({
		type : 'POST',
		url : "BmglData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			bmglKsxm : $("#bmglKsxm").val(),
			bmglBkdw : $("#bmglBkdw").val(),
			bmglBkgw : $("#bmglBkgw").val(),
			bmglByxx : $("#bmglByxx").val(),
			bmglSfzh : $("#bmglSfzh").val(),
			bmglKcglId : $("#bmglKcglId").val(),
			bmglKsdrq : $("#bmglKsdrq").val()
		},
		dataType : "json",
		success : function(bmglList) {
			for ( var i = 0; i <= bmglList.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + (i + 1) + "</td>";
				strHtml += "<td>" + bmglList[i].bmglKsxm + "</td>";
				strHtml += "<td>" + bmglList[i].bmglSfzh + "</td>";
				strHtml += "<td>" + bmglList[i].bmglBkdw + "</td>";
				strHtml += "<td>" + bmglList[i].bmglBkgw + "</td>";
				strHtml += "<td>" + bmglList[i].bmglByxx + "</td>";
				strHtml += "<td>"
					+ bmglList[i].bmglKcmc
					+ "</td>";
				strHtml += "<td>"
						+ new Date(bmglList[i].bmglKsrq).toLocaleDateString()
						+ "</td>";
				strHtml += "<td>" + bmglList[i].bmglKssj + "</td>";
				strHtml += "<td>" + bmglList[i].bmglSfqr + "</td>";
				strHtml += "<td>" + bmglList[i].bmglLxdh + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delBmgl('"
						+ bmglList[i].bmglId + "');\"";
				strHtml += "	 	class='inputButton' value='删除' />";
				strHtml += "</td>";
				strHtml += "</tr>";
			}
			$("#tbList").html(strHtml);
		}
	});
	return false;
}
$(document).ready(function() {
	loadData();
});
function loadData() {
	$.ajax({
		type : 'POST',
		url : "BmglDataCount",
		data : {
			bmglKsxm : $("#bmglKsxm").val(),
			bmglBkdw : $("#bmglBkdw").val(),
			bmglBkgw : $("#bmglBkgw").val(),
			bmglByxx : $("#bmglByxx").val(),
			bmglSfzh : $("#bmglSfzh").val(),
			bmglKcglId : $("#bmglKcglId").val(),
			bmglKsdrq : $("#bmglKsdrq").val()
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
function addBmgl() {
	window.location = "addBmgl";
}
function delBmgl(bmglId) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "delBmgl",
			data : {
				bmglId : bmglId
			},
			dataType : "json",
			success : function(e) {
				alert("删除成功!");
				window.location.reload();
			}
		});
	}
}
