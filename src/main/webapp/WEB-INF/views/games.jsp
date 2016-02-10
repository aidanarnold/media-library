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

<title>Media Library | Games</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span>
				</button>
				<a class="navbar-brand" href="/">Media Library</a>
				<ul class="nav navbar-nav">
					<li><a href="/">Home</a></li>
					<li><a href="books">Books</a></li>
					<li class="active"><a href="games">Games</a></li>
					<li><a href="movies">Movies</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<c:choose>
			<c:when test="${!empty games}">
				<table class="table table-striped">
					<tr>
						<th>Title</th>
						<th>Platform</th>
						<th>Played?</th>
						<th colspan="2"></th>
					</tr>
					<c:forEach var="game" items="${games}">
						<tr>
							<td><c:out value="${game.title}" /></td>
							<td><c:out value="${game.platform}" /></td>
							<td><c:choose>
									<c:when test="${game.isPlayed}">
								Yes
								</c:when>
									<c:otherwise>
								No
								</c:otherwise>
								</c:choose></td>
							<td><a href="<%=request.getContextPath()%>/game/update/${game.id}" role="button"
								class="btn btn-primary"> <span
									class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							</a></td>
							<td><a href="<%=request.getContextPath()%>/game/delete/${game.id}"
								onclick="return confirmDelete()">
									<button type="button" class="btn btn-danger"
										aria-label="Delete">
										<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									</button>
							</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>

			<c:otherwise>
				<h4>No games added yet.</h4>
			</c:otherwise>
		</c:choose>
		<a href="/media/game/add" role="button" class="btn btn-primary"> <span
			class="glyphicon glyphicon-plus" aria-hidden="false"></span> Add New
			Game
		</a>
	</div>
</body>
</html>
