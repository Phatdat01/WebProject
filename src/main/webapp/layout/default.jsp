<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><tiles:getAsString name="title" /></title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700&display=swap"
	rel="stylesheet">


</head>


<body>

	<div class="main-app">
		<div class="header">
			<tiles:insertAttribute name="header" />
		</div>

		<div class="content">
			<tiles:insertAttribute name="navigation" />

			<div class="view-content">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	<script src="<c:url value="/assets/js/main.js" />">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script>
                var viewContentBar = document.querySelector('.navbar__content');
                var viewContent = document.querySelector('.view-content');
                viewContentBar.onclick = function(e) {
                    viewContent.innerHTML = 'Loading';
                    viewContent.style.fontSize = '24px';
                    viewContent.style.margin = '40px';
                    viewContent.style.lineHeight = '26px';
                    e.preventDefault();
                    var serviceURL = '/ProjectJava/content';
                    var model = null;
                    setTimeout(function() {
                        $.ajax({
                            type: "GET",
                            url: serviceURL,
                            data: JSON.stringify(model),
                            contentType: "application/json; charset=utf-8",
                            dataType: 'html',
                            success: function(data) {
                                $('.main-app').load(serviceURL);
                            },
                            error: function(data, errorThrown) {
                                alert(errorThrown);
                            }
                        });
                    }, 5000);
                }
            </script>

</body>

</html>