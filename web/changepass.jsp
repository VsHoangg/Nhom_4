
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link rel="stylesheet" href="./assets/css/stylelogin.css"/>
    </head>
    <body>
        <div class="center">
            <h1>Change Password</h1>
            <h3 style="text-align: center">${requestScope.error}</h3>
            <form action="changepass" method="POST">
                <div class="txt_field">
                    <input type="text" name="user"  " required>
                    <span></span>
                    <label>username</label>
                </div>
                <div class="txt_field">
                    <input type="password" name="oldpass" required>
                    <span></span>
                    <label>Old Password</label>
                </div>
                <div class="txt_field">
                    <input type="password" name="newpass" required>
                    <span></span>
                    <label>New Password</label>
                </div>
                 <div class="txt_field">
                    <input type="password" name="repass" required>
                    <span></span>
                    <label>Confirm Password</label>
                </div>
                <input type="submit" value="Change"/>
            </form>
            <div class="signup_link">
                
            </div>
        </div>
    </body>
</html>
