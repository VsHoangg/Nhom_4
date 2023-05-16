

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm phim</title>
        <link href="assets/css/stylesignup.css" rel="stylesheet" type="text/css"/>
        <!--<link rel="stylesheet" href="./assets/css/stylesignup.css"/>-->
        <script src="./add/ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="title">Thông tin phim</div>

            <form action="addfilm" method="POST"  enctype="multipart/form-data">
                <span class="details">Tên phim</span>
                <input type="text" name="id" required>
                <br/>
                <span class="details">Độ hot</span>
                <input type="number"name="hot" required>
                <br/>
                <span class="details">Trạng thái</span>
                <input type="text"  name="status" required>
                <br/>
                <span class="details">Ngày công chiếu</span>
                <input type="date" name="pldate" required>
                <br/>

                <span class="details">Giá vé</span>
                <input type="number" name="price" required>
                <br/>
                <span class="details">Thời lượng</span>
                <input type="number"name="duration" required>
                <br/>
                <span class="details">Chi tiết phim</span>
                <br/>
                <textarea id="editor1" name="detail" rows="10" cols="80" placeholder="Enter detail" required></textarea>
                <br/>
                <span class="details">Ảnh</span>
                <input type="file" placeholder="Enter image" name="image" required>
                <br/>
                <div class="button">
                    <input type="submit" value="Add">
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
