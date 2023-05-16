

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
                    Hello ${ad.name}              
                    <a style="color: white;text-decoration: none" href="logout" >Đăng xuất</a>
                </div>
            </c:if>


        </div>
        <div id="header">
            <ul id="nav">
                <li><a href="list" >Phim hot</a></li>
                <li><a href="list" >Giá vé</a></li>
            </ul>

        </div>
        <h1 style="margin-top: 70px;" >Tổng doanh thu tháng: ${requestScope.sum}</h1>

        <div style="padding: 30px 60px;">
            <h1>Số lượng vé đặt theo khung giờ</h1>
            <table border="1" style="margin: 0 auto;text-align: center;">
                <tr>
                    <th>Khung giờ</th>
                    <th>Số lượng vé đặt</th>
                </tr>
                <c:forEach items="${requestScope.listsche}" var="ls">
                    <tr>
                        <td>${ls.startTime}-${ls.endTime}</td>
                        <td>${ls.count}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="break">

        </div>
        <div style="padding: 30px 60px;">
            <h1>Top 5 người dùng mua nhiều nhất</h1>
            <table border="1" style="margin: 0 auto;text-align: center;">
                <tr>
                    <th>Tên</th>
                    <th>Chi phí</th>
                </tr>
                <c:forEach items="${requestScope.listU}" var="lu">
                    <tr>
                        <td>${lu.name}</td>
                        <td>${lu.cost}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="break">

        </div>
        <div style="width: 40%; text-align: center;
             margin-top: 30px;float: left;">
            <h1>Doanh thu từng phim</h1>
            <canvas id="myChart"></canvas>
        </div>
        <div style="width: 55%; text-align: center;
             margin-top: 30px;float: left;">
            <h1>Số lượng vé từng phim</h1>
            <canvas id="myChartLine"></canvas>
        </div>


        <script>
            //line chart
            const labelsLine = [
            <c:forEach items="${requestScope.list}" var="i">
            '${i.filmID}',
            </c:forEach>
            ];
            const dataLine = {
            labels: labelsLine,
                    datasets: [{
                    label: 'My First Dataset',
                            data: [<c:forEach items="${requestScope.list}" var="i">
                ${i.sum},
            </c:forEach>],
                            fill: false,
                            borderColor: 'rgb(75, 192, 192)',
                            tension: 0.1
                    }]
            };
            const configLine = {
            type: 'line',
                    data: dataLine,
            };
            const myChartLine = new Chart(
                    document.getElementById('myChartLine'),
                    configLine
                    );
            //donut chart
            const data = {
            labels: [
            <c:forEach items="${requestScope.list}" var="i">
            '${i.filmID}',
            </c:forEach>
            ],
                    datasets: [{
                    label: 'My First Dataset',
                            data: [
            <c:forEach items="${requestScope.list}" var="i">
                ${i.sumturnover},
            </c:forEach>
                            0],
                            backgroundColor: [
                                    'rgb(255, 99, 132)',
                                    'rgb(54, 162, 235)',
                                    '#00ffff',
                                    '#0000ff',
                                    '#ff00ff',
                                    '#ff0000',
                                    '#ffff00',
                                    '#00cc00'
                            ],
                            hoverOffset: 4
                    }]
            };
            const config = {
            type: 'pie',
                    data: data,
            };
            const myChart = new Chart(
                    document.getElementById('myChart'),
                    config
                    );
        </script>

    </body>

</html>
