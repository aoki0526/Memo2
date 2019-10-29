<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規メモ作成</title>
</head>
<body>
<form action = "/Memo2/NewMemoServlet"method ="post">
<p>新規メモ</p>
<%--title,textの入力を受付、DBへ登録する --%>
タイトル：<input type="text"name="title"><br>
本文：<br>
<textarea name="text" rows="4" cols="30"></textarea><br>
<input type="submit" value="保存"><br>
</form>
<%--リクエストスコープにmesあれば表示(EL式) --%>
<c:if test ="${requestScope.mes != null && requestScope.mes != ''}">
  <tr>
  <c:out value="${requestScope.mes}"/></tr></c:if>
<input type="button" value="戻る" onclick="history.back()">
</body>
</html>