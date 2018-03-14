<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员页面</title>
<link rel="stylesheet" href="css/manager.css" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/jquery.form.min.js"></script>
<script type="text/javascript" src="js/manager.js"></script>
</head>
<body>
	<!-- 标题栏 -->
	<div class="manager_title_div">
		<a href="index.action" style="float: right; margin-right: 25px; margin-top: 8%;">返回首页</a>
	</div>
	
	<!-- 下边的部分 -->
	<div class="manager_other_div">
		
		<div style="width:100%;height: 30px; background-color: #dd611e;"></div><!-- 分隔栏 -->
		<!-- 左边的导航栏 -->
		<div class="manager_navi_div">
			<h5>管理类别</h5>
			<div class="manager_service_div">
				<ul class="manager_service_ul">
					<li><a href="toManager.action">添加图书</a></li>
					<li><a href="toEditBook.action">编辑图书</a></li>
					<li><a href="toEditUser.action">账号管理</a></li>
				</ul>
			</div>
		</div>
		
		<!-- 右边的主要显示部分 -->
		<div class="manager_show_div">
			
			<!-- 编辑图书-->
			<div class="manager_edit" id="edit_book_id">
				<!-- 查询要编辑的图书 -->
				<div class="search_input_div">
					<form action="${pageContext.request.contextPath }/queryEditBooks.action" method="post">
						<!-- 通过参数绑定方式将值绑定到query方法的形参中 -->
						<select name="searchType">
							<option>书名</option>
							<option>作者</option>
							<option>索书号</option>
							<option>出版社</option>
						</select>
						<input type="text"  autofocus="autofocus" 	placeholder="请输入检索内容" 
							name="searchContent" class="edit_search_input"/>
						<input type="submit" value="检索" class="edit_search_submit_btn"/>
					</form>
				</div>
				<!-- 编辑图书查询结果 -->
				<!-- 查询结果为空时，提示 -->
				<c:if test="${bookList != null && bookList.size() == 0 }">
					<h4 style="color: red; text-align: center; margin-top: 100px;">书本信息不存在</h4>
				</c:if>
				<!-- 修改成功提示 -->
				<c:if test="${editSuccessTip != null}">
					<h4 style="color: green; text-align: center; margin-top: 100px;">${editSuccessTip }</h4>
				</c:if>
				<form action="${pageContext.request.contextPath }/editBooks.action"
					 method="post" id="edit_book_table">
				<table class="manager_edit_table">
					<c:if test="${bookList != null && bookList.size() > 0}">
					<tr class="th_tr">
						<th>书名</th>
						<th>作者</th>
						<th>版本</th>
						<th>出版社</th>
						<th>馆藏书号</th>
						<th>状态</th>
						<th>ID</th>
						<th>删除</th>
					</tr>
					</c:if>
					<tbody id="edit_book_tbody">
						<c:forEach items="${bookList }" var="book" varStatus="status">
						<tr class="td_tr" id="edit_book_tr_id">
							<td><input type="text" class="manager_input" 
								name="books[${status.index}].bookname" value="${book.bookname }"/></td>
							<td><input type="text" class="manager_input"
								name="books[${status.index}].writer" value="${book.writer }" /></td>
							<td><input type="text" class="manager_input" 
								name="books[${status.index}].version" value="${book.version }"/></td>
							<td><input type="text" class="manager_input" 
								name="books[${status.index}].company" value="${book.company }"/></td>
							<td><input type="text" class="manager_input" 
								name="books[${status.index}].address" value="${book.address }"/></td>
							<td><input type="text" class="manager_input" style="width: 50px;" readonly="readonly"
								name="books[${status.index}].state" value="${book.state }"/></td>
							<td><input type="text" class="manager_input" style="width: 50px;" readonly="readonly"
								name="books[${status.index}].id" value="${book.id }"/></td>
							<td><a href="javascript: return false;" onclick="deleteBook(${book.id},this);">删除</a></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 按钮框 -->
				<c:if test="${bookList != null && bookList.size() > 0}">
				<div class="botton_div">
					<input class="add_box_button" type="submit" value="提交修改" onclick="submitEditBook();" />
				</div>
				</c:if>
				</form>
			</div>
			
			
		</div>
	</div>
</body>
</html>