<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.*" %>
<% Memolist memolist = (Memolist)request.getAttribute("memolist"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta charset="UTF-8">
<title>メモ編集</title>
</head>
<body>
<form action ="/Memo2-1/Edit"method="post">
<p>メモ編集</p>
タイトル:<input type="text" name="title" value="<%=memolist.getTitle()%>"><br>
本文:<br>
<textarea name="text" rows="4" cols="30"><%=memolist.getText()%></textarea><br>
最終更新:<%=memolist.getDate() %><br>
<input type="hidden" name="no" value="<%=memolist.getNo()%>">
<button type="submit">保存</button>
</form>
<button type="button" onclick="location.href='<c:url value="/Delete" ><c:param name="no" value='${ memolist.no }'/></c:url>'">削除</button>
<input type="button" value="戻る" onclick="history.back()">
</body>
</html>