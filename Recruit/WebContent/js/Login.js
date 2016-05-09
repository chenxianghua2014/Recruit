document.onkeydown = function(event) {
	e = event ? event : (window.event ? window.event : null);
	if (e.keyCode == 13) {
		onSubmitLogin();
	}
};
function RefreshCode() {
	var ImgCodeInst = document.getElementById("randcode");
	var dt = new Date();
	ImgCodeInst.src = "image.jsp?" + dt.getMilliseconds();
}

// 登录功能提交
function onSubmitLogin() {
	if ($("#txtUserName").val() == "") {
		alert("请输入用户名!");
		$("#txtUserName").focus();
		return;
	}
	if ($("#txtPassword").val() == "") {
		alert("请输入用户密码!");
		$("#txtPassword").focus();
		return;
	}
	if (document.FormMain.rand.value == "") {
		alert("请输入验证码!");
		document.FormMain.rand.focus();
		return;
	}
	var randjsp = $.ajax({
		url : "rand.jsp?anticache=" + Math.floor(Math.random() * 100000),
		async : false
	}).responseText;

	if (document.FormMain.rand.value.toLowerCase() != randjsp.toLowerCase()) {
		alert("验证码输入错误!");
		RefreshCode();
		document.FormMain.rand.focus();
		return;
	}
	var options = {
		type : 'post',
		url : "zzjgLogin",
		success : showResponse,
		error : showResponse,
		clearForm : false
	};
	// ajax上传表单
	$("#FormMain").ajaxSubmit(options);
}

function showResponse(responseText, statusText) {
	if (responseText.message.indexOf("成功") != -1)
//		window.location.href = "Index";
		window.location = "Jtjlk";
	else
		alert(responseText.message);
}