

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change</title>
        <link rel="stylesheet" href="./assets/css/stylesignup.css"/>
    </head>
    <body>
        <div class="container">
    <div class="title">Change information</div>
    <h3>${requestScope.error}</h3> 
    <c:set var="ad" value="${requestScope.acc}"/>
    <div class="content">
        <h1>${acc.userID}</h1>
        <form action="changein4" method="POST">
            <input type="hidden" name="id" value="${acc.userID}"/>
        <div class="user-details">
          <div class="input-box">
            <span class="details">Username</span>
            <input type="text" placeholder="Enter your username" name="username" value="${ad.name}" required>
          </div>
          <div class="input-box">
            <span class="details">Email</span>
            <input type="text" placeholder="Enter your email" name="email" value="${ad.email}" required>
          </div>
            
          <div class="input-box">
            <span class="details">Addresss</span>
            <input type="text" placeholder="Enter your Address" name="address" value="${ad.address}" required>
          </div>
          <div class="input-box">
            <span class="details">Phone Number</span>
            <input type="text" placeholder="Enter your number" name="number" value="${ad.phonenumber}" required>
          </div>
          
        </div>
        <div class="button">
          <input type="submit" value="Change">
        </div>
      </form>
    </div>
  </div>
    </body>
</html>
