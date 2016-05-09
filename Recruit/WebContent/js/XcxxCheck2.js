var pageSize = 10;
var dataCount = 0;
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
		url : "LoadXcxxData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			keywords : $("#keywords").val(),
			xcxxCheckstatus : $("#xcxxCheckstatus").val(),
			onlySelf : 'no'
		},
		dataType : "json",
		success : function(xcxxs) {
			for ( var i = 0; i <= xcxxs.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + xcxxs[i].xcxxAddcompany + "</td>";
				strHtml += "<td>" + xcxxs[i].xcxxXclb + "</td>";
				strHtml += "<td>" + xcxxs[i].xcxxTitle + "</td>";
				strHtml += "<td>" + xcxxs[i].xcxxFbsj + "</td>";
				
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"editXcxx('" + xcxxs[i].xcxxId + "');\"";
				strHtml += "	 	class='inputButton' value='查看' />";
				strHtml += "</td>";
				
				if (xcxxs[i].xcxxCheckstatus == "待审核") {
					strHtml += "<td class='alignCenter'><input name='button'";
					strHtml += "		type='button' onclick=\"ExcuteCheck('"
							+ xcxxs[i].xcxxId + "');\"";
					strHtml += "	 	class='inputButton' value='提交集团审核' />";
					strHtml += "</td>";
				} else if (xcxxs[i].xcxxCheckstatus == "不通过") {
					strHtml += "<td class='alignCenter'><input name='button'";
					strHtml += "		type='button' onclick=\"ShowCheckDetailed('"
							+ xcxxs[i].xcxxId + "');\"";
					strHtml += "	 	class='inputButton' value='不通过' />";
					strHtml += "</td>";
				} else {
					strHtml += "<td class='alignCenter'><input name='button'";
					strHtml += "		type='button' ";
					strHtml += "	 	class='inputButton' value='"
							+ xcxxs[i].xcxxCheckstatus + "' />";
					strHtml += "</td>";
				}
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
		url : "LoadXcxxDataCount",
		data : {
			keywords : $("#keywords").val(),
			xcxxCheckstatus : $("#xcxxCheckstatus").val(),
			onlySelf : 'no'
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
function editXcxx(_id) {
	window.location = "XcxxView?id=" + _id;
}

function ExcuteCheck(_id) {
	window.location = "XcxxExcuteCheck2?id=" + _id;
}

function ShowCheckDetailed(_id) {
	window.location = "XcxxCheckDetailed?id=" + _id;
}