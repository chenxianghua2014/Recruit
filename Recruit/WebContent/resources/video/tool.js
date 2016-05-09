function includejs(jsfile)
{
	var file = "<script language='javascript' src=\""+jsfile+"\"><\/script>"; 
	document.write(file);
}

//includejs("lang.js");

function GetCookie(name)
{
	var strCookie = document.cookie;
    var arrCookie = strCookie.split("; ");
    for (var i = 0;i < arrCookie.length;i++)
    {
		var arr = arrCookie[i].split("=");
        if (arr[0] == name)
		{
			if(arr.length>1)
			{
				var value = unescape(arr[1]);
				if((value+"")=="undefined")
					value="";
				return value;
			}
			return "";
		}
    }
    return "";
}
function SetCookie(name,value)
{
	var expirehours=24*365;
	var cookieString = name + "=" + escape(value);
    if (expirehours > 0)
    {
		var date = new Date();
        date.setTime(date.getTime() + expirehours * 3600 * 1000);
        cookieString = cookieString + "; expires=" + date.toGMTString();
	}
    document.cookie=cookieString+";path=/";
}
function rmcookie(name)
{
	var expdate = new Date(); 
	Expdate.setTime(expdate.getTime() - (86400 * 1000 * 1)); 
    SetCookie(name, "", expdate);
}
function GetPageParams(szName,szDefaultValue)
{
	var szURL = document.URL;
	var nStart = szURL.indexOf("?");
	if(nStart != -1)
	{
		var szParam=szURL.slice(nStart+1);
		var arrParam = szParam.split("&");
		var i;
		for(i=0;i<arrParam.length;i++)
		{	
			var nPos = arrParam[i].indexOf(szName+"=");
			if(nPos == 0)
			{
				nPos += szName.length ;
				var szValue = arrParam[i].slice(nPos+1);
				return szValue;
			}
		}
	}
	else
	{
	}

	return szDefaultValue;
}
//"xx".len() return bytes of string
String.prototype.len=function()   
{   
  return   this.length   +   escape(this).split("%u").length-1;   
}
String.prototype.trim=function() {return this.replace(/(^\s*)|(\s*$)/g,"");}
String.prototype.ltrim=function() {return this.replace(/(^\s*)/g,"");}
String.prototype.rtrim=function() {return this.replace(/(\s*$)/g,"");}
String.prototype.isInteger=function() {return /^(-|\+)?\d+$/.test(this);}
String.prototype.isPositiveInteger=function() {return /^\d+$/.test(this);}
String.prototype.isNegativeInteger=function() {return /^-\d+$/.test(this);}
String.prototype.IsEmpty=function(){return this.trim().length==0;}

