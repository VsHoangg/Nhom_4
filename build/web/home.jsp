

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>home</title>
        <link rel="stylesheet" href="./assets/css/style.css"/>
        <script src="./add/ckeditor/ckeditor.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
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
                    Hello  <a href="changein4?id=${ad.userID}" style="padding: 0 10px;text-decoration: none;color: white">${ad.name}  | </a>         
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
            <div class="content-heading">
                <ul id="nav">
                    <li>Phim sắp chiếu</li>
                </ul>
            </div>

            <div class="filmlist">
                <c:forEach items="${requestScope.film}" var="p">
                    <div class="movie">
                        <a href="view?id=${p.filmID}"><img src="movie/image?name=${p.image}" width="100" height="200" /></a>

                        <p>${p.filmID}</p>
                    </div> 
                </c:forEach>
            </div>
            <div class="content-heading">
                <ul id="nav">
                    <li>Phim đang chiếu</li>
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
            <c:set var="page" value="${requestScope.page}"/>
            <div class="pagination">
                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                    <c:if test="${page==i}">
                        <a href="list?page=${i}" id="choosed">${i}</a>
                    </c:if>
                    <c:if test="${page!=i}">
                        <a href="list?page=${i}" >${i}</a>
                    </c:if>
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
