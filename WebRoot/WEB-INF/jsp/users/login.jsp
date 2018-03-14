<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<script type="text/javascript">
	//点击更换二维码
	function myReload() {  
    	document.getElementById("authCode").src = document  
            .getElementById("authCode").src  
            + "?nocache=" + new Date().getTime();  
	} 
</script> 
<link rel="stylesheet" href="css/login.css">
<script type="text/javascript" src="js/jquery-3.2.1.min.js"><!--一定是先导入库文件-->
</script>
<script type="text/javascript" src="js/add.js">
</script>
</head>
<body>
	<sf:form id="frm" action="${pageContext.request.contextPath }/login.action" method="post">
		<fieldset>
			<h2 class="header">用户登录</h2>
			<div class="errorTip" id="account-error">
				${loginError }
				${username }
			</div>
			<input type="text" class="user-account" placeholder="请输入账号" name="username" value="${user.username }" />
			<div class="errorTip" id="pass-error">${passwd }</div>
			<input type="password" class="user-pass" placeholder="请输入密码" name="passwd" value="${user.passwd }" />
			<div class="errorTip" id="vercode-error">${vercodeError }</div>
			<input type="text" class="vercode" placeholder="输入验证码" class="authIn" 
				name="inputVercode" value="${inputVercode }"/>
			<img id="authCode" alt="验证码" src="authCode.servlet" onclick="myReload()" class="authCode" />
			<input class="sub" type="submit" value="登录"/>
		<div>
			<a href="toRegist.action">注册账号</a>
			<a href="">忘记密码</a>
		</div>
		</fieldset>
	</sf:form>
</body>
</html>