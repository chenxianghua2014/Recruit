<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="title">当前位置:题库卷库管理>测评费用管理(会员)下载</div>
	<div class="editBlock">
		<table>
			<tr>
				<th>下载地址：</th>
				<td><a href="${uri}">测评费用管理(会员).pdf</a></td>
			</tr>
			<tr>
				<th></th>
				<td align="left">
					<input type="button" class="inputButton" value="取消"onclick="history.back()" />
				</td>
			</tr>
		</table>

	</div>
   
  </body>
</html>