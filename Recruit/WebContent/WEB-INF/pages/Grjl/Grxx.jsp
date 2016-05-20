<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html style="overflow:hidden">
<head>
<title>我的简历</title>
<link rel="stylesheet" href="resources/jBox/Skins/Blue/jbox.css" type="text/css"></link>
<link rel="stylesheet" href="skins/default/main.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="resources/validator-0.7.1/jquery.validator.css"></link>
<link rel="stylesheet" href="resources/uploadify/css/uploadify.css" type="text/css" />
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.form.js"></script>
<script type="text/javascript" src="resources/uploadify/jquery.uploadify-3.1.js"></script>
<script type="text/javascript" src="resources/validator-0.7.1/jquery.validator.js"></script>
<script type="text/javascript" src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript" src="js/Grxx.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				//身份证提取生日
				//alert(getBirthdatByIdNo(document.getElementById("resumeSfzh").value));
				//document.getElementById("resumeBirthday").value = getBirthdatByIdNo(document.getElementById("resumeSfzh").value);
				$dp.$('resumeBirthday').value = getBirthdatByIdNo(document
						.getElementById("resumeSfzh").value);
				//证书
				$('#form12').on('valid.form', function() {
					var id = $('#resumeId').val();
					if (id == "") {
						alert("请将基本信息填写完成!");
						return;
					} else {
						$.ajax({
							url : 'SaveZs',
							type : 'POST',
							data : $(this).serialize(),
							success : function(d) {
								alert("保存成功!");
								$("#href12").css('color', 'green');
								//window.location.reload();
							}
						});
					}
				});
				/* //IT技能
				$('#form13').on('valid.form', function() {
					var id = $('#resumeId').val();
					if (id == "") {
						alert("请将基本信息填写完成!");
						return;
					} else {
						$.ajax({
							url : 'SaveITjnagain',
							type : 'POST',
							data : $(this).serialize(),
							success : function(d) {
								alert("保存成功!");
								$("#href13").css('color', 'green');
								//window.location.reload();
							}
						});
					}
				}); */
				//基本信息验证提交
				$('#form1').on('valid.form', function() {
					$("#JbxxSaveinput1").attr("disabled", true);
					$("#Button1").attr("disabled", true);
					
					$.ajax({
						url : 'SaveJbxx',
						type : 'POST',
						data : $(this).serialize(),
						success : function(d) {
							alert("保存成功!");
							$("#href1").css('color', 'green');
							window.location.reload();
						}
					});
				});
				//联系方式验证提交
				$('#form2').on('valid.form', function() {
					//alert($('#formQtxx').isValid());
					var id = $('#resumeId').val();
					if (id == "") {
						alert("请将基本信息填写完成!");
						return;
					} else {
						$.ajax({
							url : 'SaveLxfs',
							type : 'POST',
							data : $(this).serialize(),
							success : function(d) {
								alert("保存成功!");
								$("#href2").css('color', 'green');
								//window.location.reload();
							}
						});
					}
				});

				$('#form2012').on('valid.form', function() {
					//alert("进入函数");
					var id = $('#resumeId').val();
					if (id == "") {
						alert("请将其他信息填写完成!");
						return;
					} else {
						$.ajax({
							url : 'SaveQtxx',
							type : 'POST',
							data : $(this).serialize(),
							success : function(d) {
								alert("保存成功!");
								$("#href2").css('color', 'green');
								//window.location.reload();
							}
						});
					}
				});
			});
	//删除证书
	function delete12() {

		if (confirm("您确定要删除该条记录吗?")) {
			function showResponseMov(d) {
				$("#resumeHdzs").val(d.model.resume.resumeHdzs);
				$("#resumeQtzs").val(d.model.resume.resumeQtzs);
				alert("删除成功！");
				$("#href12").css('color', 'black');
			}
			var options = {
				type : 'post',
				url : "DeleteZs",
				success : showResponseMov,
				error : showResponseMov,
				clearForm : false
			};
			$("#form12").ajaxSubmit(options);
		}

	}
	function delete13() {
		if (confirm("您确定要删除该条记录吗?")) {
			function showResponseMov(d) {
				$("#resumeITjtjn").val(d.model.resume.resumeITjtjn);
				$("#resumeITsxjn").val(d.model.resume.resumeITsxjn);
				$("#resumeQtjn").val(d.model.resume.resumeQtjn);
				$("#href13").css('color', 'black');
				alert("删除成功！");
			}
			var options = {
				type : 'post',
				url : "DeleteItjn",
				success : showResponseMov,
				error : showResponseMov,
				clearForm : false
			};
			$("#form13").ajaxSubmit(options);
		}
	}
</script>
<style type="text/css">
.href {
	font-size: 14;
	font-family: monospace;
	text-decoration: NONE;
	position: relative;
	line-height: 25px;
}

body {
	margin: 0px;
}

