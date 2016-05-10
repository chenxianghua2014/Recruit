<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/jBox/Skins/Blue/jbox.css" type="text/css"></link>
<link rel="stylesheet" href="resources/validator-0.7.1/jquery.validator.css" type="text/css"></link>
<link rel="stylesheet" href="resources/pagination/pagination.css" type="text/css"></link>
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript" src="resources/validator-0.7.1/jquery.validator.js"></script>
<script type="text/javascript" src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/Jtjlk.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
.inputText {
	width: 60px;
}
</style>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<link href="skins/default/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function zzjgLogout() {
		if (confirm("确认退出本次登陆么？")) {
			window.location.href = "zzjgLogout";
		}
	}

	function redirect(url) {
		document.getElementById("mainFrame").src = url;
	}
</script>
<style style="text/css">
html {
	overflow-x: hidden;
}

* {
	margin: 0px;
	padding: 0px;
}

body {
	margin: 0px;
	padding: 0px;
}

.top {
	width: 100%;
	height: 80px;
	background-color: #015bac;
	background: url(images/top.jpg) no-repeat left top;
}

.tishi {
	text-align: right;
	height: 80px;
}

.tishi p {
	height: 40px;
	line-height: 40px;
	font-family: "微软雅黑";
	font-size: 14px;
	color: #FFF;
	font-weight: bold;
	padding-right: 20px;
}

.tishi a {
	font-family: "微软雅黑";
	font-size: 14px;
	color: #FFF;
	text-decoration: none;
}

.tishi p img {
	vertical-align: middle;
	padding-right: 5px;
}

