// JavaScript Document

 $(function(){
    //选中菜单
	$("#systemInfo").find("a").click(function(){		//找到menu中的a触发一个事件。
	 $("#systemInfo").find("a").removeClass("on");	//每次加载，都先移除掉ON样式
	 $(this).addClass("on");				    //再找到当前选中的a，加载on样式
	})
 })		
 