<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>社区-博文评论列表</title>
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
<script type="text/javascript" src="js/htBbsReview.js"></script>
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
<div class="title">当前位置:科工论坛>评论管理</div> 
<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td  height="32" colspan="4">
						<div class="neirong">
						<span>帖子内容：</span>
							${bbs.bbsNr}
						</div>
					</td>
				</tr>
				<tr>
					<td height="32" colspan="4" class="subtitle">查询</td>
				</tr>
				<tr>
					<td align="right">
						发布人：<input name="reviewPlr" type="text" class="inputText" id="reviewPlr" />
						内容：<input name="reviewNr" type="text" class="inputText" id="reviewNr" />
						<input name="button" type="button" class="inputButton" value="查询"
						onclick="loadData('${bbsId}')" />
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
					<th width="30px">序号</th>
					<th width="50px">发布人</th>
					<th width="650px">内容</th>
					<th width="140px">时间</th>
					<th width="50px" class="alignCenter">管理回复</th>
					<th width="30px" class="alignCenter">删除</th>
					<th width="30px" class="alignCenter">禁言</th>
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
	<div class="publish">
					<form name="ff" id="ff">
						 <input type="hidden" name="bbsId" value="${bbs.bbsId}" />
						<script id="bbsReviewNr" name="reviewNr" stype="text/plain" style="width:790px;height:100px;"></script>
						<div class="pubfb">
							<div class="pubright">
								<a href="javascript:void(0);" onclick="htBbsReviewSave();">发表</a>
							</div>
						</div>
					</form>
	</div>
</body>
<script type="text/javascript">
	var editor;
	var toobars = [ 'emotion' ];
	$(document).ready(function() {
		editor = UE.getEditor('bbsReviewNr', {
			toolbars : [ toobars ],
			maximumWords : 1000
		});
		editor.addListener("ready", function() {
			editor.setContent('${review.reviewNr }');
		});
	});
</script>
</html>
