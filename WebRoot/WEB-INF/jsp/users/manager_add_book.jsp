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
			<!-- 添加图书 -->
			<div class="manager_edit" id="add_book_id" >
				<!-- 查询当前借阅结果的表格 -->
				<div>
					<form method="post" id="addBookForm"
						action="${pageContext.request.contextPath }/insertBooks.action">
					<table class="manager_edit_table" id="add_book_table">
						<tr class="th_tr">
							<th>书名</th>
							<th>作者</th>
							<th>版本</th>
							<th>出版社</th>
							<th>馆藏书号</th>
							<th>删除</th>
						</tr>
						<tbody id="add_book_tbody">
							<tr class="td_tr">
								<td><input type="text" class="manager_input" placeholder="请输入书名" autofocus="autofocus"
									name="books[0].bookname"/></td>
								<td><input type="text" class="manager_input" placeholder="请输入作者"
									name="books[0].writer"/></td>
								<td><input type="text" class="manager_input" placeholder="请输入版本"
									name="books[0].version"/></td>
								<td><input type="text" class="manager_input" placeholder="请输入出版社"
									name="books[0].company"/></td>
								<td><input type="text" class="manager_input" placeholder="请输入馆藏号"
									name="books[0].address"/></td>
								<td><a href="javascript: return false;" onclick="deleteRow(this)">删除</a></td>
							</tr>
						</tbody>
					</table>
					<!-- 按钮框 -->
					<div class="botton_div">
						<input class="add_box_button" type="submit" value="提交" onclick="submitAddBook();" />
						<input class="add_box_button" type="button" value="清空" onclick="deleteAllRow();"/>
						<input class="add_box_button" type="button" value="添加一行" onclick="addRow();" />
					</div>
					</form>
				</div>
			</div>
			
		</div>
	</div>
</body>
</html>