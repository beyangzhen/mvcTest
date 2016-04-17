<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>添加商品</h1>
		<hr>
		<form action="product?action=add" method="post">
			商品名称：<input type="text" name="name"><br>
			价格：<input type="text" name="price"><br>
			数量：<input type="text" name="amount"><br>
			<input type="submit" value="添加"/>
		</form>
	</center>
</body>
</html>