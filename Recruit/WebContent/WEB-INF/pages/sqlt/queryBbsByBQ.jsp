<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/pagination/pagination.css" type="text/css"></link>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="resources/pagination/jquery.pagination.js"></script>
</head>
<body>
<div class="title"></div>

<div class="dataGrid">
<form action="BbsSave" method="post">
<table >
<tr>
<td>标签：<c:forEach items="${ltbqList}" var="map">
<a href="queryBbsByLtbq?ltbqName=${map.ltbqName}&&ltbqId=${map.ltbqId}">${map.ltbqName}</a>
<!-- <a href="javascript:void(0);" onclick="loadData('${map.ltbqName}')">${map.ltbqName}</a> -->
</c:forEach></td>
</tr>
<!-- <tr><td> -->
<!--   <textarea rows="3" cols="170" name="bbsNr"> -->
<!--   发表您的论坛主要内容。。。。。。。。。。 -->
<!--   </textarea> -->
<!-- </td></tr> -->
<!-- <tr ><td rowspan="4" colspan="4" align="right"> -->
<!--   <input type="submit" value="发表"> -->
<!-- </td></tr> -->
 </table>
  </form>
    <div class="dataGrid">
		<table>
			<caption>
				查询结果 <span class="note"></span>
			</caption>
			<tbody id="tbList"></tbody>
		</table>
<table>
	<c:forEach items="${bbsList}" var="bbs">
   <tr>
   <td>${bbs.bbsFbr }:${bbs.bbsNr}</td>
   <td align="right"><a href="delBbs?bbsId=${bbs.bbsId}">删除</a></td>
   </tr>
   <tr>
   <td>${bbs.fbsj}</td>
   </tr>
   <c:forEach items="${reviewList}" var="map">
   <c:if test="${bbs.bbsId eq map.bbsId}">
	<tr>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;${map. reviewPlr}:${map. reviewNr}</td>
	<td align="right">${map. plsj}&nbsp;&nbsp;&nbsp;&nbsp;<a href="delReview?reviewId=${map.reviewId}">删除</a></td>
	</tr>
	</c:if>
	</c:forEach>
   <tr>
   <td>
   </tr>
   </c:forEach>
</table>

	</div>
</div>

 
	
	
</body>
</html>
