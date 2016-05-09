<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript">
	function KsxcglSava() {
		if (Validator.Validate(document.getElementById('ff'), 3)) {
			document.ff.action = "KsxcglSava";
			document.ff.method = "post";
			document.ff.submit();
		}
	}
</script>
</head>
<body>
	<div class="title">当前位置:考试管理>考试现场管理</div>
	<div class="editBlock">
		<form name="ff" id="ff">
			<input type="hidden" id="ksxcglId" name="ksxcglId"
				value="${ ksxcgl.ksxcglId }" />
			<table>
				<tr>
					<td colspan="2" class="subtitle">编辑考试现场信息</td>
				</tr>
				<tbody>
					<tr>
						<th width="150"><span class="warning">*</span>考生姓名</th>
						<td><select id="ksxmId" name="ksxmId" dataType="Require"
							msg="未选择考生姓名">
								<option value="">--请选择--</option>
								<c:forEach var="map" items="${bmglList}">
									<option value="${map.bmglId}">${map.bmglKsxm }</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<th align="right"><span class="warning">*</span>考场选择</th>
						<td><select id="ksxcglKc" name="ksxcglKc" dataType="Require"
							msg="未选择考场">
								<option value="">--请选择--</option>
								<c:forEach var="map" items="${kcglList}">
									<option value="${map.kcglId }">${map.kcglName }</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<th align="right">违纪情况</th>
						<td><input type="radio" id="rdo1" name="ksxcglWjqk" value="是" />是
							<input type="radio" id="rdoN2" name="ksxcglWjqk" value="否"
							checked="checked" />否</td>
					<tr>
						<th align="right">特殊情况</th>
						<td><input type="radio" id="rdo1" name="ksxcglTszg" value="是" />是
							<input type="radio" id="rdoN2" name="ksxcglTszg" value="否"
							checked="checked" />否</td>
					</tr>
					<tr>
						<th></th>
						<td><input type="button" class="inputButton" value="保存"
							onclick="KsxcglSava();" /> &nbsp;&nbsp; <input type="button"
							class="inputButton" value="取消" onclick="history.back()" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>

</html>