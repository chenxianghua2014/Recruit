<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>中国航天科工集团公司人才招聘平台</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="css/main.css" rel="stylesheet" />
<link type="text/css" href="resources/jBox/Skins/Blue/jbox.css" rel="stylesheet"></link>
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/Main.js"></script>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div class="wrap clr" align="center">
		<div class="login">
			<div class="loginTop">应聘者登录</div>
			<div class="loginLine">
				<img src="images/line01.png" />
			</div>
			<iframe src="LoginFrmFirst" id="LoginFrmFirst" name="LoginFrmFirst" width="100%" height="200" frameborder="0" allowtransparency="true"></iframe>
		</div>
		<%--<div class="information">
			<div class="infMore">
				<span style="cursor:pointer;"
					onclick="window.location.href='InformationCenter'">&gt;&gt;更多</span>
			</div>
			<div class="infTop">招聘新闻</div>
			<div class="infLine">
				<img src="images/line02.png" />
			</div>
			<div class="infList">
				<ul>
					<c:forEach items="${ mv.model['zpxw']}" var="map"
						varStatus="status">
						<c:choose>
							<c:when test="${ map.zzjgId == 'test001'}">
								<li><a style="color: red;" href="ViewNews?id=${map.zpxwId}">${map.zpxwTitle}</a><span
									style="color: red;">${map.zpxwFbsj}</span>
								</li>
							</c:when>
							<c:otherwise>
								<li><a href="ViewNews?id=${map.zpxwId}">${map.zpxwTitle}</a><span>${map.zpxwFbsj}</span>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="LeftBtn">
			<a href="javascript:void(0);"
				onclick="javascript:window.location='MyRecruit'">我的求职</a> <a
				href="javascript:void(0);"
				onclick="javascript:window.location='MyApplication'">网上测评预约</a> <a
				href="javascript:void(0);">网上测评</a>
		</div>
	--%>
	</div>
	<%--<div class="wrap clr">
		<div class="companyname" style="margin-right:20px;">
			<div class="comTitle">
				<img src="images/Culturetitle.png" style="padding-right:5px;" />招聘单位
			</div>
			<div class="comCon">
				<ul>
					<c:forEach items="${ mv.model['zzjg']}" var="map"
						varStatus="status">
						<li class="menu2" onmouseover="this.className='menu1'"
							onmouseout="this.className='menu2'"><a
							href="javascript:void(0);" onmouseover="this.className='Af'"
							onmouseout="this.className='AfNone'" class="Af"
							onclick="window.location.href='InstituteOf?id=${map.zzjgId}'">${map.zzjgDwmc}</a>
							<div class="list">
								<ul style="width:412px;">
									<c:forEach items="${ mv.model['zzjg3']}" var="zzjg3"
										varStatus="status">
										<c:choose>
											<c:when test="${map.zzjgId eq zzjg3.zzjgSjdw}">
												<li><a href="javascript:void(0);"
													onclick="window.location.href='InstituteOf?id=${zzjg3.zzjgId}'">${zzjg3.zzjgDwmc}</a>
												</li>
											</c:when>
										</c:choose>
									</c:forEach>
								</ul>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="companyname">
			<div class="comTitle">
				<img src="images/Culturetitle.png" style="padding-right:5px;" />招聘专业
			</div>
			<div class="comCon">
				<ul>
					<c:forEach items="${ mv.model['zylb']}" var="map"
						varStatus="status">
						<li class="menu2" onmouseover="this.className='menu1'"
							onmouseout="this.className='menu2'"><a
							href="javascript:void(0);" onmouseover="this.className='Af'"
							onmouseout="this.className='AfNone'" class="Af"
							onclick="window.location.href='SearchPosition?zpzy='+escape('${ map.zylbName}')">${
								map.zylbName}</a>
							<div class="list">
								<ul style="width:412px;">
									<c:forEach items="${ map.zpzys}" var="mapChi"
										varStatus="status">
										<li><a href="javascript:void(0);"
											onclick="window.location.href='SearchPosition?zpzy='+escape('${ mapChi.zpzyName}')">${
												mapChi.zpzyName}</a></li>
									</c:forEach>
								</ul>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="companyname1">
			<div class="comTitle1">
				<img src="images/Culturetitle.png" style="padding-right:5px;" />职位搜索
			</div>
			<div class="search">
				<table width="100%">
					<tr>
						<td>招聘单位</td>
						<td><input type="text" style="width: 146px;" id="zpdw"
							name="zpdw" />
						</td>
						<td>招聘专业</td>
						<td><input type="text" style="width: 146px;" id="zpzy"
							name="zpzy" />
						</td>
						<td>职位类别</td>
						<td><select style="width: 150px;" id="zwlb" name="zwlb">
								<option></option>
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
						</td>
					</tr>
					<tr>
						<td>工作地点</td>
						<td><select style="width: 150px;" id="gzdd" name="gzdd">
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
						</td>
						<td>学历要求</td>
						<td><select style="width: 150px;" id="xlyq" name="xlyq">
								<option value=""></option>
								<option value="大专">大专</option>
								<option value="本科">本科</option>
								<option value="硕士">硕士</option>
								<option value="博士">博士</option>
						</select>
						</td>
						<td>发布时间</td>
						<td><select name="dt" id="dt" style="width: 150px;">
								<option value=""></option>
								<option value="30">近一个月内</option>
								<option value="90">近三个月内</option>
								<option value="180">近六个月内</option>
						</select>
						</td>
					</tr>
					<tr>
						<td colspan="5"></td>
						<td class="jobsea"><a href="javascript:void(0);"
							onclick="seachPosition();"><span>职位搜索</span> </a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	--%>
	<div id="FriendLink">
		<table>
			<tr align="center">
				<td align="left">&nbsp;&nbsp;友情链接：</td>
				<td>
					<select style="width:440px;" name="csic1" id="csic1"
						onchange="if(this.selectedIndex!=0)window.open(this.options[this.selectedIndex].value,'','')">
						<option>中国航天科工网站群</option>
					</select>
				</td>
				<td>
					<select style="width:440px;" name="csic2" id="csic2"
						onchange="if(this.selectedIndex!=0)window.open(this.options[this.selectedIndex].value,'','')">
						<option>军工集团</option>
					</select>
				</td>
			</tr>
		</table>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>
