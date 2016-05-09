var pageSize = 10;
var dataCount = 0;
function pageselectCallback(page_index) {
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
	
	var v1,v2,v3;
	switch($("#jtjlkZt").val()){
		case '测评,测评':
			v2 = '测评';	//jtjlkCpjg : "测评"
			break;
		case '一面进行中,二面进行中,终面进行中':
			v3 = '一面进行中,二面进行中,终面进行中';	//jtjlkMszt : "一面进行中,二面进行中,终面进行中"
			break;
		default:
			v1 = $("#jtjlkZt").val();
			break;
	}
	
	$
			.ajax({
				type : 'POST',
				url : "LoadBdwjlkData",
				data : {
					pageNum : page_index,
					pageSize : pageSize,
					currentPageSize : currentPageSize,
					jtjlkName : $("#jtjlkName").val(),
					jtjlkByyx : $("#jtjlkByyx").val(),
					jtjlkZy : $("#jtjlkZy").val(),
					jtjlkXl : $("#jtjlkXl").val(),
					jtjlkZw : $("#jtjlkZw").val(),
					jtjlkGwlb : $("#jtjlkGwlb").val(),
					jtjlkHkd : $("#jtjlkHkd").val(),
					jtjlkZt : v1,
					jtjlkMszt: v3,
					jtjlkCpjg:v2
				/*
				 * , jtjlkZw : $("#jtjlkZw").val(), jtjlkGwlb :
				 * $("#jtjlkGwlb").val()
				 */
				},
				dataType : "json",
				success : function(jtjlks) {
					for ( var i = 0; i <= jtjlks.length - 1; i++) {
						strHtml += "<tr>";
						strHtml += "<td><input name='subBox' type='checkbox' value='"+jtjlks[i].jtjlkId+"'/></td>";
						strHtml += "<td>" + jtjlks[i].jtjlkName + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkCsrq + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkSfzh + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkSex + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkByxx + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkZy + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkXl + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkZw + "</td>";
						strHtml += "<td>" +formatLongDate(jtjlks[i].jtjlkAddtime) + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkHkd + "</td>";
						//strHtml += "<td>" + jtjlks[i].zzjgName + "</td>";
						strHtml += "<td>" + jtjlks[i].jtjlkJlzt + "</td>";
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"window.open('YljlHr?resumeId="
								+ jtjlks[i].jtjlkUserid
								+ "&jtjlkId=jtjlk');\" class='inputButton' value='查看' />";
						/*if (jtjlks[i].jtjlkCpjg != "测评淘汰"
								&& jtjlks[i].jtjlkJlzt != "录用") {
							strHtml += "<td class='alignCenter'><input name='button'";
							strHtml += "		type='button' onclick=\"CollectionJl('"
									+ jtjlks[i].jtjlkId + "')\"";
							strHtml += "	 	class='inputButton' value='收藏' />";
							strHtml += "</td>";
						} else {
							strHtml += "<td class='alignCenter'>不可用";
							strHtml += "</td>";
						}*/
						/*
						 * strHtml += "<td class='alignCenter'><input
						 * name='button'"; strHtml += " type='button'
						 * onclick=\"DelJl('" + jtjlks[i].jtjlkId + "')\"";
						 * strHtml += " class='inputButton' value='删除' />";
						 * strHtml += "</td>";
						 */
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
	var v1,v2,v3;
	switch($("#jtjlkZt").val()){
		case '测评,测评':
			v2 = '测评';	//jtjlkCpjg : "测评"
			break;
		case '一面进行中,二面进行中,终面进行中':
			v3 = '一面进行中,二面进行中,终面进行中';	//jtjlkMszt : "一面进行中,二面进行中,终面进行中"
			break;
		default:
			v1 = $("#jtjlkZt").val();
			break;
	}
	
	$.ajax({
		type : 'POST',
		url : "LoadBdwJtjlkDataCount",
		data : {
			jtjlkName : $("#jtjlkName").val(),
			jtjlkByyx : $("#jtjlkByyx").val(),
			jtjlkZy : $("#jtjlkZy").val(),
			jtjlkXl : $("#jtjlkXl").val(),
			jtjlkZw : $("#jtjlkZw").val(),
			jtjlkGwlb : $("#jtjlkGwlb").val(),
			jtjlkHkd : $("#jtjlkHkd").val(),
			//zzjgName : $("#jtjlkTddw").val(),
			jtjlkZt : v1,
			jtjlkMszt: v3,
			jtjlkCpjg:v2
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

function DelJl(_id) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "JtjlkDel",
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

function loadDataExp(){
	var ids = new Array();
	$("input[name=subBox]").each(function() {  
	if ($(this).attr("checked")) {
		ids.push($(this).val());
	}
	});
	if(ids.length<1){
		alert("请选择要导出的简历！");
		return;
	}
	$.ajax({
		type : 'POST',
		url : "LoadBdwAndXsdwjlkLevel3DataExp",
		data : {"ids":ids.join(",")},
		dataType : "json",
		success : function(jtjlks) {
			console.log(jtjlks);
			window.open("uppics/"+jtjlks.msgBody);
		}
	});
}

function CollectionJl(id) {
	if (confirm("您确定要收藏该简历吗?")) {
		$.ajax({
			type : 'POST',
			url : "CollectionJtjlk",
			data : {
				jtjlkId : id
			},
			dataType : "text",
			success : function(_result) {
				if (_result.indexOf("error") != -1) {
					alert("该简历已在流程中，简历状态为'" + unescape(_result.split("_")[1])
							+ "'！");
				} else {
					alert('收藏成功!');
					window.location.reload();
				}
			}
		});
	}
}

function formatLongDate(d){
	var date = new Date(d);
	return date.toLocaleString();
}