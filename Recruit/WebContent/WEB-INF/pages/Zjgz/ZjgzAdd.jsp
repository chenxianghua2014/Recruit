<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
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
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript"
	src="resources/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	function ZjgzSava(){
		if(Validator.Validate(document.getElementById('ff'),3) ){
		document.ff.action="ZjgzSava";
		document.ff.method="post";
		document.ff.submit();
		}
	}
	
	
	</script>
</head>
<body>
	<div class="title">当前位置:测评>题库卷库管理>组卷规则管理</div>
	<div class="editBlock">
		<form name="ff" id="ff">
			<input type="hidden" id="zjgzId" name="zjgzId"
				value="${ zjgz.zjgzId }" />
			<table>
				<tr>
					<td colspan="2" class="subtitle">编辑考试现场信息</td>
				</tr>
				<tbody>
					<tr>
						<th width="150"><span class="warning">*</span>规则编号</th>
						<td><input type="text" id="ZJGZ_BH" name="zjgzBh"
							value="${ zjgz.zjgzBh }" dataType="Require"  msg="必填"/></td>
					</tr> 
					<tr>
						<th align="right">优先等级</th>
						<td><input type="radio" id="rdo1" name="zjgzYxdj" value="一级" <c:out value="${zjgz.zjgzYxdj== '一级'?'checked':''}"/>/>一级
						<input type="radio" id="rdoN2" name="zjgzYxdj"value="二级" <c:out value="${zjgz.zjgzYxdj== '二级'?'checked':''}"/>/>二级
						<input type="radio" id="rdoN3" name="zjgzYxdj"value="三级" <c:out value="${zjgz.zjgzYxdj== '三级'?'checked':''}"/>/>三级
						<input type="radio" id="rdoN4" name="zjgzYxdj"value="四级" <c:out value="${zjgz.zjgzYxdj== '四级'?'checked':''}"/>/>四级
						<input type="radio" id="rdoN5" name="zjgzYxdj"value="五级" dataType="Group"  msg="必须选定一个优先等级"<c:out value="${zjgz.zjgzYxdj== '五级'?'checked':''}"/>/>五级
							</td>
					</tr>
					<tr>
						<th align="right">适用考试类型</th>
						<td><input type="radio" id="rdo1" name="zjgzSykslx" value="管理岗"<c:out value="${zjgz.zjgzSykslx== '管理岗'?'checked':''}"/>/>管理岗<input type="radio" id="rdoN2" name="zjgzSykslx"
							value="技术岗" dataType="Group"  msg="必须选定一个考试类型" <c:out value="${zjgz.zjgzSykslx== '技术岗'?'checked':''}"/>/>技术岗</td>
					</tr>
					<tr>
						<th><span class="warning">*</span>规则内容</th>
						<td><script id="zjgzGznr" name="zjgzGznr" type="text/plain"
				style="width:100%;height:100px;" dataType="Require"  msg="必填"></script>
				</td>
					</tr>
					
				<tr>
				<th></th>
				<td>
					<input type="button" class="inputButton" value="确定" onclick="ZjgzSava();"/> &nbsp;&nbsp; 
					<input type="button" class="inputButton" value="取消" onclick="history.back()" />
				</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	var editor;
	$(document).ready(function() {
		editor = UE.getEditor('zjgzGznr');
		editor.addListener("ready", function() {
			editor.setContent('${zjgz.zjgzGznr }');
		});
	});
</script>
</html>