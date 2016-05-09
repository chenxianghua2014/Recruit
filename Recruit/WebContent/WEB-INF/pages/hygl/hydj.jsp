<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'luntan.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/jBox/Skins/Default/jbox.css"
	type="text/css"></link>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript" src="js/Hydj.js"></script>
<script type="text/javascript">
function addHycpdj(){
if(Validator.Validate(document.getElementById('ff'),3) ){
	document.ff.action="UpdHycpdj";
	document.ff.method="post";
	document.ff.submit();
	}
}
</script>
</head>
  </head>
  
  <body>
  <div class="title">当前位置:会员维护>收费规则</div> 
    <div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">查询</td>
				</tr>
				<tr>
				   <td style="text-align: left;"><input name="button"
						type="button" class="inputButton" value="添加" onclick="addHydj();"/>
					</td>
					<td align="right">会员等级：<input name="hydjName" type="text" class="inputText"
						id="hydjName" />
					<input name="button"
						type="button" class="inputButton" value="查询" onclick="loadData()" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>
				查询结果
			</caption>
			<thead>
				<tr>
					<th>会员等级</th>
					<th>应付费（元）</th>
					<th>测评次数</th>
					<th width="60" class="alignCenter">修改</th>
					<th width="60" class="alignCenter">删除</th>
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
    <table boder="0" id="tbList">  
	</table>
	<div id="Pagination" class="pagination">
	</div>
	<div>
		<form name="ff" id="ff">
			<table>
				<tr>
					<td>会员测评单价（元/次）：</td>
					<td><input type="text" name="hycpdj"  dataType="Number"  msg="请填写具体钱数"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;<input name="button" type="button" class="inputButton" value="添加/修改" onclick="addHycpdj();"/></td>
				</tr>
				<tr>
					<td>目前会员测评单价（元/次）：${hycpdj} 元/次&nbsp;&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>
  </body>
</html>
