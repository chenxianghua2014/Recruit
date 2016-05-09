<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<style style="text/css">
body {
	margin: 0px;
	padding: 0px;
	font-family: "微软雅黑";
	min-width: 784px;
}

li {
	font-size: 14px;
}

.Recruitment {
	width: 100%;
	height: 370px;
	*height: 404px;
	border: 1px solid #CCC;
}

.title {
	width: 100%;
	height: 37px;
	background: url(images/login_titlebg.gif) repeat-x;
	font-size: 14px;
	line-height: 37px;
	position: relative;
}
.title span{ display: block; width:40px; height:30px; position: absolute;top:0px; right:0px;}

.Recruitment .content {
	padding: 0px 15px;
}

.Recruitment .content p {
	line-height: 25px;
	text-indent: 2em;
	font-size: 12px;
}

.right {
	width: 100%;
}

.right .backlog {
	width: 100%;
	height: 180px;
	border: 1px solid #CCC;
	min-width: 220px;
}

.community {
	border: 1px solid #CCC;
}

.community ul li {
	height: 30px;
	line-height: 30px;
	border-bottom: 1px dashed #CCCCCC;
	width: 95%;
	list-style: none;
}

.community ul li span {
	font-size: 12px;
	float: right;
	color: #666;
}

.community .title span a {
	font-size: 12px;
	text-decoration: none;
	color: #307fb1;
}

.community .title span {
	float: right;
	padding-right: 15px;
}

.backlog_con p {
	text-align: left;
	font-size: 14px;
	line-height: 20px;
	border-bottom: 1px dashed #ccc;
	position: relative;
	padding-left: 10px;
}

.backlog_con p span {
	width: 60px;
	height: 20px;
	line-height: 15px;
	position: absolute;
	top: 0px;
	right: 0px;
}

.null p {
	font-size: 16px;
	color: #ccc;
	text-align: center;
	padding-top: 50px;
}

a {
	text-decoration: none;
	font-size: 12px;
	color: #000;
}

a:hover {
	font-size: 12px;
	color: #0081cc;
}
.comname{ color:#0081cc;}
.index_List li{ position: relative;}
.index_List li span{ display:block; width:120px; height:30px; position: absolute; top:0px; right:0px;}
</style>
</head>
<body>
	<table border="0" width="100%" align="center">
		<tr>
			<td rowspan="2" style="width: 60%;">
				<div class="Recruitment">
					<div class="title">&nbsp;&nbsp;招聘日程</div>
					<div class="content" style="height:333px;overflow-y:auto;">
						${ mv.model["zpxw"].getZpxwContent() }</div>
				</div>
			</td>
			<td style="width: 40%; height: 186px;">
				<div class="right">
					<div class="backlog">
						<div class="title">&nbsp;&nbsp;待办事项</div>
						<div class="backlog_con">
							<c:if test="${loginSession.zzjgSjdw =='test001'}">
								<p>
									<a href="XcxxCheck">宣传信息待审核(${ mv.model["xcxx"] })</a><span><a
										href="XcxxCheck">更多>></a>
									</span>
								</p>
								<p>
									<a href="articlesh">社区信息待审核(${
										mv.model["articleDshCount"]})</a><span><a href="articlesh">更多>></a>
									</span>
								</p>
							</c:if>
							<p>
								<a href="Msqgl">面试圈账号待删除(${ mv.model["waitDeleteCount"] })</a><span><a
									href="Msqgl">更多>></a>
								</span>
							</p>
							<p>
								<a href="Xxtz?status=Faild">消息通知发送失败(${
									mv.model["msgFailed"] })</a><span><a href="Xxtz">更多>></a>
								</span>
							</p>
						</div>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="height: 186px;"><div class="right">
					<div class="backlog">
						<div class="title">&nbsp;&nbsp;过程管理</div>
						<div class="backlog_con">
							<p>
								<a href="Xdjl">新到简历(${ mv.model["xdjl"] })</a><span><a
									href="Xdjl">更多>></a>
								</span>
							</p>
							<p>
								<a href="Ytzcpjl">新到测评成绩(${ mv.model["cpjl"] })</a><span><a
									href="Ytzcpjl">更多>></a>
								</span>
							</p>
							<p>
								<a href="Yapmsjl">新到面试结果(${ mv.model["msjl"] })</a><span><a
									href="Yapmsjl">更多>></a>
								</span>
							</p>
							<p>
								<a href="bzmember">预付费余额不足(${ mv.model["hyCount"] })</a><span><a
									href="bzmember">更多>></a>
								</span>
							</p>
						</div>
					</div>
				</div></td>
		</tr>
		<tr>
			<td colspan="2"><div class="community">
					<div class="title">
						&nbsp;&nbsp;社区动态<span><a href="htBbs">更多</a> </span>
					</div>
					<ul class="index_List">
						<c:if test="${empty mv.model['reviewList']}">
							<li>没有人回复/评论您的帖子。</li>
						</c:if>
						<c:forEach items="${ mv.model['reviewList']}" var="map"
							varStatus="status">
							<li style="heigth:50px;">
								<strong class='comname'>${map.reviewPlr}：</strong>回复/评论了您的:&nbsp;&nbsp;<a href="htqueryBbsById?bbsId=${map.bbsId}">${map.bbsNr}</a>
								<span>${map.plsj}</span></li>
						</c:forEach>
					</ul>
				</div></td>
		</tr>
	</table>
</body>
</html>