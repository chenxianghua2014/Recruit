$(document).ready(function() {
	var menuId = "#menu"; 
	$(menuId).find("dd").hide();
	$(menuId).find("dt").hover(function(){ $(this).addClass("hover")}, function(){$(this).removeClass("hover") })
	.click(function(){
			
		if($(this).parent().find("dd").get(0).style.display == "block"){
				$(this).parent().find("dd").hide();
				$(this).removeClass("on");
			} else {
				$("dt").removeClass("on")
				$("dd").hide();
				$(this).addClass("on");
				$(this).parent().find("dd").slideDown("fast"); 
			}
			
	});
	$(menuId).find("a").focus(function(){this.blur()}) 
	$(menuId).find("dd").click(function(){ $("dd").removeClass("focus");$(this).addClass("focus") })
})