<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>strawHat图书馆</title>
<link rel="stylesheet" href="css/index.css" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</head>
<body>
	<!-- 标题栏 -->
	<div class="title_div">
		<span class="title_content">草帽图书馆</span>
		<select class="login_select">
			<!-- 如果已经登录，第一项显示用户名称 -->
			<c:if test="${user != null }">
				<option onclick="#">${user.getNickname()}</option>
			</c:if>
			<!-- 未登录，第一项显示登录选项 -->
			<c:if test="${user == null }">
				<option onclick="goToUrl('toLogin.action');">登录</option>
			</c:if>
			<option onclick="goToUrl('toRegist.action');">注册</option>
			<option onclick="goToUrl('logout.action');">退出</option>
		</select>
	</div>
	
	<!-- 导航栏 -->
	<div class="navi_div">
		<ul>
			<li><a href="index.action" class="navia" >首页</a></li>
			<li><a href="javascript: return false;" class="navia">本馆概况</a></li>
			<li onMouseOver="showDown('reader_service_down');" onmouseout="hideDown('reader_service_down');">
				<a href="javascript: return false;" class="navia">读者服务</a>
				<!-- 鼠标滑过时显示下拉列表 -->
				<ul class="home_down_list" id="reader_service_down">	
					<li><a href="javascript:return false;" 
						onclick="toMyLibrary('<%=session.getAttribute("user") %>');">我的图书馆</a></li>	
					<li><a href="toBookSearch.action">图书检索</a></li>	
				</ul>
			</li>
			<li><a href="javascript: return false;" class="navia">帮助信息</a></li>
			<li><a href="javascript: return false;" class="navia" onclick="toManagerPage();">图书管理</a></li>
		</ul>
	</div>
	
	<div class="other_div">
		<!-- 首页中的图书检索框 -->
		<div class="home_search_div">
			<div>
				<form action="${pageContext.request.contextPath }/queryBooks.action" method="post">
					<select name="searchType">
						<option>书名</option>
						<option>作者</option>
						<option>索书号</option>
						<option>出版社</option>
					</select>
					<input type="text"  autofocus="autofocus" 	placeholder="请输入检索内容"
						class="home_search_input" name="searchContent"/>
					<input type="submit" value="检索" class="home_search_submit_btn" />
				</form>
			</div>
		</div>
		<!-- 首页显示图片的框 -->
		<div class="home_show_div">
		</div> 
	</div>
	
</body>
</html>