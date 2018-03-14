function borrowBook(bookId, user){
	if(user == 'null'){	//未登录
		var flag = confirm("您还未登录，是否前往登录？");
		if(flag)	//跳转到登录页面
			window.location.href="toLogin.action";	
	}else{	//已登录
		var j = {"bookId": bookId};
		$.ajax({
			type:"post",
			url:"borrowBook.action",
//			data:'bookId='+bookId,
			data:j,
			success:function(data){
				if(data == "isBorrowed"){
					alert("该书已经被借阅，请尝试借阅其他书本");
					return;
				}else{
					alert("您成功借阅了《"+data+"》");
					window.location.href="queryBooks.action";//刷新
				}
			}
		});
	}
}

//预约图书
function oppoBook(bookId, user){
	if(user == 'null'){	//未登录
		var flag = confirm("您还未登录，是否前往登录？");
		if(flag)	//跳转到登录页面
			window.location.href="toLogin.action";	
	}else{	//已登录
		$.ajax({
			type:"post",
			url:"oppoBook.action",
			data:'bookId='+bookId,
			success:function(data){
				alert(data);
			}
		});
	}
}





