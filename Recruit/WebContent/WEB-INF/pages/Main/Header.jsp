<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="header">
	<div class="top">
		<img src="images/logo.png" />
		<div class="loginLink">
			<a href="javascript:void(0);" style="font-weight: bold;">总部招聘</a> |
			<c:choose>
				<c:when test="${userLoginSession eq null}">
					<a href="javascript:void(0);" onclick="UserLogin()">登录</a>
					<script type="text/javascript">
						function UserLogin() {
							$.jBox.open("iframe:LoginFrm", "用户登录", 280, 200, {
								buttons : {}
							});
						}
					</script>
				</c:when>
				<c:otherwise>
					<a href="javascript:void(0);" onclick="UserLogout()">注销</a>
					<script type="text/javascript">
						function UserLogout() {
							if (confirm("确认注销本次登陆么？")) {
								window.location.href = "UserLogout";
							}
						}
					</script>
				</c:otherwise>
			</c:choose>
			| <a href="register">注册</a>
		</div>
	</div>
	<div class="nav">
		<div class="navL">
			<div class="navR">
				<ul class="clr">
					<li><a href="Main">首页</a>
					</li>
					<li><a href="GroupIntroduction">集团概况</a>
					</li>
					<%--<li><a href="TalentStrategy">员工发展</a></li>
					<li><a href="InformationCenter">招聘新闻</a></li>
					<li><a href="SearchEnterprise">招聘日程</a></li>
					<li><a href="SearchPosition">招聘职位</a></li>
					--%>
					<li><a href="MyRecruit">我的求职</a>
					</li>
					<%--<li><a href="AboutOnlineApplication">招聘指南</a></li>
					--%>
					<li style="background: none;" ><a
						href="http://www.casic.com.cn/"><img src="images/btnIndex.png" />
					</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<div id="otherbanner">
	<img src="images/otherbanner.jpg" />
</div>