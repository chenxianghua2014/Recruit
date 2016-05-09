<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>社区-论坛帖子</title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript" src="js/htBbs.js"></script>
<script type="text/javascript">
	function BbsSave(){
		
		if(Validator.Validate(document.getElementById('ff'),2) ){
			if(editor.getContent('bbs.bbsNr') == ""){
				alert("您还没有填写内容！请填写您的评论在发表");
			}else{
				document.ff.action = "BbsSave";
				document.ff.method = "POST";
				document.ff.submit();
			}
		}
	}
</script>
<style type="text/css">
.editBlock a{background-color:#fff; border:1px solid #ccc; padding:5px; font-size:12px; color:#000; text-decoration: none;}
</style>
</head>
<body>

	<div class="title">当前位置:内容管理及审核>科工论坛》帖子管理</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle"><c:forEach items="${ltbqList}" var="map">
					<input type="hidden" name="ltbqName" title="${map.ltbqName}"/>
					<a href="javascript:void(0);" onclick="loadData('${map.ltbqName}','${map.ltbqId}')">${map.ltbqName}</a>
					</c:forEach></td>
				</tr>
				<tr>
					<td height="32" colspan="4" class="subtitle">查询</td>
				</tr>
				<tr>
					 <td style="text-align: left;"><input name="button"
						type="button" class="inputButton" value="添加" onclick="htBbsAdd();"/>
					</td>
					<td align="right">
						内容：<input name="bbsNr" type="text" class="inputText" id="bbsNr" />
						发布人：<input name="bbsFbr" type="text" class="inputText" id="bbsFbr" />
						所属板块：<select name="ltbkId" id="ltbkId">
							<option value="">--请选择--</option>
							<c:forEach items="${ltbkList}" var="map">
								<option value="${map.ltbkId }">${map.ltbkName }</option>
							</c:forEach>
						</select> <input name="button" type="button" class="inputButton" value="查询"
						onclick="loadData('')" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>
				查询结果
			</caption>
			<thead>
				<tr>
					<th width="30px" >序号</th>
					<th width="50px" >发布人</th>
					<th width="300px">内容</th>
					<th width="100px">所属板块</th>
					<th  width="50px">评论数目</th>
					<th  width="40px">赞数目</th>
					<th  width="80px">发布时间</th>
					<th width="50px" class="alignCenter">管理评论</th>
					<th width="50px" class="alignCenter">移动分组</th>
					<th width="30px" class="alignCenter">修改</th>
					<th width="30px" class="alignCenter">删除</th>
					<th width="30px" class="alignCenter">禁言</th>
				</tr>
			</thead>
			<tbody id="tbList"></tbody>
		</table>
		<table class="page">
			<tr>
				<td style="text-align: right;"><div id="Pagination"
						class="pagination"></div>
				</td>
				<td style="width:265px;text-align: center;">当前显示 <span
					id="start">1</span> - <span id="end">10</span> 条记录 共 <span
					id="count">0</span> 条记录</td>
			</tr>
		</table>

	</div>
</body>

</html>
