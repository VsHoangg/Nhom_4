

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sửa phim</title>
        <link href="assets/css/stylesignup.css" rel="stylesheet" type="text/css"/>
        <!--<link rel="stylesheet" href="./assets/css/stylesignup.css"/>-->
        <script src="./add/ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="title">Đổi thông tin phim</div>
            <c:set var="f" value="${requestScope.film}"/>
            <form action="changefilm" method="POST"  >
                <span class="details">Tên phim</span>
                <input type="text" name="id" readonly="" value="${f.filmID}">
                <br/>
                <span class="details">Độ hot</span>
                <input type="text" name="hot" required value="${f.hotLevel}">
                <br/>
                <span class="details">Trạng thái</span>
                <input type="text"  name="status" required value="${f.status}">
                <br/>
                <span class="details">Ngày công chiếu</span>
                <input type="date" name="pldate" required value="${f.publish_date}">
                <br/>

                <span class="details">Giá vé</span>
                <input type="text" name="price" required value="${f.price}">
                <br/>
                <span class="details">Thời lượng</span>
                <input type="text" name="duration" required value="${f.duration}">
                <br/>
                <span class="details">Chi tiết phim</span>
                <br/>
                <textarea id="editor1" name="detail" rows="10" cols="80" placeholder="Enter detail" required >${f.information}</textarea>
                <br/>
                <span class="details">Ảnh</span>
                <input type="file" placeholder="Enter image" name="image">
                <br/>
                <div class="button">
                    <input type="submit" value="Change">
                </div>
                <script>
                    // Replace the <textarea id="editor1"> with a CKEditor 4
                    // instance, using default configuration.
                    CKEDITOR.replace('editor1');
                </script>
            </form>
        </div>
    </div>
</body>
</html>
