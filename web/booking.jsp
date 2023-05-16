

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./assets/css/stylebooking.css"/>
    </head>
    <body>
        
        <div class="movie-container">
            <label> Booking ticket of movie:${requestScope.movie.filmID}</label><br/>
            <c:set var="sc" value="${requestScope.sc}"/>
            <p> Ngày: ${sc.day}</p>
            <p> Giờ: ${sc.startTime}-${sc.endTime}</p>
            <ul class="showcase">
                <li>
                    <input type="checkbox" class="seat">
                    <!--<div class="seat"></div>-->
                    <small>Available</small>
                </li>
                <li>
                    <input type="checkbox" class="seat" checked>
                    <!--                    <div class="seat selected"></div>-->
                    <small>Selected</small>
                </li>
                <li>
                    <input type="checkbox" class="seat" checked disabled>
                    <!--<div class="seat sold"></div>-->
                    <small>Sold</small>
                </li>
            </ul>
        </div>
        <div class="container">
            <div class="screen"></div>
            <c:set var="boo" value="${requestScope.booked}"/>
            <form action="booking" method="POST">
                <div class="row">
                    
                    <input type="checkbox" class="seat" name="seat" id="A1" value="A1">
                    <input type="checkbox" class="seat" name="seat" id="A2" value="A2">
                    <input type="checkbox" class="seat" name="seat" id="A3" value="A3">
                    <input type="checkbox" class="seat" name="seat" id="A4" value="A4">
                    <input type="checkbox" class="seat" name="seat" id="A5" value="A5">
                    <input type="checkbox" class="seat" name="seat" id="A6" value="A6">
                    <input type="checkbox" class="seat" name="seat" id="A7" value="A7">
                    <input type="checkbox" class="seat" name="seat" id="A8" value="A8">
                </div>
                <div class="row">
                    <input type="checkbox" class="seat" name="seat" id="B1" value="B1">
                    <input type="checkbox" class="seat" name="seat" id="B2" value="B2">
                    <input type="checkbox" class="seat" name="seat" id="B3" value="B3">
                    <input type="checkbox" class="seat" name="seat" id="B4" value="B4">
                    <input type="checkbox" class="seat" name="seat" id="B5" value="B5">
                    <input type="checkbox" class="seat" name="seat" id="B6" value="B6">
                    <input type="checkbox" class="seat" name="seat" id="B7" value="B7">
                    <input type="checkbox" class="seat" name="seat" id="B8"value="B8">
                </div>
                <div class="row">
                    <input type="checkbox" class="seat" name="seat" id="C1" value="C1">
                    <input type="checkbox" class="seat" name="seat" id="C2" value="C2">
                    <input type="checkbox" class="seat" name="seat" id="C3" value="C3">
                    <input type="checkbox" class="seat" name="seat" id="C4" value="C4">
                    <input type="checkbox" class="seat" name="seat" id="C5" value="C5">
                    <input type="checkbox" class="seat" name="seat" id="C6" value="C6">
                    <input type="checkbox" class="seat" name="seat" id="C7" value="C7">
                    <input type="checkbox" class="seat" name="seat" id="C8"value="C8">
                </div>
                <div class="row">
                    <input type="checkbox" class="seat" name="seat" id="D1" value="D1">
                    <input type="checkbox" class="seat" name="seat" id="D2" value="D2">
                    <input type="checkbox" class="seat" name="seat" id="D3" value="D3">
                    <input type="checkbox" class="seat" name="seat" id="D4" value="D4">
                    <input type="checkbox" class="seat" name="seat" id="D5" value="D5">
                    <input type="checkbox" class="seat" name="seat" id="D6" value="D6">
                    <input type="checkbox" class="seat" name="seat" id="D7" value="D7">
                    <input type="checkbox" class="seat" name="seat" id="D8" value="D8">
                </div>
                <div class="row">
                    <input type="checkbox" class="seat" name="seat" id="E1" value="E1">
                    <input type="checkbox" class="seat" name="seat" id="E2" value="E2">
                    <input type="checkbox" class="seat" name="seat" id="E3" value="E3">
                    <input type="checkbox" class="seat" name="seat" id="E4" value="E4">
                    <input type="checkbox" class="seat" name="seat" id="E5" value="E5">
                    <input type="checkbox" class="seat" name="seat" id="E6" value="E6">
                    <input type="checkbox" class="seat" name="seat" id="E7" value="E7">
                    <input type="checkbox" class="seat" name="seat" id="E8" value="E8">
                </div>
                <div class="row">
                    <input type="checkbox" class="seat" name="seat" id="F1" value="F1">
                    <input type="checkbox" class="seat" name="seat" id="F2" value="F2">
                    <input type="checkbox" class="seat" name="seat" id="F3" value="F3">
                    <input type="checkbox" class="seat" name="seat" id="F4" value="F4">
                    <input type="checkbox" class="seat" name="seat" id="F5" value="F5">
                    <input type="checkbox" class="seat" name="seat" id="F6" value="F6">
                    <input type="checkbox" class="seat" name="seat" id="F7" value="F7">
                    <input type="checkbox" class="seat" name="seat" id="F8"value="F8">
                </div>
                <c:forEach items="${boo}" var="i">
                    <script>
           
                document.getElementById('${i}').checked
                        = true;
                  
                document.getElementById('${i}').disabled
                        = true;
            
        </script> 
                </c:forEach>
                <input type="text" name="film" value="${requestScope.movie.filmID}" hidden>
                <input type="text" name="price" value="${requestScope.movie.price}" hidden>
                <input type="text" name="sid" value="${sc.SID}" hidden>
                <input type="submit" value="book tickets" style="width: 100%;height: 40px;font-size: 15px; margin-top: 20px;"/>
            </form>

        </div>
    </body>
</html>
