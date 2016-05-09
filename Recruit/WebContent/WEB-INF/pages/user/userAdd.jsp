<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="resources/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="resources/ueditor/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8"
	src="resources/ueditor/lang/zh-cn/zh-cn.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src=""></script>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript"
	src="resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#zzjgSjdw").find("option[value='${zzjg.zzjgSjdw}']").attr(
						"selected", true);
			});
	function checkPass(){
		if(document.form1.zzjgZhmm.value != document.form1.zzjgZhmm1.value){
			alert("两次输入密码不一致！");
			return false;
		}
		if(Validator.Validate(document.getElementById('ff'),3) ){
		document.form1.action="zzjgSave";
		document.form1.method="post";
		document.form1.submit();
		}
	}

</script>
</head>

<body>
	<div class="editBlock">
		<form name="form1" id="ff">
			<input type="hidden" name="zzjgId" value="${zzjg.zzjgId}" id="zzjgId" />
			<table>
				<tr>
					<td colspan="2" align="left"><span
						style="color:blue;size: 50px;">单位基本信息</span></td>
				</tr>
				<tr>
					<th width="150"><span class="warning">*</span>单位名称:</th>
					<td><input type="text" name="zzjgDwmc"
						value="${zzjg.zzjgDwmc}" dataType="Limit" min="1" max="20" msg="必填(20个字之内)" /></td>
				</tr>
				<tr>
					<th width="150"><span class="warning">*</span>单位代码:</th>
					<td><input type="text" name="zzjgDwdm" value="${zzjg.zzjgDwdm}"
						id="zzjgDwdm" dataType="Require"  msg="必填" /></td>
				</tr>
				<tr>
					<th width="150">单位简称:</th>
					<td><input type="text" name="zzjgDwjc" value="${zzjg.zzjgDwjc}"
						id="zzjgDwdm"/></td>
				</tr>
				<tr>
					<th width="150"><span class="warning">*</span>单位账号:</th>
					<td><input type="text" name="zzjgDwzh"
						value="${zzjg.zzjgDwzh}" dataType="Require"  msg="必填" /></td>
				</tr>
				<tr>
					<th width="150"><span class="warning">*</span>账号密码:</th>
					<td><input type="password" name="zzjgZhmm"
						value="" id=""
						dataType="SafeString"   msg="密码不符合安全规则"/></td>
				</tr>
				<tr>
					<th width="150"><span class="warning">*</span>确认密码:</th>
					<td><input type="password" name="zzjgZhmm1" value="" id="zzjgZhmm1"
						dataType="SafeString"   msg="密码不符合安全规则" /></td>
				</tr>
				<tr>
					<td colspan="2" align="left"><span
						style="color:blue;size: 50px;">单位关联信息</span></td>
				</tr>
				<tr>
					<th width="150"><span class="warning">*</span>上级单位:</th>
					<td><select name="zzjgSjdw" id="zzjgSjdw">
							<option value="">--请选择--</option>
							<option value="test001">集团公司总部</option>
							<c:forEach items="${zzjgFOList}" var="map">
								<option value="${map.zzjgId}">${map.zzjgDwmc}</option>
							</c:forEach>
					</select>
				</tr>
				<tr>
					<td colspan="2" align="left"><span
						style="color:blue;size: 50px;">单位说明信息</span></td>
				</tr>
				<tr>
					<th width="150"><span class="warning">*</span>单位负责人:</th>
					<td><input type="text" name="zzjgDwfzr" value="${zzjg.zzjgDwfzr}"
						id="" dataType="Chinese" msg="真实姓名只允许中文"/></td>
				</tr>
				<tr>
					<th width="150"><span class="warning">*</span>招聘联系人:</th>
					<td><input type="text" name="zzjgZplxr" value="${zzjg.zzjgZplxr}"
						id=""dataType="Chinese" msg="真实姓名只允许中文"/></td>
				</tr>
				<tr>
					<th width="150"><span class="warning">*</span>联系人电话:</th>
					<td><input type="text" name="zzjgLxrdh"
						value="${zzjg.zzjgLxrdh}" id="zzjgLxrdh"
						dataType="Number" msg="手机号码不正确" /></td>
				</tr>
				<tr>
					<th width="150"><span class="warning">*</span>联系人邮箱:</th>
					<td><input type="text" name="zzjgLxremail"
						value="${zzjg.zzjgLxremail}" id=""
						dataType="Email" msg="信箱格式不正确" /></td>
				</tr>
				<tr>
					<th width="150">单位简介:</th>
					<td><textarea rows="5" cols="60" name="zzjgDwjj" id="">
					    ${zzjg.zzjgDwjj}
					    </textarea></td>
				</tr>
				<tr>
					<th></th>
					<td>
					<input type="button" class="inputButton" value="确定" onclick="checkPass();" />
					<input type="button" class="inputButton" value="取消" onclick="history.back()" />	
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
