//自动加载数据
$(document).ready(function() {
	LoadJyjl();
});
function LoadJyjl() {
	// 自动加载证书
	$.ajax({
		type : 'POST',
		url : "LoadZs",
		data : {},
		dataType : "json",
		success : function(zss) {
			if (zss.length > 0) {
				$("#href12").css('color', 'green');
				for (var i = 0; i < zss.length; i++) {
					adddiv12(zss[i]);
				}
			} else {
				adddiv12();
			}
		}
	});
	// 自动加载IT技能
	$.ajax({
		type : 'POST',
		url : "LoadITjn",
		data : {},
		dataType : "json",
		success : function(ITjns) {
			if (ITjns.length > 0) {
				$("#href13").css('color', 'green');
				for (var i = 0; i < ITjns.length; i++) {
					adddiv13(ITjns[i]);
				}
			} else {
				adddiv13();
			}
		}
	});
	// 自动加载教育经历 cxh
	$.ajax({
		type : 'POST',
		url : "LoadJyjl",
		data : {},
		dataType : "json",
		success : function(jyjls) {
				$("#href3").css('color', 'green');
				for (var i = 0; i < jyjls.length; i++) {
					adddiv3(jyjls[i]);
				}
			
		}
	});
	
	/*$.ajax({
		type : 'POST',
		url : "LoadJyjl",
		data : {},
		dataType : "json",
		success : function(jyjls) {
			if (jyjls.length > 0) {
				$("#href3").css('color', 'green');
				for (var i = 0; i < jyjls.length; i++) {
					adddiv3(jyjls[i]);
				}
			} else {
				adddiv3();
			}
		}
	});*/
	// 自动加载校内经历
	$.ajax({
		type : 'POST',
		url : "LoadXnjl",
		data : {},
		dataType : "json",
		success : function(xnjls) {
			if (xnjls.length > 0) {
				$("#href4").css('color', 'green');
				for (var i = 0; i < xnjls.length; i++) {
					adddiv4(xnjls[i]);

				}
			} else {
				adddiv4();
			}
		}
	});
	// 自动加载校内职务
	$.ajax({
		type : 'POST',
		url : "LoadXnzw",
		data : {},
		dataType : "json",
		success : function(xnzws) {
			if (xnzws.length > 0) {
				$("#href5").css('color', 'green');
				for (var i = 0; i < xnzws.length; i++) {
					adddiv5(xnzws[i]);
				}
			} else {
				adddiv5();
			}
		}
	});
	// 自动加载实践经历
	$.ajax({
		type : 'POST',
		url : "LoadSjjl",
		data : {},
		dataType : "json",
		success : function(sjjls) {
			if (sjjls.length > 0) {
				$("#href6").css('color', 'green');
				for (var i = 0; i < sjjls.length; i++) {
					adddiv6(sjjls[i]);
				}
			} else {
				adddiv6();
			}
		}
	});
	// 自动加载实习经历
	$.ajax({
		type : 'POST',
		url : "LoadSxjl",
		data : {},
		dataType : "json",
		success : function(sxjls) {
			if (sxjls.length > 0) {
				$("#href7").css('color', 'green');
				for (var i = 0; i < sxjls.length; i++) {
					adddiv7(sxjls[i]);
				}
			} else {
				adddiv7();
			}
		}
	});
	// 自动加载工作经历
	$.ajax({
		type : 'POST',
		url : "LoadGzjl",
		data : {},
		dataType : "json",
		success : function(gzjls) {
			if (gzjls.length > 0) {
				$("#href8").css('color', 'green');
				for (var i = 0; i < gzjls.length; i++) {
					adddiv8(gzjls[i]);
				}
			} else {
				adddiv8();
			}
		}
	});
	// 自动加载项目经验
	$.ajax({
		type : 'POST',
		url : "LoadXmjy",
		data : {},
		dataType : "json",
		success : function(xmjys) {
			if (xmjys.length > 0) {
				$("#href9").css('color', 'green');
				for (var i = 0; i < xmjys.length; i++) {
					adddiv9(xmjys[i]);
				}
			} else {
				adddiv9();
			}
		}
	});
	// 自动加载培训经历
	$.ajax({
		type : 'POST',
		url : "LoadPxjl",
		data : {},
		dataType : "json",
		success : function(pxjls) {
			if (pxjls.length > 0) {
				$("#href10").css('color', 'green');
				for (var i = 0; i < pxjls.length; i++) {
					adddiv10(pxjls[i]);
				}
			} else {
				adddiv10();
			}
		}
	});
	// 自动加载语言能力
	$.ajax({
		type : 'POST',
		url : "LoadYynl",
		data : {},
		dataType : "json",
		success : function(yynls) {
			if (yynls.length > 0) {
				$("#href11").css('color', 'green');
				for (var i = 0; i < yynls.length; i++) {
					adddiv11(yynls[i]);
				}
			} else {
				adddiv11();
			}
		}
	});
	// 自动加载其他信息
	$.ajax({
		type : 'POST',
		url : "LoadQtxx",
		data : {},
		dataType : "json",
		success : function(qtxxs) {
			if (qtxxs.length > 0) {
				$("#href14").css('color', 'green');
				for (var i = 0; i < qtxxs.length; i++) {
					adddiv14(qtxxs[i]);
				}
			} else {
				adddiv14();
			}
		}
	});
	// 自动加载附件
	$.ajax({
		type : 'POST',
		url : "LoadFj",
		data : {},
		dataType : "json",
		success : function(fjs) {
			if (fjs.length > 0) {
				$("#href15").css('color', 'green');
				for (var i = 0; i < fjs.length; i++) {
					var name = fjs[i].resumeFj;
					if (name.indexOf(".pdf") != -1) {
						adddiv16(fjs[i]);
					} else {
						adddiv15(fjs[i]);
					}
				}
			}
		}
	});

	// 自动加载自制简历
	$
			.ajax({
				type : 'POST',
				url : "LoadZzjl",
				data : {},
				dataType : "json",
				success : function(resume) {
					if (resume.resumeZzjl != null
							&& resume.resumeZzjl.length > 0) {
						var strHtml = "";
						strHtml += "<table><tbody><tr>";
						strHtml += "<th style=\"width:112px;height: 29px;text-align:right\">已上传简历</th>";
						strHtml += "<td colspan=\"3\"><a href="
								+ resume.resumeZzjl + ">查看自制简历</a></td>";
						strHtml += "<td class=\"toolbar\">"
								+ "<input type=\"button\""
								+ " class=\"inputButton\" value=\"删除\" onclick=\"deleteZzjl('"
								+ resume.resumeId + "')\" />" + "</td>";
						strHtml += "</tr></tbody></table>";

						$("#ddiv19").html(strHtml);
					}
				}
			});
}

// 隐藏/展开个人信息
function show_div1() {
	$("#_div1").toggle(500);
}

// 隐藏/展开联系方式
function show_div2() {
	$("#_div2").toggle(500);
}

// 隐藏/展开教育经历
function show_div3() {
	$("#ddiv3").toggle(500);
	$("#dddiv3").toggle(500);
}

// 隐藏/展开校内奖励
function show_div4() {
	$("#ddiv4").toggle(500);
	$("#dddiv4").toggle(500);
}

// 隐藏/展开校内职务
function show_div5() {
	$("#ddiv5").toggle(500);
	$("#dddiv5").toggle(500);
}

// 隐藏/展开实践经历
function show_div6() {
	$("#ddiv6").toggle(500);
	$("#dddiv6").toggle(500);
}

// 隐藏/展开实习经历
function show_div7() {
	$("#ddiv7").toggle(500);
	$("#dddiv7").toggle(500);
}

// 隐藏/展开工作经历
function show_div8() {
	$("#ddiv8").toggle(500);
	$("#dddiv8").toggle(500);
}

// 隐藏/展开项目经验
function show_div9() {
	$("#ddiv9").toggle(500);
	$("#dddiv9").toggle(500);
}

// 隐藏/展开培训经历
function show_div10() {
	$("#ddiv10").toggle(500);
	$("#dddiv10").toggle(500);
}

// 隐藏/展开语言能力
function show_div11() {
	$("#ddiv11").toggle(500);
	$("#dddiv11").toggle(500);
}

// 隐藏/展开证书
function show_div12() {
	$("#ddiv12").toggle(500);
	$("#_div12").toggle(500);
}

// 隐藏/展开IT技能
function show_div13() {
	$("#ddiv13").toggle(500);
	$("#_div13").toggle(500);
}

// 隐藏/展开其他信息
function show_div14() {
	$("#ddiv14").toggle(500);
	$("#dddiv14").toggle(500);
}

// 隐藏/展开附件/作品
function show_div15() {
	$("#123").toggle(500);
}

