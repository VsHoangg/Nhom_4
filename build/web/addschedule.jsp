
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./assets/css/stylesignup.css"/>
    </head>
    <body>
        <div class="container">
            <div class="title">Add Schedules</div>
            <h3>${requestScope.error}</h3> 
            <div class="content">
                <form action="addschedulefilm" method="POST">
                    <div class="user-details">
                        <div class="input-box">
                            <span class="details">Phim:</span>
                            <input type="text" name="filmid" value="${requestScope.film.filmID}" style="width: 200%"/>
                        </div>
                    </div>
                    <div class="user-details">
                        <div class="input-box">
                            <span class="details">Ngày:</span>
                            <input type="date" name="day"/>
                        </div>
                    </div>
                    <div class="gender-details">
                        <c:forEach  items="${requestScope.schedules}" var="s">
                            <input type="checkbox" name="schedule" value="${s.startTime}"/>
                            ${s.startTime}-${s.endTime}<br/>
                        </c:forEach>
                    </div>
                    <div class="button">
                        <input type="submit" value="Add">
                    </div>
                </form>
            </div>
        </div>

    </body>
</html>
