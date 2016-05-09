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
		url : "LoadTxlData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			keywords : $("#keywords").val()
		},
		dataType : "json",
		success : function(txls) {
			for ( var i = 0; i <= txls.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + ((page_index - 1) * 10 + i + 1) + "</td>";
				strHtml += "<td>" + txls[i].txlName + "</td>";
				strHtml += "<td>" + txls[i].txlDepartment + "</td>";
				strHtml += "<td>" + txls[i].txlPosition + "</td>";
				strHtml += "<td>" + txls[i].txlPhone + "</td>";
				strHtml += "<td>" + txls[i].txlEmail + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"editTxl('"
						+ txls[i].txlId + "');\"";
				strHtml += "	 	class='inputButton' value='编辑' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"TxlDel('"
						+ txls[i].txlId + "');\"";
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
		url : "LoadTxlDataCount",
		data : {
			keywords : $("#keywords").val()
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

function AddTxl() {
	window.location.href = "TxlAdd";
}
function editTxl(_id) {
	window.location.href = "TxlAdd?id=" + _id;
}
function TxlDel(_id) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "TxlDel",
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


function ImportShow() {
	var win = window.showModalDialog("ImportTxlShow", "true",
			"dialogWidth:300px;dialogHeight:150px;scroll:no;status:no");
	if (!win) {
		loadData();
	}
}
