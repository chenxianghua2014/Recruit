<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.lang.* ,java.util.*, com.ttgis.recruit.domain.Tkgl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="rp"
	value=" onclick=\"this.src=this.src + '?' + new Date()\" src" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>英语考试</title>
<link href="css/cpgl/cpstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet"
	href="resources/jBox/Skins/Blue/jbox.css" />
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript" src="js/KeyDown.js"></script>
<script type="text/javascript" language="javascript">
	/*------判断radio是否有选中，获取选中的值--------*/
	var maxtime = 25 * 60;//半个小时，按秒计算，自己调整!
	function CountDown() {
		if (maxtime >= 0) {
			minutes = Math.floor(maxtime / 60);
			seconds = Math.floor(maxtime % 60);
			msg = minutes + "分" + seconds + "秒";
			document.all["timer"].innerHTML = msg;
			if (maxtime == 5 * 60)
				$.jBox.messager(
						"您的考试时间还剩余：<span style='color:red;'>5分钟</span>！请抓紧时间",
						"剩余考试时间");
			--maxtime;
		} else {
			clearInterval(timer);
			$.jBox.messager('时间到，考试结束', '剩余考试时间');
			$.jBox.tip("正在提交试卷，请耐心等待一下！", 'loading');
			document.ff.action = "JsgEngSubmit";
			document.ff.method = "post";
			document.ff.submit();
			window.setTimeout(function() {
				$.jBox.tip('已成功提交试卷。', 'success');
			}, 10000);
		}
	}
	timer = setInterval("CountDown()", 1000);
	$(function() {
		$("#btnSubmit").click(function() {
			alert("提交成功");
		});
	});
	function checkValidate() {
		if (Validator.Validate(document.getElementById('ff'), 3)) {
			if (confirm("您确定要提交试卷么?")) {
				if (confirm("您确定要提交试卷么?")) {
					if (confirm("您确定要提交试卷么?")) {
						$.jBox.tip("正在提交试卷，请耐心等待一下！", 'loading');
						document.ff.action = "JsgEngSubmit";
						document.ff.method = "post";
						document.ff.submit();
						window.setTimeout(function() {
							$.jBox.tip('已成功提交试卷。', 'success');
						}, 10000);
					}
				}
			}
		}
	}
