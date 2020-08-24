<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Job</title>
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script type="text/javascript"
	src=" https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	margin: 10px;
}
h1 {
    margin-left: 10px;
    padding-left: 10px;
}
.card-body {
    background: lightgray;
}
.jumbotron {
	height: auto;
	padding: 5px 0 !important;
}
</style>
</head>
<body>
	<div class="jumbotron">
		<h1>Job Portal</h1>
	</div>
	<div class="container py-3">
		<div class="row">
			<div class="mx-auto col-sm-6">
				<!-- form user info -->
				<div class="card">
					<div class="card-header">
						<h4 class="mb-0">Add New Job</h4>
					</div>
					<div class="card-body">
						<form action="/addJob" method="post">
							<div class="form-group row">
								<label for="lblTitle" class="col-sm-3 col-form-label">Title
								</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" id="jobTitle"
										name="jobTitle" placeholder="Enter Title">
								</div>
							</div>
							<div class="form-group row">
								<label for="lblCompany" class="col-sm-3 col-form-label">Company
								</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" id="companyName"
										name="companyName" placeholder="Enter Company Name">
								</div>
							</div>
							<div class="form-group row">
								<label for="lblLocation" class="col-sm-3 col-form-label">Location
								</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" id="location"
										name="location" placeholder="Enter Location">
								</div>
							</div>
							<div class="form-group row">
								<label for="lblSkillSet" class="col-sm-3 col-form-label">Skill
									Set </label>
								<div class="col-lg-8">
									<input type="text" class="form-control" id="skillSet"
										name="skillSet" aria-describedby="skillHelp"
										placeholder="Enter skill set"> <small id="skillHelp"
										class="form-text text-muted">Please enter comma(',')
										separated values.</small>
								</div>
							</div>
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>