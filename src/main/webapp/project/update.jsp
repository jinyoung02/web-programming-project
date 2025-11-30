<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>북마크 수정</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f4f7f9;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        h1 {
            color: #333;
            border-bottom: 2px solid #eee;
            padding-bottom: 10px;
        }

        .form-box {
            margin-top: 20px;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 6px;
            background-color: #fafafa;
        }

        label {
            font-size: 15px;
            display: block;
            margin-top: 12px;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        .btn-save {
            background-color: #5cb85c;
            color: white;
            border: none;
            padding: 10px 18px;
            border-radius: 4px;
            margin-top: 15px;
            cursor: pointer;
        }

        .btn-save:hover {
            background-color: #4cae4c;
        }

        .btn-cancel {
            margin-left: 15px;
            color: #6a5acd;
            font-size: 15px;
            text-decoration: none;
        }

        .btn-cancel:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>북마크 수정</h1>

    <div class="form-box">
        <form action="link" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${link.id}">

            <label>이름</label>
            <input type="text" name="name" value="${link.name}" required>

            <label>URL</label>
            <input type="text" name="url" value="${link.url}" required>

            <button type="submit" class="btn-save">저장하기</button>
            <a href="link?action=list" class="btn-cancel">취소</a>
        </form>
    </div>

</div>

</body>
</html>