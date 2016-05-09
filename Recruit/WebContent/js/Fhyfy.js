
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
	$
			.ajax({
				type : 'POST',
				url : "queryFhyfy",
				data : {
					pageNum : page_index,
					pageSize : pageSize,
					currentPageSize : currentPageSize,
					zzjgDwmc : $("#zzjgDwmc").val(),
					zzjgDwdm : $("#zzjgDwdm").val(),
					fyye:$("#fyye").val()
				},
				dataType : "json",
				success : function(zzjgList) {
					for ( var i = 0; i <= zzjgList.length - 1; i++) {
						strHtml += "<tr>";
						strHtml += "<td>" + zzjgList[i].zzjgDwdm + "</td>";
						strHtml += "<td>" + zzjgList[i].zzjgDwmc + "</td>";
						if(zzjgList[i].yfkye ==null ||zzjgList[i].yfkye == ""){
							strHtml += "<td>" + "----" + "</td>";
						}else{
							strHtml += "<td>" + zzjgList[i].yfkye + "</td>";
						}
						if(zzjgList[i].yffy ==null ||zzjgList[i].yffy == ""){
							strHtml += "<td>" + "----" + "</td>";
						}else{
							strHtml += "<td>" + zzjgList[i].yffy + "</td>";
						}
						if(zzjgList[i].cpcs == null ||zzjgList[i].cpcs == ""){
							strHtml += "<td>" + "----" + "</td>";
						}else{
							strHtml += "<td>" + zzjgList[i].cpcs + "</td>";
						}
						if(zzjgList[i].sqfy == null ||zzjgList[i].sqfy == ""){
							strHtml += "<td>" + "----" + "</td>";
						}else{
							strHtml += "<td>" + zzjgList[i].sqfy + "</td>";
						}
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"editKsxcgl('"
								+ zzjgList[i].zzjgId + "');\"";
						strHtml += "	 	class='inputButton' value='修改' />";
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
		url : "queryFhyfyCount",
		data : {
			zzjgDwmc : $("#zzjgDwmc").val(),
			zzjgDwdm : $("#zzjgDwdm").val(),
			fyye:$("#fyye").val()
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
function editKsxcgl(zzjgId) {
	window.location = "queryFhyfyById?zzjgId="+zzjgId;
}
