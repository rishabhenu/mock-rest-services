<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mock Services</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://unpkg.com/vue@2.1.10/dist/vue.js"></script>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
</head>
<body>
	<div id="app">
		<div>{{name}}</div>
		<a v-bind:href='link'>Home Page</a>
		<div @click='getUrl'>{getUrl}</div>
	</div>
	<script type="text/javascript" src="${context}/resources/js/main.js">
		new Vue({
			el : '#app',
			data : {
				name : "Rishabh Sharma",
				link : "${context}/get-all-services",
				url : ''
			},
			methods :{
				getUrl : function(){
					return location.host+'/'+this.link;
				}
			}
		});
	</script>
</body>
</html>