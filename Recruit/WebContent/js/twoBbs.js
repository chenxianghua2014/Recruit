
var pageSize = 10;
var dataCount = 0;
var id="";
function pageselectCallback(page_index, jq) {
	var strHtml = "";
	page_index += 1; 
	var currentPageSize = (dataCount < page_index * pageSize ? dataCount
			- (page_index * pageSize - 10)
			: pageSize);
	$("#count").html(dataCount);
	if(dataCount == 0)
		$("#start").html(0);
	else
		$("#start").html(page_index * 10 - 9);
	$("#end").html(currentPageSize + page_index * 10 - 10);
	$.ajax({
		type : 'POST',
		url : "LoadReviewData",
		data : {
			pageNum : page_index,
			pageSize : pageSize,
			currentPageSize : currentPageSize,
			bbsId : id
		},
		dataType : "json",
		success : function(reviewList) {
			for ( var i = 0; i <= reviewList.length - 1; i++) {
				strHtml += " <div class='pinglun'>";
				strHtml += "<ul>";
				strHtml += "<li>";
				strHtml += "<div class='plmain'>";
				strHtml += "<div class='plmainRight'>";
				strHtml += "<div class='plmainLeft'>"+ reviewList[i].reviewPlr+"：</div>";
				strHtml += "<div class='plmainRightCon'>"+ reviewList[i].reviewNr+"</div>";
				strHtml += "<div class='plmainRightP'>";
				strHtml += "<div class='huifuRightTime'>"+ reviewList[i].plsj + "&nbsp;&nbsp;<a href='javascript:void(0);'onclick=\"showHuifupublish('"
								+ reviewList[i].reviewId
								+ "');\">回复</a></div>   ";                       
				strHtml += "</div>";
				strHtml += "<div class='huifu'>";
				$.ajax({
					type : 'POST',
					url : "qtGetReplyByReviewId",
					data : {
						reviewId :  reviewList[i].reviewId
					},
					dataType : "json",
					async:false,
					success :function(replyList){
						for( var j = 0; j <= replyList.length - 1; j++){
							strHtml += "<div class='huifuLeft'>"+ replyList[j].replyName+"：</div>";
					        strHtml += "<div class='huifuRight'>";
					        strHtml += "<div class='huifuRightP'>"+ replyList[j].replyNr+"</div>";
					        strHtml += "<div class='huifuRightTime'>"+ replyList[j].hfsj + "&nbsp;&nbsp;<a href='javascript:void(0);'onclick=\"showHuifupublish('"
								+ reviewList[i].reviewId
								+ "');\">回复</a></div>";
						}
					}
				});
				strHtml += "<div id='"+reviewList[i].reviewId+2+"' class='huifuText' placeholder='我也说一句'><a href='javascript:void(0);'" +
						"onclick=\"showHuifupublish('"
								+ reviewList[i].reviewId
								+ "');\">我也说一句</a></div>";
				strHtml += "<div class='huifupublish' style='display: none;' id='"+reviewList[i].reviewId+"'>";
				strHtml += "<form name='replyForm' action='qtBbsLtbqReplySave' method='post'>";
				strHtml += "<input type='hidden' name='bbsId' value='"+reviewList[i].bbsId+"'/>";
				strHtml += "<input type='hidden' name='reviewId' value='"+reviewList[i].reviewId+"'/>";
				strHtml += "<script id='"+reviewList[i].reviewId+1+"' name='replyNr' type='text/plain' style='width:670px;height:80px;'></script>";
				
				strHtml += "<div class='huifupubfb'>";
				strHtml += "<div class='huifupubright'><input class = 'inputButton' type='submit' value='发表'/></div>";
				strHtml += "</div> ";      
				strHtml += "</form> ";      
				strHtml += "</div> ";      
	            strHtml += "</div>";
	            strHtml += "</div>   ";                            
	            strHtml += "</div>";
	            strHtml += "</div>  "; 
	            strHtml += "</li>";
	            strHtml += "</ul>";
	            strHtml += "</div>";
			}
			$("#tbList").html(strHtml);
			
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
function getUrlParam(name)
{
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}
$(document).ready(function() {
	loadData(getUrlParam("bbsId"));
});
function loadData(bbsId) {
	id=bbsId;
	$.ajax({
		type : 'POST',
		url : "LoadReviewDataCount",
		data : {
			bbsId : id
		},
		dataType : "json",
		success : function(count) {
			dataCount = count;
			var optInit = getOptionsFromForm();
			$("#Pagination").pagination(dataCount, optInit);
			$("#setoptions").click(function() {
				var opt = getOptionsFromForm();
				$("#Pagination").pagination(dataCount, opt);
			});
		}
	});
}
function showHuifupublish(reviewId){
	if($("#username").val() == null || $("#username").val() == ""){
		$.jBox.open("iframe:LoginFrm", "用户登录", 280, 200, {
			buttons : {}
		});
	}else{
		editor = UE.getEditor(reviewId+1, {
			toolbars : [ toobars ],
			maximumWords : 1000
		});
		
		document.getElementById(reviewId+2).style.display ="none";
		document.getElementById(reviewId).style.display ="";
	}
}
