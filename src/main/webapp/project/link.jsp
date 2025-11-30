<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>북마크 관리</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f7f9; }
        .container { max-width: 800px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }
        h1 { color: #333; border-bottom: 2px solid #eee; padding-bottom: 10px; }
        .add-form, .bookmark-list { margin-top: 20px; padding: 15px; border: 1px solid #ddd; border-radius: 6px; background-color: #fafafa; }
        input[type="text"], button { padding: 10px; margin-right: 5px; border-radius: 4px; border: 1px solid #ccc; }
        button { background-color: #5cb85c; color: white; border: none; cursor: pointer; }
        button:hover { background-color: #4cae4c; }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
            table-layout: fixed;
        }
        th, td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #ddd;
            word-wrap: break-word;
        }
        th:nth-child(5), td:nth-child(5) { width: 150px; }

        th { background-color: #f2f2f2; }
        .action-btn { padding: 5px 10px; margin: 0 2px; }
    </style>
</head>
<body>
<div class="container">
    <h1>북마크 관리 페이지</h1>

    <h2>1. 새 북마크 추가</h2>
    <div class="add-form">
        <form action="link" method="post">
            <input type="hidden" name="action" value="insert">
            <input type="text" placeholder="이름 (예: 구글)" name="name" required>
            <input type="text" placeholder="주소(URL)" name="url" required>
            <button type="submit">➕ 추가</button>

            <button type="button" onclick="location.href='link?action=list'">🔄 새로고침</button>
        </form>
    </div>

    <h2>2. 현재 북마크 목록</h2>
    <div class="bookmark-list">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>이름</th>
                <th>주소(URL)</th>
                <th>바로가기</th>
                <th>관리</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="bm" items="${links}">
                <tr>
                    <td>${bm.id}</td>
                    <td>${bm.name}</td>
                    <td>${bm.url}</td>

                    <td>
                        <c:set var="linkUrl" value="${bm.url}" />
                        <c:if test="${!bm.url.startsWith('http')}">
                            <c:set var="linkUrl" value="https://${bm.url}" />
                        </c:if>

                        <a href="${linkUrl}" target="_blank">🔗 이동</a>
                    </td>

                    <td>
                        <a href="link?action=edit&id=${bm.id}" class="action-btn">✏ 수정</a>

                        <a href="link?action=delete&id=${bm.id}"
                           class="action-btn"
                           onclick="return confirm('정말 삭제하시겠습니까?');">🗑 삭제</a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>
</body>
</html>