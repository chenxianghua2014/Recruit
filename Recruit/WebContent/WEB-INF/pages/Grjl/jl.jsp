<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
body {
	font-family: SimSun;
}

table.admintable {
    /* font-family:"Trebuchet MS", Arial, Helvetica, sans-serif; */
	/* border: 1px solid #000000; */
	border-collapse: collapse;
	table-layout: fixed;
	word-break: break-strict;
}

table.admintable td,table.admintable th{
  font-size:1em;
  border:1px solid ;
  padding:3px 7px 2px 7px;
}
table.admintable th{
  font-size:1.1em;
  text-align:left;
  padding-top:5px;
  padding-bottom:4px;
  font-weight:bold;
}

table.admintableSp {
    border: 1px solid #000; 
	border-collapse: collapse;
	table-layout: fixed;
	word-break: break-strict;
}

table.admintableSp td,table.admintableSp th{
  font-size:1em;
  border:none ;
  padding:3px 7px 2px 7px;
}

table.admintableSp td.tleft{
   border-right: 1px solid #000; 
}
 


.titleBg {
	background-color: #c6d9f1;
	font-weight: 600;
}

.titleHi {
	height: 70px;
	font-weight: 800;
}

.titleFixHei {
	height: 200px;
}
</style>
</head>
<body>
	<table width="100%">
		<%--<tr>
			<td align="center"><h1>${ mv.model['resume'].resumeName }简历
				</h1></td>
		</tr>
		--%>
		<tr>
			<td align="center">
				<h1>应聘人员登记表</h1>
			</td>
		</tr>
	</table>

	<table width="100%" height="300px" border="1" class="admintable">
		<tr>
			<td width="10%" align="center">姓名</td>
			<td width="20%" align="center">${ mv.model['resume'].resumeName }</td>
			<td width="10%" align="center">性别</td>
			<td width="20%" align="center">${ mv.model['resume'].resumeSex }</td>
			<td width="10%" align="center">出生年月</td>
			<td width="20%" align="center">
				<c:set var="string1" value="${fn:substring(mv.model['resume'].resumeBirthday,0, 7)}" />
				<c:set var="string2" value="${fn:replace(string1,'-', '.')}" />
				${string2}
			</td>
			<td width="10%" rowspan="4" colspan="2" align="center">
				<img src="${ mv.model['resume'].resumePhotos }" width="90" height="100" />
			</td>
		</tr>
		<tr>
		
			<td align="center">民族</td>
			<td align="center">${ mv.model['resume'].resumeItjtjn }</td>
			<td align="center">出生地</td>
			<td align="center">${ mv.model['resume'].resumeRxqhkszcsProvince
				}-${mv.model['resume'].resumeRxqhkszcsCity }</td>
			<td align="center">籍贯</td>
			<td align="center">${ mv.model['resume'].resumeHdzs }-${mv.model['resume'].resumeQtzs }</td>
		</tr>
		<tr>
			<td align="center" rowspan="2">入党时间</td>
			<td align="center" rowspan="2">
				<c:set var="string1" value="${mv.model['resume'].resumeZzmm }" />
				<c:choose>
					<c:when test="${ string1 == '党员'}"> 
						${ mv.model['resume'].resumeQtjn}
					</c:when>
					<c:otherwise> 
						无
					</c:otherwise>
				</c:choose>

			</td>
			<td align="center">学历</td>
			<td align="center">${ mv.model['Jyjl'][0].resumeXl }</td>

			<td align="center" rowspan="2">职称/资格</td>
			<td align="center" rowspan="2" colspan="1">${ mv.model['Gzjl'][0].resumeGzbm }</td>
		</tr>
		<tr>
			<td align="center">学位</td>
			<td align="center">${ mv.model['Jyjl'][0].resumeZyms }</td>
		</tr>
		<tr>
			<td align="center" rowspan="2">参加工作时间</td>
			<td rowspan="2" align="center">${ mv.model['resume'].resumeCjgzsj }</td>
			<td align="center">健康状况</td>
			<td align="center" colspan="2">${ mv.model['resume'].resumeItsxjn }</td>
			<td align="center">外语种类</td>
			<td align="center" colspan="2">${ mv.model['Yynl'][0].resumeYylb }</td>
		</tr>
		<tr>
			<td align="center">户口地</td>
			<td align="center" colspan="2">${ mv.model['resume'].resumeMqszcsProvince
				}-${mv.model['resume'].resumeMqszcsCity }</td>
			<td align="center">外语水平</td>
			<td align="center" colspan="2">${ mv.model['Yynl'][0].resumeYyfs }</td>
		</tr>
		<tr>
			<td align="center">毕业院校</td>
			<td align="center" colspan="3">${ mv.model['Jyjl'][0].resumeXxmc }</td>
			<td align="center">专业</td>
			<td colspan="3" align="center">
				${mv.model['Jyjl'][0].resumeZyl}-${mv.model['Jyjl'][0].resumeZy}</td>
		</tr>
		<tr>
			<td align="center">工作单位</td>
			<td align="center" colspan="3">${ mv.model['Gzjl'][0].resumeGzgs }</td>
			<td align="center">职务</td>
			<td colspan="3" align="center">${ mv.model['Gzjl'][0].resumeGzcs }</td>
		</tr>
		<tr>
			<td align="center" colspan="1">拟应聘岗位1</td>
			<td align="center" colspan="7">${ mv.model['resume'].resumeNypgw1 }</td>
		</tr>
		<c:set var="string1" value="${mv.model['resume'].resumeNypgw2 }" />
		<c:choose>
			<c:when test="${string1 != ''}">
				<tr>
					<td align="center" colspan="1">拟应聘岗位2</td>
					<td align="center" colspan="7">${ mv.model['resume'].resumeNypgw2}</td>
				</tr>
			</c:when>
		</c:choose>

	</table>


	<table width="100%" class="admintableSp"  >

		<tr>
			<td class="tleft" rowspan="${fn:length(mv.model['Jyjl'])+fn:length(mv.model['Gzjl'])}"
				width="35px">简历</td>
			<c:set var="string1" value="${fn:substring(mv.model['Jyjl'][0].resumeJdsj,0, 7)}" />
			<c:set var="string2" value="${fn:replace(string1,'-', '.')}" />
			<c:set var="string3" value="${fn:substring(mv.model['Jyjl'][0].resumeJdsj1,0, 7)}" />
			<c:set var="string4" value="${fn:replace(string3,'-', '.')}" />
			<td align="center">${string2}-${string4}</td>
			<td align="center">${mv.model['Jyjl'][0].resumeXxmc}</td>
			<td align="center">${mv.model['Jyjl'][0].resumeZyl}-${mv.model['Jyjl'][0].resumeZy}</td>
			<td align="center">${mv.model['Jyjl'][0].resumeXl}</td>
		</tr>
		<c:forEach items="${ mv.model['Jyjl']}" begin="1" var="map" varStatus="status">
			<tr height="50px">
				<c:set var="string1" value="${fn:substring(map.resumeJdsj,0, 7)}" />
				<c:set var="string2" value="${fn:replace(string1,'-', '.')}" />
				<c:set var="string3" value="${fn:substring(map.resumeJdsj1,0, 7)}" />
				<c:set var="string4" value="${fn:replace(string3,'-', '.')}" />
				<td align="center">${string2}-${string4}</td>
				<td align="center">${map.resumeXxmc}</td>
				<td align="center">${map.resumeZyl}-${map.resumeZy}</td>
				<td align="center">${map.resumeXl}</td>
			</tr>
		</c:forEach>

		<tr>
			<%-- <td class="tleft" rowspan="${fn:length(mv.model['Gzjl'])}" width="35px" >工作经历</td> --%>
			<c:set var="string1" value="${fn:substring(mv.model['Gzjl'][0].resumeGzsj,0, 7)}" />
			<c:set var="string2" value="${fn:replace(string1,'-', '.')}" />
			<c:set var="string3" value="${fn:substring(mv.model['Gzjl'][0].resumeGzsj1,0, 7)}" />
			<c:set var="string4" value="${fn:replace(string3,'-', '.')}" />
			<td align="center">${string2}-${string4}</td>
			<td align="center">${mv.model['Gzjl'][0].resumeGzgs}</td>
			<td align="center">${mv.model['Gzjl'][0].resumeGzcs}</td>
			<td align="center">${mv.model['Gzjl'][0].resumeGzms}</td>
		</tr>
		<c:forEach items="${ mv.model['Gzjl']}" begin="1" var="map" varStatus="status">
			<tr height="50px">
				<c:set var="string1" value="${fn:substring(map.resumeGzsj,0, 7)}" />
				<c:set var="string2" value="${fn:replace(string1,'-', '.')}" />
				<c:set var="string3" value="${fn:substring(map.resumeGzsj1,0, 7)}" />
				<c:set var="string4" value="${fn:replace(string3,'-', '.')}" />
				<td align="center">${string2}-${string4}</td>
				<td align="center">${map.resumeGzgs}</td>
				<td align="center">${map.resumeGzcs}</td>
				<td align="center">${map.resumeGzms}</td>
			</tr>

		</c:forEach>
	</table>

	<table width="100%" class="admintable" style="border-top:none" >

		<tr class="titleFixHei">
			<td width="35px">奖惩情况</td>
			<td>${ mv.model['resume'].resumeJcqk }</td>

		</tr>
		<tr class="titleFixHei">
			<td width="35px">培训情况</td>
			<td>${ mv.model['resume'].resumePxqk }</td>

		</tr>
		<tr class="titleFixHei">
			<td width="35px">熟悉何种专业技术及有何种特长</td>
			<td>${ mv.model['resume'].resumeSxhzjn }</td>

		</tr>
		<tr class="titleFixHei">
			<td width="35px">兴趣爱好</td>
			<td>${ mv.model['resume'].resumeXqah }</td>

		</tr>
	</table>

	<%--家庭关系	--%>
	<table width="100%" class="admintable" style="border-top:none">
		<tr height="50px">
			<td rowspan="${fn:length(mv.model['Pxjl'])+1}" width="35px">主要家庭成员及社会关系</td>
			<td width="20%" align="center">关系</td>
			<td width="20%" align="center">姓名</td>
			<td width="20%" align="center">出生年月</td>
			<td width="25%" align="center">政治面貌</td>
			<td width="35%" align="center">工作单位及职务</td>
		</tr>
		<c:forEach items="${ mv.model['Pxjl']}" var="map" varStatus="status">
			<tr height="50px">
				<td width="20%" align="center">
					<c:set var="string1" value="${fn:replace(map.resumePxmc,'|', '｜')}" />
					<c:set var="string2" value="${fn:replace(string1,'<', '＜')}" />
					<c:set var="string3" value="${fn:replace(string2,'>', '＞')}" />
					${fn:replace(string3,'-', '－')}
				</td>
				<td width="25%" align="center">
					<c:set var="string1" value="${fn:replace(map.resumePxjg,'|', '｜')}" />
					<c:set var="string2" value="${fn:replace(string1,'<', '＜')}" />
					<c:set var="string3" value="${fn:replace(string2,'>', '＞')}" />
					${fn:replace(string3,'-', '－')}
				</td>
				<td width="20%" align="center">
					<c:set var="string1" value="${fn:substring(map.resumePxsj,0, 7)}" />
					<c:set var="string2" value="${fn:replace(string1,'-', '.')}" />
					${string2}
				</td>
				<td width="20%" align="center">${map.resumePxsj1}</td>
				<td width="35%" align="center">
					<c:set var="string1" value="${fn:replace(map.resumePxnr,'|', '｜')}" />
					<c:set var="string2" value="${fn:replace(string1,'<', '＜')}" />
					<c:set var="string3" value="${fn:replace(string2,'>', '＞')}" />
					${fn:replace(string3,'-', '－')}
				</td>
			</tr>
		</c:forEach>
	</table>
	<table width="100%" class="admintable" style="border-top:none">
		<tr>
			<td align="center" width="13%">联系电话 (手机)</td>
			<td align="center" width="27%">${ mv.model['resume'].resumeTelphone }</td>
			<td align="center" width="12%">电子邮箱</td>
			<td align="center" width="48%">${ mv.model['resume'].resumeEmail }</td>

		</tr>
	</table>

</body>
</html>
