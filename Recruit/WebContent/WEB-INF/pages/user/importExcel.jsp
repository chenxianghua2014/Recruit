<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="java.sql.*,java.io.*,java.lang.*,java.util.*,java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet" href="resources/jBox/Skins/Blue/jbox.css"/>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript">
function importExcelDemo(){
	if(Validator.Validate(document.getElementById('ff'),3) ){
		$.jBox.tip("正在批量导入组织机构，请耐心等待一下！", 'loading');
		// 模拟2秒后完成操作
			document.ff.action="importExcelDemo";
			document.ff.method="post";
			document.ff.submit();
		window.setTimeout(function () { 
			$.jBox.tip('导入已完成。', 'success'); 
			}, 10000);
		}
}

</script>
</head>
<body>
	<div class="title">当前位置:账户管理>集团用户管理>批量导入组织机构</div>
	<div class="editBlock">
		<form enctype="multipart/form-data" name="ff" id="ff">
			<table>
				<tr>
					<th><span class="warning">*</span>上级单位：</th>
					<td><select name="zzjgSjdw" dataType="Require"  msg="必须选择上级单位">
							<option value="">--请选择--</option>
							<option value="test001">二级单位</option>
							<c:forEach items="${userFOList}" var="map">
								<option value="${map.zzjgId}">${map.zzjgDwmc}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th><span class="warning">*</span>文本选择：</th>
					<td><input type="file" name="excel" style="width:450px; height:24px" dataType="Require"  msg="必选"></td>
				</tr>
				<tr>
					<th>具体要求：</th>
					<td>
						<p>
							1、请按模板要求填写。<br /> 2、Excel文件中不能存在公式。<br />
							3、每次最多导入999行，文件大小限制在1M以内。<br /> 4、导入速度和每次导入的数据量有关，请耐心等待。
						</p>
						<p class ="excelModel">5、<a href="<%=path%>/excelModel/zzjgImportModel.xls">组织机构导入模板.xls</a></p>
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
					<input class="inputButton" type="button" value="导入" onclick="importExcelDemo();">
					<input class="inputButton" type="button" value="返回" onclick="window.location.href='queryZzjgFO'">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div class="editBlock">
	<table style="color:#CC0000;">
	<c:if test="${!empty list}">
			<tr>
				<td style="color: red;">您导入的账号列表</td>
			</tr>
			<tr>
				<td>单位名称</td>
				<td>单位代码</td>
				<td>单位账号</td>
				<td>单位负责人</td>
				<td>联系人电话</td>
			</tr>
	</c:if>
		<c:forEach items="${list}" var="map" varStatus="status">
			<tr>
				<td>${map.zzjgDwmc }</td>
				<td>${map.zzjgDwdm }</td>
				<td>${map.zzjgDwzh }</td>
				<td>${map.zzjgDwfzr}</td>
				<td>${map.zzjgLxrdh}</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>
