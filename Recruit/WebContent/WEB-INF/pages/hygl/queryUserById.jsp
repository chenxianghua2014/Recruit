<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<script type="text/javascript">
function memberSave(){
	if(Validator.Validate(document.getElementById('ff'),3) ){
	document.bqtj.action="memberSave";
	document.bqtj.method="post";
	document.bqtj.submit();
	}
}
</script>
</head>
  </head>
  
  <body>
  <div class="title">当前位置:会员管理>添加/修改会员信息</div> 
<div class="editBlock">
  <form name="bqtj" id="ff">
  <table>
  <tr>
  <th width="40%"><span class="warning">*</span>用户账号:</th>
  <td><input type="text" name="memberIdcard" value="${user.userIdcard}" dataType="Require"  msg="必填" />
    <input type="hidden" name="memberId" value="${member.memberId}"/>
    </td>
   </tr>
    <tr>
   <th width="40%"><span class="warning">*</span>用户姓名:</th>
  <td>
  <input type="text" name="memberName" value="${user.userName}" dataType="Require"  msg="必填" />
  </td>
  </tr>
   <tr>
   <th width="40%"><span class="warning">*</span>会员等级:</th>
  <td>
  <select name="memberHydj" dataType="Require"  msg="请选择会员等级">
  <option value="">--请选择--</option>
  <c:forEach items="${hydjList}" var="map">
  <option value="${map.hydjName}">${map.hydjName}</option>
  </c:forEach>
  </select>
  </td>
  </tr>
  <tr>
  <td colspan="2" class="toolbar">
			<input type="button" class="inputButton" value="确定" onclick="memberSave();"/> &nbsp;&nbsp; 
			<input type="button" class="inputButton" value="取消" onclick="history.back()" />
  </td>
  </tr>
  </table>
  </form>
  </div>
  </body>
  
</html>
