<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post">
		Email : <input type="text" name="email"/> </br>
		Username : <input type="text" name="username"/> </br>
		Password : <input type="password" name="password" pattern="^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$"/> </br>
		Password : <input type="password" name="password2"/> </br>
		Role : <input type="number" name="role" min="0" max="1"/> </br>
		
		<input type="submit" />
	</form>
</body>
</html>