<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>选择考试种类</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/cpgl/cpstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet"
	href="resources/jBox/Skins/Blue/jbox.css" />
<script type="text/javascript" src="js/KeyDown.js"></script>

<script type="text/javascript">
	function cpglKssm() {
		if (document.getElementById("bmglBkgw1").value == "管理岗"
				|| document.getElementById("bmglBkgw2").value == "管理岗"
				|| (document.getElementById("bmglBkgw1").value == "管理岗" && document
						.getElementById("bmglBkgw2").value == "技术岗")
				|| (document.getElementById("bmglBkgw1").value == "技术岗" && document
						.getElementById("bmglBkgw2").value == "管理岗")) {
			$.ajax({
				type : "POST",
				url : "checkKscjGlg",
				data : {
					ksxcglIdcard : $("#bmglSfzh").val()
				},
				datatype : "json",
				success : function(kscjGlgCount) {
					if (kscjGlgCount > 0) {
						$.ajax({
							type : "POST",
							url : "checkKscjEng",
							data : {
								ksxcglIdcard : $("#bmglSfzh").val(),
								ksxcglKkslx : "管理岗"
							},
							datatype : "json",
							success : function(kscjEng) {
								if (kscjEng > 0) {
									window.location.href = "cpglKssm";
								} else {
									alert("您已经参加过考试，不能重复进行！");
								}
							}
						});
					} else {
						window.location.href = "cpglKssm";
					}
				}
			});
		} else {
			alert("单位通知您报考的岗位是技术岗，请选择技术岗考试！");
		}
	}
	function cpglKssm1() {
		if (document.getElementById("bmglBkgw1").value == "技术岗"
				|| document.getElementById("bmglBkgw2").value == "技术岗"
				|| (document.getElementById("bmglBkgw1").value == "技术岗" && document
						.getElementById("bmglBkgw2").value == "管理岗")
				|| (document.getElementById("bmglBkgw1").value == "管理岗" && document
						.getElementById("bmglBkgw2").value == "技术岗")) {
			$.ajax({
				type : "POST",
				url : "checkKscjJsg",
				data : {
					ksxcglIdcard : $("#bmglSfzh").val()
				},
				datatype : "json",
				success : function(kscjJsgCount) {
					if (kscjJsgCount > 0) {
						$.ajax({
							type : "POST",
							url : "checkKscjEng",
							data : {
								ksxcglIdcard : $("#bmglSfzh").val(),
								ksxcglKkslx : "技术岗"
							},
							datatype : "json",
							success : function(kscjEng) {
								if (kscjEng > 0) {
									window.location.href = "cpglKssm1";
								} else {
									alert("您已经参加过考试，不能重复进行！");
								}
							}
						});
					} else {
						window.location.href = "cpglKssm1";
					}
				}
			});
		} else {
			alert("单位通知您报考的岗位是管理岗，请选择管理岗考试！");
		}
	}
	function cpglKssm2() {
		$.ajax({
			type : "POST",
			url : "checkGxcs",
			data : {
				bmglSfzh : $("#bmglSfzh").val(),
				bmglKsxm : $("#bmglKsxm").val()
			},
			datatype : "json",
			success : function(result) {
				if (result) {
					window.location.href = "cpglKssm2";
				} else {
					alert("您未参加前面的考试或者已经参加过个性测试！");
				}
			}
		});
	}
</script>
</head>

<body>
	<p>&nbsp;</p>
	<div id="top"></div>
	<div id="content">
		<input type="hidden" id="bmglBkgw1" name="bmglBkgw1"
			value="${cpglLoginSession1.bmglBkgw}" /> <input type="hidden"
			id="bmglBkgw2" name="bmglBkgw2" value="${cpglLoginSession2.bmglBkgw}" />
		<input type="hidden" id="bmglKsxm" name="bmglKsxm"
			value="${cpglLoginSession1.bmglKsxm}" /> <input type="hidden"
			id="bmglSfzh" name="bmglSfzh" value="${cpglLoginSession1.bmglSfzh}" />
		<h3>选择您要参加的考试</h3>
		<div class="manageBtn">
			<a href="javascript:void(0);" class="manbtn" onclick="cpglKssm();">进入考试</a>
		</div>
		<div class="technologyBtn">
			<a href="javascript:void(0);" class="tecbtn" onclick="cpglKssm1();">进入考试</a>
		</div>
		<div class="otherexam">
			<a href="javascript:void(0);" onclick="cpglKssm2();">补考个性测试 >></a>
		</div>
	</div>
	<%@ include file="../Main/cpglFooter.jsp"%>
</body>
</html>