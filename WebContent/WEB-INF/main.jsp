<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core'  prefix='c' %>
<%@ page isELIgnored="false" %>
<%@ page import="model.ShowAllmemolist" %>
<%@ page import="model.Memolist" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Memo--Main</title>
</head>
<body>
<p>すべてのMEMO</p>
<button type="button" onclick="location.href='<c:url value="/NewMemoServlet"></c:url>'">新規MEMO</button>
<%--メモ一覧表示(タイトルと日時) --%>
<table border="1" style="table-layout: fixed; width:300px;">
  <tr>
    <th bgcolor="#CC99FF">タイトル</th>
    <th bgcolor="#CC99FF">編集日時</th>
  </tr>
<c:forEach var="memo" items="${ memolist }"><%--url,paramでパラメーター渡す --%>
  <tr>
    <td><a href="<c:url value="/Edit" ><c:param name="no" value='${ memo.no }'/></c:url>"><c:out value="${memo.title }"/></a>:</td>
    <td><c:out value="${memo.date }"/></td>
    </tr>
</c:forEach>
</table>
</body>
</html>