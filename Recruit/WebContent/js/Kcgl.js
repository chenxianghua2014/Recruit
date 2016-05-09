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
		url : "KcglData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			kcglName: $("#kcglName").val(),
			kcglKsrq: $("#kcglKsrq").val(),
			kcglKssjStart: $("#kcglKssjStart").val(),
			kcglKssjEnd: $("#kcglKssjEnd").val(),
			kcglKczt: $("#kcglKczt").val()
		},
		dataType : "json",
		success : function(listKcgl) { 
			for ( var i = 0; i <= listKcgl.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + listKcgl[i].kcglName + "</td>";
				strHtml += "<td>" + listKcgl[i].kcglDz + "</td>";
				strHtml += "<td>" + listKcgl[i].kcglKsrq + "</td>";
				strHtml += "<td>" + listKcgl[i].kcglKssjStart+"--"+listKcgl[i].kcglKssjEnd+ "</td>";
				strHtml += "<td>" + listKcgl[i].kcglKcrl + "</td>";
				strHtml += "<td>" + listKcgl[i].kcglYbms + "</td>";
				strHtml += "<td>" + listKcgl[i].kcglSyrl + "</td>";
				strHtml += "<td>" + listKcgl[i].kcglKczt + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"updKcgl('"
						+ listKcgl[i].kcglId + "');\"";
				strHtml += "	 	class='inputButton' value='修改' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delKcgl('"
						+ listKcgl[i].kcglId + "');\"";
				strHtml += "	 	class='inputButton' value='删除' />";
				strHtml += "</td>";
//				strHtml += "<td class='alignCenter'><input name='button'";
//				strHtml += "		type='button' onclick=\"addBmgl('"
//						+ listKcgl[i].kcglId + "');\"";
//				strHtml += "	 	class='inputButton' value='报名' />";
//				strHtml += "</td>";
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
function addBmgl(kcglId) {
	window.location = "addBmgl?zzjgId="+kcglId;
}
function loadData() {
	$.ajax({
		type : 'POST',
		url : "KcglDataCount",
		data : {
			kcglName: $("#kcglName").val(),
			kcglKsrq: $("#kcglKsrq").val(),
			kcglKssjStart: $("#kcglKssjStart").val(),
			kcglKssjEnd: $("#kcglKssjEnd").val(),
			kcglKczt: $("#kcglKczt").val()
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
function addKcgl() {
	window.location = "addKcgl";
}
function updKcgl(kcglId) {
	window.location = "addKcgl?kcglId="+kcglId;
}
function delKcgl(kcglId) {
	var html = "<div style='padding:10px;'>输入您的密码：<input type='password' id='pswAgain' name='pswAgain' /></div>";
	var submit = function(v, h, f) {
		if (f.pswAgain == '') {
			alert("请输入您的密码！");
			return false;
		}
		if ($("#zzjgEjmm").val() == h.find("#pswAgain").val()) {
			$.jBox.tip("恭喜您，密码正确！");
			if (confirm("您确定要删除该条记录吗?")) {
				$.ajax({
					type : 'GET',
					url : "delKcgl",
					data : {
						kcglId : kcglId
					},
					dataType : "json",
					success : window.location.reload()
				});
			}
			return true;
		} else {
			alert("密码输入错误！");
			return false;
		}
	};
	$.jBox(html, {
		title : "请输入您的密码",
		submit : submit
	});
}
