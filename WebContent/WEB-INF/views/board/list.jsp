<%@page import="java.util.List"%>
<%@page import="web.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- <% List<Board> bList = (List) request.getAttribute("list"); %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<jsp:include page = "/WEB-INF/views/layout/header.jsp" />

<div class ="container">

<h1>게시판</h1>
<hr>


<table class = "table">
<tr>
	<th>번호</th>
	<th>제목</th>
	<th>ID</th>
	<th>내용</th>
	<th>조회수</th>
	<th>작성일</th>
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
	<td>${board.content }</td>
	<td>${board.hit }</td>
	<td><fmt:formatDate value="${board.writtendate }" pattern="yyyy-MM-dd"/></td>
</tr>
<%-- <%}%> --%>
</c:forEach>
</table>

</div><!--  .container -->

<jsp:include page = "/WEB-INF/views/layout/footer.jsp" />