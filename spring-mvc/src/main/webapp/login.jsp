<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>spring on the way</title>
</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="4">
		<tr>
			<td bgcolor="#000099">
				<table width="100%" border="0" cellspacing="0" cellpadding="4">
					<tr>
						<td bgcolor="#FFFFFF">&nbsp;<b>*</b>&nbsp;
						</td>
						<td width="100%"><font color="#CCCCCC">&nbsp; <font
								color="#FFFFFF">Title</font>
						</font></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%" bgcolor="#EAEAEA" colspan="2">
				<form name="loginForm" action="login.do" method="post">
					<p>
						<label for="usernmae">用户名：</label> <br> <input type="text" id="username"
							name="user.username">
					</p>
					<p>
						<label for="password">密码：</label> <br> <input type="password" id="password"
							name="user.password">
					</p>
					<p>
						<input type="submit" value="登陆">
					</p>
					<p>&nbsp;</p>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>