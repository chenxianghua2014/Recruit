<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>预览简历</title>
<link rel="stylesheet" href="skins/default/main.css" type="text/css" />
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.form.js"></script>
<script type="text/javascript">
	//证书以及IT技能页面变化
	function meizz(e) {
		var resumeId = $("#resumeId").val();
		e.href = "uppics/" + resumeId + ".doc";
	}
	//AJAX请求PDF导出
	function newPDF() {
		window.open("DownloadResume?id=" + $("#resumeId").val());
	}
	//AJAX请求PDF导出
	function newPDFHR() {
		var options = {
			type : 'get',
			url : "newPDFHR",
			success : showResponseMov,
			error : showResponseMov,
			clearForm : false
		};
		$("#ff").ajaxSubmit(options);
		function showResponseMov() {
			window.location.href = "DownloadResumeHr?id="
					+ $("#resumeId").val() + "&jtjlkId=" + $("#jtjlkId").val();
		}
	}
	function newPDFjtjlk() {
		var options = {
			type : 'get',
			url : "newPDFjtjlk",
			success : showResponseMov,
			error : showResponseMov,
			clearForm : false
		};
		$("#ff").ajaxSubmit(options);
		function showResponseMov() {
			window.location.href = "DownloadResumeHrJtjlk?id="
					+ $("#resumeId").val();
		}
	}

	/*============获取地址===========*/
	function Dialog(a) {
		//获取当前网址，如： http://localhost:8088/test/test.jsp
		var curPath = window.document.location.href;
		//获取主机地址之后的目录，如： test/test.jsp
		var pathName = window.document.location.pathname;
		var pos = curPath.indexOf(pathName);
		//获取主机地址，如： http://localhost:8088
		var localhostPaht = curPath.substring(0, pos);
		//获取带"/"的项目名，如：/test
		var projectName = pathName.substring(0,
				pathName.substr(1).indexOf('/') + 1);
		var lujing = localhostPaht + projectName;
		var newlujing = lujing + "/" + a;
		window.showModalDialog(newlujing);
	}
</script>

