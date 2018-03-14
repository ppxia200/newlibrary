//显示输入提示
function inputErrorTip(id, msg){
	var div = document.getElementById(id);
	div.innerHTML = msg;
}

//点击验证码重新加载
function myReload() {  
	document.getElementById("authCode").src = document  
        .getElementById("authCode").src  
        + "?nocache=" + new Date().getTime(); 
} 