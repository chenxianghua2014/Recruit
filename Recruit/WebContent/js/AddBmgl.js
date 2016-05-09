function selJtjlk() {
	if ($("#bmglSfzh").val() != "") {
		$
				.ajax({
					type : 'GET',
					url : "selJtjlk",
					data : {
						idCard : $("#bmglSfzh").val()
					},
					dataType : "json",
					success : function(jtjlks) {
						var strHtml = "";
						for ( var i = 0; i <= jtjlks.length - 1; i++) {
							strHtml += "<tr>";
							strHtml += "<td>" + jtjlks[i].jtjlkName + "</td>";
							strHtml += "<td>" + jtjlks[i].jtjlkCsrq + "</td>";
							strHtml += "<td>" + jtjlks[i].jtjlkSfzh + "</td>";
							strHtml += "<td>" + jtjlks[i].jtjlkSex + "</td>";
							strHtml += "<td>" + jtjlks[i].jtjlkByxx + "</td>";
							strHtml += "<td>" + jtjlks[i].jtjlkZy + "</td>";
							strHtml += "<td>" + jtjlks[i].jtjlkXl + "</td>";
							strHtml += "<td>" + jtjlks[i].jtjlkZw + "</td>";
							strHtml += "<td>" + jtjlks[i].jtjlkGwlb + "</td>";
							strHtml += "<td>"
									+ (jtjlks[i].jtjlkApcpgwlb == null ? "未安排"
											: jtjlks[i].jtjlkApcpgwlb)
									+ "</td>";
							if (jtjlks[i].jtjlkApcpgwlb == null) {
								strHtml += "<td class='alignCenter'>不可用</td>";
							} else {
								if (jtjlks[i].jtjlkCpcj == "未完成"
										|| jtjlks[i].jtjlkCpcj == "未通知") {
									strHtml += "<td class='alignCenter'><input name='button'";
									strHtml += "		type='button' onclick=\"Xzkc('"
											+ jtjlks[i].jtjlkId
											+ "','"
											+ jtjlks[i].jtjlkUserid + "')\"";
									strHtml += "	 	class='inputButton' value='操作' />";
									strHtml += "</td>";
								}
								else {
									strHtml += "<td class='alignCenter'>已测评</td>";
								}
							}
							strHtml += "</tr>";
						}
						$("#tbList").html(strHtml);
					}
				});
	}
}
function checkSubmit() {

}
function GetKcInfo(kcId) {
	$.ajax({
		type : 'POST',
		url : "queryKcglById",
		data : {
			kcId : kcId
		},
		dataType : "json",
		success : function(ksgl) {
			$("#ksdz").html(ksgl.kcglDz);
			$("#ksrq").html(ksgl.kcglKsrq);
			$("#kssj").html(ksgl.kcglKssjStart + "-" + ksgl.kcglKssjEnd);
			$("#kcrl").html(ksgl.kcglKcrl);
			$("#ybm").html(ksgl.kcglYbms);
			$("#syrl").html(ksgl.kcglSyrl);
		}
	});
}
function loadKc() {
	$.ajax({
		type : 'POST',
		url : "queryKcgl",
		data : {},
		dataType : "json",
		success : function(ksgls) {
			$("#kcId").empty();
			if (ksgls.length > 0) {
				for ( var i = 0; i < ksgls.length; i++) {
					$("#kcId").append(
							"<option value='" + ksgls[i].kcglId + "'>"
									+ ksgls[i].kcglName + "</option>");
				}
				GetKcInfo(ksgls[0].kcglId);
			}
		}
	});
}
function Xzkc(_jtjlkId, _userId) {
	$("#jtjlkId").val(_jtjlkId);
	$("#userId").val(_userId);
	var strHtml = "<div class='editBlock'><table>"
			+ "<tr><th width='60'>考场报名:</th><td><select id='kcId' data-rule='required;' name='kcId' onchange='GetKcInfo(this.value)' style='width:200px;'></select></td></tr>"
			+ "<tr><th>考试地址:</th><td id='ksdz'></td></tr>"
			+ "<tr><th>考试日期:</th><td id='ksrq'></td></tr>"
			+ "<tr><th>考试时间:</th><td id='kssj'></td></tr>"
			+ "<tr><th>考场容量:</th><td id='kcrl'></td></tr>"
			+ "<tr><th>已报名:</th><td id='ybm'></td></tr>"
			+ "<tr><th>剩余容量:</th><td id='syrl'></td></tr>"
			+ "<tr><th></th><td><input type='button' onclick='Apbm()' class='inputButton' value='安排报名' /></td></tr></table></div>";
	$.jBox.open(strHtml, '安排面试', 350, 320, {
		buttons : {
			'关闭' : true
		}
	});
	loadKc();
}
function Apbm() {
	$.ajax({
		type : 'POST',
		url : "RegistrationByManage",
		data : {
			kcId : $("#kcId").val(),
			id : $("#jtjlkId").val(),
			userId : $("#userId").val()
		},
		dataType : "json",
		success : function(_result) {
			alert(_result.message);
			if (_result.message.indexOf("成功") != -1)
				$.jBox.close();
		}
	});
}