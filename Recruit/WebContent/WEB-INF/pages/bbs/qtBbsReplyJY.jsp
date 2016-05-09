<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

</head>
<body>
<input type="hidden" id="bbsId" value="${bbsId}">
</body>
<script type="text/javascript">
		alert("您已经被禁言，请联系管理员！");
		var bbsId = document.getElementById("bbsId").value;
		window.location.href = "getBbsById?bbsId="+bbsId;
</script>
</html>