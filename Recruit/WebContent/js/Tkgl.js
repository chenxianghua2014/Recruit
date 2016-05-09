
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
		url : "LoadTkglData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			tkglStbh : $("#tkglStbh").val(),
			tkglStlx : $("#tkglStlx").val(),
			tkglSttg : $("#tkglSttg").val()
		},
		dataType : "json",
		success : function(tkgl) { 
			for ( var i = 0; i <= tkgl.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + tkgl[i].tkglStbh + "</td>";
				strHtml += "<td class='alignCenter'>" + tkgl[i].tkglStlx + "</td>";
				strHtml += "<td>" + tkgl[i].tkglSttg + "</td>";
				strHtml += "<td class='alignCenter'>" + tkgl[i].tkglStda + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"updTkgl('"
						+ tkgl[i].tkglId + "');\"";
				strHtml += "	 	class='inputButton' value='编辑' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delTkgl('"
						+ tkgl[i].tkglId + "');\"";
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
	document.getElementById('showWork').style.display = "none";
	var html = "<div style='padding:10px;'>输入您的密码：<input type='password' id='pswAgain' name='pswAgain' /></div>";
	if($("#zhmm").val() == '' || $("#zzjgZhmm").val() != $("#zhmm").val()){
		var submit = function (v, h, f) {
			if (f.pswAgain == '') {
				$.jBox.tip("请输入您的密码！");
				return false;
			}else{
				window.location = "zhmmSession?zhmm="+h.find("#pswAgain").val();
				if($("#zzjgZhmm").val() == $("#zhmm").val()){
					document.getElementById('showWork').style.display = "";
					loadData();
					return true;
				}
			}
			if($("#zzjgZhmm").val() == h.find("#pswAgain").val()){
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
	if($("#zzjgZhmm").val() == $("#zhmm").val()){
		document.getElementById('showWork').style.display = "";
		loadData();
	}
});

function loadData() {
	$.ajax({
		type : 'POST',
		url : "LoadTkglDataCount",
		data : {
			tkglStbh : $("#tkglStbh").val(),
			tkglStlx : $("#tkglStlx").val(),
			tkglSttg : $("#tkglSttg").val()
			
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
function AddTkgl() {
	window.location = "TkglAdd";
}
function updTkgl(_Id) {
	window.location = "TkglAdd?tkglId=" + _Id;
}

function delTkgl(_Id) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "delTkgl",
			data : {
				tkglId : _Id
			},
			dataType : "json",
			success : window.location.reload()
		});
	}
}
