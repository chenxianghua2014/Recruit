var pageSize = 10;
var dataCount = 0;

function pageselectCallback(page_index) {
	page_index += 1;
	var currentPageSize = (dataCount < page_index * pageSize ? dataCount
			- (page_index * pageSize - 10) : pageSize);
	$("#count").html(dataCount);
	if (dataCount == 0)
		$("#start").html(0);
	else
		$("#start").html(page_index * 10 - 9);
	$("#end").html(currentPageSize + page_index * 10 - 10);
	var strHtml = "";
	var dwid="";
	$.ajax({
		type : 'POST',
		url : "InformationCenterData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			ZPXW_CONTENT:$("#zpnr").val(),
			ZZJG_ID:dwid
		},
		dataType : "json",
		success : function(jtjlks) {
			for(var j=0; j<jtjlks.length; j++){
				if(jtjlks[j].zpxwTop!="top")continue;
				//console.log(jtjlks[j]);
				var date = new Date(jtjlks[j].zpxwAddtime*1);
				var theDate = date.Format("yyyy-MM-dd");
				strHtml += "<tr style=\"color:red;\"><td width=\"300\"><a  style=\"color:red;\" href='ViewNews?id="+jtjlks[j].zpxwId+"'>"+jtjlks[j].zpxwTitle+"</a></td><td width=\"220\">"+jtjlks[j].zpxwAddcompany+"</td><td width=\"100\">"+theDate+"</td></tr>";
			}
			for(var i=0; i<jtjlks.length; i++){
				if(jtjlks[i].zpxwTop=="top")continue;
				//console.log(jtjlks[i]);
				var date = new Date(jtjlks[i].zpxwAddtime*1);
				var theDate = date.Format("yyyy-MM-dd");
				strHtml += "<tr><td width=\"300\"><a href='ViewNews?id="+jtjlks[i].zpxwId+"'>"+jtjlks[i].zpxwTitle+"</a></td><td width=\"220\">"+jtjlks[i].zpxwAddcompany+"</td><td width=\"100\">"+theDate+"</td></tr>";
			}
			$("#tbList").html(strHtml);
		}
	});
	return false;
}

Date.prototype.Format = function(fmt) 
{
	var o = { 
	 "M+" : this.getMonth()+1,                 //月份 
	 "d+" : this.getDate(),                    //日 
	 "h+" : this.getHours(),                   //小时 
	 "m+" : this.getMinutes(),                 //分 
	 "s+" : this.getSeconds(),                 //秒 
	 "q+" : Math.floor((this.getMonth()+3)/3), //季度 
	 "S"  : this.getMilliseconds()             //毫秒 
	}; 
	if(/(y+)/.test(fmt)) 
	 fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	for(var k in o) 
	 if(new RegExp("("+ k +")").test(fmt)) 
	fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
	return fmt; 
}

function getOptionsFromForm() {
	var opt = {
		callback : pageselectCallback
	};
	opt.prev_text = "上一页";
	opt.next_text = "下一页";
	return opt;
}

//页面加载
$(document).ready(function() {
	loadData();
});
  
function loadData() {
	var dwid="";
	$.post("InformationCenterCount",{
			currentPageSize:pageSize,
			pageNum:0,
			pageSize:pageSize,
			ZPXW_CONTENT:$("#zpnr").val(),
			ZZJG_ID:dwid
		},function(data){
			dataCount = data;
			var optInit = getOptionsFromForm();
			$("#Pagination").pagination(dataCount, optInit);
			$("#setoptions").click(function() {
				var opt = getOptionsFromForm();
				$("#Pagination").pagination(dataCount, opt);
			});
		});
}