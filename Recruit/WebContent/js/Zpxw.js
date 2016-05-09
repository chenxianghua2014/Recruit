var pageSize = 10;
var dataCount = 0;
var pi, pijq;
function pageselectCallback(page_index, jq) {
	pi = page_index;
	pijq = jq;
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
		url : "LoadZpxwData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			keywords : $("#keywords").val()
		},
		dataType : "json",
		success : function(zpxxs) {
			//console.log(zpxxs);
			for (var i = 0; i <= zpxxs.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + zpxxs[i].zpxwTitle + "</td>";
				strHtml += "<td>" + zpxxs[i].zpxwFbsj + "</td>";

				strHtml += "<td class='alignCenter'>";
				
				if(zpxxs[i].zpxwTop==0){
					strHtml += "<input name='button' type='button' onclick=\"setTop('" + zpxxs[i].zpxwId + "',1, this.value);\") ";
					strHtml += "class='inputButton' value='取消置顶' />";
				}else{
					strHtml += "<input name='button' type='button' onclick=\"setTop('" + zpxxs[i].zpxwId + "',0, this.value);\") ";
					strHtml += "class='inputButton' value='置顶' />";
				}
				strHtml += "</td>";

				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"window.location.href='ZpxwContent?id=" + zpxxs[i].zpxwId + "'\")";
				strHtml += "	 	class='inputButton' value='查看' />";
				strHtml += "</td>";

				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"ZpxwDel('" + zpxxs[i].zpxwId + "');\"";
				strHtml += "	 	class='inputButton' value='删除' />";
				strHtml += "</td>";
			}
			$("#tbList").html(strHtml);
		}
	});
	return false;
}

function setTop(_id, _status, ve){
	var html = "<div style=\"padding-top: 8px;\">&nbsp;有效时间至：<input type='text' id='ltime' name='ltime' class=\"Wdate\" onFocus=\"WdatePicker({isShowClear:false,readOnly:true})\" /></div>";
	if(ve=="取消置顶")
		html = "您确认要取消置顶？";
	var submit = function (v, h, f) {
	    if (f.ltime == '') {
	        $.jBox.tip("有效时间。", 'error', { focusId: "ltime" }); 
	        return false;
	    }else{
	    	if(ve!="置顶")
	    		f.ltime = "1970-00-01";
	    }
		$.ajax({
			type : 'GET',
			url : "setTop",
			data : {
				id : _id,
				status:_status,
				rtime:'',
				ltime:f.ltime
			},
			dataType : "json",
			success : function(_result) {
				if(_result.message=="success"){
					alert("操作成功！");
					$("#tbList").html("");
					pageselectCallback(pi, pijq);
				}
				else
					alert("操作失败！");
			}
		});
	    return true;
	};

	$.jBox(html, { title: ve, submit: submit, width: 220, height:100 });
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
		url : "LoadZpxwDataCount",
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

function AddZpxw() {
	window.location = "ZpxwAdd";
}

function ZpxwDel(_id) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "ZpxwDel",
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