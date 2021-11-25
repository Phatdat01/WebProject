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
<link href="<c:url value="/assets/css/mystyle.css" />" rel="stylesheet">
<style>
	 <%@ include file="/assets/css/mystyle1.css" %>
</style>
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
			<div class="changePage">
				<p id="numPage" style="display: none">
					<c:out value="${numPage}" />
				</p>
				<p id="current" style="display: none">
					<c:out value="${current}" />
				</p>
				<button class="page page-btn" id="subtract">Previous</button>
				<button class="page page-btn" id="add">Next</button>
			</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
				
				var numPage=parseInt(document.getElementById("numPage").innerHTML);
				function changeBtn(){
					var currentPage=parseInt(document.getElementById("current").innerHTML);
					
					var previousBtn = document.getElementById("subtract");
					var nextBtn = document.getElementById("add");
					
					if(currentPage == 1){
						previousBtn.style.display= 'none';
					}else{
						previousBtn.style.display= 'inline-block';
					}
					
					if(currentPage+1 > numPage){
						nextBtn.style.display= 'none';
					}else{
						nextBtn.style.display= 'inline-block';
					}
				}
				
			
				$("body").on("click", "#subtract", function() {
					var current=parseInt(document.getElementById("current").innerHTML);	
					current-=1;					
					
					var serviceURL = '/ProjectJava/content?current=' +current;
					$.ajax({
						type: "GET",
						url: serviceURL,
						success: function(data) {	
							$('.main-app').html(data);							
							changeBtn();
						},
						error: function(data, errorThrown) {
							alert(errorThrown);
						}
					})
				})
				
				$("body").on("click", "#add", function() {
					var current=parseInt(document.getElementById("current").innerHTML);
					
					current+=1;
					
					var serviceURL = '/ProjectJava/content?current=' +current;
					$.ajax({
						type: "GET",
						url: serviceURL,
						success: function(data) {
							
							$('.main-app').html(data);
							changeBtn();
							
						},
						error: function(data, errorThrown) {
							alert(errorThrown);
						}
					})
				})
				
				changeBtn();
 
                function del(id) {
                	var current=parseInt(document.getElementById("current").innerHTML);
                	alert(current)
                	var confirm=confirmBox();
                    if (confirm){
						var serviceURL = '/ProjectJava/deletecontent';
						$.ajax({
							type: 'POST',
							url: serviceURL,
							data:{
								idc:id,
								current: current
							},
							success: function(data) {
								
								$('.main-app').html(data);
								
							},
							error: function(data, errorThrown) {
								alert(errorThrown)
							}
						})
                    }
                 }
                
                 function confirmBox() {
                 	var result = confirm("Bạn muốn xóa thông tin này?");
                    return result;
                 }
		</script>
		</div>
	</div>
</body>
</html>