//显示和隐藏下拉列表
function showDown(id){
	document.getElementById(id).style.display='block';
}
function hideDown(id){
	document.getElementById(id).style.display='none';
}
//跳转到指定的url地址
function goToUrl(url){
	window.location.href=url;
}

//跳转带我的图书馆页面
//需求： 先判断是否登录，若未登录则跳转到登录页面，若登录将我的图书馆初始化为当前借阅记录
function toMyLibrary(user){
	//
	if(user == "null"){
		window.location.href="toLogin.action";
		return;
	}
	//
	window.location.href="queryMyBorrow.action";
	
}
//跳转到图书管理页面
function toManagerPage(){
	$.ajax({
		type: "post",
		url: "isToMananger.action",
		success: function(data){
			if(data=="noLogin"){
				if(confirm("您还未登录，是否前往登录？"))
					window.location.href="toLogin.action";
				return;
			}
			if(data =="noManager"){
				alert("对不起，您不是管理员身份，无法前往该页面");
				return;
			}
			window.location.href="toManager.action";
		}
	});
}




