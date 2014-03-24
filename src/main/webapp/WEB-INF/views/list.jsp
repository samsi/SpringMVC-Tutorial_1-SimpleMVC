<!-- Add this to enable JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html><head><meta charset="UTF-8"><title>Spring MVC - Tutorial 1: Data List</title></head>
<body>
	<table border="1">
		<tr><th>Name</th><th>Age</th><th>Options</th></tr>
		<c:choose>
			<c:when test="${empty personData}">
				<!-- if personData is empty, show "No data" -->
				<tr><td colspan="4" align="center">No Data</td></tr>
			</c:when>
			<c:otherwise>
				<!-- if it's not empty, show the data-->
				<c:forEach items="${personData}" var="personData">
					<tr>
						<td>${personData.name}</td>
						<td>${personData.age}</td>
						<td>
							<a href="${pageContext.request.contextPath}/edit?id=${personData.id}">Edit</a>
							&nbsp;|&nbsp;
							<a href="${pageContext.request.contextPath}/delete/${personData.id}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr><td colspan="4" align="center">
			<!-- "pageContext.request.contextPath" returns path of current application (use this for relative links) -->
			<a href="${pageContext.request.contextPath}/form">Add new data</a>
		</td></tr>
	</table>
</body>
</html>