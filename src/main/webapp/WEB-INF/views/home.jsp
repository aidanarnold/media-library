<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
<script src="<c:url value="/resources/jquery/jquery-2.1.1-min.js" />"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>
<script src="<c:url value="/resources/custom/app.js" />"></script>
<link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/bootstrap/css/bootstrap-theme.min.css" />" rel="stylesheet">

<title>Media Library | Home</title>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
          </button>
          <a class="navbar-brand" href="<%=request.getContextPath()%>">Media Library</a>
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="books">Books</a></li>
            <li><a href="games">Games</a></li>
            <li><a href="movies">Movies</a></li>
          </ul>
	    </div>
	  </div>
   </nav>
<div class="container"> 
<h1>
	Hello there!  
</h1>

<P>  Select a media type above to manage your media. </P>
</div>  
</body>
</html>
