<%@page import="java.util.List"%>
<%@page import="web.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- <% List<Board> bList = (List) request.getAttribute("list"); %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<jsp:include page = "/WEB-INF/views/layout/header.jsp" />

<style type="text/css">
table, th{
	text-align: center;
}
/* 두번째자식(제목)은 왼쪽정렬 */
tr td:nth-child(2){
	text-align: left;
}
/* 첫번째자식이 아닌 tr td에 세로 선 입력 */
tr td:not(:first-child), tr th:not(:first-child){
	border-left : 3px solid white;
}

</style>

<div class ="container">

<h1>게시판 <small>ㅎ ㅎ ㅎ </small></h1>
<hr>



<table class = "table table-hover table-striped table table-condensed">
<tr class="success">
	<th style = "width:10%">번호</th>
	<th style = "width:50%">제목</th>
	<th style = "width:20%">ID</th>
<!-- 	<th style = "width:15%">내용</th> -->
	<th style = "width:10%">조회수</th>
	<th style = "width:10%">작성일</th>
</tr>
<%-- <% for(int i = 0 ; i < bList.size() ; i++){%> --%>
<c:forEach items="${list }" var="board">
<tr>
	<td>
		${board.boardno }
<%-- 	<%=bList.get(i).getBoardno() %></td> --%>
	<td>
<%-- 		<a href="/board/view?bno=<%=bList.get(i).getBoardno() %>"> --%>
<%-- 		<%=bList.get(i).getTitle() %></a> --%>
		<a href="/board/view?bno=${board.boardno }">
		${board.title }</a>
	</td>
<%-- 	<td><%=bList.get(i).getId() %></td> --%>
<%-- 	<td><%=bList.get(i).getContent() %></td> --%>
<%-- 	<td><%=bList.get(i).getHit() %></td> --%>
<%-- 	<td><%=bList.get(i).getWrittendate() %></td> --%>
	<td>${board.id }</td>
<%-- 	<td>${board.content }</td> --%>
	<td>${board.hit }</td>
	<td><fmt:formatDate value="${board.writtendate }" pattern="yyyy-MM-dd"/></td>
</tr>
<%-- <%}%> --%>
</c:forEach>
</table>

<jsp:include page = "/WEB-INF/views/layout/paging.jsp" />
</div><!--  .container -->



<jsp:include page = "/WEB-INF/views/layout/footer.jsp" />