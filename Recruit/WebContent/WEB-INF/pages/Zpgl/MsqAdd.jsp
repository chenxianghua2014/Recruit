<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link rel="stylesheet" href="resources/upload/upload.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="resources/validator-0.7.1/jquery.validator.css" type="text/css"></link>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/jquery.validator.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript"
	src="resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/MsqAdd.js"></script>
</head>
<body>
	<div class="title">当前位置:面试圈>安排面试</div>
	<div class="editBlock">
		<form action="MsqSave" method="post" autocomplete="off"
			data-validator-option="{theme:'yellow_right_effect',stopOnError:true}"
			onsubmit="SelectAll()">
			<input type="hidden" id="msqId" name="msqId"
				value="${ msq.model['msq'].msqId }" />
			<table>
				<tr>
					<td colspan="2" class="subtitle">安排面试</td>
				</tr>
				<tbody>
					<tr>
						<th width="250"><span class="warning">*</span>面试圈名称</th>
						<td><input type="text" class="inputText" id="msqName"
							name="msqName" value="${ msq.model['msq'].msqName }"
							data-rule="required;" /></td>
					</tr>
					<tr>
						<th width="250"><span class="warning">*</span>面试类别</th>
						<td><select id="msqMslb" name="msqMslb" style="width:140px;" data-rule="required;">
								<option value="一面">一面</option>
								<option value="二面">二面</option>
								<option value="终面">终面</option>
						</select></td>
					</tr>
					<tr>
						<th width="150"><span class="warning">*</span>面试地点</th>
						<td><input type="text" class="inputText" id="msqMsdd"
							name="msqMsdd" value="${ msq.model['msq'].msqMsdd }"
							data-rule="required;" /></td>
					</tr>
					<tr>
						<th><span class="warning">*</span>面试时间</th>
						<td><input type="text" class="inputText" id="msqMssj"
							name="msqMssj"
							onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							value="${ msq.model['msq'].msqMssj }" data-rule="required;" /></td>
					</tr>
					<tr>
						<th><span class="warning">*</span>面试官</th>
						<td><table boder="0" style="width: 350px;border: none;">
								<tr>
									<td colspan="3"><select id="bm" style="width:140px;"
										name="bm" onchange="bindMsg()">
									</select></td>
								</tr>
								<tr>
									<td colspan="3"><select id="zw" style="width:140px;"
										name="zw" onchange="bindMsg()">
									</select></td>
								</tr>
								<tr>
									<td><select id="selMsg" style="width:140px;height:200px;"
										name="selMsg" multiple="multiple">
									</select>
									</td>
									<td style="text-align: center;"><input type="button"
										value="添加" class="inputButton" onclick="AddMsg()"><br>
										<br> <input type="button" value="移除" class="inputButton"
										onclick="RemoveMsg()"><br> <br> <input
										type="button" value="全部添加" class="inputButton"
										onclick="AddALLMsg()"><br> <br> <input
										type="button" value="全部移除" class="inputButton"
										onclick="RemoveAllMsg()">
									</td>
									<td><select id="msqMsgNames"
										style="width:140px;height:200px;" name="msqMsgNames"
										multiple="multiple" data-rule="required;">
											<c:if test="${fn:length(msq.model['msg'].id) != 0}">
												<c:forEach var="i" begin="0"
													end="${fn:length(msq.model['msg'].id) - 1}" step="1">
													<option value="${msq.model['msg'].name[i]}">
														${msq.model['msg'].name[i]}</option>
												</c:forEach>
											</c:if>
									</select>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</tbody>
				<tr>
					<th></th>
					<td><input type="submit" class="inputButton" value="确定" />
						&nbsp;&nbsp; <input type="button" class="inputButton" value="取消"
						onclick="history.back()" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
