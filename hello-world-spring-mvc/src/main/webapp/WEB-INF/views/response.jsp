<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="sections/head.jsp" />
</head>
<body class="m-4">
	<div class="container">
		<jsp:include page="sections/header.jsp" />
		<h1>Greetings: ${greetings}</h1>
	</div>
</body>
</html>