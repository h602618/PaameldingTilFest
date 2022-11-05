<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="simple.css">
        <title>Participants</title>
    </head>

    <body>
        <p>Logged in as: ${userPhone} / ${userName}</p>
        <h2>Participants</h2>
        <table>
            <tbody>
                <tr>
                    <th>Gender</th>
                    <th align="left">Name</th>
                    <th align="left">Phone</th>
                </tr>

                <c:forEach var="user" items="${users}">
                    <tr style=<c:if test="${user.phone==userPhone}">"background-color:#aaffaa;"</c:if>>
                        <td align="center">
                            <c:choose>
                                <c:when test="${user.gender=='male'}">♂</c:when>
                                <c:otherwise>♀</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${user.name}</td>
                        <td>${user.phone}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <br>

        <form method="post">
            <input type="submit" value="Log Out"/>
        </form>
    </body>
</html>