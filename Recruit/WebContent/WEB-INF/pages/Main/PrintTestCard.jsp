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
<link type="text/css" href="resources/jBox/Skins/Blue/jbox.css"
	rel="stylesheet"></link>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="resources/jquery/jquery.PrintArea.js"></script>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div class="wrap clr">
		<div class="menu">
			<div class="menuTop">
				<img src="images/title03.png" />
			</div>
			<ul>
				<li><a href="SearchEnterprise">招聘日程</a>
				</li>
				<li><a href="SearchPosition">招聘职位</a>
				</li>
				<li><a href="MyRecruit" style="color:#0046ae;">我的求职</a>
				</li>
				<li><a href="Message">消息中心</a>
				</li>
			</ul>
		</div>
		<div class="content">
			<div class="conTop">
				<span>您的当前位置：首页 >> 校园招聘 >> 我的求职 >> 打印准考证</span> <img
					src="images/tag04.png" />&nbsp; <strong>我的求职</strong>My Recruit
			</div>
			<div id="TestCard" class="zkz">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<th colspan="4">
							<h1>
								中国航天科工集团公司校园招聘考试 <br /> <br />准&nbsp;&nbsp;&nbsp;&nbsp;考&nbsp;&nbsp;&nbsp;&nbsp;证
							</h1> <br />
						</th>
					</tr>
					<tr>
						<th>姓名</th>
						<td>${bmgl.bmglKsxm }</td>
						<th>姓别</th>
						<td>${bmgl.bmglKsxb }</td>
					</tr>
					<tr>
						<th>身份证号</th>
						<td>${bmgl.bmglSfzh }</td>
						<th>报考岗位</th>
						<td>${bmgl.bmglBkgw }</td>
					</tr>
					<tr>
						<th>考试日期</th>
						<td><fmt:formatDate value="${bmgl.bmglKsrq }" type="date" />
						</td>
						<th>考试时间</th>
						<td>${bmgl.bmglKssj }</td>
					</tr>
					<tr>
						<th>考试地点</th>
						<td colspan="3" style="text-align: left;">&nbsp;&nbsp;${bmgl.bmglKsdd }</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: center; border-bottom: none;"><h2>《考生须知》
							</h2>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: left; border-bottom: none;">
							&nbsp;&nbsp;1、核对准考证上的姓名、身份证号、考试时间、考场地址等信息，如有差错请在考试前及时登陆中国航天科工集团公司人才招聘社区进行更改。
							<br />&nbsp;&nbsp;2、考生进入考场必须携带<u
							style="color: red; font-weight: bold;">《中国航天科工集团公司校园招聘考试准考证》打印件和身份证，缺一不可</u>，以备监考老师检查。
							<br />&nbsp;&nbsp;3、考试过程有一定的计算量，<u
							style="color: red; font-weight: bold;">可携带必要的演算用笔、纸张和计算器</u>。 <br />&nbsp;&nbsp;4、考试为机考，考试过程中如遇到死机、白屏等情况，请及时联系监考老师。
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: center; border-bottom: none;"><h2>《考场规则》
							</h2>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: left;">
							&nbsp;&nbsp;1、考生须于每场考试开始前20分钟到达考试地点，凭《准考证》和有效身份证件入场；考试开始后迟到20分钟者不得入场；未带证件或证件不齐、不符者，监考老师有权拒绝其入场。
							<br />&nbsp;&nbsp;2、 考生进入考场后，需将《准考证》和身份证放在明显位置，以备核查。 <br />&nbsp;&nbsp;3、考生除必要的演算用笔、纸张、计算器外，不得将书籍资料、通讯工具、移动存储设备、电脑、电子记事本以及其它未明确规定带入的物品带入座位。已带到考点的上述物品放在考点指定位置，否则按违纪处理。
							<br />&nbsp;&nbsp;4、
							考生应按照考试系统的要求进行考试，不得擅自进行冷、热启动及其它与考试无关的操作，违规操作造成的后果自负。 <br />&nbsp;&nbsp;5、考生须遵守考试纪律，考试中保持考场安静，禁止吸烟，严禁交头接耳、左顾右盼、窥视。考生不得要求监考老师解释试题，遇到异常情况和问题时举手询问。
							<br />&nbsp;&nbsp;6、 保持考场卫生，爱护机房设备，损坏者照价赔偿。 <br />&nbsp;&nbsp;7、考试中途不得离开考场，因病不能坚持考试者，应报告监考老师，根据具体情况处理。考生交卷后应立即离开考场，不得将草稿纸等带出考场，不得在考场附近逗留、喧哗。
							<br />&nbsp;&nbsp;8、
							考生须服从考试工作人员的管理，接受监考老师的监督和检查，服从监考老师的安排。不得无理取闹，不得辱骂、威胁考试工作人员。</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: center;"><h3>准考证发放单位：中国航天科工集团公司培训中心</h3>
						</td>
					</tr>
				</table>
			</div>
			<div class="seabtn" style="margin-top:30px;">
				<a href="javascript:void(0);" onclick="Print()">确认打印</a>
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
	function Print() {
		$.ajax({
			type : 'POST',
			url : "DoPrintTestCard",
			data : {
				id : "${bmgl.bmglId }"
			},
			dataType : "json",
			success : function(count) {
				$("#TestCard").printArea();
			}
		});
	}
</script>
</html>