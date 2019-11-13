<%@page import="java.util.List"%>
<%@page import="web.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% List<Board> bList = (List) request.getAttribute("list"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type = "text/css">
table{
	border-collapse: collapse;
	border:1px solid #ccc;
}
td{
	border-top: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	padding : 5px 10px;
	background: ivory;
}
td:hover{
	background: #ccf;
}

</style>
</head>
<body>

<h1>게시판</h1>
<hr>

<hr>
<table>
<tr>
	<th>번호</th>
	<th>제목</th>
	<th>ID</th>
	<th>내용</th>
	<th>조회수</th>
	<th>작성일</th>
</tr>
<% for(int i = 0 ; i < bList.size() ; i++){%>
<tr>
	<td><%=bList.get(i).getBoardno() %></td>
	<td><%=bList.get(i).getTitle() %></td>
	<td><%=bList.get(i).getId() %></td>
	<td><%=bList.get(i).getContent() %></td>
	<td><%=bList.get(i).getHit() %></td>
	<td><%=bList.get(i).getWrittendate() %></td>

</tr>
<%}%>

</table>

</body>
</html>