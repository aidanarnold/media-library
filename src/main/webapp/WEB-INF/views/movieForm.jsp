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
		<form class="form-horizontal" method="POST" action="<%=request.getContextPath()%>/movie/add">
			<c:choose>
				<c:when test="${empty movie.id}">
					<legend>Add New Movie</legend>
				</c:when>
				<c:otherwise>
					<legend>Update Movie</legend>
				</c:otherwise>
			</c:choose>
			<div>
				<form:hidden path="movie.id" />
				<form:input path="movie.id" value="${movie.id}" type="hidden" />
			</div>
			<div class="form-group">
				<form:label path="movie.title" class="control-label col-sm-2">Title</form:label>
				<div class="col-sm-4">
					<form:input type="text" path="movie.title" class="form-control"
						placeholder="Title" />
				</div>
				<div>
					<form:errors path="movie.title" cssStyle="color: #ff0000;"></form:errors>
				</div>
			</div>

			<div class="form-group">
				<form:label path="movie.year" class="control-label col-sm-2">Year</form:label>

				<div class="col-sm-4">
					<form:input type="text" path="movie.year" class="form-control"
						placeholder="1900" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Watched?</label>
				<div class="col-sm-4">
					<form:checkbox path="movie.isWatched" value="Watched" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Save</button>
					<a href="<%=request.getContextPath()%>/movies"><button type="button"
							class="btn btn-default">Cancel</button></a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>