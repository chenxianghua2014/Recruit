<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
</body>
<script type="text/javascript">
	$(document).ready(
			function() {
				if ("${uri}" == "Xxtz")
					alert("发送成功!");
				else if ("${uri}" == "ApmsError") {
					alert("该简历已完成终面!");
					window.location.href = "Shdjl";
					return;
				} else if ("${uri}" == "ApmsError_Scdjl") {
					alert("该简历已完成终面!");
					window.location.href = "Scdjl";
					return;
				} else if ("${uri}" == "ApmsErrorNotEnd") {
					alert("上一轮面试还未完成,无法执行本次通知面试!");
					window.location.href = "Shdjl";
					return;
				} else if ("${uri}" == "ApmsErrorNotEnd_Scdjl") {
					alert("上一轮面试还未完成,无法执行本次通知面试!");
					window.location.href = "Scdjl";
					return;
				} else if ("${uri}" == "registerUser") {
					alert("恭喜您！注册成功");
					window.location.href = "Main";
					return;
				} else if ("${uri}" == "updRegisterUser") {
					alert("恭喜您！信息已成功修改");
					window.location.href = "Main";
					return;
				} else if ("${uri}" == "qtNewArticleSave") {
					alert("恭喜您！发表成功，请您耐心等待审核。");
					window.location.href = "rmbw";
					return;
				} else if ("${uri}" == "qtNewXqqzSave") {
					alert("恭喜您！发表成功，请您耐心等待审核。");
					window.location.href = "qtXqqz";
					return;
				} else if ("${uri}" == "cantBm") {
					alert("您还没有登陆，请您先登陆，在进行报名");
					window.location.href = "Main";
					return;
				} else if ("${uri}" == "checkUserIsCZ") {
					alert("您填写的信息不正确，请重新填写！");
					window.location.href = "zhmm";
					return;
				} else if ("${uri}" == "scsjJkgl") {
					alert("试题生成成功！");
					window.location.href = "Jkgl";
					return;
				} else if ("${uri}" == "successCzUserUpdPD") {
					alert("恭喜您，密码找回成功！");
					window.location.href = "Main";
					return;
				} else if ("${uri}" == "jkglSCwb") {
					alert("此卷库已经生成试卷，请勿重新生成！");
					window.location.href = "Jkgl";
					return;
				} else if ("${uri}" == "updZzjg") {
					alert("修改成功！");
					window.location.href = "getZzjg";
					return;
				} else if ("${uri}" == "updZzjgEjmm") {
					alert("密码修改成功！");
					window.location.href = "updEJMM";
					return;
				} else if ("${uri}" == "importTkglFxt") {
					alert("导入成功！");
					window.location.href = "TkglFxt";
					return;
				} else if ("${uri}" == "importTkgl") {
					alert("导入成功！");
					window.location.href = "Tkgl";
					return;
				} else if ("${uri}" == "importTkglThree") {
					alert("导入成功！");
					window.location.href = "Tkgl";
					return;
				} else if ("${uri}" == "importTkglOne") {
					alert("导入成功！");
					window.location.href = "Tkgl";
					return;
				} else if ("${uri}" == "importTkglZero") {
					alert("导入成功！");
					window.location.href = "Tkgl";
					return;
				} else if ("${uri}" == "Scdjl_Apzw") {
					alert("安排职位成功！");
					window.location.href = "Scdjl";
					return;
				} else if ("${uri}" == "againRegisterUser") {
					alert("您填写的身份证号或者登录名重复不能注册");
					window.location.href = "register";
					return;
				} else if ("${uri}" == "Apms")
					alert("通知成功!");
				else if ("${uri}" == "Xdjl" || "${uri}" == "Ytzcpjl"
						|| "${uri}" == "Yapmsjl" || "${uri}" == "Shdjl"
						|| "${uri}" == "Scdjl")
					alert("操作成功!");
				else if ("${uri}" == "XcxxExist") {
					alert("您发布的信息已经存在！");
					window.location.href = "Xcxx";
					return;
				}
				else if ("${uri}" == "orgError") {
					alert("您没有权限执行此操作！");
					window.location.href = "MbglXxtz";
					return;
				}
				else
					alert("保存成功!");
				window.location.href = "${uri}";
			});
</script>
</html>