function GetErrorDesc(errCode,szDefaultDesc)
{
	switch(errCode)
	{
	case 1:	return "no find page!";	break;
	case 2:	return "unknown error!";break;
	case 3: return "Invalid user or password";break;
	case 4: return "Invalid or unsupported http command!";break;
	}
	
	return szDefaultDesc;
}
function onHelp()
{
	//help function is optional
	//use /help/xx.asp.htm as help file for /xx.asp
	var szUrl = window.location+"";//make it as string
	var pos = szUrl.lastIndexOf('/');
	var szPath=szUrl.substring(0,pos);
	var szFile=szUrl.substring(pos,szUrl.length);
	var arr=szFile.split(".");
	szFile=arr[0]+"_help";
	var szHelpFile=szPath+"/help"+szFile+".htm";
	location.href=szHelpFile;
}
function CreateHelpBtn()
{
	var bShowHelp=1;
	//bShowHelp=0;
	if(bShowHelp)
	{
		document.write("<input class=btn_mouseout onmouseover=\"this.className='btn_mouseover'\" onmouseout=\"this.className='btn_mouseout'\" style=\"width:100px\" type=\"button\" value=\""+GS("HELP")+"\" onclick=onHelp(); />&nbsp;&nbsp;");
	}
}
function SetItemText(item_id,szText)
{
	SetDlgItemText(item_id,szText);
}
function SetDlgItemText(item_id,szText)
{
	try
	{
		document.getElementById(item_id).value = szText;
	}
	catch(e)
	{
		alert("no find item:"+item_id);
	}
}
function EnableWindow(item_id,enable)
{
	document.getElementById(item_id).disabled = !enable;
}
function GetXmlHttpObject()
{
	if (window.XMLHttpRequest)
	{
		// code for IE7+, Firefox, Chrome, Opera, Safari
		return new XMLHttpRequest();
	}
	if(window.ActiveXObject)
	{
		// code for IE6, IE5
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
	return null;
}
function XmlGetValue(root,nodeName)
{
	if(root==null)
		return null;
	var node = root.getElementsByTagName(nodeName)[0];
	if(node)
	{
		if(!node.hasChildNodes)
		{
			return "";
		}
		var child=node.childNodes[0];
		if(child == undefined)
			return "";
		var szValue = child.nodeValue;
		return szValue;
	}
	return null;
}
function XmlHasProp(root,PropName)
{
	var val = root.getAttribute(PropName);
	return val!=undefined;
}
function XmlGetProp(root,PropName)
{
	try
	{
		var val = root.getAttribute(PropName)
		return val;
	}
	catch(e)
	{
		alert("fail,root="+root+",PropName="+PropName);
	}
}
function XmlGetNode(root,nodeName,idx)
{
	if(idx==undefined)
	{
		idx=0;//default value
	}
	
	var node = root.getElementsByTagName(nodeName)[idx];
	return node;
}
function XmlGetNodeText(node)
{
	if(IsIE())
		return node.text;
	return node.textContent;
}
function XmlGetNodeCount(root,nodeName)
{
	if(root.getElementsByTagName(nodeName)==null)
	{
		alert("no find:"+nodeName);
	}
	var node = root.getElementsByTagName(nodeName).length;
	return node;
}
function GetUserLevelDesc(Level)
{
	if(Level=="0")
		return GS("ADMIN");
	else if(Level=="1")
		return GS("OPERATOR");
	else if(Level=="2")
		return GS("GUEST");
	return "";	
}
function IsIE()
{
	var isIE = (navigator.appName == "Microsoft Internet Explorer");
	return isIE;
}
//判断日期是否有效
//兼容两种方式:2010-02-14或者02-14-2010
//alert("2002-01-31".isDate());
//alert("01-41-2001".isDate());
String.prototype.IsDate = function()
{
	var sz=this.split("-");
	if(sz.length!=3)
		return false;
	if(parseInt(sz[0])<1970 && parseInt(sz[2])<1970)
		return false;
	var year=parseInt(sz[0],10);
	var month=parseInt(sz[1],10);
	var day=parseInt(sz[2],10);
	if(parseInt(year)<1970)
	{
		month=parseInt(sz[0],10);
		day=parseInt(sz[1],10);
		year = parseInt(sz[2],10);
	}
	var d = new Date(year, month-1, day);
	return (d.getFullYear()==year&&(d.getMonth()+1)==month&&d.getDate()==day);
}
String.prototype.IsTime = function()
{
	var sz=this.split(":");
	if(sz.length!=3)
		return false;
		
	var hour=parseInt(sz[0]);
	var minute=parseInt(sz[1]);
	var second=parseInt(sz[2]);
	if(hour>=0 && hour<24 && minute>=0 && minute<60 && second>=0 && second<60)
		return true;
	return false;	
}
function GetDdnsStatus(Status)
{
	//要与ddns.h中的enum eDdnsStatus保持一致
	var arr= new Array(
	GS("INIT"),
	GS("PROCESSING"),
	GS("DISABLED"),
	GS("INVALID_PARAM"),
	GS("CONNECT_FAIL"),
	GS("GET_WAN_IP_FAIL"),
	GS("BAD_AUTH"),
	GS("NO_HOST"),
	GS("FAIL"),
	GS("SUCCESS")
	);
	return arr[Status];
}
function GetUpnpStatus(Status)
{
	//要与upnp.h中的enum eUpnpStatus保持一致
	var arr= new Array(
	GS("INIT"),
	GS("PROCESSING"),
	GS("DISABLED"),
	GS("INIT_FAIL"),
	GS("FAIL"),
	GS("GET_LAN_IP_FAIL"),
	GS("SUCCESS")
	);
	return arr[Status];
}
function GetDateString(oDate)
{
	var year=oDate.getYear();
	var month = oDate.getMonth() + 1;
	if (month <= 9)
		month = "0" + month
	
	var day = oDate.getDate();
	if (day <= 9)
		day = "0" + day;
	
	var hour=oDate.getHours();
	if(hour<=9)
		hour="0"+hour;
	
	var minute=oDate.getMinutes();
	if(minute<=9)
		minute="0"+minute;

	var second=oDate.getSeconds();
	if(second<=9)
		second="0"+second;
	var sDate = year+"-"+month + "-" + day+" "+hour+":"+minute+":"+second;
	return sDate;
}
var g_IDT_DelayHideStatus=-1;
function SetAjaxStatus(sz,timeout)
{
	ajaxResult.innerHTML="<font color=yellow>&nbsp;("+sz+")</font>";
	
	if(g_IDT_DelayHideStatus!=-1)
	{
		clearTimeout(g_IDT_DelayHideStatus);
		g_IDT_DelayHideStatus=-1;
	}
	ajaxResult.style.display = '';
	if(timeout==-1)
	{
		//default delay 3 seconds
		timeout=3;
	}
	if(timeout!=0)
	{
		g_IDT_DelayHideStatus=setTimeout("ajaxResult.innerHTML=\"&nbsp;\"", timeout*1000);
	}
}
function GetXml(xmlPath,OnGetXmlOK,OnGetXmlFail)
{
	var xmlHttp = GetXmlHttpObject();
	xmlHttp.onreadystatechange=function()
	{
		if (xmlHttp.readyState==4)
		 {
		 	//alert(xmlHttp.responseText);
		 	var xml=xmlHttp.responseXML;
		 	OnGetXmlHandler(xml,OnGetXmlOK,OnGetXmlFail);
		}
	}
	
	//xmlHttp.setRequestHeader("If-Modified-Since", "0");
    //xmlHttp.open("GET", xmlPath, true);
	xmlHttp.open("GET", xmlPath);
	xmlHttp.setRequestHeader("Access-Control-Allow-Origin", "http://10.0.80.240:81");
	xmlHttp.send(null);
    return false;
}
function OnGetXmlHandler(xml,OnGetXmlOK,OnGetXmlFail)
{
	var Result = xml.getElementsByTagName("Result")[0];
	if(Result!=null)
	{
		var Success = XmlGetValue(Result,"Success");
		if(Success == "1")
		{
			OnGetXmlOK(xml);
		}
		else
		{   
			var ErrorCode=XmlGetValue(Result,"ErrorCode");
			SetAjaxStatus(GetHttpErrorDesc(ErrorCode),0);
			OnGetXmlFail(xml);
		}
	}
	else
	{
		SetAjaxStatus(GS("FAIL_FETCH_DATA"),0);
		OnGetXmlFail(xml);
	}
}
function GetHttpErrorDesc(ErrorCode)
{
	if(ErrorCode=="eHttpError_Invalid_XML_Request")
	{
		return GS("NOT_SUPPORT_FUNCTION");
	}
	else if(ErrorCode=="eHttpError_No_Auth")
	{
		return GS("NO_AUTH");
	}
	
	return GS("UNKNOWN_ERROR");
}
function EnableControl(szName,bEnable)
{
	var objs = document.getElementsByTagName(szName); 
	for(var i=0; i<objs.length; i++)   
	{
		//objs[i].disabled=!bEnable;
		EnableItem(objs[i],bEnable);
	}
}
function InitLangSelection()
{
	var str="";
	for(var i=0;i<g_arrLang.length;i++)
	{
		var lang=g_arrLang[i];
		str+="&nbsp;<a href = \"javascript:set_language('"+lang[0]+"')\">"+lang[1]+"</a>";
	}	
	spanLang.innerHTML=str;
}
//WriteString
function WS(str)
{
	return document.write(GS(str));
}

var g_arrLang=[];
var g_lang_idx=-1;

function GetDefaultLang()
{
	var s="eng";
	try
	{
		if(navigator.appName=='Netscape' || navigator.appName=='Firefox')
		{
			s=navigator.language;
		}
		else
		{
			s=window.navigator.systemLanguage;
		}
	}
	catch(e)
	{
	}
    s=s.toLowerCase();
    if(s=='zh-cn')
	{
		s="chs";
	}
	else if(s=='zh-hk' || s=='zh-tw' || s=='zh-mo')
	{
		s="cht";
	}
	else if(s.indexOf('en')>-1) 
	{
	  s="eng";
	}
	else if(s.indexOf('fr')>-1)
	{
		s="fr";
	}
	else if(s.indexOf('de')>-1)
	{
		s='deutsch';
	}
	else if(s.indexOf('it')>-1)
	{
		s='italian';
	}
	else if(s.indexOf('es')>-1)
	{
		s='spain';
	}
	else if(s.indexOf('ko')>-1)
	{
		s='korea';
	}
	else if(s.indexOf('jis')>-1)
	{
		s='japanese';
	}
	else if(s.indexOf('pl')>-1)
	{
		s='polish';
	}
    else if(s.indexOf('pt')>-1)
	{
		s='portuguese'; 
	}
	else if(s.indexOf('ru')>-1)
	{
		s='russian';
	}
	return s;
}
//GetString
function GS(str)
{
	if(g_lang_idx==-1)
	{
		//make sure all language has the same item count
		{
			var nc=0;
			for(var i=0;i<g_arrLang.length;i++)
			{
				if(nc==0)
				{
					nc=g_arrLang[i].length;
				}
				else if(nc!=g_arrLang[i].length)
				{
					var langDesc=g_arrLang[i][1];
					alert("lang["+langDesc+"] string count is unmatch,missing some string?");
				}
			}
		}
		
		var szLang=GetCookie('language');
		if(szLang=="" || szLang=="undefined")
		{
			szLang=GetDefaultLang();
		}
		for(var i=0;i<g_arrLang.length;i++)
		{
			var langArr=g_arrLang[i];
			if(langArr[0]==szLang)
			{
				g_lang_idx=i;
				break;
			}
		}
		
		if(g_lang_idx==-1)
		{
			g_lang_idx=0;
		}
	}
	
	var arr=g_arrLang[g_lang_idx];
	for(var i=0;i<g_arrstring.length;i++)
	{
		if(str == g_arrstring[i])
		{
			return arr[i];
		}
	}
	alert("no lang:"+str);
	return "";
}

//g_eWndMsg must sync with enum eWndMsg(master)
var g_eWndMsg = 
{ 
	FocusChange:0,
	StatusChange:1,
	AlarmEvent:2,
	VideoEffectChange:3,
	Connected:4,
	StartWorking:5,
	DisConnected:6,
	ExitFullScreen:7,
	LightModeChange:8,
	WindowModeChange:9,
	VideoMirrorChange:10,
	VideoSizeChange:11,
	MotionDetect:12,
	SpeakStatusChange:13,
	RecordStatusChange:14,
	VideoColorChange:15,
	FpsChange:16,
	PtzChange:17,
	VideoMaskChange:18,
	IRLedChange:19,
	PtzStatusChange:20,
	PtzPresetCfgChange:21,
	
	g_eWndMsg_end:100
};

var g_eVideoWndMode=
{
	Selected:1,
	SelectMotionDetect:2,
	VideoMask:4,
	
	g_eVideoWndMode:100	//holdplace
};
//must sync with CVideoWnd::m_dwVideoWndStyle
var g_VideoWndStyle= 
{ 
	VWS_SELECTED:				1,
	VWS_SELECT_MOTION_DETECT:	2,
	VWS_VIDEO_MASK:				4,
	VWS_SHOW_TIME:				8,
	VWS_DISABLE_DBLCLICK:		16,
	g_VideoWndStyle_holdplace:	0
};	
var g_devCaps=
{
	DevCaps_VideoColor:0x0001,
	DevCaps_MultipleLiveStream:0x0002,
	DevCaps_IRLed:0x0004,
	DevCaps_LightMode:0x0008,
	DevCaps_hardwareOsd:0x0010,
	DevCaps_holdplace:0
};

//must sync with enum eRecordType
var g_eRecordType = 
{
	Manual:1,
	MotionDetect:2
};

function GetCurUsr()
{
	var usr = GetPageParams("user","");
	if(usr=="")
	{
		usr=GetCookie("user");
	}
	return usr;
}
function GetCurPwd()
{
	var usr = GetPageParams("password","");
	if(usr=="")
	{
		usr=GetCookie("password");
	}
	return usr;
}

var g_select_color='red';

function SetWeekHourCfg(cfg)
{
	for (var i = 0; i < cfg.length && i < 84; i++)
	{
		var idx_start=i*4;
		var mask = parseInt(cfg.charAt(i),16);
		for(var bit=0;bit<4;bit++)
		{
			var idx=idx_start+bit;
			var obj=document.getElementById("td_"+idx);
			obj.style.backgroundColor=(mask&(1<<(3-bit)))?g_select_color:obj.normalBackColor;
		}
	}
}

function GetWeekHourCfg()
{
	var szMask="";
	var arr=new Array('0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f');
	for(var week=0;week<7;week++)
	{
		for(var hour=0;hour<48;hour+=4)
		{
			var idx=week*48+hour;
			var bit0=(document.getElementById("td_"+(idx+0)).style.backgroundColor==g_select_color)?1:0;
			var bit1=(document.getElementById("td_"+(idx+1)).style.backgroundColor==g_select_color)?1:0;
			var bit2=(document.getElementById("td_"+(idx+2)).style.backgroundColor==g_select_color)?1:0;
			var bit3=(document.getElementById("td_"+(idx+3)).style.backgroundColor==g_select_color)?1:0;
			var value=(bit0<<3) | (bit1<<2)| (bit2<<1)| (bit3<<0);
			szMask+=arr[value];
		}
	}
	return szMask;
}

function CreateWeekHourCtrl()
{
	var idx = 0;
	var szTable=("<table border=\"1\" cellspacing =\"0\" cellpadding=\"0\"  >");

	//hour header
	{
		szTable+=("<tr>");
		szTable+=("<td >&nbsp;</td>");
		for (var hour = 0; hour < 24; hour++)
		{
			var text = hour + "";
			if (hour < 10)
			{
				text = "0" + text;
			}

			szTable+=("<td colspan=2>" + text + "</td>");
		}
		szTable+=("</tr>");
	}
	var week = new Array(GS("WEEK0"),GS("WEEK1"),GS("WEEK2"),GS("WEEK3"),GS("WEEK4"),GS("WEEK5"),GS("WEEK6"));
	for (var i = 0; i < 7; i++)
	{
		szTable+=("<tr>");
		szTable+=("<td >" + week[i] + "</td>");
		for (var hour = 0; hour < 48; hour++)
		{
			var oddHour = ((hour % 4) == 2) || ((hour % 4) == 3);
			var backColor = oddHour ? "#EEEEEE" : "#FFFFFF";
			szTable+=("<td id=td_" + (idx++) + " bgcolor=\"" + backColor + "\" onselectstart=\"return OnSelectStart(event)\" ondragstart=\"OnTdDragStart(event)\" ondragend=\"OnTdDragEnd(event)\" onmousedown=\"OnTdMouseDown(event)\" onmouseup=\"OnTdMouseUp(event)\"  onmousemove=\"OnTdMouseMove(event);\" onstartdrag=\"OnTdDragStart(event)\">&nbsp</td>");

		}
		szTable+=("</tr>");
	}
	szTable+=("</table>");
	
	spanWeekHour.innerHTML=szTable;
	
	{
		for (var i = 0; i < 7; i++)	
		{
			for (var hour = 0; hour < 48; hour++)
			{
				var oddHour = ((hour % 4) == 2) || ((hour % 4) == 3);
				var backColor = oddHour ? "#EEEEEE" : "#FFFFFF";
				var idx=i*48+hour;
				var obj=document.getElementById("td_"+idx);
				obj.normalBackColor=backColor;
			}
		}
	}
	
	EnableWeekHourCtrl(false);
}

var g_start_row=-1;
var g_start_col=-1;
var g_stop_row=-1;
var g_stop_col=-1;
//var g_weekHourEnable=false;
function EnableWeekHourCtrl(enable)
{
	spanWeekHour.disabled=!enable;
	//g_weekHourEnable=enable;
}

function Id2Idx(id)
{
	var szId=id;
	var idx=szId.indexOf('_');
	if(idx>0)
	{
		return szId.substr(idx+1);
	}
	return 0;
}
function OnTdMouseDown(event)
{
	if(spanWeekHour.disabled)
		return;
	event = event? event: window.event;

	//AddLog("onTdMouseDown()"+event.srcElement.id);
	var objSrc = event.srcElement ? event.srcElement:event.target;
	var pos=Id2Idx(objSrc.id);
	g_start_col=pos%48;
	g_start_row=(pos - g_start_col)/48;
	AddLog("start row="+g_start_row+",col="+g_start_col);
}

function min(v1,v2)
{
	return (v1)>(v2)?(v2):(v1);
}

function max(v1,v2)
{
	return (v1)>(v2)?(v1):(v2);
}

function OnTdMouseUp(event)
{
	if(spanWeekHour.disabled)
		return;

	event = event? event: window.event;
	//AddLog("onTdMouseUp()"+event.srcElement.id);
	var objSrc = event.srcElement ? event.srcElement:event.target;
	var pos=Id2Idx(objSrc.id);
	g_stop_col=pos%48;
	g_stop_row=(pos - g_stop_col)/48;
	AddLog("stop  row="+g_stop_row+",col="+g_stop_col);
	
	if(g_start_row==g_stop_row && g_start_col==g_stop_col)
	{
		var row=g_start_row;
		var col=g_start_col;
		var ID="td_"+(row*48+col);
		try
		{
			var obj=document.getElementById(ID);
			if(obj.style.backgroundColor!=g_select_color)
			{
				obj.style.backgroundColor=g_select_color;
			}
			else
			{
				obj.style.backgroundColor=obj.normalBackColor;
			}
		}
		catch(e)
		{
			AddLog("catch");
		}
	}
	else
	{
		UpdateGrid(event);
	}
	g_start_row=-1;
}

function UpdateGrid(event)
{
	if(spanWeekHour.disabled)
		return;
	
	event = event? event: window.event;
	
	var row_min=min(g_start_row,g_stop_row);
	var row_max=max(g_start_row,g_stop_row);
	var col_min=min(g_start_col,g_stop_col);
	var col_max=max(g_start_col,g_stop_col);
	
	var bSelect=true;
	if(chkCancelSelect.checked || event.ctrlKey)
	{
		bSelect=false;
	}
	
	for(var row=row_min;row<=row_max;row++)
	{
		for(var col=col_min;col<=col_max;col++)
		{
			var ID="td_"+(row*48+col);
			try
			{
				var obj=document.getElementById(ID);
				obj.style.backgroundColor=bSelect?g_select_color:obj.normalBackColor;
			}
			catch(e)
			{
				AddLog("catch");	
			}
		}
	}
	
	UpdateMask();
}

function UpdateMask()
{
	//spanMask.innerHTML=GetWeekHourCfg()+"<br>";
	//SetWeekHourCfg(GetWeekHourCfg());
}

function OnTdMouseMove(event)
{
	event = event? event: window.event;
	if(g_start_row!=-1)
	{
		//AddLog("OnTdMouseMove()");
		var objSrc = event.srcElement ? event.srcElement:event.target;
		var pos=Id2Idx(objSrc.id);
		g_stop_col=pos%48;
		g_stop_row=(pos - g_stop_col)/48;
		
		UpdateGrid(event);
		
	}
}
function OnTdDragStart(event)
{
	AddLog("OnTdDragStart()");
}

function OnTdDragEnd(event)
{
	AddLog("OnTdDragEnd()");
}
function OnSelectStart(event)
{
	//AddLog("OnSelectStart()");
	return false;
}
function AddLog(sz)
{
	//spanStatus.innerHTML+=sz;
	//spanStatus.innerHTML+="<br>";
}

//从param中解析name数据
//param格式:
//name0=value0&name1=value1...
function GetParamString(param,name)
{
	var arr = param.split("&");
	var i;
	for(i=0;i<arr.length;i++)
	{	
		var nPos = arr[i].indexOf(name+"=");
		if(nPos == 0)
		{
			nPos += name.length ;
			var szValue = arr[i].slice(nPos+1);
			return szValue;
		}
	}
	
	return "";
}

function GetParamInt(param,name)
{
	var value=0;
	var szValue=GetParamString(param,name);
	if(szValue!="")
	{
		value=parseInt(szValue);
	}
	return value;
}
function GetFileIco(Name,Type)
{
	if(Type=="folder")
	{
		return "ico-folder";
	}
	
	var arr=Name.split(".");
	if(arr.length>=2)
	{
		var ext=arr[arr.length-1];
		if(ext=="mp4")	return "ico-mp4";
		if(ext=="rar")	return "ico-rar";
		if(ext=="jpg" || ext=="jpeg")	return "ico-jpg";
		if(ext=="asf" || ext=="avi")	return "ico-asf";
		if(ext=="exe")	return "ico-exe";
		if(ext=="htm" || ext=="html")	return "ico-htm";
	}
	
	return "ico-default";
}
function SetPercent(percent)
{
	percent=Math.round(percent*100)/100;
	if(percent>=0 && percent<=100)
	{
	 	bar.style.width=percent+"%";
	 	bar.innerHTML = bar.style.width; 
	 	spanBar.style.display="block";
	}
}

function CSlideButton(objName,slideWidth, value, minValue,maxValue, dsTdID)
{
    this.objName = objName;
    this.divInName = objName + "InDiv";
    this.urlButton = "images/slider.gif";
    this.urlBackground = "/images/black.gif";
    this.slideWidth = slideWidth;
    this.minValue = minValue;
	this.maxValue = maxValue;
    this.dsTdID = dsTdID;
    
    this.bMouseDown = false;
    this.buttonOffset = slideWidth*(value-minValue)/(maxValue-minValue);
    //form1.ipcam.DW("value="+value+",max="+maxValue+",min="+minValue+",slideWidth="+slideWidth+",off="+this.buttonOffset);
    this.mouseOffset = 0;
    this.curValue = value;
    
    this.SBDisplay = SBDisplay;
    this.SBSetOffset = SBSetOffset;
    this.SBMouseDown = SBMouseDown;
    this.SBMouseMove = SBMouseMove;
    this.SBMouseUp = SBMouseUp;
    this.SBSetValue = SBSetValue;
    this.disabled=false;
}

function SBDisplay()
{
    var __target_link;
    document.write("<div style=\"position:relative; top:0px; left:0px; width:" + this.slideWidth + "px; height:2px\" >");
    
    document.write("<div name=" + this.divInName + " id=" + this.divInName + " value=" + this.objName + 
                   " style=\"z-index:2; position:relative; top:2px; left:6px; width:7px; height:2px\" " + 
                   "onMouseDown=\"SBMouseDown(this)\" " +
                   "onMouseMove=\"SBMouseMove(this)\" " +
                   "onMouseUp=\"SBMouseUp(this)\" " +
                   " >");
    __target_link = this.urlButton;
    document.write("<img src=\"" + __target_link + "\" />");
    
    document.write("</div>");
    
    document.write("<table border='0' style=\"position:absolute; top:8px; left:0px; width:" + this.slideWidth + "px; height:5px;\" " + 
                   "border=\"0\" " + "cellpadding=\"0\" " + "cellspacing=\"0\" " + 
                   " >");
    document.write("<tr>");
    __target_link = this.urlBackground;
    document.write("<td background=\"" + __target_link + "\" " + 
                   "style=\"background-repeat:repeat-x; background-position:center; width:100%; height:5px\" " + 
                   " >&nbsp;</td>");
    document.write("</tr>");
    document.write("</table>");
    
    document.write("</div>");
    
    this.SBSetOffset(this.buttonOffset);
}

function SBSetValue(value)
{
	if(this.bMouseDown)
	{
		return;
	}
	
	this.buttonOffset = this.slideWidth*(value-this.minValue)/(this.maxValue-this.minValue);
	this.SBSetOffset(this.buttonOffset);
}

function SBSetOffset(buttonOffset)
{
    buttonOffset = buttonOffset < 0 ? 0 : buttonOffset;
    buttonOffset = buttonOffset >  this.slideWidth ? this.slideWidth : buttonOffset;
    eval(this.divInName).style.left = buttonOffset;
    
    var val = buttonOffset * this.maxValue / this.slideWidth;
    val = val < this.minValue ? this.minValue : val;
    val = val > this.maxValue ? this.maxValue : val;
    this.curValue = parseInt(val);
    
    // show current value
    var objTd = document.getElementById(this.dsTdID);
    if (null != objTd)
    {
        objTd.innerText = this.curValue;
    }
}

function SBMouseDown(obj)
{
    if(eval(obj.value).disabled)
    	return;
    	
    eval(obj.value).bMouseDown = true;
    obj.setCapture();
    
    eval(obj.value).buttonOffset = obj.offsetLeft;
    eval(obj.value).mouseOffset = event.clientX;
    //window.status = eval(obj.value).buttonOffset + ":" + eval(obj.value).mouseOffset;
}

function SBMouseMove(obj)
{
    if(eval(obj.value).disabled)
    	return;
    if (false == eval(obj.value).bMouseDown)
    {
        return;
    }
    
    var newLeft = eval(obj.value).buttonOffset + event.clientX - eval(obj.value).mouseOffset;
    
    newLeft = newLeft < 0 ? 0 : newLeft;
    newLeft = newLeft > eval(obj.value).slideWidth ? eval(obj.value).slideWidth : newLeft;
    obj.style.left = newLeft;
    
    obj.releaseCapture();
    
    eval(obj.value).SBSetOffset(obj.offsetLeft);
    SetColor();
    obj.setCapture();
    //window.status = eval(obj.value).buttonOffset + ":" + eval(obj.value).mouseOffset;
}

function SBMouseUp(obj)
{
    if(eval(obj.value).disabled)
    	return;
    eval(obj.value).bMouseDown = false;
    obj.releaseCapture();
    eval(obj.value).SBSetOffset(obj.offsetLeft);
}

function VideoRealChg(objName, slideWidth, value, minValue,maxValue, dsTdID)
{
    this.base = CSlideButton;
    this.base.call(this, objName,slideWidth, value, minValue,maxValue, dsTdID); 
    
    this.SBDisplay = SBDisplayReal;
    this.SBMouseUp = SBMouseUpReal;
}

function SBDisplayReal()
{
	var __target_link;
    document.write("<div style=\"position:relative; top:0px; left:0px; width:" + this.slideWidth + "px; height:1px\" >");
    
		document.write("<div name=" + this.divInName + " id=" + this.divInName + " value=" + this.objName + 
					   " style=\"z-index:2; position:relative; top:5px; left:0px; width:2px; height:2px\" " + 
					   "onMouseDown=\"SBMouseDown(this)\" " +
					   "onMouseMove=\"SBMouseMove(this)\" " +
					   "onMouseUp=\"SBMouseUpReal(this)\" " +
					   " >");
			__target_link = this.urlButton;
			document.write("<img src=\"" + __target_link + "\" />");
		document.write("</div>");
	    
		document.write("<table border='0' style=\"position:absolute; top:0px; left:0px; width:" + (this.slideWidth+9) + "px; height:1px;\" " + 
					   "border=\"0\" " + "cellpadding=\"0\" " + "cellspacing=\"0\" " + 
					   " >");
			document.write("<tr>");
				__target_link = this.urlBackground;
				document.write("<td background=\"" + __target_link + "\" " + 
							   "style=\"background-repeat:repeat-x; background-position:center; width:100%; height:1px\" " + 
							   " >&nbsp;</td>");
			document.write("</tr>");
		document.write("</table>");
    
    document.write("</div>");
    
    this.SBSetOffset(this.buttonOffset);
}

function SBMouseUpReal(obj)
{
    SBMouseUp(obj);
	SetColor();
}

var colorValue = new Array();   // 存储颜色值
var color = new Array();        // 保存颜色值对象的instance  作为全局使用 0:亮度; 1:对比度; 2:饱和度; 3:色度;

var g_chlSave="";
var g_brightSave="";
var g_contrastSave="";
var g_hueSave="";
var g_saturationSave="";
var g_tickSave=0;
//drag slider generate too much calls,so use lazy way
function SetColor()
{
    var chl=form1.ipcam.GetCurSel();
    if(g_chlSave != chl 
    	|| g_brightSave != bright.curValue
    	|| g_contrastSave!=contrast.curValue
    	|| g_hueSave!=hue.curValue
    	|| g_saturationSave!=saturation.curValue
    	)
    {
		var now = new Date();
    	var ticks = now.getTime();
    	now="";
    	if(g_tickSave==0 || ticks-g_tickSave>500)
    	{
			g_chlSave = chl;
	    	g_brightSave = bright.curValue;
	    	g_contrastSave=contrast.curValue;
	    	g_hueSave=hue.curValue;
	    	g_saturationSave=saturation.curValue;
	    	
		   	form1.ipcam.SetVideoColor(g_chlSave,g_brightSave,g_contrastSave,g_hueSave,g_saturationSave);
		   	
			var now = new Date();
	    	var ticks = now.getTime();
	    	now="";
		   	g_tickSave=ticks;
    	}
    }
}

function DefaultColor()
{
    bright.SBSetValue(128);
    contrast.SBSetValue(128);
    saturation.SBSetValue(128);
    hue.SBSetValue(128);
    
    SetColor();
}
function EnableItem(obj,enable)
{
	if(enable==true || enable==1 || enable=="1")
	{
		enable=1;
	}
	else
	{
		enable=0;
	}
	
	var bChangeClass=false;
	if(obj.type=="text" || obj.type=="password")
	{
		bChangeClass=true;
	}
	
	if(bChangeClass)
	{
		obj.readOnly=!enable;
		obj.className=enable?"enable":"disable";
	}
	else
	{
		obj.disabled=!enable;
	}
}
function DisableItem(obj,disable)
{
	EnableItem(obj,!disable);
}
function GetSmartEyeStatus(Status)
{
	//must sycn with enum eSmartEyeStatus(ddns_smarteye.h )
	return GS(Status);
}
//param format:
//"name1=value1\r\n"
//"name2=value1\r\n"
//...
function GetParamStringEx(param,name)
{
	var arr = param.split("\n");//use "\r\n" not work with string extracted from XML,why?
	var i;
	for(i=0;i<arr.length;i++)
	{	
		var nPos = arr[i].indexOf(name+"=");
		if(nPos == 0)
		{
			nPos += name.length ;
			var szValue = arr[i].slice(nPos+1);
			
			var atpos = szValue.indexOf("\r");
			if (atpos > -1) {
				szValue= szValue.substring(0, atpos);
			}
			
			return szValue;
		}
	}
	
	return "";
}
function GetParamIntEx(param,name,defaultValue)
{
	if(defaultValue==undefined)
	{
		defaultValue=0;
	}
	
	var value=defaultValue;
	var szValue=GetParamStringEx(param,name);
	if(szValue!="")
	{
		value=parseInt(szValue);
	}
	return value;
}
function IsParamExist(param,name)
{
	var arr = param.split("\r\n");
	var i;
	for(i=0;i<arr.length;i++)
	{	
		var nPos = arr[i].indexOf(name+"=");
		if(nPos == 0)
		{
			return true;
		}
	}
	return fasle;	
}

function RefreshPtzStatus()
{
	var chl = form1.ipcam.GetCurSel();
	var cmb=cmbPtzPreset;
	if(!form1.ipcam.IsWorking(chl))
	{
		cmb.disabled=1;
		return;
	}
	
	cmb.options.length=0;

	var pi=form1.ipcam.GetPtzStatus(chl);
	var status=GetParamStringEx(pi,"status");
	var presetId=GetParamIntEx(pi,"presetId",-1);
	
	if(status=="ePtzStatus_Reposition")
	{
		cmb.disabled=1;	

		var item=document.createElement("OPTION");
		item.text=GS("PTZ_REPOSITION");
		item.value=-1;
		cmb.options.add(item);
		cmb.value=item.value;
		return;
	}
	
	var cfg=form1.ipcam.GetPtzPresetInfo(chl);
	var support=GetParamIntEx(cfg,"support");
	if(support!=1)
	{
		cmb.disabled=1;
		return;
	}
	//form1.ipcam.DT("presetInfo="+cfg);
	var presetcount=GetParamIntEx(cfg,"presetcount");
	if(status!="ePtzStatus_Moving_Preset" 
		&& status!="ePtzStatus_Preset")
	{
		var item=document.createElement("OPTION");
		item.text=GS("NONE");
		item.value=-1;
		cmb.options.add(item);   
	}
	//form1.ipcam.DT("presetcount="+presetcount+",presetId="+presetId+",status="+status);
	var valueSelect=-1;
	for(var i=1;i<=presetcount;i++)
	{
		var id=GetParamIntEx(cfg,"id_"+i,-1);
		var name=GetParamStringEx(cfg,"name_"+i);
		var x=GetParamIntEx(cfg,"x_"+i);
		var y=GetParamIntEx(cfg,"y_"+i);
		
		if(id>0)
		{
			var item=document.createElement("OPTION");
			item.text=i+":"+name;
			
			if(id==presetId)
			{
				//form1.ipcam.DW("find matching preset,status="+status);
				if(status=="ePtzStatus_Moving_Preset")
				{
					item.text=GS("MOVING_TO")+" "+i+":"+name;
					valueSelect=id;
				}
				else if(status=="ePtzStatus_Preset")
				{
					valueSelect=id;
				}
				//form1.ipcam.DT("valueSelect="+valueSelect);
			}
			item.value=id;
			cmb.options.add(item);   
			
		}
		else
		{
			//item.text=i+":none";
		}
	}
	
	if(valueSelect!=-1)
	{
		cmb.value=valueSelect;
	}

	cmb.disabled=!form1.ipcam.IsAuthAction(chl,"videocfg");
}

function GetMjpgRecordFps()
{
	var defaultFps=30;
	var fps=GetCookie("mjpgRecordFps");
	if(fps==undefined || fps=="")
	{
		return defaultFps;
	}
	
	fps=parseInt(fps);
	if(fps<0 || fps>30)
	{
		fps=defaultFps;
	}

	return fps;
}
function SetMjpgREcordFps(fps)
{
	SetCookie("mjpgRecordFps",fps);	
}
 
function OnWeekHourSelectAll()
{
	var bSelect=true;
	var row_min=0,row_max=6;
	var col_min=0,col_max=47;
	for(var row=row_min;row<=row_max;row++)
	{
		for(var col=col_min;col<=col_max;col++)
		{
			var ID="td_"+(row*48+col);
			try
			{
				var obj=document.getElementById(ID);
				obj.style.backgroundColor=bSelect?g_select_color:obj.normalBackColor;
			}
			catch(e)
			{
				AddLog("catch");	
			}
		}
	}
}
function OnWeekHourInvertSelect()
{
	var row_min=0,row_max=6;
	var col_min=0,col_max=47;
	for(var row=row_min;row<=row_max;row++)
	{
		for(var col=col_min;col<=col_max;col++)
		{
			var ID="td_"+(row*48+col);
			try
			{
				var obj=document.getElementById(ID);
				var bSelect=(obj.style.backgroundColor!=g_select_color);
				obj.style.backgroundColor=bSelect?g_select_color:obj.normalBackColor;
			}
			catch(e)
			{
				AddLog("catch");	
			}
		}
	}
}
