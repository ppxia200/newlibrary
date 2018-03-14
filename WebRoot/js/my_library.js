//我的图书馆的页面操作
//格式化日期
function formatDate(time) {
	if(time == null)
		return "";
	else{
		var result = "";
		var date = new Date(time);
		if(date.getMonth()+1 < 10)
			result += date.getFullYear()+"-"+"0"+(date.getMonth()+1);
		else
			result += date.getFullYear()+"-"+(date.getMonth()+1);
		if(date.getDate()<10)
			result += "-"+"0"+date.getDate();
		else
			result += "-"+date.getDate();
	    return result;
	}
}
//当前借阅
function currentBorrow(){
	window.location.href="queryMyBorrow.action";
}
//当前预约
function myOppo(){
	$.ajax({
		type: "post",
		url: "queryOppos.action",
		success: function(data){
			var oppoList = data;
			var innerHtml = "";
			for(var x=0; x<oppoList.length; x++){
				innerHtml += "<tr class='td_tr'><td>"+oppoList[x].bookname+"</td>"+
				"<td>"+oppoList[x].writer+"</td>"+
				"<td>"+oppoList[x].company+"</td>"+
				"<td>"+formatDate(oppoList[x].oppoDate)+"</td>"+
				"<td>"+formatDate(oppoList[x].endDate)+"</td>"+
				"<td>"+oppoList[x].state+"</td>"+
				"<td>"+oppoList[x].address+"</td>" +
				"<td><a href='javascript:return false;' onclick='cancelOppoBook("+oppoList[x].bookId+");'>取消</a></td></tr>";
			}
			var table = document.getElementById("oppo_table_body");
			document.getElementById("oppo_count").innerHTML = oppoList.length;	//设置数量
			
			table.innerHTML = innerHtml;
			var div =document.getElementById("current_borrow_id").style.display="none";
			var div =document.getElementById("my_history_id").style.display="none";
			var div =document.getElementById("my_info_id").style.display="none";
			$(".my_oppo").slideDown(800);
		}
	});
}
//基本信息
function myInfo(){
	$.ajax({
		type: "post",
		url: "queryMyInfo.action",
		success: function(userInfo){
			document.getElementById("username_id").innerHTML ="账号：   &nbsp&nbsp"+ userInfo.username;
			document.getElementById("identity_id").innerHTML ="身份：   &nbsp&nbsp"+ userInfo.identity;
			document.getElementById("nickname_id").innerHTML ="用户名：   &nbsp&nbsp"+ userInfo.nickname;
			document.getElementById("current_b_id").innerHTML ="当前借阅图书：   &nbsp&nbsp"+ userInfo.currentBorrowCount;
			document.getElementById("current_o_id").innerHTML ="当前预约图书：   &nbsp&nbsp"+ userInfo.currentOppoCount;
			document.getElementById("max_borrow_id").innerHTML ="最大可借图书：   &nbsp&nbsp"+ userInfo.maxBorrow;
			document.getElementById("max_o_id").innerHTML ="最大可预约图书：   &nbsp&nbsp"+ userInfo.maxOppo;
			document.getElementById("create_time_id").innerHTML ="注册时间：   &nbsp&nbsp"+ formatDate(userInfo.createTime);
			
			var div =document.getElementById("current_borrow_id").style.display="none";
			var div =document.getElementById("my_oppo_id").style.display="none";
			var div =document.getElementById("my_history_id").style.display="none";
			$(".my_info").slideDown(800);
		}
	});
}
//借阅历史
function borrowHistory(){
	$.ajax({
		type: "post",
		url: "queryBorrowHistory.action",
		success: function(data){
			var borrowHistoryList = data;
			var innerHtml = "";
			for(var x=0; x<borrowHistoryList.length; x++){
				innerHtml += "<tr class='td_tr'><td>"+borrowHistoryList[x].bookname+"</td>"+
				"<td>"+borrowHistoryList[x].writer+"</td>"+
				"<td>"+borrowHistoryList[x].company+"</td>"+
				"<td>"+formatDate(borrowHistoryList[x].borrowDate)+"</td>"+
				"<td>"+formatDate(borrowHistoryList[x].shouldReturnDate)+"</td>"+
				"<td>"+formatDate(borrowHistoryList[x].returnDate)+"</td>";
				if(borrowHistoryList[x].isReturn)
					innerHtml += "<td>已还</td></tr>";
				else
					innerHtml += "<td>借阅中</td></tr>";
			}
			var table = document.getElementById("hist_table_body");
			document.getElementById("hist_count").innerHTML = borrowHistoryList.length;	//设置数量
			
			table.innerHTML = innerHtml;
			var div =document.getElementById("current_borrow_id").style.display="none";
			var div =document.getElementById("my_oppo_id").style.display="none";
			var div =document.getElementById("my_info_id").style.display="none";
			$(".my_history").slideDown(800);
		}
	});
}

//续借
function renewBook(renewCount, userId, bookId, id){
	
	if(renewCount>=1){	//最大续借次数为1
		alert("当前续借次数已达上限");
		return;
	}
	//续借
	$.ajax({
		type: "post",
		url: "renewBook.action",
		data: "userId="+userId+"&bookId="+bookId+"&id="+id,
		success: function(){
			alert("续借成功");
			window.location.href="queryMyBorrow.action";
		}
	});
}

//还书
function returnBook(id,userId, bookId){
	$.ajax({
		type: "post",
		url: "returnBook.action",
		data: "userId="+userId+"&bookId="+bookId+"&id="+id,
		success: function(){
			alert("还书成功");
			window.location.href="queryMyBorrow.action";
		}
	});
}

//取消预约
function cancelOppoBook(bookId){
	$.ajax({
		type:"post",
		url:"cancelOppoBook.action",
		data:'bookId='+bookId,
		success:function(data){
			alert("成功取消预约");
			myOppo();	//刷新当前预约
		}
	});
}