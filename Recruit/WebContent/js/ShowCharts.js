function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = location.search.substr(1).match(reg);
	if (r != null)
		return unescape(decodeURI(r[2]));
	return null;
}

$(document).ready(function() {
	var sfzh = getQueryString("sfzh");
	var ksxcglKkslx = getQueryString("kslx");
	var categories1 = [ "判断", "思考", "实感", "外向" ];
	var categories2 = [ "认知", "情感", "直觉", "内向" ];
	var options = {
		chart : {
			renderTo : "container",
			type : "bar"
		},
		title : {
			text : "性格倾向示意图"
		},
		subtitle : {
			text : ""
		},
		xAxis : [ {
			categories : categories1,
			reversed : false
		}, { // mirror axis on right side
			opposite : true,
			reversed : false,
			categories : categories2,
			linkedTo : 0
		} ],
		yAxis : {
			title : {
				text : null
			},
			labels : {
				formatter : function() {
					return "";
				}
			},
			min : -100,
			max : 100
		},

		plotOptions : {
			series : {
				stacking : "normal"
			}
		},

		tooltip : {
			formatter : function() {
				return Highcharts.numberFormat(Math.abs(this.point.y), 0);
			}
		},
		series : []
	};

	var options2 = {
		chart : {
			renderTo : "container2",
			type : "column"
		},
		title : {
			text : ""
		},
		subtitle : {
			text : ""
		},
		subtitle : {
			text : ""
		},
		xAxis : {
			categories : [ "传统型", "研究型", "现实型", "艺术型", "企业型", "社会型" ]
		},
		yAxis : {
			min : 0,
			title : {
				text : ""
			}
		},
		tooltip : {
			formatter : function() {
				return this.x;
			}
		},
		legend : {
			layout : "vertical",
			backgroundColor : "#FFFFFF",
			align : "left",
			verticalAlign : "top",
			x : -20,
			y : 70,
			floating : true,
			shadow : true
		},
		series : []
	};

	$.ajax({
		type : "POST",
		url : "queryByGxcsId",
		data : {
			sfzh : sfzh,
			kslx : ksxcglKkslx
		},
		dataType : "json",
		async : false,
		success : function(gxcs) {
			var pd = -gxcs.gxcsPanduan;
			var sk = -gxcs.gxcsSikao;
			var sg = -gxcs.gxcsShigan;
			var wx = -gxcs.gxcsWaixiang;
			var rz = gxcs.gxcsRenzhi;
			var qg = gxcs.gxcsQinggan;
			var zj = gxcs.gxcsZhijue;
			var nx = gxcs.gxcsNeixiang;
			var ctx = gxcs.gxcsChuantongxing;
			var yjx = gxcs.gxcsYanjiuxing;
			var xsx = gxcs.gxcsXianshixing;
			var ysx = gxcs.gxcsYishuxing;
			var qyx = gxcs.gxcsQiyexing;
			var shx = gxcs.gxcsShehuixing;
			 var WXNX="";
			 var SJZJ="";
			 var SKQG="";
			 var PDRZ="";;
			if(gxcs.gxcsWaixiang > gxcs.gxcsNeixiang){
				var WXNX ="E";
			}else{
				var WXNX ="I";
			}
			
			if(gxcs.gxcsShigan > gxcs.gxcsZhijue){
				var SJZJ = "S";
			}else{
				var SJZJ = "N";
			}
			
			if(gxcs.gxcsSikao > gxcs.gxcsQinggan){
				var SKQG = "T";
			}else{
				var SKQG = "F";
			}
			
			if(gxcs.gxcsPanduan > gxcs.gxcsRenzhi){
				var PDRZ = "J";
			}else{
				var PDRZ = "P";
			}
			var SZ =WXNX+SJZJ+SKQG+PDRZ;
			if(SZ == "ISTJ"){
				document.getElementById("gxcsISTJ").style.display = "";
			}else if(SZ == "ISFJ" ){
				document.getElementById("gxcsISFJ").style.display = "";
			}else if(SZ == "INFJ"){
				document.getElementById("gxcsINFJ").style.display = "";
			}else if(SZ == "INTJ"){
				document.getElementById("gxcsINTJ").style.display = "";
			}else if(SZ == "ISTP"){
				document.getElementById("gxcsISTP").style.display = "";
			}else if(SZ == "ISFP"){
				document.getElementById("gxcsISFP").style.display = "";
			}else if(SZ == "INFP"){
				document.getElementById("gxcsINFP").style.display = "";
			}else if(SZ == "INTP"){
				document.getElementById("gxcsINTP").style.display = "";
			}else if(SZ == "ESTP"){
				document.getElementById("gxcsESTP").style.display = "";
			}else if(SZ == "ESFP"){
				document.getElementById("gxcsESFP").style.display = "";
			}else if(SZ == "ENFP"){
				document.getElementById("gxcsENFP").style.display = "";
			}else if(SZ == "ENTP"){
				document.getElementById("gxcsENTP").style.display = "";
			}else if(SZ == "ESTJ"){
				document.getElementById("gxcsESTJ").style.display = "";
			}else if(SZ == "ESFJ"){
				document.getElementById("gxcsESFJ").style.display = "";
			}else if(SZ == "ENFJ"){
				document.getElementById("gxcsENFJ").style.display = "";
			}else if(SZ == "ENTJ"){
				document.getElementById("gxcsENTJ").style.display = "";
			}
			
			var series = {
				data : [{color:"#FF4500 ",y:pd},
				    	{color:"#1E1E1E",y:sk},
				    	{color:"#C0FF3E",y:sg},
				    	{color:"#CD1076",y:wx}
				    	]
			};
			series.name = " ";
			var series2 = {
				data : [{color:"#1E90FF",y:rz},
				    	{color:"#9400D3",y:qg},
				    	{color:"#5B5B5B",y:zj},
				    	{color:"#FFD700",y:nx}
				    	]
			};
			series2.name = " ";
			options.series.push(series);
			options.series.push(series2);
			var chart = new Highcharts.Chart(options);
			var series3 = {
				data : [{color:"#1874CD",y:ctx},
				    	{color:"#473C8B",y:yjx},
				    	{color:"#00FF00",y:xsx},
				    	{color:"#8B0000",y:ysx},
				    	{color:"#CDAD00",y:qyx},
				    	{color:"#CD2990",y:shx}
				    	]
			};
			series3.name = " ";
			options2.series.push(series3);
			var chart2 = new Highcharts.Chart(options2);
			$("#container").show();
			$("#container2").show();
		},
		error : function(e) {
			$("#content").html("该人员未进行个性测试.");
			$("#content").show();
			$("#container").hide();
			$("#container2").hide();
			$("#gxcsjs").hide();
		}
	});
});