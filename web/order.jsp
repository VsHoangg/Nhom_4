

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
                <li><a href="list" >Phim hot</a></li>
                    <c:if test="${sessionScope.account.role != 1 }">
                    <li><a href="order" >Giỏ hàng</a></li>
                    </c:if>
            </ul>

        </div>
        <div class="content" style="margin-top: 65px">
            <div style="height: 200px"></div>
            <div id="user-infor">
                <c:set var="user" value="${requestScope.user}"/>
                ${requestScope.MESSAGE}
                <h2>Thông tin thanh toán: </h2>
                <p>User:${user.name} &nbsp;&nbsp;&nbsp;Phone: ${user.phonenumber}&nbsp;&nbsp;&nbsp;Email:${user.email}</p>
            </div>
            <div id="cart">
                <c:forEach items="${requestScope.carts}" var="carts">
                    <div class="orderline">
                        <div class="deletecart">
                            <form action="deletecart" method="POST">
                                <input type="text" name="cartid" value="${carts.OID}" hidden>
                                <input type="submit" value="Hoàn tác">
                            </form>
                        </div>
                        <div class="info">
                            <h1>${carts.OID}</h1>
                            <h1>Tên phim: ${carts.filmID}</h1>
                            <h1>Ngày: ${carts.sch.day}</h1>
                            <h1>Giờ: ${carts.sch.startTime}</h1>
                            <h1>Vị trí:
                                <c:forEach items="${carts.positions}" var="po">
                                    ${po}&nbsp;
                                </c:forEach>
                            </h1>
                        </div>


                        <div class="break">

                        </div>
                    </div>

                </c:forEach>
            </div>
            <h2>Tổng tiền: ${requestScope.sum}</h2>
            <c:set var="er" value="${requestScope.failed}"/>
            <c:if test="${er!=null}">
                <h1>Vị trí ${er} đã được đặt</h1>
                <a href="order">Trở lại giỏ hàng</a>
            </c:if>
            <c:if test="${er==null}">
                <form action="order" method="POST">
                    <input type="text" name="uid" value="${user.userID}" hidden><!-- comment -->
                    <input type="submit" value="Thanh toán">
                </form>
            </c:if>

        </div>



        <footer class="footer">
            <div class="container">
                Copyright@2023
            </div>
        </footer>
    </body>
</html>
