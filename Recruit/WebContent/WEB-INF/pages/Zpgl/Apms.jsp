<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link rel="stylesheet"
	href="resources/validator-0.7.1/jquery.validator.css" type="text/css"></link>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/jquery.validator.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript" src="js/Apms.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
.inputText {
	width: 65px;
}
</style>
</head>
<body>
	<form action="MsqSave" method="post" autocomplete="off"
		data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
		<div class="title">当前位置:面试圈>安排面试</div>
		<div class="editBlock">
			<table>
				<tbody>
					<tr>
						<td height="32" colspan="4" class="subtitle">简历查询</td>
					</tr>
					<tr>
						<th width="150"></th>
						<td style="text-align:right;">姓名:<input name="jtjlkName"
							type="text" class="inputText" id="jtjlkName" value="${ Name }" />&nbsp;
							毕业院校:<input name="jtjlkByyx" type="text" class="inputText"
							id="jtjlkByyx" />&nbsp; 专业:<input name="jtjlkZy" type="text"
							class="inputText" id="jtjlkZy" />&nbsp; 学历:<input name="jtjlkXl"
							type="text" class="inputText" id="jtjlkXl" />&nbsp; 职位:<input
							name="jtjlkZw" type="text" class="inputText" id="jtjlkZw" />&nbsp;
							岗位类型:<select name="jtjlkGwlb" id="jtjlkGwlb">
								<option value="">全部岗位</option>
								<option value="技术岗">技术岗</option>
								<option value="管理岗">管理岗</option>
						</select>&nbsp;<input name="button" type="button" onclick="loadData();"
							class="inputButton" value="查询" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="dataGrid">
			<table>
				<caption>简历查询结果</caption>
				<thead>
					<tr>
						<th>姓名</th>
						<th>性别</th>
						<th>毕业院校</th>
						<th>专业</th>
						<th>学历</th>
						<th>职位</th>
						<th>职位类别</th>
						<th width="60" class="alignCenter">测评成绩</th>
						<th width="60" class="alignCenter">面试状态</th>
						<th width="60" class="alignCenter">操作</th>
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
		<div class="editBlock" id="divControll" style="display: none;">
			<table>
				<tr>
					<td colspan="2" class="subtitle">安排面试</td>
				</tr>
				<tbody>
					<tr>
						<th>面试圈名称</th>
						<td><select id="msqId" name="msqId" style="width:140px;"></select>
						</td>
					</tr>
					<tr>
						<th>面试时间</th>
						<td><input type="hidden" id="jtjlkId" name="jtjlkId" /><input
							name="msqglDetailedMssj" type="text" class="inputText"
							style="width:142px" id="msqglDetailedMssj"
							onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							data-rule="面试时间:required;" /></td>
					</tr>
					<tr>
						<th>面试地点</th>
						<td><input name="msqglDetailedMsdd" type="text"
							class="inputText" id="msqglDetailedMsdd" style="width:142px"
							data-rule="面试地点:required;" /></td>
					</tr>
					<tr>
						<th>所在部门</th>
						<td><select id="bm" style="width:140px;" name="bm"
							onchange="bindMsg()">
						</select>
						</td>
					</tr>
					<tr>
						<th>所属职位</th>
						<td><select id="zw" style="width:140px;" name="zw"
							onchange="bindMsg()">
						</select>
						</td>
					</tr>
					<tr>
						<th>面试官</th>
						<td><input name="msg" type="hidden" id="msg" /> <select
							id="msqglDetailedMsg" style="width:140px;"
							name="msqglDetailedMsg" multiple="multiple"
							data-rule="面试官:required;">
								<option value="面试官A">面试官A</option>
								<option value="面试官B">面试官B</option>
								<option value="面试官C">面试官C</option>
						</select>
						</td>
					</tr>
					<tr>
						<th>发送面试通知</th>
						<td><input type="submit" class="inputButton" value="通知面试" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>
