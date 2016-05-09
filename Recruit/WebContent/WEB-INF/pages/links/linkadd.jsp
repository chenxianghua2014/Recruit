<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<form action="linkadd" method="post" enctype ="multipart/form-data">
		<div class="title">当前位置:友情链接>友情链接</div>
		<input name="mbglId" type="hidden" class="mbglId" id="mbglName" value="${ mbgl.mbglId}" />
		<div class="editBlock">
			<table>
				<tbody>
					<tr>
						<td height="32" colspan="2" class="subtitle">添加友情链接</td>
					</tr>
					<tr>
						<th width="150">友情链接栏目:</th>
						<td>
						<select name="linkCat">
							<option value="集团直属二级单位" selected="selected">集团直属二级单位</option>
							<option value="集团所属三级单位">集团所属三级单位</option>
						</select>
						</td>
					</tr>
					<tr>
						<th width="150">友情链接名称:</th>
						<td><input name="linkName" type="text" class="inputText" style="width:300px;" value="" /></td>
					</tr>
					<tr>
						<th width="150">友情链接地址:</th>
						<td><input name="linkl" type="text" class="inputText" style="width:300px;" value="" /></td>
					</tr>
					<tr>
						<th width="150">上传图片:</th>
						<td><input name="linkpic" id="linkpic" type="file"/></td>
					</tr>
					<tr>
						<th width="150"></th>
						<td><input type="submit" class="inputButton" value="保存" />&nbsp;<input type="button" class="inputButton" value="返回" onclick="history.back()" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>
