<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>注册页面</title>
  <link rel="stylesheet" href="//apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css">
</head>
 <style type="text/css">
  .form{
	  top:200px;
	  width:400px;
	  margin:auto;
	  position:relative;
  }
  </style>
<body>
	<div class="form">
	 <div class="form-group">
	    <label for="username">用户名</label>
	    <input type="text" id="username" class="form-control"  placeholder="填写用户名" required>
	  </div>
	 <div class="form-group">
	    <label for="password">密码</label>
	    <input type="password" id="password" class="form-control"  placeholder="填写密码" required>
	  </div>
	  <div class="form-group">
	    <label for="phone">电话</label>
	    <input type="text" id="phone" class="form-control"  placeholder="填写电话号码" required>
	  </div>
	  <div class="form-group">
	    <label for="address">地址</label>
	    <input type="text" id="address" class="form-control"  placeholder="填写地址" required>
	  </div>
	  <button class="btn btn-primary">注册</button>
	</div>
</body>
<script src="http://localhost:8080/LoginAndRegister/js/jquery-1.8.3.min.js"></script>
<script>
	var url = <%=request.getParameter("url")%>
	$("button").click(function(){
		var username = $("#username").val();
		var password = $("#password").val();
		var phone = $("#phone").val();
		var address = $("#address").val();
		$.ajax({
			contentType: "application/json;charset=UTF-8",
			url:"http://localhost:8080/LoginAndRegister/userRegister",    //请求的url地址
			data:JSON.stringify({username:username, password:password, phone:phone, address:address}),
			type:"POST",   //请求方式
			success:function(data){
				if(data == "exist") alert("用户名已存在！");
				else if(data == "error") alert("注册失败！");
				else window.location.href= url + "?token=" + data;
			},
			error:function(data){
				alert("连接失败！");
			}
		});
	});
</script>
</html>