<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>个人微博</title>
<link type="text/css" href="css/jlsq/style.css" rel="stylesheet" />
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
	src="resources/pagination/jquery.pagination.js"></script>
<link type="text/css" href="resources/jBox/Skins/Blue/jbox.css"
	rel="stylesheet"></link>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="js/XqqzReview.js"></script>
</head>
<body>
	<table align="center">
		<tr>
			<td>
				<div class="htcontent">
					<div class="particulars">
						<h3>${ xqqz.xqqzTitle }</h3>
						<h4>
							作者：${ xqqz.xqqzFbr} &nbsp;&nbsp;&nbsp;时间： ${ xqqz.fbsj}&nbsp;&nbsp;&nbsp;
							字体：<span class="blue12_20">【<a class="b"
								onclick="Zoom2.style.fontSize='18px';lineHeight='24px';Javascript:return false;"
								href="#">大</a>】 【<a class="b"
								onclick="Zoom2.style.fontSize='16px';Javascript:return false;"
								href="#">中</a>】 【<a class="b"
								onclick="Zoom2.style.fontSize='14px';Javascript:return false;"
								href="#">小</a>】
							</span>
						</h4>
						<div id="Zoom2"
							style="font-family: '微软雅黑';font-size: 14px;line-height: 2em;">${ xqqz.xqqzNr}</div>
						<div class="publish">
							<form name="ff" id="ff">
								<input type="hidden" name="xqqzId" id="xqqzId"
									value="${xqqz.xqqzId}" />
								<script id="xqqzReviewNr" name="xqqzReviewNr" type="text/plain"
									style="width:790px;height:100px;"></script>
								<div class="pubfb">
									<div class="pubright">
										<a href="javascript:void(0);" onclick="htsaveXqqzReview();">发表</a>
									</div>
								</div>
							</form>
						</div>
						<!--查询结果-->
						<div class="subtitle_pl">
							<div id="tbList"></div>
						</div>
					</div>
					<div>
						<table width="800px">
							<tr>
								<td style="text-align: right;"><div id="Pagination"
										class="pagination"></div></td>
								<td style="width:265px;text-align: center;">当前显示 <span
									id="start">1</span> - <span id="end">10</span> 条记录 共 <span
									id="count">0</span> 条评论记录
								</td>
							</tr>
						</table>
					</div>

				</div>
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
	var editor;
	var toobars = [ 'emotion' ];
	$(document).ready(function() {
		editor = UE.getEditor('xqqzReviewNr', {
			toolbars : [ toobars ],
			maximumWords : 1000
		});
		editor.addListener("ready", function() {
			editor.setContent('${xqqzReview.xqqzReviewNr }');
		});
	});
	function htsaveXqqzReview() {
		if (editor.getContent('xqqzReviewNr') == "") {
			alert("您还没有填写内容！请填写您的评论在发表");
		} else {
			document.ff.action = "htsaveXqqzReview";
			document.ff.method = "post";
			document.ff.submit();
		}
	}
</script>
</html>
