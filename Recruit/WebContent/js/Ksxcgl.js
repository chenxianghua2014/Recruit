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
		url : "LoadKsxcglData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			ksxcglName : $("#ksxcglName").val(),
			ksxcglIdcard : $("#ksxcglIdcard").val(),
			ksxcglBkdw : $("#ksxcglBkdw").val(),
			ksxcglKkslx : $("#ksxcglKkslx").val(),
			ksxcglKc : $("#ksxcglKc").val()
		},
		dataType : "json",
		success : function(ksxcgl) { 
			for ( var i = 0; i <= ksxcgl.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + ksxcgl[i].ksxcglName + "</td>";
				strHtml += "<td>" + ksxcgl[i].ksxcglIdcard + "</td>";
				strHtml += "<td>" + ksxcgl[i].ksxcglBkdw + "</td>";
				strHtml += "<td>" + ksxcgl[i].ksxcglKkslx + "</td>";
				strHtml += "<td>" + ksxcgl[i].ksxcglKc + "</td>";
				strHtml += "<td>" + ksxcgl[i].ksxcglWjqk + "</td>";
				strHtml += "<td>" + ksxcgl[i].ksxcglTszg + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"updKsxcgl('"
						+ ksxcgl[i].ksxcglId + "');\"";
				strHtml += "	 	class='inputButton' value='修改' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delKsxcgl('"
						+ ksxcgl[i].ksxcglId + "');\"";
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
		url : "LoadKsxcglDataCount",
		data : {
			ksxcglName : $("#ksxcglName").val(),
			ksxcglIdcard : $("#ksxcglIdcard").val(),
			ksxcglBkdw : $("#ksxcglBkdw").val(),
			ksxcglKkslx : $("#ksxcglKkslx").val(),
			ksxcglKc : $("#ksxcglKc").val()
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
function updKsxcgl(ksxcglId) {
	window.location = "KsxcglAdd?ksxcglId=" + ksxcglId;
}

function delKsxcgl(_Id) {
	var htmlStr = "<div style='padding:10px;'>请输入密码：<input type='text' id='erjimima' name='erjimima' /></div>";
	$.jBox(htmlStr, { title: "二级密码", submit: function (v, h, f) {
	    if (f.erjimima == '') {
	        return false;
	    }else{
	    	$.ajax({
				type : 'POST',
				url : "checkmm",
				data : {
					mm : f.erjimima
				},
				dataType : "json",
				success : function(d){
					if(d==true){
						$.ajax({
							type : 'GET',
							url : "DelKsxcgl",
							data : {
								ksxcglId : _Id
							},
							dataType : "json",
							success :  function(msg){
								alert('删除成功！');
								window.location.reload();
							}
						});
					}
				}
			});
		    return true;
	    }
	}});

}
