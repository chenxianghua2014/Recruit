<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.lang.* ,java.util.*, com.ttgis.recruit.domain.Tkgl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理岗考试</title>
<link href="css/cpgl/ht_cpstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet"
	href="resources/jBox/Skins/Blue/jbox.css" />
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript" language="javascript">
	$.document.ready(function() {
		alert("您已经被禁言，请联系管理员！");
		window.location.href = "article";
	});
	/*------判断radio是否有选中，获取选中的值--------*/
	var maxtime = 90 * 60;//半个小时，按秒计算，自己调整!
	function CountDown() {
		if (maxtime >= 0) {
			minutes = Math.floor(maxtime / 60);
			seconds = Math.floor(maxtime % 60);
			msg = minutes + "分" + seconds + "秒";
			document.all["timer"].innerHTML = msg;
			if (maxtime == 5 * 60)
				alert('距离考试结束还有5分钟，请您抓紧时间！');
			--maxtime;
		} else {
			clearInterval(timer);
			alert("时间到，结束!");
		}
	}
	timer = setInterval("CountDown()", 1000);
	$(function() {
		$("#btnSubmit").click(function() {
			alert("提交成功");
		});
	});
	function checkValidate() {
		// 		if (document.ff.${map.tkglId}.value == ""|| document.ff.${map.tkglId}.value == null) {
		// 			window.alert("您有没答的试题");
		// 		}
		// 		else if(document.ff.${amap.id}.value == "" || document.ff.${amap.id}.value == null){

		// 		}
		// 		else{
		if (Validator.Validate(document.getElementById('ff'), 3)) {
			document.ff.action = "TestSubmit";
			document.ff.method = "post";
			document.ff.submit();
		}
	}
	function JkglEngViewImage(jkId) {
		$.jBox.tip("正在生成PDF试卷，请耐心等待！", 'loading');
		window.location = "JkglEngViewImage?jkId=" + jkId;
		window.setTimeout(function() {
			$.jBox.tip('生成已完成。', 'success');
		}, 5000);
	}
