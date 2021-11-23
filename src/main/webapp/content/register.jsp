<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Register page</title>
<link href="<c:url value="/assets/css/mystyle.css " />" rel="stylesheet">
<link href="<c:url value="/assets/css/mystyle3.css " />" rel="stylesheet">
<script src="<c:url value="/assets/js/main.js " />"></script>
</head>

<body>
	<div class="app-login">
		<h2 class="msg_text">
			<%= request.getAttribute("msg") != null ? request.getAttribute("msg") : " " %>
		</h2>
		<h4 class="app__validate"></h4>

		<form class="form__login" action="/ProjectJava/registercontroller"
			method="Post" id="form-1">
			<h2 class="login__heading">Register</h2>
			<div class="form__name">
				<input class="form-login__input" type="text"
					rule="isRequired|min:3|max:30" placeholder="User name"
					name="username"> <br> <span class="form__message"></span>
			</div>
			<div class="form__name">
				<input class="form-login__input" type="text"
					rule="isRequired|isEmail|max:50" placeholder="E-mail" name="email">
				<br> <span class="form__message"></span>
			</div>
			<div class="form__name">
				<input class="form-login__input" type="password"
					rule="isRequired|min:8|max:30" placeholder="Password"
					name="password"> <br> <span class="form__message"></span>
			</div>
			<div class="form__name">
				<input class="form-login__input" type="password"
					rule="isRequired|isPassword" placeholder="Re Password"
					name="repassword"> <br> <span class="form__message"></span>
			</div>
			<input class="form__submit" type="submit" value="Register"> <a
				class="login__link" href="/ProjectJava/view/login.jsp"> Click here to Login </a>
		</form>
	</div>

	<script>
                const formNameSelector = '.form__name';
                const formMessage = '.form__message';
                const invalidForm = '.app__validate';
                var form1 = new Validator('#form-1');
            </script>

</body>

</html>