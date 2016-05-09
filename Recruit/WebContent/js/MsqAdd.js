function SelectAll() {
	$("#msqMsgNames option").each(function() {
		$(this).attr("selected", true);
	});
}
function AddMsg() {
	var _v = $("#selMsg").val();
	if (_v == null)
		return;
	for ( var i = 0; i < _v.length; i++) {
		var _t = $("#selMsg option[value='" + _v[i] + "']").text();
		var flag = false;
		$("#msqMsgNames option").each(function() {
			if (_v[i] == $(this).val())
				flag = true;
		});
		if (flag != true)
			$("#msqMsgNames").append(
					"<option value='" + _v[i] + "'>" + _t + "</option>");
	}
}

function RemoveMsg() {
	var _v = $("#msqMsgNames").val();
	for ( var i = 0; i < _v.length; i++) {
		$("#msqMsgNames option[value='" + _v[i] + "']").remove();
	}
}

function AddALLMsg() {
	$("#selMsg option").each(function() {
		var _v = $(this).val();
		var flag = false;
		$("#msqMsgNames option").each(function() {
			if (_v == $(this).val())
				flag = true;
		});
		if (flag != true)
			$("#msqMsgNames").append(
					"<option value='" + $(this).val() + "'>"
							+ $(this).text() + "</option>");
	});
}

function RemoveAllMsg() {
	$("#msqMsgNames").empty();
}
$(document).ready(function() {
	bindSelect();
	bindMsg();
});

function bindSelect() {
	$.ajax({
		type : 'POST',
		url : "LoadBm",
		data : {},
		dataType : "json",
		success : function(bms) {
			$("#bm").empty();
			$("#bm").append("<option value=''>部门(所有)</option>");
			if (bms.length > 0) {
				for ( var i = 0; i < bms.length; i++) {
					$("#bm").append(
							"<option value='" + bms[i].txlDepartment + "'>"
									+ bms[i].txlDepartment + "</option>");
				}
			}
		}
	});
	$.ajax({
		type : 'POST',
		url : "LoadZw",
		data : {},
		dataType : "json",
		success : function(zws) {
			$("#zw").empty();
			$("#zw").append("<option value=''>职位(所有)</option>");
			if (zws.length > 0) {
				for ( var i = 0; i < zws.length; i++) {
					$("#zw").append(
							"<option value='" + zws[i].txlPosition + "'>"
									+ zws[i].txlPosition + "</option>");
				}
			}

		}
	});
}
function bindMsg() {
	$.ajax({
		type : 'POST',
		url : "LoadMsg",
		data : {
			zw : $("#zw").val(),
			bm : $("#bm").val()
		},
		dataType : "json",
		success : function(msgs) {
			$("#selMsg").empty();
			if (msgs.length > 0) {
				for ( var i = 0; i < msgs.length; i++) {
					$("#selMsg").append(
							"<option value='" + msgs[i].txlName + "'>"
									+ msgs[i].txlName + "</option>");
				}
			}

		}
	});
}