<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="navbar">
	<form action='/ProjectJava/content' method="post">
		<div class="navbar__search">
			<input class="navbar__search-input" type="text" name="search"
				id="search" placeholder="Search...">
			<button class="navbar__search-icon" type="submit">
				<i class="fa fa-search navbar__search-icon-search"></i>
			</button>
		</div>
	</form>
	<a href='/ProjectJava/content' onclick="clearSearch()"
		class="navbar__content"> <i class="fa fa-table"></i>
		<h4 class="navbar__content-text">View contents</h4>
	</a> 
	<a href="/ProjectJava/view/formcontent.jsp" class="navbar__content">
		<i class="fa fa-edit"></i>
		<h4 class="navbar__content-text">Form content</h4>
	</a>

	<script>
                function clearSearch() {
                    document.cookie = "search=;0,0;";
                }
            </script>
</div>