// 专业下拉框变化
function addOptions(s, j) {
	switch (s) {
	case "电气信息类":
		$("#" + j).empty();
		var soojs_text = [ "电气工程及其自动化", "自动化", "电子信息工程", "通信工程", "计算机科学与技术",
				"电子科学与技术", "生物医学工程", "电子工程与自动化", "信息工程", "软件工程", "影视艺术技术",
				"网络工程", "信息显示与光电技术", "集成电路设计与集成系统", "光电信息工程", "广播电视工程",
				"电气信息工程", "计算机软件", "电子工程与管理", "微电子制造工程", "假肢矫形工程", "数字媒体艺术",
				"医学信息工程", "信息物理工程", "医疗器械工程", "智能科学与技术", "数字媒体技术", "医学影像工程",
				"真空电子技术", "电磁场与无线技术", "电信工程及管理", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "电子信息科学类":
		$("#" + j).empty();
		var soojs_text = [ "电子信息科学与技术", "微电子学", "光信息科学与技术", "科技防卫", "信息安全",
				"信息科学技术", "光电子技术科学", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "仪器仪表类":
		$("#" + j).empty();
		var soojs_text = [ "测控技术与仪器", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "工商管理类":
		var soojs_text = [ "工商管理", "市场营销", "会计学", "财务管理", "人力资源管理", "旅游管理",
				"商品学", "审计学", "电子商务", "物流管理", "国际商务", "物业管理", "特许经营管理",
				"连锁经营管理", "资产评估", "电子商务及法律", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "管理科学与工程类":
		var soojs_text = [ "管理科学", "信息管理与信息系统", "工业工程", "工程管理", "工程造价",
				"房地产经营管理", "产品质量工程", "项目管理", "管理科学与工程", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "经济学类":
		var soojs_text = [ "经济学", "国际经济与贸易", "财政学", "金融学", "国民经济管理", "贸易经济",
				"保险", "金融工程", "税务", "信用管理", "网络经济学", "体育经济", "投资学",
				"环境资源与发展经济学", "海洋经济学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "能源动力学":
		var soojs_text = [ "热能与动力工程", "核工程与核技术", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "教育学类":
		var soojs_text = [ "教育学", "学前教育", "特殊教育", "教育技术学", "小学教育", "艺术教育",
				"人文教育", "科学教育", "言语听觉科学", "华文教育", "医学人文学系", "医用理学系", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "交通运输类":
		var soojs_text = [ "交通运输", "交通工程", "油气储运工程", "飞行技术", "航海技术", "轮机工程",
				"其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "航空航天类":
		var soojs_text = [ "飞行器设计与工程", "飞行器动力工程", "飞行器制造工程", "飞行器环境与生命保障工程",
				"其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "机械类":
		var soojs_text = [ "机械设计制造及其自动化", "材料成型及控制工程", "工业设计", "过程装备与控制工程",
				"其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "统计学类":
		var soojs_text = [ "统计学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "工程力学类":
		var soojs_text = [ "工程力学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "材料类":
		var soojs_text = [ "冶金工程", "金属材料工程", "无机非金属材料工程", "高分子材料工程", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "数学类":
		var soojs_text = [ "数学与应用数学", "信息与计算科学", "数理基础科学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "物理学类":
		var soojs_text = [ "物理学", "应用物理学", "声学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "化学类":
		var soojs_text = [ "化学", "应用化学", "化学生物学", "分子科学与工程", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "中国语言文学类":
		var soojs_text = [ "汉语言文学", "汉语言", "对外汉语", "中国少数民族语言文学", "古典文献",
				"中国语言文化", "应用语言学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "外国语言文学类":
		var soojs_text = [ "英语", "俄语", "德语", "法语", "西班牙语", "阿拉伯语", "日语", "波斯语",
				"朝鲜语", "菲律宾语", "梵语巴利语", "印度尼西亚语", "印地语", "柬埔寨语", "老挝语", "缅甸语",
				"马来语", "蒙古语", "僧伽罗语", "泰语", "乌尔都语", "希伯来语", "越南语", "豪萨语",
				"斯瓦希里语", "阿尔巴尼亚语", "保加利亚语", "波兰语", "捷克语", "罗马尼亚语", "葡萄牙语",
				"瑞典语", "土耳其语", "希腊语", "匈牙利语", "意大利语", "塞尔维亚-克罗地亚语", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "地质学类":
		var soojs_text = [ "地质学", "地球化学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "地理科学类":
		var soojs_text = [ "地理科学", "资源环境与城乡规划管理", "地理信息系统", "地球信息科学与技术", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "地球物理学类":
		var soojs_text = [ "地球物理学", "地球与空间科学", "空间科学与技术", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "大气科学类":
		var soojs_text = [ "大气科学", "应用气象学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "海洋科学类":
		var soojs_text = [ "海洋科学", "海洋技术", "海洋管理", "军事海洋学", "海洋生物资源与环境", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "力学类":
		var soojs_text = [ "理论与应用力学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "新闻传播学类":
		var soojs_text = [ "新闻学", "广播电视新闻学", "广告学", "编辑出版学", "传播学", "媒体创意",
				"其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "材料科学类":
		var soojs_text = [ "材料物理", "材料化学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "环境科学类":
		var soojs_text = [ "环境科学", "生态学", "资源环境科学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "心理学类":
		var soojs_text = [ "应用心理学", "心理学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "法学类":
		var soojs_text = [ "法学", "知识产权", "监狱学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "地矿类":
		var soojs_text = [ "采矿工程", "石油工程", "矿物加工工程", "勘查技术与工程", "资源勘查工程", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "天文学类":
		var soojs_text = [ "天文学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "艺术类":
		var soojs_text = [ "音乐学", "作曲与作曲技术理论", "音乐表演", "绘画", "雕塑", "美术学",
				"艺术设计学", "艺术设计", "舞蹈学", "舞蹈编导", "戏剧学", "表演", "导演", "戏剧影视文学",
				"戏剧影视美术设计", "摄影", "录音艺术", "动画", "播音与主持艺术", "广播电视编导", "艺术学",
				"影视学", "广播影视编导", "书法学", "照明技术", "会展艺术与技术", "音乐科技与艺术", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "历史学类":
		var soojs_text = [ "历史学", "世界历史", "考古学", "博物馆学", "民族学", "文物保护技术", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "哲学类":
		var soojs_text = [ "哲学", "逻辑学", "宗教学", "伦理学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "土建类":
		var soojs_text = [ "建筑学", "城市规划", "土木工程", "建筑环境与设备工程", "给水排水工程", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "水利类":
		var soojs_text = [ "水利水电工程", "水文与水资源工程", "港口航道与海岸工程", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "测绘类":
		var soojs_text = [ "测绘工程", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "基础医学类":
		var soojs_text = [ "组织胚胎学", "病理学系", "生理学", "病理生理学", "药理学系", "生物化学",
				"细胞与遗传医学系", "免疫学系", "病原生物学系", "神经生物学系", "法医学系", "生物医学外语",
				"生物物理学系", "医学信息学系", "解剖学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "临床医学":
		var soojs_text = [ "耳鼻喉", "临床诊断", "精神卫生", "皮肤病与性病", "康复与运动医学", "影像医学",
				"麻醉学", "全科医学", "肿瘤学", "中西医结合", "眼科", "内科", "外科", "儿科", "妇产",
				"口腔医学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "中医学类":
		var soojs_text = [ "中医学", "针灸推拿学", "蒙医学", "藏医学", "中西医临床医学", "维医学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "法医学类":
		var soojs_text = [ "法医学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "护理学类":
		var soojs_text = [ "内外科护理学", "妇科护理学", "人文护理学", "护理学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "武器类":
		var soojs_text = [ "武器系统与发射工程", "探测制导与控制技术", "弹药工程与爆炸技术",
				"特种能源工程与烟火技术", "地面武器机动工程", "信息对抗技术", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "生物科学类":
		var soojs_text = [ "生物科学", "生物技术", "生物信息学", "生物信息技术", "生物科学与生物技术",
				"动植物检疫", "生物化学与分子生物学", "医学信息学", "植物生物技术", "动物生物技术", "动物资源科学",
				"生物安全", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "化工与制药类":
		var soojs_text = [ "化学工程与工艺", "制药工程", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "生物工程类":
		var soojs_text = [ "生物工程", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "农业工程类":
		var soojs_text = [ "农业机械化及其自动化", "农业电气化与自动化", "农业建筑环境与能源工程", "农业水利工程",
				"森林工程", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "林业工程类":
		var soojs_text = [ "木材科学与工程", "林产化工", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "公安技术类":
		var soojs_text = [ "刑事科学技术", "消防工程", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "植物生产类":
		var soojs_text = [ "农学", "园艺", "植物保护", "茶学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "草业科学类":
		var soojs_text = [ "草业科学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "森林资源类":
		var soojs_text = [ "林学", "森林资源保护与游憩", "野生动物与自然保护区管理", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "环境生态类":
		var soojs_text = [ "园林", "水土保护与荒漠化防治", "农业资源与环境", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "动物生产类":
		var soojs_text = [ "动物科学", "蚕学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "动物医学类":
		var soojs_text = [ "动物医学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "水产类":
		var soojs_text = [ "水产养殖学", "海洋渔业科学与技术", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "环境与安全类":
		var soojs_text = [ "环境工程", "安全工程", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "药学类":
		var soojs_text = [ "生物合成药物化学", "天然药物化学和生药学", "药事管理与临床药学", "药剂学",
				"物理化学", "放射药学", "药物分析", "医院药学", "分子与细胞药理学", "基础药理学", "临床药理学",
				"天然药理学", "毒理学系", "合成药物化学", "中药学", "制药工程", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "海洋工程类":
		var soojs_text = [ "船舶与海洋工程", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "轻工纺织食品类":
		var soojs_text = [ "食品科学与工程", "轻化工程", "包装工程", "印刷工程", "纺织工程",
				"服装设计与工程", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "农业经济管理类":
		var soojs_text = [ "农林经济管理", "农村区域发展", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "公共管理类":
		var soojs_text = [ "行政管理", "公共事业管理", "土地资源管理", "劳动与社会保障", "公共关系学",
				"公共政策学", "城市管理", "公共管理", "文化产业管理", "会展经济与管理", "国防教育与管理",
				"航运管理", "劳动管理", "公共安全管理", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "公安学类":
		var soojs_text = [ "治安学", "侦查学", "边防管理", "火灾勘察", "禁毒学", "警犬技术",
				"经济犯罪侦查", "边防指挥", "消防指挥", "警卫学", "公安情报学", "犯罪学", "公安管理学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "图书档案学类":
		var soojs_text = [ "图书馆学", "档案学", "信息资源管理", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "生命科学":
		var soojs_text = [ "生态与进化生物学系", "微生物学与微生物工程系", "生理学与生物物理学",
				"遗传学和遗传工程系", "生物化学系", "生物科学与技术系", "生物工程系", "生物医学工程系",
				"生物信息学与生物统计学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "公共卫生学":
		var soojs_text = [ "流行病学", "劳动卫生与职业病学", "环境卫生学", "营养与食品卫生学", "少儿卫生学",
				"卫生统计与社会医学", "预防医学", "卫生微生物学", "医院管理学", "卫生化学", "卫生法与卫生监督学",
				"健康教育学", "卫生政策与管理学系", "卫生事业管理学", "社会医学", "卫生经济学", "病理学与营养学系",
				"环境医学系", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "政治学类":
		var soojs_text = [ "政治学与行政学", "国际政治", "外交学", "思想政治教育", "国际文化交流",
				"国际政治经济学", "国际事务", "国际事务与国际关系", "国际事务与欧洲关系", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "马克思主义理论类":
		var soojs_text = [ "中国革命史与中国共产党党史", "科学社会主义与国际共产主义运动", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "社会学类":
		var soojs_text = [ "社会学", "社会工作", "家政学", "人类学", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "体育学类":
		var soojs_text = [ "体育教育", "运动训练", "社会体育", "运动人体科学", "民族传统体育",
				"运用康复与健康", "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "其他":
		var soojs_text = [ "其他" ];
		$("#" + j).empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#" + j).append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	default:
		$("#" + j).empty();
		$("#" + j).append("<option value='--请选择--'>" + '--请选择--' + "</option>");
	}
};

// 籍贯省份下拉框变化
function addOptionsagain2(s) {
	switch (s) {
	case "北京市":
		$("#resumeQtzs").empty();
		var soojs_text = [ " ", "北京市", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "天津市":
		$("#resumeQtzs").empty();
		var soojs_text = [ " ", "天津市", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "上海市":
		$("#resumeQtzs").empty();
		var soojs_text = [ " ", "上海市", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "重庆市":
		$("#resumeQtzs").empty();
		var soojs_text = [ " ", "重庆市", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "安徽省":
		$("#resumeQtzs").empty();
		var soojs_text = [ " ", "合肥", "芜湖", "安庆", "马鞍山", "蚌埠", "阜阳", "铜陵",
				"滁州", "黄山", "淮南", "六安", "巢湖", "宣城", "池州", "宿州", "淮北", "毫州",
				"其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "澳门特别行政区":
		$("#resumeQtzs").empty();
		var soojs_text = [ " ", "澳门", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "福建省":
		$("#resumeQtzs").empty();
		var soojs_text = [ " ", "福州", "厦门", "泉州", "漳州", "莆田", "三明", "南平", "宁德",
				"龙岩", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "甘肃省":
		var soojs_text = [ " ", "兰州", "金昌", "嘉峪关", "酒泉", "天水", "武威", "白银",
				"张掖", "平凉", "定西", "陇南", "庆阳", "临夏", "甘南", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "广东省":
		var soojs_text = [ " ", "深圳", "广州", "惠州", "汕头", "珠海", "佛山", "中山", "东莞",
				"韶关", "江门", "湛江", "肇庆", "清远", "潮州", "河源", "揭阳", "茂名", "汕尾",
				"顺德", "梅州", "开平", "阳江", "云浮", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "广西壮族自治区":
		var soojs_text = [ " ", "南宁", "桂林", "柳州", "北海", "玉林", "梧州", "防城港",
				"钦州", "贵港", "百色", "河池", "来宾", "崇左", "贺州", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "贵州省":
		var soojs_text = [ " ", "贵阳", "遵义", "六盘水", "安顺", "铜仁", "毕节", "黔西南",
				"黔南", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "国外":
		var soojs_text = [ " ", "国外", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "海南省":
		var soojs_text = [ " ", "海口", "三亚", "洋浦经济开发区", "文昌", "琼海", "万宁", "儋州",
				"东方", "五指山", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "河北省":
		var soojs_text = [ " ", "石家庄", "廊坊", "保定", "唐山", "秦皇岛", "邯郸", "沧州",
				"张家口", "承德", "邢台", "衡水", "燕郊开发区", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "河南省":
		var soojs_text = [ " ", "郑州", "洛阳", "开封", "焦作", "南阳", "新乡", "周口", "安阳",
				"平顶山", "许昌", "信阳", "商丘", "驻马店", "漯河", "濮阳", "鹤壁", "三门峡", "济源",
				"其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "黑龙江省":
		var soojs_text = [ " ", "哈尔滨", "伊春", "绥化", "大庆", "齐齐哈尔", "牡丹江", "佳木斯",
				"鸡西", "鹤岗", "双鸭山", "黑河", "七台河", "大兴安岭", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "湖北省":
		var soojs_text = [ " ", "武汉", "宜昌", "黄石", "襄阳", "十堰", "荆州", "荆门", "孝感",
				"鄂州", "黄冈", "随州", "咸宁", "仙桃", "潜江", "天门", "神农架", "恩施", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "湖南省":
		var soojs_text = [ " ", "长沙", "株洲", "湘潭", "常德", "益阳", "郴州", "昭阳", "怀化",
				"娄底", "永州", "张家界", "湘西", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "吉林省":
		var soojs_text = [ " ", "长春", "吉林", "辽源", "通化", "四平", "松原", "延吉", "白山",
				"白城", "延边", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "江苏省":
		var soojs_text = [ " ", "南京", "苏州", "无锡", "常州", "昆山", "常熟", "扬州", "南通",
				"镇江", "徐州", "连云港", "盐城", "张家港", "江阴", "太仓", "泰州", "淮安", "宿迁",
				"丹阳", "溧阳", "泰兴", "宜兴", "靖江", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "江西省":
		var soojs_text = [ " ", "南昌", "九江", "景德镇", "萍乡", "新余", "鹰潭", "赣州",
				"吉安", "宜春", "抚州", "上饶", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "辽宁省":
		var soojs_text = [ " ", "沈阳", "大连", "鞍山", "营口", "抚顺", "锦州", "丹东",
				"葫芦岛", "本溪", "辽阳", "铁岭", "盘锦", "朝阳", "阜新", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "内蒙古自治区":
		var soojs_text = [ " ", "呼和浩特", "赤峰", "包头", "通辽", "鄂尔多斯", "巴彦淖尔", "乌海",
				"呼伦贝尔", "乌兰察布", "兴安盟", "锡林郭勒盟", "阿拉善盟", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "宁夏回族自治区":
		var soojs_text = [ " ", "银川", "吴忠", "中卫", "石嘴山", "固原", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "青海省":
		var soojs_text = [ " ", "西宁", "海东", "海西", "海北", "黄南", "海南", "果洛", "玉树",
				"其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "山东省":
		var soojs_text = [ " ", "济南", "青岛", "烟台", "潍坊", "威海", "淄博", "临沂", "济宁",
				"东营", "泰安", "日照", "德州", "菏泽", "滨州", "枣庄", "聊城", "莱芜", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "山西省":
		var soojs_text = [ " ", "太原", "运城", "大同", "临汾", "长治", "晋城", "阳泉", "朔州",
				"晋中", "忻州", "吕梁", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "陕西省":
		var soojs_text = [ " ", "西安", "咸阳", "宝鸡", "铜川", "延安", "渭南", "榆林", "汉中",
				"安康", "商洛", "杨凌", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "四川省":
		var soojs_text = [ " ", "成都", "锦阳", "乐山", "泸州", "德阳", "宜宾", "自贡", "内江",
				"攀枝花", "南充", "眉山", "广安", "资阳", "遂宁", "广元", "达州", "雅安", "西昌",
				"巴中", "甘孜", "阿坝", "凉山", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "台湾省":
		var soojs_text = [ " ", "台湾", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "西藏自治区":
		var soojs_text = [ " ", "拉萨", "日喀则", "林芝", "山南", "昌都", "那曲", "阿里", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "香港特别行政区":
		var soojs_text = [ " ", "香港", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "新疆维吾尔自治区":
		var soojs_text = [ " ", "乌鲁木齐", "克拉玛依", "喀什地区", "伊犁", "阿克苏", "哈密",
				"石河子", "阿拉尔", "五家渠", "图木舒克", "昌吉", "阿勒泰", "吐鲁番", "塔城", "和田",
				"克孜勒苏柯尔克孜", "巴音敦楞", "博尔塔拉", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "云南省":
		var soojs_text = [ " ", "昆明", "曲靖", "玉溪", "大理", "丽江", "红河州", "普洱",
				"保山", "昭通", "文山", "西双版纳", "德宏", "楚雄", "临沧", "怒江", "迪庆", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "浙江省":
		var soojs_text = [ " ", "杭州", "宁波", "温州", "绍兴", "金华", "嘉兴", "台州", "湖州",
				"丽水", "舟山", "衢州", "义乌", "海宁", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "其他":
		var soojs_text = [ " ", "天文学", "其他" ];
		$("#resumeQtzs").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeQtzs").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	default:
		$("#resumeQtzs").empty();
		$("#resumeQtzs").append("<option value=' '>" + ' ' + "</option>");
	}
};
// 户口地省份下拉框变化
function addOptionsagain(s) {
	switch (s) {
	case "北京市":
		$("#resumeMqszcsCity").empty();
		var soojs_text = [ " ", "北京市", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "天津市":
		$("#resumeMqszcsCity").empty();
		var soojs_text = [ " ", "天津市", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "上海市":
		$("#resumeMqszcsCity").empty();
		var soojs_text = [ " ", "上海市", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "重庆市":
		$("#resumeMqszcsCity").empty();
		var soojs_text = [ " ", "重庆市", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "安徽省":
		$("#resumeMqszcsCity").empty();
		var soojs_text = [ " ", "合肥", "芜湖", "安庆", "马鞍山", "蚌埠", "阜阳", "铜陵",
		                   "滁州", "黄山", "淮南", "六安", "巢湖", "宣城", "池州", "宿州", "淮北", "毫州",
		                   "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "澳门特别行政区":
		$("#resumeMqszcsCity").empty();
		var soojs_text = [ " ", "澳门", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "福建省":
		$("#resumeMqszcsCity").empty();
		var soojs_text = [ " ", "福州", "厦门", "泉州", "漳州", "莆田", "三明", "南平", "宁德",
		                   "龙岩", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "甘肃省":
		var soojs_text = [ " ", "兰州", "金昌", "嘉峪关", "酒泉", "天水", "武威", "白银",
		                   "张掖", "平凉", "定西", "陇南", "庆阳", "临夏", "甘南", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "广东省":
		var soojs_text = [ " ", "深圳", "广州", "惠州", "汕头", "珠海", "佛山", "中山", "东莞",
		                   "韶关", "江门", "湛江", "肇庆", "清远", "潮州", "河源", "揭阳", "茂名", "汕尾",
		                   "顺德", "梅州", "开平", "阳江", "云浮", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "广西壮族自治区":
		var soojs_text = [ " ", "南宁", "桂林", "柳州", "北海", "玉林", "梧州", "防城港",
		                   "钦州", "贵港", "百色", "河池", "来宾", "崇左", "贺州", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "贵州省":
		var soojs_text = [ " ", "贵阳", "遵义", "六盘水", "安顺", "铜仁", "毕节", "黔西南",
		                   "黔南", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "国外":
		var soojs_text = [ " ", "国外", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "海南省":
		var soojs_text = [ " ", "海口", "三亚", "洋浦经济开发区", "文昌", "琼海", "万宁", "儋州",
		                   "东方", "五指山", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "河北省":
		var soojs_text = [ " ", "石家庄", "廊坊", "保定", "唐山", "秦皇岛", "邯郸", "沧州",
		                   "张家口", "承德", "邢台", "衡水", "燕郊开发区", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "河南省":
		var soojs_text = [ " ", "郑州", "洛阳", "开封", "焦作", "南阳", "新乡", "周口", "安阳",
		                   "平顶山", "许昌", "信阳", "商丘", "驻马店", "漯河", "濮阳", "鹤壁", "三门峡", "济源",
		                   "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "黑龙江省":
		var soojs_text = [ " ", "哈尔滨", "伊春", "绥化", "大庆", "齐齐哈尔", "牡丹江", "佳木斯",
		                   "鸡西", "鹤岗", "双鸭山", "黑河", "七台河", "大兴安岭", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "湖北省":
		var soojs_text = [ " ", "武汉", "宜昌", "黄石", "襄阳", "十堰", "荆州", "荆门", "孝感",
		                   "鄂州", "黄冈", "随州", "咸宁", "仙桃", "潜江", "天门", "神农架", "恩施", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "湖南省":
		var soojs_text = [ " ", "长沙", "株洲", "湘潭", "常德", "益阳", "郴州", "昭阳", "怀化",
		                   "娄底", "永州", "张家界", "湘西", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "吉林省":
		var soojs_text = [ " ", "长春", "吉林", "辽源", "通化", "四平", "松原", "延吉", "白山",
		                   "白城", "延边", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "江苏省":
		var soojs_text = [ " ", "南京", "苏州", "无锡", "常州", "昆山", "常熟", "扬州", "南通",
		                   "镇江", "徐州", "连云港", "盐城", "张家港", "江阴", "太仓", "泰州", "淮安", "宿迁",
		                   "丹阳", "溧阳", "泰兴", "宜兴", "靖江", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "江西省":
		var soojs_text = [ " ", "南昌", "九江", "景德镇", "萍乡", "新余", "鹰潭", "赣州",
		                   "吉安", "宜春", "抚州", "上饶", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "辽宁省":
		var soojs_text = [ " ", "沈阳", "大连", "鞍山", "营口", "抚顺", "锦州", "丹东",
		                   "葫芦岛", "本溪", "辽阳", "铁岭", "盘锦", "朝阳", "阜新", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "内蒙古自治区":
		var soojs_text = [ " ", "呼和浩特", "赤峰", "包头", "通辽", "鄂尔多斯", "巴彦淖尔", "乌海",
		                   "呼伦贝尔", "乌兰察布", "兴安盟", "锡林郭勒盟", "阿拉善盟", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "宁夏回族自治区":
		var soojs_text = [ " ", "银川", "吴忠", "中卫", "石嘴山", "固原", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "青海省":
		var soojs_text = [ " ", "西宁", "海东", "海西", "海北", "黄南", "海南", "果洛", "玉树",
		                   "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "山东省":
		var soojs_text = [ " ", "济南", "青岛", "烟台", "潍坊", "威海", "淄博", "临沂", "济宁",
		                   "东营", "泰安", "日照", "德州", "菏泽", "滨州", "枣庄", "聊城", "莱芜", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "山西省":
		var soojs_text = [ " ", "太原", "运城", "大同", "临汾", "长治", "晋城", "阳泉", "朔州",
		                   "晋中", "忻州", "吕梁", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "陕西省":
		var soojs_text = [ " ", "西安", "咸阳", "宝鸡", "铜川", "延安", "渭南", "榆林", "汉中",
		                   "安康", "商洛", "杨凌", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "四川省":
		var soojs_text = [ " ", "成都", "锦阳", "乐山", "泸州", "德阳", "宜宾", "自贡", "内江",
		                   "攀枝花", "南充", "眉山", "广安", "资阳", "遂宁", "广元", "达州", "雅安", "西昌",
		                   "巴中", "甘孜", "阿坝", "凉山", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "台湾省":
		var soojs_text = [ " ", "台湾", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "西藏自治区":
		var soojs_text = [ " ", "拉萨", "日喀则", "林芝", "山南", "昌都", "那曲", "阿里", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "香港特别行政区":
		var soojs_text = [ " ", "香港", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "新疆维吾尔自治区":
		var soojs_text = [ " ", "乌鲁木齐", "克拉玛依", "喀什地区", "伊犁", "阿克苏", "哈密",
		                   "石河子", "阿拉尔", "五家渠", "图木舒克", "昌吉", "阿勒泰", "吐鲁番", "塔城", "和田",
		                   "克孜勒苏柯尔克孜", "巴音敦楞", "博尔塔拉", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "云南省":
		var soojs_text = [ " ", "昆明", "曲靖", "玉溪", "大理", "丽江", "红河州", "普洱",
		                   "保山", "昭通", "文山", "西双版纳", "德宏", "楚雄", "临沧", "怒江", "迪庆", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "浙江省":
		var soojs_text = [ " ", "杭州", "宁波", "温州", "绍兴", "金华", "嘉兴", "台州", "湖州",
		                   "丽水", "舟山", "衢州", "义乌", "海宁", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	case "其他":
		var soojs_text = [ " ", "天文学", "其他" ];
		$("#resumeMqszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeMqszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
					+ "</option>");
		}
		break;
	default:
		$("#resumeMqszcsCity").empty();
	$("#resumeMqszcsCity").append("<option value=' '>" + ' ' + "</option>");
	}
};


// 现居地省份下拉框变化
function addOptionsagainandagain(s) {
	switch (s) {
	case "北京市":
		$("#resumeRxqhkszcsCity").empty();
		var soojs_text = [ " ", "北京市", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "天津市":
		$("#resumeRxqhkszcsCity").empty();
		var soojs_text = [ " ", "天津市", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "上海市":
		$("#resumeRxqhkszcsCity").empty();
		var soojs_text = [ " ", "上海市", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "重庆市":
		$("#resumeRxqhkszcsCity").empty();
		var soojs_text = [ " ", "重庆市", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "安徽省":
		$("#resumeRxqhkszcsCity").empty();
		var soojs_text = [ " ", "合肥", "芜湖", "安庆", "马鞍山", "蚌埠", "阜阳", "铜陵",
				"滁州", "黄山", "淮南", "六安", "巢湖", "宣城", "池州", "宿州", "淮北", "毫州",
				"其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "澳门特别行政区":
		$("#resumeRxqhkszcsCity").empty();
		var soojs_text = [ " ", "澳门", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "福建省":
		$("#resumeRxqhkszcsCity").empty();
		var soojs_text = [ " ", "福州", "厦门", "泉州", "漳州", "莆田", "三明", "南平", "宁德",
				"龙岩", "其他" ];
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "甘肃省":
		var soojs_text = [ " ", "兰州", "金昌", "嘉峪关", "酒泉", "天水", "武威", "白银",
				"张掖", "平凉", "定西", "陇南", "庆阳", "临夏", "甘南", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "广东省":
		var soojs_text = [ " ", "深圳", "广州", "惠州", "汕头", "珠海", "佛山", "中山", "东莞",
				"韶关", "江门", "湛江", "肇庆", "清远", "潮州", "河源", "揭阳", "茂名", "汕尾",
				"顺德", "梅州", "开平", "阳江", "云浮", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "广西壮族自治区":
		var soojs_text = [ " ", "南宁", "桂林", "柳州", "北海", "玉林", "梧州", "防城港",
				"钦州", "贵港", "百色", "河池", "来宾", "崇左", "贺州", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "贵州省":
		var soojs_text = [ " ", "贵阳", "遵义", "六盘水", "安顺", "铜仁", "毕节", "黔西南",
				"黔南", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "国外":
		var soojs_text = [ " ", "国外", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "海南省":
		var soojs_text = [ " ", "海口", "三亚", "洋浦经济开发区", "文昌", "琼海", "万宁", "儋州",
				"东方", "五指山", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "河北省":
		var soojs_text = [ " ", "石家庄", "廊坊", "保定", "唐山", "秦皇岛", "邯郸", "沧州",
				"张家口", "承德", "邢台", "衡水", "燕郊开发区", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "河南省":
		var soojs_text = [ " ", "郑州", "洛阳", "开封", "焦作", "南阳", "新乡", "周口", "安阳",
				"平顶山", "许昌", "信阳", "商丘", "驻马店", "漯河", "濮阳", "鹤壁", "三门峡", "济源",
				"其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "黑龙江省":
		var soojs_text = [ " ", "哈尔滨", "伊春", "绥化", "大庆", "齐齐哈尔", "牡丹江", "佳木斯",
				"鸡西", "鹤岗", "双鸭山", "黑河", "七台河", "大兴安岭", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "湖北省":
		var soojs_text = [ " ", "武汉", "宜昌", "黄石", "襄阳", "十堰", "荆州", "荆门", "孝感",
				"鄂州", "黄冈", "随州", "咸宁", "仙桃", "潜江", "天门", "神农架", "恩施", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "湖南省":
		var soojs_text = [ " ", "长沙", "株洲", "湘潭", "常德", "益阳", "郴州", "昭阳", "怀化",
				"娄底", "永州", "张家界", "湘西", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "吉林省":
		var soojs_text = [ " ", "长春", "吉林", "辽源", "通化", "四平", "松原", "延吉", "白山",
				"白城", "延边", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "江苏省":
		var soojs_text = [ " ", "南京", "苏州", "无锡", "常州", "昆山", "常熟", "扬州", "南通",
				"镇江", "徐州", "连云港", "盐城", "张家港", "江阴", "太仓", "泰州", "淮安", "宿迁",
				"丹阳", "溧阳", "泰兴", "宜兴", "靖江", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "江西省":
		var soojs_text = [ " ", "南昌", "九江", "景德镇", "萍乡", "新余", "鹰潭", "赣州",
				"吉安", "宜春", "抚州", "上饶", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "辽宁省":
		var soojs_text = [ " ", "沈阳", "大连", "鞍山", "营口", "抚顺", "锦州", "丹东",
				"葫芦岛", "本溪", "辽阳", "铁岭", "盘锦", "朝阳", "阜新", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "内蒙古自治区":
		var soojs_text = [ " ", "呼和浩特", "赤峰", "包头", "通辽", "鄂尔多斯", "巴彦淖尔", "乌海",
				"呼伦贝尔", "乌兰察布", "兴安盟", "锡林郭勒盟", "阿拉善盟", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "宁夏回族自治区":
		var soojs_text = [ " ", "银川", "吴忠", "中卫", "石嘴山", "固原", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "青海省":
		var soojs_text = [ " ", "西宁", "海东", "海西", "海北", "黄南", "海南", "果洛", "玉树",
				"其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "山东省":
		var soojs_text = [ " ", "济南", "青岛", "烟台", "潍坊", "威海", "淄博", "临沂", "济宁",
				"东营", "泰安", "日照", "德州", "菏泽", "滨州", "枣庄", "聊城", "莱芜", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "山西省":
		var soojs_text = [ " ", "太原", "运城", "大同", "临汾", "长治", "晋城", "阳泉", "朔州",
				"晋中", "忻州", "吕梁", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "陕西省":
		var soojs_text = [ " ", "西安", "咸阳", "宝鸡", "铜川", "延安", "渭南", "榆林", "汉中",
				"安康", "商洛", "杨凌", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "四川省":
		var soojs_text = [ " ", "成都", "锦阳", "乐山", "泸州", "德阳", "宜宾", "自贡", "内江",
				"攀枝花", "南充", "眉山", "广安", "资阳", "遂宁", "广元", "达州", "雅安", "西昌",
				"巴中", "甘孜", "阿坝", "凉山", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "台湾省":
		var soojs_text = [ " ", "台湾", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "西藏自治区":
		var soojs_text = [ " ", "拉萨", "日喀则", "林芝", "山南", "昌都", "那曲", "阿里", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "香港特别行政区":
		var soojs_text = [ " ", "香港", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "新疆维吾尔自治区":
		var soojs_text = [ " ", "乌鲁木齐", "克拉玛依", "喀什地区", "伊犁", "阿克苏", "哈密",
				"石河子", "阿拉尔", "五家渠", "图木舒克", "昌吉", "阿勒泰", "吐鲁番", "塔城", "和田",
				"克孜勒苏柯尔克孜", "巴音敦楞", "博尔塔拉", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "云南省":
		var soojs_text = [ " ", "昆明", "曲靖", "玉溪", "大理", "丽江", "红河州", "普洱",
				"保山", "昭通", "文山", "西双版纳", "德宏", "楚雄", "临沧", "怒江", "迪庆", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "浙江省":
		var soojs_text = [ " ", "杭州", "宁波", "温州", "绍兴", "金华", "嘉兴", "台州", "湖州",
				"丽水", "舟山", "衢州", "义乌", "海宁", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	case "其他":
		var soojs_text = [ " ", "天文学", "其他" ];
		$("#resumeRxqhkszcsCity").empty();
		for (var i = 0; i < soojs_text.length; i++) {
			$("#resumeRxqhkszcsCity").append(
					"<option value='" + soojs_text[i] + "'>" + soojs_text[i]
							+ "</option>");
		}
		break;
	default:
		$("#resumeRxqhkszcsCity").empty();
		$("#resumeRxqhkszcsCity")
				.append("<option value=''>" + '' + "</option>");
	}
};

// 继续添加div的id变量
var a = 300;
var b = 400;
var c = 500;
var d = 600;
var e = 700;
var f = 800;
var g = 900;
var q = 1000;
var i = 1100;
var j = 1200;
var k = 1300;
var l = 1400;

// 继续添加form的id变量
var a3 = 2000;
var a4 = 2500;
var a5 = 2600;
var a6 = 2700;
var a7 = 2800;
var a8 = 2900;
var a9 = 3000;
var a10 = 3100;
var a11 = 3200;
var a14 = 3300;
var a15 = 3400;
var b3 = 100;
var b4 = 100;
var b5 = 100;
var b6 = 100;
var b7 = 100;
var b8 = 100;
var b9 = 100;

var b10 = 100;
var b11 = 100;

var yynlInputId = 1000;
var yynlDeleteId = 1000;
var jyjlInputId = 1000;
var jyjlDeleteId = 1000;
var gzjlInputId = 1000;
var gzjlDeleteId = 1000;
var xnjlInputId = 1000;
var xnjlDeleteId = 1000;
var xnzwInputId = 1000;
var xnzwDeleteId = 1000;
var sjjlInputId = 1000;
var sjjlDeleteId = 1000;
var sxjlInputId = 1000;
var sxjlDeleteId = 1000;
var xmjyInputId = 1000;
var xmjyDeleteId = 1000;
var pxjlInputId = 1000;
var pxjlDeleteId = 1000;
var qtxxInputId = 1000;
var qtxxDeleteId = 1000;
var fjInputId = 1000;
var fjjlDeleteId = 1000;

// 删除教育经历
function deletediv3(h, jyjlid) {
	if (jyjlid != 'undefined') {
		if (confirm("您确定要删除该条记录吗?")) {
			$.ajax({
				type : "POST",
				url : "DeleteJyjl",
				data : {
					"resumeJyjlid" : jyjlid
				}
			});
			alert('删除成功!');
			$("#" + h).remove();
		}
	}

	if (jyjlid == 'undefined') {
		if (confirm("您确定要删除吗?")) {
			$("#" + h).remove();
		}
	}
}

// 删除校内奖励
function deletediv4(cc, xnjlid) {
	if (xnjlid != 'undefined') {
		if (confirm("您确定要删除该条记录吗?")) {
			$.ajax({
				type : "POST",
				url : "DeleteXnjl",
				data : {
					"resumeXnjlid" : xnjlid
				}
			});
			alert('删除成功!');
			$("#" + cc).remove();

		}
	}
	if (xnjlid == 'undefined') {
		if (confirm("您确定要删除吗?")) {
			$("#" + cc).remove();
		}
	}
}

// 删除校内职务
function deletediv5(dd, xnzwid) {
	if (xnzwid != 'undefined') {
		if (confirm("您确定要删除该条记录吗?")) {
			$.ajax({
				type : "POST",
				url : "DeleteXnzw",
				data : {
					"resumeXnzwid" : xnzwid
				}
			});
			alert('删除成功!');
			$("#" + dd).remove();

		}
	}
	if (xnzwid == 'undefined') {
		if (confirm("您确定要删除吗?")) {
			$("#" + dd).remove();
		}
	}
}

// 删除实践经历
function deletediv6(ee, sjjlid) {
	if (sjjlid != 'undefined') {
		if (confirm("您确定要删除该条记录吗?")) {
			$.ajax({
				type : "POST",
				url : "DeleteSjjl",
				data : {
					"resumeSjjlid" : sjjlid
				}
			});
			alert('删除成功!');
			$("#" + ee).remove();

		}
	}
	if (sjjlid == 'undefined') {
		if (confirm("您确定要删除吗?")) {
			$("#" + ee).remove();
		}
	}
}

// 删除实习经历
function deletediv7(ff, sxjlid) {
	if (sxjlid != 'undefined' && sxjlid != undefined) {
		if (confirm("您确定要删除该条记录吗?")) {
			$.ajax({
				type : "POST",
				url : "DeleteSx",
				data : {
					"resumeSxid" : sxjlid
				}
			});
			alert('删除成功!');
			$("#" + ff).remove();

		}
	}
	if (sxjlid == 'undefined' || sxjlid == undefined) {
		if (confirm("您确定要删除吗?")) {
			$("#" + ff).remove();
		}
	}
}

// 删除工作经历
function deletediv8(gg, gzjlid) {
	if (gzjlid != 'undefined') {
		if (confirm("您确定要删除该条记录吗?")) {
			$.ajax({
				type : "POST",
				url : "DeleteGzjl",
				data : {
					"resumeGzjlid" : gzjlid
				}
			});
			alert('删除成功!');
			$("#" + gg).remove();

		}
	}
	if (gzjlid == 'undefined') {
		if (confirm("您确定要删除吗?")) {
			$("#" + gg).remove();
		}
	}
}

// 删除项目经验
function deletediv9(qq, xmjyid) {
	if (xmjyid != 'undefined') {
		if (confirm("您确定要删除该条记录吗?")) {
			$.ajax({
				type : "POST",
				url : "DeleteXmjy",
				data : {
					"resumeXmjyid" : xmjyid
				}
			});
			alert('删除成功!');
			$("#" + qq).remove();
		}
	}
	if (xmjyid == 'undefined') {
		if (confirm("您确定要删除吗?")) {
			$("#" + qq).remove();
		}
	}
}

// 删除培训经历
function deletediv10(ii, pxjlid) {
	if (pxjlid != 'undefined') {
		if (confirm("您确定要删除该条记录吗?")) {
			$.ajax({
				type : "POST",
				url : "DeletePxjl",
				data : {
					"resumePxjlid" : pxjlid
				}
			});
			alert('删除成功!');
			$("#" + ii).remove();

		}
	}
	if (pxjlid == 'undefined') {
		if (confirm("您确定要删除吗?")) {
			$("#" + ii).remove();
		}
	}
}

// 删除语言能力
function deletediv11(jj, yynlid) {
	if (yynlid != 'undefined') {
		if (confirm("您确定要删除该条记录吗?")) {
			$.ajax({
				type : "POST",
				url : "DeleteYynl",
				data : {
					"resumeYynlid" : yynlid
				}
			});
			alert('删除成功!');
			$("#" + jj).remove();

		}
	}
	if (yynlid == 'undefined') {
		if (confirm("您确定要删除吗?")) {
			$("#" + jj).remove();
		}
	}
}

// 删除其他信息
function deletediv14(kk, qtxxid) {
	if (qtxxid != 'undefined') {
		if (confirm("您确定要删除该条记录吗?")) {
			$.ajax({
				type : "POST",
				url : "DeleteQtxx",
				data : {
					"resumeQtxxid" : qtxxid
				}
			});
			alert('删除成功!');
			$("#" + kk).remove();

		}
	}
	if (qtxxid == 'undefined') {
		if (confirm("您确定要删除吗?")) {
			$("#" + kk).remove();
		}
	}
}

// 删除附件/作品
function deletediv15(fjid) {
	if (fjid != 'undefined') {
		if (confirm("您确定要删除该条记录吗?")) {
			$.ajax({
				type : "POST",
				url : "DeleteFj",
				data : {
					"resumeFjid" : fjid
				}
			});
			alert('删除成功!');
			window.location.reload();
		}
	}
}

// 删除自制简历
function deleteZzjl(resumeId) {
	if (resumeId != 'undefined') {
		if (confirm("您确定要删除该条记录吗?")) {
			$.ajax({
				type : "POST",
				url : "DeleteZzjl",
				data : {
					"resumeId" : resumeId
				}
			});
			alert('删除成功!');
			window.location.reload();
		}
	}
}

var xxlb = 100;
var xxmc = 100;
var tdxx = 100;
var inputxx = 100;
// 继续添加教育经历
function bindByxx(_xxmc, _tdxx, _inputxx, sslb) {
	if ($('#' + _xxmc).val() != "其他") {
		$('#' + _inputxx).val($('#' + _xxmc).val());
		$('#' + _inputxx).hide();
	} else {
		$('#' + _inputxx).val("");
		$('#' + _inputxx).show();
	}
}

function adddiv3(jyjls) {
	var j = "resumeZy" + a;
	var h = "_div" + b;
	var aaa = "form" + a3;
	var idchange = "id" + b3;
	var zylid = "zylid" + b4;
	var _jyjlInputId = "jyjlInputId" + jyjlInputId;
	var _jyjlDeleteId = "jyjlDeleteId" + jyjlDeleteId;
	var _xxlb = "xxlb" + xxlb;
	var _xxmc = "xxmc" + xxmc;
	var _tdxx = "tdxx" + tdxx;
	var _inputxx = "inputxx" + inputxx;
	if (jyjls != undefined) {
		var jyjlid = jyjls.resumeJyjlid;
	}
	$("#ddiv3")
			.append(
					"<div id="
							+ h
							+ ">"
							+ "<form action=\"SaveJyjlagain\" id="
							+ aaa
							+ " name=\"SaveJyjl\" method=\"post\" autocomplete=\"off\" data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true}\">"
							+ "<div>"
							+ "<input type=\"hidden\" id=\"resumeId\" name=\"resumeId\"  value="
							+ $('#resumeId').val()
							+ ">"
							+ "<input type=\"hidden\" id="
							+ _jyjlInputId
							+ " name=\"resumeJyjlid\"  value='"
							+ (jyjls == undefined ? '' : jyjls.resumeJyjlid)
							+ "'/>"
							+ "</div>"
							+ "<table>"
							+ "<tbody>"
							+ "<tr>"
							+ "<th style=\"width: 112px; height: 27px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "就读时间"
							+ "</th>"
							+ "<td>"
							+ "<input type=\"text\" class=\"inputText\" style='width:125px;' name=\"resumeJdsj\" onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\" data-rule=\"开始日期: required; date;\"  value="
							+ (jyjls == undefined ? '' : jyjls.resumeJdsj)
							+ ">"
							+ "&nbsp"
							+ "到"
							+ "&nbsp"
							+ "<input type=\"text\" class=\"inputText\" style='width:125px;' name=\"resumeJdsj1\" onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\" data-rule=\"结束日期: required; date; match[gt, resumeJdsj, date]\"  value="
							+ (jyjls == undefined ? '' : jyjls.resumeJdsj1)
							+ ">"
							+ "</td>"
							
/*							+ "<th style=\"width: 112px; height: 27px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "就读时间"
							+ "</th>"
							+ "<td>"
							+ "<input type=\"text\" class=\"inputText\" style='width:125px;' name=\"resumeJdsj\" onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\" data-rule=\"开始日期: required; date;\"  value="
							+ (jyjls == undefined ? '' : jyjls.resumeJdsj)
							+ ">"
							+ "&nbsp"
							+ "到"
							+ "&nbsp"
							+ "<input type=\"text\" class=\"inputText\" style='width:125px;' name=\"resumeJdsj1\" onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\" data-rule=\"结束日期: required; date; match[gt, resumeJdsj, date]\"  value="
							+ (jyjls == undefined ? '' : jyjls.resumeJdsj1)
							+ ">"
							+ "</td>"
*/							
							
							+ "<td class=\"toolbar\" >"
							+ "<input type=\"submit\" class=\"inputButton\"  value=\"保存\"/>"
							+ "</td>"
							+ "<td class=\"toolbar\">"
							+ "<input type=\"button\" id="
							+ _jyjlDeleteId
							+ " class=\"inputButton\" value=\"删除\" onclick=\"deletediv3('"
							+ h
							+ "','"
							+ jyjlid
							+ "')\"/>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width: 112px; height: 27px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "学校名称"
							+ "</th>"
							+ "<td  id="
							+ _tdxx
							+ ">"
							+ "<select id='"
							+ _xxmc
							+ "' onchange=\"bindByxx('"
							+ _xxmc
							+ "','"
							+ _tdxx
							+ "','"
							+ _inputxx
							+ "')\" name=\"selSchool\" data-rule=\"学校名称:required\";>"
							+ "</select>&nbsp;<input type='text' id='"
							+ _inputxx
							+ "' name='resumeXxmc' data-rule=\"学校名称:required\"; value='"
							+ (jyjls == undefined ? '' : jyjls.resumeXxmc)
							+ "' style='display:none;'/>"+ "<span class=\"note\">" + "&nbsp;"
							+ "</td>"
							+ "</tr>"
							
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "专业"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<select   id="
							+ zylid
							+ "  name=\"resumeZyl\" data-rule=\"专业类别:required\"; onchange=\"bindZpzy(this.value,'"
							+ j
							+ "');qita(this.id);\">"
							+ "<option value=\"\">"
							+ "</option>"
							+ "<option value=\"数学\">"
							+ "数学"
							+ "</option>"
							+ "<option value=\"工程与技术科学基础学科\">"
							+ "工程与技术科学基础学科"
							+ "</option>"
							+ "<option value=\"测绘科学技术\">"
							+ "测绘科学技术"
							+ "</option>"
							+ "<option value=\"材料科学\">"
							+ "材料科学"
							+ "</option>"
							+ "<option value=\"矿山、冶金工程技术\">"
							+ "矿山、冶金工程技术"
							+ "</option>"
							+ "<option value=\"机械工程\">"
							+ "机械工程"
							+ "</option>"
							+ "<option value=\"动力与电气工程\">"
							+ "动力与电气工程"
							+ "</option>"
							+ "<option value=\"能源科学技术及核科学技术\">"
							+ "能源科学技术及核科学技术"
							+ "</option>"
							+ "<option value=\"电子、通信与自动控制技术\">"
							+ "电子、通信与自动控制技术"
							+ "</option>"
							+ "<option value=\"计算机科学技术\">"
							+ "计算机科学技术"
							+ "</option>"
							+ "<option value=\"纺织、食品科学技术\">"
							+ "纺织、食品科学技术"
							+ "</option>"
							+ "<option value=\"信息科学与系统科学\">"
							+ "信息科学与系统科学"
							+ "</option>"
							+ "<option value=\"土木建筑工程\">"
							+ "土木建筑工程"
							+ "</option>"
							+ "<option value=\"水利工程、交通运输工程\">"
							+ "水利工程、交通运输工程"
							+ "</option>"
							+ "<option value=\"航空、航天科学技术\">"
							+ "航空、航天科学技术"
							+ "</option>"
							+ "<option value=\"环境、安全科学技术\">"
							+ "环境、安全科学技术"
							+ "</option>"
							+ "<option value=\"管理学\">"
							+ "管理学"
							+ "</option>"
							+ "<option value=\"马克思主义、哲学及宗教学\">"
							+ "马克思主义、哲学及宗教学"
							+ "</option>"
							+ "<option value=\"语言学、文学及艺术学\">"
							+ "语言学、文学及艺术学"
							+ "</option>"
							+ "<option value=\"历史学、考古学\">"
							+ "历史学、考古学"
							+ "</option>"
							+ "<option value=\"经济学\">"
							+ "经济学"
							+ "</option>"
							+ "<option value=\"政治学、法学及军事学\">"
							+ "政治学、法学及军事学"
							+ "</option>"
							+ "<option value=\"力学\">"
							+ "力学"
							+ "</option>"
							+ "<option value=\"社会学及民族学\">"
							+ "社会学及民族学"
							+ "</option>"
							+ "<option value=\"新闻学与传播学\">"
							+ "新闻学与传播学"
							+ "</option>"
							+ "<option value=\"图书馆、情报与文献学\">"
							+ "图书馆、情报与文献学"
							+ "</option>"
							+ "<option value=\"教育学\">"
							+ "教育学"
							+ "</option>"
							+ "<option value=\"体育科学\">"
							+ "体育科学"
							+ "</option>"
							+ "<option value=\"统计学\">"
							+ "统计学"
							+ "</option>"
							+ "<option value=\"物理学\">"
							+ "物理学"
							+ "</option>"
							+ "<option value=\"化学及化学工程\">"
							+ "化学及化学工程"
							+ "</option>"
							+ "<option value=\"天文学及地球科学\">"
							+ "天文学及地球科学"
							+ "</option>"
							+ "<option value=\"生物学\">"
							+ "生物学"
							+ "</option>"
							+ "<option value=\"农、林、畜牧及水产学\">"
							+ "农、林、畜牧及水产学"
							+ "</option>"
							+ "<option value=\"医学、卫生学及药学\">"
							+ "医学、卫生学及药学"
							+ "</option>"
							+ "<option value=\"其他\">"
							+ "其他"
							+ "</option>"
							+ "</select>"
							+ "&nbsp;"
							+ "<select id="
							+ j
							+ "   name=\"resumeZy\"  onChange=\"javascript:qita(this.id);\"  data-rule=\"专业:required\";>"
							+ "</select>"
							+ "</td>"
							+ "</tr>"
							
							/*+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">学历"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<select id="
							+ idchange
							+ " name=\"resumeXl\" data-rule=\"学历:required\"; onChange=\"javascript:qita(this.id);\">"
							+ "<option value=\"\"></option>"
							+ "<option value=\"初中\">初中</option>"
							+ "<option value=\"高中\">高中</option>"
							+ "<option value=\"中技\">中技</option>"
							+ "<option value=\"中专\">中专</option>"
							+ "<option value=\"大专\">大专</option>"
							+ "<option value=\"本科\">本科</option>"
							+ "<option value=\"MBA\">MBA</option>"
							+ "<option value=\"硕士\">硕士</option>"
							+ "<option value=\"博士\">博士</option>"
							+ "<option value=\"其他\">其他</option>"
							+ "</select>"
							+ "</td>"
							+ "</tr>"*/
							
							
							
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "学历"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input  type=\"text\" name=\"resumeXl\"  maxlength=\"50\"    data-rule=\"学历:required;\" "
							+ "value =" 
							+ (jyjls == undefined ? '' : jyjls.resumeXl)   
							+ ">"
							/*+ "<span class=\"note\"><br>&nbsp;15门课程&nbsp;500字符以内</span>"*/
							+ "</td>"
							+ "</tr>"
							
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "学位"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input  type=\"text\" name=\"resumeZyms\"  maxlength=\"50\"    data-rule=\"学历:required;\" "
							+ "value =" 
							+ (jyjls == undefined ? '' : jyjls.resumeZyms)   
							+ ">"
							
							/*+ "<textarea  cols=\"58\" rows=\"4\" name=\"resumeZyms\"  maxlength=\"50\"    data-rule=\"学位:required;\" >"
							+ (jyjls == undefined ? '' : jyjls.resumeZyms)
							+ "</textarea>"*/
							/*+ "<span class=\"note\"><br>&nbsp;15门课程&nbsp;500字符以内</span>"*/
							+ "</td>"
							+ "</tr>"
							/*
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "院系排名"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\"  name=\"resumeYxpm\"  data-rule=\"院系排名:required;\" maxlength=\"20\"  value="
							+ (jyjls == undefined ? '' : jyjls.resumeYxpm)
							+ ">"
							+ "<span class=\"note\">&nbsp;请按“名次/总人数”的格式进行填写</span>"
							+ "</td>"
							+ "</tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "班级排名"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\"  name=\"resumeBjpm\"  data-rule=\"班级排名:required;\" maxlength=\"20\"  value="
							+ (jyjls == undefined ? '' : jyjls.resumeBjpm)
							+ ">"
							+ "<span class=\"note\">&nbsp;请按“名次/总人数”的格式进行填写</span>"
							+ "</td>" + "</tr>"*/
							+ "</tbody>" + "</table>"
							+ "</form>" + "</div>");
	a = a + 1;
	b = b + 1;
	a3 = a3 + 1;
	b3 = b3 + 1;
	b4 = b4 + 1;
	jyjlInputId = jyjlInputId + 1;
	jyjlDeleteId = jyjlDeleteId + 1;
	xxlb = xxlb + 1;
	xxmc = xxmc + 1;
	tdxx = tdxx + 1;
	inputxx = inputxx + 1;
	if (jyjls != undefined) {
		$("#" + zylid).val(jyjls.resumeZyl);
		bindZpzy(jyjls.resumeZyl, j);
		$("#" + j).val(jyjls.resumeZy);
		/*$("#" + idchange).val(jyjls.resumeXl);*/
		$("#" + _xxmc).val(jyjls.resumeXxmc);
	}
	LoadXxmc(_xxmc, _tdxx, _inputxx);
	$(document)
			.ready(
					function() {
						$("#" + aaa)
								// 验证成功
								.on(
										'valid.form',
										function() {
											var id = $('#resumeId').val();
											if (id == "") {
												alert("请将基本信息填写完成!");
												return;
											} else {
												$
														.ajax({
															url : 'SaveJyjlagain',
															type : 'POST',
															data : $(this)
																	.serialize(),
															success : function(
																	d) {
																$(
																		"#"
																				+ _jyjlInputId)
																		.val(
																				d.model.resume_jyjl.resumeJyjlid);
																var jyjlid = $(
																		"#"
																				+ _jyjlInputId)
																		.val();
																$(
																		"#"
																				+ _jyjlDeleteId)
																		.attr(
																				"onclick",
																				"deletediv3('"
																						+ h
																						+ "','"
																						+ jyjlid
																						+ "')");
																alert("保存成功!");
																$("#href3")
																		.css(
																				'color',
																				'green');
															}
														});
											}
										});
						if (jyjls != undefined) {
							if (jyjls.resumeJyjlid == ""
									|| jyjls.resumeJyjlid == null) {
								bindZpzy("");
								$("#" + j).val("");
							}
						}
						/*
						 * if (jyjls != undefined) { if (jyjls.resumeJyjlid == "" ||
						 * jyjls.resumeJyjlid == null) { LoadXxmc(""); $("#" +
						 * _xxmc).val(""); } }
						 */
					});
	return aaa;
}

function bindZpzy(sslb, j) {
	$.ajax({
		type : "POST",
		url : "LoadJlzy",
		data : {
			type : sslb
		},
		dataType : "json",
		async : false,
		success : function(_result) {
			$("#" + j).empty();
			$("#" + j).append("<option value=''></option>");
			for (var i = 0; i < _result.length; i++) {
				$("#" + j).append(
						"<option value='" + _result[i].zpzyName + "'>"
								+ _result[i].zpzyName + "</option>");
			}
			if (_result.length == 0)
				$("#" + j).append("<option value='其他'>其他</option>");
		}
	});
}

function LoadXxmc(_xxmc, _tdxx, _inputxx) {
	$.ajax({
		type : "POST",
		url : "LoadXxmc",
		data : {
			type : ""
		},
		dataType : "json",
		async : false,
		success : function(_result) {
			$("#" + _xxmc).empty();
			$("#" + _xxmc).append("<option value=''></option>");
			var selIndex = -1;
			for (var i = 0; i < _result.length; i++) {
				if (_result[i].xxmcName == $("#" + _inputxx).val()) {
					$("#" + _xxmc).append(
							"<option selected='selected' value='"
									+ _result[i].xxmcName + "'>"
									+ _result[i].xxmcName + "</option>");
					selIndex = i;
				} else
					$("#" + _xxmc).append(
							"<option value='" + _result[i].xxmcName + "'>"
									+ _result[i].xxmcName + "</option>");
			}
			if ($('#' + _inputxx).val() != $("#" + _xxmc).val()) {
				$('#' + _inputxx).show();
				$("#" + _xxmc).append(
						"<option selected='selected' value='其他'>其他</option>");
			}
			if (selIndex != -1) {
				$("#" + _xxmc).append("<option value='其他'>其他</option>");
				$('#' + _inputxx).hide();
			} else {
				$("#" + _xxmc).append("<option value='其他'>其他</option>");
			}
		}
	});
}

// 继续添加校内奖励
function adddiv4(xnjls) {
	var cc = "_div" + c;
	var bbb = "form" + a4;
	var _xnjlInputId = "xnjlInputId" + xnjlInputId;
	var _xnjlDeleteId = "xnjlDeleteId" + xnjlDeleteId;
	if (xnjls != undefined) {
		var xnjlid = xnjls.resumeXnjlid;
	}
	$("#ddiv4")
			.append(
					"<div id="
							+ cc
							+ ">"
							+ "<div>"
							+ "<form action=\"SaveXnjlagain\" id="
							+ bbb
							+ " method=\"post\" autocomplete=\"off\" data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true}\">"
							+ "<div>"
							+ "<input type=\"hidden\" id=\"resumeId\" name=\"resumeId\" value="
							+ $('#resumeId').val()
							+ ">"
							+ "<input type=\"hidden\" id="
							+ _xnjlInputId
							+ " name=\"resumeXnjlid\"  value='"
							+ (xnjls == undefined ? '' : xnjls.resumeXnjlid)
							+ "'/>"
							+ "</div>"
							+ "<table>"
							+ "<tbody>"
							+ "<tr>"
							+ "<th style=\"width: 112px; text-align:right\">"
							+ "奖项名称"
							+ "</th>"
							+ "<td>"
							+ "<input type=\"text\" class=\"inputText\"   id=\"resumeJxmc\" name=\"resumeJxmc\"  data-rule=\"奖项名称:required;\"  maxlength=\"50\" value="
							+ (xnjls == undefined ? '' : xnjls.resumeJxmc)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;20字符以内"
							+ "</span>"
							+ "</td>"
							+ "<td class=\"toolbar\">"
							+ "<input type=\"submit\" class=\"inputButton\"  value=\"保存\"/>"
							+ "</td>"
							+ "<td class=\"toolbar\">"
							+ "<input type=\"button\" id="
							+ _xnjlDeleteId
							+ " class=\"inputButton\" value=\"删除\" onclick=\"deletediv4('"
							+ cc
							+ "','"
							+ xnjlid
							+ "' )\"/>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "获奖时间"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeHjsj\" name=\"resumeHjsj\" onFocus=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\"   value="
							+ (xnjls == undefined ? '' : xnjls.resumeHjsj)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;日期格式:YYYY-MM-DD"
							+ "</span>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "奖励说明"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<textarea id=\"resumeJlsm\" cols=\"58\" rows=\"4\" name=\"resumeJlsm\"  maxlength=\"500\" >"
							+ (xnjls == undefined ? '' : xnjls.resumeJlsm)
							+ "</textarea>" + "<span class=\"note\">" + "<br/>"
							+ "&nbsp;500字符以内" + "</span>" + "</td>" + "</tr>"
							+ "<tbody>" + "</table>" + "</form>" + "</div>"
							+ "</div>");
	c = c + 1;
	a4 = a4 + 1;
	xnjlInputId = xnjlInputId + 1;
	xnjlDeleteId = xnjlDeleteId + 1;
	$(document)
			.ready(
					function() {
						$("#" + bbb)
								// 验证成功
								.on(
										'valid.form',
										function() {
											var id = $('#resumeId').val();
											if (id == "") {
												alert("请将基本信息填写完成!");
												return;
											} else {
												$
														.ajax({
															url : 'SaveXnjlagain',
															type : 'POST',
															data : $(this)
																	.serialize(),
															success : function(
																	d) {
																$(
																		"#"
																				+ _xnjlInputId)
																		.val(
																				d.model.resume_xnjl.resumeXnjlid);
																var xnjlid = $(
																		"#"
																				+ _xnjlInputId)
																		.val();
																$(
																		"#"
																				+ _xnjlDeleteId)
																		.attr(
																				"onclick",
																				"deletediv4('"
																						+ cc
																						+ "','"
																						+ xnjlid
																						+ "')");
																alert("保存成功!");
																$("#href4")
																		.css(
																				'color',
																				'green');

															}
														});
											}
										});
					});
}

// 继续添加校内职务
function adddiv5(xnzws) {
	var dd = "_div" + d;
	var ccc = "form" + a5;
	var _xnzwInputId = "xnzwInputId" + xnzwInputId;
	var _xnzwDeleteId = "xnzwDeleteId" + xnzwDeleteId;
	if (xnzws != undefined) {
		var xnzwid = xnzws.resumeXnzwid;
	}
	$("#ddiv5")
			.append(
					"<div id="
							+ dd
							+ ">"
							+ "<div>"
							+ "<form action=\"SaveXnzwagain\"id="
							+ ccc
							+ " method=\"post\" autocomplete=\"off\" data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true}\">"
							+ "<div>"
							+ "<input type=\"hidden\" id=\"resumeId\" name=\"resumeId\" value="
							+ $('#resumeId').val()
							+ ">"
							+ "<input type=\"hidden\" id="
							+ _xnzwInputId
							+ " name=\"resumeXnzwid\"  value='"
							+ (xnzws == undefined ? '' : xnzws.resumeXnzwid)
							+ "'/>"
							+ "</div>"
							+ "<table>"
							+ "<tbody>"
							+ "<tr>"
							+ "<th style=\"width: 112px; text-align:right\">"
							+ "校内职务名称"
							+ "</th>"
							+ "<td>"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeXnzwmc\" name=\"resumeXnzwmc\" data-rule=\"校内职务名称:required;\"  maxlength=\"20\" value="
							+ (xnzws == undefined ? '' : xnzws.resumeXnzwmc)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;20字符以内"
							+ "</span>"
							+ "</td>"
							+ "<td class=\"toolbar\" >"
							+ "<input type=\"submit\" class=\"inputButton\"  value=\"保存\"/>"
							+ "</td>"
							+ "<td class=\"toolbar\" >"
							+ "<input type=\"button\" id="
							+ _xnzwDeleteId
							+ " class=\"inputButton\" value=\"删除\" onclick=\"deletediv5('"
							+ dd
							+ "','"
							+ xnzwid
							+ "')\"/>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "任职时间"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeRzsj\" name=\"resumeRzsj\"  data-rule=\"开始日期: required; date;\" onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\"    value="
							+ (xnzws == undefined ? '' : xnzws.resumeRzsj)
							+ ">"
							+ "&nbsp"
							+ "到"
							+ "&nbsp"
							+ "<input type=\"text\" class=\"inputText\"  name=\"resumeRzsj1\" data-rule=\"结束日期: required; date; match[gt, resumeRzsj, date]\"  onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\"     value="
							+ (xnzws == undefined ? '' : xnzws.resumeRzsj1)
							+ ">"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "职责和业绩"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<textarea id=\"resumeZzhyj\" cols=\"58\" rows=\"4\" name=\"resumeZzhyj\"  maxlength=\"500\" >"
							+ (xnzws == undefined ? '' : xnzws.resumeZzhyj)
							+ "</textarea>" + "<span class=\"note\">" + "<br/>"
							+ "&nbsp;500字符以内" + "</span>" + "</td>" + "</tr>"
							+ "<tbody>" + "</table>" + "</form>" + "</div>"
							+ "</div>");
	d = d + 1;
	a5 = a5 + 1;
	xnzwInputId = xnzwInputId + 1;
	xnzwDeleteId = xnzwDeleteId + 1;
	$(document)
			.ready(
					function() {
						$("#" + ccc)
								// 验证成功
								.on(
										'valid.form',
										function() {
											var id = $('#resumeId').val();
											if (id == "") {
												alert("请将基本信息填写完成!");
												return;
											} else {
												$
														.ajax({
															url : 'SaveXnzwagain',
															type : 'POST',
															data : $(this)
																	.serialize(),
															success : function(
																	d) {
																$(
																		"#"
																				+ _xnzwInputId)
																		.val(
																				d.model.resume_xnzw.resumeXnzwid);
																var xnzwid = $(
																		"#"
																				+ _xnzwInputId)
																		.val();
																$(
																		"#"
																				+ _xnzwDeleteId)
																		.attr(
																				"onclick",
																				"deletediv5('"
																						+ dd
																						+ "','"
																						+ xnzwid
																						+ "')");
																alert("保存成功!");
																$("#href5")
																		.css(
																				'color',
																				'green');
															}
														});
											}
										});
					});
}

// 继续添加实践经历
function adddiv6(sjjls) {
	var ee = "_div" + e;
	var ddd = "form" + a6;
	var _sjjlInputId = "sjjlInputId" + sjjlInputId;
	var _sjjlDeleteId = "sjjlDeleteId" + sjjlDeleteId;
	if (sjjls != undefined) {
		var sjjlid = sjjls.resumeSjjlid;
	}
	$("#ddiv6")
			.append(
					"<div id="
							+ ee
							+ ">"
							+ "<div>"
							+ "<form action=\"SaveSjjlagain\" id="
							+ ddd
							+ "  method=\"post\" autocomplete=\"off\" data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true}\">"
							+ "<div>"
							+ "<input type=\"hidden\" id=\"resumeId\" name=\"resumeId\" value="
							+ $('#resumeId').val()
							+ ">"
							+ "<input type=\"hidden\" id="
							+ _sjjlInputId
							+ " name=\"resumeSjjlid\"  value='"
							+ (sjjls == undefined ? '' : sjjls.resumeSjjlid)
							+ "'/>"
							+ "</div>"
							+ "<table>"
							+ "<tbody>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "实践名称"
							+ "</th>"
							+ "<td>"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeSjmc\" name=\"resumeSjmc\" data-rule=\"实践名称:required;\" maxlength=\"20\" value="
							+ (sjjls == undefined ? '' : sjjls.resumeSjmc)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;20字符以内"
							+ "</span>"
							+ "</td>"
							+ "<td class=\"toolbar\" >"
							+ "<input type=\"submit\" class=\"inputButton\"  value=\"保存\"/>"
							+ "</td>"
							+ "<td class=\"toolbar\" >"
							+ "<input type=\"button\" id="
							+ _sjjlDeleteId
							+ " class=\"inputButton\" value=\"删除\" onclick=\"deletediv6('"
							+ ee
							+ "','"
							+ sjjlid
							+ "')\"/>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "实践时间"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeSjsj\" name=\"resumeSjsj\" data-rule=\"开始日期: required; date;\" onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\"      value="
							+ (sjjls == undefined ? '' : sjjls.resumeSjsj)
							+ ">"
							+ "&nbsp"
							+ "到"
							+ "&nbsp"
							+ "<input type=\"text\" class=\"inputText\"  name=\"resumeSjsj1\" data-rule=\"结束日期: required; date; match[gt, resumeSjsj, date]\"   onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\"     value="
							+ (sjjls == undefined ? '' : sjjls.resumeSjsj1)
							+ ">"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "实践描述"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<textarea id=\"resumeSjms\" cols=\"58\" rows=\"4\" name=\"resumeSjms\"  maxlength=\"500\" >"
							+ (sjjls == undefined ? '' : sjjls.resumeSjms)
							+ "</textarea>" + "<span class=\"note\">" + "<br/>"
							+ "&nbsp;500字符以内" + "</span>" + "</td>" + "</tr>"
							+ "<tbody>" + "</table>" + "</form>" + "</div>"
							+ "</div>");
	e = e + 1;
	a6 = a6 + 1;
	sjjlInputId = sjjlInputId + 1;
	sjjlDeleteId = sjjlDeleteId + 1;
	$(document)
			.ready(
					function() {
						$("#" + ddd)
								// 验证成功
								.on(
										'valid.form',
										function() {
											var id = $('#resumeId').val();
											if (id == "") {
												alert("请将基本信息填写完成!");
												return;
											} else {
												$
														.ajax({
															url : "SaveSjjlagain",
															type : 'POST',
															data : $(this)
																	.serialize(),
															success : function(
																	d) {
																$(
																		"#"
																				+ _sjjlInputId)
																		.val(
																				d.model.resume_sjjl.resumeSjjlid);
																var sjjlid = $(
																		"#"
																				+ _sjjlInputId)
																		.val();
																$(
																		"#"
																				+ _sjjlDeleteId)
																		.attr(
																				"onclick",
																				"deletediv6('"
																						+ ee
																						+ "','"
																						+ sjjlid
																						+ "')");
																alert("保存成功!");
																$("#href6")
																		.css(
																				'color',
																				'green');

															}
														});
											}
										});
					});
}

// 继续添加实习经历
function adddiv7(sxjls) {
	var ff = "_div" + f;
	var eee = "form" + a7;
	var _sxjlInputId = "sxjlInputId" + sxjlInputId;
	var _sxjlDeleteId = "sxjlDeleteId" + sxjlDeleteId;
	if (sxjls != undefined) {
		var sxjlid = sxjls.resumeSxid;
	}
	$("#ddiv7")
			.append(
					"<div id="
							+ ff
							+ ">"
							+ "<div>"
							+ "<form action=\"SaveSxjlagain\" id="
							+ eee
							+ " method=\"post\" autocomplete=\"off\" data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true}\">"
							+ "<div>"
							+ "<input type=\"hidden\" id=\"resumeId\" name=\"resumeId\" value="
							+ $('#resumeId').val()
							+ ">"
							+ "<input type=\"hidden\" id="
							+ _sxjlInputId
							+ " name=\"resumeSxid\"  value='"
							+ (sxjls == undefined ? '' : sxjls.resumeSxid)
							+ "'/>"
							+ "</div>"
							+ "<table>"
							+ "<tbody>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 27px;text-align:right\">"
							+ "实习单位"
							+ "</th>"
							+ "<td >"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeSxgs\" name=\"resumeSxgs\" data-rule=\"实习单位:required;\" maxlength=\"20\" value="
							+ (sxjls == undefined ? '' : sxjls.resumeSxgs)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;20字符以内"
							+ "</span>"
							+ "</td>"
							+ "<td class=\"toolbar\" >"
							+ "<input type=\"submit\" class=\"inputButton\" value=\"保存\"/>"
							+ "</td>"
							+ "<td class=\"toolbar\" >"
							+ "<input type=\"button\" id="
							+ _sxjlDeleteId
							+ " class=\"inputButton\" value=\"删除\" onclick=\"deletediv7('"
							+ ff
							+ "','"
							+ sxjlid
							+ "')\"/>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "实习城市"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeSxcs\" name=\"resumeSxcs\"    maxlength=\"20\" value="
							+ (sxjls == undefined ? '' : sxjls.resumeSxcs)
							+ " >"
							+ "<span class=\"note\">"
							+ "&nbsp;20字符以内"
							+ "</span>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "实习部门"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeSxbm\" name=\"resumeSxbm\"   maxlength=\"20\" value="
							+ (sxjls == undefined ? '' : sxjls.resumeSxbm)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;20字符以内"
							+ "</span>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "实习职位"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeSxzw\" name=\"resumeSxzw\"   maxlength=\"20\" value="
							+ (sxjls == undefined ? '' : sxjls.resumeSxzw)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;20字符以内"
							+ "</span>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "实习行业"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeSxhy\" name=\"resumeSxhy\"   maxlength=\"20\" value="
							+ (sxjls == undefined ? '' : sxjls.resumeSxhy)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;20字符以内"
							+ "</span>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "实习时间"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeSxsj\" name=\"resumeSxsj\" data-rule=\"开始日期: required; date;\"  onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\"       value="
							+ (sxjls == undefined ? '' : sxjls.resumeSxsj)
							+ ">"
							+ "&nbsp"
							+ "到"
							+ "&nbsp"
							+ "<input type=\"text\" class=\"inputText\"  name=\"resumeSxsj1\" data-rule=\"结束日期: required; date; match[gt, resumeSxsj, date]\"   onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\"     value="
							+ (sxjls == undefined ? '' : sxjls.resumeSxsj1)
							+ ">"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "实习描述"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<textarea id=\"resumeSxms\" cols=\"58\" rows=\"4\" name=\"resumeSxms\"  maxlength=\"500\" >"
							+ (sxjls == undefined ? '' : sxjls.resumeSxms)
							+ "</textarea>" + "<span class=\"note\">" + "<br/>"
							+ "&nbsp;500字符以内" + "</span>" + "</td>" + "</tr>"
							+ "<tbody>" + "</table>" + "</form>" + "</div>"
							+ "</div>");
	f = f + 1;
	a7 = a7 + 1;
	sxjlInputId = sxjlInputId + 1;
	sxjlDeleteId = sxjlDeleteId + 1;
	$(document).ready(
			function() {
				$("#" + eee) // 验证成功
				.on(
						'valid.form',
						function() {
							var id = $('#resumeId').val();
							if (id == "") {
								alert("请将基本信息填写完成!");
								return;
							} else {
								$.ajax({
									url : "SaveSxjlagain",
									type : 'POST',
									data : $(this).serialize(),
									success : function(d) {
										$("#" + _sxjlInputId).val(
												d.model.resume_sx.resumeSxid);
										var sxjlid = $("#" + _sxjlInputId)
												.val();
										$("#" + _sxjlDeleteId).attr(
												"onclick",
												"deletediv7('" + ff + "','"
														+ sxjlid + "')");
										alert("保存成功!");
										$("#href7").css('color', 'green');

									}
								});
							}
						});
			});
}

// 继续添加工作经历
function adddiv8(gzjls) {
	var gg = "_div" + g;
	var fff = "form" + a8;
	var bb10 = "nypgw1" + b10;
	var bb11 = "nypgw2" + b11;
	var _gzjlInputId = "gzjlInputId" + gzjlInputId;
	var _gzjlDeleteId = "gzjlDeleteId" + gzjlDeleteId;
	if (gzjls != undefined) {
		var gzjlid = gzjls.resumeGzjlid;
	}
	
	$("#ddiv8")
			.append(
					"<div id="
							+ gg
							+ ">"
							+ "<div>"
							+ "<form action=\"SaveGzjlagain\" id="
							+ fff
							+ " method=\"post\" autocomplete=\"off\" data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true}\">"
							+ "<div>"
							+ "<input type=\"hidden\" id=\"resumeId\" name=\"resumeId\" value="
							+ $('#resumeId').val()
							+ ">"
							+ "<input type=\"hidden\" id="
							+ _gzjlInputId
							+ " name=\"resumeGzjlid\"  value='"
							+ (gzjls == undefined ? '' : gzjls.resumeGzjlid)
							+ "'/>"
							+ "</div>"
							+ "<table>"
							+ "<tbody>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "工作单位"
							+ "</th>"
							+ "<td>"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeGzgs\" name=\"resumeGzgs\"  data-rule=\"工作单位:required;\" maxlength=\"50\" value="
							+ (gzjls == undefined ? '' : gzjls.resumeGzgs)
							+ ">"
							
							+ "</td>"
							+ "<td class=\"toolbar\">"
							+ "<input type=\"submit\" class=\"inputButton\" value=\"保存\"/>"
							+ "</td>"
							+ "<td class=\"toolbar\" >"
							+ "<input type=\"button\" id="
							+ _gzjlDeleteId
							+ " class=\"inputButton\" value=\"删除\" onclick=\"deletediv8('"
							+ gg
							+ "','"
							+ gzjlid
							+ "')\"/>"
							+ "</td>"
							+ "</tr>"
							
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "工作时间"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeGzsj\" name=\"resumeGzsj\" data-rule=\"开始日期:required; \"   onfocus=\"WdatePicker({dateFmt:'yyyy.MM'})\"    value="
							+ (gzjls == undefined ? '' : gzjls.resumeGzsj)
							+ ">"
							+ "&nbsp"
							+ "到"
							+ "&nbsp"
							+ "<input type=\"text\" class=\"inputText\"  name=\"resumeGzsj1\"   data-rule=\"结束日期: required;  " 
							/*+ "match[gt, resumeGzsj, date]" */
							+ "\"  onfocus=\"WdatePicker({dateFmt:'yyyy.MM'})\"     value="
							+ (gzjls == undefined ? '' : gzjls.resumeGzsj1)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;日期格式:YYYY.MM"
							+ "</span>"
							+ "</td>"
							+ "</tr>"
							
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "职务"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeGzcs\" name=\"resumeGzcs\"    maxlength=\"50\" value="
							+ (gzjls == undefined ? '' : gzjls.resumeGzcs)
							+ ">"
							/*+ "<span class=\"note\">"
							+ "&nbsp;10字符以内"
							+ "</span>"*/
							+ "</td>"
							+ "</tr>"
							
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "职称（资格）"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeGzbm\" name=\"resumeGzbm\"   maxlength=\"10\" value="
							+ (gzjls == undefined ? '' : gzjls.resumeGzbm)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;10字符以内"
							+ "</span>"
							+ "</td>"
							+ "</tr>"
							
							
							
							/*+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "工作职位"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeGzzw\" name=\"resumeGzzw\"   maxlength=\"20\" value="
							+ (gzjls == undefined ? '' : gzjls.resumeGzzw)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;20字符以内"
							+ "</span>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "工作行业"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeGzhy\" name=\"resumeGzhy\"    maxlength=\"20\" value="
							+ (gzjls == undefined ? '' : gzjls.resumeGzhy)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;20字符以内"
							+ "</span>"
							+ "</td>"
							+ "</tr>"
							*/
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "工作描述"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<textarea id=\"resumeGzms\" cols=\"58\" rows=\"4\" name=\"resumeGzms\" maxlength=\"500\" >"
							+ (gzjls == undefined ? '' : gzjls.resumeGzms)
							+ "</textarea>" + "<span class=\"note\">" + "<br/>"
							+ "&nbsp;500字符以内" + "</span>" + "</td>" + "</tr>"
							+ "<tbody>" + "</table>" + "</form>" + "</div>"
							+ "</div>");
	g = g + 1;
	a8 = a8 + 1;
	bb10++;
	bb11++;
	gzjlInputId = gzjlInputId + 1;
	gzjlDeleteId = gzjlDeleteId + 1;
	if (gzjls != undefined)
		$("#" + bb10).val(gzjls.resumeGzzw);
	if (gzjls != undefined)
		$("#" + bb11).val(gzjls.resumeGzhy);
	$(document)
			.ready(
					function() {
						$("#" + fff)
								// 验证成功
								.on(
										'valid.form',
										function() {
											var id = $('#resumeId').val();
											if (id == "") {
												alert("请将基本信息填写完成!");
												return;
											} else {
												$
														.ajax({
															url : 'SaveGzjlagain',
															type : 'POST',
															data : $(this)
																	.serialize(),
															success : function(
																	d) {
																$(
																		"#"
																				+ _gzjlInputId)
																		.val(
																				d.model.resume_gzjl.resumeGzjlid);
																var gzjlid = $(
																		"#"
																				+ _gzjlInputId)
																		.val();
																$(
																		"#"
																				+ _gzjlDeleteId)
																		.attr(
																				"onclick",
																				"deletediv8('"
																						+ gg
																						+ "','"
																						+ gzjlid
																						+ "')");
																alert("保存成功!");
																$("#href8")
																		.css(
																				'color',
																				'green');

															}
														});
											}
										});
					});
}

// 继续添加项目经验
function adddiv9(xmjys) {
	var qq = "_div" + q;
	var ggg = "form" + a9;
	var _xmjyInputId = "xmjyInputId" + xmjyInputId;
	var _xmjyDeleteId = "xmjyDeleteId" + xmjyDeleteId;
	if (xmjys != undefined) {
		var xmjyid = xmjys.resumeXmjyid;
	}
	$("#ddiv9")
			.append(
					"<div id="
							+ qq
							+ ">"
							+ "<div>"
							+ "<form action=\"SaveXmjyagain\" id="
							+ ggg
							+ " method=\"post\" autocomplete=\"off\" data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true}\">"
							+ "<div>"
							+ "<input type=\"hidden\" id=\"resumeId\" name=\"resumeId\" value="
							+ $('#resumeId').val()
							+ ">"
							+ "<input type=\"hidden\" id="
							+ _xmjyInputId
							+ " name=\"resumeXmjyid\"  value='"
							+ (xmjys == undefined ? '' : xmjys.resumeXmjyid)
							+ "'/>"
							+ "</div>"
							+ "<table>"
							+ "<tbody>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "项目名称"
							+ "</th>"
							+ "<td>"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeXmmc\" name=\"resumeXmmc\" data-rule=\"项目名称:required;\"  maxlength=\"20\" value="
							+ (xmjys == undefined ? '' : xmjys.resumeXmmc)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;20字符以内"
							+ "</span>"
							+ "</td>"
							+ "<td class=\"toolbar\">"
							+ "<input type=\"submit\" class=\"inputButton\" value=\"保存\"/>"
							+ "</td>"
							+ "<td class=\"toolbar\" >"
							+ "<input type=\"button\" id="
							+ _xmjyDeleteId
							+ " class=\"inputButton\" value=\"删除\" onclick=\"deletediv9('"
							+ qq
							+ "','"
							+ xmjyid
							+ "')\"/>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "团队规模"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeTdgm\" name=\"resumeTdgm\"   maxlength=\"20\" value="
							+ (xmjys == undefined ? '' : xmjys.resumeTdgm)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;20字符以内"
							+ "</span>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "项目简介"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<textarea id=\"resumeXmjj\" cols=\"58\" rows=\"4\" name=\"resumeXmjj\"  maxlength=\"500\" >"
							+ (xmjys == undefined ? '' : xmjys.resumeXmjj)
							+ "</textarea>"
							+ "<span class=\"note\">"
							+ "<br/>"
							+ "&nbsp;500字符以内"
							+ "</span>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "项目角色"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeXmjs\" name=\"resumeXmjs\"    maxlength=\"20\" value="
							+ (xmjys == undefined ? '' : xmjys.resumeXmjs)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;20字符以内"
							+ "</span>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "参与时间"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeCysj\" name=\"resumeCysj\" data-rule=\"开始日期: required; date;\"  onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\"     value="
							+ (xmjys == undefined ? '' : xmjys.resumeCysj)
							+ ">"
							+ "&nbsp"
							+ "到"
							+ "&nbsp"
							+ "<input type=\"text\" class=\"inputText\"  name=\"resumeCysj1\"  data-rule=\"结束日期: required; date; match[gt, resumeCysj, date]\"  onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\"     value="
							+ (xmjys == undefined ? '' : xmjys.resumeCysj1)
							+ ">"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "项目成果"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<textarea id=\"resumeXmcg\" cols=\"58\" rows=\"4\" name=\"resumeXmcg\"  maxlength=\"500\" >"
							+ (xmjys == undefined ? '' : xmjys.resumeXmcg)
							+ "</textarea>" + "<span class=\"note\">" + "<br/>"
							+ "&nbsp;500字符以内" + "</span>" + "</td>" + "</tr>"
							+ "<tbody>" + "</table>" + "</form>" + "</div>"
							+ "</div>");
	q = q + 1;
	a9 = a9 + 1;
	xmjyInputId = xmjyInputId + 1;
	xmjyDeleteId = xmjyDeleteId + 1;
	$(document)
			.ready(
					function() {
						$("#" + ggg)
								// 验证成功
								.on(
										'valid.form',
										function() {
											var id = $('#resumeId').val();
											if (id == "") {
												alert("请将基本信息填写完成!");
												return;
											} else {
												$
														.ajax({
															url : 'SaveXmjyagain',
															type : 'POST',
															data : $(this)
																	.serialize(),
															success : function(
																	d) {
																$(
																		"#"
																				+ _xmjyInputId)
																		.val(
																				d.model.resume_xmjy.resumeXmjyid);
																var xmjyid = $(
																		"#"
																				+ _xmjyInputId)
																		.val();
																$(
																		"#"
																				+ _xmjyDeleteId)
																		.attr(
																				"onclick",
																				"deletediv9('"
																						+ qq
																						+ "','"
																						+ xmjyid
																						+ "')");
																alert("保存成功!");
																$("#href9")
																		.css(
																				'color',
																				'green');

															}
														});
											}
										});
					});
};
// 继续添加培训经历 改为家庭关系 cxh
function adddiv10(pxjls) {
	var ii = "_div" + i;
	var hhh = "form" + a10;
	var _pxjlInputId = "pxjlInputId" + pxjlInputId;
	var _pxjlDeleteId = "pxjlDeleteId" + pxjlDeleteId;
	if (pxjls != undefined) {
		var pxjlid = pxjls.resumePxjlid;
	}
	$("#ddiv10")
			.append(
					"<div id="
							+ ii
							+ ">"
							+ "<div>"
							+ "<form action=\"SavePxjlagain\" id="
							+ hhh
							+ " method=\"post\" autocomplete=\"off\" data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true}\">"
							+ "<div>"
							+ "<input type=\"hidden\" id=\"resumeId\" name=\"resumeId\" value="
							+ $('#resumeId').val()
							+ ">"
							+ "<input type=\"hidden\" id="
							+ _pxjlInputId
							+ " name=\"resumePxjlid\"  value='"
							+ (pxjls == undefined ? '' : pxjls.resumePxjlid)
							+ "'/>"
							+ "</div>"
							+ "<table>"
							+ "<tbody>"
							
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "关系"
							+ "</th>"
							
							+ "<td>"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumePxmc\" name=\"resumePxmc\"  data-rule=\"关系:required;\"   maxlength=\"10\" value="
							+ (pxjls == undefined ? '' : pxjls.resumePxmc)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;10字符以内"
							+ "</span>"
							+ "</td>"
							
							+ "<td class=\"toolbar\">"
							+ "<input type=\"submit\" class=\"inputButton\"  value=\"保存\"/>"
							+ "</td>"
							+ "<td class=\"toolbar\" >"
							+ "<input type=\"button\" id="
							+ _pxjlDeleteId
							+ " class=\"inputButton\" value=\"删除\" onclick=\"deletediv10('"
							+ ii
							+ "','"
							+ pxjlid
							+ "')\"/>"
							+ "</td>"
							+ "</tr>"
							
							/*+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							
							+ "</th>"
							+ "<td colspan=\"\">"
							
							+ "<span class=\"note\">"
							+ "&nbsp;如配偶、子女、父亲、母亲、岳父（公）、岳母（婆），并按照顺序添写"
							+ "</span>"
							+ "</td>"
							+ "</tr>"*/
							
							
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "姓名"
							+ "</th>"
							+ "<td colspan=\"\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumePxjg\" name=\"resumePxjg\"    maxlength=\"10\" value="
							+ (pxjls == undefined ? '' : pxjls.resumePxjg)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;10字符以内"
							+ "</span>"
							+ "</td>"
							+ "</tr>"
							
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "出生年月"
							+ "</th>"
							+ "<td colspan=\"\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumePxsj\" name=\"resumePxsj\" data-rule=\"出生年月: required; date;\"  onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\"     value="
							+ (pxjls == undefined ? '' : pxjls.resumePxsj)
							+ ">"
							+ "</td>"
							+ "</tr>"
							
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "政治面貌"
							+ "</th>"
							+ "<td colspan=\"\">"
							+ "<input type=\"text\" class=\"inputText\"  name=\"resumePxsj1\"    data-rule=\"政治面貌: required;  maxlength=\"10\" value="
							+ (pxjls == undefined ? '' : pxjls.resumePxsj1)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;10字符以内"
							+ "</span>"
							+ "</td>"
							+ "</tr>"
							
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "工作单位及职务"
							+ "</th>"
							+ "<td colspan=\"\">"
							+ "<input type=\"text\" class=\"inputText\"  name=\"resumePxnr\"    data-rule=\"工作单位及职务: required;  maxlength=\"10\" value="
							+ (pxjls == undefined ? '' : pxjls.resumePxnr)
							+ ">"
							+ "<span class=\"note\">"
							+ "&nbsp;10字符以内"
							+ "</span>"
							+ "</td>"
							+ "</tr>"
							
							
							
							+ "<tbody>" 
							+ "</table>" 
							+ "</form>" 
							+ "</div>"
							+ "</div>");
	i = i + 1;
	a10 = a10 + 1;
	pxjlInputId = pxjlInputId + 1;
	pxjlDeleteId = pxjlDeleteId + 1;
	$(document)
			.ready(
					function() {
						$("#" + hhh)
								// 验证成功
								.on(
										'valid.form',
										function() {
											var id = $('#resumeId').val();
											if (id == "") {
												alert("请将基本信息填写完成!");
												return;
											} else {
												$
														.ajax({
															url : 'SavePxjlagain',
															type : 'POST',
															data : $(this)
																	.serialize(),
															success : function(
																	d) {
																$(
																		"#"
																				+ _pxjlInputId)
																		.val(
																				d.model.resume_pxjl.resumePxjlid);
																var pxjlid = $(
																		"#"
																				+ _pxjlInputId)
																		.val();
																$(
																		"#"
																				+ _pxjlDeleteId)
																		.attr(
																				"onclick",
																				"deletediv10('"
																						+ ii
																						+ "','"
																						+ pxjlid
																						+ "')");
																alert("保存成功!");
																$("#href10")
																		.css(
																				'color',
																				'green');

															}
														});
											}
										});
					});
};
// 继续添加证书
var _ZSdiv = 1600;
var _ZSform = 3600;
var _ZSInputId = 1000;
var _ZSDeleteId = 1000;
var _HDZS = 1000;
function adddiv12(zss) {
	if (zss != undefined) {
		var zsid = zss.resumeZsid;
	}
	var ZSdiv = "ZSdiv" + _ZSdiv;
	var ZSform = "ZSform" + _ZSform;
	var ZSInputId = "ZSInputId" + _ZSInputId;
	var ZSDeleteId = "ZSDeleteId" + _ZSDeleteId;
	var HDZS = "HDZS" + _HDZS;
	$("#ddiv12")
			.append(
					"<div id="
							+ ZSdiv
							+ ">"
							+ "<div>"
							+ "<form action=\"SaveZsagain\" id="
							+ ZSform
							+ " method=\"post\" autocomplete=\"off\" data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true}\">"
							+ "<div>"
							+ "<input type=\"hidden\" id=\"resumeId12\" name=\"resumeId\" value="
							+ $('#resumeId').val()
							+ " />"
							+ "<input type=\"hidden\" id="
							+ ZSInputId
							+ " name=\"resumeZsid\" value='"
							+ (zss == undefined ? '' : zss.resumeZsid)
							+ "' />"
							+ "</div>"
							+ "<table>"
							+ "<tbody>"
							+ "<tr>"
							+ "<th style=\"width: 112px; height: 27px; text-align: right\">"
							+ "获得证书"
							+ "</th>"
							+ "<td>"
							+ "<select id="
							+ HDZS
							+ " name=\"resumeHdzs\" data-rule=\"获得证书:required\" onChange=\"javascript:qita(this.id);\">"
							+ "<option value=\"\">"
							+ "</option>"
							+ "<option value=\"英语证书\">"
							+ "英语证书"
							+ "</option>"
							+ "<option value=\"计算机证书\">"
							+ "计算机证书"
							+ "</option>"
							+ "<option value=\"学校证书\">"
							+ "学校证书"
							+ "</option>"
							+ "<option value=\"第二外语证书\">"
							+ "第二外语证书"
							+ "</option>"
							+ "<option value=\"财务类证书\">"
							+ "财务类证书"
							+ "</option>"
							+ "<option value=\"专业资格证书\">"
							+ "专业资格证书"
							+ "</option>"
							+ "<option value=\"兼职实习证明\">"
							+ "兼职实习证明"
							+ "</option>"
							+ "<option value=\"发表论文、专利证书\">"
							+ "发表论文、专利证书"
							+ "</option>"
							+ "<option value=\"竞赛获奖证书\">"
							+ "竞赛获奖证书"
							+ "</option>"
							+ "<option value=\"其他\">"
							+ "其他证书"
							+ "</option>"
							+ "</select>"
							+ "</td>"
							+ "<td class=\"toolbar\">"
							+ "<input type=\"submit\" class=\"inputButton\" value=\"保存\" />"
							+ "</td>"
							+ "<td class=\"toolbar\"><input type=\"button\" id="
							+ ZSDeleteId
							+ " class=\"inputButton\" value=\"删除\" onclick=\"deletediv12('"
							+ ZSdiv
							+ "','"
							+ zsid
							+ "')\" />"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"text-align: right\">"
							+ "其他证书"
							+ "</th>"
							+ "<td colspan=\"3\"><input type=\"text\" class=\"inputText\" style='width: 450px' value='"
							+ (zss == undefined ? '' : zss.resumeQtzs)
							+ "' id=\"resumeQtzs\" name=\"resumeQtzs\" maxlength=\"50\" />"
							+ "<span class=\"note\">" + "&nbsp;50字符以内"
							+ "</span>" + "</td>" + "</tr>" + "<tbody>"
							+ "</table>" + "</form>" + "</div>" + "</div>");
	_ZSdiv = _ZSdiv + 1;
	_ZSform = _ZSform + 1;
	_ZSInputId = _ZSInputId + 1;
	_ZSDeleteId = _ZSDeleteId + 1;
	_HDZS = _HDZS + 1;
	if (zss != undefined)
		$("#" + HDZS).val(zss.resumeHdzs);
	$(document).ready(
			function() {
				$("#" + ZSform).on(
						'valid.form',
						function() {
							var id = $('#resumeId').val();
							if (id == "") {
								alert("请将基本信息填写完成!");
								return;
							} else {
								$.ajax({
									url : 'SaveZsagain',
									type : 'POST',
									data : $(this).serialize(),
									success : function(d) {
										$("#" + ZSInputId).val(
												d.model.resume_zs.resumeZsid);
										var zsid = $("#" + ZSInputId).val();
										$("#" + ZSDeleteId).attr(
												"onclick",
												"deletediv12('" + ZSdiv + "','"
														+ zsid + "')");
										alert("保存成功!");
										$("#href12").css('color', 'green');
									}
								});
							}
						});
			});
}

// 删除证书
function deletediv12(ZSdiv, zsid) {
	if (zsid != 'undefined') {
		if (confirm("您确定要删除该条记录吗?")) {
			$.ajax({
				type : "POST",
				url : "DeleteZs",
				data : {
					"resumeZsid" : zsid
				}
			});
			alert('删除成功!');
			$("#" + ZSdiv).remove();
		}
	}
	if (zsid == 'undefined') {
		if (confirm("您确定要删除吗?")) {
			$("#" + ZSdiv).remove();
		}
	}
}

// 继续添加IT技能
var _ITjndiv = 1500;
var _ITjnform = 3500;
var _ITjnInputId = 1000;
var _ITjnDeleteId = 1000;
var _ITjtjn = 1000;
var _ITsxjn = 1000;
function adddiv13(ITjns) {
	if (ITjns != undefined) {
		var ITjnid = ITjns.resumeITjnid;
	}
	var ITjndiv = "ITjndiv" + _ITjndiv;
	var ITjnform = "ITjnform" + _ITjnform;
	var ITjnInputId = "ITjnInputId" + _ITjnInputId;
	var ITjnDeleteId = "ITjnDeleteId" + _ITjnDeleteId;
	var ITjtjn = "ITjtjn" + _ITjtjn;
	var ITsxjn = "ITsxjn" + _ITsxjn;
	$("#ddiv13")
			.append(
					"<div id="
							+ ITjndiv
							+ ">"
							+ "<div>"
							+ "<form action=\"SaveITjnagain\" id="
							+ ITjnform
							+ " method=\"post\" autocomplete=\"off\" data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true}\">"
							+ "<div>"
							+ "<input type=\"hidden\" id=\"resumeId\" name=\"resumeId\" value="
							+ $('#resumeId').val()
							+ " />"
							+ "<input type=\"hidden\" id="
							+ ITjnInputId
							+ "  name=\"resumeITjnid\"/ value='"
							+ (ITjns == undefined ? '' : ITjns.resumeITjnid)
							+ "'>"
							+ "</div>"
							+ "<table>"
							+ "<tbody>"
							+ "<tr>"
							+ "<th style=\"width: 112px; height: 27px; text-align: right\">"
							+ "IT精通技能"
							+ "</th>"
							+ "<td>"
							+ "<select id="
							+ ITjtjn
							+ " name=\"resumeITjtjn\" data-rule=\"IT精通技能:required\" onChange=\"javascript:qita(this.id);\">"
							+ "<option value=\"\">"
							+ "</option>"
							+ "<option value=\"CAD/CAM\">"
							+ "CAD/CAM"
							+ "</option>"
							+ "<option value=\"Miscellaneous\">"
							+ "Miscellaneous"
							+ "</option>"
							+ "<option value=\"Networking\">"
							+ "Networking"
							+ "</option>"
							+ "<option value=\"New Media\">"
							+ "New Media"
							+ "</option>"
							+ "<option value=\"Project Management\">"
							+ "Project Management"
							+ "</option>"
							+ "<option value=\"Qualitsy Assurance\">"
							+ "Qualitsy Assurance"
							+ "</option>"
							+ "<option value=\"Techical Recruitsing\">"
							+ "Techical Recruitsing"
							+ "</option>"
							+ "<option value=\"Tech Sales/Marketing\">"
							+ "Tech Sales/Marketing"
							+ "</option>"
							+ "<option value=\"Technical Writser\">"
							+ "Technical Writser"
							+ "</option>"
							+ "<option value=\"Unix\">"
							+ "Unix"
							+ "</option>"
							+ "<option value=\"Windows develop\">"
							+ "Windows develop"
							+ "</option>"
							+ "<option value=\"Office Applications\">Office Applications</option>"
							+ "<option value=\"AS/400\">"
							+ "AS/400"
							+ "</option>"
							+ "<option value=\"Data Warehousing\">"
							+ "Data Warehousing"
							+ "</option>"
							+ "<option value=\"Database Systems\">"
							+ "Database Systems"
							+ "</option>"
							+ "<option value=\"E-Commerce/Internet\">"
							+ "E-Commerce/Internet"
							+ "</option>"
							+ "<option value=\"ERP\">"
							+ "ERP"
							+ "</option>"
							+ "<option value=\"Executive Level\">"
							+ "Executive Level"
							+ "</option>"
							+ "<option value=\"Hardware\">"
							+ "Hardware"
							+ "</option>"
							+ "<option value=\"Help Desk\">"
							+ "Help Desk"
							+ "</option>"
							+ "<option value=\"Legacy Systems\">"
							+ "Legacy Systems"
							+ "</option>"
							+ "<option value=\"其他\">"
							+ "其他"
							+ "</option>"
							+ "</select>"
							+ "</td>"
							+ "<td class=\"toolbar\">"
							+ "<input type=\"submit\" class=\"inputButton\" value=\"保存\" />"
							+ "</td>"
							+ "<td class=\"toolbar\">"
							+ "<input type=\"button\" id="
							+ ITjnDeleteId
							+ " class=\"inputButton\" value=\"删除\" onclick=\"deletediv13('"
							+ ITjndiv
							+ "','"
							+ ITjnid
							+ "')\" />"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"text-align: right\">"
							+ "IT熟悉技能"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<select id="
							+ ITsxjn
							+ " name=\"resumeITsxjn\" data-rule=\"IT熟悉技能:required\" onChange=\"javascript:qita(this.id);\">"
							+ "<option value=\"\">"
							+ "</option>"
							+ "<option value=\"CAD/CAM\">"
							+ "CAD/CAM"
							+ "</option>"
							+ "<option value=\"Miscellaneous\">"
							+ "Miscellaneous"
							+ "</option>"
							+ "<option value=\"Networking\">"
							+ "Networking"
							+ "</option>"
							+ "<option value=\"New Media\">"
							+ "New Media"
							+ "</option>"
							+ "<option value=\"Project Management\">"
							+ "Project Management"
							+ "</option>"
							+ "<option value=\"Qualitsy Assurance\">"
							+ "Qualitsy Assurance"
							+ "</option>"
							+ "<option value=\"Techical Recruitsing\">"
							+ "Techical Recruitsing"
							+ "</option>"
							+ "<option value=\"Tech Sales/Marketing\">"
							+ "Tech Sales/Marketing"
							+ "</option>"
							+ "<option value=\"Technical Writser\">"
							+ "Technical Writser"
							+ "</option>"
							+ "<option value=\"Unix\">Unix</option>"
							+ "<option value=\"Windows develop\">"
							+ "Windows develop"
							+ "</option>"
							+ "<option value=\"Office Applications\">"
							+ "Office Applications"
							+ "</option>"
							+ "<option value=\"AS/400\">"
							+ "AS/400"
							+ "</option>"
							+ "<option value=\"Data Warehousing\">"
							+ "Data Warehousing"
							+ "</option>"
							+ "<option value=\"Database Systems\">"
							+ "Database Systems"
							+ "</option>"
							+ "<option value=\"E-Commerce/Internet\">"
							+ "E-Commerce/Internet"
							+ "</option>"
							+ "<option value=\"ERP\">"
							+ "ERP"
							+ "</option>"
							+ "<option value=\"Executive Level\">"
							+ "Executive Level"
							+ "</option>"
							+ "<option value=\"Hardware\">"
							+ "Hardware"
							+ "</option>"
							+ "<option value=\"Help Desk\">"
							+ "Help Desk"
							+ "</option>"
							+ "<option value=\"Legacy Systems\">"
							+ "Legacy Systems"
							+ "</option>"
							+ "<option value=\"其他\">"
							+ "其他"
							+ "</option>"
							+ "</select>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"text-align: right\">"
							+ "其他技能"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" style='width: 450px' id=\"resumeQtjn\" name=\"resumeQtjn\" value='"
							+ (ITjns == undefined ? '' : ITjns.resumeQtjn)
							+ "' maxlength=\"50\" />" + "<span class=\"note\">"
							+ "&nbsp;50字符以内" + "</span>" + "</td>" + "</tr>"
							+ "<tbody>" + "</table>" + "</form>" + "</div>"
							+ "</div>");
	_ITjndiv = _ITjndiv + 1;
	_ITjnform = _ITjnform + 1;
	_ITjnInputId = _ITjnInputId + 1;
	_ITjnDeleteId = _ITjnDeleteId + 1;
	_ITjtjn = _ITjtjn + 1;
	_ITsxjn = _ITsxjn + 1;
	if (ITjns != undefined)
		$("#" + ITjtjn).val(ITjns.resumeITjtjn);
	if (ITjns != undefined)
		$("#" + ITsxjn).val(ITjns.resumeITsxjn);
	$(document)
			.ready(
					function() {
						$("#" + ITjnform)
								.on(
										'valid.form',
										function() {
											var id = $('#resumeId').val();
											if (id == "") {
												alert("请将基本信息填写完成!");
												return;
											} else {
												$
														.ajax({
															url : 'SaveITjnagain',
															type : 'POST',
															data : $(this)
																	.serialize(),
															success : function(
																	d) {
																$(
																		"#"
																				+ ITjnInputId)
																		.val(
																				d.model.resume_ITjn.resumeITjnid);
																var ITjnid = $(
																		"#"
																				+ ITjnInputId)
																		.val();
																$(
																		"#"
																				+ ITjnDeleteId)
																		.attr(
																				"onclick",
																				"deletediv13('"
																						+ ITjndiv
																						+ "','"
																						+ ITjnid
																						+ "')");
																alert("保存成功!");
																$("#href13")
																		.css(
																				'color',
																				'green');
															}
														});
											}
										});
					});
}

// 删除IT技能
function deletediv13(ITjndiv, ITjnid) {
	if (ITjnid != 'undefined') {
		if (confirm("您确定要删除该条记录吗?")) {
			$.ajax({
				type : "POST",
				url : "DeleteITjn",
				data : {
					"resumeITjnid" : ITjnid
				}
			});
			alert('删除成功!');
			$("#" + ITjndiv).remove();
		}
	}

	if (ITjnid == 'undefined') {
		if (confirm("您确定要删除吗?")) {
			$("#" + ITjndiv).remove();
		}
	}
}

// 继续添加语言能力
function adddiv11(yynls) {
	var jj = "_div" + j;
	var iii = "form" + a11;
	var bb5 = "yylbid" + b5;
	var bb6 = "tsnlid" + b6;
	var bb7 = "dxnlid" + b7;
	var bb8 = "djksid" + b8;
	var _yynlInputId = "yynlInputId" + yynlInputId;
	var _yynlDeleteId = "yynlDeleteId" + yynlDeleteId;
	if (yynls != undefined) {
		var yynlid = yynls.resumeYynlid;
	}
	$("#ddiv11")
			.append(
					"<div id="
							+ jj
							+ ">"
							+ "<div>"
							+ "<form action=\"SaveYynlagain\" id="
							+ iii
							+ " method=\"post\" autocomplete=\"off\" data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true}\">"
							+ "<div>"
							+ "<input type=\"hidden\" id=\"resumeId\" name=\"resumeId\" value="
							+ $('#resumeId').val()
							+ ">"
							+ "<input type=\"hidden\" id="
							+ _yynlInputId
							+ " name=\"resumeYynlid\"  value='"
							+ (yynls == undefined ? '' : yynls.resumeYynlid)
							+ "'/>"
							+ "</div>"
							+ "<table>"
							+ "<tbody>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "外语种类"
							+ "</th>"
							+ "<td>"
							+ "<select id="
							+ bb5
							+ " name=\"resumeYylb\" data-rule=\"外语种类:required\";  onChange=\"javascript:qita(this.id);\">"
							+ "<option value=\"\">"
							+ ""
							+ "</option>"
							+ "<option value=\"英语\">"
							+ "英语"
							+ "</option>"
							+ "<option value=\"日语\">"
							+ "日语"
							+ "</option>"
							+ "<option value=\"俄语\">"
							+ "俄语"
							+ "</option>"
							+ "<option value=\"阿拉伯语\">"
							+ "阿拉伯语"
							+ "</option>"
							+ "<option value=\"法语\">"
							+ "法语"
							+ "</option>"
							+ "<option value=\"德语\">"
							+ "德语"
							+ "</option>"
							+ "<option value=\"西班牙语\">"
							+ "西班牙语"
							+ "</option>"
							+ "<option value=\"葡萄牙语\">"
							+ "葡萄牙语"
							+ "</option>"
							+ "<option value=\"意大利语\">"
							+ "意大利语"
							+ "</option>"
							+ "<option value=\"韩语/朝鲜语\">"
							+ "韩语/朝鲜语"
							+ "</option>"
							+ "<option value=\"普通话\">"
							+ "普通话"
							+ "</option>"
							+ "<option value=\"其他\">"
							+ "其他"
							+ "</option>"
							+ "</select>"
							+ "</td>"
							+ "<td class=\"toolbar\">"
							+ "<input type=\"submit\" class=\"inputButton\" value=\"保存\"/>"
							+ "</td>"
							+ "<td class=\"toolbar\">"
							+ "<input type=\"button\" class=\"inputButton\" id="
							+ _yynlDeleteId
							+ " value=\"删除\" onclick=\"deletediv11('"
							+ jj
							+ "','"
							+ yynlid
							+ "')\"/>"
							+ "</td>"
							+ "</tr>"
							/*+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "外语水平"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<select id="
							+ bb6
							+ " name=\"resumeTsnl\" data-rule=\"外语水平:required\";>"
							+ "<option value=\"\">"
							+ ""
							+ "</option>"
							+ "<option value=\"一般\">"
							+ "一般"
							+ "</option>"
							+ "<option value=\"良好\">"
							+ "良好"
							+ "</option>"
							+ "<option value=\"熟练\">"
							+ "熟练"
							+ "</option>"
							+ "<option value=\"精通\">"
							+ "精通"
							+ "</option>"
							+ "</select>"
							+ "</td>"
							+ "</tr>"*/
							/*+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "读写能力"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<select id="
							+ bb7
							+ " name=\"resumeDxnl\" data-rule=\"读写能力:required\";>"
							+ "<option value=\"\">"
							+ ""
							+ "</option>"
							+ "<option value=\"一般\">"
							+ "一般"
							+ "</option>"
							+ "<option value=\"良好\">良好</option>"
							+ "<option value=\"熟练\">熟练</option>"
							+ "<option value=\"精通\">精通</option>"
							+ "</select>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "等级考试"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<select id="
							+ bb8
							+ " name=\"resumeDjks\" data-rule=\"等级考试:required\";>"
							+ "<option value=\"\">"
							+ ""
							+ "</option>"
							+ "<option value=\"未参加\">"
							+ "未参加"
							+ "</option>"
							+ "<option value=\"未通过\">"
							+ "未通过"
							+ "</option>"
							+ "<option value=\"英语4级\">"
							+ "英语4级"
							+ "</option>"
							+ "<option value=\"英语6级\">"
							+ "英语6级"
							+ "</option>"
							+ "<option value=\"专业4级\">"
							+ "专业4级"
							+ "</option>"
							+ "<option value=\"专业8级\">"
							+ "专业8级"
							+ "</option>"
							+ "<option value=\"TOEFL\">"
							+ "TOEFL"
							+ "</option>"
							+ "<option value=\"GRE\">"
							+ "GRE"
							+ "</option>"
							+ "<option value=\"GMAT\">"
							+ "GMAT"
							+ "</option>"
							+ "<option value=\"IELTS\">"
							+ "IELTS"
							+ "</option>"
							+ "<option value=\"TOEIC\">"
							+ "TOEIC"
							+ "</option>"
							+ "</select>"
							+ "</td>"
							+ "</tr>"*/
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "<span class=\"warning\">*</span>"
							+ "外语水平"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeYyfs\" name=\"resumeYyfs\"   data-rule=\"外语水平:required;\"  maxlength=\"20\" value="
							+ (yynls == undefined ? '' : yynls.resumeYyfs)
							+ ">" + "<span class=\"note\">" + "&nbsp;20字符以内"
							+ "</span>" + "</td>" + "</tr>" + "<tbody>"
							+ "</table>" + "</form>" + "</div>" + "</div>");
	j = j + 1;
	a11 = a11 + 1;
	b5 = b5 + 1;
	b6 = b6 + 1;
	b7 = b7 + 1;
	b8 = b8 + 1;
	yynlInputId = yynlInputId + 1;
	yynlDeleteId = yynlDeleteId + 1;
	if (yynls != undefined)
		$("#" + bb5).val(yynls.resumeYylb);
	if (yynls != undefined)
		$("#" + bb6).val(yynls.resumeTsnl);
	if (yynls != undefined)
		$("#" + bb7).val(yynls.resumeDxnl);
	if (yynls != undefined)
		$("#" + bb8).val(yynls.resumeDjks);
	$(document)
			.ready(
					function() {
						$("#" + iii)
								// 验证成功
								.on(
										'valid.form',
										function() {
											var id = $('#resumeId').val();
											if (id == "") {
												alert("请将基本信息填写完成!");
												return;
											} else {
												$
														.ajax({
															url : 'SaveYynlagain',
															type : 'POST',
															data : $(this)
																	.serialize(),
															success : function(
																	d) {
																$(
																		"#"
																				+ _yynlInputId)
																		.val(
																				d.model.resume_yynl.resumeYynlid);
																var yynlid = $(
																		"#"
																				+ _yynlInputId)
																		.val();
																$(
																		"#"
																				+ _yynlDeleteId)
																		.attr(
																				"onclick",
																				"deletediv11('"
																						+ jj
																						+ "','"
																						+ yynlid
																						+ "')");
																alert("保存成功!");
																$("#href11")
																		.css(
																				'color',
																				'green');
															}
														});
											}
										});
					});
};
// 继续添加其他信息cxh
function adddiv1414141414(qtxxs) {
	var kk = "_div" + k;
	var jjj = "form" + a14;
	var bb9 = "qtxxlbid" + b9;
	var _qtxxInputId = "qtxxInputId" + qtxxInputId;
	var _qtxxDeleteId = "qtxxDeleteId" + qtxxDeleteId;
	if (qtxxs != undefined) {
		var qtxxid = qtxxs.resumeQtxxid;
	}
	$("#ddiv14")
			.append(
					"<div id="
							+ kk
							+ ">"
							+ "<div>"
							+ "<form action=\"SaveQtxxagain\" id="
							+ jjj
							+ " method=\"post\" autocomplete=\"off\" data-validator-option=\"{theme:'yellow_right_effect',stopOnError:true}\">"
							+ "<div>"
							+ "<input type=\"hidden\" id=\"resumeId\" name=\"resumeId\" value="
							+ $('#resumeId').val()
							+ ">"
							+ "<input type=\"hidden\" id="
							+ _qtxxInputId
							+ " name=\"resumeQtxxid\"  value='"
							+ (qtxxs == undefined ? '' : qtxxs.resumeQtxxid)
							+ "'/>"
							+ "</div>"
							+ "<table>"
							+ "<tbody>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "其他信息类别"
							+ "</th>"
							+ "<td>"
							+ "<select id="
							+ bb9
							+ " name=\"resumeQtxxlb1\" data-rule=\"required(not)\" onChange=\"javascript:qita(this.id);\">"
							+ "<option value=\"\">"
							+ ""
							+ "</option>"
							+ "<option value=\"奖惩情况\">"
							+ "奖惩情况"
							+ "</option>"
							+ "<option value=\"培训情况\">"
							+ "培训情况"
							+ "</option>"
							+ "<option value=\"熟悉何种专业技术及有何种特长\">"
							+ "熟悉何种专业技术及有何种特长"
							+ "</option>"
							+ "<option value=\"兴趣爱好\">"
							+ "兴趣爱好"
							/*+ "</option>"
							+ "<option value=\"职业技能\">"
							+ "职业技能"
							+ "</option>"
							+ "<option value=\"社会活动\">"
							+ "社会活动"
							+ "</option>"
							+ "<option value=\"荣誉\">"
							+ "荣誉"
							+ "</option>"
							+ "<option value=\"宗教信仰\">"
							+ "宗教信仰"
							+ "</option>"
							+ "<option value=\"推荐信\">"
							+ "推荐信"
							+ "</option>"
							+ "<option value=\"其他\">"
							+ "其他"
							+ "</option>"*/
							+ "</select>"
							/*+ "&nbsp;"
							+ "<input type=\"text\" class=\"inputText\" id=\"resumeQtxxlb2\" data-rule=\"其他信息:required;\"  name=\"resumeQtxxlb2\" maxlength=\"50\"    value="
							+ (qtxxs == undefined ? '' : qtxxs.resumeQtxxlb2)
							+ ">"
							+ "<span class=\"note\">"
							+ "50字符以内"
							+ "</span>"*/
							+ "</td>"
							+ "<td class=\"toolbar\" >"
							+ "<input type=\"submit\" class=\"inputButton\" value=\"保存\"/>"
							+ "</td>"
							+ "<td class=\"toolbar\" >"
							+ "<input type=\"button\" id="
							+ _qtxxDeleteId
							+ " class=\"inputButton\" value=\"删除\" onclick=\"deletediv14('"
							+ kk
							+ "','"
							+ qtxxid
							+ "')\"/>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "信息描述"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<textarea id=\"resumeXxms\" cols=\"58\" rows=\"4\" name=\"resumeXxms\"  maxlength=\"500\" >"
							+ (qtxxs == undefined ? '' : qtxxs.resumeXxms)
							+ "</textarea>" + "<span class=\"note\"><br/>"
							+ "&nbsp;500字符以内" + "</span>" + "</td>" + "</tr>"
							+ "<tbody>" + "</table>" + "</form>" + "</div>"
							+ "</div>");
	k = k + 1;
	a14 = a14 + 1;
	b9 = b9 + 1;
	qtxxInputId = qtxxInputId + 1;
	qtxxDeleteId = qtxxDeleteId + 1;
	if (qtxxs != undefined)
		$("#" + bb9).val(qtxxs.resumeQtxxlb1);
	$(document)
			.ready(
					function() {
						$("#" + jjj)
								// 验证成功
								.on(
										'valid.form',
										function() {
											var id = $('#resumeId').val();
											if (id == "") {
												alert("请将基本信息填写完成!");
												return;
											} else {
												$
														.ajax({
															url : 'SaveQtxxagain',
															type : 'POST',
															data : $(this)
																	.serialize(),
															success : function(
																	d) {
																$(
																		"#"
																				+ _qtxxInputId)
																		.val(
																				d.model.resume_qtxx.resumeQtxxid);
																var qtxxid = $(
																		"#"
																				+ _qtxxInputId)
																		.val();
																$(
																		"#"
																				+ _qtxxDeleteId)
																		.attr(
																				"onclick",
																				"deletediv14('"
																						+ kk
																						+ "','"
																						+ qtxxid
																						+ "')");
																alert("保存成功!");
																$("#href14")
																		.css(
																				'color',
																				'green');

															}
														});
											}
										});
					});
};
var wenjian = 1;
// 继续添加附件/作品
function adddiv15(fjs) {
	var _fjInputId = "fjInputId" + fjInputId;
	var _fjjlDeleteId = "fjjlDeleteId" + fjjlDeleteId;
	if (fjs != undefined) {
		var fjid = fjs.resumeFjid;
	}
	var newlujing = lujing + "/" + fjs.resumeFj;
	$("#ddiv15")
			.append(
					"<input type=\"hidden\" id="
							+ _fjInputId
							+ " name=\"resumeFjid\"  value='"
							+ (fjs == undefined ? '' : fjs.resumeFjid)
							+ "'/>"
							+ "<table>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "已上传附件"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<img src="
							+ fjs.resumeFj
							+ " width=\"150px\" height=\"100px\" onclick=\"window.open('"
							+ newlujing
							+ "')\">"
							+ "<p class=\"note\" >"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点击图片可查看原图"
							+ "</p>"
							+ "</td>"
							+ "<td class=\"toolbar\">"
							+ "<input type=\"button\" id="
							+ _fjjlDeleteId
							+ " class=\"inputButton\" value=\"删除\" onclick=\"deletediv15('"
							+ fjid + "')\" />" + "</td>" + "</tr>" + "</table>");
	fjInputId = fjInputId + 1;
	fjjlDeleteId = fjjlDeleteId + 1;

};
function adddiv16(fjs) {
	_wenjian = wenjian;
	var _fjInputId = "fjInputId" + fjInputId;
	var _fjjlDeleteId = "fjjlDeleteId" + fjjlDeleteId;
	if (fjs != undefined) {
		var fjid = fjs.resumeFjid;
	}
	var newlujing = lujing + "/" + fjs.resumeFj;
	$("#ddiv15")
			.append(
					"<input type=\"hidden\" id="
							+ _fjInputId
							+ " name=\"resumeFjid\"  value='"
							+ (fjs == undefined ? '' : fjs.resumeFjid)
							+ "'/>"
							+ "<table>"
							+ "<tr>"
							+ "<th style=\"width:112px;height: 29px;text-align:right\">"
							+ "已上传附件"
							+ "</th>"
							+ "<td colspan=\"3\">"
							+ "<a href=\"javascript:void(0)\" style=\"text-decoration:underline;cursor:pointer;\" onclick=\"window.open('"
							+ newlujing
							+ "')\">"
							+ "点击查看已上传文件"
							+ "</a>"
							+ "</td>"
							+ "<td class=\"toolbar\">"
							+ "<input type=\"button\" id="
							+ _fjjlDeleteId
							+ " class=\"inputButton\" value=\"删除\" onclick=\"deletediv15('"
							+ fjid + "')\" />" + "</td>" + "</tr>" + "</table>");
	fjInputId = fjInputId + 1;
	fjjlDeleteId = fjjlDeleteId + 1;
	wenjian = wenjian + 1;

};

/* ============获取地址=========== */
// 获取当前网址，如： http://localhost:8088/test/test.jsp
var curPath = window.document.location.href;
// 获取主机地址之后的目录，如： test/test.jsp
var pathName = window.document.location.pathname;
var pos = curPath.indexOf(pathName);
// 获取主机地址，如： http://localhost:8088
var localhostPaht = curPath.substring(0, pos);
// 获取带"/"的项目名，如：/test
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var lujing = localhostPaht + projectName;

// AJAX提交证书请求
function enter12() {
	var options = {
		type : 'post',
		url : "SaveZs",
		success : showResponseMov,
		error : showResponseMov,
		clearForm : false
	};
	$("#form12").ajaxSubmit(options);
	function showResponseMov() {
		alert("保存成功！");
		$("#href12").css('color', 'green');
	}
}

// AJAX提交IT技能请求
function enter13() {
	var options = {
		type : 'post',
		url : "SaveItjn",
		success : showResponseMov,
		error : showResponseMov,
		clearForm : false
	};
	$("#form13").ajaxSubmit(options);
	function showResponseMov() {
		alert("保存成功！");
		$("#href13").css('color', 'green');
	}
}

// AJAX提交校内奖励请求
function enterandenter4(bbb) {
	var options = {
		type : 'post',
		url : "SaveXnjlagain",
		success : showResponseMov,
		error : showResponseMov,
		clearForm : false
	};
	$("#" + bbb).ajaxSubmit(options);
	function showResponseMov() {
		alert("保存成功！");
		$("#href4").css('color', 'green');
	}
}

// AJAX提交校内职务请求
function enterandenter5(ccc) {
	var options = {
		type : 'post',
		url : "SaveXnzwagain",
		success : showResponseMov,
		error : showResponseMov,
		clearForm : false
	};
	$("#" + ccc).ajaxSubmit(options);
	function showResponseMov() {
		alert("保存成功！");
		$("#href5").css('color', 'green');
	}
}

// AJAX提交实践经历请求
function enterandenter6(ddd) {
	var options = {
		type : 'post',
		url : "SaveSjjlagain",
		success : showResponseMov,
		error : showResponseMov,
		clearForm : false
	};
	$("#" + ddd).ajaxSubmit(options);
	function showResponseMov() {
		alert("保存成功！");
		$("#href6").css('color', 'green');
	}
}

// AJAX提交实习经历请求
function enterandenter7(eee) {
	var options = {
		type : 'post',
		url : "SaveSxjlagain",
		success : showResponseMov,
		error : showResponseMov,
		clearForm : false
	};
	$("#" + eee).ajaxSubmit(options);
	function showResponseMov() {
		alert("保存成功！");
		$("#href7").css('color', 'green');
	}
}

// AJAX提交工作经历请求
function enterandenter8(fff) {
	var options = {
		type : 'post',
		url : "SaveGzjlagain",
		success : showResponseMov,
		error : showResponseMov,
		clearForm : false
	};
	$("#" + fff).ajaxSubmit(options);
	function showResponseMov() {
		alert("保存成功！");
		$("#href8").css('color', 'green');
	}
}

// AJAX提交项目经验请求
function enterandenter9(ggg) {
	var options = {
		type : 'post',
		url : "SaveXmjyagain",
		success : showResponseMov,
		error : showResponseMov,
		clearForm : false
	};
	$("#" + ggg).ajaxSubmit(options);
	function showResponseMov() {
		alert("保存成功！");
		$("#href9").css('color', 'green');
	}
}

// AJAX提交培训经历请求
function enterandenter10(hhh) {
	var options = {
		type : 'post',
		url : "SavePxjlagain",
		success : showResponseMov,
		error : showResponseMov,
		clearForm : false
	};
	$("#" + hhh).ajaxSubmit(options);
	function showResponseMov() {
		alert("保存成功！");
		$("#href10").css('color', 'green');
	}
}

// AJAX提交语言能力请求
function enterandenter11(iii) {
	var options = {
		type : 'post',
		url : "SaveYynlagain",
		success : showResponseMov,
		error : showResponseMov,
		clearForm : false
	};
	$("#" + iii).ajaxSubmit(options);
	function showResponseMov() {
		alert("保存成功！");
		$("#href11").css('color', 'green');
	}
}

// AJAX提交其他信息请求
function enterandenter14(jjj) {
	var options = {
		type : 'post',
		url : "SaveQtxxagain",
		success : showResponseMov,
		error : showResponseMov,
		clearForm : false
	};
	$("#" + jjj).ajaxSubmit(options);
	function showResponseMov() {
		alert("保存成功！");
		$("#href14").css('color', 'green');
	}
}

// AJAX提交附件/作品请求
function enterandenter15() {
	var id = $('#fjresumeId').val();
	var name = $('#file').val();
	var fileName = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
	if (fileName != 'jpg' && fileName != 'gif' && fileName != 'bmp'
			&& fileName != 'png' && fileName != 'pdf') {
		alert("请选择正确的附件格式！*.jpg *.gif *.png *.bmp *.pdf");
		return;
	}
	if (id == "") {
		alert("请将基本信息保存完成再上传附件!");
		return;
	}
	var options = {
		type : 'post',
		url : "SaveFj",
		success : showResponseMov,
		error : showResponseMov,
		clearForm : false,
		beforeSend : function() {
			$("#tishi").html("<span>正在上传中………请稍等</span>");
		}
	};
	$("#form15").ajaxSubmit(options);
	function showResponseMov(d) {
		if (d == "false") {
			alert("上传文件过大！");
			$("#tishi").remove();
		} else if (d == "fjfalse") {
			alert("最多只能上传5份附件！");
			$("#tishi").remove();
		} else if (d == "true") {
			alert("上传成功！");
			window.location.reload();
		} else if (d != "true" && d != "fjfalse" && d != "false") {
			alert("上传成功");
			window.location.reload();
		} else {
			window.location.href = "Main";
		}
	}
}

// AJAX提交自制个人简历/作品请求
function enterandenter19() {
	var id = $('#zzjlresumeId').val();
	var name = $('#fileZzjl').val();
	var fileName = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
	if (fileName != 'jpg' && fileName != 'gif' && fileName != 'bmp'
			&& fileName != 'png' && fileName != 'pdf') {
		alert("请选择正确的附件格式！*.jpg *.gif *.png *.bmp *.pdf");
		return;
	}
	if (id == "") {
		alert("请将基本信息保存完成再上传附件!");
		return;
	}
	var options = {
		type : 'post',
		url : "SaveZzjl",
		success : showResponseMov,
		error : showResponseMov,
		clearForm : false,
		beforeSend : function() {
			$("#tishi").html("<span>正在上传中………请稍等</span>");
		}
	};
	$("#form16").ajaxSubmit(options);
	function showResponseMov(d) {
		if (d == "false") {
			alert("上传文件过大！");
			$("#tishi").remove();
		} else if (d == "fjfalse") {
			alert("最多只能上传5份附件！");
			$("#tishi").remove();
		} else if (d == "SessionLost") {
			alert("登录超时，请重新登录！");
			$("#tishi").remove();
			window.location.href = "Main";
		} else if (d == "true") {
			alert("上传成功！");
			window.location.reload();
		} else if (d != "true" && d != "fjfalse" && d != "false") {
			alert("上传成功");
			window.location.reload();
		} else {
			window.location.href = "Main";
		}
	}
}

// 一寸照片提交
function enterPhotos() {
	var name = $('#resumePhotos').val();
	var id = $('#photosresumeId').val();
	var fileName = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
	if (fileName != 'jpg' && fileName != 'bmp' && fileName != 'png'
			&& fileName != 'jpeg') {
		alert("请选择正确的照片格式！*.jpg *.png *.bmp*.jpeg");
		return;
	}

	if (id == "") {
		alert("请将基本信息保存完成再上传照片!");
		return;
	}
	var options = {
		type : 'post',
		url : "SavePhotos",
		success : showResponseMov,
		error : showResponseMov,
		clearForm : false,
		beforeSend : function() {
			$("#xinzeng").html(
					"<td> </td><td colspan='2'><span>正在上传中………请稍等</span></td>");
		}
	};
	$("#Photosform").ajaxSubmit(options);
	function showResponseMov(d) {
		if (d == "false") {
			alert("上传照片过大！请选择1M以下照片！");
			$("#xinzeng").remove();
		} else {
			alert("上传成功！");
			window.location.reload();
		}
	}
}

// 预览简历请求
function look() {
	var resumeId = $("#resumeId").val();
	window.location.href = "Yljl?resumeId=" + resumeId;
}

function trim(s) {
	return s.replace(/^\s+|\s+$/g, "");
};

// 验证身份证号并获取出生日期
function getBirthdatByIdNo(iIdNo) {
	var tmpStr = "";
	var idDate = "";
	var tmpInt = 0;
	var strReturn = "";

	iIdNo = trim(iIdNo);

	if ((iIdNo.length != 15) && (iIdNo.length != 18)) {
		strReturn = "输入的身份证号位数错误";
		return strReturn;
	}

	if (iIdNo.length == 15) {
		tmpStr = iIdNo.substring(6, 12);
		tmpStr = "19" + tmpStr;
		tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-"
				+ tmpStr.substring(6)

		return tmpStr;
	} else {
		tmpStr = iIdNo.substring(6, 14);
		tmpStr = tmpStr.substring(0, 4) + "." + tmpStr.substring(4, 6) + "."
				+ tmpStr.substring(6)

		return tmpStr;
	}
}

// 页面是否包含给定文本字符串
function isHaveStr(str) {
	var html = document.body.innerHTML;
	if (html.indexOf(str) > -1)
		return "学历";
	else
		return "<span style=\"color:red;\">最高学历</span>";
}
