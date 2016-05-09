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
<title>科工论坛</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/> 
<link type="text/css" href="css/jlsq/style.css" rel="stylesheet" />
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
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
<link type="text/css" href="resources/jBox/Skins/Blue/jbox.css"
	rel="stylesheet"></link>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/twoBbs.js"></script>
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
</script>

</head>
<body>
	<%@ include file="../Main/Header.jsp"%>
	<div class="wrap clr">
		<div class="menu">
			<div class="menuTop">
				<img src="images/title01.png" />
			</div>
			<ul>
				<li><a style="color:#0046ae;" href="javascript:void(0);" onclick="javascript:window.location='jlsq'">科工论坛</a></li>
	            <li><a href="javascript:void(0);" onclick="javascript:window.location='zyty'">经验博文</a></li>
	            <li><a href="javascript:void(0);" onclick="javascript:window.location='qtXqqz'">兴趣圈子</a></li>
			</ul>
		</div>

		<div class="content">
			<div class="conTop">
		        	<img src="images/tag04.png" />&nbsp; 
		            <strong>科工论坛</strong>BBS
		            <span>您的当前位置：首页 >> 交流社区 >> 科工论坛</span>
            </div>
			<div class="main">
				 <div class="Tab">
        			<span>标签:</span>
        			<c:forEach items="${ltbqList}" var="map">
					<a href="javascript:void(0);" onclick="javascript:window.location='queryByBq?ltbqName=${map.ltbqName}&ltbqId=${map.ltbqId}'" title="${map.ltbqName}">${map.ltbqName}</a>
					</c:forEach>
         		 </div>
         		 <div class="bbscontent">
	            	${bbs.bbsNr}
	             	<div class="bssName">发布人：${bbs.bbsFbr}<span>时间：${bbs.fbsj}</span></div>
         		 </div>
         		 <div class="publish" style="margin-top:30px;">
						         	<form name="ff" id="ff">
						            <input type="hidden" name="bbsId" value="${bbs.bbsId}"/>
						            <input type="hidden" name="username" id="username" value="${userLoginSession.userName}"/>
						           		<script id="reviewNr" name="reviewNr" type="text/plain" style="width:790px;height:100px;"></script>
						           		<div class="pubfb">
						                	<div class="pubright"><a href="javascript:void(0);" onclick="qtLtbqReviewSave();">发表</a></div>
						                </div>
					                </form>
				 </div>
					<div class="subtitle_pl">
						<div id="tbList"></div>
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
		</div>
	</div>
	<%@ include file="../Main/Footer.jsp"%>
</body>
<script type="text/javascript">
var editor;
var toobars = ['emotion'];
$(document).ready(function() {
	editor = UE.getEditor('reviewNr', {
		toolbars : [ toobars ],
		maximumWords : 1000
	});
	editor.addListener("ready", function() {
		editor.setContent('${review.reviewNr }');
	});
});
function qtLtbqReviewSave(){
	if($("#username").val() == null || $("#username").val() == ""){
		$.jBox.open("iframe:LoginFrm", "用户登录", 280, 200, {
			buttons : {}
		});
	}else{
		if(editor.getContent('reviewNr') == ""){
			alert("您还没有填写内容！请填写您的评论在发表");
		}else{
			document.ff.action="qtLtbqReviewSave";
			document.ff.method="post";
			document.ff.submit();
		}
	}
}
</script>
</html>
