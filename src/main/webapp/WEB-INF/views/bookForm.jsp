<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>

<html>
<head>
<script src="<c:url value="/resources/jquery/jquery-2.1.1-min.js" />"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>
<script src="<c:url value="/resources/custom/app.js" />"></script>
<link
	href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/bootstrap/css/bootstrap-theme.min.css" />"
	rel="stylesheet">

<title>Media Library</title>
</head>
<body>
	<br />
	<div class="container">
		<form class="form-horizontal" method="POST" action="<%=request.getContextPath()%>/book/add">
			<c:choose>
				<c:when test="${empty book.id}">
					<legend>Add New Book</legend>
				</c:when>
				<c:otherwise>
					<legend>Update Book</legend>
				</c:otherwise>
			</c:choose>
			<div>
				<form:hidden path="book.id" />
				<form:input path="book.id" value="${book.id}" type="hidden" />
			</div>
			<div class="form-group">
				<form:label path="book.title" class="control-label col-sm-2">Title</form:label>
				<div class="col-sm-4">
					<form:input type="text" path="book.title" class="form-control"
						placeholder="Title" />
				</div>
				<div>
					<form:errors path="book.title" cssStyle="color: #ff0000;"></form:errors>
				</div>
			</div>

			<div class="form-group">
				<form:label path="book.author" class="control-label col-sm-2">Author</form:label>

				<div class="col-sm-4">
					<form:input type="text" path="book.author" class="form-control"
						placeholder="Author" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Read?</label>
				<div class="col-sm-4">
					<form:checkbox path="book.isRead" value="Read" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Save</button>
					<a href="<%=request.getContextPath()%>/books"><button type="button"
							class="btn btn-default">Cancel</button></a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>