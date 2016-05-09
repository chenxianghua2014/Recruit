function seachPosition() {
	window.location.href = "SearchPosition?zpdw=" + escape($("#zpdw").val())
			+ "&zpzy=" + escape($("#zpzy").val()) + "&zwlb=" + escape($("#zwlb").val())
			+ "&gzdd=" + escape($("#gzdd").val())+ "&xlyq=" + escape($("#xlyq").val())
			+ "&dt=" + escape($("#dt").val());
}
$(document).ready(function(){
	$.ajax({
		type : 'POST',
		url : "LinkListJson",
		data : {},
		dataType : "json",
		success : function(mav) {
			$("#csic1").empty();	//集团直属二级单位
			$("#csic1").append("<option value=\"\" selected=\"selected\">集团直属二级单位</option>");
			$("#csic2").empty();	//集团所属三级单位
			$("#csic2").append("<option value=\"\" selected=\"selected\">集团所属三级单位</option>");

			for (var i = 0; i <= mav.length - 1; i++) {
				switch(mav[i].linkCat){
					case"集团直属二级单位":
						$("#csic1").append("<option value=\"" + mav[i].linkl + "\">" + mav[i].linkName + "</option>");
						break;
					case"集团所属三级单位":
						$("#csic2").append("<option value=\"" + mav[i].linkl + "\">" + mav[i].linkName + "</option>");
						break;
				}
			}
		}
	});
});
