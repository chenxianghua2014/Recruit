var pageSize = 10;
var dataCount = 0;
function pageselectCallback(page_index) {
	page_index += 1;
	var strHtml = "";
	var currentPageSize = (dataCount < page_index * pageSize ? dataCount
			- (page_index * pageSize - 10) : pageSize);
	$("#count").html(dataCount);
	if (dataCount == 0)
		$("#start").html(0);
	else
		$("#start").html(page_index * 10 - 9);
	$("#end").html(currentPageSize + page_index * 10 - 10);
	$
			.ajax({
				type : 'POST',
				url : "LoadJtjlkData",
				data : {
					pageNum : page_index,
					pageSize : pageSize,
					currentPageSize : currentPageSize,
					jtjlkName : $("#jtjlkName").val(),
					jtjlkByyx : $("#jtjlkByyx").val(),
					jtjlkZy : $("#jtjlkZy").val(),
					jtjlkXl : $("#jtjlkXl").val(),
					jtjlkZw : $("#jtjlkZw").val(),
					jtjlkGwlb : $("#jtjlkGwlb").val(),
					jtjlkHkd : $("#jtjlkHkd").val()
				/*
				 * , jtjlkZw : $("#jtjlkZw").val(), jtjlkGwlb :
				 * $("#jtjlkGwlb").val()
				 */
				},
				dataType : "json",
				success : function(jtjlks) {
                    /**
                     * 控制回显
                     * 若ids中存在该id，则将该checkbox勾选
                     * */
					var ids = $("#jtjlkIds").val().split("/");
					for (var i = 0; i <= jtjlks.length - 1; i++) {
						strHtml += "<tr>";
						/*var j = 0;
						for (j; j < ids.length; j++) {
							if (jtjlks[i].jtjlkId == ids[j]) {
								strHtml += "<td><input name='subBox' type='checkbox' checked='checked' onclick='editJtjlkId(this)' value='"
										+ jtjlks[i].jtjlkId + "'/></td>";
								break;
							}

						}
						if (j == ids.length) {
							strHtml += "<td><input name='subBox' type='checkbox'  onclick='editJtjlkId(this)' value='"
									+ jtjlks[i].jtjlkId + "'/></td>";
						}*/

						strHtml += "<td>" + jtjlks[i].jtjlkName + "</td>"; /* 姓名 */
						strHtml += "<td>" + jtjlks[i].jtjlkGwlb + "</td>"; /* 应聘岗位1 */
						strHtml += "<td>" + jtjlks[i].jtjlkZw + "</td>"; /* 应聘岗位2 */
						strHtml += "<td>" + jtjlks[i].jtjlkSex + "</td>"; /* 性别 */
						strHtml += "<td>" + jtjlks[i].jtjlkCpcj + "</td>"; /* 工作单位 */
						strHtml += "<td>" + jtjlks[i].jtjlkJlzt + "</td>"; /* 职务 */
						strHtml += "<td>" + jtjlks[i].jtjlkCsrq + "</td>"; /* 出生年月 */
						strHtml += "<td>" + jtjlks[i].jtjlkYxpm + "</td>"; /* 参加工作时间 */
						strHtml += "<td>" + jtjlks[i].jtjlkBjpm + "</td>"; /* 入党时间 */
						strHtml += "<td>" + jtjlks[i].jtjlkXl + "</td>"; /* 学历 */
						strHtml += "<td>" + jtjlks[i].jtjlkLy + "</td>"; /* 学位 */
						strHtml += "<td>" + jtjlks[i].jtjlkByxx + "</td>"; /* 毕业院校 */
						strHtml += "<td>" + jtjlks[i].jtjlkZy + "</td>"; /* 专业 */
						strHtml += "<td>" + jtjlks[i].jtjlkCpjg + "</td>"; /* 外语种类 */
						strHtml += "<td>" + jtjlks[i].jtjlkMszt + "</td>"; /* 联系电话 */
						strHtml += "<td>" + jtjlks[i].jtjlkHkd + "</td>"; /* 户口地 */

						/*
						 * strHtml += "<td>" + jtjlks[i].jtjlkSfzh + "</td>";
						 * strHtml += "<td>" + jtjlks[i].jtjlkBjpm + "</td>";
						 */
						strHtml += "<td class='alignCenter'><input name='button'";
						strHtml += "		type='button' onclick=\"window.open('YljlHr?resumeId="
								+ jtjlks[i].jtjlkUserid
								+ "&jtjlkId=jtjlk');\" class='inputButton' value='查看' />";

						/*
						 * if (jtjlks[i].jtjlkCpjg != "测评淘汰" &&
						 * jtjlks[i].jtjlkJlzt != "录用") { strHtml += "<td class='alignCenter'><input
						 * name='button'"; strHtml += " type='button'
						 * onclick=\"CollectionJl('" + jtjlks[i].jtjlkId +
						 * "')\""; strHtml += " class='inputButton' value='收藏'
						 * />"; strHtml += "</td>"; } else { strHtml += "<td class='alignCenter'>不可用";
						 * strHtml += "</td>"; }
						 */
						/*
						 * strHtml += "<td class='alignCenter'><input
						 * name='button'"; strHtml += " type='button'
						 * onclick=\"DelJl('" + jtjlks[i].jtjlkId + "')\"";
						 * strHtml += " class='inputButton' value='删除' />";
						 * strHtml += "</td>";
						 */
						strHtml += "</td>";
						strHtml += "</tr>";

					}
					$("#tbList").html(strHtml);
					/**
					 * 控制checkAll选项
					 * 任意一个checkbox未选中，第一行的checkbox就不勾选
					 */
					var subBox = $("input[name=subBox]");
					for (var i = 0; i < subBox.length; i++) {
						if (!subBox[i].checked) {
							$("#checkAll").attr("checked", false);
							break;
						}
					}
				}
			});

	return false;
}