.toolbar {
	width: 100px;
}
</style>
<script type="text/javascript">
	//一寸照片弹出div
	function photosdiv() {
		var strHtml = "<form action='SavePhotos' method='post' id='Photosform' enctype='multipart/form-data'  autocomplete='off'"
			+ "data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true} \">"
				+ "<div id='beforesend'>"
				+ "<div class='editBlock' id='photodiv'>"
				+ "<table>"
				+ "	<tr>"
				+ "<th>上传照片</th>"
				+ "<input type='hidden' id='photosresumeId' name='resumeId' value='${mv.model['resume'].resumeId}'>"
				+ "<td>"
				+ "<input type='file' name='resumePhotos'  id='resumePhotos' class='inputText' style='width:250px;'/>"
				+ "<input type='button' value='保存' onclick='enterPhotos()' class='inputButton' size=\"12\" >"
				+ "</td>"
				+ "</tr>"
				+ "<tr id='xinzeng'>"
				+ "</tr>"
				+ "<div style='height:16px;color:red;'>照片尺寸：2.5*3.5cm 或者413*295像素，*.png、*.jpg、*.bmp 格式，文件小于1M。</div>"
				+ "</table>" + "</div>" + "</div>" + "</form>";
		$.jBox.open(strHtml, '上传一寸照片 ', 500, 140, {
			buttons : {
				'关闭' : true
			}
		});
	}

	function addOption(_id, _cid) {
		var _value = $("#" + _cid).attr("value");
		if (_value.indexOf("其他") > -1)
			return;
		var arr_select = [];

		$("#" + _id + " option").each(function() {
			arr_select.push($(this).val());
		});

		arr_select.push($("#" + _cid).attr("value"));

		$("#" + _id).empty();
 
		for ( var k = 0; k < arr_select.length; k++) {
			if (k == arr_select.length - 1)
				$("#" + _id).append(
						"<option value='"+arr_select[k]+"' selected=selected>"
								+ arr_select[k] + "</option>");
			else
				$("#" + _id).append(
						"<option value='"+arr_select[k]+"'>" + arr_select[k]
								+ "</option>");
		}
		$.jBox.close();
	}

	//变态的其他选择框
	function qita(id) {
		var _text = $("#" + id).find("option:selected").text();
		//alert(_text == "党员");
		if (_text.indexOf("其他") > -1) {

			$("#showThis").hide();
			var cid = id + "ooooo";
			var strHtml = "<div style='margin-top:8px;'>"
					+ "&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' name='"+cid+"' id='"+cid+"' class='inputText' style='width:250px;'/>&nbsp;&nbsp;"
					+ "<input type='button' value='确定' onclick=\"javascript:addOption('"
					+ id + "','" + cid
					+ "');\" class='inputButton' size=\"12\" />" + "</div>";
			$.jBox.open(strHtml, _text, 380, 105);
		} else if (_text == "党员") {
			$("#showThis").show(1000);
			$("#isPartyM").hide();
			//$("#"+_id).append("<option value='"+arr_select[k]+"'>"+arr_select[k]+"</option>");

		}
	}
