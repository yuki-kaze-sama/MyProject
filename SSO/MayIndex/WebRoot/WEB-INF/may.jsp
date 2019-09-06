<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>查看密码</title>
  <style type="text/css">
  .form{
	  top:200px;
	  width:400px;
	  margin:auto;
	  position:relative;
  }
  </style>
  <link rel="stylesheet" href="//apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css">
</head>
<body>
	 <div class="form">
	  <div class="form-group">
	    <label for="username">密码</label>
	    <p>${USER_SESSION.password}</p>
	  </div>
	  
	  <button class="btn btn-primary">提交</button>
	</div>
</body>
<script src="http://localhost:8080/LoginAndRegister/js/jquery-1.8.3.min.js"></script>
<script>
	$("button").click(function(){
		var token = "<%=request.getParameter("token")%>";
		alert("123213");
		window.location.href= "http://localhost:8080/MainIndex/toMain" + "?token=" + token;
	});
</script>
</html>