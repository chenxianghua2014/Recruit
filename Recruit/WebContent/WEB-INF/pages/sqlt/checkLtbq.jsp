<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>社区-论坛移动分组</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
function addLtbqBbs(){
	var item = $(":radio:checked");
	 var len=item.length;
	 if(len<=0){
	  alert("请您选择一个标签！");
	 }else{
		 document.ff.action="addLtbqBbs";
			document.ff.method="post";
			document.ff.submit();
	 }
}

</script>
  </head>
  
  <body>
<div class="title">当前位置:科工论坛>论坛标签分组</div> 
<div class="editBlock">
   <form name="ff" id="ff">
   <input type="hidden" name="bbsId" value="${bbsId}">
   <table>
   <c:forEach items="${ltbqList}" var="map">
   
   <tr>
   <td width="30px;"></td>
   <td><input type="radio" name="ltbqId" value="${map.ltbqId}"/> ${map.ltbqName}</td>
   </tr>
   </c:forEach>
   <tr>
   <td width="30px;"></td>
   <td><input type="button" class="inputButton" value="确定" onclick="addLtbqBbs();"/></td>
   </tr>
   </table>
   
   
   </form>
</div>
  </body>
</html>
