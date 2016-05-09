<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>社区-博文管理添加</title>
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
	$("#articleType").find("option[value='${article.articleType}']").attr("selected",true);
});

function saveArticle(){
	if(Validator.Validate(document.getElementById('ff'),3) ){
	document.ff.action="ArticleSave";
	document.ff.method="post";
	document.ff.submit();
	}
}
</script>
</head>
<body>
<div class="title">当前位置:博文管理>编辑博文信息</div> 
<div class="editBlock">
	<form  name="ff" id="ff">
		<input type="hidden" id="articleId" name="articleId"
			value="${ article.articleId }" />
		<table boder="0">
			<tr>
				<th width="150"><span class="warning">*</span>博文标题:</th>
				<td><input type="text" id="articleTitle" name="articleTitle"
					value="${ article.articleTitle }" dataType="Limit" min="1" max="30" msg="必填(30个字之内)"/>
				</td>
			</tr>
			<tr>
				<th width="150"><span class="warning">*</span>作者:</th>
				<td><input type="text" id="articleAuthor" name="articleAuthor"
					value="${ article.articleAuthor }" dataType="Limit" min="1" max="5" msg="必填(5个字之内)"/>
				</td>
			</tr>
			<tr>
				<th width="150"><span class="warning">*</span>类型:</th>
				<td>
				<select name="articleType" id="articleType" dataType="Require"  msg="未选择类型" >
				<option value="">--请选择--</option>
				<option value="经验博文(员工)">经验博文(员工)</option>
				<option value="经验博文(新人)">经验博文(新人)</option>
				</select>
				</td>
			</tr>
			<tr>
			<td colspan="2"><script id="articleContent" name="articleContent" type="text/plain"
				style="width:100%;height:300px;"></script></td>
			</tr>
			<tr>
			<td colspan="2" class="toolbar">
			<input type="button" class="inputButton" value="确定" onclick="saveArticle();"/> &nbsp;&nbsp; 
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
		editor = UE.getEditor('articleContent');
		editor.addListener("ready", function() {
			editor.setContent('${article.articleContent }');
		});
	});
</script>
</html>
