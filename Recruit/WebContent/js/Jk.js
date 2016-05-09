
var pageSize = 10;
var dataCount = 0;
function pageselectCallback(page_index, jq) {
	var strHtml = "";
	page_index += 1; 
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
		url : "JKData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			jkName : $("#jkName").val()
		},
		dataType : "json",
		success : function(jkList) {
			for ( var i = 0; i <= jkList.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + (i+1) + "</td>";
				strHtml += "<td>" + jkList[i].jkName + "</td>";
				strHtml += "<td>" + jkList[i].tjsj + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"editJk('"
						+ jkList[i].jkId + "');\"";
				strHtml += "	 	class='inputButton' value='修改' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delJk('"
						+ jkList[i].jkId + "');\"";
				strHtml += "	 	class='inputButton' value='删除' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"scsj('"
						+ jkList[i].jkId + "');\"";
				strHtml += "	 	class='inputButton' value='生成试卷' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"JkglView('"
						+ jkList[i].jkId + "');\"";
				strHtml += "	 	class='inputButton' value='查看试卷' />";
				strHtml += "</td>";
				strHtml += "</tr>";
			}
			$("#tbList").html(strHtml);
		}
	});
	return false;
}
function checkLtbqArticle(articleId){
	window.location = "checkLtbqArticle?articleId=" + articleId;
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
	document.getElementById('showWork').style.display = "none";
	var html = "<div style='padding:10px;'>输入您的密码：<input type='password' id='pswAgain' name='pswAgain' /></div>";
	if($("#ejmm").val() == '' || $("#zzjgEjmm").val() != $("#ejmm").val()){
		var submit = function (v, h, f) {
			if (f.pswAgain == '') {
				$.jBox.tip("请输入您的密码！");
				return false;
			}else{
				window.location = "ejmmSession?ejmm="+h.find("#pswAgain").val();
				if($("#zzjgEjmm").val() == $("#ejmm").val()){
					document.getElementById('showWork').style.display = "";
					loadData();
					return true;
				}
			}
			if($("#zzjgEjmm").val() == h.find("#pswAgain").val()){
				$.jBox.tip("恭喜您，密码正确！");
				document.getElementById('showWork').style.display = "";
				loadData();
				return true;
			}else{
				document.getElementById('showWork').style.display = "none";
				$.jBox.tip("密码输入错误！");
				return false;
			}
		};
		$.jBox(html, { title: "请输入您的密码", submit: submit });
	}
	if($("#zzjgEjmm").val() == $("#ejmm").val()){
		document.getElementById('showWork').style.display = "";
		loadData();
	}
});
function loadData() {
	$.ajax({
		type : 'POST',
		url : "JKDataCount",
		data : {
			jkName : $("#jkName").val()
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

function JkAdd() {
	window.location = "JkAdd";
}
function editJk(JkId) {
	window.location = "JkAdd?jkId=" + JkId;
}
function scsj(JkId) {
	if (confirm("您确定要生成试卷么?")) {
		$.jBox.tip("正在生成试卷，请耐心等待一下！", 'loading');
		window.location = "scsjJkgl?jkId=" + JkId;
		window.setTimeout(function () { 
			$.jBox.tip('已成功生成试卷。', 'success'); 
			}, 60000);
	}
	
}
function JkglView(JkId) {
	window.location = "JkglView?jkId=" + JkId;
}
function delJk(JkId) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "delJk",
			data : {
				jkId : JkId
			},
			dataType : "json",
			success : window.location.reload()
		});
	}
}