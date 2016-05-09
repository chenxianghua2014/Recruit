$(document).ready(function() {
 	
 	initDatagrid();
 	initForm();
 	//initTextarea();
	//initTitleBar();



})


//初始化数据表格,此方法仅适用于页面只有一个datagrid
function initDatagrid(){
	$(".dataGrid table").eq(0).find("tr:even").addClass("odd")
	$(".dataGrid table").eq(0).find("tr").hover(function(){ $(this).addClass("over")}, function(){$(this).removeClass("over") });
	DGCheckboxCtrl();
	//表头checkbox
	$(".dataGrid thead :checkbox").click(function(){
												  var tbodyCheckBox=$(this).parents("thead").next("tbody").find(":checkbox[@disabled='']");
												  if($(this).get(0).checked == true)
												 
												  	 tbodyCheckBox.attr("checked","checked");
												   else												  	
												   tbodyCheckBox.attr("checked","");
												    DGCheckboxCtrl();
													//var checkedNum=$(this).parents("thead").next("tbody").find(":checked").size()
													//showTip("You have Seleted: "+ checkedNum ,this)
												   })
	//tbody checkbox
	$(".dataGrid tbody :checkbox").click(function(){
												  	var tbodyCheckboxNum = $(this).parents("tbody").find(":checkbox[@disabled='']").size()//$(".dataGrid tbody :checkbox") ;
													var tbodyCheckedNum =  $(this).parents("tbody").find(":checked[@disabled='']").size() //$(".dataGrid tbody :checked");
													
													 if(tbodyCheckboxNum!=tbodyCheckedNum)
														 $(this).parents("table").find("thead :checkbox").attr("checked","");
													else
													 $(this).parents("table").find("thead :checkbox").attr("checked","checked");									  
												   DGCheckboxCtrl();
												 
												// showTip("You have Seleted: "+tbodyCheckedNum,this)
												  })
	  
}
function DGCheckboxCtrl(){
	

	//
	$(".dataGrid tbody :checkbox").parents("tr").removeClass("on");
	$(".dataGrid tbody :checked").parents("tr").addClass("on");
	
		}


//初始化表单	
function initForm(){
		try{
		$("textarea[@readonly],:text[@readonly],:password[@readonly]").focus(function(){this.blur();})
		$("textarea,:text,:password,:file").focus(function(){ $(this).addClass("onFocus"); }).blur(function(){ $(this).removeClass("onFocus"); })
		
		$(":text").filter(function(){return $(this).attr("class") != "Wdate"}).get(0).focus();
	}
	catch(e){}
	}


//title栏
function initTitleBar()
{
	if($("div[@class=title]").get() && top.frames["leftFrame"])
	{
	 var  leftKnot = top.frames["leftFrame"].$("#menu");
	 var  firstTitle =   $(leftKnot).find("dd.focus").parents("dl").find("dt").text() ;
	 var  secondTitle = $(leftKnot).find("dd.focus").text() ;
	 
		if(firstTitle!="" && secondTitle!="")
		{
			var t=""
			if($("title").html()!=="")
			 t= " &gt; "+ $("title").html()
			
		$("div[@class=title]").html("<span>当前位置：</span>"+firstTitle+ " &gt; "+secondTitle+t)	;
		}else{
		$("div[@class=title]").html("<span></span>");
		}
    }
}
//输入字数限制
function initTextarea()
{
	$("textarea[@maxchar]").after("<div class='remain'>&nbsp;&nbsp;您还可以输入<span></span>个字符&nbsp;&nbsp;</div>")
	$("textarea[@maxchar]").each(function(){
								var maxchar=$(this).attr("maxchar"),lens= $(this).val().replace(/[^\x00-\xff]/gi,'ch').length ,	remain= parseInt(maxchar)-lens;  
								$(this).next("div[@class='remain']").children("span").text(remain);
								
	})
	$("textarea[@maxchar]").bind("keyup",charLeft).bind("keydown",charLeft).bind("change",charLeft);
}
	
 function charLeft()
{ 
   var maxchar=$(this).attr("maxchar"),lens= $(this).val().replace(/[^\x00-\xff]/gi,'ch').length ,	remain= parseInt(maxchar)-lens;  
   $(this).next("div[@class='remain']").children("span").text(remain);
  if(remain < 0)
    {
    $(this).val($(this).val().substring(0,maxchar))
	$(this).next("div[@class='remain']").children("span").text(0);
	}
} 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//居中弹出窗口
function popUp(url,width,height,winname,left,top)
{       
	    var width = width || 400,height =height|| 350;
		var left = (left==''||left==null)?(screen.width - width)/2:left;
		var top = (top==''||top==null)?(screen.height - height)/2:top;
		var winnames = (winname=='')?'popUpWin':winname;
		win = window.open(url, winnames, 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,copyhistory=yes,width='+width+',height='+height+',left='+left+', top='+top+',screenX='+left+',screenY='+top+'');
		win.focus();
}
//返回
function goback()
{
	history.back();
}
//最大值
function checkMaxInput(_this,maxleng,leftInforId) 
{ 
	var left=document.getElementById(leftInforId); 
	var len=_this.value.replace(/[^\x00-\xff]/gi,'ch').length; 
	var remainnum =parseInt(maxleng)-len; 
	left.value = remainnum; 
	if(remainnum < 0) 
	{ 
		if(_this.value.length!=len) 
		{ 
			if((len-_this.value.length)>(maxleng/2)) 
			{ 
			_this.value=_this.value.substring(0,maxleng/2); 
			} 
			else 
			{ 
			_this.value=_this.value.substring(0,maxleng-(len-_this.value.length)); 
			} 
		}else{ 
			_this.value=_this.value.substring(0,maxleng); 
		} 
			left.value=0; 
			return; 
	} 
}

