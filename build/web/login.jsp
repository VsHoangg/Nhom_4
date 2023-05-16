

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="./assets/css/stylelogin.css"/>
    </head>
    <body>
        <div class="center">
            <h1>Login</h1>
            <h3 style="text-align: center">${requestScope.error}</h3>
            <form action="login" method="POST">
                <div class="txt_field">
                    <input type="text" name="user" required>
                    <span></span>
                    <label>Username</label>
                </div>
                <div class="txt_field">
                    <input type="password" name="pass" required>
                    <span></span>
                    <label>Password</label>
                </div>
                <input type="submit" value="Login"/>
            </form>
            <div class="signup_link">
                Don't have account? <a href="signup" >Sign up</a>
            </div>
        </div>

    </body>
</html>
