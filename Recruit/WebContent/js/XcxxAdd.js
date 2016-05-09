$(document).ready(function() {
	var date = new Date();
	if (fbsj == "")
		$("#xcxxFbsj").val(date.format('yyyy-MM-dd'));
	if ($("#orgId").val() != "test001") {
		$("#xcxxXclb").append("<option value=\"单位概况\">单位概况</option>");
	} else {
		$("#xcxxXclb").append("<option value=\"集团概况\">集团概况</option>");
		$("#xcxxXclb").append("<option value=\"员工发展\">员工发展</option>");
		$("#xcxxXclb").append("<option value=\"招聘指南\">招聘指南</option>");
	}
});

function bindXcnr(v) {
	if ($("#orgId").val() != "test001") {
		$("#xcxxTitle").append("<option value=\"单位简介\">单位简介</option>");
	} else {
		if (v == "")
			v = "集团概况";
		$("#xcxxTitle").empty();
		switch (v) {
		case "集团概况":
			$("#xcxxTitle").append("<option value=\"集团简介\">集团简介</option>");
			$("#xcxxTitle").append("<option value=\"组织机构\">组织机构</option>");
			$("#xcxxTitle").append("<option value=\"发展历程\">发展历程</option>");
			$("#xcxxTitle").append("<option value=\"企业文化\">企业文化</option>");
			break;
		case "组织结构":
			$("#xcxxTitle").append("<option value=\"组织结构\">组织结构</option>");
			$("#xcxxTitle").append("<option value=\"发展历程\">发展历程</option>");
			$("#xcxxTitle").append("<option value=\"发展战略\">发展战略</option>");
			break;
		case "领导关怀":
			$("#xcxxTitle").append("<option value=\"领导关怀\">领导关怀</option>");
			$("#xcxxTitle").append("<option value=\"资质荣誉\">资质荣誉</option>");
			break;
		case "招聘指南":
			$("#xcxxTitle").append("<option value=\"关于网申\">关于网申</option>");
			$("#xcxxTitle").append("<option value=\"关于约考\">关于约考</option>");
			$("#xcxxTitle").append("<option value=\"关于测评\">关于测评</option>");
			$("#xcxxTitle").append("<option value=\"关于移动招聘\">关于移动招聘</option>");
			break;
		case "员工发展":
			$("#xcxxTitle").append("<option value=\"人才战略\">人才战略</option>");
			$("#xcxxTitle").append("<option value=\"院士专家\">院士专家</option>");
			$("#xcxxTitle").append("<option value=\"员工成长\">员工成长</option>");
			$("#xcxxTitle").append("<option value=\"员工风采\">员工风采</option>");
			break;
		default:
			break;
		}
	}
}

function Yl() {
	var obj = new Object();
	obj.file = $("#movSrc").val();
	obj.image = $("#imgSrc").val();
	window
			.showModalDialog("XcxxMovPreview", obj,
					"dialogWidth:600px;dialogHeight:400px;scroll:no;status:no;resizable:no;");
}

// 日期格式化
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}