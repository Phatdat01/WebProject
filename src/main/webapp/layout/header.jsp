<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>form</title>
<link href="<c:url value="/assets/css/mystyle.css " />" rel="stylesheet">
<link href="<c:url value="/assets/css/mystyle4.css " />" rel="stylesheet">

</head>

<body>
	<h2 class="header__title">CMS</h2>
	<!-- header__account--appear : hiện account -->
	<div class="header__account ">
		<i class="fa fa-user header__account-icon"></i> <i
			class="fa fa-caret-down"></i>

		<div class="header-user">
			<a href="/ProjectJava/profilecontroller"
				class="header-user__profile header-user__profile--border"> <i
				class="fa fa-user"></i> User Profile
			</a> <a href="/ProjectJava/logincontroller?action=logout"
				class="header-user__profile"> <i class="fa fa-sign-out"></i>
				Logout
			</a>
		</div>
	</div>
<script>
var accountUser = document.querySelector('.header__account');

var app = document.querySelector('.main-app');


	accountUser.onclick = (event) => {
		event.stopPropagation();
		accountUser.style.backgroundColor = "#EEEEEE";
		accountUser.classList.add('header__account--appear');

	};



	app.onclick = () => {
		accountUser.style.backgroundColor = "";
		accountUser.classList.remove('header__account--appear');
	};

</script>
</body>

</html>