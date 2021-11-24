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
<title>Document</title>
<link href="<c:url value="/assets/css/mystyle1.css" />" rel="stylesheet">

</head>
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
								href="/ProjectJava/addcontent?id=<c:out value='${row.id}' />">Edit</a>
								<span onclick="del(${row.id})" style="cursor: pointer">Delete</span>
								<c:set var="idcontent" value="${idcontent+1}" /></td>
						</tr>
					</c:forEach>
				</span>
			</table>
			<div>
				<c:forEach begin="1" end="${numPage}" var="i">
					<a class="page" data-idContent="<c:out value='${i}'/>"
						id="${current==i?"active":" "}" > <c:out value='${i}' />
					</a>
				</c:forEach>
				<button>previous</button>
			</div>
			
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
			
			</script>
			<script>
			
            	var deletebtn = document.querySelector('.del');
				
                	$("body").on("click", ".page", function(e) {
                    	
                		e.preventDefault();
                		
                        	var numPage = $(this).text().trim();
                        	
                        	
				          var serviceURL = '/ProjectJava/content?currentPage=' + numPage;
							var model = null;
						$.ajax({
							type: "GET",
							url: serviceURL,
							data: JSON.stringify(model),
							contentType: "application/json; charset=utf-8",
							dataType: 'html',
							success: function(data) {
								
								$('.main-app').html(data);
							},
							error: function(data, errorThrown) {
								alert(errorThrown);
							}
						});
                        	
                            
                	})

               
               
                
                function del(eeee) {
					var confirm = confirmBox();
                    if (confirm) {
						var serviceURL = '/ProjectJava/deletecontent?id=' +eeee;
						var model = null;
						$.ajax({
							type: "GET",
							url: serviceURL,
							data: JSON.stringify(model),
							contentType: "application/json; charset=utf-8",
							dataType: 'html',
							success: function(data) {
								$('.view-content').load('/ProjectJava/content');
							},
							error: function(data, errorThrown) {
								alert(errorThrown)
							}
							})
						}
                    }
				
// 				function del(idc) {
//                 	var confirm = confirmBox();
//                     if (confirm) {
//                     	$.ajax({
//                         	url: '/ProjectJava/deletecontent',
//                             type: 'POST',
//                             data: {
//                             	idc: idc
//                             }
//                         });
//                     }
//                  }

                 function confirmBox() {
                 	var result = confirm("Xoá thật à?");
                    return result;
                 }
        	</script>
		</div>
	</div>
</body>

</html>