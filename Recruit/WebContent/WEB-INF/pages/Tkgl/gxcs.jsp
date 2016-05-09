<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.lang.* ,java.util.*, com.ttgis.recruit.domain.Tkgl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>个性测试考试</title>
<style>
td{ border-bottom:1px dashed #ccc; height:40px; line-height:30px; font-size:14px;}
</style>
<link href="css/cpgl/cpstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet" href="resources/jBox/Skins/Blue/jbox.css"/>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript" src="js/KeyDown.js"></script>
<script type="text/javascript" language="javascript">
	/*------判断radio是否有选中，获取选中的值--------*/
	var maxtime = 25 * 60;//半个小时，按秒计算，自己调整!
	function CountDown() {
		if (maxtime >= 0) {
			minutes = Math.floor(maxtime / 60);
			seconds = Math.floor(maxtime % 60);
			msg = minutes + "分" + seconds + "秒";
			document.all["timer"].innerHTML = msg;
			if (maxtime == 5 * 60)
				$.jBox.messager("您的考试时间还剩余：<span style='color:red;'>5分钟</span>！请抓紧时间", "剩余考试时间");
			--maxtime;
		} else {
			clearInterval(timer);
			$.jBox.messager('时间到，考试结束', '剩余考试时间');
			$.jBox.tip("正在提交试卷，请耐心等待一下！", 'loading');
			document.ff.action = "TestSubmitgx";
			document.ff.method = "post";
			document.ff.submit();
			window.setTimeout(function () { 
				$.jBox.tip('已成功提交试卷。', 'success'); 
				}, 5000);
		}
	}
	timer = setInterval("CountDown()", 1000);
	$(function() {
		$("#btnSubmit").click(function() {
			alert("提交成功");
		});
	});
	function checkValidate() {
			if(Validator.Validate(document.getElementById('ff'),3) ){
				if (confirm("您确定要提交试卷么?")) {
					if (confirm("您确定要提交试卷么?")) {
						if (confirm("您确定要提交试卷么?")) {
							$.jBox.tip("正在提交试卷，请耐心等待一下！", 'loading');
								document.ff.action = "TestSubmitgx";
								document.ff.method = "post";
								document.ff.submit();
							window.setTimeout(function () { 
								$.jBox.tip('已成功提交试卷。', 'success'); 
								}, 5000);
						}
					}
				}
		}
	}
</script>
</head>
<body>
	<div id="fixd">
		<div id="top"></div>
		<div class="conTop">
			<p>考生姓名：${cpglLoginSession1.bmglKsxm}&nbsp;&nbsp;&nbsp;
				身份证号：${cpglLoginSession1.bmglSfzh}</p>
			<div class="time">
				<img src="images/cpgl/time.jpg" />考试剩余时间 ：<span id="timer"></span>
			</div>
		</div>
	</div>
	
		<div class="conRight">
			<div class="questions">
				<form name="ff" id="ff">
					<input type="hidden" name="clickNum" id="clickNum"value="1"/>
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th colspan="2"><h2>高校毕业生招聘考试 ——个性测试</h2></th>
						</tr>
						<tr>
							<th colspan="2">
								<p style="text-align: left; font-size: 12px;">答题说明:</p>
								<p style="text-align: left; font-size: 12px;">1.本部分为测评考试第三部分，考试时长25分钟。</p>
								<p style="text-align: left; font-size: 12px;">一、人的个性与职业有着密切的关系，不同职业对从业者的人格特征的要求是有差距的，如果通过科学的测试，可以预知自己的个性特征，这有助于选择适合于个人发展的职业。您将要阅读的这个《职业价格自测问卷》，可以帮助您作个性自评，从而获自己的个性特征更适合从事哪方面的工作。
									 请根据对每一题目的第一印象作答，不必仔细推敲，答案没有好坏、对错之分。具体填写方法是，根据自己的情况，如果选择“是”，请打“√”
									，否则请打“X”</p>
							</th>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2"><h3>一、判断题</h3></td>
						</tr>
						<tr>
							<td style="text-align: left;">1、我喜欢把一件事情做完后再做另一件事。</td>
							<td width="80"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name1"
								value="是" />√ <input type="radio" name="name1" value="否" />X</td>
						</tr>
						<tr>
							<td style="text-align: left;">2、在工作中我喜欢独自筹划，不愿受别人干涉。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name2"
								value="是" />√ <input type="radio" name="name2" value="否" />X</td>
						</tr>
						<tr>
							<td style="text-align: left;">3、在集体讨论中，我往往保持沉默。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name3"
								value="是" />√ <input type="radio" name="name3" value="否" />X</td>
						</tr>
						<tr>
							<td style="text-align: left;">4、我喜欢做戏剧、音乐、歌舞、新闻采访等方面的工作。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name4"
								value="是" />√ <input type="radio" name="name4" value="否" />X</td>
						</tr>
						<tr>
							<td style="text-align: left;">5、每次写信我都一挥而就，不再重复。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name5"
								value="是" />√ <input type="radio" name="name5" value="否" />X</td>
						</tr>
						<tr>
							<td style="text-align: left;">6、我经常不停地思考某一问题，直到想出正确的答案.</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name6"
								value="是" />√ <input type="radio" name="name6" value="否" />X</td>
						</tr>
						<tr>
							<td style="text-align: left;">7、对别人借我的和我借别人的东西，我都能记得很清楚。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name7"
								value="是" />√ <input type="radio" name="name7" value="否" />X</td>
						</tr>
						<tr>
							<td style="text-align: left;">8、我喜欢抽象思维的工作，不喜欢动手的工作。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name8"
								value="是" />√ <input type="radio" name="name8" value="否" />X</td>
						</tr>
						<tr>
							<td style="text-align: left;">9、我喜欢成为人们注意的焦点。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name9"
								value="是" />√ <input type="radio" name="name9" value="否" />X</td>
						</tr>
						<tr>
							<td style="text-align: left;">10、我喜欢不时地夸耀一下自己取得的好成就。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name10"
								value="是" />√ <input type="radio" name="name10" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">11、我曾经渴望有机会参加探险。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name11"
								value="是" />√ <input type="radio" name="name11" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">12、当我一个独处时，会感到更愉快。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name12"
								value="是" />√ <input type="radio" name="name12" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">13、我喜欢在做事情前，对此事情做出细致的安排.</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name13"
								value="是" />√ <input type="radio" name="name13" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">14、我讨厌修理自行车、电器一类的工作。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name14"
								value="是" />√ <input type="radio" name="name14" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">15、我喜欢参加各种各样的聚会。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name15"
								value="是" />√ <input type="radio" name="name15" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">16、我愿意从事虽然工资少、但是比较稳定的职业</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name16"
								value="是" />√ <input type="radio" name="name16" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">17、音乐能使我陶醉。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name17"
								value="是" />√ <input type="radio" name="name17" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">18、我办事很少思前想后。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name18"
								value="是" />√ <input type="radio" name="name18" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">19、我喜欢经常请示上级。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name19"
								value="是" />√ <input type="radio" name="name19" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">20、我喜欢需要运用智力的游戏。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name20"
								value="是" />√ <input type="radio" name="name20" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">21、我很难做那种需要持续集中注意力的工作。</td>

							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name21"
								value="是" />√ <input type="radio" name="name21" value="否" />X
							</td>
							</tr>
						<tr>
							<td style="text-align: left;">22、我喜欢亲自动手制作一些东西，从中得到乐趣。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name22"
								value="是" />√ <input type="radio" name="name22" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">23、我的动手能力很差。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name23"
								value="是" />√ <input type="radio" name="name23" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">24、和不熟悉的人交谈对我来说毫不困难。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name24"
								value="是" />√ <input type="radio" name="name24" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">25、和别人谈判时，我总是很容易放弃自己的观点.</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name25"
								value="是" />√ <input type="radio" name="name25" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">26、我很容易结识同性朋友。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name26"
								value="是" />√ <input type="radio" name="name26" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">27、对于社会问题，我通常持中庸的态度。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name27"
								value="是" />√ <input type="radio" name="name27" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">28、当我开始做一件事情后，即使碰到再多的困难，我也要执著地干下去。
							</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name28"
								value="是" />√ <input type="radio" name="name28" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">29、我是一个沉静而不易动感情的人。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name29"
								value="是" />√ <input type="radio" name="name29" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">30、当我工作时，我喜欢避免干扰。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name30"
								value="是" />√ <input type="radio" name="name30" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">31、我的理想是当一名科学家。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name31"
								value="是" />√ <input type="radio" name="name31" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">32、与言情小说相比，我更喜欢推理小说。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name32"
								value="是" />√ <input type="radio" name="name32" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">33、有些人太霸道，有时明明知道他们是对的，也要和他们对着干。
							</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name33"
								value="是" />√ <input type="radio" name="name33" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">34、我爱幻想。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name34"
								value="是" />√ <input type="radio" name="name34" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">35、我总是主动地向别人提出自己的建议。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name35"
								value="是" />√ <input type="radio" name="name35" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">36、我喜欢使用榔头一类的工具。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name36"
								value="是" />√ <input type="radio" name="name36" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">37、我乐于解除别人的痛苦。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name37"
								value="是" />√ <input type="radio" name="name37" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">38、我更喜欢自己下了赌注的比赛或游戏。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name38"
								value="是" />√ <input type="radio" name="name38" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">39、我喜欢按部就班地完成要做的工作。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name39"
								value="是" />√ <input type="radio" name="name39" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">40、我希望能经常换不同的工作来做。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name40"
								value="是" />√ <input type="radio" name="name40" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">41、我总留有充裕的时间去赴约会。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name41"
								value="是" />√ <input type="radio" name="name41" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">42、我喜欢阅读自然科学方面的书籍和杂志。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name42"
								value="是" />√ <input type="radio" name="name42" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">43、如果掌握一门手艺并能以此为生，我会感到非常满意。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name43"
								value="是" />√ <input type="radio" name="name43" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">44、我曾渴望当一名汽车司机。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name44"
								value="是" />√ <input type="radio" name="name44" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">45、听别人谈“家中被盗”一类的事，很难引起我的同情。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name45"
								value="是" />√ <input type="radio" name="name45" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">46、如果待遇相同，我宁愿当商品推销员，而不愿当图书管理员。
							</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name46"
								value="是" />√ <input type="radio" name="name46" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">47、我讨厌跟各类机械打交道。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name47"
								value="是" />√ <input type="radio" name="name47" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">48、我小时候经常把玩具拆开，把里面看个究竟。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name48"
								value="是" />√ <input type="radio" name="name48" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">49、当接受新任务后，我喜欢以自己的独特方法去完成它。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name49"
								value="是" />√ <input type="radio" name="name49" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">50、我有文艺方面的天赋。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name50"
								value="是" />√ <input type="radio" name="name50" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">51、我喜欢把一切安排得整整齐齐、井井有条。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name51"
								value="是" />√ <input type="radio" name="name51" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">52、我喜欢作一名教师。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name52"
								value="是" />√ <input type="radio" name="name52" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">53、和一群人在一起的时候，我总想不出恰当的话来说。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name53"
								value="是" />√ <input type="radio" name="name53" value="否" />X
							</td>
							</tr>
						<tr>
							<td style="text-align: left;">54、看情感影片时，我常禁不住眼圈红润。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name54"
								value="是" />√ <input type="radio" name="name54" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">55、我讨厌学数学。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name55"
								value="是" />√ <input type="radio" name="name55" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">56、在实验室里独自做实验会令我寂寞难耐。</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name56"
								value="是" />√ <input type="radio" name="name56" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">57、对于急躁、爱发脾气的人，我仍能以礼相待。
							</td>
							<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name57"
								value="是" />√ <input type="radio" name="name57" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">58、遇到难解答的问题时，我常常放弃。
							</td>
						<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name58"
								value="是" />√ <input type="radio" name="name58" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">59、大家公认我是一名勤劳踏实的、愿为大家服务的人。
							</td>
						<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name59"
								value="是" />√ <input type="radio" name="name59" value="否" />X
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">60、我喜欢在人事部门工作。
							</td>
						<td><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name60"
								value="是" />√ <input type="radio" name="name60" value="否" />X
							</td>
						</tr>
				
						<tr>
							<td colspan="2"><p style="text-align: left; font-size: 16px; font-weight: bold">二、哪一个答案最能贴切的描绘你一般的感受或行为？</p>
									</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">61、当你要外出一整天，你会 （ ）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name61"
								value="J" /> A 计划你要做什么和在什么时候做 <input type="radio"
								dataType="Group" msg="必须选定一个答案"  name="name61" value="p" />B 说去就去</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">62、你认为自己是一个 （ ）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name62"
								value="P" /> A 较为随兴所至的人 <input type="radio" name="name62"
								value="J" />B 较为有条理的人</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">63、假如你是一位老师，你会选教（ ） 
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name63"
								value="S" /> A 以事实为主的课程 <input type="radio" name="name63"
								value="N" />B 涉及理论的课程</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">64、你通常（ ）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name64"
								value="E" /> A 与人容易混熟 <input type="radio" name="name64"
								value="I" />B 比较沉静或矜持</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">65、一般来说，你和哪些人比较合得来？（ ）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name65"
								value="N" /> A 富于想象力的人 <input type="radio" name="name65"
								value="S" />B 现实的人</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">66、你是否经常让（ ）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name66"
								value="F" /> A 你的情感支配你的理智 <input type="radio" dataType="Group" msg="必须选定一个答案" 
								name="name66" value="T" />B 你的理智主宰你的情感</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">67、处理许多事情上，你会喜欢（ ）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name67"
								value="P" /> A 凭兴所至行事 <input type="radio" name="name67"
								value="J" />B 按照计划行事</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">
								68、你是否（ ）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name68"
								value="E" /> A 容易让人了解 <input type="radio" name="name68"
								value="I" />B 难于让人了解</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">
								69、按照程序表做事（ ）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name69"
								value="J" /> A 合你心意 <input type="radio" name="name69" value="P" />B
								令你感到束缚</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">
								70、当你有一份特别的任务，你会喜欢（ ）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name70"
								value="J" /> A 开始前小心组织计划 <input type="radio" name="name70"
								value="P" />B 边做边找须做什么</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">
								71、在大多数情况下，你会选择（）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name71"
								value="P" /> A 顺其自然， <input type="radio" name="name71"
								value="J" />B 按程序表做事</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">
								72、大多数人会说你是一个（）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name72"
								value="I" /> A 重视自我隐私的人 <input type="radio" name="name72"
								value="E" />B 非常坦率开放的人</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">
								73、你宁愿被人认为是一个（）
							</td>
						</tr>
						<tr>
							<td  colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name73"
								value="S" /> A 实事求是的人 <input type="radio" name="name73"
								value="N" />B 机灵的人</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">
								74、在一大群人当中，通常是（）
							</td>
						</tr>
						<tr>
							<td  colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name74"
								value="E" /> A 你介绍大家认识， <input type="radio" name="name74"
								value="I" />B 别人介绍你</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">
								75、你会跟哪些人做朋友？（）
							</td>
						</tr>
						<tr>
							<td  colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name75"
								value="N" /> A 常提出新注意的， <input type="radio" name="name75"
								value="S" />B 脚踏实地的</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2"> 
								76、你倾向（）
							</td>
						</tr>
						<tr>
							<td  colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name76"
								value="F" /> A 重视感情多于逻辑， <input type="radio" name="name76"
								value="T" />B 重视逻辑多于感情</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">
								77、你比较喜欢（）
							</td>
						</tr>
						<tr>
							<td  colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name77"
								value="P" /> A 坐观事情发展才作计划， <input type="radio"
								dataType="Group" msg="必须选定一个答案"  name="name77" value="J" />B 很早就作计划</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">
								78、你喜欢花很多的时间（）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name78"
								value="I" /> A 一个人独处， <input type="radio" name="name78"
								value="E" />B 合别人在一起</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">
								79、与很多人一起会（）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name79"
								value="E" /> A 令你活力培增， <input type="radio" name="name79"
								value="I" />B 常常令你心力憔悴</td>
						</tr>
						<tr>
							<td style="text-align: left;"colspan="2">
								80、你比较喜欢（）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name80"
								value="J" /> A 很早便把约会、社交聚集等事情安排妥当， <input type="radio"
								dataType="Group" msg="必须选定一个答案"  name="name80" value="P" />B 无拘无束，看当时有什么好玩就做什么</td>
						</tr>
						<tr>
							<td style="text-align: left;"colspan="2">
								81、计划一个旅程时，你较喜欢（）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name81"
								value="P" /> A 大部分的时间都是跟当天的感觉行事， <input type="radio"
								dataType="Group" msg="必须选定一个答案"  name="name81" value="J" />B 事先知道大部分的日子会做什么</td>
						</tr>
						<tr>
							<td style="text-align: left;"colspan="2">
								82、在社交聚会中，你（）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name82"
								value="I" /> A 有时感到郁闷， <input type="radio" name="name82"
								value="E" />B 常常乐在其中</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">
								83、你通常（）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name83"
								value="E" /> A 和别人容易混熟， <input type="radio" name="name83"
								value="I" />B 趋向自处一隅</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">
								84、哪些人会更吸引你?（）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name84"
								value="N" /> A 一个思想敏捷及非常聪颖的人 <input type="radio"
								dataType="Group" msg="必须选定一个答案"  name="name84" value="S" />B 实事求是，具丰富常识的人</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">
								85、在日常工作中，你会（）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name85"
								value="P" /> A 颇为喜欢处理迫使你分秒必争的突发 <input type="radio"
								dataType="Group" msg="必须选定一个答案"  name="name85" value="J" />B 通常预先计划，以免要在压力下工作</td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">
								86、你认为别人一般（）
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="radio" dataType="Group" msg="必须选定一个答案"  name="name86"
								value="I" /> A 要花很长时间才认识你 <input type="radio" dataType="Group" msg="必须选定一个答案" 
								name="name86" value="E" />B 用很短的时间便认识你</td>
						</tr>
						<tr>
							
							<tr>
								<td><h3>三、在下列每一对词语中，哪一个词语更合你心意？请仔细想想这些词语的意义，而不要理会他们的字形或读音。</h3>
								</td>
							</tr>
							<tr >
								<td style="text-align: left;" colspan="2">87 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name87" value="I" /> A 注重隐私 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name87" value="E" />B 坦率开放</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">88 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name88" value="J" /> A 预先安排的 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name88" value="P" />B 无计划的</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">89 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name89" value="N" /> A 抽象 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name89" value="S" />B 具体</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">90 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name90" value="F" /> A 温柔 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name90" value="T" />B 坚定</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">91 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name91" value="T" /> A 思考 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name91" value="F" />B 感受</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">92 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name92" value="S" /> A 事实 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name92" value="N" />B 意念</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">93 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name93" value="P" /> A 冲动 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name93" value="J" />B 决定</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">94 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name94" value="E" /> A 热衷 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name94" value="I" />B 文静</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">95 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name95" value="I" /> A 文静 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name95" value="E" />B 外向</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">96 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name96" value="J" /> A 有系统 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name96" value="P" />B 随意</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">97 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name97" value="N" /> A 理论 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name97" value="S" />B 肯定</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">98 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name98" value="F" /> A 敏感 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name98" value="T" />B 公正</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">99 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name99" value="T" /> A 令人信服 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name99" value="F" />B 感人的</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">100 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name100" value="S" /> A 声明 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name100" value="N" />B 概念</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">101 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name101" value="P" /> A 不受约束 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name101" value="J" />B 预先安排</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">102 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name102" value="I" /> A 矜持 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name102" value="E" />B 健谈</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">103 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name103" value="J" /> A 有条不紊 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name103" value="P" />B 不拘小节</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">104 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name104" value="N" /> A 意念 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name104" value="S" />B 实况</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">105 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name105" value="F" /> A 同情怜悯 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name105" value="T" />B 远见</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">106 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name106" value="T" /> A 利益 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name106" value="F" />B 祝福</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">107 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name107" value="S" /> A 务实的 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name107" value="N" />B 理论的</td>
							</tr>
							<tr>
								<td colspan="2">108 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name108" value="I" /> A 朋友不多 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name108" value="E" />B 朋友众多</td>
							</tr>
							<tr>
								<td colspan="2">109 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name109" value="F" /> A 有系统 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name109" value="P" />B 即兴</td>
							</tr>
							<tr>
								<td colspan="2">110 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name110" value="N" /> A 富想象的 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name110" value="S" />B 以事论事</td>
							</tr>
							<tr>
								<td colspan="2">111 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name111" value="F" /> A 亲切的 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name111" value="T" />B 客观的</td>
							</tr>
							<tr>
								<td colspan="2">112 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name112" value="T" /> A 客观的 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name112" value="F" />B 热情的</td>
							</tr>
							<tr>
								<td colspan="2">113 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name113" value="S" /> A 建造 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name113" value="N" />B 发明</td>
							</tr>
							<tr>
								<td colspan="2">114 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name114" value="I" /> A 文静 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name114" value="E" />B 爱合群</td>
							</tr>
							<tr>
								<td colspan="2">115 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name115" value="N" /> A 理论 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name115" value="S" />B 事实</td>
							</tr>
							<tr>
								<td colspan="2">116 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name116" value="F" /> A 富同情 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name116" value="T" />B 合逻辑</td>
							</tr>
							<tr>
								<td colspan="2">117 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name117" value="T" /> A 具分析力 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name117" value="F" />B 多愁善感</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">118 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name118" value="S" /> A 合情合理 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name118" value="N" />B 令人着迷</td>
							</tr>
							<tr>
								<td>
									<p style="text-align: left; font-size: 16px;">四、哪一个答案最能贴切地描绘你一般的感受或行为</p>
									</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">119.当你要在一个星期内完成一个大项目，你在开始的时候会 <input
									type="radio" dataType="Group" msg="必须选定一个答案"  name="name119" value="J" /> A
									把要做的不同工作依次列出 <input type="radio" name="name119" value="P" />B
									马上动工</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">120.在社交场合中，你经常会感到 <input type="radio"
									name="name120" dataType="Group" msg="必须选定一个答案"  value="I" /> A
									与某些人很难打开话匣儿和保持对话，或是 <input type="radio" name="name120"
									value="E" />B 与多数人都能从容地长谈</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">121.要做许多人也做的事，你比较喜欢 <input type="radio"
									name="name121" dataType="Group" msg="必须选定一个答案"  value="S" /> A 按照一般认可的方法去做， <input
									type="radio" name="name121" value="N" />B 构想一个自己的想法</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">122.你刚认识的朋友能否说出你的兴趣？ <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name122" value="E" /> A 马上可以 <input
									type="radio" name="name122" value="I" />B 要待他们真正了解你之后才可以</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">123.你通常较喜欢的科目是 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name123" value="N" /> A 讲授概念和原则的 <input
									type="radio" dataType="Group" msg="必须选定一个答案"  name="name123" value="S" />B
									讲授事实和数据的</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">124.哪个是较高的赞誉，或称许为？ <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name124" value="F" /> A 一贯感性的人 <input
									type="radio" name="name124" value="T" />B 一贯理性的人</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">125.你认为按照程序表做事 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name125" value="P" /> A
									有时是需要的，但一般来说你不大喜欢这样做，或是 <input type="radio" name="name125"
									value="J" />B 大多数情况下是有帮助而且是你喜欢做的</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">126.和一群人在一起，你通常会选 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name126" value="I" /> A 跟你很熟悉的个别人谈话 <input
									type="radio" name="name126" value="E" />B 参与大伙的谈话</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">127.社交聚会上，你会 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name127" value="E" /> A 是说话很多的一个 <input
									type="radio" dataType="Group" msg="必须选定一个答案"  name="name127" value="I" />B
									让别人多说话</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">128.把周末期间要完成的事列成清单，这个主意会 <input type="radio"
									name="name128" value="J" /> A 合你意 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name128" value="P" />B 使你提不起劲</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">129.哪个是较高的赞誉，或称许为 <input type="radio"
									name="name129" value="T" /> A 能干的 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name129" value="F" />B 富有同情心</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">130.你通常喜欢 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name130" value="J" /> A 事先安排你的社交约会 <input
									type="radio" dataType="Group" msg="必须选定一个答案"  name="name130" value="P" />B
									随兴之所至做事</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">131.总的说来，要做一个大型作业时，你会选 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name131" value="P" /> A 边做边想该做什么 <input
									type="radio" name="name131" value="J" />B 首先把工作按步细分</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">132.你能否滔滔不绝地与人聊天 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name132" value="I" /> A 只限于跟你有共同兴趣的人 <input
									type="radio" dataType="Group" msg="必须选定一个答案"  name="name132" value="E" />B
									几乎跟任何人都可以</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">133.你会 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name133" value="N" /> A 跟随一些证明有效的方法，或是 <input
									type="radio" dataType="Group" msg="必须选定一个答案"  name="name133" value="S" />B
									分析还有什么毛病，及针对尚未解决的难题</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">134.为乐趣而阅读时，你会 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name134" value="S" /> A 喜欢奇特或创新的表达方式 <input
									type="radio" dataType="Group" msg="必须选定一个答案"  name="name134" value="N" />B
									喜欢作者直话直说</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">135.你宁愿替哪一类上司（或者老师）工作？ <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name135" value="F" /> A 天性淳良，但常常前后不一的
									<input type="radio" name="name135" value="T" />B 言词尖锐但永远合乎逻辑的</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">136.你做事多数是 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name136" value="P" /> A 按当天心情去做 <input
									type="radio" dataType="Group" msg="必须选定一个答案"  name="name136" value="J" />B
									照拟好的程序表去做</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">137.你是否 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name137" value="E" /> A 可以和任何人按需求从容地交谈，或是 <input
									type="radio" dataType="Group" msg="必须选定一个答案"  name="name137" value="I" />B
									只是对某些人或在某种情况下才可以畅所欲言</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="2">138.要作决定时，你认为比较重要的是 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name138" value="T" /> A 据事实衡量 <input
									type="radio" name="name138" value="F" />B 考虑他人的感受和意见</td>
							</tr>
							<tr>
								<td>
							<h3>五、在下列每一对词语中，哪一个词语更合你心意？</h3>
									</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">139 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name139" value="N" /> A 想象的 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name139" value="S" />B 真实的</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">140 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name140" value="F" /> A 仁慈慷慨的 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name140" value="T" />B 意志坚定的</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">141 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name141" value="T" /> A 公正的 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name141" value="F" />B 有关怀心</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">142 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name142" value="S" /> A 制作 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name142" value="N" />B 设计</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">143 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name143" value="N" /> A 可能性 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name143" value="S" />B 必然性</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">144 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name144" value="F" /> A 温柔 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name144" value="T" />B 力量</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">145 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name145" value="T" /> A 实际 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name145" value="F" />B 多愁善感</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">146 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name146" value="S" /> A 制造 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name146" value="N" />B 创造</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">147 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name147" value="N" /> A 新颖的 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name147" value="S" />B 已知的</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">148 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name148" value="F" /> A 同情 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name148" value="T" />B 分析</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">149 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name149" value="T" /> A 坚持己见 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name149" value="F" />B 温柔有爱心</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">150 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name150" value="S" /> A 具体的 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name150" value="N" />B 抽象的</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">151 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name151" value="F" /> A 全心投入 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name151" value="T" />B 有决心的</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">152 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name152" value="T" /> A 能干 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name152" value="F" />B 仁慈</td>
							</tr>
							<tr>
								<td style="text-align: left;"colspan="2">153 <input type="radio" dataType="Group" msg="必须选定一个答案" 
									name="name153" value="S" /> A 实际 <input type="radio"
									dataType="Group" msg="必须选定一个答案"  name="name153" value="N" />B 创新</td>
							</tr>
					</table>
					<a href="javascript:void(0);" class="btn" onclick="checkValidate();">提交答卷</a>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../Main/cpglFooter.jsp"%>
</body>
</html>