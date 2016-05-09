<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link rel="stylesheet"
	href="resources/validator-0.7.1/jquery.validator.css" type="text/css"></link>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/jquery.validator.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<div class="title">当前位置:宣传信息>编辑职位信息</div>
	<div class="editBlock">
		<form action="ZwglSava" method="post" autocomplete="off"
			onsubmit="frmSubmit()"
			data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
			<input type="hidden" id="positionId" name="positionId"
				value="${ position.positionId }" /> <input type="hidden"
				id="positionStatus" name="positionStatus"
				value="${ position.positionStatus }" />
			<table>
				<tr>
					<td colspan="2" class="subtitle">编辑职位信息</td>
				</tr>
				<tbody>
					<tr>
						<th width="150"><span class="warning">*</span>职位名称</th>
						<td><input type="text" class="inputText" id="positionName"
							style="width:400px;" name="positionName"
							value="${ position.positionName }" data-rule="职位名称:required;" />
						</td>
					</tr>
					<tr>
						<th><span class="warning">*</span>职位类别</th>
						<td><select id="positionType" name="positionType"
							style="width:400px;" data-rule="职位类别:required;">
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
						</select></td>
					</tr>
					<tr>
						<th><span class="warning">*</span>工作地点</th>
						<td><select id="positionWorkaddress"
							name="positionWorkaddress" style="width:400px;"
							data-rule="工作地点:required;">
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
					</tr>
					<tr>
						<th><span class="warning">*</span>学历要求</th>
						<td><c:set var="s" value="${ position.positionXlyq}" /> <c:choose>
								<c:when test="${fn:indexOf(s,'大专')>-1}">
									<lable> <input type="checkbox" id="positionXlyqdz"
										name="positionXlyq" value="大专" checked="true" />大专</lable>
								</c:when>
								<c:otherwise>
									<lable> <input type="checkbox" id="positionXlyqdz"
										name="positionXlyq" value="大专" />大专</lable>
								</c:otherwise>
							</c:choose> <c:choose>
								<c:when test="${fn:indexOf(s,'本科')>-1}">
									<lable> <input type="checkbox" id="positionXlyqbk"
										name="positionXlyq" value="本科" checked="true" />本科</lable>
								</c:when>
								<c:otherwise>
									<lable> <input type="checkbox" id="positionXlyqbk"
										name="positionXlyq" value="本科" />本科</lable>
								</c:otherwise>
							</c:choose> <c:choose>
								<c:when test="${fn:indexOf(s,'硕士')>-1}">
									<lable> <input type="checkbox" id="positionXlyqss"
										name="positionXlyq" value="硕士" checked="true" />硕士</lable>
								</c:when>
								<c:otherwise>
									<lable> <input type="checkbox" id="positionXlyqss"
										name="positionXlyq" value="硕士" />硕士</lable>
								</c:otherwise>
							</c:choose> <c:choose>
								<c:when test="${fn:indexOf(s,'博士')!=-1}">
									<lable> <input type="checkbox" id="positionXlyqbs"
										name="positionXlyq" value="博士" checked="true" />博士</lable>
								</c:when>
								<c:otherwise>
									<lable> <input type="checkbox" id="positionXlyqbs"
										name="positionXlyq" value="博士" />博士</lable>
								</c:otherwise>
							</c:choose></td>
					</tr>
					<tr>
						<th><span class="warning">*</span>专业类别</th>
						<td><select id="positionZylb" name="positionZylb"
							style="width:400px;" onchange="bindZpzy(this.value);"
							data-rule="专业类别:required;">
						</select></td>
					</tr>
					<tr>
						<th width="150"><span class="warning">*</span>招聘专业</th>
						<td><select id="positionZpzy" name="positionZpzy"
							style="width:400px;" data-rule="招聘专业:required;">
						</select></td>
					</tr>
					<tr>
						<th width="150"><span class="warning">*</span>需求人数</th>
						<td><input type="text" class="inputText" id="positionXqrs"
							style="width:400px" name="positionXqrs"
							value="${ position.positionXqrs }"
							data-rule="需求人数:required;integer;" /></td>
					</tr>
					<tr>
						<th width="150"><span class="warning">*</span>招聘分类</th>
						<td><select id="positionZpfl" name="positionZpfl"
							style="width:400px;" data-rule="招聘分类:required;">
								<option></option>
								<option value="高校毕业生招聘">高校毕业生招聘</option>
								<option value="实习生招聘">实习生招聘</option>
								<option value="大专生招聘">大专生招聘</option>
								<option value="其他专项招聘">其他专项招聘</option>
						</select><input type="text" class="inputText" id="txtZpfl"
							style="width:400px;display:none;" name="txtZpfl" /></td>
					</tr>
					<tr>
						<th><span class="warning">*</span>职位状态</th>
						<td><input type="radio" id="rdo1" name="rdo" value="未开始"
							checked="true" />未开始<input type="radio" id="rdoN2" name="rdo"
							value="正在进行" />正在进行 <input type="radio" id="rdoN3" name="rdo"
							value="已结束" />已结束</td>
					</tr>
					<tr>
						<th>职责模板</th>
						<td><select id="selZzmb" name="selZzmb" style="width:120px;"><option
									value=""></option>
						</select>&nbsp;<input type="button" class="inputButton" value="加载"
							onclick="loadZzmbContent()" /></td>
					</tr>
					<tr>
						<th>职位职责</th>
						<td><textarea id="positionDuty"
								style="width:400px;height:100px;" name="positionDuty">${ position.positionDuty }</textarea>
							<input type="button" class="inputButton" style="display: none;"
							onclick="ImportShow('positionDuty');" value="导入" /></td>
					</tr>
					<tr>
						<th>要求模板</th>
						<td><select id="selYqmb" name="selYqmb" style="width:120px;"><option
									value=""></option>
						</select>&nbsp;<input type="button" class="inputButton" value="加载"
							onclick="loadYqmbContent()" /></td>
					</tr>
					<tr>
						<th>职位要求</th>
						<td><textarea id="positionRequierd"
								style="width:400px;height:100px;" name="positionRequierd">${ position.positionRequierd }</textarea>
							<input type="button" class="inputButton" style="display: none;"
							onclick="ImportShow('positionRequierd');" value="导入" /></td>
					</tr>
					<tr>
						<th></th>
						<td><input type="submit" class="inputButton" value="确定" />
							&nbsp;&nbsp; <input type="button" class="inputButton" value="取消"
							onclick="history.back()" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(
			function() {
				bindZzmb();
				bindYqmb();
				bindZylb();
				$("input[name=rdo][value=${position.positionStatus}]").attr(
						"checked", true);
				$("#positionType").val("${position.positionType}");
				$("#positionZpfl").val("${position.positionZpfl}");
				$("#positionWorkaddress")
						.val("${position.positionWorkaddress}");
				$("#positionXlyq").val("${position.positionXlyq}");
				$("#positionZylb").val("${position.positionZylb}");
				bindZpzy("${position.positionZylb}");
				$("#positionZpzy").val("${position.positionZpzy}");
			});
	function frmSubmit() {
		$("#positionStatus").val($("input[name='rdo']:checked").val());
	}
	function ImportShow(_id) {
		var win = window.showModalDialog("ImportShow", "true",
				"dialogWidth:300px;dialogHeight:150px;scroll:no;status:no");
		if (!win) {
			$.ajax({
				type : "POST",
				url : "LoadFile",
				data : {},
				dataType : "json",
				async : false,
				success : function(_result) {
					$("#" + _id).val(_result.modelMap.wordReader);
				}
			});
		}
	}

	function bindZylb() {
		$.ajax({
			type : "POST",
			url : "LoadZylb",
			data : {
				type : '职位要求'
			},
			dataType : "json",
			async : false,
			success : function(_result) {
				$("#positionZylb").empty();
				$("#positionZylb").append("<option value=''></option>");
				for (var i = 0; i < _result.length; i++) {
					$("#positionZylb").append(
							"<option value='" + _result[i].zylbName + "'>"
									+ _result[i].zylbName + "</option>");
				}
			}
		});
	}

	function bindZpzy(sslb) {
		$.ajax({
			type : "POST",
			url : "LoadZpzy",
			data : {
				type : sslb
			},
			dataType : "json",
			async : false,
			success : function(_result) {
				$("#positionZpzy").empty();
				$("#positionZpzy").append("<option value=''></option>");
				for (var i = 0; i < _result.length; i++) {
					$("#positionZpzy").append(
							"<option value='" + _result[i].zpzyName + "'>"
									+ _result[i].zpzyName + "</option>");
				}
			}
		});
	}

	function bindYqmb() {
		$.ajax({
			type : "POST",
			url : "LoadMbgl",
			data : {
				type : '职位要求'
			},
			dataType : "json",
			async : false,
			success : function(_result) {
				$("#selYqmb").empty();
				$("#selYqmb").append("<option value=''></option>");
				for (var i = 0; i < _result.length; i++) {
					$("#selYqmb").append(
							"<option value='" + _result[i].mbglId + "'>"
									+ _result[i].mbglName + "</option>");
				}
			}
		});
	}

	function bindZzmb() {
		$.ajax({
			type : "POST",
			url : "LoadMbgl",
			data : {
				type : '职位职责'
			},
			dataType : "json",
			async : false,
			success : function(_result) {
				$("#selZzmb").empty();
				$("#selZzmb").append("<option value=''></option>");
				for (var i = 0; i < _result.length; i++) {
					$("#selZzmb").append(
							"<option value='" + _result[i].mbglId + "'>"
									+ _result[i].mbglName + "</option>");
				}
			}
		});
	}

	function loadZzmbContent() {
		if ($("#selZzmb").val() == "")
			return;
		$.ajax({
			type : "POST",
			url : "loadMbContent",
			data : {
				id : $("#selZzmb").val()
			},
			dataType : "json",
			async : false,
			success : function(_result) {
				$("#positionDuty").val(_result.mbglContent);
			}
		});
	}

	function loadYqmbContent() {
		if ($("#selYqmb").val() == "")
			return;
		$.ajax({
			type : "POST",
			url : "loadMbContent",
			data : {
				id : $("#selYqmb").val()
			},
			dataType : "json",
			async : false,
			success : function(_result) {
				$("#positionRequierd").val(_result.mbglContent);
			}
		});
	}
</script>
</html>
