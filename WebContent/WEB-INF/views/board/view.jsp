<%@page import="java.util.List"%>
<%@page import="web.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% Board board = (Board) request.getAttribute("board"); %>

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/board/list");
	});
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
	});

	//삭제버튼 동작
	$("#btnDelete").click(function() {
	});
	
});
</script>

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


<jsp:include page = "/WEB-INF/views/layout/header.jsp" />
<div class="container">
<h1>게시글 상세 내용</h1>
<hr>


<table class="table table-bordered">
<tr>
<td class="info">글번호</td><td colspan="3">${board.boardno }</td>
</tr>

<tr>
<td class="info">제목</td><td colspan="3">${board.title }</td>
</tr>

<tr>
<td class="info">아이디</td><td>${board.id }</td>
<td class="info">닉네임</td><td>[ 추후 추가 ]</td>
</tr>

<tr>
<td class="info">조회수</td><td>${board.hit }</td>
<td class="info">추천수</td><td>[ 추후 추가 ]</td>
</tr>

<tr>
<td class="info">작성일</td><td colspan="3">${board.writtendate }</td>
</tr>

<tr><td class="info"  colspan="4">본문</td></tr>

<tr><td colspan="4">${board.content }</td></tr>

</table>

<div class="text-center">	
	<button id="btnList" class="btn btn-primary" onclick="location.href='/board/list';">목록</button>
	<button id="btnUpdate" class="btn btn-info">수정</button>
	<button id="btnDelete" class="btn btn-danger">삭제</button>
</div>

</div><!-- .container -->

<jsp:include page = "/WEB-INF/views/layout/footer.jsp" />

</body>
</html>