<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base target="_self"> 
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet" href="resources/jBox/Skins/Blue/jbox.css"/>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript">
function importTkglThree(){
	if(Validator.Validate(document.getElementById('ff'),3) ){
		$.jBox.tip("正在批量导入试题，请耐心等待一下！", 'loading');
		// 模拟2秒后完成操作
			document.ff.action="importTkglThree";
			document.ff.method="post";
			document.ff.submit();
		window.setTimeout(function () { 
			$.jBox.tip('导入已完成。', 'success'); }, 50000);
		}
}

</script>
</head>

<body>
<div class="title">当前位置:测评>综合题题库管理>三选项批量导入</div>
	<div class="editBlock">
	<form enctype="multipart/form-data" name="ff" id="ff">
		<table>
				<tr>
					<th><span class="warning">*</span>文本选择：</th>
					<td><input type="file" name="excel" style="width:450px; height:24px" dataType="Require"  msg="必选"></td>
				</tr>
				<tr>
					<th>具体要求：</th>
					<td>
						<span style="color: red;">三选项试题导入！</span>
						<p>
							1、请按模板要求填写。<br /> 2、Excel文件中不能存在公式。<br />
							3、每次最多导入999行，文件大小限制在1M以内。<br /> 4、导入速度和每次导入的数据量有关，请耐心等待。
						</p>
						<p class ="excelModel">5、<a href="<%=path%>/excelModel/ThreeChooseTkgl.xls">三选项试题导入格式.xls</a></p>
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
					<input class="inputButton" type="button" value="导入" onclick="importTkglThree();">
					<input class="inputButton" type="button" value="返回" onclick="window.location.href='Tkgl'">
					</td>
				</tr>
		</table>
	</form>
	</div>
</body>
<script type="text/javascript">
</script>
</html>
