<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的图书馆</title>
<link  rel="stylesheet" href="css/my_library.css" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/my_library.js"></script>
</head>
<body>
	<!-- 标题栏 -->
	<div class="my_library_title_div">
		<a href="index.action" style="float: right; margin-right: 25px; margin-top: 8%;">返回首页</a>
	</div>
	
	<!-- 下边的部分 -->
	<div class="my_library_other_div">
		
		<div style="width:100%;height: 30px; background-color: #34a8d3;"></div><!-- 分隔栏 -->
		<!-- 左边的导航栏 -->
		<div class="my_library_navi_div">
			<h5>我的操作</h5>
			<div class="my_library_service_div">
				<ul class="my_service_ul">
					<li><a href="javascript:return false;" onclick="currentBorrow();">当前借阅</a></li>
					<li><a href="javascript:return false;" onclick="myOppo();">我的预约</a></li>
					<li><a href="javascript:return false;" onclick="myInfo();">我的信息</a></li>
					<li><a href="javascript:return false;" onclick="borrowHistory();">借阅历史</a></li>
				</ul>
			</div>
		</div>
		
		<!-- 右边的主要显示部分 -->
		<div class="my_library_show_div">
			<!-- 当前借阅 -->
			<div class="current_borrow" id="current_borrow_id" >
				<p class="current_title">当前借阅图书：<span style="color: black;">${borrowInfoUserList.size() }</span></p>
				<div style="width:99%;height: 3px; background-color: #34a8d3;"></div><!-- 隔栏 -->
				<!-- 查询当前借阅结果的表格 -->
				<table class="current_borrow_table">
					<tr class="th_tr">
						<th>书名</th>
						<th>作者</th>
						<th>出版社</th>
						<th>借阅日期</th>
						<th>应还日期</th>
						<th>续借量</th>
						<th>馆藏书号</th>
						<th>续借</th>
						<th>还书</th>
					</tr>
					<c:forEach items="${borrowInfoUserList }" var="borrowInfo">
						<tr class="td_tr">
							<td>${borrowInfo.bookname }</td>
							<td>${borrowInfo.writer }</td>
							<td>${borrowInfo.company }</td>
							<td><fmt:formatDate value="${borrowInfo.borrowDate }" pattern="yyyy-MM-dd" /></td>
							<td><fmt:formatDate value="${borrowInfo.shouldReturnDate }" pattern="yyyy-MM-dd" /></td>
							<td>${borrowInfo.renewCount }</td>
							<td>${borrowInfo.address }</td>
							<td><a href="javascript:return false;" style="background-color: green;"
								onclick="renewBook(${borrowInfo.renewCount},${borrowInfo.userId},
								${borrowInfo.bookId},${borrowInfo.id});">续借</a></td>
							<td><a href="javascript:return false;"
								onclick="returnBook(${borrowInfo.id},${borrowInfo.userId},${borrowInfo.bookId});">还书</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
			<!-- 我的预约 -->
			<div class="my_oppo" id="my_oppo_id">
				<p class="current_title" style="color: #34a8d3; 
					background-color: white;">当前预约图书：<span style="color: black;" id="oppo_count"></span></p>
				<div style="width:99%; height: 2px; background-color: #34a8d3;"></div><!-- 隔栏 -->
				<!-- 查询当前预定结果的表格 -->
				<table class="current_borrow_table">
					<tr class="th_tr">
						<th>书名</th>
						<th>作者</th>
						<th>出版社</th>
						<th>预约日</th>
						<th>截止日期</th>
						<th>状态</th>
						<th>馆藏书号</th>
						<th>取消预约</th>
					</tr>
					<tbody id="oppo_table_body">
					</tbody>
				</table>
			</div>
			
			<!-- 我的信息 -->
			<div class="my_info" id="my_info_id">
				<!-- 信息框 -->
				<div class="msg-box">
					<h4 style="margin-bottom: 3px; margin-left: 42%; margin-top: 10px;">我的基本信息</h4>
					<hr class="my_msg_hr" style="width: 23%; margin-left: 38%; margin-bottom: 20px;">
					<img class="my_icon" src=""/>
					<span class="msg-label" id="username_id">账号：</span><hr class="my_msg_hr">
					<span class="msg-label" id="identity_id">身份：    </span><hr class="my_msg_hr">
					<span class="msg-label" id="nickname_id">用户名：    </span><hr class="my_msg_hr">
					<span class="msg-label" id="current_b_id">当前借阅图书：    </span><hr class="my_msg_hr">
					<span class="msg-label" id="current_o_id">当前预约图书：    </span><hr class="my_msg_hr">
					<span class="msg-label" id="max_borrow_id">最大可借图书：   </span><hr class="my_msg_hr">
					<span class="msg-label" id="max_o_id">最大可预约图书：    </span><hr class="my_msg_hr">
					<span class="msg-label" id="create_time_id">注册时间：   </span><hr class="my_msg_hr">
				</div>
			</div>
			
			<!-- 借阅历史 -->
			<div class="my_history" id="my_history_id">
				<p class="current_title" style="background-color: white; color: #34a8d3;">我的借阅历史：<span style="color: black;" id="hist_count"></span></p>
				<hr style="width:99%; border-bottom: dashed #34a8d3; "/><!-- 隔栏 -->
				<!-- 查询当前借阅结果的表格 -->
				<table class="current_borrow_table">
					<tr class="th_tr">
						<th>书名</th>
						<th>作者</th>
						<th>出版社</th>
						<th>借阅日期</th>
						<th>应还日期</th>
						<th>还书日期</th>
						<th>状态</th>
					</tr>
					<tbody id="hist_table_body">
					</tbody>
				</table>
			</div>
			
		</div>
	</div>
	
	
</body>
</html>