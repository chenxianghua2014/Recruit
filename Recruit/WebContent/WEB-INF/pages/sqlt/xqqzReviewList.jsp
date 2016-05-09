<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>社区-兴趣圈子>评论管理</title>

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
<script type="text/javascript" src="js/xqqzReviewList.js"></script>
<style type="text/css">
.editBlock a{background-color:#fff; border:1px solid #ccc; padding:5px; font-size:12px; color:#000; text-decoration: none;}
</style>
<script type="text/javascript">
function htBbsReviewSave() {
		if (editor.getContent('bbsReviewNr') == "") {
			alert("您还没有填写内容！请填写您的评论在发表");
		} else {
			document.ff.action = "htBbsReviewSave";
			document.ff.method = "post";
			document.ff.submit();
		}
}
</script>
</head>
<body >
<div class="title">当前位置:兴趣圈子>评论管理</div> 
<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">查询</td>
				</tr>
				<tr>
					<td align="right">
						发布人：<input name="xqqzReviewPlr" type="text" class="inputText" id="xqqzReviewPlr" />
						内容：<input name="xqqzReviewNr" type="text" class="inputText" id="xqqzReviewNr" />
						<input name="button" type="button" class="inputButton" value="查询"
						onclick="loadData('${xqqzId}')" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>
				查询结果 <span class="note"></span>
			</caption>
			<thead>
				<tr>
					<th>序号</th>
					<th>评论人</th>
					<th width="600px">内容</th>
					<th>时间</th>
					<th width="60" class="alignCenter">删除</th>
					<th width="60" class="alignCenter">禁言</th>
				</tr>
			</thead>
			<tbody id="tbList"></tbody>
		</table>
		<table class="page">
			<tr>
				<td style="text-align: right;"><div id="Pagination"
						class="pagination"></div>
				</td>
				<td style="width:265px;text-align: center;">当前显示 <span
					id="start">1</span> - <span id="end">10</span> 条记录 共 <span
					id="count">0</span> 条记录</td>
			</tr>
		</table>

	</div>
</body>
</html>
