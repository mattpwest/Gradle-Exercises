<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h1>Corruption Bills</h1>

<c:forEach var="vehicle" items="${vehicles}">
    <h2>Vehicle : ${vehicle.registrationNumber}</h2>
    <p>Bill: R ${bills[vehicle]}</p>
</c:forEach>
</body>
</html>
