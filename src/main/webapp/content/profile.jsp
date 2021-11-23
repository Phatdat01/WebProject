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
</head>
<link href="<c:url value="/assets/css/mystyle.css " />"
	rel="stylesheet">
<link href="<c:url value="/assets/css/mystyle2.css " />"
	rel="stylesheet">


<body>
	<div class="app">
		<!-- form không hợp lệ thì thêm class="form-invalid" -->
		<h4 class="app__validate"></h4>

		<div class="form_profile_heading">
			<h1 class="heading">Edit Profile</h1>
		</div>
		<div class="profile">
			<div class="form_title">Profile form Elements</div>
			<!-- action="/DoAnJava/profilecontroller" method="POST"-->
			<form class="form__login form__login-js" id="form-1">
				<div class="form__name">
					<label class="form_lable">First Name</label> <input
						name="firstname" id="firstname" type="text"
						value="<c:out value=" ${objRegister.firstname} " />"
						rule="isRequired|min:3|max:30" class="filed__name"
						placeholder="Enter the first name"> <span
						class="form__message"></span>
				</div>
				<div class="form__name">
					<label class="form_lable">Last Name</label> <input name="lastname"
						id="passSignUp"
						value="<c:out value=" ${objRegister.lastname} " />"
						id="description" rule="isRequired|min:3|max:30" type="text"
						class="filed__name" placeholder="Enter the last name"> <span
						class="form__message"></span>
				</div>

				<div class="form__name">
					<!-- 
                <c:out value="${email}"/>-->
					<label class="form_lable">Email</label> <input name="email"
						id="email" readonly rule="isRequired|isEmail"
						value="<c:out value=" ${sessionScope.userLogin.email} " />"
						type="email" class="filed__name filed__name--email" placeholder="">
					<span class="form__message"></span>
				</div>

				<div class="form__name">
					<label class="form_lable">Phone</label> <input name="phone"
						id="phone" rule="isRequired|min:9|max:13" type="text"
						value="<c:out value=" ${objRegister.phone} " />" id="description"
						class="filed__name" placeholder="Enter your phone number">
					<span class="form__message"></span>
				</div>

				<div class="form__name">
					<label class="form_lable">Description</label>
					<textarea name="description" id="description"
						rule="isRequired|max:200" type="text" class="filed__name"
						style="padding: 0.5rem 1rem; line-height: 1.6rem; height: 67px;"><c:out
							value="${objRegister.description}" /></textarea>
					<span class="form__message"></span>
				</div>

				<button type="submit" class="btn btn-submit btn-submit-js">Submit
					Button</button>
				<button type="reset" class="btn btn-reset">Reset Button</button>

			</form>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	<script src="<c:url value="/assets/js/main.js " />"></script>
	<script>
                    const formNameSelector = '.form__name';
                    const formMessage = '.form__message';
                    const invalidForm = '.app__validate';
                    var form1 = new Validator('#form-1', false);
     </script>


</body>

</html>