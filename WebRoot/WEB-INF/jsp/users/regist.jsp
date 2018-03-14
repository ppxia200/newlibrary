<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册页面</title>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/regist.js"></script>

<link rel="stylesheet" href="css/regist.css" />
</head>
<body>
	<form id="refrm" action="${pageContext.request.contextPath }/regist.action" method="post">
		<fieldset>
			<h3>普通用户注册信息</h3>
			<div class="erTip" id="nicknameTip">${nickname }${userExistError}</div>
			<input type="text" name="nickname" placeholder="请输入用户名" value="${user.nickname }"
				onfocus="inputErrorTip('nicknameTip','请输入1-12个字符的用户名');" 
				onblur="inputErrorTip('nicknameTip','');" />
				
			<div class="erTip" id="usernameTip">${username }</div>
			<input type="text" name="username" placeholder="请输入学号"  value="${user.username }"
				onfocus="inputErrorTip('usernameTip','请输入12位数字的账号');" 
				onblur="inputErrorTip('usernameTip','');"/>
				
			<div class="erTip" id="passwdTip">${passwd }</div>
			<input type="password" name="passwd" placeholder="请输入密码" value="${user.passwd }"
				onfocus="inputErrorTip('passwdTip','请输入6-10个数字或字母的密码');" 
				onblur="inputErrorTip('passwdTip','');" /> 
				
			<div class="erTip">${comfirmPassError}</div>
			<input type="password" name="confirmPass" placeholder="输入确认密码" value="${confirmPass }" />
			
			<div class="erTip">${vercoeError}</div>
			<input type="text" name="inputVercode" placeholder="输入验证码" class="reAuthIn" />
			<img id="authCode" alt="验证码" src="authCode.servlet" onclick="myReload()" class="reAuthCode" />
			<input type="submit" value="完成" class="resub" />
		</fieldset>
	</form>
</body>
</html>