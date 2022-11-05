<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
    <head>
        <title>Log in</title>
        <link rel="stylesheet" href="simple.css">
    </head>
    <body>
        <h2>Log in</h2>
        <p style="color:red;">${redirectMessage}</p>
        <form method="post">
            <fieldset>
                <label for="phone">Phone:</label>
                <input type="tel" pattern="\d{8}" title="Must be exactly 8 digits" required name="phone"
                       id="phone"/>

                <label for="password">Password:</label>
                <input type="password" required name="password" id="password"/>

                <br/><br/>

                <input type="submit" value="Logg Inn"/>
            </fieldset>
        </form>
    </body>
</html>