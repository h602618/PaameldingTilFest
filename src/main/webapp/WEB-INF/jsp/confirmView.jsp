<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Participation Confirmation</title>
        <link rel="stylesheet" href="simple.css">
    </head>

    <body>
        <h2>Participant Confirmation</h2>
        <p>Participation recieved for</p>
        <p>
            ${user.first_name}<br/>
            ${user.last_name}<br/>
            ${user.phone}<br/>
            ${user.gender}
        </p>

        <a href="/participants">Go to participants</a>
    </body>
</html>