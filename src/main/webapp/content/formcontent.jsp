<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<c:url value="/assets/css/mystyle.css" />" rel="stylesheet">
<link href="<c:url value="/assets/css/mystyle5.css" />" rel="stylesheet">
<script src="<c:url value="/assets/js/main.js " />"></script>
<style>
	.form__name.invalid .form__message {
    color: var(--rose-pearl-color);
}
</style>
</head>

<body>

	<div class="form_content">
		<h4 class="app__validate"></h4>
		<div class="form_content_heading">
			<h1 class="heading">Add Content</h1>
		</div>
		<div class="add__content">
			<div class="form__title">Content Form Elements</div>

			<c:if test="${addcontent == null}">
				<form class="form__content" id="form-1" action="/ProjectJava/addcontent" method="post">
			</c:if>
			<c:if test="${addcontent != null}">
				<form class="form__content" id="form-1" action="/ProjectJava/addcontent" method="post">
				
			</c:if>

			<!-- <form class="form__content" id="form-1"  method="Post"> -->

			<c:if test="${addcontent != null}">
				<input type="hidden" name="id"
					value="<c:out value='${addcontent.id}' />" />
			</c:if>

			<div class="form__name">
				<label class="label_form">Title</label>
				<!-- <p> title</p> -->
				<input type="text" id="title"
					value="<c:out value='${addcontent.title}' />"
					rule="isRequired|min:10|max:200" class="filed__name" name="title"
					placeholder="Enter the title"> <span class="form__message"></span>
			</div>

			<div class="form__name">
				<label class="label_form">Brief</label>
				<!-- <p> Brief</p> -->
				<textarea id="brief" name="brief"
					rule="isRequired|min:3|max:150" class="filed__name"
					placeholder=" " style="height: 100px"><c:out value='${addcontent.brief}' /></textarea>
				<span class="form__message"></span>
			</div>

			<div class="form__name">
				<label class="label_form">Content</label>
				<!-- <p>Content</p> -->
				<textarea id="content" name="content"
					rule="isRequired|min:5|max:1000" class="filed__name"
					placeholder=" " style="height: 180px"><c:out value='${addcontent.content}' /></textarea>
				<span class="form__message"></span>
			</div>
			<div class="form__name">
				<button type="submit" class="btn btn-submit">Submit Button</button>
				<button type="reset" class="btn btn-reset">Reset Button</button>
			</div>

			</form>
		</div>
	</div>



	<script>
		const formNameSelector = '.form__name';
		const formMessage = '.form__message';
		const invalidForm = '.app__validate';
		var form1 = new Validator('#form-1');
	</script>
</body>

</html>