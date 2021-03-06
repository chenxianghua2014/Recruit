﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/highcharts/highcharts.js"></script>
<script type="text/javascript" src="js/ShowCharts.js"></script>
<style type="text/css">
.highcharts-legend {
	display: none;
}
.cpbt {text-align: center; font-size:28px;font-weight:bold; line-height: 2em;}
.MBTIAnalyse{font-size:16px; text-align: left; line-height: 2em;}
.MBTIAnalyse span {font-size:20px; text-align: left;font-weight:bold; }
.MBTIAnalyse b {font-size:16px; text-align: left;font-weight:bold; }
</style>
</head>
<body>
	<div class="editBlock">
		<div class="cpbt">岗位匹配测评分析报告</div>
		<div id="content" style="display: none; height: 100px;"></div>
		<div id="container" style="height:300px; width: 98%;"></div>
		<div id="showExplain" style="font-size:20px; text-align: left;"></div>
		<div id="gxcsENFJ" class="MBTIAnalyse" style="display: none;">
			<span>第一部分：基本人格分析</span><br/>
			<b>总体评价：</b>谆谆善诱地引导他人<br/>
			
			<b>性格优点：</b><br/>
			•  善于社交、善劝服，精力旺盛，热情洋溢，能很快理解他人情感的需要、动机和所忧虑的事情，因此能做到与他人高度协调，既可以做有号召力的领袖，也可以做忠实的追随者<br/>
			•  性情平和，心胸宽阔，且很圆滑，很会促进周边关系的和睦，对于批评和紧张特别敏感<br/>
			•  容易看出他人的发展潜力，并倾力帮助他人发挥潜力，是体贴的助人为乐者，你愿意组织大家参与活动，使大家和睦又感到愉快<br/>
			•  理想主义者，非常看重自己的价值，对自己尊重景仰的人、事业和公司都非常忠诚。有责任感、谨慎、坚持不懈，同时对新观点很好奇<br/>
			
			<b>性格缺点：</b><br/>
			•  理想化，认为世界是自己想象中的那样，不愿意接受与此相抵触的事情，经常忽略理想所需要的现实和细节问题<br/>
			•	依照情感行事，很少用逻辑，主要根据个人的价值观进行判断，无视行为所带来的后果<br/>
			•	有很高的热情，急于迎接新的挑战，有时会做出错误的假设或过于草率的决定<br/>
			•	总想得到表扬，希望自己的才能和贡献得到赏识，对于批评非常脆弱，容易忧虑，感到内疚，失去自信，当压力很大时，会变得暴躁，慌乱、吹毛求疵<br/>
			<span>第二部分：职业倾向分析</span><br/>
			<b>工作中的优势：</b><br/>
			•	具有优秀的交流及表达能力，善于与别人感情交融，建立合作关系<br/>
			•	对自己所信仰的事业尽职尽责，有冲劲和闯劲，鞭策自己做出成绩，达到目的<br/>
			•	有条理，有组织和领导能力，能够促进和谐，尊重不同意见<br/>
			•	兴趣广泛，头脑灵活，渴望推陈出新<br/>
			
			<b>工作中的劣势：</b><br/>
			•	不愿意干与自己价值观相冲突的事情<br/>
			•	易于满足小范围管理，决不放弃控制权<br/>
			•	不愿与别人产生分歧或冲突，容易把人际关系理想化，很难在竞争强、气氛紧张的环境下工作，逃避矛盾冲突，疏忽不愉快的事情<br/>
			•	在没有收集足够证据前，易于仓促决定，容易因轻率犯错误<br/>
			
			
			<b>适合的岗位特征：</b><br/>
			•	工作环境轻松，可以接触各种各样的人，并与他们建立和维护亲密、互助的关系 <br/>
			•	工作中可以接触到新观念，探究新方法，尤其那些可以帮助他人改善的方法<br/>
			•	有相当的自主权，并承担一定的责任，充分发挥组织和决策能力 <br/>
			•	可以创造性地解决问题，做出的贡献能够得到别人的赏识和鼓励 <br/>
			•	工作有较强的变化性，挑战性，允许自身有条不紊地进行规划<br/>
			•	工作允许同时掌控多个项目，但不要过多处理常规和细致的部分<br/>
		</div>
		<div id="gxcsENFP" class="MBTIAnalyse"style="display: none;">
			<span>第一部分：基本人格分析</span><br/>
			<b>总体评价：</b>善于公关，认为没有不可能的事情<br/>
			<b>性格优点：</b><br/>
			•	对周围的人和事物观察得相当透彻，可以发现事物的深层含义和意义，并能看到他人看不到的事物内在的抽象联系<br/>
			•	崇尚和谐善意、热情、友好、体贴、情绪强烈，需要他人的肯定，也乐于称赞和帮助他人。总是避免矛盾，更在意维护人际关系<br/>
			•	有丰富的想象力，善于创新，自信，富有灵感和新思想，警觉，更注重理解，而不是判断<br/>
			•	喜欢提出计划，并大力将其付诸实施。特别善于替别人发现机会，并有能力且愿意帮助他们采取行动，抓住机会<br/>
			<b>性格缺点：</b><br/>
			•	只要感兴趣，什么都去做。容易忽视现实和事物的逻辑，通常在事情开始阶段或有变化的阶段较为投入，而对后续较为常规或沉闷的部分，难以持续投入<br/>
			•	总是能轻意想出新注意，喜欢着手许多事情，无法专注于一件事情，很少能把事情“从头做到尾”<br/>
			•	总能看到太多的可能性，因此无法确定那些事情是自己真正追求的<br/>
			•	组织纪律性比较弱，不肯服从，无视限制和程序。喜欢即兴发挥，不愿意筹备和计划，对细节没有兴趣。<br/>
			<span>第二部分：职业倾向分析</span><br/>
			<b>工作中的优势：</b><br/>
			•	能够打破常规思考，考虑事情发展可能出现的新情况<br/>
			•	敢于冒险、敢于尝试新事物，能克服障碍，能够在任何真正感兴趣的领域中成功 <br/>
			•	适应能力强，能迅速改变自己的行事速度及目标，兴趣广泛、对自己感兴趣的东西接受能力强 <br/>
			•	能统观全局，能看出行为和思想之间的潜在含义，能洞察别人，交际能力强<br/>
			•	能够把自己的天赋与别人的兴趣和能力集合起来，善于赋予适合的人以合适的职位/任务<br/>
			
			<b>工作中的劣势：</b><br/>
			•	做事不太条理，或不善于分清主次顺序，把握事情的轻重 <br/>
			•   通常不喜欢任何重复或例行的事务，易于烦躁或不耐烦，尤其是当工作中的创造性过程结束后<br/>
			•	不能容忍与过于严谨的机构或个人工作，组织性观念不强<br/>
			•	在工作细节的完成上有一些困难，斗志不足，容易松懈，通常不愿付出过多的努力<br/>
			
			<b>适合的岗位特征：</b><br/>
			•	在人际友好、轻松的环境中与不同特点的人一起工作，避免冲突和矛盾 <br/>
			•	工作充满乐趣，富于挑战，允许自由发挥灵感和创造力，参与冒险 <br/>
			•	可以创造新的想法、产品、服务或帮助别人，然后看到计划变为现实 <br/>
			•	工作环境与的理念、个人价值观一致 <br/>
			•	规则和限制少，能够自己安排工作的进程和节奏 <br/>
			•	工作不要求处理太多的重复性、程序性、常规性、琐碎的事物 <br/>
		</div>
		<div id="gxcsENTJ" class="MBTIAnalyse"style="display: none;">
			<span>第一部分：基本人格分析</span><br/>
			<b>总体评价：</b>喜欢将一切至于掌控之中<br/>
			<b>性格优点：</b><br/>
			•	直率、果断，能够妥善解决组织的问题，是天生的领导者和组织的创建者<br/>	
			•	逻辑性强，善于分析，习惯用批判的眼光看待事物，能很快地在头脑里形成概念和理论，并能把可能性变成计划<br/>
			•	善于系统、全局地分析和解决各种错综复杂的问题，为了达到目的，会制定全盘计划和制度并安排好人和物的来源，推动变革和创新<br/>
			•	愿意接受挑战，并希望其他人能够像自己一样投入，对常规活动不感兴趣。擅长需要论据和机智的谈吐的事情，如公开演讲之类<br/>
			<b>性格缺点：</b><br/>
			•	经常在没有了解细节和形势之前就草率地做决定<br/>
			•	总是很客观、带有批判性地对待生活，容易对别人的情况和需要表现得较粗心、直率、无耐心 <br/>
			•	考虑问题非常理智，很少受无关因素影响。没有时间和兴趣去体会情感，容易忽略他人的感受，显得不尽人情<br/>
			•	容易夸大自己的经验、能力 <br/>
			<span>第二部分：职业倾向分析</span><br/>
			<b>工作中的优势：</b><br/>
			•	自信且有天生的领导才能，对于在工作中胜任有强烈的动机，能逻辑地、分析地做出决定 <br/>
			•	敢于采取大胆行动，有不达目的不罢休的势头 <br/>
			•	能看到事情的可能发展情况及其潜在的含义 <br/>
			•	有创造性解决问题的能力，能客观地审查问题 <br/>
			•	雄心勃勃，工作勤奋，诚实而直率，工作原则强<br/>
			<b>工作中的劣势：</b><br/>
			•	爱发号施令、挑剔、严厉，对那些反应不如敏捷的人缺乏耐心 <br/>
			•	对一些世俗的小事没有兴趣，对那些既定问题不愿再审查 <br/>
			•	工作至上而忽视工作的其他方面，不愿花时间适当地欣赏、夸奖同事或别人 <br/>
			•	可能因急于做出决定而忽视有关的事实和重要细节 <br/>
			•	可能不要求或不允许别人提供建议和帮助<br/>
			<b>适合的岗位特征：</b><br/>
			•	有组织、有条理的工作环境，在清晰而明确的指导原则下与他人一起工作 <br/>
			•	充满挑战和竞争的氛围，创造性处理复杂而且难度较大的问题，提出合乎逻辑的解决办法 <br/>
			•	领导、管理、组织和完善一个机构的运行体系，确保有效运转并达到计划目标 <br/>
			•	能够提高并展示个人能力，能够不断得到提升，有机会接触到各种有能力而且有权力的人 <br/>
			•	成果能够得到他人肯定，并得到合理的回报 <br/>
			•	能够确立工作目标，并施展组织才能，管理监督他人，而不需要处理人际冲突 <br/>
		</div>
		<div id="gxcsENTP" class="MBTIAnalyse"style="display: none;">
			<span>第一部分：基本人格分析</span><br/>
			<b>总体评价：</b>智多星，总有一些新点子<br/>
			<b>性格优点：</b><br/>
			•	好奇心强，喜欢新鲜事物，有很强的创造性和主动性，多才多艺，很善于处理挑战性的问题<br/>
			•	善于快速抓住事物本质，喜欢从新角度思考，机警而坦率，有杰出的分析能力，是优秀的策略家<br/>
			•	不喜欢条条框框的限制和守旧的工作方式，认为“计划赶不上变化”，规定应该是富有弹性的<br/>
			•	善于理解他人，善于鼓舞他人，能用自己的热情感染他人<br/>
			<b>性格缺点：</b><br/>
			
			•	总是充满热情的寻找新鲜事物，但行事缺少稳定的计划和流程，经常依靠临场发挥，容易忽略简单、常规的方法和一些重要的细节<br/>
			•	注意力容易转移，对目标的韧性和坚持性不够，一旦主要问题被解决，就会转移到下一个目标<br/>
			•	通常同时展开多项任务与活动，不愿丢掉任何一种可能性，可能使别人的计划安排受到影响<br/>
			•	有天生的直觉和预知能力，会使误认为知道了别人的想法而表现出不耐烦<br/>
			
			<span>第二部分：职业倾向分析</span><br/>
			<b>工作中的优势：</b><br/>
			•	出色的交际才能、能使别人对自己的观点感到兴奋，不会感到拘谨，能舒适地适应多数社交场合 <br/>
			•	具有探险精神、创新意识以及克服困难的勇气，自信，只要想做，什么都能做到 <br/>
			•	能灵活地适应新情况，有熟练的变换能力，可以快速搜集所需信息，擅长创新和客观公正的分析 <br/>
			•	能够把握事情的全局，弄清思想和行为的长远影响，并能同时处理多个问题  <br/>
			<b>工作中的劣势：</b><br/>
			•	过于自信，可能会不恰当地运用自己的能力和社会经历 <br/>
			•	很可能不切实际地许诺，可能会是不可靠的、不负责任的 <br/>
			•	当创造性的问题解决后，便对项目失去兴趣，对待细节和后续工作可能缺乏耐心，对自己要求不    严格，不能做具体细节工作，不能贯彻始终<br/>
			•	对事物容易感到厌烦，并且可能在不恰当的时候把注意力转移到别的事情上去<br/>
			<b>适合的岗位特征：</b><br/>
			•	工作能够充分发挥的创造性和开拓性，能够不断提高自己的能力，并能得到承认和鼓励 <br/>
			•	在快速成长、变化的环境中工作，从事挑战性较大的任务 <br/>
			•	工作能让体验到乐趣、活跃和兴奋，不要做重复的、繁琐的、简单的细节工作，有一定的弹性，  较为灵活，能够自由的、不受各种死板制度限制地工作<br/>
			•	能够结识不同的人，与有能力的人或自己尊重的人交往，并开展有意义的合作<br/>
		</div>
		<div id="gxcsESFJ" class="MBTIAnalyse"style="display: none;">
			<span>第一部分：基本人格分析</span><br/>
			<b>总体评价：</b>热情主动地帮别人把事情做好<br/>
			<b>性格优点：</b><br/>
			•	非常重视与别人的关系，易觉察出他人的需要，待人友好、善解人意并有很强的责任心<br/>
			•	很实际、有条理，做事彻底，有一致性，对细节和事实有出色的记忆力，喜欢在经验和事实之上做出决策，将事情安排妥当，喜欢自己成为活跃而有用的人物<br/>
			•	很好地适应日常的常规工作和活动，不喜欢做需要掌握抽象观点或客观分析的工作<br/>
			•	喜欢安全和稳定的环境，注重并很好地遵守社会约定规范支持现存制度<br/>
			•	忠于自己的职责，愿意超出自己的责任范围而做一些对别人有帮助的事情，在遇到困难和取得成功时，都很积极活跃，希望付出能得到回报或赞扬<br/>
			<b>性格缺点：</b><br/>
			•	过分在意别人的情感和想法，以至于总是给予别人额外的关心和帮助，有时态度强硬，容易侵占别人的空间，当遇到冲突时，通常采取回避或是妥协的方式，而非积极的、正面的处理<br/>
			•	敏感，做事总是希望得到别人的鼓励和赞赏，担心被忽视，不愿接受批评，很可能变得沮丧<br/>
			•	容易陷入细节中，很难从问题中跳出来更宏观、更客观的对待 <br/>
			•	通常很难变通，拒绝尝试新方法，习惯根据经验做出决定，以至于信息不足造成决策的草率<br/>
			<span>第二部分：职业倾向分析</span><br/>
			<b>工作中的优势：</b><br/>
			•	有很大的精力和动力完成任务、创造成果，不论工作还是消遣时间，都愿意为团体尽力<br/>
			•	能够有效地和别人协作，并且和他人建立起友好和睦的人际关系 <br/>
			•	处理事实和细节问题时，能够记住并利用各种事实，具有客观的态度和得天独厚的天资才能 <br/>
			•	责任意识强，果断坚决、稳重可靠，工作勤奋认真，富有效率<br/>
			•	乐意遵循已制订的例行公事和工作程序，能够维护组织一向的价值观念和工作原则 <br/>
			
			<b>工作中的劣势：</b><br/>
			•	不愿意尝试、接受新的和未经考验的观点和想法，难于适应新情况，在不同的工作任务之间来回切换有时会困难 <br/>
			•	没有得到表扬和欣赏的时候可能会变得失望、泄气，对于别人的异议和批评耿耿于怀 <br/>
			•	容易表现得过于敏感，逃避难堪的场合，不喜欢在紧张的气氛中工作 <br/>
			•	只关注具体的细节之处，而不能整体把握一个情况或者事物的长远影响 <br/>
			
			<b>适合的岗位特征：</b><br/>
			•	在友好的环境中工作，与他人充分合作并能协调一致，能够感受到大家的赞赏和支持，并可以把同事当作朋友<br/>
			•	工作制度完善，内容要求明确且易于理解，能有固定的、清晰的评价标准 <br/>
			•	工作成果能够给人们带来实际的帮助，能够运用的细致和计划性<br/>
			•	能够让组织安排并督促自己和他人的工作，以确保事情尽可能顺利、有效的进行<br/>
			•	能够与别人建立温暖、坦诚的关系，通过有形或无形的方式帮助他人提高生活质量<br/>
			•	做常规的项目或工作，有一定的控制权，没有太强的压力和应变要求 <br/>


		</div>
		<div id="gxcsESFP" class="MBTIAnalyse"style="display: none;">
			<span>第一部分：基本人格分析</span><br/>
			<b>总体评价：</b>表演者型,有我在就有笑声<br/>
			<b>性格优点：</b><br/>
			•	对人和新的经历都感兴趣，善于观察，看重眼前事物；更多地从做事的过程中学到东西，而不是研究或读书。<br/>
			•	热爱生活，适应性强且随遇而安，爱热闹，热情、友好，表现欲强烈，有魅力和说服力，灵活、随和，很好相处。<br/>
			•	通常很少事先做什么计划，讨厌框框，自信能够遇事随机应变，当机立断。<br/>
			•	善于处理人际关系，经常扮演和事老的角色，圆滑得体，通常可以让别人接受自己的建议，不喜欢将自己的意愿强加别人。<br/>
			<b>性格缺点：</b><br/>
			•	对各种事情都好奇，以致总是分心，工作受到干扰。做事容易拖拉，难以约束自己，显得不是那么尽职尽责。 <br/>
			•	因为积极活跃的个性，总是忙碌于具体的事务中，并无暇去制订计划，致使面临应急和变化时会不知所措。 <br/>
			•	经常忽视理论思考和逻辑分析，做决定时习惯于相信自己的感觉，或凭一时兴趣、冲动，有时不考虑结果。 <br/>
			<span>第二部分：职业倾向分析</span><br/>
			<b>工作中的优势：</b><br/>
			•	工作时精力充沛和充满活力，是具有协作精神的团队队员<br/>
			•	实际却有丰富的常识 <br/>
			•	忠实于关心的人和组织 <br/>
			•	可望合作，以真实准确的方法帮助他人 <br/>
			•	柔韧性和愿意冒险，尝试新事物，对迅速发生的改变和转变适应良好 <br/>
			
			<b>工作中的劣势：</b><br/>
			•	难以独自工作，尤其是持续一段的工作 <br/>
			•	以表面价值接受事物，容易错失进一步暗示的倾向 <br/>
			•	不喜欢提前准备，在组织时间上有问题<br/>
			•	抵制确立长期目标，经常难以达到最后期限 <br/>
			•	不喜欢过多的条条框框和官僚作风 <br/>
			•	如果涉及到个人感情，就难以做出有逻辑的决定 <br/>
			<b>适合的岗位特征：</b><br/>
			•	能够不断地从实际经验中学习<br/>
			•	能够促进大家的合作，充分动员他人的能力和热情，熟练处理人际关系和争执冲突，消除紧张气氛 <br/>
			•	工作能让体验到快乐和惊喜，能有自我发挥的空间，少受层级结构、规则和条条框框的限制 <br/>
			•	可以直接和客户打交道，能深入参与和实践，而不愿意排除在外 <br/>
			•	能够应对突发或处理紧迫的事情，并考虑周边人的需求 <br/>
					
		</div>
		<div id="gxcsESTJ" class="MBTIAnalyse"style="display: none;">
			<span>第一部分：基本人格分析</span><br/>
			<b>总体评价：</b>掌控当下，让各种事务有条不紊地进行<br/>
			<b>性格优点：</b><br/>
			•	喜欢推进事情并掌控局势，敏锐，对细节有出色的记忆力，善于组织，有计划、有条理、喜欢把事情安排的井井有条，按照计划和步骤行事<br/>
			•	做事速度快，讲求效率，有责任感，善于自我约束，能够尽职尽责地完成工作<br/>
			•	是一个有极强的逻辑性、喜欢做决定的人，做事客观、善于分析，而且有很强的推理能力<br/>
			•	性格外向，为人友好、直爽，处事讲求原则，通常是坚定的、可以信赖的伙伴<br/>
			<b>性格缺点：</b><br/>
			•	看问题有很强的批判性，注意力更多关注存在的问题，通常不能对别人的贡献表示赞赏和肯定<br/>
			•	喜欢把自己的标准强加给别人，对自己和他人都要求严格，通常被周围的人看成“独裁者”<br/>
			•	遵照逻辑和客观的原则做事，较少考虑自己的行为和决定给他人带来的影响<br/>
			•	专注于实施自己细致的计划，容易错过外界的很多变化和信息，甚至难以停下来听一听别人的意见，忽视了许多发展的可能性及事物潜在的关联关系 <br/>
			<span>第二部分：职业倾向分析</span><br/>
			<b>工作中的优势：</b><br/>
			•	能强有力地承担自己的义务；必要的时候能够快刀斩乱麻、意志坚定 <br/>
			•	非常务实，对既定目标坚韧不拔，能够自始至终地关注着公司（或组织）的目标 <br/>
			•	办事精确、很少出差错，有要把工作做好的强烈愿望 <br/>
			•	能有很好地遵循已经建立起的工作安排和工作程序的习惯 <br/>
			•	能够敏感地察觉出不合逻辑、不连贯、不现实以及不称职的人或事 <br/>
			
			<b>工作中的劣势：</b><br/>
			•	不愿意尝试、接受新的和未经考验的观点和想法 <br/>
			•	不能忍受没有效率的工作，或需花很多时间才能完成的程序或工作 <br/>
			•	只考虑眼前需要而不顾长远利益，对当前不存在的可能性没有兴趣 <br/>
			•	有为了实现自己的利益而无视别人的需要的趋向<br/>
			•	追求目标时总想凌驾于别人之上，不喜欢相反的意见，可能频繁地打断别人<br/>
			
			<b>适合的岗位特征：</b><br/>
			•	喜欢在稳定、讲求规范的环境中工作，要求有明确的前景和清晰的等级制度 <br/>
			•	工作氛围友好，与勤奋有责任心的同事一起工作 <br/>
			•	工作能够发挥的逻辑、推理、计划、控制和组织能力 <br/>
			•	工作任务要明确、具体、有可操作性和实际结果 <br/>
			•	工作允许自己进行决策，并负责组织管理，能给一定的控制权，承担较大责任 <br/>
		</div>
		<div id="gxcsESTP" class="MBTIAnalyse"style="display: none;">
			<span>第一部分：基本人格分析</span><br/>
			<b>总体评价：</b>不间断地尝试新的挑战<br/>
			
			<b>性格优点：</b><br/>
			•	敏锐，善于看出眼前的需要，并迅速做出反应，想出容易的办法去解决难办的事情，以此使自己的工作变得愉快 <br/>
			•	天生的乐天派，积极活跃，随遇而安，乐于享受今天<br/>
			•	好奇心强，思路开阔，容易接受事物，喜欢探求新方法，倾向于通过逻辑分析和推理做出决定，不会感情用事<br/>
			•	如果形势需要，会表现出坚韧的意志力。偏爱灵活地处理实际情况，而不是根据计划办事 <br/>
			•	性格外向，友好而迷人，很受欢迎，并且能在大多数社会情况中很放松自如<br/>
			
			<b>性格缺点：</b><br/>
			•	由于关注外界各种变化信息，喜欢处理紧急情况，不愿意制订规划去预防紧急情况的发生<br/>
			•	常一次着手许多事情，超出自己的负荷，不能履行诺言，可能使周围的人陷入混乱<br/>
			•	注意力完全集中在有趣的活动上，喜欢不断地接受新的挑战，不愿意在目前沉闷的工作中消磨时间，难以估计自己行为带来的结果 <br/>
			•	当情况环境转变时，很容易忽视他人的情感，变得迟钝和鲁莽<br/>
			
			
			<span>第二部分：职业倾向分析</span><br/>
			<b>工作中的优势：</b><br/>
			•	具有敏锐的观察力，对实际讯息具有出色的记忆力 <br/>
			•	明白该做什么，明白现实的对待完成工作的必要条件 <br/>
			•	精力充沛，工作时充满活力 <br/>
			•	具有随机应变的能力，适应力强，愿意冒险和尝试新事物 <br/>
			•	具有实际、现实的观察力和丰富的常识 <br/>
			•	逐步上升的方式，在工作中创造生动有趣的气氛 <br/>
			
			<b>工作中的劣势：</b><br/>
			•	对别人的感觉迟钝、麻木，或对别人的感觉过于疏忽 <br/>
			•	缺乏耐心和无法忍受行政细节和手续 <br/>
			•	很难作决定和优先考虑计划 <br/>
			•	易冲动，易受诱惑或迷惑 <br/>
			•	不喜欢过多的规矩和条条框框的官僚作风 <br/>
			
			<b>适合的岗位特征：</b><br/>
			•	在一个没有太多的规则约束的环境中工作，完成自己的任务后可以享受自由的时间 <br/>
			•	工作可以使发挥“救火”的能力，利用直接的经验，寻找解决问题的最佳方案 <br/>
			•	在工作中，接触真实的人和事务，进行有形产品的制造或服务<br/>
			•	工作充满挑战，允许用冒险的方式处理紧急情况 <br/>
			•	工作能发挥敏锐的观察力、理解力以及对事实的记忆力 <br/>
			•	工作能让结识各种不同的人，并能与讲究实际、充满活力的同事坦诚相处 <br/>
		</div>
		<div id="gxcsINFJ" class="MBTIAnalyse"style="display: none;">
			<span>第一部分：基本人格分析</span><br/>
			<b>总体评价：</b>基于博爱的理想，设身处地的关怀他人<br/>
			
			<b>性格优点：</b><br/>
			•	有计划、有条理，喜欢遵照固有的模式处理问题，在同一时间内只专注一件事情<br/>
			•	忠诚、有责任心，喜欢解决问题，通常在认真思考之后行动<br/>
			•	有敏锐的洞察力，有坚定的原则，就算被别人怀疑，也相信自己的决定，依靠坚韧不拔取得成功<br/>
			•	重感情，忠于自我价值观，有强烈的愿望为大家做贡献<br/>
			
			<b>性格缺点：</b><br/>
			
			•	完美和固执，易走极端。一旦决定后，拒绝改变，并抵制那些与的价值相冲突的想法<br/>
			•	总是探寻事情的意义和价值，专注各种想法，会显得不切实际，经常会忽视一些常规的细节<br/>
			•	敏感，非常关注个人的感受和他人的反应，对批评很介意，甚至会视为人身攻击 <br/>
			
			
			<span>第二部分：职业倾向分析</span><br/>
			<b>工作中的优势：</b><br/>
			•	对于那些认为很重要的项目专注且执着，对自己信仰的事业尽职尽责，有不达目的不罢休的干劲 <br/>
			•	坚决果断，有说服力的领导，并有高度的组织能力 <br/>
			•	有创造力，能提出独树一帜的解决问题的办法 <br/>
			•	与别人感情交融，能预见别人的需要，对别人真正关心，愿意帮助别人成长和发展 <br/>
			•	独立，有很强的个人信念  <br/>
			
			<b>工作中的劣势：</b><br/>
			•	不够灵活，思维单一，有时过分的专心致志，结果可能导致死板 <br/>
			•	很难做与自己价值观相冲突的事 <br/>
			•	对计划的可行性有不切实际的倾向 <br/>
			•	易于仓促下判断，且一旦做出决定不愿再回头过来审视一下，更不愿撤销决定 <br/>
			•	不会处理矛盾，易于忽略不快<br/>
			
			<b>适合的岗位特征：</b><br/>
			•	工作符合的个人价值观和信念，能让在人格上和职业上都保持诚实正直的品质 <br/>
			•	工作环境友好、没有紧张的关系，的努力能得到别人的精神支持，想法能得到重视<br/>
			•	工作最好能提供创立新颖的观点和方法的空间，有计划的解决工作中出现的各种问题 <br/>
			•	最好能一对一的开展工作，实施自己的想法，为别人的提供帮助或服务，促进别人的成长和发展 <br/>
			•	能够独立的工作，并自主安排自己的时间及环境，对自己的工作进程和工作成果较大的支配权<br/>

		
		</div>
		<div id="gxcsINFP" class="MBTIAnalyse"style="display: none;">
			<span>第一部分：基本人格分析</span><br/>
			<b>总体评价：</b>生活在自己的理想世界<br/>
			
			<b>性格优点：</b><br/>
			•	非常善良，有同情心，善解人意，也比较敏感，非常崇尚内心的平和，看重个人的价值<br/>
			•	有独创性、有个性，好奇心强，思路开阔，有容忍力<br/>
			•	一旦全身心地投入一项工作时，往往发挥出冲刺式的干劲，全神贯注，全力以赴<br/>
			•	对人、事和思想信仰负责，一般能够忠实履行自己的义务。但对于意义不大的日常工作，做起来可能有些困难<br/>
			
			<b>性格缺点：</b><br/>
			
			•	经常忽略逻辑思考和具体现实，沉浸于梦想。当意识到理想与现实之间的差距，就容易灰心丧气<br/>
			•	非常固执，经常局限在自己的想法里，对外界的客观具体事物没有兴趣<br/>
			•	总是用高标准要求自己，投入太多感情，导致对批评相当敏感，可能会变得吹毛求疵 <br/>
			
			
			<span>第二部分：职业倾向分析</span><br/>
			<b>工作中的优势：</b><br/>
			•	忠于职守，考虑周到细致且能集中注意力深入某个问题或观点 <br/>
			•	渴望打破常规思考，并考虑新的可能情况 <br/>
			•	擅长独立工作，能与尊敬的人保持频繁、有意义的支持性交流关系 <br/>
			•	对收集所需信息有一种天生的好奇与技巧 <br/>
			•	适应能力强，能很快改变的行事速度及目标  <br/>
			
			<b>工作中的劣势：</b><br/>
			•	必须控制方案或计划，否则可能会失去兴趣 <br/>
			•	有变得无秩序性的倾向，很难把握优先处理的事 <br/>
			•	不愿做与自己价值观相冲突的工作 <br/>
			•	很难在竞争的、气氛紧张的环境中工作下去 <br/>
			•	在处理及完成重要细节问题上缺乏纪律或原则性<br/>
			
			<b>适合的岗位特征：</b><br/>
			•	在一个注重合作、没有压力和人际冲突的环境中，与其他富有创造性和同情心的同事一起工作 <br/>
			•	在一个没有太多限制、灵活的机构中工作，有私人的工作空间和足够的时间 <br/>
			•	工作能够符合个人的价值观，能够帮助他人成长和发展，挖掘他人的潜力 <br/>
			•	工作允许深入地与他人沟通和合作，理解、帮助、激励他人，并有机会接触到尊敬的人 <br/>
			•	在工作中，可以发挥的创造力，并能得到鼓励和嘉奖，自己的能力不断得到提升 <br/>
			•	给足够的时间，深化的想法，并为实现它们而坚定地工作 <br/>

		
		</div>
		<div id="gxcsINTJ" class="MBTIAnalyse"style="display: none;">
				<span>第一部分：基本人格分析</span><br/>
				<b>总体评价：</b>专家型，追求能力与独立<br/>
				
				<b>性格优点：</b><br/>
				•	考虑问题理智、清晰、简洁，不受他人影响，客观的批判一切，运用理性思维做出判断<br/>
				•	不屈从于权威，聪明，有判断力，对自己要求严格，甚至也这样去要求别人<br/>
				•	有很强的自制力，以自己的方式做事，不会被别人的批评干扰，是所有性格中最独立的 <br/>
				•	是优秀的策略家和富有远见的规划者，能够很快把情况的有利与不利方面看的很清楚<br/>
				•	具有独特的、创造性的观点，喜欢来自多方面的挑战，在感兴趣的领域里，会投入令人难以置信的精力、专心和动力 <br/>
				
				<b>性格缺点：</b><br/>
				
				•	过于注重远见卓识，很容易忽略和错过与自己理论模式不符的细节和现象<br/>
				•	过分独立的个性和工作习惯，使得总是“拒绝”别人的参与和帮助，难以发现自己计划中的缺<br/>
				•	有时会过于固执和死板，沉迷于一些出色的但不重要的想法中，并且事事要求完美<br/>
				•	只注重自己，很少去理解他人，自以为是，总是想当然的把自己的观点强加给别人，制定不切实际的高标准 <br/>
				
				<span>第二部分：职业倾向分析</span><br/>
				<b>工作中的优势：</b><br/>
				•	标准高、工作原则性强 <br/>
				•	能很好适应一个人单独工作、独立、自主 <br/>
				•	擅长于从事技术性工作，擅长理论和技术分析以及逻辑的解决问题 <br/>
				•	富于想象，善于创造体系，有创造性解决问题的能力，能客观地审查问题 <br/>
				•	对于在工作中胜任和胜出有强烈的动机，即使在面对阻挠时也会义无返顾地去实现目标<br/>
				
				<b>工作中的劣势：</b><br/>
				•	对一些世俗的小事没有兴趣 <br/>
				•	对自己的观点顽固地坚持 <br/>
				•	对一些工作所要的“社会细节”没有耐心 <br/>
				•	可能因太过于独立而不能适应合作的环境<br/>
				•	完成创造性的问题解决之后可能会对项目丧失兴趣 <br/>
				
				<b>适合的岗位特征：</b><br/>
				•	能够发挥独创性，提出解决问题的独到办法，并控制它们的执行<br/>
				•	可以致力于实现的美好想法，以合乎逻辑而且有序的方式进行工作<br/>
				•	工作不要求反复执行那些实际的、常规的和以细节为核心的任务<br/>
				•	工作有高度的自主和控制力，权力不断增加，可以通过自己的努力，改变或推进事情的进展 <br/>
				•	在运行顺利和谐，对人际没有过多要求的环境中，独立的开展工作，最好能够和聪明或有能力的人进行交流 <br/>


		
		</div>
		<div id="gxcsINTP" class="MBTIAnalyse"style="display: none;">
				<span>第一部分：基本人格分析</span> <br/>
				<b>总体评价：</b>聪颖机智地解决问题 <br/>
				
				<b>性格优点：</b> <br/>
				•	极其聪慧，有逻辑性，对找到创造性解决问题的办法更感兴趣 <br/>
				•	非常独立，有批判性和怀疑精神，深藏不露，内心通常在投入地思考问题，对一个观点或形势能做出超乎超于常人的、独立准确的分析 <br/>
				•	更善于处理概念和想法，而不是与人打交道 <br/>
				•	能宽容很多不同的行为，只是在自己认为应该的时候才争论和提出问题，但是如果基本原则受到挑战，就不在保持灵活性而以原则办事 <br/>
				
				<b>性格缺点：</b> <br/>
				
				•	如果没有机会运用自己的才能，或得不到赏识，会感到沮丧，消极的批判 <br/>
				•	过于注重逻辑分析，只要不合逻辑，即使再重要，也很有可能放弃  <br/>
				•	过分理智，忽视情感和现实，察觉不到他人的需要，也不考虑自己的观点对他人的影响 <br/>
				•	对解决问题着迷，极善于发现想法中的缺陷，却很难把它们表达出来，对常规的细节没有耐心 <br/>
				
				<span>第二部分：职业倾向分析</span> <br/>
				<b>工作中的优势：</b> <br/>
				•	独立自主，能独自工作，并且全神贯注  <br/>
				•	搜集所需用信息时表现出理智的好奇心、独特的洞悉力，能够综合考虑和运用大量的信息  <br/>
				•	喜欢能够学到新知识、掌握新技能的环境，学习新知识的信心和动力都很大  <br/>
				•	能够客观地分析和处理问题，不感情用事，即使在压力很大的情况下也能逻辑地分析事物  <br/>
				•	能够理解非常复杂和高度抽象的概念，创造性地解决问题，具有探险精神、创造意识以及克服困难的勇气 <br/>
				
				<b>工作中的劣势：</b> <br/>
				•	过于自信，可能会不恰当地运用自己的能力和社会经历  <br/>
				•	做事容易丧失兴趣，主要问题一旦解决，兴趣便不复存在，不能实施并贯彻到底  <br/>
				•	对程式化的事情和固执的人缺乏耐心  <br/>
				•	思想、观点对别人来说过于复杂、难以理解  <br/>
				•	对别人的情感、批评和要求反映迟钝  <br/>
				
				<b>适合的岗位特征：</b> <br/>
				•	工作可以去挑战复杂的问题，可以尝试一些别出心裁的方法，并为找到更好的结果去冒险  <br/>
				•	工作可以不断提高自己的能力和权力，与那些有才华的人们一起工作  <br/>
				•	把精力投入到有创造性的、富于理论逻辑的过程，而不是最后的结果  <br/>
				•	不需要花时间组织或管理其他人，调节人际关系  <br/>
				•	工作环境灵活宽松，没有过多的限制、规则和烦琐的会议 <br/>
				•	独立工作，有大量的不受打扰的时间，有较多的深入需要深入思考的事情  <br/>
		
		</div>
		<div id="gxcsISFJ" class="MBTIAnalyse"style="display: none;">
			<span>第一部分：基本人格分析</span><br/>
			<b>总体评价：</b>值得信赖和依靠<br/>
			
			<b>性格优点：</b><br/>
			•	务实、实事求是，追求具体和明确的事情，喜欢做实际的考虑<br/>
			•	善于单独思考、收集和考察丰富的外在信息。不喜欢逻辑的思考和理论的应用，拥有对细节很强的记忆力<br/>
			•	与人交往时较为敏感，谦逊而少言、善良、有同情心，喜欢关心他人并提供实际的帮助，但通常不愿意将个人情感表现出来<br/>
			•	做事有很强的原则性，尊重约定，维护传统。工作时严谨而有条理，愿意承担责任，依据明晰的评估和收集的信息来做决定，充分发挥自己客观的判断和敏锐的洞察力<br/>
			 
			
			<b>性格缺点：</b><br/>
			•	有高度的责任心，会陷入日常事务的细节中去，每件事情都会从头做到尾 <br/>
			•	有时容易忽略事情的全局和发展变化趋势，难以预见存在的可能性<br/>
			•	总是替别人着想，以至于让人感觉“关心过度”。在工作中，过多的承受和忍耐，不太习惯表达，却将情绪在家庭和生活中发泄出来<br/>
			•	不停地制订计划并保证完成，以至于经常花费更多的时间和投入更多的精力来完成工作 <br/>
			
			
			
			
			<span>第二部分：职业倾向分析</span><br/>
			<b>工作中的优势：</b><br/>
			•	十分关注细节，能够准确地把握事实 <br/>
			•	大多数具有出色的组织才能，非常强的责任意识，别人可以信任<br/>
			•	愿意在传统的机构中工作，而且兢兢业业、不遗余力，能够连续地工作，对相同的工作任务不会感到厌倦<br/>
			•	强烈的工作热情，认真负责，工作努力，喜欢运用固定的办事程序 <br/>
			•	良好的协作技巧，能和别人建立起和谐友好的关系，尊重别人的地位和能力 <br/>
			
			<b>工作中的劣势：</b><br/>
			•	可能会低估自己的能力，难于坚决地维护自己的需要和利益 <br/>
			•	对反对意见过于敏感；在紧张的工作环境里感到很受压抑 <br/>
			•	如果自己得不到充分的重视和赞赏，可能会感到灰心丧气 <br/>
			•	不愿意尝试、接受新的和未经考验的观点和想法，难以适应新境况，或者在不同的工作任务之间来回切换时会有困难 <br/>
			
			<b>适合的岗位特征：</b><br/>
			•	在规范、传统、稳定的环境下工作，可以给他人提供服务或帮助 <br/>
			•	适合在责任清晰，有一定私人空间、人际关系和谐的氛围中工作 <br/>
			•	要求细致、精确，能够发挥出色的观察力和对细节的关注能力 <br/>
			•	工作能够让集中精力，关注一件事情或一个人，而不是平行开展多项工作 <br/>
			•	通过工作，能够得到同事和上级的认可、欣赏和鼓励 <br/>
			•	按照标准化的工作流程和规范开展工作，不要在事先没有准备的情况下把的工作展示给别人 <br/>
					
		</div>
		<div id="gxcsISFP" class="MBTIAnalyse"style="display: none;">
			<span>第一部分：基本人格分析</span><br/>
			<b>总体评价：</b>用有形的作品展示丰富内心世界<br/>
			
			<b>性格优点：</b><br/>
			•	和蔼、友善、敏感，谦虚地看待自己的能力，能平静愉悦地享受目前的生活，喜欢自由自在地安排自己的活动<br/>
			•	善于观察、务实、讲求实际，了解现实和周围的人，并且能够灵活地对他们的情况和需要做出反应，但很少寻求其动机和含义，是优秀的短期规划者<br/>
			•	有耐心，易通融，很好相处。没有领导别人的愿望，往往是忠实的跟随者和很好的合作伙伴 <br/>
			•	很有艺术天分，内心深沉，其实很热情，不太喜欢表现<br/>
			
			<b>性格缺点：</b><br/>
			
			•	完全着眼于现在，不能为将来做打算，也不能很好地安排时间和精力 <br/>
			•	天生对他人具有高度的敏感，总是难以拒绝别人，有时为了满足他人的需求而拼命地工作，以至于在此过程中忽视了自己<br/>
			•	过分忽视事物之间的内在联系和逻辑思考，难以理解复杂的事情 <br/>
			•	对他人的批评会感到生气或气馁，有时容易过分自责，容易相信别人，很少对别人的动机有所怀疑，也不会发现别人行为背后的隐含意义<br/>
			
			<span>第二部分：职业倾向分析</span><br/>
			<b>工作中的优势：</b><br/>
			•	热情、慷慨，对自己很关心的人和组织忠诚，主动愿意支持组织的目标 <br/>
			•	考虑周到，注意重要的细节，特别是那些与人相关的<br/>
			•	准确评估目前形势的能力和看出什么是最需要保持稳定的能力 <br/>
			•	仔细评估冒风险和试用新方法时的灵活性和主动性  <br/>
			
			<b>工作中的劣势：</b><br/>
			•	没有能力观察到目前不存在的机会和可以选择的机会 <br/>
			•	不愿提早准备，在利用自己的时间上有问题 <br/>
			•	在与自己的感受相矛盾时很难做出符合逻辑的决定 <br/>
			•	不喜欢过多的规则，不喜欢结构过于复杂的机构，会被大量的极其复杂的任务压的喘不过气<br/>
			
			<b>适合的岗位特征：</b><br/>
			•	在活跃的、合作的环境下工作，最小限度的人际冲突 <br/>
			•	作为对集体忠诚和乐于合作的一员，在彼此积极支持的气氛下工作 <br/>
			•	工作要求关注细节，切实，能够快速处理问题，提供实际帮助 <br/>
			•	有独立工作的自由，周围的人必须和谐有礼貌，工作中没有太多的规则、结构、僵化的程序 <br/>
			•	符合内在价值观和审美情趣 <br/>
			•	工作不要求例行公事的公开讲话，或总是拒绝别人 <br/>
		</div>
		<div id="gxcsISTJ" class="MBTIAnalyse"style="display: none;">
			<span>第一部分：基本人格分析</span> <br/>
			
			<b>总体评价：</b>细致、谨慎地执行好现有规则 <br/>
			
			<b>性格优点：</b><br/>
			•	是一个认真而严谨的人，勤奋而负有责任感，认准的事情很少会改变或气馁，做事深思熟虑，信守承诺并值得信赖<br/>
			•	依靠理智的思考来做决定，总是采取客观、合乎逻辑的步骤，不会感情用事，甚至在遇到危机时都能够表现得平静<br/>
			•	谨慎而传统，重视稳定性、合理性；天生独立，需要把大量的精力倾注到工作中，并希望其它人也是如此，善于聆听并喜欢将事情清晰而条理的安排好<br/>
			•	喜欢先充分收集各种信息，然后根据信息去综合考虑实际的解决方法，而不是运用理论去解决。对细节非常敏感，有很实际的判断力<br/>
			
			<b>性格缺点：</b><br/>
			
			•	固执，一旦决定的事情，会对其他的观点置之不理，并经常沉浸于具体的细节和日常的操作中 <br/>
			•	看问题有很强的批判性，通常持怀疑态度<br/>
			•	非常独立，我行我素，不能理解不合逻辑的事情，并对与风格不同的人不能理解，非常挑剔<br/>
			•	非常有主见，时常会将自己的观点和标准强加给别人，而且无视那些不自信的人的建议 <br/>
			
			
			
			
			<span>第二部分：职业倾向分析</span><br/>
			<b>工作中的优势：</b><br/>
			•	办事精确，工作专心细致，能够独立完成工作<br/>
			•	乐意遵循确定的日常安排和传统的方针政策，是组织忠诚的维护者、支持者<br/>
			•	具有非常强的责任意识<br/>
			•	通情达理、视角现实 、有稳定平和的心态 <br/>
			
			<b>工作中的劣势：</b><br/>
			•	办事死板，不愿意尝试，接受新环境或经验观点，见到实际应用的结果后才肯接受新观点<br/>
			•	难以看到问题的整体以及行为的长远影响 <br/>
			•	不能理解与自己的要求不同的要求 <br/>
			•	在压力和挫折面前不够坚持<br/>
			
			<b>适合的岗位特征：</b><br/>
			•	工作环境稳定，不需要太多的冒险和变动，最好依照经验和规律解决事情 <br/>
			•	有较多的独立工作时间，可以专心的完成整个项目或任务 <br/>
			•	较多使用事实、细节和运用实际经验的技术性工作，能够发挥自己精细、准确、逻辑性强的才能<br/>
			•	工作对象是具体的产品或服务，工作成果要有形并且可以衡量 <br/>
			•	要有明确的工作目标和清晰的组织结构层次 <br/>
			•	逐渐增加工作中的责任，承担更多的任务，尽可能少的安排社会活动 <br/>
			•	工作有足够的准备和实施时间，在交付成果之前能够进行自我成就评估<br/>

		</div>
		<div id="gxcsISTP" class="MBTIAnalyse"style="display: none;">
			<span>第一部分：基本人格分析</span><br/>
			
			<b>总体评价：</b>平静地思考着，但间或的行为往往出人意料，喜欢冒险<br/>
			
			<b>性格优点：</b><br/>
			•	通常是安静或沉默的，喜欢行动而非言语，时常被认为不太愿意接近人<br/>
			•	非常独立，不愿受规则约束，具备很好的迎接挑战和处理问题的能力，通常很喜欢户外活动和运动<br/>
			•	密切关注周围发生的事情，常常充当解决困难的人。一旦需要，会快速反应，抓住问题的核心以最有实效的方式予以解决<br/>
			•	善于思考和分析，喜欢客观独立地作决定，并把一切都清楚直接地安排妥当。对技术工作很有天赋，是使用工具和双手工作的专家<br/>
			
			<b>性格缺点：</b><br/>
			
			•	非常实际，总能找到简捷的解决办法，这使有时会偷工减料，不能完成所有的步骤和细节<br/>
			•	非常喜欢多样化和新奇刺激，对所有的选择都持开放态度，所以不善于做决定<br/>
			•	通常无视自己的情感和需要，忽视他的人感受，对于自己的决定对他人产生的影响不够重视<br/>
			•	总是独立分析，独自判断，不喜欢与别人分享自己的反应、情感和担忧，使得周围的人行动或配合起来比较被动<br/>
			
			
			
			
			<span>第二部分：职业倾向分析</span><br/>
			<b>工作中的优势：</b><br/>
			•	敏锐的观察力和对事实信息的出色记忆力 <br/>
			•	喜欢独立工作或与敬佩的人并肩工作 <br/>
			•	在压力之下面对危机能保持头脑冷静 <br/>
			•	知道完成工作需要做什么和必须做什么 <br/>
			•	实际性和丰富的常识，倾向于用手和工具工作 <br/>
			•	对突然变化和迅速发生的转变适应良好，愿意冒险以及尝试新事物 <br/>
			
			<b>工作中的劣势：</b><br/>
			•	缺乏进行愿与交流的兴趣，尤其是表面上的交谈 <br/>
			•	不喜欢事先准备，你在组织时间上有一定困难 <br/>
			•	难以看到目前不存在的机会和选择 <br/>
			•	对行政上的细节和程序缺乏耐心 <br/>
			•	很强的独立性，不喜欢过多的条条框框、官僚作风 <br/>
			
			<b>适合的岗位特征：</b><br/>
			•	在一个没有过多规则和操作标准要求的环境下工作，可以享受冒险的乐趣，也可以应付危机 <br/>
			•	允许独立工作，而且可以经常做户外活动 <br/>
			•	工作最好能运用技术：机械的技术、运用机械的技术 <br/>
			•	工作具有挑战性，有效的分配活动和能量，不必履行多余的程序和常规 <br/>
			•	尽可能少的监督工作，也不需要过多的去监视他人 <br/>
			•	工作有明确的方向，允许划定和使用对自身而言可以最有效获得的资源 <br/>

		</div>
		<div id="container2" style="height:300px; width: 98%;"></div>
		<div id="gxcsjs" style="font-size:14px; text-align: left;">
			<b style="font-size:14px;">传统型：</b>喜欢传统性质的的职业或环境，避免艺术性质的职业或情境，会以传统的能力解决工作或其他            方面的问题。<br/>
			<b style="font-size:14px;">研究型：</b>用研究的能力解决工作及其他方面的问题，即自觉、好学、自信，重视科学，但缺乏领导方面的才能。<br/>
			<b style="font-size:14px;">现实型：</b>喜爱实用性的职业或情境，以从事所喜好的活动，避免社会性的职业或情境。<br/>
			<b style="font-size:14px;">艺术型：</b>富有表达能力和直觉、独立、具创意、不顺从（包括表演、写作、语言），并重视审美的领域。<br/>
			<b style="font-size:14px;">企业型：</b>喜欢企业性质的的职业或环境，避免研究性质的职业或情境，会以企业方面的能力解决工作或其他方面的问题能力。<br/>
			<b style="font-size:14px;">社会型：</b>喜爱社会型的职业或情境，避免实用性的职业或情境，并以社交方面的能力解决工作及其他方面的问题，但缺乏机械能力与科学能力。<br/>
		</div>
	</div>
</body>
</html>