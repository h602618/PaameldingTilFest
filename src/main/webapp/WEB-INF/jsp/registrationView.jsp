<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
    <head>
        <title>Registation</title>
        <link href="simple.css" rel="stylesheet" type="text/css">
        <link href="main.css" rel="stylesheet" type="text/css">
        <script src="js/myscript.js" defer></script>
    </head>

    <body>
        <h2>Registration</h2>

        <form method="post">
            <fieldset>
                <label for="first-name">First name</label>
                <input id="first-name" name="first-name"
                       title="First name can must start with Uppercase then lowercase"
                       pattern="\s*\p{Lu}\p{Ll}+\s*"
                       placeholder="Fill in first name"
                       required type="text">
                <span class="error">${firstNameError}</span>


                <label for="last-name">Last name</label>
                <input id="last-name" name="last-name"
                       title="Last name can must start with Uppercase then lowercase"
                       pattern="\s*\p{Lu}\p{Ll}+\s*" placeholder="Fill in last name"
                       required type="text">
                <span class="error">${lastNameError}</span>


                <label for="phone">Phone (8 digits)</label>
                <input id="phone" name="phone" title="Phone number must be exactly 8 digits" pattern="\d{8}"
                       placeholder="Fill in your phone number"
                       required type="tel">
                <span class="error">${phoneError}</span>


                <label for="password">Password</label>
                <input id="password" name="password"
                       title="Must be at least 4 characters, only letters letters and numbers"
                       pattern="[\p{L}\d]{4,}"
                       placeholder="Choose a password"
                       type="password" required oninput="validateStrength(this)">
                <span class="error">${passwordError}</span>


                <label for="repeat-password">Password repeated</label>
                <input id="repeat-password" name="repeat-password"
                       title="Must match the above password"
                       pattern="[\p{L}\d]{4,}"
                       placeholder="Repeat the password"
                       type="password" required oninput="validatePassword()">
                <span class="error">${passwordMatchError}</span>


                <label>Gender:</label>
                <input id="gender_male" name="gender" checked="checked" type="radio" value="male">male
                <input id="gender_female" name="gender" type="radio" value="female">female
                <span class="error">${genderError}</span>

                <br/><br/>
                <input type="submit" value="Sign me up"/>
            </fieldset>
        </form>


    </body>
</html>