<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>社区-论坛管理</title>
<link href="css/htgl/bbs/sq_style.css" rel="stylesheet" type="text/css" />
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="resources/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="resources/ueditor/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8"
	src="resources/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript "
	src="resources/ueditor/dialogs/image/image.js"></script>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript" src="js/Bbs.js"></script>
<script type="text/javascript">

	function checkLength(which) {
		var maxChars = 200; //
		if (which.value.length > maxChars) {
			alert("您出入的字数超出限制!");
			// 超过限制的字数了就将 文本框中的内容按规定的字数 截取
			which.value = which.value.substring(0, maxChars);
			return false;
		} else {
			var curr = which.value.length; //250 减去 当前输入的
			document.getElementById("sy").innerHTML = curr.toString();
			return true;
		}
	}
	function BbsSave(){
		
		if(Validator.Validate(document.getElementById('ff'),2) ){
			if(editor.getContent('bbs.bbsNr') == ""){
				alert("您还没有填写内容！请填写您的评论在发表");
			}else{
				document.ff.action = "BbsSave";
				document.ff.method = "POST";
				document.ff.submit();
			}
		}
	}
</script>
<style type="text/css">
.editBlock a{background-color:#fff; border:1px solid #ccc; padding:5px; font-size:12px; color:#000; text-decoration: none;}
</style>
</head>
<body>

	<div class="title">当前位置:内容管理及审核>科工论坛</div>
	<div id="all">
		<!--查询模块-->
		<div class="subtitle">
			<div class="title">&nbsp; >> 查询</div>
			<div class="editBlock">
				标签：
				<c:forEach items="${ltbqList}" var="map">
					<a href="javascript:void(0);"
						onclick="loadData('${map.ltbqName}','${map.ltbqId}')">${map.ltbqName}</a>
				</c:forEach>
				<div style="float:right;">
					内容：<input name="bbsNr" type="text" class="inputText" id="bbsNr" />
					发布人：<input name="bbsFbr" type="text" class="inputText" id="bbsFbr" />
					所属板块：<select name="ltbkId" id="ltbkId">
						<option value="">--请选择--</option>
						<c:forEach items="${ltbkList}" var="map">
							<option value="${map.ltbkId }">${map.ltbkName }</option>
						</c:forEach>
					</select> <input name="button" type="button" class="inputButton" value="查询"
						onclick="loadData('')" />
				</div>
			</div>

		</div>
		 <div class="publish" >
			 <form name="ff" id="ff">
					<script id="bbsNrueditor" name="bbsNr" type="text/plain" style="width:99.9%;height:100px;"></script>
					<div class="pubfb">
					<span class="warning">*</span>
					  板块：<select name="ltbkId" dataType="Require"  msg="请先选择版块"  >
										<option value="">--请选择--</option>
							<c:forEach items="${ltbkList}" var="map">
								<option value="${map.ltbkId}">${map.ltbkName }</option>
							</c:forEach>
						</select>
						<input name="button"type="button" class="inputButton" value="发表" onclick="BbsSave();"/>
					</div>
				</form>
         </div>
		<div class="subtitle_pl">
			<div class="title">&nbsp; >> 查询结果</div>
			<div id="tbList"></div>
		</div>
	</div>
	<div class="dataGrid">
		<table class="page">
			<tr>
				<td style="text-align: right;"><div id="Pagination"
						class="pagination"></div></td>
				<td style="width:265px;text-align: center;">当前显示 <span
					id="start">1</span> - <span id="end">10</span> 条记录 共 <span
					id="count">0</span> 条记录
				</td>
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript">
var editor;
var toobars = ['emotion'];
$(document).ready(function() {
	editor = UE.getEditor('bbsNrueditor', {
		toolbars : [ toobars ],
		maximumWords : 1000
	});
	editor.addListener("ready", function() {
		editor.setContent('${bbs.bbsNr }');
	});
});
</script>
</html>
