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
			
			<!-- 用户管理 -->
			<div class="manager_edit" id="authority_manage_id">
				<!-- 查询要编辑的图书 -->
				<div class="search_input_div">
					<form action="${pageContext.request.contextPath }/queryEditUsers.action" method="post">
						<!-- 通过参数绑定方式将值绑定到query方法的形参中 -->
						<select name="searchType">
							<option>用户名</option>
							<option>账号</option>
						</select>
						<input type="text"  autofocus="autofocus" 	placeholder="请输检索内容" 
							name="searchContent" class="edit_search_input"/>
						<input type="submit" value="检索" class="edit_search_submit_btn"/>
					</form>
				</div>
				<!-- 编辑图书查询结果 -->
				<!-- 查询结果为空时，提示 -->
				<c:if test="${userList != null && userList.size() == 0 }">
					<h4 style="color: red; text-align: center; margin-top: 100px;">用户信息不存在</h4>
				</c:if>
				<!-- 修改成功提示 -->
				<c:if test="${editSuccessTip != null}">
					<h4 style="color: green; text-align: center; margin-top: 100px;">${editSuccessTip }</h4>
				</c:if>
				<form action="${pageContext.request.contextPath }/editUsers.action" method="post">
				<table class="manager_edit_table">
					<c:if test="${userList != null && userList.size() > 0 }">
					<tr class="th_tr">
						<th>用户名</th>
						<th>账号</th>
						<th>身份</th>
						<th>ID</th>
						<th>密码</th>
						<th>权限提升</th>
					</tr>
					</c:if>
					<tbody id="edit_book_tbody">
						<c:forEach items="${userList }" var="user" varStatus="status">
						<tr class="td_tr" id="edit_user_tr_id">
							<td><input name="users[${status.index }].nickname" value="${user.nickname}"
								type="text" class="manager_input" readonly="readonly"/></td>
							<td><input name="users[${status.index }].username" value="${user.username}"
								type="text" class="manager_input" readonly="readonly" /></td>
							<td><input name="users[${status.index }].identity" value="${user.identity}"
								type="text" class="manager_input" readonly="readonly"/></td>
							<td><input name="users[${status.index }].id" value="${user.id}"
								type="text" class="manager_input" readonly="readonly"/></td>
							<td><input name="users[${status.index }].passwd" value="${user.passwd}"
								type="password" class="manager_input" /></td>
							<td><a href="javascript: return false;" onclick="setManager(${user.id}, '${user.identity}')">管理员</a></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 按钮框 -->
				<c:if test="${userList != null && userList.size() > 0}">
				<div class="botton_div">
					<input class="add_box_button" type="submit" value="提交修改" />
				</div>
				</c:if>
				</form>
			</div>
			
			
		</div>
	</div>
</body>
</html>