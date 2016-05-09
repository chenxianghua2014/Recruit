<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>社区-兴趣圈子添加</title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="resources/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="resources/ueditor/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8"
	src="resources/ueditor/lang/zh-cn/zh-cn.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src=""></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript"
	src="resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#xqqzType").find("option[value='${xqqz.xqqzType}']").attr("selected",true);
});
function xqqzSave(){
	if(Validator.Validate(document.getElementById('ff'),3) ){
	document.ff.action="xqqzSave";
	document.ff.method="post";
	document.ff.submit();
	}
}
</script>
</head>
<body>
<div class="title">当前位置:兴趣圈子>信息添加/修改</div> 
<div class="editBlock">
	<form  name="ff" id="ff">
		<input type="hidden" id="xqqzId" name="xqqzId"
			value="${ xqqz.xqqzId }" />
		<table>
			<tr>
				<th width="150"><span class="warning">*</span>兴趣标题:</th>
				<td><input type="text" id="xqqzTitle" name="xqqzTitle"
					value="${ xqqz.xqqzTitle }"dataType="Limit" min="1" max="30" msg="必填(30个字之内)" />
				</td>
			</tr>
			<tr>
				<th width="150"><span class="warning">*</span>兴趣类型:</th>
				<td>
				<select id="xqqzType" name="xqqzType"dataType="Require" msg="请选择">
						<option value="" >--请选择--</option>
						<c:forEach items="${xqqzLxglList}" var="map">
							<option value="${map.xqqzlxglId}" >${map.xqqzlxglLxmc}</option>
						</c:forEach>
						</select>
				</td>
			</tr>
			<tr>
			<td colspan="2"><script id="xqqzNr" name="xqqzNr" type="text/plain"
				style="width:100%;height:300px;"></script></td>
			</tr>
			<tr>
			<td colspan="2" class="toolbar">
			<input type="button" class="inputButton" value="确定" onclick="xqqzSave();"/> &nbsp;&nbsp; 
			<input type="button" class="inputButton" value="取消" onclick="history.back()" />
			</td>
			</tr>
		</table>
	</form>
	</div>
</body>
<script type="text/javascript">
	var editor;
	$(document).ready(function() {
		editor = UE.getEditor('xqqzNr');
		editor.addListener("ready", function() {
			editor.setContent('${xqqz.xqqzNr }');
		});
	});
</script>
</html>
