<%@ page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mock Services</title>
<link href="${context}/resources/styling/fonts/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${context}/resources/styling/fonts/Quicksand-fonts.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${context}/resources/styling/bootstrap-grid.min.css"
	type="text/css">
<link rel="stylesheet" type="text/css"
	href="${context}/resources/styling/mock-services.css">

<script src="${context}/resources/js/head/vue.js"></script>
<script type="text/javascript"
	src="${context}/resources/js/head/axios.js"></script>
</head>
<body>
	<div id="app" class="container">
		<mock-services></mock-services>
	</div>
	<script src="${context}/resources/js/head/main.js"></script>
</body>
</html>