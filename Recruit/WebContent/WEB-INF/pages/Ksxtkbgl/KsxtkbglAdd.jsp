<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<title></title>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript"
	src="resources/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript">
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
</script>
</head>
<body>
	<div class="title">当前位置:考试管理>考试系统开闭管理</div>
	<div class="editBlock">
		<form name="ff" id="ff">
			<input type="hidden" id="ksxtkbglId" name="ksxtkbglId"
				value="${ ksxtkbgl.ksxtkbglId }" />
				<table>
					<tr>
						<th><span class="warning">*</span>考试开始时间：</th>
						<td><input type="text" id="ksxtkbglStarttime" name="ksxtkbglStarttime" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							value="${ ksxtkbgl.ksxtkbglStarttime }"  dataType="Require"  msg="必填"/>
						</td>
					</tr>
					<tr>
						<th><span class="warning">*</span>考试结束时间：</th>
						<td><input type="text" id="ksxtkbglEndtime"name="ksxtkbglEndtime"onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							value="${ ksxtkbgl.ksxtkbglEndtime }"  dataType="Require"  msg="必填"/></td>
					</tr>
					<tr>
						<th></th>
						<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="inputButton" value="保存" onclick="KsxtkbglSava();"/>
							<input type="button" class="inputButton" value="取消" onclick="history.back()" />
						</td>
					</tr>
				</table>
		</form>
	</div>
</body>
</html>
