<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.lang.* ,java.util.*, com.ttgis.recruit.domain.Tkgl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="rp"
	value=" onclick=\"this.src=this.src + '?' + new Date()\" src" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理岗考试</title>
<link href="css/cpgl/cpstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet"
	href="resources/jBox/Skins/Blue/jbox.css" />
<script type="text/javascript" src="js/KeyDown.js"></script>
<script type="text/javascript" language="javascript">
	/*------判断radio是否有选中，获取选中的值--------*/
	var maxtime = 50 * 60;//半个小时，按秒计算，自己调整!
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
			document.ff.action = "TestSubmit";
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
						document.ff.action = "TestSubmit";
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
						<th colspan="2"><h1>高校毕业生招聘考试试题 ——职业基本能力（管理岗）</h1></th>
					</tr>
					<tr>
						<th colspan="2">
							<p style="text-align: left; font-size: 12px;">答题说明:</p>
							<p style="text-align: left; font-size: 12px;">1.
								本部分为测评考试第一部分，考试时长50分钟，总分75分。</p>
					</tr>
					<tr>
						<td colspan="2"><h3>一、言语理解与表达</h3></td>
					</tr>
				</table>
				<table width="100%">
					<c:forEach items="${ tkglList['lx1'] }" var="map"
						varStatus="status">
						<tr>
							<td>${status.index+1}、</td>
							<td colspan="2">${fn:replace(map.tkglSttg, " src", rp)}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_1"
								name="${map.tkglId}" value="A" />
							</td>
							<td>A.</td>
							<td>${map.tkglStxxA}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_2"
								name="${map.tkglId}" value="B" />
							</td>
							<td>B.</td>
							<td>${map.tkglStxxB}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_3"
								name="${map.tkglId}" value="C" />
							</td>
							<td>C.</td>
							<td>${map.tkglStxxC}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_4"
								name="${map.tkglId}" value="D" dataType="Group" msg="必须选定一个选项" />
							</td>
							<td>D.</td>
							<td>${map.tkglStxxD}</td>

						</tr>
					</c:forEach>
					<tr>
						<td colspan="3"><h3>二、判断推理</h3></td>
					</tr>
					<tr>
						<td colspan="3"><h4>（1）定义判断</h4></td>
					</tr>
					<c:forEach items="${ tkglList['lx2'] }" var="map"
						varStatus="status">
						<tr>
							<td>${status.index+1}、</td>
							<td colspan="2">${fn:replace(map.tkglSttg, " src", rp)}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_1"
								name="${map.tkglId}" value="A" />
							</td>
							<td>A.</td>
							<td>${map.tkglStxxA}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_2"
								name="${map.tkglId}" value="B" />
							</td>
							<td>B.</td>
							<td>${map.tkglStxxB}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_3"
								name="${map.tkglId}" value="C" />
							</td>
							<td>C.</td>
							<td>${map.tkglStxxC}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_4"
								name="${map.tkglId}" value="D" dataType="Group" msg="必须选定一个选项" />
							</td>
							<td>D.</td>
							<td>${map.tkglStxxD}</td>
						</tr>
					</c:forEach>

					<tr>
						<td colspan="3"><h4>（2）类比推理</h4></td>
					</tr>
					<c:forEach items="${ tkglList['lx3'] }" var="map"
						varStatus="status">
						<tr>
							<td>${status.index+1}、</td>
							<td colspan="2">${fn:replace(map.tkglSttg, " src", rp)}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_1"
								name="${map.tkglId}" value="A" />
							</td>
							<td>A.</td>
							<td>${map.tkglStxxA}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_2"
								name="${map.tkglId}" value="B" />
							</td>
							<td>B.</td>
							<td>${map.tkglStxxB}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_3"
								name="${map.tkglId}" value="C" />
							</td>
							<td>C.</td>
							<td>${map.tkglStxxC}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_4"
								name="${map.tkglId}" value="D" dataType="Group" msg="必须选定一个选项" />
							</td>
							<td>D.</td>
							<td>${map.tkglStxxD}</td>
						</tr>
					</c:forEach>

					<tr>
						<td colspan="3"><h4>（3）逻辑判断</h4></td>
					</tr>
					<c:forEach items="${ tkglList['lx4'] }" var="map"
						varStatus="status">
						<tr>
							<td>${status.index+1}、</td>
							<td colspan="2">${fn:replace(map.tkglSttg, " src", rp)}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_1"
								name="${map.tkglId}" value="A" />
							</td>
							<td>A.</td>
							<td>${map.tkglStxxA}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_2"
								name="${map.tkglId}" value="B" />
							</td>
							<td>B.</td>
							<td>${map.tkglStxxB}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_3"
								name="${map.tkglId}" value="C" />
							</td>
							<td>C.</td>
							<td>${map.tkglStxxC}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_4"
								name="${map.tkglId}" value="D" dataType="Group" msg="必须选定一个选项" />
							</td>
							<td>D.</td>
							<td>${map.tkglStxxD}</td>
						</tr>
					</c:forEach>
					<%
						int STnum = 1;
					%>
					<tr>
						<td colspan="3"><h3>三、资料分析</h3></td>
					</tr>
					<c:forEach items="${ tkglList['lx7'] }" var="map"
						varStatus="status">
						<tr>
							<td><%=STnum++%>、</td>
							<td colspan="2">${fn:replace(map.tkglSttg, " src", rp)}</td>
						</tr>
						<%
							int STnumsmall = 1;
						%>
						<c:forEach items="${zlfxListFxt}" var="amap" varStatus="astatus">
							<c:if test="${map.tkglStbh eq  amap.stbh}">
								<tr>
									<td>(<%=STnumsmall++%>)、</td>
									<td colspan="2">${fn:replace(amap.sttg, " src", rp)}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="radio" id="a_1"
										name="${amap.id}" value="A" />
									</td>
									<td>A.</td>
									<td>${amap.stxxa}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="radio" id="a_2"
										name="${amap.id}" value="B" />
									</td>
									<td>B.</td>
									<td>${amap.stxxb}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="radio" id="a_3"
										name="${amap.id}" value="C" />
									</td>
									<td>C.</td>
									<td>${amap.stxxc}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="radio" id="a_4"
										name="${amap.id}" value="D" dataType="Group" msg="必须选定一个选项" />
									</td>
									<td>D.</td>
									<td>${amap.stxxd}</td>
								</tr>
							</c:if>

						</c:forEach>
					</c:forEach>
					<tr>
						<td colspan="3"><h3>四、Office及一般管理</h3></td>
					</tr>
					<tr>
						<td colspan="3"><h4>（1）、一般管理</h4></td>
					</tr>
					<c:forEach items="${ tkglList['lx5'] }" var="map"
						varStatus="status">
						<tr>
							<td>${status.index+1}、</td>
							<td colspan="2">${fn:replace(map.tkglSttg, " src", rp)}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_1"
								name="${map.tkglId}" value="A" />
							</td>
							<td>A.</td>
							<td>${map.tkglStxxA}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_2"
								name="${map.tkglId}" value="B" />
							</td>
							<td>B.</td>
							<td>${map.tkglStxxB}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_3"
								name="${map.tkglId}" value="C" dataType="Group" msg="必须选定一个选项" />
							</td>
							<td>C.</td>
							<td>${map.tkglStxxC}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_4"
								name="${map.tkglId}" value="D" dataType="Group" msg="必须选定一个选项" />
							</td>
							<td>D.</td>
							<td>${map.tkglStxxD}</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="3"><h4>（2）、office题</h4></td>
					</tr>
					<c:forEach items="${ tkglList['lx6'] }" var="map"
						varStatus="status">
						<tr>
							<td>${status.index+1}、</td>
							<td colspan="2">${fn:replace(map.tkglSttg, " src", rp)}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_1"
								name="${map.tkglId}" value="A" />
							</td>
							<td>A.</td>
							<td>${map.tkglStxxA}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_2"
								name="${map.tkglId}" value="B" />
							</td>
							<td>B.</td>
							<td>${map.tkglStxxB}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_3"
								name="${map.tkglId}" value="C" dataType="Group" msg="必须选定一个选项" />
							</td>
							<td>C.</td>
							<td>${map.tkglStxxC}</td>
						</tr>
						<tr>
							<td style="text-align:right;"><input type="radio" id="a_3"
								name="${map.tkglId}" value="D" dataType="Group" msg="必须选定一个选项" />
							</td>
							<td>D.</td>
							<td>${map.tkglStxxD}</td>
						</tr>
					</c:forEach>
				</table>
				<a href="javascript:void(0);" class="btn" onclick="checkValidate();">提交答卷</a>
			</form>
		</div>
	</div>
	<%@ include file="../Main/cpglFooter.jsp"%>
</body>
</html>