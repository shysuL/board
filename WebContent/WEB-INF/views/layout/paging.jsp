<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div>
	<ul class="pagination pagination-sm">
	<!-- 처음으로 돌아가기  -->
	<c:if test="${Paging.curPage ne 1 }">
	<li><a href = "/board/list">&larr;처음</a></li>
	</c:if>
	<!-- 이전 페이징리스트로 가기  -->
	<li><a href = "/board/list?curPage=${Paging.startPage-Paging.pageCount }">&laquo;</a></li>
	
	<!-- 이전 페이지로 가기  -->
	<li><a href = "/board/list?curPage=${Paging.curPage-1 }">&lt;</a></li>
	
		<c:forEach begin= "${Paging.startPage }" end = "${Paging.endPage }" var="i">
			<c:choose>
				<c:when test = "${Paging.curPage eq i }">
					<li class = "active"><a href = "/board/list?curPage=${i }">${i }</a></li>		
				</c:when>
				<c:otherwise>
					<li><a href = "/board/list?curPage=${i }">${i }</a></li>						
				</c:otherwise>	
			</c:choose>
		</c:forEach>
	</ul>

</div>