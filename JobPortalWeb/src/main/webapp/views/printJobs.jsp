<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
<script type="text/javascript"
	src=" https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script type="text/javascript"
	src=" https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<!-- Below are the jQuery scripts required for Data Tables. -->
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>

<!-- Initialization code of data table at the time of page load. -->
<script>
	$(document).ready(function() {
		$('#myDatatable').DataTable({
		});
	});
</script>
<style type="text/css">
body {
	margin: 10px;
}
h1 {
    margin-left: 10px;
    padding-left: 10px;
}
.jumbotron {
	height: auto;
	padding: 5px 0 !important;
}
.dataTables_wrapper {
    margin-top: 35px;
}
</style>

</head>
<body>
	<div class="jumbotron">
		<h1>Job Portal</h1>
	</div>
	<h2>
		<a href="/addJobView" class="btn btn-outline-info">Add New Job</a>
	</h2>

	<c:if test="${not empty list}">
		<table border="1" id="myDatatable" class="display datatable">
			<thead>
				<tr>
					<th>ID</th>
					<th>Title</th>
					<th>Location</th>
					<th>Company</th>
					<th>Skill Set</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="listValue" items="${list}">
					<tr>
						<td>${listValue.jobId}</td>
						<td>${listValue.jobTitle}</td>
						<td>${listValue.location}</td>
						<td>${listValue.companyName}</td>
						<td>${listValue.skillSet}</td>
						<td><a href="/updateJob/${listValue.jobId}" class="btn btn-outline-info btn-sm">Edit</a> <a
							href="/deleteJob/${listValue.jobId}" class="btn btn-outline-info btn-sm">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>