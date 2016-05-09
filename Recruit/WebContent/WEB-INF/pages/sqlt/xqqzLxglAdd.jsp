<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>社区-兴趣圈子类型添加</title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/local/zh_CN.js"></script>
	<script type="text/javascript">
		function doPost(){
			if(Validator.Validate(document.getElementById('ff'),3) ){
			form1.action="xqqzLxglSave";
			form1.method="post";
			form1.submit();
			}
		}
	</script>
</head>
<body>
	<div class="title">当前位置:兴趣圈子类型管理>添加/修改类型</div>
	<div class="editBlock">
		<form name=form1 id="ff">
			<table>
				<tr>
					<th width="40%"><span class="warning">*</span>类型名称:</th>
					<td><input type="text" name="xqqzlxglLxmc"
						value="${xqqzLxgl.xqqzlxglLxmc}" dataType="Limit" min="1" max="5" msg="必填(5个字之内)" />
						<input type="hidden" name="xqqzlxglId" value="${xqqzLxgl.xqqzlxglId}" /></td>
				</tr>
				<tr>
					<td colspan="2" class="toolbar"><input type="button"
						class="inputButton" value="确定" onclick="doPost();"/> &nbsp;&nbsp; <input
						type="button" class="inputButton" value="取消"
						onclick="history.back()" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>
