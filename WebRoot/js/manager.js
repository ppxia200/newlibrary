//添加一行
function addRow(){
	var tab = document.getElementById("add_book_table");
	var rows = tab.rows.length;
	var tr = tab.insertRow(rows);
	tr.className = "td_tr";
	
	var td = tr.insertCell(0);
	td.innerHTML = "<input type='text' name='books["+(rows-1)+"].bookname' " +
			"class='manager_input' placeholder='请输入书名' autofocus='autofocus'/>";
	var td = tr.insertCell(1);
	td.innerHTML = "<input type='text' name='books["+(rows-1)+"].writer' " +
			"class='manager_input' placeholder='请输入作者'/>";
	var td = tr.insertCell(2);
	td.innerHTML = "<input type='text' name='books["+(rows-1)+"].version' " +
			"class='manager_input' placeholder='请输入版本'/>";
	var td = tr.insertCell(3);
	td.innerHTML = "<input type='text' name='books["+(rows-1)+"].company' " +
			"class='manager_input' placeholder='请输入出版社'/>";
	var td = tr.insertCell(4);
	td.innerHTML = "<input type='text' name='books["+(rows-1)+"].address' " +
			"class='manager_input' placeholder='请输入馆藏号'/>";
	var td = tr.insertCell(5);
	td.innerHTML = "<a href='javascript: return false;' onclick='deleteRow(this)'>删除</a>";
}

//删除一行
function deleteRow(obj){
	if(confirm("确定删除当前行吗？")){
		obj.obj.parentNode.parentNode.innerHTML = "";
		var row = obj.parentNode.parentNode.rowIndex;
		var table = document.getElementById("add_book_table");
		table.deleteRow(row);
//		tbody.removeChild(selectedTr);
	}
}
//提交修改图书信息
function submitEditBook(){
	$("#edit_book_table").ajaxForm(function(data){    
        if(data == "inputError")
        	alert("书本信息不完整，请保证书本信息都不为空"); 
        else{
        	alert(data);
        	window.location.href="toEditBook.action"
        }
	});
}
//提交添加图书
function submitAddBook(){
    $("#addBookForm").ajaxForm(function(data){    
        if(data == "noInput")
        	alert("您还未输入任何数据！");
        else if(data == "inputError")
        	alert("输入信息不完整，请保证信息都不为空");
        else{
        	alert("成功添加书本");
//        	document.getElementById("add_book_tbody").innerHTML ="";
        	document.getElementById("add_book_tbody").innerHTML ="";
        }
    });       
}

//删除一本已有的图书
function deleteBook(bookId, node){
	
	if(confirm("确定删除当前图书吗？")){
		$.ajax({
			type: "post",
			url: "deleteBook.action",
			data: "id="+bookId,
			success: function(data){
				if(data == "isBorrowed")
					alert("该书处于借阅状态，不可删除")
				else{
					alert("成功删除图书");
					var selectedTr = node.parentNode.parentNode;
					var tbody = document.getElementById("edit_book_tbody");
					tbody.removeChild(selectedTr);
				}
			}
		});
	}
}
//将用户设置为管理员
function setManager(userId, identity){
	if(identity == "管理员"){
		alert("当前用户已是管理员！");
		return;
	}
	$.ajax({
		type: "post",
		url : "setManager.action",
		data: "id="+userId,
		success: function(){
			alert("权限修改成功");
			window.location.href="queryEditUsers.action";
		}
	});
}
//清空所有行
function deleteAllRow(){
	if(confirm("确定清空所有添加信息吗？"))
		document.getElementById("add_book_tbody").innerHTML ="";
}