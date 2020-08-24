<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="details" method="post">
Enter First Name:<input type="text" name="fname">
<br />
<br />
Enter Last Name:<input type="text" name="lname">
<br />
<br />
<button type="submit" >Submit</button>
</form>
<c:if test="${not empty list}">
<table border="1">
    <tr>
        <th>First Name</th> <th>Last Name</th> <th>Full Name</th> 
    </tr>
    <c:forEach var="listValue" items="${list}">
        <tr>
            <td>${listValue.fname}</td>
            <td>${listValue.lname}</td>
            <td>${listValue}</td>
        </tr>
    </c:forEach>

 

</table>
</c:if>
</body>
</html>