</script>
<style>
td {
	border-bottom: 1px dashed #ccc;
	height: 40px;
	line-height: 30px;
	font-size: 14px;
}
</style>
</head>
<body>
	<div id="fixd">
		<div id="top"></div>
		<div class="conTop">
			<p>考生姓名：${cpglLoginSession1.bmglKsxm}&nbsp;&nbsp;&nbsp;
				身份证号：${cpglLoginSession1.bmglSfzh}</p>
			<div class="time">
				<img src="images/cpgl/time.jpg" />考试剩余时间 ：<span id="timer"></span>
			</div>
		</div>
	</div>

	<div class="conRight">
		<div class="questions">
			<form name="ff" id="ff">
				<input type="hidden" name="clickNum" id="clickNum" value="1" />
				<table width="100%">
					<tr>
						<th colspan="2"><h1>高校毕业生招聘考试 ——英语考试</h1></th>
					</tr>
					<tr>
						<th colspan="2">
							<p style="text-align: left; font-size: 12px;">答题说明:</p>
							<p style="text-align: left; font-size: 12px;">1.
								本部分为测评考试第二部分，考试时长25分钟,总分为25分。</p>
					</tr>
					<tr>
						<th style="text-align: left; font-size: 12px;" colspan="2">第一部分
							语言知识及应用</th>
					</tr>
				</table>
				<table width="100%">
					<tr>
						<td colspan="5"><h2>第一节 情景应用:</h2>
							<h3>阅读1-5问题框，选择正确的一个。</h3>
						</td>
					</tr>

					<c:forEach items="${jsgEngList['qjyy'] }" var="map"
						varStatus="status">
						<tr>
							<td>${status.index+1}、</td>
							<td colspan="2">${fn:replace(map.tkglSttg, " src", rp)}</td>
						</tr>
						<tr>
							<td style="text-align:right; "><input type="radio" id="a_1"
								name="${map.tkglId}" value="A" /></td>
							<td>A.</td>
							<td>${map.tkglStxxA}</td>
						</tr>
						<tr>
							<td style="text-align:right; "><input type="radio" id="a_2"
								name="${map.tkglId}" value="B" /></td>
							<td>B.</td>
							<td>${map.tkglStxxB}</td>
						</tr>
						<tr>
							<td style="text-align:right; "><input type="radio" id="a_3"
								name="${map.tkglId}" value="C" dataType="Group" msg="必须选定一个选项" />
							</td>
							<td>C.</td>
							<td>${map.tkglStxxC}</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="3"><h2>第二节 单项选择</h2>
							<h3>选择你认为正确的答案。</h3>
						</td>
					</tr>
					<c:forEach items="${jsgEngList['swxz'] }" var="map"
						varStatus="status">
						<tr>
							<td>${status.index+1}、</td>
							<td colspan="2">${fn:replace(map.tkglSttg, " src", rp)}</td>
						</tr>
						<tr>
							<td style="text-align:right; "><input type="radio" id="a_1"
								name="${map.tkglId}" value="A" /></td>
							<td>A.</td>
							<td>${map.tkglStxxA}</td>
						</tr>
						<tr>
							<td style="text-align:right; "><input type="radio" id="a_2"
								name="${map.tkglId}" value="B" /></td>
							<td>B.</td>
							<td>${map.tkglStxxB}</td>
						</tr>
						<tr>
							<td style="text-align:right; "><input type="radio" id="a_3"
								name="${map.tkglId}" value="C" /></td>
							<td>C.</td>
							<td>${map.tkglStxxC}</td>
						</tr>
						<tr>
							<td style="text-align:right; "><input type="radio" id="a_4"
								name="${map.tkglId}" value="D" dataType="Group" msg="必须选定一个选项" />
							</td>
							<td>D.</td>
							<td>${map.tkglStxxD}</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="3"><h2>第二部分 阅读</h2>
						</td>
					</tr>
					<tr>
						<td colspan="3"><h3>第一节 信息匹配(选择你认为正确的答案。)</h3>
						</td>
					</tr>
					<c:forEach items="${jsgEngList['xxpp'] }" var="map"
						varStatus="status">
						<tr>
							<td></td>
							<td colspan="2">${fn:replace(map.tkglSttg, " src", rp)}</td>
						</tr>
						<tr>
							<td colspan="3">请按顺序选择正确的答案： 1、<select name="${map.tkglId}1"
								id="a_1" dataType="Require" msg="必填">
									<option value="">--请选择--</option>
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
									<option value="E">E</option>
									<option value="F">F</option>
							</select> 2、<select name="${map.tkglId}2" id="a_1" dataType="Require"
								msg="必填">
									<option value="">--请选择--</option>
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
									<option value="E">E</option>
									<option value="F">F</option>
							</select> 3、<select name="${map.tkglId}3" id="a_1" dataType="Require"
								msg="必填">
									<option value="">--请选择--</option>
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
									<option value="E">E</option>
									<option value="F">F</option>
							</select> 4、<select name="${map.tkglId}4" id="a_1" dataType="Require"
								msg="必填">
									<option value="">--请选择--</option>
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
									<option value="E">E</option>
									<option value="F">F</option>
							</select> 5、<select name="${map.tkglId}5" id="a_1" dataType="Require"
								msg="必填">
									<option value="">--请选择--</option>
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
									<option value="E">E</option>
									<option value="F">F</option>
							</select></td>
						</tr>
					</c:forEach>

					<tr>
						<td colspan="3"><h3>第二节 阅读理解</h3>
						</td>
					</tr>
					<c:forEach items="${jsgEngList['ydlj'] }" var="map"
						varStatus="status">
						<tr>
							<td></td>
							<td colspan="2">${fn:replace(map.tkglSttg, " src", rp)}</td>
						</tr>
						<%
							int ydljNumSmall = 1;
						%>
						<c:forEach items="${ydljListFxtM}" var="emap" varStatus="estatus">
							<c:if test="${emap.stbh eq map.tkglStbh}">

								<tr>
									<td>(<%=ydljNumSmall++%>)、</td>
									<td colspan="2">${fn:replace(emap.sttg, " src", rp)}</td>
								</tr>
								<tr>
									<td style="text-align:right; "><input type="radio"
										id="a_1" name="${emap.id}" value="A" /></td>
									<td>A.</td>
									<td>${emap.stxxa}</td>
								</tr>
								<tr>
									<td style="text-align:right; "><input type="radio"
										id="a_2" name="${emap.id}" value="B" /></td>
									<td>B.</td>
									<td>${emap.stxxb}</td>
								</tr>
								<tr>
									<td style="text-align:right; "><input type="radio"
										id="a_3" name="${emap.id}" value="C" /></td>
									<td>C.</td>
									<td>${emap.stxxc}</td>
								</tr>
								<tr>
									<td style="text-align:right; "><input type="radio"
										id="a_4" name="${emap.id}" value="D" dataType="Group"
										msg="必须选定一个选项" /></td>
									<td>D.</td>
									<td>${emap.stxxd}</td>
								</tr>

							</c:if>
						</c:forEach>
					</c:forEach>

					<tr>
						<td colspan="3"><h3>第三节 应用短文阅读</h3>
						</td>
					</tr>
					<c:forEach items="${jsgEngList['yydw1'] }" var="map"
						varStatus="status">
						<tr>
							<td>1、</td>
							<td colspan="2">${fn:replace(map.tkglSttg, " src", rp)}</td>
						</tr>
						<%
							int stfxtnum = 1;
						%>
						<c:forEach items="${yydwListFxtNA}" var="emap" varStatus="estatus">
							<c:if test="${emap.stbh eq map.tkglStbh}">
								<tr>
									<td>(<%=stfxtnum++%>)、</td>
									<td colspan="2">${fn:replace(emap.sttg, " src", rp)}</td>
								</tr>
								<tr>
									<td style="text-align:right; "><input type="radio"
										id="a_1" name="${emap.id}" value="A" /></td>
									<td>A.</td>
									<td>${emap.stxxa}</td>
								</tr>
								<tr>
									<td style="text-align:right; "><input type="radio"
										id="a_2" name="${emap.id}" value="B" /></td>
									<td>B.</td>
									<td>${emap.stxxb}</td>
								</tr>
								<tr>
									<td style="text-align:right; "><input type="radio"
										id="a_3" name="${emap.id}" value="C" /></td>
									<td>C.</td>
									<td>${emap.stxxc}</td>
								</tr>
								<tr>
									<td style="text-align:right; "><input type="radio"
										id="a_4" name="${emap.id}" value="D" dataType="Group"
										msg="必须选定一个选项" /></td>
									<td>D.</td>
									<td>${emap.stxxd}</td>
								</tr>

							</c:if>
						</c:forEach>
					</c:forEach>
					<c:forEach items="${jsgEngList['yydw2'] }" var="map"
						varStatus="status">
						<tr>
							<td>2、</td>
							<td colspan="2">${fn:replace(map.tkglSttg, " src", rp)}</td>
						</tr>
						<%
							int yydw2fxt = 1;
						%>
						<c:forEach items="${yydwListFxtNB}" var="emap" varStatus="estatus">
							<c:if test="${emap.stbh eq map.tkglStbh}">
								<tr>
									<td>(<%=yydw2fxt++%>)、</td>
									<td colspan="2">${fn:replace(emap.sttg, " src", rp)}</td>
								</tr>
								<tr>
									<td style="text-align:right; "><input type="radio"
										id="a_1" name="${emap.id}" value="A" /></td>
									<td>A.</td>
									<td>${emap.stxxa}</td>
								</tr>
								<tr>
									<td style="text-align:right; "><input type="radio"
										id="a_2" name="${emap.id}" value="B" /></td>
									<td>B.</td>
									<td>${emap.stxxb}</td>
								</tr>
								<tr>
									<td style="text-align:right; "><input type="radio"
										id="a_3" name="${emap.id}" value="C" /></td>
									<td>C.</td>
									<td>${emap.stxxc}</td>
								</tr>
								<tr>
									<td style="text-align:right; "><input type="radio"
										id="a_4" name="${emap.id}" value="D" dataType="Group"
										msg="必须选定一个选项" /></td>
									<td>D.</td>
									<td>${emap.stxxd}</td>
								</tr>

							</c:if>
						</c:forEach>
					</c:forEach>
				</table>
				<a href="javascript:void(0);" class="btn" onclick="checkValidate();">提交答卷</a>
			</form>
		</div>
	</div>
	<%@ include file="../Main/cpglFooter.jsp"%>
</body>
</html>