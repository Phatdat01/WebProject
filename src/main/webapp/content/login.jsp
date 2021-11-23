<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Login page</title>
<link href="<c:url value="/assets/css/mystyle.css " />" rel="stylesheet">
<link href="<c:url value="/assets/css/mystyle3.css " />" rel="stylesheet">
<script src="<c:url value="/assets/js/main.js " />"></script>
</head>

<body>
	<div class="app-login">
		<h2 class="msg_text">
			<%= request.getAttribute("msg") != null ? request.getAttribute("msg") : " " %>
		</h2>
		<!-- form không hợp lệ thì thêm class="form-invalid" -->

		<h4 class="app__validate"></h4>
		<form action="/ProjectJava/logincontroller" method="Post" class="form__login" id="form-1">
			<h2 class="login__heading">Please Sign In</h2>
			<div class="form__name">
				<input name="email" type="email"
					rule="isRequired|isEmail|max:50|min:5" class="filed__name"
					placeholder="E-mail"> <span class="form__message"></span>
			</div>
			<div class="form__name">
				<input name="password" rule="isRequired|min:8|max:30"
					type="password" class="filed__name" placeholder="Password">
				<span class="form__message"></span>
			</div>

			<div class="form__name">
				<div class="form__name__remember">
					<input name="remember" type="checkbox"
						class="form__name__remember-input"> Remember me
				</div>
				<span class="form__message"></span>
			</div>
            <input class="form__submit" type="submit" value="Login">
            <a class="login__link" href="/ProjectJava/view/register.jsp"> Click here to Register </a>
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