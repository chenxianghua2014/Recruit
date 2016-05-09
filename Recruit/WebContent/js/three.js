var pageSize = 10;
var dataCount = 0;
function pageselectCallback(page_index) {
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
			onlySelf : 'yes'
		},
		dataType : "json",
		success : function(xcxxs) {
			for ( var i = 0; i <= xcxxs.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + xcxxs[i].xcxxXclb + "</td>";
				strHtml += "<td>" + xcxxs[i].xcxxTitle + "</td>";
				strHtml += "<td>" + xcxxs[i].xcxxFbsj + "</td>";
				strHtml += "<td>" + xcxxs[i].xcxxCheckstatus + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"editXcxx('"
						+ xcxxs[i].xcxxId + "');\"";
				strHtml += "	 	class='inputButton' value='编辑' />";
				strHtml += "</td>";
				/*strHtml += "<td class='alignCenter'>";
				strHtml += "<input name='button'";
				strHtml += "		type='button' onclick=\"XcxxDel('" + xcxxs[i].xcxxId + "');\"";
				strHtml += "	 	class='inputButton' value='删除' />";
				strHtml += "</td>";*/
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
			onlySelf : 'yes'
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

function AddXcxx() {
	window.location = "XcxxAdd";
}
function editXcxx(_id) {
	window.location = "XcxxAdd?id=" + _id;
}
function XcxxDel(_id) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "XcxxDel",
			data : {
				id : _id
			},
			dataType : "json",
			success : function(_result) {
				if (_result > 0) {
					alert('删除成功!');
					window.location.reload();
				}
			}
		});
	}
}