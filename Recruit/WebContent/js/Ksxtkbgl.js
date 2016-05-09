
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
		url : "LoadKsxtkbglData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : (dataCount < page_index * pageSize ? dataCount
					- (page_index * pageSize - 10) : pageSize),
			keywords : ""
		},
		dataType : "json",
		success : function(ksxtkbgl) { 
			for ( var i = 0; i <= ksxtkbgl.length - 1; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + (i+1) + "</td>";
				strHtml += "<td>" + ksxtkbgl[i].ksxtkbglStarttime + "</td>";
				strHtml += "<td>" + ksxtkbgl[i].ksxtkbglEndtime + "</td>"; 
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"KsxtkbglAdd('"
						+ ksxtkbgl[i].ksxtkbglId + "');\"";
				strHtml += "	 	class='inputButton' value='修改' />";
				strHtml += "</td>";
				strHtml += "<td class='alignCenter'><input name='button'";
				strHtml += "		type='button' onclick=\"delKsxtkbgl('"
						+ ksxtkbgl[i].ksxtkbglId + "');\"";
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
	$.ajax({
		type : 'POST',
		url : "LoadKsxtkbglDataCount",
		data : {
			keywords : ""
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
});
function getOptionsFromForm() {
	var opt = {
		callback : pageselectCallback
	};
	opt.prev_text = "上一页";
	opt.next_text = "下一页";
	return opt;
}
function AddKsxtkbgl() {
	window.location = "KsxtkbglAdd";
}
function KsxtkbglAdd(_Id) {
	window.location = "KsxtkbglAdd?ksxtkbglId=" + _Id;
}

function delKsxtkbgl(_Id) {
	if (confirm("您确定要删除该条记录吗?")) {
		window.location = "delKsxtkbgl?ksxtkbglId=" + _Id;
	}
}
function KsxtkbglSava(){
	var now = new Date();
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var clock = year + "-";
    if(month < 10)
        clock += "0";
    clock += month + "-";
    if(day < 10)
        clock += "0";
    clock += day + " ";
    if(hh < 10)
        clock += "0";
    clock += hh + ":";
    if (mm < 10) clock += '0'; 
    clock += mm; 
    var pD=function(s){	
    	var dt=s.split(/ /);	
    	var d=dt[0].split(/-/);	
    	var t;	if(dt[1]){		
    		t=dt[1].split(/:/);		
    		t.push(0);		
    		t.push(0);	
    		}else{		
    			t=[0,0,0];	
    			}	
    	return new Date(d[0],d[1]-1,d[2],t[0],t[1],t[2]);
    	};
    var pS=function(clock){	
    	var Y=clock.getFullYear();	
    	var M=clock.getMonth()+1;	
    	(M<10)&&(M='0'+M);	
    	var D=clock.getDate();	
    	(D<10)&&(D='0'+D);	
    	var h=clock.getHours();	
    	(h<10)&&(h='0'+h);	
    	var m=clock.getMinutes();	
    	(m<10)&&(m='0'+m);	
    	var s=clock.getSeconds();	
    	(s<10)&&(s='0'+s);	
    	return Y+'-'+M+'-'+D+' '+h+':'+m+':'+s;
    	};
    
    var dateNow1 = pD(clock);
    var dateNow = pS(dateNow1);
	if(Validator.Validate(document.getElementById('ff'),3) ){
		if($("#ksxtkbglStarttime").val() < dateNow){
			alert("开始时间不能小于当前时间！");
			document.getElementById("ksxtkbglStarttime").value = "";
		}else if($("#ksxtkbglEndtime").val() <= $("#ksxtkbglStarttime").val()){
			alert("考试结束时间不能小于考试开始时间，请重新填写！");
			document.getElementById("ksxtkbglEndtime").value = "";
			document.getElementById("ksxtkbglStarttime").value = "";
		}else{
			document.ff.action="KsxtkbglSava";
			document.ff.method="post";
			document.ff.submit();
		}
	}
}