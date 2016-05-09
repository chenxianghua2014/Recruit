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
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>经验博文</title>
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
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<link type="text/css" href="resources/jBox/Skins/Blue/jbox.css"
	rel="stylesheet"></link>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="js/ArticleReview.js"></script>
<script type="text/javascript">
	function htsaveArticleReview() {
		if (editor.getContent('articleReviewNr') == "") {
			alert("您还没有填写内容！请填写您的评论在发表");
		} else {
			document.ff.action = "htsaveArticleReview";
			document.ff.method = "post";
			document.ff.submit();
		}
	}
</script>

</head>

<body>
	<table align="center">
		<tr>
			<td>

				<div>
					<div class="particulars">
						<h3>${ article.articleTitle }</h3>
						<h4>
							作者:${ article.articleAuthor} &nbsp;&nbsp;&nbsp;时间：${ article.addTime}&nbsp;&nbsp;&nbsp;
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
							style="font-family: '微软雅黑';font-size: 14px;line-height: 2em;">${ article.articleContent}</div>
						<!--             <div class="parlink"><a href="#">赞</a> | <a href="#">评论</a> | <a href="#">转发</a></div> -->
						<div class="publish">
							<form name="ff" id="ff">
								<input type="hidden" name="articleId"
									value="${article.articleId}" />
								<script id="articleReviewNr" name="articleReviewNr"
									type="text/plain" style="width:790px;height:100px;"></script>
								<div class="pubfb">
									<div class="pubright">
										<a href="javascript:void(0);" onclick="htsaveArticleReview();">发表</a>
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
		editor = UE.getEditor('articleReviewNr', {
			toolbars : [ toobars ],
			maximumWords : 1000
		});
		editor.addListener("ready", function() {
			editor.setContent('${articleReview.articleReviewNr }');
		});
	});
</script>
</html>
