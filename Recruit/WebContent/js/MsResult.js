$(document).ready(function() {
	loadData();
});
function loadData() {
	var strHtml = "";
	$.ajax({
		type : 'POST',
		url : "LoadMsResultData",
		data : {},
		dataType : "json",
		success : function(msqgls) {
			for(var i = 0; i < msqgls.length; i++) {
				strHtml += "<tr>";
				strHtml += "<td>" + 1 + "</td>";
				strHtml += "<td>" + msqgls[i].msqglDetailedMslb + "</td>";
				strHtml += "<td>"
						+ new Date(msqgls[i].msqglDetailedMssj)
								.toLocaleString() + "</td>";
				strHtml += "<td>" + msqgls[i].msqglDetailedMsdd + "</td>";
				strHtml += "<td>" + msqgls[i].msqglDetailedMsg + "</td>";
				strHtml += "</tr>";
				$("#tbList").html(strHtml);
				$("#msqglDetailedId").val(msqgls[i].msqglDetailedId);
			}
		}
	});
}