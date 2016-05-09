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
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/Yapmsjl.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
.inputText {
	width: 65px;
}
</style>
</head>
<body>
	<div class="title">当前位置:招聘管理>已安排面试简历</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">已安排面试简历查询</td>
				</tr>
				<tr>
					<%--					<th width="150"></th>--%>
					<td style="text-align:right;">姓名:<input name="jtjlkName"
						type="text" class="inputText" id="jtjlkName" />&nbsp; 毕业院校:<input
						name="jtjlkByyx" type="text" class="inputText" id="jtjlkByyx"
						style="width:95px;" placeholder="请输入学校全称" />&nbsp; 专业:<input
						name="jtjlkZy" type="text" class="inputText" id="jtjlkZy" />&nbsp;
						学历:<select name="jtjlkXl" class="inputText" id="jtjlkXl">
							<option value=""></option>
							<option value="大专">大专</option>
							<option value="本科">本科</option>
							<option value="硕士">硕士</option>
							<option value="博士">博士</option>
					</select>&nbsp; 职位:<input name="jtjlkZw" type="text" class="inputText"
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
					</select> &nbsp; <label>标注排序：<input id="startpp" name="button"
							type="checkbox" value="6" /></label>&nbsp;&nbsp; <input name="button"
						type="button" onclick="loadData();" class="inputButton" value="查询" />
						<input name="button" type="button" onclick="loadDataExp();"
						class="inputButton" value="批量导出" />&nbsp;<input
						name="button" type="button" onclick="showBatch();"
						class="inputButton" value="批量操作" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>已安排面试简历查询结果</caption>
			<thead>
				<tr>
					<th width="30"><input id="checkAll" type="checkbox" /></th>
					<th width="50">姓名</th>
					<th width="80">出生日期</th>
					<th>身份证号</th>
					<th width="40">性别</th>
					<th>毕业院校</th>
					<th>专业</th>
					<th width="40">学历</th>
					<th>职位</th>
					<th>生源地</th>
					<th width="60" class="alignCenter">测评成绩</th>
					<th width="68" class="alignCenter">面试状态</th>
					<th width="60" class="alignCenter">标注/备注</th>
					<th width="60">投递时间</th>
					<th width="60" class="alignCenter">简历详情</th>
					<th width="60" class="alignCenter">面试筛选</th>
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
	<div class="editBlock" id="divControll" style="display: none;">
		<table>
			<tr>
				<td colspan="2" class="subtitle">面试详情</td>
			</tr>
			<tbody>
				<tr>
					<td colspan="2"><iframe src="" id="frmResult" name="frmResult"
							width="100%" height="320" scrolling="auto" frameborder="0"
							allowtransparency="true"></iframe><input type="hidden" id="Id"
						name="Id" /><input type="hidden" id="UserId" name="UserId" /> <input
						type="hidden" id="jtjlkMszt" name="jtjlkMszt" /></td>
				</tr>
				<tr>
					<td style="text-align:right;">筛选结果：</td>
					<td><input name="button" type="button" onclick="excute('通过');"
						class="inputButton" value="通过" />&nbsp;<input name="button"
						type="button" onclick="excute('淘汰');" class="inputButton"
						value="淘汰" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="editBlock" id="divControllBatch" style="display: none;">
		<table>
			<tr>
				<td colspan="2" class="subtitle">批量操作</td>
			</tr>
			<tbody>
				<tr>
					<td style="text-align: center;" colspan="2">筛选结果： <input
						id="Ids" type="hidden" value="" /> <input name="button"
						type="button" onclick="excuteBatch('通过');" class="inputButton"
						value="通过" />&nbsp; <input name="button" type="button"
						onclick="excuteBatch('淘汰');" class="inputButton" value="淘汰" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<script language="JavaScript" type="text/JavaScript">
		function showBatch() {
			$("#divControll").hide();
			$("#divControllBatch").show();
			document.body.scrollTop = document.body.scrollHeight;
		}
	</script>
</body>
</html>
