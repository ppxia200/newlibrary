<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册成功页面</title>
<link rel="stylesheet" href="css/regist_success.css" />
</head>
<body>
	<div>
		<p><span style="color: red">恭喜您</span>，账号<span style="color: red">${username }</span>注册成功！</p>
		<input type="button" value="前往登录页面" onclick="location.href='toLogin.action'" />
	</div>
</body>
</html>