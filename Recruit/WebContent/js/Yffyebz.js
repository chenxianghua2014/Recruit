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
		url : "bzLoadMemberData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			memberName: $("#memberName").val(),
			memberIdcard:$("#memberIdcard").val(),
			memberHydj: $("#memberHydj").val()
		},
		dataType : "json",
		success : function(memberList) { 
			for ( var i = 0; i <= memberList.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + memberList[i].memberIdcard + "</td>";
				strHtml += "<td>" + memberList[i].memberName + "</td>";
				strHtml += "<td>" + memberList[i].memberHydj + "</td>";
				strHtml += "<td>" + memberList[i].memberYfkye + "</td>";
				strHtml += "<td>" + memberList[i].yjf + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delMember('"
						+ memberList[i].memberId + "');\"";
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
		url : "bzLoadMemberDataCount",
		data : {
			memberName: $("#memberName").val(),
			memberIdcard:$("#memberIdcard").val(),
			memberHydj: $("#memberHydj").val()
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
function memberAdd() {
	window.location = "memberAdd";
}
function delMember(memberId) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "delMember",
			data : {
				memberId : memberId
			},
			dataType : "json",
			success : window.location.reload()
		});
	}
}
