
var pageSize = 10;
var dataCount = 0;
function pageselectCallback(page_index, jq) {
	page_index += 1;
	var strHtml = "";
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
		url : "LoadZjgzData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : (dataCount < page_index * pageSize ? dataCount
					- (page_index * pageSize - 10) : pageSize),
			keywords : $("#keywords").val(),
		},
		dataType : "json",
		success : function(zjgz) { 
			for ( var i = 0; i <= zjgz.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + zjgz[i].zjgzBh + "</td>";
				strHtml += "<td>" + zjgz[i].zjgzYxdj + "</td>";
				strHtml += "<td>" + zjgz[i].zjgzSykslx + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "type='button' onclick=\"ZjgzView('" + zjgz[i].zjgzId
						+ "');\"";
				strHtml += "class='inputButton' value='查看' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "type='button' onclick=\"ZjgzAdd('" + zjgz[i].zjgzId
						+ "');\"";
				strHtml += "class='inputButton' value='编辑' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'"; 
				strHtml += "type='button' onclick=\"delZjgz('"
						+ zjgz[i].zjgzId + "');\"";
				strHtml += "class='inputButton' value='删除' />";
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
		url : "LoadZjgzDataCount",
		data : {
			keywords : $("#keywords").val(),
			
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
function AddZjgz() {
	window.location = "ZjgzAdd";
}
function ZjgzAdd(_Id) {
	window.location = "ZjgzAdd?zjgzId=" + _Id;
}
function ZjgzView(_Id) {
	window.location = "ZjgzView?zjgzId=" + _Id;
}
function delZjgz(_Id) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "delZjgz",
			data : {
				zjgzId : _Id
			},
			dataType : "json",
			success : window.location.reload()
		});
	}
}
