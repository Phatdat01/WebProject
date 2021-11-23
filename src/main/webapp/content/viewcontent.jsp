<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Document</title>
<link href="<c:url value="/assets/css/mystyle1.css" />" rel="stylesheet">

</head>

<script src="https://code.jquery.com/jquery-latest.js"></script>

<body>

	<div class="view-content__body">
		<h1 class="view-content__body-title">View Content</h1>
		<div class="view-content__wrap">
			<c:set var="idcontent" value="${(current-1)*10+1}" />
			<table id="viewtable" class="content-tb">
				<tr>
					<th class="tb-id">#</th>
					<th class="tb-title">Title</th>
					<th class="tb-brief">Brief</th>
					<th class="tb-cd">Created Date</th>
					<th class="tb-action">Action</th>
				</tr>
				<span name="loadcontent"> <c:forEach items="${tbcontents}"
						var="row">
						<tr>
							<td class="tb-id"><c:out value="${idcontent}" /></td>
							<td><c:out value="${row.title}" /></td>
							<td><c:out value="${row.brief}" /></td>
							<td><c:out value="${row.createdate}" /></td>
							<td><a
								href="/ProjectJava/editcontent?id=<c:out value='${row.id}' />">Edit</a>
								<span onclick="del(${row.id})" style="cursor: pointer">Delete</span>
								<c:set var="idcontent" value="${idcontent+1}" /></td>
						</tr>
					</c:forEach>
				</span>
			</table>
			<div>
				<c:forEach begin="1" end="${numPage}" var="i">
					<a class="page" id="${current==i?"
						active ":" "}" href="content?currentPage=${i}"> <c:out
							value='${i}' />
					</a>
				</c:forEach>
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


                                        $("body").on("click", ".page", function() {
                                            var numPage = $(this).text();
                                            $.ajax({
                                                url: '/ProjectJava/content',
                                                type: 'POST',
                                                data: {
                                                    currentPage: numPage
                                                },
                                                suscess: function(data) {
                                                    alert(data);
                                                }
                                            });
                                        })

                                        function del(idc) {
                                            var confirm = confirmBox();
                                            if (confirm) {
                                                $.ajax({
                                                    url: '/ProjectJava/deletecontent',
                                                    type: 'POST',
                                                    data: {
                                                        idc: idc
                                                    }
                                                });
                                            }
                                        }

                                        function confirmBox() {
                                            var result = confirm("Xoá thật à?");
                                            return result;
                                        }
                                    </script>
		</div>
	</div>
</body>

</html>