function getOptionsFromForm() {
	var opt = {
		callback : pageselectCallback
	};
	opt.prev_text = "上一页";
	opt.next_text = "下一页";
	return opt;
}

$(document).ready(function() {
	loadData();
});

function loadData() {
	$.ajax({
		type : 'POST',
		url : "LoadJtjlkDataCount",
		data : { // 查询条件
			jtjlkName : $("#jtjlkName").val(),
			jtjlkByyx : $("#jtjlkByyx").val(),
			jtjlkZy : $("#jtjlkZy").val(),
			jtjlkXl : $("#jtjlkXl").val(),
			jtjlkZw : $("#jtjlkZw").val(),
			jtjlkGwlb : $("#jtjlkGwlb").val(),
			jtjlkHkd : $("#jtjlkHkd").val()
		},
		dataType : "json", // 返回总记录数 count
		success : function(count) {
			dataCount = count;
			var optInit = getOptionsFromForm();
			$("#Pagination").pagination(dataCount, optInit); // 使用 jquery
			// pagination插件实现分页
			$("#setoptions").click(function() {
				var opt = getOptionsFromForm();
				$("#Pagination").pagination(dataCount, opt);
			});
		}
	});
}

function DelJl(_id) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'GET',
			url : "JtjlkDel",
			data : {
				id : _id
			},
			dataType : "json",
			success : function(_result) {
				if (_result > 0) {
					alert('删除成功!');
					window.location.reload();
				}
			}
		});
	}
}

function CollectionJl(id) {
	if (confirm("您确定要收藏该简历吗?")) {
		$.ajax({
			type : 'POST',
			url : "CollectionJtjlk",
			data : {
				jtjlkId : id
			},
			dataType : "text",
			success : function(_result) {
				if (_result.indexOf("error") != -1) {
					alert("该简历已在流程中，简历状态为'" + unescape(_result.split("_")[1])
							+ "'！");
				} else {
					alert('收藏成功!');
				}
			}
		});
	}
}

$(function() {
	$("#checkAll").click(function() {
		$('input[name="subBox"]').attr("checked", this.checked);
		for (var len = 0; len < $('input[name="subBox"]').length; len++) {
			editJtjlkId($('input[name="subBox"]')[len]);
		}
	});
	var $subBox = $("input[name='subBox']");
	$subBox
			.click(function() {
				$("#checkAll")
						.attr(
								"checked",
								$subBox.length == $("input[name='subBox']:checked").length ? true
										: false);
			});

}

);

function loadDataExp() {
	// 集团简历库ID
	/*var ids = new Array();
	$("input[name=subBox]").each(function() {
		if ($(this).attr("checked")) {
			ids.push($(this).val());
		}
	});*/
	var ids = $("#jtjlkIds").val().split("/");
	ids.shift();
	if (ids.length < 1) {
		alert("请选择要导出的简历！");
		return;
	}
	$.jBox.tip("正在导出，请耐心等待一会！", 'loading');
	$.ajax({
		type : 'POST',
		url : "expResumes",
		data : {
			"ids" : ids.join(",")
		},
		dataType : "json",
		success : function(rst) {
			if (rst.status == "success") {
				alert("导出成功!");
				$.jBox.closeTip();
				window.open("uppics/" + rst.msgBody);
			} else
				alert("导出失败,请联系管理员!");
			$.jBox.closeTip();
		},
		error : function(rst) {
			alert("导出失败,请联系管理员!");
			$.jBox.closeTip();
		}
	});
}
/**
 * 点击checkbox出发事件
 * 将checkbox的id值传入或删除
 * 将刚值存储在id=jtjlkIds的input中
 * 注意严格控制checkbox选中或者未选的条件
 * 注意健壮性
 * */
function editJtjlkId(someCheckbox) {
	// alert(someCheckbox.value);
	var ids = $("#jtjlkIds").val();

	if (someCheckbox.checked) {
		if (ids.indexOf(someCheckbox.value) == -1) {
			ids = ids + "/" + someCheckbox.value;
		}
	} else {
		ids = ids.replace("/" + someCheckbox.value, "");
	}
	$("#jtjlkIds").val(ids);
	//alert(ids);

}
