<%@page import="com.ttgis.recruit.domain.Zzjg"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<base target="mainFrame" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="resources/jquery/jquery-1.3.1.js"></script>
<script type="text/javascript">
	$(function() {
		$('.menu h3').click(function() {
			var $dt = $(this).siblings().children('dt');
			var $dd = $(this).siblings().children('dd');
			if ($dt.is(':visible')) {
				$dt.slideUp();
				$dd.slideUp();
			} else {
				$('.menu dt').slideUp();
				$('.menu dd').slideUp();
				$dt.slideDown();
			}
		});

		$('.menu dt').toggle(function() {
			$('.menu dt').siblings('dd').slideUp();
			$(this).siblings('dd').slideDown();
		}, function() {
			$(this).siblings('dd').slideUp();
		});
		
		//新的简历
		$.ajax({
			type : 'POST',url : "LoadZpglDataCount",
			data : {
				jtjlkName : "", jtjlkByyx : "", jtjlkZy : "", jtjlkXl : "", jtjlkZw : "",
				jtjlkZt : "未筛选,筛选协同", jtjlkGwlb : ""
			},dataType : "json",
			success : function(count) {
				$('#Xdjl').html("&nbsp;&nbsp;<span style='color:red;'>(" + count + ")</span>");
			}
		});
		
		//适合的简历
		$.ajax({
			type : 'POST', url : "LoadZpglDataCount",
			data : {
				jtjlkName : "", jtjlkByyx : "", jtjlkZy : "", jtjlkXl : "", jtjlkZw : "",
				jtjlkZt : "通过,未测评,已测评,已安排面试", jtjlkGwlb : ""
			}, dataType : "json",
			success : function(count) {
				$('#Shdjl').html("&nbsp;&nbsp;<span style='color:red;'>(" + count + ")</span>");
			}
		});
		//已通知测评简历
		$.ajax({
			type : 'POST', url : "LoadZpglDataCount",
			data : {
				jtjlkName : '', jtjlkByyx : '', jtjlkZy : '', jtjlkXl : '', jtjlkZw : '',
				jtjlkCpjg : "测评", jtjlkGwlb : ''
			}, dataType : "json",
			success : function(count) {
				$('#Ytzcpjl').html("&nbsp;&nbsp;<span style='color:red;'>(" + count + ")</span>");
			}
		});
		//已安排面试简历
		$.ajax({
			type : 'POST', url : "LoadZpglDataCount",
			data : {
				jtjlkName : '', jtjlkByyx : '', jtjlkZy : '', jtjlkXl : '', jtjlkZw : '',
				jtjlkMszt : "一面进行中,二面进行中,终面进行中", jtjlkGwlb : ''
			}, dataType : "json",
			success : function(count) {
				$('#Yapmsjl').html("&nbsp;&nbsp;<span style='color:red;'>(" + count + ")</span>");
			}
		});
		
		//已录用简历
		$.ajax({
			type : 'POST',
			url : "LoadZpglDataCount",
			data : {
				jtjlkName : '', jtjlkByyx : '', jtjlkZy : '', jtjlkXl : '', jtjlkZw : '',
				jtjlkZt : '录用', jtjlkGwlb : ''
			},
			dataType : "json",
			success : function(count) {
				$('#Ylyjl').html("&nbsp;&nbsp;<span style='color:red;'>(" + count + ")</span>");
			}
		});
		
		//已淘汰简历
		$.ajax({
			type : 'POST',
			url : "LoadZpglDataCount",
			data : {
				jtjlkName : '', jtjlkByyx : '', jtjlkZy : '', jtjlkXl : '', jtjlkZw : '',
				jtjlkZt : '淘汰', jtjlkGwlb : ''
			},
			dataType : "json",
			success : function(count) {
				$('#Yttjl').html("&nbsp;&nbsp;<span style='color:red;'>(" + count + ")</span>");
			}
		});
		//收藏的简历
		$.ajax({
			type : 'POST',
			url : "LoadZpglDataCount",
			data : {
				jtjlkName : '', jtjlkByyx : '', jtjlkZy : '', jtjlkXl : '', jtjlkZw : '',
				jtjlkZt : "收藏", jtjlkGwlb : ''
			},
			dataType : "json",
			success : function(count) {
				$('#Scdjl').html("&nbsp;&nbsp;<span style='color:red;'>(" + count + ")</span>");
			}
		});
		//本单位全部简历
		$.ajax({
			type : 'POST',
			url : "LoadBdwAndXsdwJtjlkDataLevel3Count",
			data : {
				jtjlkName : "",
				jtjlkByyx : "",
				jtjlkZy : "",
				jtjlkXl : "",
				jtjlkZw : "",
				jtjlkGwlb : "",
				jtjlkHkd : "",
				zzjgQueryNum : "",
				jtjlkZt : "未筛选,筛选协同,通过,未测评,已测评,已安排面试,测评,一面进行中,二面进行中,终面进行中,录用,淘汰",
				jtjlkMszt: "",
				jtjlkCpjg:""
			},
			dataType : "json",
			success : function(count) {
				$('#BdwAndXsdwjlkLevel3').html("&nbsp;&nbsp;<span style='color:red;'>(" + count + ")</span>");
			}
		});

		//本单位全部简历
		$.ajax({
			type : 'POST',
			url : "LoadBdwAndXsdwJtjlkDataCount",
			data : {
				jtjlkName : "",
				jtjlkByyx : "",
				jtjlkZy : "",
				jtjlkXl : "",
				jtjlkZw : "",
				jtjlkGwlb : "",
				jtjlkHkd : "",
				zzjgQueryNum : "",
				jtjlkZt : "未筛选,筛选协同,通过,未测评,已测评,已安排面试,测评,一面进行中,二面进行中,终面进行中,录用,淘汰",
				jtjlkMszt: "",
				jtjlkCpjg:""
			},
			dataType : "json",
			success : function(count) {
				$('#BdwAndXsdwjlk').html("<span style='color:red;'>(" + count + ")</span>");
			}
		});
		
		//本单位全部简历
		$.ajax({
			type : 'POST',
			url : "LoadBdwAndXsdwJtjlkDataCount",
			data : {
				jtjlkName : "",
				jtjlkByyx : "",
				jtjlkZy : "",
				jtjlkXl : "",
				jtjlkZw : "",
				jtjlkGwlb : "",
				jtjlkHkd : "",
				zzjgQueryNum : "",
				jtjlkZt : "未筛选,筛选协同,通过,未测评,已测评,已安排面试,测评,一面进行中,二面进行中,终面进行中,录用,淘汰",
				jtjlkMszt: "",
				jtjlkCpjg:""
			},
			dataType : "json",
			success : function(count) {
				$('#BdwAndXsdwjlk').html("<span style='color:red;'>(" + count + ")</span>");
			}
		});
		
		$.ajax({
			type : 'POST',
			url : "LoadBdwJtjlkDataCount",
			data : {
				jtjlkName : "",
				jtjlkByyx : "",
				jtjlkZy : "",
				jtjlkXl : "",
				jtjlkZw : "",
				jtjlkGwlb : "",
				jtjlkHkd : "",
				jtjlkZt : "未筛选,筛选协同,通过,未测评,已测评,已安排面试,测评,一面进行中,二面进行中,终面进行中,录用,淘汰",
				jtjlkMszt: "",
				jtjlkCpjg:""
			},
			dataType : "json",
			success : function(count) {
				$('#Bdwjlk').html("<span style='color:red;'>(" + count + ")</span>");
			}
		});
		
	});
