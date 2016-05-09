<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link rel="stylesheet"
	href="resources/validator-0.7.1/jquery.validator.css" type="text/css"></link>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/jquery.validator.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<div class="title">当前位置:模版管理>编辑职位信息</div>
	<div class="editBlock">
		<form action="TxlSava" method="post" autocomplete="off"
			data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
			<input type="hidden" id="txlId" name="txlId" value="${ txl.txlId }" />
			<table>
				<tr>
					<td colspan="2" class="subtitle">编辑职位信息</td>
				</tr>
				<tbody>
					<tr>
						<th width="150"><span class="warning">*</span>联系人姓名</th>
						<td><input type="text" class="inputText" id="txlName"
							name="txlName" data-rule="required;" value="${ txl.txlName }" />
						</td>
					</tr>
					<tr>
						<th><span class="warning">*</span>联系人部门</th>
						<td><input type="text" class="inputText" id="txlDepartment"
							name="txlDepartment" data-rule="required;"
							value="${ txl.txlDepartment }" />
						</td>
					</tr>
					<tr>
						<th><span class="warning">*</span>联系人职位</th>
						<td><input type="text" class="inputText" id="txlPosition"
							name="txlPosition" data-rule="required;"
							value="${ txl.txlPosition }" />
						</td>
					</tr>
					<tr>
						<th><span class="warning">*</span>联系人电话</th>
						<td><input type="text" class="inputText" id="txlPhone"
							name="txlPhone" data-rule="required;mobile"
							value="${ txl.txlPhone }" /></td>
					</tr>
					<tr>
						<th><span class="warning">*</span>联系人邮箱</th>
						<td><input type="text" class="inputText" id="txlEmail"
							data-rule="required;email" name="txlEmail"
							value="${ txl.txlEmail }" />
						</td>
					</tr>
				</tbody>
				<tr>
					<th></th>
					<td><input type="submit" class="inputButton" value="确定" />
						&nbsp;&nbsp; <input type="button" class="inputButton" value="取消"
						onclick="history.back()" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
