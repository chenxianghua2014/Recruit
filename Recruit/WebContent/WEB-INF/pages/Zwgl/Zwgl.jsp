<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/Zwgl.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<div class="title">当前位置:职位管理>职位管理</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">职位管理</td>
				</tr>
				<tr>
					<th style="text-align: left" width="150" rowspan="2"><input
						name="button" type="button" class="inputButton" value="添加"
						onclick="AddZwgl()" />
					</th>
					<td style="text-align: right;">职位名称:<input name="positionName"
						type="text" class="inputText" style="width:75px;"
						id="positionName" />&nbsp;&nbsp;招聘专业:<input name="positionZpzy"
						type="text" style="width: 75px;" class="inputText"
						id="positionZpzy" />&nbsp;&nbsp;职位类别:<select id="positionType"
						name="positionType">
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
					</select>&nbsp;&nbsp;学历要求: <select id="positionXlyq" name="positionXlyq">
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
					</select>&nbsp;&nbsp;工作地点: <select id="positionAddress"
						name="positionAddress">
							<option value=""></option>
							<option value="北京">北京</option>
							<option value="上海">上海</option>
							<option value="广州">广州</option>
							<option value="深圳">深圳</option>
							<option value="天津">天津</option>
							<option value="南京">南京</option>
							<option value="武汉">武汉</option>
							<option value="西安">西安</option>
							<option value="哈尔滨">哈尔滨</option>
							<option value="沈阳">沈阳</option>
							<option value="大连">大连</option>
							<option value="重庆">重庆</option>
							<option value="成都">成都</option>
							<option value="杭州">杭州</option>
							<option value="呼和浩特">呼和浩特</option>
							<option value="长沙">长沙</option>
							<option value="郑州">郑州</option>
							<option value="太原">太原</option>
							<option value="贵阳">贵阳</option>
							<option value="青岛">青岛</option>
							<option value="昆明">昆明</option>
							<option value="珠海">珠海</option>
							<option value="贵州遵义">贵州遵义</option>
							<option value="广西柳州">广西柳州</option>
							<option value="浙江宁波">浙江宁波</option>
							<option value="四川绵阳">四川绵阳</option>
							<option value="河南信阳">河南信阳</option>
							<option value="江苏苏州">江苏苏州</option>
							<option value="江苏镇江">江苏镇江</option>
							<option value="湖北宜昌">湖北宜昌</option>
							<option value="湖北孝感">湖北孝感</option>
							<option value="河北廊坊">河北廊坊</option>
							<option value="山东淄博">山东淄博</option>
							<option value="海南三亚">海南三亚</option>
							<option value="其它地区">其它地区</option>
					</select> <c:if test="${zzjgId=='test001'}">&nbsp;&nbsp;发布单位: 
<%--					<input name="positionZsdw" type="text" style="width: 100px;"--%>
							<%--								class="inputText" id="positionZsdw" />--%>
							<select name="positionZsdw" id="positionZsdw">
								<option value=""></option>
								<option value="集团公司总部">集团公司总部</option>
								<c:forEach items="${zzjgFOList}" var="fmap">
									<option value="${fmap.zzjgDwmc}">&nbsp;&nbsp;&nbsp;&nbsp;${fmap.zzjgDwmc}</option>
									<c:forEach items="${zzjgSunList}" var="map">
										<c:if test="${fmap.zzjgId eq map.zzjgSjdw}">
											<option value="${map.zzjgDwmc}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.zzjgDwmc}</option>
										</c:if>
									</c:forEach>
								</c:forEach>
							</select>
						</c:if>&nbsp;&nbsp;<input name="button" type="button"
						onclick="loadData()" class="inputButton" value="查询" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>职位管理查询结果</caption>
			<thead>
				<tr>
					<th>职位名称</th>
					<th>职位类别</th>
					<th>招聘专业</th>
					<th width="68">工作地点</th>
					<th width="68">学历要求</th>
					<th width="68">需求人数</th>
					<th>招聘分类</th>
					<th>发布单位</th>
					<th width="100">发布时间</th>
					<th width="68">职位状态</th>
					<th width="60" class="alignCenter">修改</th>
					<th width="60" class="alignCenter">删除</th>
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
					id="count">0</span> 条记录</td>
			</tr>
		</table>
	</div>
</body>
</html>
