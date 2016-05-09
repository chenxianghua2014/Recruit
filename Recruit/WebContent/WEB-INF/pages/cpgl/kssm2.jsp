<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>考试说明</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/cpgl/cpstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet"
	href="resources/jBox/Skins/Blue/jbox.css" />
<script type="text/javascript" src="js/KeyDown.js"></script>
</head>

<body onload="KeyDown();">
	<div id="top"></div>
	<div id="content">
		<div class="conTitle">
			<img src="images/tag04.png" />个性测试考试需知
		</div>
		<div class="conDetails">
			<p>1. 考试时间为100分钟</p>
			<p>2. 考试内容由三个单元组成：职业基本能力、英文和个性测试。</p>
			<p>3. 每个单元模块的考试时间有限制，考生必须在限定时间内打完一个单元模块的试题，提交后方可进入下一模块，已经提交的模块无法修改答题选项。</p>
			<p>4. 如果在规定时间内没有答完个性测试单元，考生的个性测试将没有成绩，其它单元已答过的试题计入成绩。</p>
			<p>5. 考试过程中遇到死机、白屏等情况，请及时联系监考老师。</p>
		</div>
		<a  href="javascript:void(0);" onclick="javascript:window.location='Gxcs'" class="btn">确定</a>
	</div>
	<%@ include file="../Main/cpglFooter.jsp"%>
</body>
</html>