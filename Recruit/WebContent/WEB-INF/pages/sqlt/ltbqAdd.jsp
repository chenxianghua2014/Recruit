<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>社区-标签管理添加</title>
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

$(document).ready(function() {
	$("#bqlx").find("option[value='${ltbq.ltbqType}']").attr("selected",true);
});

function SaveLtbq(){
	if(Validator.Validate(document.getElementById('ff'),3) ){
	document.ff.action="SaveLtbq";
	document.ff.method="post";
	document.ff.submit();
	}
}
</script>
</head>
  </head>
  
  <body>
  <div class="title">当前位置:论坛标签>添加/修改论坛标签</div> 
<div class="editBlock">
  <form name="ff" id="ff">
  <table>
  <tr>
  <th width="40%"><span class="warning">*</span>标签名称:</th>
  <td><input type="text" name="ltbqName" value="${ltbq.ltbqName}" dataType="Limit" min="1" max="5" msg="必填(5个字之内)"/>
    <input type="hidden" name="ltbqId" value="${ltbq.ltbqId}"/>
    </td>
   </tr>
    <tr>
   <th width="40%"><span class="warning">*</span>标签类型:</th>
  <td>
  <select name="ltbqType" id="bqlx"  dataType="Require"  msg="未选择标签类型"  >
   <option value="">--请选择--</option>
  <option value="论坛">论坛</option>
  <option value="博文">博文</option>
  <option value="兴趣圈子">兴趣圈子</option>
  </select>
  </td>
  </tr>
  <tr>
  <td colspan="2" class="toolbar">
			<input type="button" class="inputButton" value="确定" onclick="SaveLtbq();"/> &nbsp;&nbsp; 
			<input type="button" class="inputButton" value="取消" onclick="history.back()" />
  </td>
  </tr>
  </table>
  </form>
  </div>
<!--    <form action="UpdateLtbqDel" method="post"> -->
<!--   <table> -->
<!--   <tr> -->
<!--   <td>删除标签名称:</td> -->
<!--   <td><input type="text" name="ltbqName"/></td> -->
<!--   </tr> -->
<!--   <tr> -->
  
<!--   <td><input type="submit" value="删除标签"/></td> -->
<!--   </tr> -->
  
<!--   </table> -->
<!--   </form> -->
  </body>
  
</html>
