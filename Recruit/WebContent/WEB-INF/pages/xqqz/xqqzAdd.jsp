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
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/> 
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
<link type="text/css" href="resources/jBox/Skins/Blue/jbox.css"
	rel="stylesheet"></link>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="js/ArticleReview.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/validator.js"></script>
</head>

<body>
<%@ include file="../Main/Header.jsp"%>
<div class="wrap clr">
	<div class="menu">
    	<div class="menuTop">
				<img src="images/cpgl/title06.png" />
			</div>
        <ul>
        	<li><a  href="javascript:void(0);" onclick="javascript:window.location='jlsq'">科工论坛</a></li>
	            <li><a  href="javascript:void(0);" onclick="javascript:window.location='zyty'">经验博文</a></li>
	            <li><a  style="color:#0046ae;" href="javascript:void(0);" onclick="javascript:window.location='qtXqqz'">兴趣圈子</a></li>
        </ul>
    </div> 
  <div class="content">
    	<div class="conTop">
        	<img src="images/cpgl/tag04.png" />&nbsp; 
            <strong>兴趣圈子发文</strong>Interest group
            <span>您的当前位置：首页 >> 交流社区 >> 兴趣圈子</span>
        </div>
        <div class="particulars">
	        <form name="ff" id="ff">
		     <input type="hidden" name="xqqzId" value="${xqqz.xqqzId}"/>
		     <input type="hidden" name="username" id="username" value="${userLoginSession.userName}"/>
		         <div class="bwtitile">
			        	  <span class="warning">*</span>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题 ：<input type="text" id="xqqzTitle" name="xqqzTitle"
							value="${ xqqz.xqqzTitle }" dataType="Limit" min="1" max="30" msg="必填(限30个字)"/>
				</div>
				<div class="bwtitile">
							<span class="warning">*</span>
							兴趣类型：<select id="xqqzType" name="xqqzType">
										<option value="" >--请选择--</option>
										<c:forEach items="${xqqzLxglList}" var="map">
											<option value="${map.xqqzlxglId}" >${map.xqqzlxglLxmc}</option>
										</c:forEach>
										</select>
				</div>
		            <div class="publish" >
			           		<script id="xqqzNr" name="xqqzNr" type="text/plain" style="width:790px;height:300px;"></script>
			           		<div class="pubfb">
			                	<div class="pubright"><a href="javascript:void(0);" onclick="qtNewXqqzSave();">发表</a></div>
			                </div>
			         </div>
	            </form>
           </div>
</div>

</div>
<%@ include file="../Main/Footer.jsp"%> 
</body>
<script type="text/javascript">
var editor;
$(document).ready(function() {
	editor = UE.getEditor('xqqzNr');
	editor.addListener("ready", function() {
		editor.setContent('${xqqz.xqqzNr }');
	});
});
function qtNewXqqzSave(){
	if($("#username").val() == null || $("#username").val() == ""){
		$.jBox.open("iframe:LoginFrm", "用户登录", 280, 200, {
			buttons : {}
		});
	}else{
		if(Validator.Validate(document.getElementById('ff'),3) ){
			if(editor.getContent('xqqzNr') == ""){
				alert("您还没有填写内容！请填写您的评论在发表");
			}else{
				document.ff.action="qtNewXqqzSave";
				document.ff.method="post";
				document.ff.submit();
			}
		}
	}
}
</script>
</html>
