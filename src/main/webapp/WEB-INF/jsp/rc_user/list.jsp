<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>用户列表</h1>
	<table border="1">
		<tr>
			<td>id</td>
			<td>姓名</td>
			<td>密码</td>
			<td>邮箱</td>
			<td>年龄</td>
			<td>生日</td>
			<td>操作</td>
		</tr>
		
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.username}</td>
				<td>${user.password}</td>
				<td>${user.email}</td>
				<td>${user.age}</td>
				<td>${user.birthDay}</td>
				<td>
					<a href="javascript:void(0)" onclick="updateById(${user.id})">修改</a>
					<a href="javascript:void(0)" onclick="deleteById(${user.id})">删除</a>		
				</td>
			</tr>	
		</c:forEach>		
	</table>
	
	<form action="1" method="post" id="deleteForm">
		<input type="hidden" name="_method" value="DELETE">
		<button>删除</button>
	</form>
	
	<form action="1" method="post" id="updateForm">
		<input type="hidden" name="_method" value="PUT">
		<button>修改</button>
	</form>

	<a href="javascript:void(0)" onclick="deleteById()">删除</a>	
	
<script type="${pageContect.request.contextPath}/resources/js/jquery.min.js"></script>	
<script type="text/javascript">
	function updateById(id) {
		alert("修改");
		
		document.getElementById('updateForm').submit();
	}
	
	function deleteById(id) {
		alert(id);
		var form = document.getElementById('deleteForm');
		form.action=id;
		document.getElementById('deleteForm').submit();
	}

</script>
</body>
</html>