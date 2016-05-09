<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>中国航天科工集团公司人才招聘平台</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="css/style.css" rel="stylesheet" />
<link type="text/css" href="resources/jBox/Skins/Blue/jbox.css" rel="stylesheet"></link>
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript">
function popbox(url){
	$.jBox("iframe:"+url,{
		title:'岗位信息',
		width:500,
		height:400,
		buttons:{'关闭':true}
	});
}

</script>
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
				<li><a href="SearchPosition" >招聘职位</a></li>
				<li><a href="MyRecruit" >我的求职</a></li>
			</ul>
		</div>
		<div class="content">
			<div class="conTop">
				<span>您的当前位置：首页 >> 校园招聘 >> 我的求职 >> 我的申请</span>
				<img src="images/tag04.png" />&nbsp; <strong>我的求职</strong>My Recruit
			</div>
			<div class="result">
				<div class="Tabbg">
					<a href="MyRecruit">我的简历</a> <a href="MyApplication"
						class="Tabactive">我的申请</a><a href="MyCollection">我的收藏</a><a href="Message">消息中心</a>
				</div>
				<div class="resultTitle">
					<img src="images/tag05.png" />我的申请记录
				</div>
				<div class="subtitle_pl">
					<table width="800px">
						<thead>
							<tr>
								<td>申请单位</td>
								<td>申请岗位</td>
								<td>简历状态</td>
								<td>考试类型</td>
								<td>网上测评预约</td>
								<td>打印准考证</td>
								<td>撤销申请</td>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${userLoginSession eq null}">
									<script type="text/javascript">
										$.jBox.open("iframe:LoginFrm", "用户登录", 280, 200, { buttons : {}});
									</script>
								</c:when>
								<c:otherwise>
									<c:forEach items="${ Applications}" var="map"
										varStatus="status">
										<tr>
											<td>${ map.sqdw}</td>
											<td><a href="javascript:popbox('ZwglView?id=${map.pid}');">${ map.sqgw}</a></td>
											<td>${ map.jlsx}</td>
											<td>${ map.apcpgwlb}</td>
											<c:choose>
												<c:when test="${map.apcpgwlb eq null}">
													<td>--</td>
													<td>--</td>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when test="${ map.bmglid eq null}">
															<td><a href="Xzcc?id=${ map.jtjlkid}">网上测评预约</a></td>
															<td>--</td>
														</c:when>
														<c:otherwise>
															<c:choose>
																<c:when test="${ map.bmglprintflag eq 0}">
																	<td>已报名</td>
																</c:when>
																<c:otherwise>
																	<td><a href="Xzcc?id=${ map.jtjlkid}">网上测评预约</a>
																	</td>
																</c:otherwise>
															</c:choose>
															</td>
															<td><a href="PrintTestCard?id=${ map.bmglid}">打印准考证</a>
															</td>
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${map.cpcj eq '未通知'}">
													<td><a href="javascript:void(0)"
														onclick="Revoke('${ map.jtjlkid}','${ map.apcpgwlb}')">撤销申请</a>
													</td>
												</c:when>
												<c:when test="${map.cpcj eq '未完成'}">
													<td><a href="javascript:void(0)"
														onclick="Revoke('${ map.jtjlkid}','${ map.apcpgwlb}')">撤销申请</a>
													</td>
												</c:when>
												<c:otherwise>
													<td>不可用</td>
												</c:otherwise>
											</c:choose>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
<script type="text/javascript">
	function ShowLogin() {
		$.jBox.open("iframe:LoginFrm", "用户登录", 280, 200, {
			buttons : {}
		});
	}
	function ApplyPosition(id) {
		window.location.href = "ApplyPosition?id=" + id;
	}
	function CancelPosition(id) {
		window.location.href = "CancelPosition?id=" + id;
	}
	function Revoke(id, type) {
		if (confirm("您确认要撤销对该单位的职位申请吗 ？您已约考，本次申请撤销将取消您对" + type
				+ "岗位的约考记录，若还有其他单位通知您测评相同岗位，请您务必再次约考并打印准考证（原准考证做废），以免影响正常测评！"))
			if (confirm("您确认要撤销对该单位的职位申请吗 ？您已约考，本次申请撤销将取消您对"
					+ type
					+ "岗位的约考记录，若还有其他单位通知您测评相同岗位，请您务必再次约考并打印准考证（原准考证做废），以免影响正常测评！"))
				window.location.href = "Revoke?id=" + id;
	}
</script>
</html>