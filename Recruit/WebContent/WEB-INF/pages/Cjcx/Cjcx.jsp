<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet"
	href="resources/jBox/Skins/Blue/jbox.css" />
<title></title>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/Cjcx.js"></script>
<script type="text/javascript">
	function exportScore() {
		if (confirm("您确定要导出成绩么?")) {
			$.jBox.tip("正在生成考生成绩单，请耐心等待！", 'loading');
			window.setTimeout(function() {
				window.location = "exportScore";
				$.jBox.tip('已完成。', 'success');
			}, 2000);

		}
	}
	function exportKscj() {
		var ksxcglBkdw = $("#bkdw").val();
		if (confirm("您确定要导出成绩么?")) {
			$.jBox.tip("正在生成考生成绩单，请耐心等待！", 'loading');
			window.location = "exportKscj?ksxcglBkdw=" + ksxcglBkdw;
		}
	}
</script>
</head>
<body>
	<input type="hidden" name="zzjgEjmm" id="zzjgEjmm"
		value="${loginSession.zzjgEjmm}" />
	<input type="hidden" name="zzjgSjdw" id="zzjgSjdw"
		value="${loginSession.zzjgSjdw}" />
	<input type="hidden" name="bkdw" id="bkdw" value="" />
	<div class="title">当前位置:成绩管理>成绩查询</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">成绩查询</td>
				</tr>
				<tr>
					<c:if
						test="${loginSession.zzjgSjdw eq null || loginSession.zzjgSjdw eq ''}">
						<td style="text-align: left;"><input name="button"
							type="button" class="inputButton" value="导出成绩Excel"
							onclick="exportKscj();" /></td>
					</c:if>
					<td style="text-align: right;">姓名:<input name="ksxcglName"
						type="text" class="inputText" id="ksxcglName" /> 身份证号:<input
						name="ksxcglIdcard" type="text" class="inputText"
						id="ksxcglIdcard" /> 报考单位: <select name="ksxcglBkdw"
						id="ksxcglBkdw">
							<option value=""></option>
							<c:if
								test="${loginSession.zzjgSjdw eq null || loginSession.zzjgSjdw eq ''}">
								<option value="集团公司总部">集团公司总部</option>
								<c:forEach items="${zzjgFOList}" var="fmap">
									<option value="${fmap.zzjgDwmc}">&nbsp;&nbsp;&nbsp;&nbsp;${fmap.zzjgDwmc}</option>
									<c:forEach items="${zzjgSunList}" var="map">
										<c:if test="${fmap.zzjgId eq map.zzjgSjdw}">
											<option value="${map.zzjgDwmc}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.zzjgDwmc}</option>
										</c:if>
									</c:forEach>
								</c:forEach>
							</c:if>
							<c:if test="${loginSession.zzjgSjdw eq 'test001'}">
								<c:forEach items="${zzjgFOList}" var="fmap">
									<option value="${fmap.zzjgDwmc}">${fmap.zzjgDwmc}</option>
									<c:forEach items="${zzjgSunList}" var="map">
										<c:if test="${fmap.zzjgId eq map.zzjgSjdw}">
											<option value="${map.zzjgDwmc}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.zzjgDwmc}</option>
										</c:if>
									</c:forEach>
								</c:forEach>
							</c:if>
							<c:if
								test="${loginSession.zzjgSjdw ne null || loginSession.zzjgSjdw ne '' || loginSession.zzjgSjdw ne 'test001'}">
								<option value="${SJzzjg.zzjgDwmc}">${SJzzjg.zzjgDwmc}</option>
							</c:if>
					</select> <input name="button" type="submit" class="inputButton" value="查询"
						onclick="loadData();" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>查询结果</caption>
			<thead>
				<tr>
					<c:if
						test="${loginSession.zzjgSjdw eq null || loginSession.zzjgSjdw eq '' || loginSession.zzjgSjdw eq 'test001'}">
						<th width="68">单位代码</th>
						<th>单位名称</th>
					</c:if>
					<th width="68">考生姓名</th>
					<th width="30">性别</th>
					<th>身份证号</th>
					<th width="80">出生日期</th>
					<th>毕业学校</th>
					<th>专业</th>
					<th width="40">学历</th>
					<th width="70">管理岗成绩</th>
					<th width="70">技术岗成绩</th>
					<th>性格测试报告</th>
					<c:if
						test="${loginSession.zzjgSjdw eq null || loginSession.zzjgSjdw eq ''}">
						<th width="140">考试时间</th>
						<th>考场名称</th>
						<th width="60" class="alignCenter">删除</th>
					</c:if>
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
</body>
</html>
