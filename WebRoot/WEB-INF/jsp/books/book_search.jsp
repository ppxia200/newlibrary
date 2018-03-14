<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>读者服务</title>
<link rel="stylesheet" href="css/book_search.css" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/book.js"></script>
</head>
<body>
	<div class="reader_service_title_div">
		<!-- <img alt="搜索图标" src="images/search.jpg" style="width: 12%; height: 80%;"> -->
		<a href="index.action" style="float: right; margin-right: 25px; margin-top: 8%;">返回首页</a>
	</div>
	
	
	<div class="reader_service_other_div">
		<div style="width:100%;height: 30px; background-color: #05a015;"></div><!-- 隔栏 -->
		<!-- 左边部分：检索结果详情 -->
		<div class="reader_service_navi_div">
			<h5>检索详情</h5>
			<div class="search_detail_div">
				<c:if test="${bookList != null }">
					<p style="font-weight: bold;margin-bottom: 0; font-size: 14px;">检索到的记录数:</p>
					<p style="font-size: 13px; margin-left: 8px;margin-top: 5px;">
						共<span style="color: red;">${bookList.size() }</span>条记录</p>
				</c:if>
			</div>
	</div>
		
	<!-- 查询结果部分 -->
	<div class="reader_service_show_div">
		<fieldset class="search_fieldset">
			<legend style="font-size: 18px; color: #05a015;">馆藏书目检索</legend>
			<!-- 查询页中的图书检索框 -->
			<div>
				<form action="${pageContext.request.contextPath }/queryBooks.action" method="post">
					<!-- 通过参数绑定方式将值绑定到query方法的形参中 -->
					<select name="searchType">
						<option>书名</option>
						<option>作者</option>
						<option>索书号</option>
						<option>出版社</option>
					</select>
					<input type="text"  autofocus="autofocus" 	placeholder="请输入检索内容" 
						name="searchContent" class="search_input"/>
					<input type="submit" value="检索" class="search_submit_btn"/>
				</form>
			</div>
		</fieldset>
		<p class="result_divider">查询结果</p>
		<div style="width:99%;height: 3px; background-color: #05a015;"></div><!-- 隔栏 -->
		
		<!-- 查询结果为空时，提示 -->
		<c:if test="${bookList != null && bookList.size() == 0 }">
			<h4 style="color: red; text-align: center; margin-top: 100px;">没有您要的查询结果</h4>
		</c:if>
		
		<!-- 查询结果不为空：显示 -->
		<c:forEach items="${bookList }" var="book">
			<h4>${book.bookname }</h4>
			<span class="result_detail" >${book.writer }<span style="color: red;">著</span></span><br />
			<span class="result_detail">${book.company }</span>
			<c:if test="${book.state == '可借' }"> <!-- 为可借时显示借阅按钮 -->
				<a href="javascript:return false;" onclick="borrowBook(${book.id}, '<%=session.getAttribute("user")%>')" >借阅</a>
			</c:if>
			<a href="javascript:return false;" onclick="oppoBook(${book.id}, '<%=session.getAttribute("user")%>')" >预约</a>
			<a href ="javascript:return false;" style="color: #05a015; text-decoration: none;" >藏书号:${book.address }</a>
			<a href ="javascript:return false;" style="color: #05a015; text-decoration: none;" >${book.state }</a>
			<hr style="border: dashed 1px; width: 99%; float:left;" />
		</c:forEach>
		
	</div>
	
</div>

</body>
</html>