

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up</title>
        <link rel="stylesheet" href="./assets/css/stylesignup.css"/>
    </head>
    <body>
        <div class="container">
            <div class="title">Registration</div>
            <h3>${requestScope.error}</h3> 
            <div class="content">
                <form action="signup" method="POST">
                    <div class="user-details">
                        <div class="input-box">
                            <span class="details">Username</span>
                            <input type="text" placeholder="Enter your username" name="username" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Email</span>
                            <input type="text" placeholder="Enter your email" name="email" required>
                        </div>

                        <div class="input-box">
                            <span class="details">Addresss</span>
                            <input type="text" placeholder="Enter your Address" name="address" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Phone Number</span>
                            <input type="text" placeholder="Enter your number" name="number" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Password</span>
                            <input type="password" placeholder="Enter your password" name="pass" required>
                        </div>
                    </div>
                    <div class="gender-details">
                        <span class="gender-title">Gender:</span>
                        <input type="radio" name="gender" id="dot-1" value="male">
                        <input type="radio" name="gender" id="dot-2" value="female">
                        <div class="category">
                            <label for="dot-1">
                                <span class="dot one"></span>
                                <span class="gender">Male</span>
                            </label>
                            <label for="dot-2">
                                <span class="dot two"></span>
                                <span class="gender">Female</span>
                            </label>
                        </div>
                    </div>
                    <div class="button">
                        <input type="submit" value="Register">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
