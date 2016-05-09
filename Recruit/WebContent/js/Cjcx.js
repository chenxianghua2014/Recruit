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
				url : "CjcxData",
				data : {
					pageNum : page_index,
					pageSize : pageSize,
					currentPageSize : currentPageSize,
					ksxcglName : $("#ksxcglName").val(),
					ksxcglIdcard : $("#ksxcglIdcard").val(),
					ksxcglBkdw : $("#ksxcglBkdw").val(),
					ksxcglKkslx : $("#ksxcglKkslx").val()
				},
				dataType : "json",
				success : function(ksxcgl) {
					for ( var i = 0; i <= ksxcgl.length - 1; i++) {
						strHtml += "<tr>";
						if ($("#zzjgSjdw").val() == null
								|| $("#zzjgSjdw").val() == ""
								|| $("#zzjgSjdw").val() == 'test001') {
							strHtml += "<td>" + ksxcgl[i].dwdm + "</td>";
							strHtml += "<td>" + ksxcgl[i].ksxcglBkdw + "</td>";
						}
						strHtml += "<td>" + ksxcgl[i].ksxcglName + "</td>";
						strHtml += "<td>" + ksxcgl[i].sex + "</td>";
						strHtml += "<td>" + ksxcgl[i].ksxcglIdcard + "</td>";
						strHtml += "<td>" + ksxcgl[i].birthday + "</td>";
						strHtml += "<td>" + ksxcgl[i].byyx + "</td>";
						strHtml += "<td>" + ksxcgl[i].zyl + "</td>";
						strHtml += "<td>" + ksxcgl[i].xl + "</td>";
						if (ksxcgl[i].ksxcglKscjYycj == null
								&& ksxcgl[i].ksxcglKscjGlg != null) {
							strHtml += "<td>" + ksxcgl[i].ksxcglKscjGlg
									+ "</td>";
						} else {
							if (ksxcgl[i].glgcj == null) {
								strHtml += "<td>" + "----" + "</td>";
							} else {
								if ($("#zzjgSjdw").val() == null || $("#zzjgSjdw").val() == "") 
									strHtml += "<td>行测("+ksxcgl[i].ksxcglKscjGlg+")<br/>英语("+ksxcgl[i].ksxcglKscjYycj+")<br/>总分(" + ksxcgl[i].glgcj + ")</td>";
								else
									strHtml += "<td>" + ksxcgl[i].glgcj + "</td>";
							}
						}
						if (ksxcgl[i].ksxcglJsgYycj == null
								&& ksxcgl[i].ksxcglKscjJsg != null) {
							strHtml += "<td>" + ksxcgl[i].ksxcglKscjJsg
									+ "</td>";
						} else {
							if (ksxcgl[i].jsgcj == null) {
								strHtml += "<td>" + "----" + "</td>";
							} else {
								if ($("#zzjgSjdw").val() == null || $("#zzjgSjdw").val() == "") 
									strHtml += "<td>行测("+ksxcgl[i].ksxcglKscjJsg+")<br/>英语("+ksxcgl[i].ksxcglJsgYycj+")<br/>总分(" + ksxcgl[i].jsgcj + ")</td>";
								else
									strHtml += "<td>" + ksxcgl[i].jsgcj + "</td>";
							}
						}
						strHtml += "<td><a href='javascript:void(0);'onclick=\"showCharts('"
								+ ksxcgl[i].ksxcglIdcard
								+ "');\">"
								+ "查看性格测试报告"
								+ "</a></td>";
						if ($("#zzjgSjdw").val() == null
								|| $("#zzjgSjdw").val() == "") {
							strHtml += "<td>" + ksxcgl[i].kssj + "</td>";
							strHtml += "<td>" + ksxcgl[i].kcname + "</td>";
							strHtml += "<td class='alignCenter'><input name='button'";
							strHtml += "		type='button' onclick=\"delKsxcgl('" + ksxcgl[i].ksxcglId + "');\"";
							strHtml += "	 	class='inputButton' value='删除' />";
							strHtml += "</td>";

						}

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
	$("#bkdw").val($("#ksxcglBkdw").val());
	$.ajax({
		type : 'POST',
		url : "CjcxCount",
		data : {
			ksxcglName : $("#ksxcglName").val(),
			ksxcglIdcard : $("#ksxcglIdcard").val(),
			ksxcglBkdw : $("#ksxcglBkdw").val(),
			ksxcglKkslx : $("#ksxcglKkslx").val()
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
function AddKsxcgl() {
	window.location = "KsxcglAdd";
}
function KsxcglAdd(_Id) {
	window.location = "KsxcglAdd?ksxcglId=" + _Id;
}
function showCharts(sfzh) {
	window.location = "ShowCharts?sfzh=" + sfzh;
}
function delKsxcgl(_Id) {
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
					url : "DelKsxcgl",
					data : {
						ksxcglId : _Id
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