<%@ page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mock Services</title>
<link rel="stylesheet"
	href="${context}/resources/styling/bootstrap-grid.min.css" type="text/css">
<link rel="stylesheet" type="text/css"
	href="${context}/resources/styling/mock-services.css">
<script src="${context}/resources/js/vue.js"></script>
</head>
<body>
	<div id="app" class="container">
	<div style="display:none;" :onload='loadConstants({"context":"${context}"})'></div>
		<div class="row" v-once>
			<div class="col-sm-12 heading device"><fmt:message key="greet"/></div>
		</div>
		<div v-if="isHomePage" class="row info-text-area device" style="font-family: 'Courier New', Courier, monospace;">
			<div class="col-12"><strong>Steps To Use :-</strong></div>
			<div class="col-12">
				<fmt:message key="steps.to.use"><fmt:param>${baseUrl}</fmt:param></fmt:message>
			</div>
		</div>
		<div class="row">
			<a :href="allServicesLink">Click Here to see All Services</a>
		</div>
	</div>
	<script src="${context}/resources/js/main.js">
	</script>
</body>
</html>