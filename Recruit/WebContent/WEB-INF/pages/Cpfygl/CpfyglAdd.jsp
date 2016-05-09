<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript">
function SaveCpfygl(){
	if(Validator.Validate(document.getElementById('ff'),3) ){
	document.ff.action="SaveCpfygl";
	document.ff.method="post";
	document.ff.submit();
	}
}

</script>
</head>
<body>
	<div class="title">当前位置:成绩管理>费用管理</div>
	<div class="editBlock">
		<form name="ff" id="ff">
			<input type="hidden" id="memberId" name="memberId"
				value="${ member.memberId }" />
			<table>
				<tr>
					<td colspan="2" class="subtitle">编辑考试现场信息</td>
				</tr>
				<tbody>
					<tr>
						<th width="150"><span class="warning">*</span>单位代码</th>
						<td><input type="text" id="memberIdcard" name="memberIdcard"
							value="${ member.memberIdcard }" disabled="disabled" /></td>
					</tr>
					<tr>
						<th width="150"><span class="warning">*</span>单位名称</th>
						<td><input type="text" id="memberName" name="memberName"
							value="${ member.memberName }" disabled="disabled"/></td>
					</tr>
					<tr>
						<th width="150"><span class="warning">*</span>会员等级</th>
						<td><input type="text" id="memberHydj" name="memberHydj"
							value="${ member.memberHydj }" disabled="disabled"/></td>
					</tr>
					<tr>
						<th><span class="warning">*(可修改)</span>预付费用</th>
						<td><input type="text" id="memberYfkye" name="memberYfkye"
							value="${ member.memberYfkye }" dataType="Currency"  msg="请填写具体金额(精确到小数点后两位)"/></td>
					</tr>
					<tr>
						<th><span class="warning">*(可修改)</span>已用次数</th>
						<td><input type="text" id="memberYycpcs" name="memberYycpcs"
							value="${ member.memberYycpcs}" dataType="Number"  msg="请填写具体数字"/></td>
					</tr>
					<tr>
					<td colspan="2" class="toolbar">
						<input type="button" class="inputButton" value="确定" onclick="SaveCpfygl();"/> &nbsp;&nbsp; 
						<input type="button" class="inputButton" value="取消" onclick="history.back()" />
 					 </td>
				    </tr>
				</tbody>
			</table>
		</form>
	</div>
</body>

</html>