</script>
</head>
<body>
	<div id="contentmain01">
		<div class="conRight">
			<div class="questions">
				<form name="ff" id="ff">
					<table>
						<tr>
							<th colspan="2"><h1>高校毕业生招聘考试——英文</h1>
							</th>
						</tr>
						<tr>
							<td colspan="2" align="center"><h2>第一部分 语言知识及应用</h2>
							</td>
						</tr>
						<tr>
							<td colspan="2"><h4>第一节 情景应用 阅读1-5问题框，选择正确的一个。</h4>
							</td>
						</tr>
						<c:forEach items="${jkglListJ}" var="jkgl" varStatus="status">
							<c:if test="${jkgl.jkglStlx eq 'J'}">
								<tr>
									<td width="20px;">${status.index+1}、</td>
									<td>${jkgl.jkglSttg}(${jkgl.jkglStda})</td>
								</tr>
								<tr>
									<td colspan="2"><input type="radio" id="a_1"
										name="${jkgl.jkglId}" value="A" />A.${jkgl.jkglStxxa}</td>
								</tr>
								<tr>
									<td colspan="2"><input type="radio" id="a_2"
										name="${jkgl.jkglId}" value="B" />B.${jkgl.jkglStxxb}</td>
								</tr>
								<tr>
									<td colspan="2"><input type="radio" id="a_3"
										name="${jkgl.jkglId}" value="C" />C.${jkgl.jkglStxxc}</td>
								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="2"><h4>第二节 单项选择 选择你认为正确的答案。</h4>
							</td>
						</tr>
						<c:forEach items="${jkglListK}" var="jkgl" varStatus="status">
							<c:if test="${jkgl.jkglStlx eq 'K'}">
								<tr>
									<td>${status.index+1}、</td>
									<td>${jkgl.jkglSttg}(${jkgl.jkglStda})</td>
								</tr>
								<tr>
									<td colspan="2"><input type="radio" id="a_1"
										name="${jkgl.jkglId}" value="A" />A.${jkgl.jkglStxxa}</td>
								</tr>
								<tr>
									<td colspan="2"><input type="radio" id="a_2"
										name="${jkgl.jkglId}" value="B" />B.${jkgl.jkglStxxb}</td>
								</tr>
								<tr>
									<td colspan="2"><input type="radio" id="a_3"
										name="${jkgl.jkglId}" value="C" />C.${jkgl.jkglStxxc}</td>
								</tr>
								<tr>
									<td colspan="2"><input type="radio" id="a_4"
										name="${jkgl.jkglId}" value="D" dataType="Group"
										msg="必须选定一个选项" />D.${jkgl.jkglStxxd}</td>

								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="2" align="center"><h2>第二部分 阅读</h2>
							</td>
						</tr>
						<tr>
							<td colspan="2"><h4>第一节 信息匹配 选择你认为正确的答案.</h4>
							</td>
						</tr>
						<c:forEach items="${jkglListL}" var="jkgl" varStatus="status">
							<c:if test="${jkgl.jkglStlx eq 'L'}">
								<tr>
									<td>${status.index+1}、</td>
									<td>${jkgl.jkglSttg}(${jkgl.jkglStda})</td>
								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="2"><h4>第二节 阅读理解
									请根据文章的内容，从题中所给的4个选项中选择1个最佳答案。</h4>
							</td>
						</tr>
						<c:forEach items="${jkglListM}" var="jkgl" varStatus="status">
							<c:if test="${jkgl.jkglStlx eq 'M-2'}">
								<tr>
									<td>${status.index+1}、</td>
									<td>${jkgl.jkglSttg}</td>
								</tr>
								<%
									int stbh = 1;
								%>
								<c:forEach items="${jkglFxtListM}" var="jkglFxt"
									varStatus="statusFxt">
									<c:if test="${jkgl.jkglStbh eq jkglFxt.stbh}">
										<tr>
											<td>(<%=stbh++%>)、</td>
											<td>${jkglFxt.sttg}(${jkglFxt.stda})</td>
										</tr>
										<tr>
											<td colspan="2"><input type="radio" id="a_1"
												name="${jkglFxt.id}" value="A" />A.${jkglFxt.stxxa}</td>
										</tr>
										<tr>
											<td colspan="2"><input type="radio" id="a_2"
												name="${jkglFxt.id}" value="B" />B.${jkglFxt.stxxb}</td>
										</tr>
										<tr>
											<td colspan="2"><input type="radio" id="a_3"
												name="${jkglFxt.id}" value="C" />C.${jkglFxt.stxxc}</td>
										</tr>
										<tr>
											<td colspan="2"><input type="radio" id="a_4"
												name="${jkglFxt.id}" value="D" dataType="Group"
												msg="必须选定一个选项" />D.${jkglFxt.stxxd}</td>
										</tr>
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="2"><h4>第三节 应用短文阅读</h4>
							</td>
						</tr>
						<c:forEach items="${jkglListNA}" var="jkgl" varStatus="status">
							<c:if test="${jkgl.jkglStlx eq 'N-A'}">
								<tr>
									<td>1、</td>
									<td>${jkgl.jkglSttg}</td>
								</tr>
								<%
									int stbh = 1;
								%>
								<c:forEach items="${jkglFxtListNA}" var="jkglFxt"
									varStatus="statusFxt">
									<c:if test="${jkgl.jkglStbh eq jkglFxt.stbh}">
										<tr>
											<td>(<%=stbh++%>)、</td>
											<td>${jkglFxt.sttg}(${jkglFxt.stda})</td>
										</tr>
										<tr>
											<td colspan="2"><input type="radio" id="a_1"
												name="${jkglFxt.id}" value="A" />A.${jkglFxt.stxxa}</td>
										</tr>
										<tr>
											<td colspan="2"><input type="radio" id="a_2"
												name="${jkglFxt.id}" value="B" />B.${jkglFxt.stxxb}</td>
										</tr>
										<tr>
											<td colspan="2"><input type="radio" id="a_3"
												name="${jkglFxt.id}" value="C" />C.${jkglFxt.stxxc}</td>
										</tr>
										<tr>
											<td colspan="2"><input type="radio" id="a_4"
												name="${jkglFxt.id}" value="D" dataType="Group"
												msg="必须选定一个选项" />D.${jkglFxt.stxxd}</td>
										</tr>
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach>
						<c:forEach items="${jkglListNB}" var="jkgl" varStatus="status">
							<c:if test="${jkgl.jkglStlx eq 'N-B'}">
								<tr>
									<td>2、</td>
									<td>${jkgl.jkglSttg}</td>
								</tr>
								<%
									int stbh = 1;
								%>
								<c:forEach items="${jkglFxtListNB}" var="jkglFxt"
									varStatus="statusFxt">
									<c:if test="${jkgl.jkglStbh eq jkglFxt.stbh}">
										<tr>
											<td>(<%=stbh++%>)、</td>
											<td>${jkglFxt.sttg}(${jkglFxt.stda})</td>
										</tr>
										<tr>
											<td colspan="2"><input type="radio" id="a_1"
												name="${jkglFxt.id}" value="A" />A.${jkglFxt.stxxa}</td>
										</tr>
										<tr>
											<td colspan="2"><input type="radio" id="a_2"
												name="${jkglFxt.id}" value="B" />B.${jkglFxt.stxxb}</td>
										</tr>
										<tr>
											<td colspan="2"><input type="radio" id="a_3"
												name="${jkglFxt.id}" value="C" />C.${jkglFxt.stxxc}</td>
										</tr>
										<tr>
											<td colspan="2"><input type="radio" id="a_4"
												name="${jkglFxt.id}" value="D" dataType="Group"
												msg="必须选定一个选项" />D.${jkglFxt.stxxd}</td>
										</tr>
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="2" align="center"><input type="button"
								class="inputButton" value="生成PDF"
								onclick="JkglEngViewImage('${jkId}');" /> &nbsp;&nbsp; <input
								type="button" class="inputButton" value="取消"
								onclick="history.back()" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>