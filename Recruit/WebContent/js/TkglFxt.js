
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
		url : "LoadTkglFxtData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			stbh : $("#stbh").val(),
			stlx : $("#stlx").val(),
			sttg : $("#sttg").val()
		},
		dataType : "json",
		success : function(tkgl) { 
			for ( var i = 0; i <= tkgl.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + tkgl[i].stbh + "</td>";
				strHtml += "<td class='alignCenter'>" + tkgl[i].stlx + "</td>";
				strHtml += "<td>" + tkgl[i].sttg + "</td>";
				strHtml += "<td class='alignCenter'>" + tkgl[i].stda + "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"updTkglFxt('"
						+ tkgl[i].id + "');\"";
				strHtml += "	 	class='inputButton' value='编辑' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delTkglFxt('"
						+ tkgl[i].id + "');\"";
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
	if($("#zhmmfxt").val() == '' || $("#zzjgZhmm").val() != $("#zhmmfxt").val()){
		var submit = function (v, h, f) {
			if (f.pswAgain == '') {
				$.jBox.tip("请输入您的密码！");
				return false;
			}else{
				window.location = "zhmmfxtSession?zhmmfxt="+h.find("#pswAgain").val();
				if($("#zzjgZhmm").val() == $("#zhmmfxt").val()){
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
	if($("#zzjgZhmm").val() == $("#zhmmfxt").val()){
		document.getElementById('showWork').style.display = "";
		loadData();
	}
});

function loadData() {
	$.ajax({
		type : 'POST',
		url : "LoadTkglFxtDataCount",
		data : {
			stbh : $("#stbh").val(),
			stlx : $("#stlx").val(),
			sttg : $("#sttg").val()
			
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
	window.location = "TkglFxtAdd";
}
function updTkglFxt(_Id) {
	window.location = "TkglFxtAdd?id=" + _Id;
}

function delTkglFxt(_Id) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "delTkglFxt",
			data : {
				id : _Id
			},
			dataType : "json",
			success : window.location.reload()
		});
	}
}
