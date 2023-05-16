

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./assets/css/style.css"/>
    </head>
    <body>
        <c:set var="ad" value="${sessionScope.account}"/>
        <div id="user">
            <div class="search-bar">
                <form action="search" class="search-form">
                    <input type="text" placeholder="search film" name="film">
                    <button type="submit"><img src="./assets/images/free-search-icon-2911-thumb.png" /></button>
                </form>
            </div>
            <c:if test="${ad==null}">
                <ul id="user-nav">
                    <li><a href="login" >Đăng nhập</a></li>
                    <li><a href="signup" >Đăng kí</a></li>
                </ul>
            </c:if>
            <c:if test="${ad!=null}">
                <div id="user-info">
                    Hello <a href="changein4?id=${ad.userID}" style="padding: 0 10px;text-decoration: none;color: white">${ad.name}  | </a>         
                    <a href="changepass" style="padding: 0 10px;text-decoration: none;color: white">Đổi mật khẩu | </a>
                    <a style="color: white;text-decoration: none" href="logout" > Đăng xuất </a>

                </div>
            </c:if>


        </div>
        <div id="header">
            <ul id="nav">
                <li><a href="top" >Phim hot</a></li>
                    <c:if test="${sessionScope.account.role != 1 }">
                    <li><a href="order" >Giỏ hàng</a></li>
                    </c:if>
            </ul>

        </div>


        <div id="content">            
            <div class="content-heading" style="margin-top: 65px">
                <ul id="nav">
                    <h3 style="color: black">Top phim được xem nhiều nhất</h3>
                    <li><a href="top?cate=week">Trong tuần</a></li>
                    <li><a href="top?cate=month">Trong tháng</a></li>
                </ul>
            </div>
            <h3>${requestScope.error}</h3>
            <div class="filmlist">
                <c:forEach items="${requestScope.data}" var="p">
                    <div class="movie">
                        <a href="view?id=${p.filmID}"><img src="movie/image?name=${p.image}" width="100" height="200" /></a>

                        <p>${p.filmID}</p>
                    </div> 
                </c:forEach>
            </div>
        </div>
        <footer class="footer">
            <div class="container">
                Copyright@2023
            </div>
        </footer>
    </body>
</html>
