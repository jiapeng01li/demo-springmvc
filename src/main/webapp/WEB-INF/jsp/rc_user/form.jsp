<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户表</title>
</head>
<body>

	<form action="save" method="post">
		<label>用户名:<input type="text" class="form-control name" placeholder="用户名" name="username" value="${sessionScope.user.username}"/></label> 
		<label>密码:<input type="password" class="form-control word" placeholder="密码" name="password" value="${requestScope.user.password}"/></label> 
		<label>邮箱:<input type="text" class="form-control email" placeholder="邮箱" name="email" value="${requestScope.user.email}"/></label> 
		<label>年龄:<input type="number" class="form-control age" placeholder="年龄" name="age" value="${requestScope.user.age}"/></label> 
		<label>年龄:<input type="text" class="form-control birth" placeholder="生日" name="birthDay" value="${requestScope.user.birthDay}"/></label>
		<label>省份:<input type="text" class="form-control province" placeholder="省份" name="address.province" value="${user.address.province}"/></label> 
		<label>城市:<input type="text" class="form-control city" placeholder="城市" name="address.city" value="${user.address.city}"/></label>
		
		
		<button class="btn btn-success btn-block">登录</button> 
	</form>

</body>
</html>