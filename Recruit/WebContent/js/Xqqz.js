var pageSize = 10;
var dataCount = 0;
var name="";
var id="";
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
		url : "LoadXqqzData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			xqqzTitle : $("#xqqzTitle").val(),
			xqqzType : $("#xqqzType").val(),
			ltbqName : name,
			ltbqId : id
		},
		dataType : "json",
		success : function(xqqzList) {
			for (var i = 0; i <= xqqzList.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td><a href='javascript:void(0);'onclick=\"htqueryXqqzById('"+ xqqzList[i].xqqzId+"');\">" + xqqzList[i].xqqzTitle + "</a></td>";
				strHtml += "<td>" + xqqzList[i].xqqzFbr + "</td>";
				if( xqqzList[i].lxmc == null || xqqzList[i].lxmc == ""){
					strHtml += "<td>----</td>";
				}else{
					strHtml += "<td>" + xqqzList[i].lxmc + "</td>";
				}
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"queryXqqzReviewById('"
						+ xqqzList[i].xqqzId + "');\"";
				strHtml += "	 	class='inputButton' value='管理评论' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"checkLtbqXqqz('"
						+ xqqzList[i].xqqzId + "');\"";
				strHtml += "	 	class='inputButton' value='移动分组' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"editxqqz('"
						+ xqqzList[i].xqqzId + "');\"";
				strHtml += "	 	class='inputButton' value='编辑' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delXqqz('"
						+ xqqzList[i].xqqzId + "');\"";
				strHtml += "	 	class='inputButton' value='删除' />";
				strHtml += "</td>";
				strHtml += "</tr>";
			}
			$("#tbList").html(strHtml);
		}
	});
	return false;
}
function checkLtbqXqqz(xqqzId){
	window.location = "checkLtbqXqqz?xqqzId=" + xqqzId;
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
	loadData("","");
});
function loadData(ltbqName,ltbqId) {
	name=ltbqName;
	id=ltbqId;
	$.ajax({
		type : 'POST',
		url : "LoadXqqzDataCount",
		data : {
			xqqzTitle : $("#xqqzTitle").val(),
			xqqzType : $("#xqqzType").val(),
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

function AddXqqz() {
	window.location = "xqqzAdd";
}
function editxqqz(xqqzId) {
	window.location = "xqqzAdd?xqqzId=" + xqqzId;
}
function queryXqqzReviewById(xqqzId) {
	window.location = "queryXqqzReviewById?xqqzId="+xqqzId;
}
function htqueryXqqzById(xqqzId){
	window.location = "htqueryXqqzById?xqqzId="+xqqzId;
}

function delXqqz(xqqzId) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "delXqqz",
			data : {
				xqqzId : xqqzId
			},
			dataType : "json",
			success : window.location.reload()
		});
	}
}