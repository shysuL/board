<%@page import="java.util.List"%>
<%@page import="web.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- <% List<Board> bList = (List) request.getAttribute("list"); %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<jsp:include page = "/WEB-INF/views/layout/header.jsp" />

<script>
$(document).ready(function() {
	//로그인되어있지 않으면 글쓰기 페이지로 넘어가지 않음
	//main으로 리다이렉트
	$("#btnWrite").click(function() {
		location.href = "/board/write";
	});
	
	$("#main").click(function() {
		location.href = "/main";
	});
});

$(document).ready(function() {
    //최상단 체크박스 클릭
    $("#checkAll").click(function() {
       //클릭되었으면
       if ($("#checkAll").prop("checked")) {
          //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
          $("input[name=checks]").prop("checked", true);
          //클릭이 안되있으면
       } else {
          //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
          $("input[name=checks]").prop("checked", false);
       }
    });
 });


</script>


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

#main{
 text-align: right;
}
#btnWrite{
 text-align: right;
 margin-bottom: 20px; ;

 
}
#btnChkDelete{

 text-align: left;
 margin-bottom: 20px; ;
 
}



</style>

<div class ="container">

<h1>게시판 <small>ㅎ ㅎ ㅎ </small></h1> 
<div  class="text-right" ><button id = "main" class="btn btn-default">메인</button></div>
<hr>


<form  action = "/board/listDelete" method = "post">
<table class = "table table-hover table-striped table table-condensed">
<tr class="success">
	<th style = "width:2%"><input type="checkbox" id = "checkAll" /></th>
	<th style = "width:10%">번호</th>
	<th style = "width:53%">제목</th>
	<th style = "width:15%">ID</th>
	<th style = "width:10%">조회수</th>
	<th style = "width:10%">작성일</th>
</tr>
<c:forEach items="${list }" var="board">
<tr>
	<td><input type = "checkbox"  id = "checks" name = "checks" value = "${board.boardno}"/></td>
	<td style = "text-align: center">
		${board.boardno }
	</td>
	<td>
		<a href="/board/view?bno=${board.boardno}">
		${board.title }</a>
	</td>
	<td>${board.id }</td>
	<td>${board.hit }</td>
	<td><fmt:formatDate value="${board.writtendate }" pattern="yyyy-MM-dd"/></td>
</tr>
</c:forEach>

</table>
<button  id = "btnChkDelete" class="btn btn-default">체크 삭제</button>
</form>
<jsp:include page = "/WEB-INF/views/layout/paging.jsp" />

<button id = "btnWrite" class="btn btn-default" >글쓰기</button>

</div><!--  .container -->



<jsp:include page = "/WEB-INF/views/layout/footer.jsp" />