</head>
<body style="margin: 0px;">
	<input type="hidden" id="resumeId" name="resumeId" value="${mv.model['resume'].resumeId}" />
	<ul></ul>

	<div id="ul1" class="editBlock" style="height: 640px;">
		<div id="div1">
			<table>
				<tbody>
					<tr>
						<td colspan="2" class="subtitle">基本信息</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="_div1">
			<table>
				<tbody>
					<tr>
						<th style="width: 155px; height: 27px">姓名</th>
						<td>${ mv.model['resume'].resumeName }</td>
					</tr>
					<tr>
						<th>性别</th>
						<td>${ mv.model['resume'].resumeSex }</td>
						<td rowspan="4" style="width: 90px">
							<img src="${ mv.model['resume'].resumePhotos }" width="90" height="100">
						</td>
					</tr>
					<tr>
						<th>民族</th>
						<td>${ mv.model['resume'].resumeItjtjn }</td>
					</tr>
					<tr>
						<th>出生日期</th>
						<td>${ mv.model['resume'].resumeBirthday }</td>
					</tr>
					<tr>
						<th>身份证号</th>
						<td>${ mv.model['resume'].resumeSfzh }</td>
					</tr>
					<tr>
						<th>籍贯</th>
						<td>${ mv.model['resume'].resumeHdzs }-${
							mv.model['resume'].resumeQtzs }</td>
					</tr>
					<tr>
						<th>户口地</th>
						<td>${ mv.model['resume'].resumeMqszcsProvince }-${
							mv.model['resume'].resumeMqszcsCity }</td>
					</tr>
					<tr>
						<th>出生地</th>
						<td>${ mv.model['resume'].resumeRxqhkszcsProvince }-${
							mv.model['resume'].resumeRxqhkszcsCity }</td>
					</tr>
					<tr>
						<th>健康状况</th>
						<td>${ mv.model['resume'].resumeItsxjn }</td>
					</tr>
					<tr>
						<th>入党时间</th>
						<td>${ mv.model['resume'].resumeQtjn }</td>
					</tr>
					<tr>
						<th>参加工作时间</th>
						<td>${ mv.model['resume'].resumeCjgzsj }</td>
					</tr>
					<tr>
						<th>拟应聘岗位1</th>
						<td>${ mv.model['resume'].resumeNypgw1 }</td>
					</tr>
					<tr>
						<th>拟应聘岗位2</th>
						<td>${ mv.model['resume'].resumeNypgw2 }</td>
					</tr>

				</tbody>
			</table>
		</div>
		<div id="div2">
			<table>
				<tbody>
					<tr>
						<td colspan="2" class="subtitle">联系方式</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="_div2">
			<div>
				<input type="hidden" id="resumeId" name="resumeId" value="${ resume.resumeId }" />
			</div>
			<table>
				<tr>
					<th style="width: 155px; height: 27px">手机号码</th>
					<td>${ mv.model['resume'].resumeTelphone }</td>
				</tr>
				<tr>
					<th>电子邮箱</th>
					<td colspan="2">${ mv.model['resume'].resumeEmail }</td>
				</tr>
			</table>
		</div>



		<div id="div3">
			<table>
				<tbody>
					<tr>
						<td colspan="2" class="subtitle">教育经历</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="ddiv3">
			<c:forEach items="${ mv.model['Jyjl']}" var="map" varStatus="status">
				<table>
					<%--<tr>
						<th style="width: 155px; height: 27px">就读时间</th>
						<td>${map.resumeJdsj}到${map.resumeJdsj1}</td>
					</tr>
					--%>
					<tr>
						<th style="width: 155px; height: 27px">学校名称</th>
						<td>${map.resumeXxmc}</td>
					</tr>
					<tr>
						<th>专业</th>
						<td>${map.resumeZyl}&nbsp;&nbsp;${map.resumeZy}</td>
					</tr>
					<tr>
						<th>学历</th>
						<td>${map.resumeXl}</td>
					</tr>
					<%--<tr>
						<th>专业描述</th>
						<td colspan="3">${map.resumeZyms}</td>
					</tr>
					<tr>
						<th>院系排名</th>
						<td colspan="3">${map.resumeYxpm}</td>
					</tr>
					<tr>
						<th>班级排名</th>
						<td colspan="3">${map.resumeBjpm}</td>
					</tr>
				--%>
				</table>
			</c:forEach>
		</div>

		<div id="div11">
			<table>
				<tbody>
					<tr>
						<td colspan="2" class="subtitle">语言能力</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="ddiv11">

			<c:forEach items="${ mv.model['Yynl']}" var="map" varStatus="status">
				<table>
					<tr>
						<th style="width: 155px; height: 27px">外语种类</th>
						<td>${map.resumeYylb}</td>
					</tr>
					<%--<tr>
						<th>听说能力</th>
						<td colspan="3">${map.resumeTsnl}</td>
					</tr>
					<tr>
						<th>读写能力</th>
						<td colspan="3">${map.resumeDxnl}</td>
					</tr>
					<tr>
						<th>等级考试</th>
						<td>${map.resumeDjks}</td>
					</tr>
					--%>
					<tr>
						<th>外语水平</th>
						<td>${map.resumeYyfs}</td>
					</tr>
				</table>
			</c:forEach>
		</div>


		<div id="div8">
			<table>
				<tbody>
					<tr>
						<td colspan="2" class="subtitle">工作经历</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="ddiv8">

			<c:forEach items="${ mv.model['Gzjl']}" var="map" varStatus="status">
				<table>
					<tr>
						<th style="width: 155px; height: 27px">工作单位</th>
						<td>${map.resumeGzgs}</td>
					</tr>
					<tr>
						<th>工作时间</th>
						<td colspan="3">${map.resumeGzsj}到${map.resumeGzsj1}</td>
					</tr>
					<tr>
						<th>职务</th>
						<td colspan="3">${map.resumeGzcs}</td>
					</tr>
					<tr>
						<th>职称（资格）</th>
						<td colspan="3">${map.resumeGzbm}</td>
					</tr>
					<%--<tr>
						<th>工作职位</th>
						<td>${map.resumeGzzw}</td>
					</tr>
					<tr>
						<th>工作行业</th>
						<td colspan="3">${map.resumeGzhy}</td>
					</tr>
					
					--%>
					<tr>
						<th>工作描述</th>
						<td>${map.resumeGzms}</td>
					</tr>
				</table>
			</c:forEach>
		</div>


		<%--<div id="div4">
			<table>
				<tbody>
					<tr>
						<td colspan="2" class="subtitle">校内奖励</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="ddiv4">
			<c:forEach items="${ mv.model['Xnjl']}" var="map" varStatus="status">
				<table>
					<tr>
						<th style="width: 155px; height: 27px">奖项名称</th>
						<td>${map.resumeJxmc}</td>
					</tr>
					<tr>
						<th>获奖时间</th>
						<td colspan="3">${map.resumeHjsj}</td>
					</tr>
					<tr>
						<th>奖励说明</th>
						<td colspan="3">${map.resumeJlsm}</td>
					</tr>
				</table>
			</c:forEach>
		</div>

		<div id="div5">
			<table>
				<tbody>
					<tr>
						<td colspan="2" class="subtitle">校内职务</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="ddiv5">
			<c:forEach items="${ mv.model['Xnzw']}" var="map" varStatus="status">
				<table>
					<tr>
						<th style="width: 155px; height: 27px">校内职务名称</th>
						<td>${map.resumeXnzwmc}</td>
					</tr>
					<tr>
						<th>任职时间</th>
						<td colspan="3">${map.resumeRzsj}到${map.resumeRzsj1}</td>
					</tr>
					<tr>
						<th>职责和业绩</th>
						<td colspan="3">${map.resumeZzhyj}</td>
					</tr>
				</table>
			</c:forEach>
		</div>

		<div id="div6">
			<table>
				<tbody>
					<tr>
						<td colspan="2" class="subtitle">实践经历</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="ddiv6">
			<c:forEach items="${ mv.model['Sjjl']}" var="map" varStatus="status">
				<table>
					<tr>
						<th style="width: 155px; height: 27px">实践名称</th>
						<td>${map.resumeSjmc}</td>
					</tr>
					<tr>
						<th>实践时间</th>
						<td colspan="3">${map.resumeSjsj}到${map.resumeSjsj1}</td>
					</tr>
					<tr>
						<th>实践描述</th>
						<td colspan="3">${map.resumeSjms}</td>
					</tr>
				</table>
			</c:forEach>
		</div>

		<div id="div7">
			<table>
				<tbody>
					<tr>
						<td colspan="2" class="subtitle">实习经历</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="ddiv7">
			<c:forEach items="${ mv.model['Sxjl']}" var="map" varStatus="status">
				<table>
					<tr>
						<th style="width: 155px; height: 27px">实习单位</th>
						<td>${map.resumeSxgs}</td>
					</tr>
					<tr>
						<th>实习城市</th>
						<td colspan="3">${map.resumeSxcs}</td>
					</tr>
					<tr>
						<th>实习部门</th>
						<td colspan="3">${map.resumeSxbm}</td>
					</tr>
					<tr>
						<th>实习职位</th>
						<td>${map.resumeSxzw}</td>
					</tr>
					<tr>
						<th>实习行业</th>
						<td colspan="3">${map.resumeSxhy}</td>
					</tr>
					<tr>
						<th>实习时间</th>
						<td colspan="3">${map.resumeSxsj}到${map.resumeSxsj1}</td>
					</tr>
					<tr>
						<th>实习描述</th>
						<td>${map.resumeSxms}</td>
					</tr>
				</table>
			</c:forEach>
		</div>


		<div id="div9">
			<table>
				<tbody>
					<tr>
						<td colspan="2" class="subtitle">项目经验</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="ddiv9">

			<c:forEach items="${ mv.model['Xmjy']}" var="map" varStatus="status">
				<table>
					<tr>
						<th style="width: 155px; height: 27px">项目名称</th>
						<td>${map.resumeXmmc}</td>
					</tr>
					<tr>
						<th>团队规模</th>
						<td colspan="3">${map.resumeTdgm}</td>
					</tr>
					<tr>
						<th>项目简介</th>
						<td colspan="3">${map.resumeXmjj}</td>
					</tr>
					<tr>
						<th>项目角色</th>
						<td>${map.resumeXmjs}</td>
					</tr>
					<tr>
						<th>参与时间</th>
						<td colspan="3">${map.resumeCysj}到${map.resumeCysj1}</td>
					</tr>
					<tr>
						<th>项目成果</th>
						<td colspan="3">${map.resumeXmcg}</td>
					</tr>
					<tr>
				</table>
			</c:forEach>
		</div>

		--%>
		<div id="div10">
			<table>
				<tbody>
					<tr>
						<td colspan="2" class="subtitle">家庭关系</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="ddiv10">

			<c:forEach items="${ mv.model['Pxjl']}" var="map" varStatus="status">
				<table>
					<tr>
						<th style="width: 155px; height: 27px">关系</th>
						<td>${map.resumePxmc}</td>
					</tr>
					<tr>
						<th>姓名</th>
						<td colspan="3">${map.resumePxjg}</td>
					</tr>
					<tr>
						<th>出生年月</th>
						<td colspan="3">${map.resumePxsj}</td>
					</tr>
					<tr>
						<th>政治面貌</th>
						<td colspan="3">${map.resumePxsj1}</td>
					</tr>
					<tr>
						<th>工作单位及职务</th>
						<td>${map.resumePxnr}</td>
					</tr>
				</table>
			</c:forEach>
		</div>



		<%--<div id="div12">
			<table>
				<tbody>
					<tr>
						<td colspan="2" class="subtitle">证书</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="ddiv12">
			<c:forEach items="${ mv.model['Zs']}" var="map" varStatus="status">
				<table>
					<tr>
						<th style="width: 155px; height: 27px">获得证书</th>
						<td>${map.resumeHdzs}</td>
					</tr>
					<tr>
						<th>其他证书</th>
						<td colspan="3">${map.resumeQtzs}</td>
					</tr>
				</table>
			</c:forEach>
		</div>

		<div id="div13">
			<table>
				<tbody>
					<tr>
						<td colspan="2" class="subtitle">IT技能</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="ddiv13">
			<c:forEach items="${ mv.model['ITjn']}" var="map" varStatus="status">
				<table>
					<tr>
						<th style="width: 155px; height: 27px">IT精通技能</th>
						<td>${map.resumeITjtjn}</td>
					</tr>
					<tr>
						<th>IT熟悉技能</th>
						<td colspan="3">${map.resumeITsxjn}</td>
					</tr>
					<tr>
						<th>其他技能</th>
						<td colspan="3">${map.resumeQtjn}</td>
					</tr>
				</table>
			</c:forEach>
		</div>

		--%>
		<div id="div14">
			<table>
				<tbody>
					<tr>
						<td colspan="2" class="subtitle">其他信息</td>
					</tr>
				</tbody>
			</table>
		</div>
		<table width="100%" border="1" class="admintable">
			

			<tr height="50px">
				<td width="20%">奖惩情况</td>
				<td width="80%">${ mv.model['resume'].resumeJcqk }</td>

			</tr>
			<tr height="50px">
				<td width="20%">培训情况</td>
				<td width="80%">${ mv.model['resume'].resumePxqk }</td>

			</tr>
			<tr height="50px">
				<td width="20%">熟悉何种专业技术及有何种特长</td>
				<td width="80%">${ mv.model['resume'].resumeSxhzjn }</td>

			</tr>
			<tr height="50px">
				<td width="20%">兴趣爱好</td>
				<td width="80%">${ mv.model['resume'].resumeXqah }</td>

			</tr>
		</table>
		<%-- <div id="ddiv14">
			<c:forEach items="${ mv.model['Qtxx']}" var="map" varStatus="status">
				<table>
					<tbody>
						<tr>
							<th style="width: 155px; height: 27px">其他信息类别</th>
							<td>${ map.resumeQtxxlb1 }</td>
						</tr>
						<tr>
							<th>信息描述</th>
							<td colspan="3">${ map.resumeXxms }</td>
						</tr>
					<tbody>
				</table>
			</c:forEach>
		</div> --%>

		<%--<div id="div15">
			<table>
				<tbody>
					<tr>
						<td colspan="2" class="subtitle">附件/作品</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<table>
				<tbody>
					<tr>
						<c:choose>
							<c:when
								test='${mv.model["resume"].resumeZzjl == null || fn:trim(mv.model["resume"].resumeZzjl) == ""}'>
							</c:when>
							<c:otherwise>
								<th style="width: 155px; height: 27px">自制个人简历</th>
								<td colspan="4"><a
									href="${mv.model['resume'].resumeZzjl}">自制个人简历</a></td>
							</c:otherwise>
						</c:choose>
					</tr>
				<tbody>
			</table>
		</div>
		<div id="ddiv15">
			<c:forEach items="${ mv.model['Fj']}" var="map" varStatus="status">
				<table id="wenjian">
					<script type="text/javascript">
						var name = "${ map.resumeFj }";
						if (name.indexOf(".pdf") != -1) {
							$("#wenjian")
									.append(
											"<tr>"
													+ "<th  style=\"width: 155px; height: 27px\">"
													+ "已上传文件"
													+ "</th>"
													+ "<td>"
													+ "<a href=\"javascript:void(0)\" onclick=\"window.open('${ map.resumeFj }')\">"
													+ "点击查看已上传文件" + "</a>"
													+ "</td>" + "</tr>")
						} else {
							$("#wenjian")
									.append(
											"<tr>"
													+ "<th  style=\"width: 155px; height: 27px\">"
													+ "已上传文件"
													+ "</th>"
													+ "<td>"
													+ "<img src='${ map.resumeFj }' width=\"150px\" height=\"100px\" onclick=\"window.open('${map.resumeFj}')\" >"
													+ "</td>" + "</tr>")
						}
					</script>
					</tr>
				</table>
			</c:forEach>
		</div>
		--%>
		<form name="ff" id="ff">
			<input type="hidden" id="resumeId" name="resumeId" value="${mv.model['resume'].resumeId}" />
			<table style="width: 100%;text-align: center;">
				<tr>
					<td class="toolbar" style="width: 200px">
						<c:choose>
							<c:when test="${mv.model['JtjlkId'] eq null}">
								<input type="button" onclick="newPDFHR()" class="inputButton" value="下载简历" />
							</c:when>
							<c:when test="${mv.model['JtjlkId'] eq 'jtjlk'}">
								<input type="button" onclick="newPDFHR()" class="inputButton" value="下载简历" />
							</c:when>
							<c:otherwise>
								<input type="hidden" id="jtjlkId" name="jtjlkId" value="${mv.model['JtjlkId']}" />
								<input type="button" onclick="newPDFHR()" class="inputButton" value="下载简历" />
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

