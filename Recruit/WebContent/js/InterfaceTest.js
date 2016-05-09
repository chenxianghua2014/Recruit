$(document).ready(function() {
	$.ajax({
		url : "http://localhost:8080/Recruit/Mobile/CheckLogin",
		type : "POST",
		dataType : 'json',
		data : {
			strUserName : "admin",
			strPassword : "dzx123"
		},
		async : false,
		success : function(result) {
			var d = JSON.stringify(result);
			// $.ajax({
			// type : 'POST',
			// url : "GetZprcIos",
			// data : {
			// sessionId : result.result.sessionId,
			// pageNum : 1,
			// pageSize : 100,
			// zpxwAddcompany : '天',
			// zpxwSchool : '长安'
			// },
			// async : false,
			// dataType : "json",
			// success : function(r) {
			// var a = JSON.stringify(r);
			// var d = eval("(" + r.result[0].zpxwContent + ")");
			// for (var i = 0; i < r.result.length; i++) {
			// $.ajax({
			// type : 'POST',
			// url : "GetZprcContent",
			// data : {
			// sessionId : result.result.sessionId,
			// zpxwId : r.result[i].zpxwId
			// },
			// async : false,
			// dataType : "json",
			// success : function(rr) {
			// var aas = JSON.stringify(rr);
			// },
			// error : function(e) {
			// alert(e);
			// }
			// });
			// }
			// },
			// error : function(e) {
			// alert(e);
			// }
			// });
			// 接口7
			// $.ajax({
			// type : 'POST',
			// url : "SendAllMessage",
			// data : {
			// sessionId : result.result.sessionId,
			// strUserId : "01ADAE08-D6BE-A0A1-C081-0C3018B16CEE"
			// },
			// async : false,
			// dataType : "json",
			// success : function(r) {
			// var a = JSON.stringify(r);
			// },
			// error : function(e) {
			// alert(e);
			// }
			// });
			// 接口1
			// $.ajax({
			// type : 'POST',
			// url : "GetMsq",
			// data : {
			// sessionId : result.result.sessionId,
			// zzjgId : "test001",
			// pageNum : "3",
			// pageSize : "10",
			// },
			// dataType : "json",
			// async : false,
			// success : function(r) {
			// var a = JSON.stringify(r);
			// console.log(r.result[0].zpxwContent);
			// var d = eval("(" + r.result[0].zpxwContent + ")");
			// },
			// error : function(e) {
			// alert(e);
			// }
			// });
			// 接口3
			// $.ajax({
			// url : "http://localhost:8080/Recruit/Mobile/GetJtjlkByMsq",
			// type : "POST",
			// dataType : 'json',
			// data : {
			// sessionId : result.result.sessionId,
			// strMsqId : '6B890F9F-6665-B40C-7BAC-FF057F6D1E1C'
			// },
			// success : function(r) {
			// var a = JSON.stringify(r);
			// alert(a);
			// }
			// });
			// $.ajax({
			// url : "http://localhost:8080/Recruit/Mobile/GetJlByWhere",
			// type : "POST",
			// dataType : 'json',
			// data : {
			// sessionId : result.result.sessionId,
			// type : 1,
			// pageNum : 1,
			// pageSize : 1111
			// },
			// success : function(r) {
			// var a = JSON.stringify(r);
			// alert(a);
			// }
			// });
			// $.ajax({
			// url : "http://localhost:8080/Recruit/Mobile/SearchPosition",
			// type : "POST",
			// dataType : 'json',
			// data : {
			// sessionId : result.result.sessionId,
			// pageNum : 1,
			// pageSize : 1111,
			// positionName : '技术岗'
			// },
			// success : function(r) {
			// var a = JSON.stringify(r);
			// alert(a);
			// }
			// });
			// $.ajax({
			// url : "http://localhost:8080/Recruit/Mobile/GetTestResults",
			// type : "POST",
			// dataType : 'json',
			// data : {
			// sessionId : result.result.sessionId,
			// pageNum : 1,
			// pageSize : 1111,
			// strZzjgId : 'test001',
			// jtjlkName : '刘',
			// jtjlkSfzh : '130'
			// },
			// success : function(r) {
			// var a = JSON.stringify(r);
			// alert(a);
			// }
			// });
			// $.ajax({
			// url : "http://localhost:8080/Recruit/Mobile/GetBmgl",
			// type : "POST",
			// dataType : 'json',
			// data : {
			// sessionId : result.result.sessionId,
			// pageNum : 1,
			// pageSize : 1111,
			// bmglKsdrq : '2014-10-14',
			// bmglKcmc : '北京'
			// },
			// success : function(r) {
			// var a = JSON.stringify(r);
			// alert(a);
			// }
			// });

			// $.ajax({
			// url : "http://localhost:8080/Recruit/Mobile/GetKcgl",
			// type : "POST",
			// dataType : 'json',
			// data : {
			// sessionId : result.result.sessionId,
			// pageNum : 1,
			// pageSize : 1111,
			// kcglName : '北',
			// kcglKsrq : '2014-10-14',
			// kcglKczt : '关闭'
			// },
			// success : function(r) {
			// var a = JSON.stringify(r);
			// alert(a);
			// }
			// });
		}
	});
});