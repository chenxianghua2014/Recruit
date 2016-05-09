<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
	<div class="editBlock">
		<c:choose>
			<c:when test="${msjl.size() == 0}">
				 未通知面试……
			</c:when>
			<c:otherwise>
				<table>
					<tbody>
						<c:forEach items="${msjl}" var="jl">
							<tr>
								<th width="60">面试官:</th>
								<td>${jl.msqglDetailedMsg}</td>
							</tr>
							<tr>
								<th width="60">面试类别:</th>
								<td>${jl.msqglDetailedMslb}</td>
							</tr>
							<tr>
								<th width="60">面试时间:</th>
								<td>${jl.msqglDetailedMssj}</td>
							</tr>
							<tr>
								<th width="60">评价:</th>
								<td>${jl.msqglDetailedPj}</td>
							</tr>
							<tr>
								<th width="60">结论:</th>
								<td>${jl.msqglDetailedJl}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