</script>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

body {
	font: 12px Verdana, Geneva, sans-serif;
	padding: 0px;
}

.menu {
	width: 245px;
	background: #FFF;
	margin-top: 1px;
}

.menu h3 {
	font-weight: bold;
	font-size: 14px;
	font-family: "微软雅黑";
	background: url(images/menu_01.jpg) no-repeat left top;
	line-height: 32px;
	padding: 0px 35px;
	cursor: pointer;
	color: #FFF;
}

.menu dt,.menu dd {
	display: none;
	height: 29px;
	line-height: 29px;
	background: url(images/menu_02.jpg) no-repeat left top;
	cursor: pointer;
	margin-top: 1px;
}

.menu dt {
	padding: 0px 40px;
}

.menu dd {
	padding: 0px 40px;
	background: #fff;
}

.show dt {
	display: block;
}

.menu a {
	color: #000;
	text-decoration: none;
	margin-left: -20px;
}

.menu a:hover {
	color: #00F;
	font-weight: bold;
}
</style>
</head>
<body>

	<%
		Object o = session.getAttribute("loginType");
		if (o != null)
		{
	%>
	<div class="menu show">
		<dl>
			<h3>招聘</h3>
			<dt>面试圈</dt>
			<dd>
				<a href="MsResult">面试结果</a>
			</dd>
		</dl>
	</div>
	<%
		} else
		{
	%>
	<%
		Zzjg zzjg = (Zzjg) session.getAttribute("loginSession");
			if (zzjg.getZzjgId().equals("test001"))
			{
	%>
	<%@ include file="MenuOne.jsp"%>
	<%
		} else if (zzjg.getZzjgSjdw().equals("test001"))
			{
	%>
	<%@ include file="MenuTwo.jsp"%>
	<%
		} else
			{
	%>
	<%@ include file="MenuThree.jsp"%>
	<%
		}
		}
	%>

</body>
</html>