<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<img src="${pageContext.request.contextPath}/images/users/orange.jpg" alt="user_image">
<form action="${pageContext.request.contextPath}/registration" method="post" enctype="multipart/form-data">
    <label for="nameId" title="username"> Name:
        <input type="text" name="name" id="nameId" required>
    </label>
    <br/>
    <label for="birthdayId" title="birthday"> Birthday:
        <input type="date" name="birthday" id="birthdayId" required>
    </label>
    <br/>
    <label for="ImageId" title="file"> Image:
        <input type="file" name="image" id="ImageId">
    </label>
    <br/>
    <label for="emailId" title="email"> Email:
        <input type="email" name="email" id="emailId">
    </label>
    <br/>
    <label for="passwordId" title="password"> Password:
        <input type="password" name="password" id="passwordId">
    </label>
    <br/>
    <label for="roleId"> Role:
        <select name="role" id="roleId">
            <c:forEach var="role" items="${requestScope.roles}">
                <option value="${role}">${role}</option>
            </c:forEach>
        </select>
    </label>
    <br/>
    <c:forEach var="gender" items="${requestScope.genders}">
        <label>${gender}
            <input type="radio" name="gender" value="${gender}">
        </label>
        <br/>
    </c:forEach>
    <button type="submit">Submit</button>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span>
                <br/>
            </c:forEach>
        </div>
    </c:if>
</form>
</body>
</html>
