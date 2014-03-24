<!-- Add this to enable Spring Form Tags -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML>
<html>
<head><meta charset="UTF-8"><title>Spring MVC - Tutorial 1:  Data Form</title></head>
<body>
	<form:form method="post" action="${pageContext.request.contextPath}/${linkSubmit}" modelAttribute="personData">
		<table border="0" role="presentation">
			<tr>
				<!-- "path" value is a member of current modelAttribute -->
				<form:hidden path="id" />
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" placeholder="insert name..." /></td>
			</tr>
			<tr>
				<td><form:label path="age">Age</form:label></td>
				<td><form:input path="age" placeholder="insert age..." /></td>
			</tr>
			<tr>
				<td><button type="reset" value="Reset">Reset</button></td>
				<td><input type="submit" value="${linkSubmit}" style="text-transform: capitalize;"/></td>
			</tr>
		</table>
	</form:form>
</body>
</html>