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
    
    <title>社区-博文移动分组</title>
    
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
function addLtbqArticle(){
	var item = $(":radio:checked");
	 var len=item.length;
	 if(len<=0){
	  alert("请您选择一个标签！");
	 }else{
		 document.ff.action="addLtbqArticle";
			document.ff.method="post";
			document.ff.submit();
	 }
}

</script>
  </head>
  
  <body>
 <div class="title">当前位置:博文管理>移动分组</div> 
 <div class="editBlock">
   <form id="ff" name="ff">
   <input type="hidden" name="articleId" value="${articleId}">
   <table>
   <c:forEach items="${ltbqList}" var="map">
   
   <tr>
   <td width="30px;"></td>
   <td><input type="radio" name="ltbqId" value="${map.ltbqId}"/> ${map.ltbqName}</td>
   </tr>
   </c:forEach>
   <tr>
   <td width="30px;"></td>
   <td><input type="button" class="inputButton" value="确定" onclick="addLtbqArticle();"/></td>
   </tr>
   </table>
   
   
   </form>
</div>
  </body>
</html>
