<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="index" method="post">
		<h4 class="no-margins">
			<font color="#6C6C6C">登录：</font>
		</h4>
		<p class="m-t-md">
			<font color="#6C6C6C">欢迎登录管理系统</font>
		</p>
		<input type="text" class="form-control uname" placeholder="用户名" name="username" /> 
		<input type="password" class="form-control pword m-b" placeholder="密码" name="password" />
		<button class="btn btn-success btn-block">登录</button>
	</form>
</body>
</html>