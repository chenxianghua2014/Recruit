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
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet" href="resources/jBox/Skins/Blue/jbox.css"/>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript" language="javascript">
$.document.ready(function (){
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
				$.jBox.messager('您的考试时间还剩余：5分钟！请抓紧时间', '剩余考试时间');
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
	function productpdf(jkId){
		$.jBox.tip("正在生成PDF试卷，请耐心等待！", 'loading');
		window.location = "JkglViewImage?jkId="+jkId;
		window.setTimeout(function () { $.jBox.tip('生成已完成。', 'success'); }, 5000);
	}
	function JkglEngView(jkId){
		window.location = "JkglEngView?jkId="+jkId;
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
							<th colspan="2"><h1>高校毕业生招聘考试试题 ——职业基本能力（管理岗）</h1></th>
						</tr>
						<tr>
							<th colspan="2">
								<p style="text-align: left; font-size: 12px;">答题说明:</p>
								<p style="text-align: left; font-size: 12px;">1. 测试时间为50分钟，总分75分。</p>
								<p style="text-align: left; font-size: 12px;">2. 将答案填入答题卡</p>
							</th>
						</tr>
						<tr>
							<td colspan="2" ><h2>一、选择题(共10题)</h2></td>
						</tr>
						<tr>
							<td colspan="2"><h4>言语理解与表达</h4></td>
						</tr>
						<c:forEach items="${jkglListA}" var="jkgl" varStatus="status">
							<c:if test="${jkgl.jkglStlx eq 'A'}" >
								<tr>
									<td width="20px;">${status.index+1}、</td>
									<td>
									${jkgl.jkglSttg}(${jkgl.jkglStda})
									</td>
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
										name="${jkgl.jkglId}" value="D" dataType="Group" msg="必须选定一个选项" />D.${jkgl.jkglStxxd}</td>

								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="2"><h2>二、判断推理(共10题)</h2></td>
						</tr>
						<tr>
							<td colspan="2"><h4>1、定义推理(共3题)</h4></td>
						</tr>
						<c:forEach items="${jkglListD}" var="jkgl" varStatus="status">
							<c:if test="${jkgl.jkglStlx eq 'D'}">
								<tr>
									<td >${status.index+1}、</td>
									<td>${jkgl.jkglSttg}(${jkgl.jkglStda})
									</td>
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
										name="${jkgl.jkglId}" value="D" dataType="Group" msg="必须选定一个选项" />D.${jkgl.jkglStxxd}</td>

								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="2"><h4>2、类比推理(共3题)</h4></td>
						</tr>
						<c:forEach items="${jkglListE}" var="jkgl" varStatus="status">
							<c:if test="${jkgl.jkglStlx eq 'E'}">
								<tr>
									<td >${status.index+1}、</td>
									<td>${jkgl.jkglSttg}(${jkgl.jkglStda})
									</td>
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
										name="${jkgl.jkglId}" value="D" dataType="Group" msg="必须选定一个选项" />D.${jkgl.jkglStxxd}</td>

								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="2"><h4>3、逻辑推理(共4题)</h4></td>
						</tr>
							<c:forEach items="${jkglListF}" var="jkgl" varStatus="status">
							<c:if test="${jkgl.jkglStlx eq 'F'}">
								<tr>
									<td >${status.index+1}、</td>
									<td>${jkgl.jkglSttg}(${jkgl.jkglStda})
									</td>
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
										name="${jkgl.jkglId}" value="D" dataType="Group" msg="必须选定一个选项" />D.${jkgl.jkglStxxd}</td>
								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="2"><h2>三、资料分析</h2></td>
						</tr>
						<c:forEach items="${jkglListG}" var="jkgl" varStatus="status">
							<c:if test="${jkgl.jkglStlx eq 'G'}">
								<tr>
									<td >${status.index+1}、</td>
									<td>${jkgl.jkglSttg}
									</td>
								</tr>
								<% int stbh = 1; %>
								<c:forEach items="${jkglFxtList}" var="jkglFxt" varStatus="statusFxt">
									<c:if test="${jkgl.jkglStbh eq jkglFxt.stbh}">
										<tr>
											<td >(<%=stbh++%>)、</td>
											<td>${jkglFxt.sttg}(${jkglFxt.stda})
											</td>
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
												name="${jkglFxt.id}" value="D" dataType="Group" msg="必须选定一个选项" />D.${jkglFxt.stxxd}</td>
										</tr>
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="2"><h2>四、Office及一般管理</h2></td>
						</tr>
						<tr>
							<td colspan="2"><h4>1、一般管理(共5题)</h4></td>
						</tr>
						<c:forEach items="${jkglListI}" var="jkgl" varStatus="status">
							<c:if test="${jkgl.jkglStlx eq 'I'}">
								<tr>
									<td >${status.index+1}、</td>
									<td>${jkgl.jkglSttg}(${jkgl.jkglStda})
									</td>
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
										name="${jkgl.jkglId}" value="D" dataType="Group" msg="必须选定一个选项" />D.${jkgl.jkglStxxd}</td>
								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="2"><h4>2、office题(共10题)</h4></td>
						</tr>
						<c:forEach items="${jkglListH}" var="jkgl" varStatus="status">
							<c:if test="${jkgl.jkglStlx eq 'H'}">
								<tr>
									<td >${status.index+1}、</td>
									<td>${jkgl.jkglSttg}(${jkgl.jkglStda})
									</td>
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
										name="${jkgl.jkglId}" value="D" dataType="Group" msg="必须选定一个选项" />D.${jkgl.jkglStxxd}</td>
								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="2" align="center">
								<input type="button" class="inputButton" value="查看英语试题" onclick="JkglEngView('${jkId}');"/> &nbsp;&nbsp;
								<input type="button" class="inputButton" value="生成PDF" onclick="productpdf('${jkId}');"/> &nbsp;&nbsp;
								<input type="button" class="inputButton" value="取消" onclick="history.back()" />
								
							</td>
						</tr>
					</table>
				</form>

			</div>
		</div>
	</div>
</body>
</html>