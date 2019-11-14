<%@page import="java.util.List"%>
<%@page import="web.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% Board board = (Board) request.getAttribute("board"); %>

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
	background: #ccf;
}
td:hover{
	background: ivory;
}     
</style>
</head>
<body>
<h1>게시글 상세 내용</h1>
<hr>
<table>
	<tr>
		<td><%= board.getBoardno() %> </td>
		<td><h2><%= board.getTitle() %></h2> </td>	
		<td></td>
		<td></td>
		<td></td>
		<td><button onclick="location.href='/board/list';">목록</button></td>	
		<td><button>수정</button></td>
		<td><button>삭제</button></td>
	</tr>
	<tr>
		<td>작성자 </td>
		<td><%= board.getId() %> </td>
		<td>작성날짜</td>
		<td><%= board.getWrittendate() %> </td>	
		<td>조회수</td>
		<td></td>
		<td>추천수</td>
		<td><%= board.getHit() %></td>
			
	</tr>
	<tr>
		<td></td>
		<td><%= board.getContent() %> </td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>

</table>



<hr>


</body>
</html>