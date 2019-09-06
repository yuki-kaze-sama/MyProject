<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>登陆页面</title>
  <style type="text/css">
  @charset "UTF-8";[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide:not(.ng-hide-animate){display:none !important;}ng\:form{display:block;}.ng-animate-shim{visibility:hidden;}.ng-anchor{position:absolute;}
  form{
  top:200px;
  width:400px;
  height:200px;
  margin:auto;
  position:relative;
  }
  </style>
  <link rel="stylesheet" href="//apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css">
</head>
<body>
	 <form ng-app="myApp"  name="myForm" ng-controller="formCtrl">
	  <div class="form-group">
	    <label for="username">用户名</label>
	    <input type="text" name="username" ng-model="username" class="form-control" aria-describedby="emailHelp" placeholder="填写用户名" required>
	    <span style="color:red" ng-show="myForm.username.$touched">
	    <span ng-show="myForm.username.$error.required">用户名未填写！</span>
	    </span>
	  </div>
	  <div class="form-group">
	    <label for="exampleInputPassword1">密码</label>
	    <input type="password" name="password" ng-model="password" class="form-control" placeholder="输入密码" required>
	    <span style="color:red" ng-show="myForm.password.$dirty && myForm.password.$touched">
	    <span ng-show="myForm.password.$error.required">密码不能为空！</span>
	    </span>
	  </div>
	  <button class="btn btn-primary" ng-click='submit()'>提交</button>     <button class="btn btn-primary" ng-click='register()'>注册</button>
	  </form>
 <script src="https://cdn.staticfile.org/angular.js/1.4.6/angular.min.js"></script>
 <script>
 	 var url = "<%=request.getParameter("url")%>";
	 var app = angular.module('myApp', []);
	 app.controller('formCtrl', function($scope, $http) {
		    $scope.submit = function() {
		    	$http({
		  	        method: 'POST',
		  	      data:{username:$scope.username,password:$scope.password},
		  	        url: 'http://localhost:8080/LoginAndRegister/userLogin',
		  	      headers: { "Content-Type": "application/json;charset=UTF-8" },
		  	    }).then(function successCallback(response) {
		  	            if(response.data != "error"){
		  	            	window.location.href= url + "?token=" + response.data;
		  	            }else{
		  	            	alert("用户名或密码错误！");
		  	            }
		  	        }, function errorCallback(response) {
		  	            // 请求失败执行代码
		  	            alert("连接失败！");
		  	    });
		    };
		    $scope.register = function() {
		    	window.location.href= "http://localhost:8080/LoginAndRegister/toRegister" + "?url=" + url;
		    }
		});
 </script>
</body>
</html>