

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function confirmSubmit() {
                if (confirm("Are you sure you want to submit the form?")) {
                    document.getElementById("FORM_ID").submit();
                }
                return false;
            }
        </script>
    </head>
    <body>
        <form action="deletefilm" method="POST" id="FORM_ID" onsubmit="confirmSubmit()">
            Chọn phim:
            <select name="film">
                <c:forEach items="${requestScope.data}" var="p">
                    <option value="${p.filmID}">${p.filmID}</option>

                </c:forEach>
            </select>
            <input type="submit" value="delete" >
        </form>
    </body>
</html>