</script>
</head>
<body>
	<input type="hidden" id="resumeId" name="resumeId" value="${mv.model['userinfo'].userId}" />
	<div onload="csschange(resume)">
		<ul class="editBlock1">
			<li id="li1">
				<a href="#div1" id="href1" class="href" style="left: 20px; top: 35px">基本信息</a>
			</li>
			<li id="li2">
				<a href="#div2" id="href2" class="href" style="left: 20px; top: 36px">联系方式</a>
			</li>
			<li id="li3">
				<a href="#div3" id="href3" class="href" style="left: 20px; top: 37px">教育经历</a>
			</li>
			<li id="li11">
				<a href="#div11" id="href11" class="href" style="left: 20px; top: 38px">语言能力</a>
			</li>
			<li id="li8">
				<a href="#div8" id="href8" class="href" style="left: 20px; top: 39px">工作经历</a>
			</li>
			<%--<li id="li4">
				<a href="#div4" id="href4" class="href" style="left: 20px; top: 40px; color: #000;">校内奖励</a>
			</li>
			<li id="li5">
				<a href="#div5" id="href5" class="href" style="left: 20px; top: 41px">校内职务</a>
			</li>
			<li id="li6">
				<a href="#div6" id="href6" class="href" style="left: 20px; top: 42px">实践经历</a>
			</li>
			<li id="li7">
				<a href="#div7" id="href7" class="href" style="left: 20px; top: 43px">实习经历</a>
			</li>
			<li id="li9">
				<a href="#div9" id="href9" class="href" style="left: 20px; top: 44px">项目经验</a>
			</li>--%>
			<li id="li10">
				<a href="#div10" id="href10" class="href" style="left: 20px; top: 45px">家庭关系</a>
			</li>

			<%--<li id="li12">
				<a href="#div12" id="href12" class="href" style="left: 20px; top: 46px">证书</a>
			</li>
			<li id="li13">
				<a href="#div13" id="href13" class="href" style="left: 20px; top: 47px">IT技能</a>
			</li>
			--%>
			<li id="li14">
				<a href="#div14" id="href14" class="href" style="left: 20px; top: 48px">其他信息</a>
			</li>
			<%--<li id="li15">
				<a href="#div15" id="href15" class="href" style="left: 20px; top: 49px">附件/作品</a>
			</li>
		--%>
		</ul>
		<ul id="ul1" class="editBlock2 editBlock">
			<div id="div1">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="subtitle">
								<a href="javascript:show_div1()">隐藏/展开</a>基本信息
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="_div1">
				<form action="SaveJbxx" id="form1" method="post" autocomplete="off"
					data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
					<div>
						<input type="hidden" id="resumeId" name="resumeId" value="${mv.model['userinfo'].userId}" />
					</div>
					<table>
						<tbody>
							<tr>
								<th style="width: 112px; height: 29px; text-align: right">
									<span class="warning">*</span>
									姓名
								</th>
								<td>
									<input type="text" class="inputText" id="resumeName" name="resumeName"
										value="${mv.model['userinfo'].userName}" readonly="readonly" data-rule="用户名:required;"
										maxlength="10" />
									<span class="note">&nbsp;10字符以内</span>
									<input type="hidden" class="inputText" id="resumeTelphone" name="resumeTelphone"
										value="${mv.model['userinfo'].userTelephone}" />
									<input type="hidden" class="inputText" id="resumeEmail" name="resumeEmail"
										value="${mv.model['userinfo'].userEmail}" />
								</td>
								<td class="toolbar">
									<input type="submit" id="JbxxSaveinput1" class="inputButton" value="保存" />
								</td>
							</tr>
							<tr>
								<th style="text-align: right">
									<span class="warning">*</span>
									性别
								</th>
								<td>
									<input type="radio" id="resumeSex" name="resumeSex" value="男" checked="checked" />
									男&nbsp;&nbsp;
									<input type="radio" id="resumeSex2" name="resumeSex" value="女" />
									女
								</td>

								<td rowspan="5" id="photos" style="width: 100px">
									<c:choose>
										<c:when
											test='${mv.model["resume"].resumePhotos == null || fn:trim(mv.model["resume"].resumePhotos) == ""}'>
											<img id="uploadImg" src="${mv.model['resume'].resumePhotos}"  alt="请上传照片" width="90" height="100"
												style="margin-left: 5px;"  />
										</c:when>
										<c:otherwise>
											<img id="uploadImg" src="${mv.model['resume'].resumePhotos}" width="90" height="100"
												style="margin-left: 5px;" />
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<th style="text-align: right">
									<span class="warning">*</span>
									民族
								</th>
								<td>
									<input type="text" class="inputText" data-rule="民族:required;" id="resumeItjtjn"
										maxlength="5" name="resumeItjtjn" value="${mv.model['resume'].resumeItjtjn}" />
								</td>
							</tr>
							<tr>
								<th style="text-align: right">
									<span class="warning">*</span>
									出生日期
								</th>
								<td>
									<input type="text" class="inputText" readonly="readonly" id="resumeBirthday"
										name="resumeBirthday" data-rule="出生日期:required;resumeBirthday"
										value="${mv.model['resume'].resumeBirthday}" />
									<span class="note">&nbsp;日期格式:YYYY.MM.DD</span>
								</td>
							</tr>
							<tr>
								<th style="text-align: right">
									<span class="warning">*</span>
									身份证号
								</th>
								<td>
									<input type="text" class="inputText" id="resumeSfzh" data-rule="身份证号:required;length[18]"
										id="resumeSfzh" readonly="readonly" maxlength="18" name="resumeSfzh"
										value="${mv.model['userinfo'].userSfzh}" />
									<span class="note">&nbsp;18位身份证号</span>
								</td>
							</tr>
							<tr>
								<th style="text-align: right">
									<span class="warning">*</span>
									籍贯
								</th>
								<td>
									<select id="resumeHdzs" name="resumeHdzs" onchange="addOptionsagain2(this.value)"
										data-rule="籍贯省份:required;">
										<option value=""></option>
										<option value="北京市">北京市</option>
										<option value="天津市">天津市</option>
										<option value="上海市">上海市</option>
										<option value="重庆市">重庆市</option>
										<option value="河北省">河北省</option>
										<option value="山西省">山西省</option>
										<option value="陕西省">陕西省</option>
										<option value="山东省">山东省</option>
										<option value="河南省">河南省</option>
										<option value="辽宁省">辽宁省</option>
										<option value="吉林省">吉林省</option>
										<option value="黑龙江省">黑龙江省</option>
										<option value="江苏省">江苏省</option>
										<option value="浙江省">浙江省</option>
										<option value="安徽省">安徽省</option>
										<option value="江西省">江西省</option>
										<option value="福建省">福建省</option>
										<option value="湖北省">湖北省</option>
										<option value="湖南省">湖南省</option>
										<option value="四川省">四川省</option>
										<option value="贵州省">贵州省</option>
										<option value="云南省">云南省</option>
										<option value="广东省">广东省</option>
										<option value="海南省">海南省</option>
										<option value="甘肃省">甘肃省</option>
										<option value="青海省">青海省</option>
										<option value="台湾省">台湾省</option>
										<option value="内蒙古自治区">内蒙古自治区</option>
										<option value="新疆维吾尔自治区">新疆维吾尔自治区</option>
										<option value="西藏自治区">西藏自治区</option>
										<option value="广西壮族自治区">广西壮族自治区</option>
										<option value="宁夏回族自治区">宁夏回族自治区</option>
										<option value="香港特别行政区">香港特别行政区</option>
										<option value="澳门特别行政区">澳门特别行政区</option>
										<option value="国外">国外</option>
									</select>&nbsp;<select id="resumeQtzs" name="resumeQtzs" data-rule="籍贯城市:required;">
										<option value=""></option>
									</select>
								</td>
							</tr>
							<tr>
								<th style="text-align: right">
									<span class="warning">*</span>
									户口地
								</th>
								<td>
									<select id="resumeMqszcsProvince" data-rule="户口地省份:required;" name="resumeMqszcsProvince"
										onchange="addOptionsagain(this.value)">
										<option value=""></option>
										<option value="北京市">北京市</option>
										<option value="天津市">天津市</option>
										<option value="上海市">上海市</option>
										<option value="重庆市">重庆市</option>
										<option value="河北省">河北省</option>
										<option value="山西省">山西省</option>
										<option value="陕西省">陕西省</option>
										<option value="山东省">山东省</option>
										<option value="河南省">河南省</option>
										<option value="辽宁省">辽宁省</option>
										<option value="吉林省">吉林省</option>
										<option value="黑龙江省">黑龙江省</option>
										<option value="江苏省">江苏省</option>
										<option value="浙江省">浙江省</option>
										<option value="安徽省">安徽省</option>
										<option value="江西省">江西省</option>
										<option value="福建省">福建省</option>
										<option value="湖北省">湖北省</option>
										<option value="湖南省">湖南省</option>
										<option value="四川省">四川省</option>
										<option value="贵州省">贵州省</option>
										<option value="云南省">云南省</option>
										<option value="广东省">广东省</option>
										<option value="海南省">海南省</option>
										<option value="甘肃省">甘肃省</option>
										<option value="青海省">青海省</option>
										<option value="台湾省">台湾省</option>
										<option value="内蒙古自治区">内蒙古自治区</option>
										<option value="新疆维吾尔自治区">新疆维吾尔自治区</option>
										<option value="西藏自治区">西藏自治区</option>
										<option value="广西壮族自治区">广西壮族自治区</option>
										<option value="宁夏回族自治区">宁夏回族自治区</option>
										<option value="香港特别行政区">香港特别行政区</option>
										<option value="澳门特别行政区">澳门特别行政区</option>
										<option value="国外">国外</option>
									</select>&nbsp;<select id="resumeMqszcsCity" name="resumeMqszcsCity" data-rule="户口地城市:required;">
										<option value=""></option>
									</select>
								</td>
								<td class="toolbar">
									<span class="warning">*</span>
									<input id="Button1" type="button" class="inputButton" value="上传照片" onclick="photosdiv()" />
								</td>
							</tr>

							<tr>
								<th style="text-align: right">
									<span class="warning">*</span>
									出生地
								</th>
								<td>
									<select id="resumeRxqhkszcsProvince" name="resumeRxqhkszcsProvince"
										onchange="addOptionsagainandagain(this.value)" data-rule="出生地省份:required;">
										<option value=""></option>
										<option value="北京市">北京市</option>
										<option value="天津市">天津市</option>
										<option value="上海市">上海市</option>
										<option value="重庆市">重庆市</option>
										<option value="河北省">河北省</option>
										<option value="山西省">山西省</option>
										<option value="陕西省">陕西省</option>
										<option value="山东省">山东省</option>
										<option value="河南省">河南省</option>
										<option value="辽宁省">辽宁省</option>
										<option value="吉林省">吉林省</option>
										<option value="黑龙江省">黑龙江省</option>
										<option value="江苏省">江苏省</option>
										<option value="浙江省">浙江省</option>
										<option value="安徽省">安徽省</option>
										<option value="江西省">江西省</option>
										<option value="福建省">福建省</option>
										<option value="湖北省">湖北省</option>
										<option value="湖南省">湖南省</option>
										<option value="四川省">四川省</option>
										<option value="贵州省">贵州省</option>
										<option value="云南省">云南省</option>
										<option value="广东省">广东省</option>
										<option value="海南省">海南省</option>
										<option value="甘肃省">甘肃省</option>
										<option value="青海省">青海省</option>
										<option value="台湾省">台湾省</option>
										<option value="内蒙古自治区">内蒙古自治区</option>
										<option value="新疆维吾尔自治区">新疆维吾尔自治区</option>
										<option value="西藏自治区">西藏自治区</option>
										<option value="广西壮族自治区">广西壮族自治区</option>
										<option value="宁夏回族自治区">宁夏回族自治区</option>
										<option value="香港特别行政区">香港特别行政区</option>
										<option value="澳门特别行政区">澳门特别行政区</option>
										<option value="国外">国外</option>
									</select>&nbsp;<select id="resumeRxqhkszcsCity" name="resumeRxqhkszcsCity"
										data-rule="出生地城市:required;">
										<option value=""></option>
									</select>
								</td>
							</tr>

							<tr>
								<th style="width: 112px; height: 29px; text-align: right">
									<span class="warning">*</span>
									健康状况
								</th>
								<td>
									<input type="text" class="inputText" name="resumeItsxjn" data-rule="健康状况:required;"
										maxlength="15" value="${mv.model['resume'].resumeItsxjn}" />
									<span class="note">&nbsp;15个字符以内</span>
								</td>

							</tr>
							<tr>
								<th style="width:112px;height: 29px;text-align:right">
									<span class="warning">*</span>
									政治面貌
								</th>
								<td>
									<select id="resumeZzmm" name="resumeZzmm" data-rule="政治面貌:required"
										onChange="javascript:qita(this.id);">
										<option value=""></option>
										<option value="党员">党员</option>
										<option value="其他">其他</option>
									</select>
									<input type="text" style="display:none" id="isPartyM" />
								</td>
							</tr>
							<tr id="showThis" style="display:none">
								<th style="text-align: right">
									<span class="warning">*</span>
									入党时间
								</th>
								<td>
									<input type="text" class="inputText" id="resumeQtjn" name="resumeQtjn"
										data-rule="入党时间:required;" value="${mv.model['resume'].resumeQtjn}" />
									<span class="note">&nbsp;日期格式:YYYY.MM</span>
								</td>
							</tr>
							<tr>
								<th style="text-align: right">
									<span class="warning">*</span>
									职称（资格）
								</th>
								<td>
									<input type="text" class="inputText" id="resumeZczg" name="resumeZczg"
										data-rule="职称（资格）:required;" value="${mv.model['resume'].resumeZczg}" />
									<!-- <span class="note">&nbsp;日期格式:YYYY.MM</span> -->
								</td>
							</tr>

							<tr>
								<th style="text-align: right">
									<span class="warning">*</span>
									参加工作时间
								</th>
								<td>
									<input type="text" class="inputText" id="resumeCjgzsj" name="resumeCjgzsj"
										data-rule="参加工作时间:required;" value="${mv.model['resume'].resumeCjgzsj} " />
									<span class="note">&nbsp;日期格式:YYYY.MM</span>
								</td>
							</tr>
							<tr>
								<th style="width:112px;height: 29px;text-align:right">
									<span class="warning">*</span>
									拟应聘岗位1
								</th>
								<td>
									<select id="resumeNypgw1" name="resumeNypgw1" data-rule="拟应聘岗位1:required">
										<option value=""></option>
										<option value="职位1">职位1</option>
										<option value="职位2">职位2</option>
									</select>
								</td>
							</tr>
							<tr>
								<th style="width:112px;height: 29px;text-align:right">拟应聘岗位2</th>
								<td>
									<select id="resumeNypgw2" name="resumeNypgw2">
										<option value=""></option>
										<option value="职位1">职位1</option>
										<option value="职位2">职位2</option>
									</select>
								</td>
							</tr>
							

							<%-- <tr>
								<th style="text-align: right">
									<span class="warning">*</span>
									学位
								</th>
								<td>
									<input type="text" class="inputText" id="resumeXuew" name="resumeXuew"
										data-rule="学位:required;" value="${mv.model['resume'].resumeXuew} " />
									<!-- <span class="note">&nbsp;日期格式:YYYY.MM</span> -->
								</td>
							</tr> --%>
						</tbody>
					</table>
				</form>
			</div>
			<div id="div2">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="subtitle">
								<a href="javascript:show_div2()">隐藏/展开</a>联系方式
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="_div2">
				<form action="SaveLxfs" id="form2" method="post" autocomplete="off"
					data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
					<div>
						<input type="hidden" id="resumeId" name="resumeId" value="${mv.model['userinfo'].userId}" />
					</div>
					<table>
						<tr>
							<th style="width: 112px; height: 29px; text-align: right">
								<span class="warning">*</span>
								手机号码
							</th>
							<td>
								<input type="text" class="inputText" id="inputid" name="resumeTelphone"
									data-rule="手机号码:required;mobile;" maxlength="11" />
								<span class="note">&nbsp;11位手机号码</span>
							</td>



							<td class="toolbar" style="width: 100px;">
								<input type="submit" class="inputButton" value="保存" />
							</td>
						</tr>
						<tr>
							<th style="text-align: right">
								<span class="warning">*</span>
								电子邮箱
							</th>

							<td colspan="2">
								<input type="text" class="inputText" id="emailid" name="resumeEmail"
									data-rule="电子邮箱:required;email;length[4~]" />
							</td>

						</tr>
						<tr>
								<th style="text-align: right">
									<!-- <span class="warning">*</span> -->
									办公电话
								</th>
								<td>
									<input type="text" class="inputText" id="resumeBgSdh" name="resumeBgSdh"
										value="${mv.model['resume'].resumeBgSdh} " />
									<!-- <span class="note">&nbsp;日期格式:YYYY.MM</span> -->
								</td>
							</tr>
					</table>
				</form>
			</div>


			<div id="div3">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="subtitle">
								<a href="javascript:show_div3()">隐藏/展开</a>教育经历
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="ddiv3"></div>
			<div id="dddiv3">
				<table colspan="3">
					<tr>
						<td colspan="3" class="toolbar">
							<input type="button" class="inputButton" value="继续添加" onclick="adddiv3()" />
							&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</div>










			<div id="div11">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="subtitle">
								<a href="javascript:show_div11()">隐藏/展开</a>语言能力
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="ddiv11"></div>
			<div id="dddiv11">
				<table colspan="3">
					<tr>
						<td colspan="3" class="toolbar">
							<input type="button" class="inputButton" value="继续添加" onclick="adddiv11()">&nbsp;&nbsp;







							
						</td>
					</tr>
				</table>
			</div>
			<div id="div8">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="subtitle">
								<a href="javascript:show_div8()">隐藏/展开</a>工作经历
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="ddiv8"></div>
			<div id="dddiv8">
				<table colspan="3">
					<tr>
						<td colspan="3" class="toolbar">
							<input type="button" class="inputButton" value="继续添加" onclick="adddiv8()">&nbsp;&nbsp;







							
						</td>
					</tr>
				</table>
			</div>
			<%--<div id="div4">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="subtitle">
								<a href="javascript:show_div4()">隐藏/展开</a>校内奖励
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="ddiv4"></div>
			<div id="dddiv4">
				<table colspan="3">
					<tr>
						<td colspan="3" class="toolbar">
							<input type="button" class="inputButton" value="继续添加" onclick="adddiv4()">&nbsp;&nbsp;


							
						</td>
					</tr>
				</table>
			</div>



			<div id="div5">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="subtitle">
								<a href="javascript:show_div5()">隐藏/展开</a>校内职务
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="ddiv5"></div>
			<div id="dddiv5">
				<table colspan="3">
					<tr>
						<td colspan="3" class="toolbar">
							<input type="button" class="inputButton" value="继续添加" onclick="adddiv5()">&nbsp;&nbsp;


							
						</td>
					</tr>
				</table>
			</div>



			<div id="div6">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="subtitle">
								<a href="javascript:show_div6()">隐藏/展开</a>实践经历
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="ddiv6"></div>
			<div id="dddiv6">
				<table colspan="3">
					<tr>
						<td colspan="3" class="toolbar">
							<input type="button" class="inputButton" value="继续添加" onclick="adddiv6()">&nbsp;&nbsp;


							
						</td>
					</tr>
				</table>
			</div>

			<div id="div7">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="subtitle">
								<a href="javascript:show_div7()">隐藏/展开</a>实习经历
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="ddiv7"></div>
			<div id="dddiv7">
				<table colspan="3">
					<tr>
						<td colspan="3" class="toolbar">
							<input type="button" class="inputButton" value="继续添加" onclick="adddiv7()">&nbsp;&nbsp;


							
						</td>
					</tr>
				</table>
			</div>


			<div id="div9">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="subtitle">
								<a href="javascript:show_div9()">隐藏/展开</a>项目经验
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="ddiv9"></div>
			<div id="dddiv9">
				<table colspan="3">
					<tr>
						<td colspan="3" class="toolbar">
							<input type="button" class="inputButton" value="继续添加" onclick="adddiv9()">&nbsp;&nbsp;


							
						</td>
					</tr>
				</table>
			</div>

			--%>
			<div id="div10">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="subtitle">
								<a href="javascript:show_div10()">隐藏/展开</a>家庭关系(含配偶、子女、父亲、母亲、岳父（公）、岳母（婆），并按照顺序添写，若无可不填写。)
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="ddiv10"></div>
			<div id="dddiv10">
				<table colspan="3">
					<tr>
						<td colspan="3" class="toolbar">
							<input type="button" class="inputButton" value="继续添加" onclick="adddiv10()">&nbsp;&nbsp;







							
						</td>
					</tr>
				</table>
			</div>


			<%--<div id="div12">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="subtitle">
								<a href="javascript:show_div12()">隐藏/展开</a>证书
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="ddiv12"></div>
			<div id="_div12">
				<table colspan="3">
					<tr>
						<td colspan="3" class="toolbar">
							<input type="button" class="inputButton" value="继续添加" onclick="adddiv12()">&nbsp;&nbsp;


							
						</td>
					</tr>
				</table>
			</div>

			<div id="div13">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="subtitle">
								<a href="javascript:show_div13()">隐藏/展开</a>IT技能
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="ddiv13"></div>
			<div id="_div13">
				<table colspan="3">
					<tr>
						<td colspan="3" class="toolbar">
							<input type="button" class="inputButton" value="继续添加" onclick="adddiv13()">&nbsp;&nbsp;


							
						</td>
					</tr>
				</table>
			</div>

			--%>
			<div id="div14">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="subtitle">
								<a href="javascript:show_div14()">隐藏/展开</a> 其他信息
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div>
				<form action="SaveQtxx" id="form2012" method="post" autocomplete="off"
					data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
					<div>
						<input type="hidden" id="resumeId" name="resumeId" value="${mv.model['userinfo'].userId}" />
					</div>
					<table>
						<tr>
							<th style="width: 112px; height: 29px; text-align: right">
								<span class="warning">*</span>
								奖惩情况
							</th>
							<td>
								<textarea cols="50" rows="4" name="resumeJcqk" maxlength="500" data-rule="奖惩情况:required;">${mv.model['resume'].resumeJcqk } </textarea>
							</td>

						</tr>
						<tr>
							<th style="width: 112px; height: 29px; text-align: right">
								<span class="warning">*</span>
								培训情况
							</th>
							<td>
								<textarea cols="50" rows="4" name="resumePxqk" maxlength="500" data-rule="培训情况:required;">${mv.model['resume'].resumePxqk } </textarea>
							</td>

						</tr>
						<tr>
							<th style="width: 112px; height: 29px; text-align: right">
								<span class="warning">*</span>
								熟悉何种专业技术及有何种特长
							</th>
							<td>
								<textarea cols="50" rows="4" name="resumeSxhzjn" maxlength="1000" data-rule="熟悉何种专业技术及有何种特长:required;">${mv.model['resume'].resumeSxhzjn } </textarea>
							</td>

						</tr>
						<tr>
							<th style="width: 112px; height: 29px; text-align: right">
								<span class="warning">*</span>
								兴趣爱好
							</th>
							<td>
								<textarea cols="50" rows="4" name="resumeXqah" maxlength="500" data-rule="兴趣爱好:required;">${mv.model['resume'].resumeXqah } </textarea>
							</td>

						</tr>

						<tr>
							<td></td>
							<td class="toolbar" style="width: 100px;">
								<input  type="submit" class="inputButton" value="保存" />
							</td>

						</tr>
					</table>
				</form>
			</div>
			<%-- <div id="_div222">
				<form action="SaveLxfs" id="form2" method="post" autocomplete="off"
					data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
					<div>
						<input type="hidden" id="resumeId" name="resumeId" value="${mv.model['userinfo'].userId}" />
					</div>
					<table>
						<tr>
							<th style="width: 112px; height: 29px; text-align: right">
								<span class="warning">*</span>
								奖惩情况
							</th>

							<td class="toolbar" style="width: 100px;">
								<input type="submit" class="inputButton" value="保存" />
							</td>
						</tr>
						<tr>
							<th style="text-align: right">
								<span class="warning">*</span>
								电子邮箱
							</th>

							<td colspan="2">
								<input type="text" class="inputText" id="emailid" name="resumeEmail"
									data-rule="电子邮箱:required;email;length[4~]" />
							</td>

						</tr>
					</table>
				</form>
			</div> --%>



			<%--<div id="ddiv14">
			</div>
			--%>
			<%--<div id="dddiv14">
				<table colspan="3">
					<tr>
						<td colspan="3" class="toolbar">
							<input type="button" class="inputButton" value="继续添加" onclick="adddiv14()" />&nbsp;&nbsp; 

						</td>
					</tr>
				</table>
			</div>

			--%>
			<%--<div id="div15">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="subtitle">
								<a href="javascript:show_div15()">隐藏/展开</a>附件/作品(单次上传最大500k,最多上传5份)
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="sczzjl">
				<form action="SaveZzjl" method="post" enctype="multipart/form-data" id="form16"
					autocomplete="off" data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
					<input type="hidden" id="zzjlresumeId" name="zzjlresumeId"
						value="${mv.model['resume'].resumeId}" />
					<!-- <div id="zzjlhtml"></div> -->
					<table>
						<tbody>
							<tr>
								<div id="ddiv19"></div>
								<th style="width: 112px; height: 29px; text-align: right">上传自制简历</th>
								<td>
									<input type="file" class="inputText" name="resumeZzjl" id="fileZzjl" maxlength="50" />
								</td>
								<td class="toolbar">
									<input type="button" class="inputButton" onclick="enterandenter19()" value="保存" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<div id="123">
				<form action="SaveFj" method="post" enctype="multipart/form-data" id="form15" autocomplete="off"
					data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
					<input type="hidden" id="fjresumeId" name="resumeId" value="${mv.model['resume'].resumeId}" />
					<table>
						<tbody>
							<tr>
								<div id="ddiv15"></div>
								<th style="width: 112px; height: 29px; text-align: right">附件地址</th>
								<td>
									<input type="file" class="inputText" name="resumeFj" id="file" maxlength="50" />
								</td>
								<td class="toolbar">
									<input type="button" class="inputButton" onclick="enterandenter15()" value="保存" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>--%>
		</ul>
		<table style="margin-left: 350px;">
			<tr text-align="center;">
				<td class="toolbar">
					<input type="button" class="inputButton" onclick="aaa()" value="提交简历" />
				</td>
				<td class="toolbar">
					<input type="button" class="inputButton" onclick="look()" value="预览简历" />
				</td>
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//页面载入时下拉框变化
						$("#resumeRxqhkszcsProvince")
								.val(
										"${mv.model['resume'].resumeRxqhkszcsProvince}");
						addOptionsagainandagain("${mv.model['resume'].resumeRxqhkszcsProvince}");
						$("#resumeRxqhkszcsCity").val(
								"${mv.model['resume'].resumeRxqhkszcsCity}");

						$("#resumeMqszcsProvince").val(
								"${mv.model['resume'].resumeMqszcsProvince}");
						addOptionsagain("${mv.model['resume'].resumeMqszcsProvince}");
						$("#resumeMqszcsCity").val(
								"${mv.model['resume'].resumeMqszcsCity}");

						$("#resumeHdzs")
								.val("${mv.model['resume'].resumeHdzs}");
						addOptionsagain2("${mv.model['resume'].resumeHdzs}");
						$("#resumeQtzs")
								.val("${mv.model['resume'].resumeQtzs}");

						//拟应聘岗位信息自动填入
						$("#resumeNypgw1").val(
								"${mv.model['resume'].resumeNypgw1}");
						$("#resumeNypgw2").val(
								"${mv.model['resume'].resumeNypgw2}");

						//是否党员自动填写
						if ("${mv.model['resume'].resumeZzmm}" == "党员") {

							$("#resumeZzmm").val(
									"${mv.model['resume'].resumeZzmm}");
							$("#isPartyM").hide();
							$("#showThis").show();
							//alert("${mv.model['resume'].resumeZzmm}" == "党员");
						} else {
							$("#resumeZzmm").val("其他");
							//alert("${mv.model['resume'].resumeZzmm}");
							$("#isPartyM").val(
									"${mv.model['resume'].resumeZzmm}");
							$("#isPartyM").show();
						}

						//注册信性别自动填写
						if ("${mv.model['userinfo'].userSex}" == "女") {
							$("#resumeSex").removeAttr("checked");
							$("#resumeSex2").attr("checked", "checked");
						}
						if ("${mv.model['resume'].resumeSex}" == "男") {
							$("#resumeSex2").removeAttr("checked");
							$("#resumeSex").attr("checked", "checked");
						}
						if ("${mv.model['resume'].resumeSex}" == "女") {
							$("#resumeSex").removeAttr("checked");
							$("#resumeSex2").attr("checked", "checked");
						}

						//注册信息自动填写
						if ("${mv.model['resume'].resumeTelphone}" != null
								&& "${mv.model['resume'].resumeTelphone}" != "") {
							$("#inputid").val(
									"${mv.model['resume'].resumeTelphone}");
						}
						if ("${mv.model['resume'].resumeTelphone}" == null
								|| "${mv.model['resume'].resumeTelphone}" == "") {
							$("#inputid").val(
									"${mv.model['userinfo'].userTelephone}");
						}
						if ("${mv.model['resume'].resumeEmail}" != null
								&& "${mv.model['resume'].resumeEmail}" != "") {
							$("#emailid").val(
									"${mv.model['resume'].resumeEmail}");
						}
						if ("${mv.model['resume'].resumeEmail}" == null
								|| "${mv.model['model'].resumeEmail}" == "") {
							$("#emailid").val(
									"${mv.model['userinfo'].userEmail}")
						}

						//导航栏变色
						if ("${mv.model['resume'].resumeName}" != ""
								&& "${mv.model['resume'].resumeName}" != null
								&& "${mv.model['resume'].resumeSex}" != ""
								&& "${mv.model['resume'].resumeSex}" != null
								&& "${mv.model['resume'].resumeSfzh}" != ""
								&& "${mv.model['resume'].resumeSfzh}" != null
								&& "${mv.model['resume'].resumeBirthday}" != ""
								&& "${mv.model['resume'].resumeBirthday}" != null) {
							$("#href1").css('color', 'green');
						}
						if ("${mv.model['resume'].resumeTelphone}" != ""
								&& "${mv.model['resume'].resumeTelphone}" != null
								&& "${mv.model['resume'].resumeEmail}" != ""
								&& "${mv.model['resume'].resumeEmail}" != null) {
							$("#href2").css('color', 'green');
						}
					});
	function aaa() {//遍历并去除每一个元素的值
		var confirmTipBox = confirm("是否确认提交简历，若点击“确认”本次应聘简历将不可再修改！");
		if (confirmTipBox == true) {
			var id = $('#resumeId').val();
			//验证图片是否为空
			var uploadImg = $("#uploadImg").attr("src");
			if(uploadImg == '' || uploadImg == "null"){
				alert("请上传照片");
				return ;
			}
			$.jBox.tip('正在保存简历信息...', 'loading');
			window
					.setTimeout(
							function() {
								if (id == "") {
									alert("请先将基本信息填写完成并保存!");
									return;
								}else {
									var sFlag = 0;
									$("form")
											.each(
													function() {
														var values, index;
														values = $(this)
																.serializeArray();
														for (index = 0; index < values.length; index++) {
															if ($('#form1')
																	.isValid()
																	&& $(
																			'#form2')
																			.isValid()
																	&& $(
																			'#form2012')
																			.isValid()
																	&& $(
																			'#form2000')
																			.isValid()
																	&& $(
																			'#form3200')
																			.isValid()) {
																if (this.id == "form15")
																	break;
																var frmId = parseInt(this.id
																		.replace(
																				"form",
																				""));
																if ((frmId >= 2000 && frmId < 2500)
																		|| (frmId >= 3200 && frmId < 3299)) {
																	if ($(
																			'#'
																					+ this.id)
																			.isValid()) {
																		$
																				.ajax({
																					url : this.action,
																					type : 'POST',
																					data : $(
																							this)
																							.serialize(),
																					async : false,
																					success : function(
																							d) {
																						sFlag = 1;
																					}
																				});
																		break;
																	}
																} else {
																	var _v = values[index].value;
																	var _n = values[index].name;
																	if (_n != "resumeId") {
																		if (_v != "") {
																			$
																					.ajax({
																						url : this.action,
																						type : 'POST',
																						data : $(
																								this)
																								.serialize(),
																						async : false,
																						success : function(
																								d) {
																							sFlag = 1;
																						}
																					});
																			break;
																		}
																	}
																}
															}
														}
													});
									if (sFlag == 1) {
										$.jBox.tip('提交成功!', 'success');
										window.location.href = 'ApplyPosition?id='
												+ id;
										;
<%--									window.location='queryByBq?ltbqName=${map.ltbqName}&ltbqId=${map.ltbqId}'--%>
	} else {
										$.jBox.tip('请按提示规则填写内容!', 'success');
									}
								}
							}, 1000);
		}
	}
</script>
</html>