.left_menu {
	width: 180px;
	float: left;
}
</style>
</head>
<body>
	<div class="top">
		<div class="tishi">
			<p>
				<a href="#">管理系统首页</a> | <a href="http://www.casic.com.cn">集团公司首页</a>
			</p>
			<p>
				<a title="查看用户基本信息" href="reviewZzjg" target="mainFrame"> <img src="images/admin.png" />
				</a> 您好！
				<span style="color: black; size: 4em;">
					<%
						Object o = session.getAttribute("loginType");
						if (o == null) {
					%>
					${loginSession.zzjgDwmc}
					<%
						} else {
					%>
					${loginSession.userName}
					<%
						}
					%>
				</span>
				&nbsp;&nbsp;
				<span id="localtime"></span>
				【<a title="注销登录" href="javascript: void(0);" onclick="zzjgLogout();">注销</a>】
			</p>
			<script type="text/javascript">
				function showLocale(objD) {
					var str, colorhead, colorfoot;
					var yy = objD.getYear();
					if (yy < 1900)
						yy = yy + 1900;
					var MM = objD.getMonth() + 1;
					if (MM < 10)
						MM = '0' + MM;
					var dd = objD.getDate();
					if (dd < 10)
						dd = '0' + dd;
					var hh = objD.getHours();
					if (hh < 10)
						hh = '0' + hh;
					var mm = objD.getMinutes();
					if (mm < 10)
						mm = '0' + mm;
					var ss = objD.getSeconds();
					if (ss < 10)
						ss = '0' + ss;
					var ww = objD.getDay();
					if (ww == 0)
						colorhead = "<font color=\"#dbe6b7\">";
					if (ww > 0 && ww < 6)
						colorhead = "<font color=\"#FFFFFF\">";
					if (ww == 6)
						colorhead = "<font color=\"#FFFFFF\">";
					if (ww == 0)
						ww = "星期日";
					if (ww == 1)
						ww = "星期一";
					if (ww == 2)
						ww = "星期二";
					if (ww == 3)
						ww = "星期三";
					if (ww == 4)
						ww = "星期四";
					if (ww == 5)
						ww = "星期五";
					if (ww == 6)
						ww = "星期六";
					colorfoot = "</font>"
					str = colorhead + yy + "-" + MM + "-" + dd + " " + hh + ":"
							+ mm + ":" + ss + "  " + ww + colorfoot;
					return (str);
				}
				function tick() {
					var today;
					today = new Date();
					document.getElementById("localtime").innerHTML = showLocale(today);
					window.setTimeout("tick()", 1000);
				}
				tick();
			</script>
		</div>
	</div>
	<div class="title">当前位置:集团简历库>集团简历库</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">集团简历库</td>
				</tr>
				<tr>
					<td style="text-align:right;">
						姓名:
						<input name="jtjlkName" type="text" class="inputText" id="jtjlkName" />
						&nbsp; 毕业院校:
						<input name="jtjlkByyx" type="text" class="inputText" id="jtjlkByyx" style="width:95px;"
							placeholder="请输入学校全称" />
						&nbsp; 专业:
						<input name="jtjlkZy" type="text" class="inputText" id="jtjlkZy" />
						&nbsp; 学历: <select name="jtjlkXl" class="inputText" id="jtjlkXl">
							<option value=""></option>
							<option value="初中">初中</option>
							<option value="高中">高中</option>
							<option value="中技">中技</option>
							<option value="中专">中专</option>
							<option value="大专">大专</option>
							<option value="本科">本科</option>
							<option value="MBA">MBA</option>
							<option value="硕士">硕士</option>
							<option value="博士">博士</option>
							<option value="其他">其他</option>
						</select>
						<%--&nbsp; 职位:<input name="jtjlkZw" type="text" class="inputText"
						id="jtjlkZw" />&nbsp;职位类别:<select id="jtjlkGwlb" name="jtjlkGwlb">
							<option value=""></option>
							<option value="综合管理类">综合管理类</option>
							<option value="科研管理类">科研管理类</option>
							<option value="生产管理类">生产管理类</option>
							<option value="经营管理类">经营管理类</option>
							<option value="市场营销类">市场营销类</option>
							<option value="其他管理类">其他管理类</option>
							<option value="研发类">研发类</option>
							<option value="设计类">设计类</option>
							<option value="工艺类">工艺类</option>
							<option value="其他技术类">其他技术类</option>
							<option value="技能类">技能类</option>
							<option value="医护类">医护类</option>
							<option value="教育培训类">教育培训类</option>
							<option value="服务保障类">服务保障类</option>
					</select>--%>&nbsp; 户口地:
						<input name="jtjlkHkd" type="text" class="inputText" id="jtjlkHkd" />
						&nbsp;
						<input  id="jtjlkIds" name="jtjlkIds" type="hidden"/>
						<input name="button" type="button" onclick="loadData();" class="inputButton" value="查询" />
						<input name="button" type="button" onclick="loadDataExp('录用');" class="inputButton"
							value="批量导出" />

					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>集团简历库查询结果</caption>
			<thead>
				<tr>
					<th width="30"><input id="checkAll" type="checkbox" /></th>
					<th width="3%">姓名</th>
					<th width="5%">应聘岗位1</th>
					<th width="5%">应聘岗位2</th>
					<th width="3%">性别</th>
					<th width="8%">工作单位</th>
					<th width="5%">职务</th>
					<th width="8%">出生年月</th>
					<th width="5%">参加工作时间</th>
					<th width="5%">入党时间</th>
					<th width="5%">学历</th>
					<th width="5%">学位</th>
					<th width="5%">毕业院校</th>
					<th width="5%">专业</th>
					<th width="5%">外语种类</th>
					<th width="5%">联系电话</th>
					<th width="5%">户口地</th>
					<th width="5%" class="alignCenter">简历详情</th>
					<%-- <th width="60" class="alignCenter">收藏</th>--%>
					<!-- <th width="60" class="alignCenter">删除</th> -->
				</tr>
			</thead>
			<tbody id="tbList"></tbody>
		</table>
		<table class="page">
			<tr>
				<td style="text-align: right;">
					<div id="Pagination" class="pagination"></div>
				</td>
				<td style="width:265px;text-align: center;">
					当前显示
					<span id="start">1</span>
					-
					<span id="end">10</span>
					条记录 共
					<span id="count">0</span>
					条记录
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
