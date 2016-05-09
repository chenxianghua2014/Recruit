<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>中国航天科工集团公司人才招聘平台</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="resources/pagination/pagination.css" type="text/css"></link>
<link type="text/css" href="resources/jBox/Skins/Blue/jbox.css" rel="stylesheet"></link>
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript" src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="js/SearchPosition.js"></script>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div class="wrap clr">
		<div class="menu">
			<div class="menuTop">
				<img src="images/title03.png" />
			</div>
			<ul>
				<li><a href="SearchEnterprise" >招聘日程</a></li>
				<li><a href="SearchPosition" style="color:#0046ae;">招聘职位</a></li>
				<li><a href="MyRecruit" >我的求职</a></li>
			</ul>
		</div>
		<div class="content">
			<div class="conTop">
				<span>您的当前位置：首页 >> 校园招聘>> 招聘职位</span> <img src="images/tag04.png" />&nbsp;
				<strong>查询职位</strong>Search Position
			</div>
			<div class="main">
				<div class="Possearch cls">
					<div class="seacriteria">
						<p>招聘单位:</p>
						<input name="zpdw" id="zpdw" type="text" style="width:236px" />
					</div>
					<div class="seacriteria">
						<p>招聘专业:</p>
						<input type="text" name="zpzy" id="zpzy" style="width:236px" /> </select>
					</div>
					<div class="seacriteria">
						<p>职位类别:</p>
						<select name="zwlb" id="zwlb" style="width:240px">
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
						</select>
					</div>
					<div class="seacriteria">
						<p>工作地点:</p>
						<select name="gzdd" id="gzdd" style="width:240px">
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
						</select>
					</div>
					<div class="seacriteria">
						<p>学历要求:</p>
						<select name="xlyq" id="xlyq" style="width:240px">
							<option value=""></option>
							<option value="大专">大专</option>
							<option value="本科">本科</option>
							<option value="硕士">硕士</option>
							<option value="博士">博士</option>
						</select>
					</div>
					<div class="seacriteria">
						<p>发布时间:</p>
						<select name="dt" id="dt" style="width:240px">
							<option value="365"></option>
							<option value="30">近一个月内</option>
							<option value="90">近三个月内</option>
							<option value="180">近六个月内</option>
						</select>
					</div>
					<div class="seabtn">
						<a href="javascript:void(0);" onclick="loadData()">搜索</a>
					</div>
				</div>
				<div class="result">
					<div class="resultTitle">
						<img src="images/tag05.png" />搜索结果
					</div>
					<table width="800px">
						<thead>
							<tr>
								<td style="width:90px;">职位名称</td>
								<td style="width:70px;">职位类别</td>
								<td style="width:60px;">学历要求</td>
								<td style="width:80px;">招聘专业</td>
								<td style="width:30px;">人数</td>
								<td style="width:50px;">招聘类别</td>
								<td style="width:110px;">招聘单位</td>
								<td style="width:60px;">工作地点</td>
								<td style="width:75px;">发布时间</td>
								<td style="width:75px;">职位状态</td>
								<td style="width:90px">&nbsp;</td>
							</tr>
						</thead>
						<tbody id="tbList"></tbody>
					</table>
					<table width="800px">
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
			</div>
		</div>
	</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>