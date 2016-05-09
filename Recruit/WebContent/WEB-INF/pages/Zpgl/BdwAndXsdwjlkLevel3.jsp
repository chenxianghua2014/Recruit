<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/jBox/Skins/Blue/jbox.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="resources/validator-0.7.1/jquery.validator.css" type="text/css"></link>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/jquery.validator.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/BdwAndXsdwjlkLevel3.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
.inputText {
	width: 60px;
}
</style>
</head>
<body>
	<div class="title">当前位置:招聘管理>本单位及下属单位全部简历</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">本单位及下属单位全部简历</td>
				</tr>
				<tr>
					<td style="text-align:right;">姓名:<input name="jtjlkName"
						type="text" class="inputText" id="jtjlkName" />&nbsp; 毕业院校:<input
						name="jtjlkByyx" type="text" class="inputText" id="jtjlkByyx"
						style="width:95px;" placeholder="请输入学校全称" />&nbsp; 专业:<input
						name="jtjlkZy" type="text" class="inputText" id="jtjlkZy" />&nbsp;
						学历: <select name="jtjlkXl" class="inputText" id="jtjlkXl">
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
					</select>&nbsp; 职位:<input name="jtjlkZw" type="text" class="inputText"
						id="jtjlkZw" />&nbsp;投递单位: <select name="zzjgQueryNum"
						id="zzjgQueryNum">
							<option value="1">一级单位</option>
							<option value="2">二级单位</option>
							<option value="3">三级单位</option>
							<option value="">所有单位</option>
					</select>&nbsp;职位类别:<select id="jtjlkGwlb" name="jtjlkGwlb">
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
					</select>&nbsp; 生源地:<input name="jtjlkHkd" type="text" class="inputText"
						id="jtjlkHkd" /><br />&nbsp;&nbsp;&nbsp;&nbsp;招聘过程状态: <select
						id="jtjlkZt" name="jtjlkZt">
							<option
								value="未筛选,筛选协同,通过,未测评,已测评,已安排面试,测评,一面进行中,二面进行中,终面进行中,录用,淘汰">全部状态</option>
							<option value="未筛选,筛选协同">新的简历</option>
							<option value="通过,未测评,已测评,已安排面试">适合的简历</option>
							<option value="测评,测评">已通知测评简历</option>
							<option value="一面进行中,二面进行中,终面进行中">已安排面试简历</option>
							<option value="录用,录用">已录用简历</option>
							<option value="淘汰,淘汰">已淘汰简历</option>
					</select>&nbsp;<input name="button" type="button" onclick="loadData();"
						class="inputButton" value="查询" />&nbsp;<input name="button"
						type="button" onclick="loadDataExp();" class="inputButton"
						value="批量导出" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>本单位简历库查询结果</caption>
			<thead>
				<tr>
					<th width="20"><input id="checkAll" type="checkbox" /></th>
					<th width="80">姓名</th>
					<th width="80">出生日期</th>
					<th>身份证号</th>
					<th width="40">性别</th>
					<th>毕业院校</th>
					<th>专业</th>
					<th width="40">学历</th>
					<th width="60">职位</th>
					<th width="60">投递时间</th>
					<th>生源地</th>
					<th>投递单位</th>
					<th width="80">招聘过程状态</th>
					<th width="60" class="alignCenter">简历详情</th>
				</tr>
			</thead>
			<tbody id="tbList"></tbody>
		</table>
		<table class="page">
			<tr>
				<td style="text-align: right;"><div id="Pagination"
						class="pagination"></div></td>
				<td style="width:265px;text-align: center;">当前显示 <span
					id="start">1</span> - <span id="end">10</span> 条记录 共 <span
					id="count">0</span> 条记录
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
