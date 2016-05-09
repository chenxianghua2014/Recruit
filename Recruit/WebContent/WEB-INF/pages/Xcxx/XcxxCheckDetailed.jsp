<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<div class="title">当前位置:宣传信息>单位简介审核意见</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" class="subtitle">简介信息审核意见</td>
				</tr>
				<tr>
					<td height="32">${CheckDetailed}</td>
				</tr>
				<tr>
					<td height="32" style="text-align: center;"><input
						type="button" class="inputButton" value="返回"
						onclick="history.back()" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
