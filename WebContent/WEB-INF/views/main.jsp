<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<jsp:include page = "/WEB-INF/views/layout/header.jsp" />

<style type = "text/css">
form {
	text-align: center;
}
label{
	text-align: left;
}

</style>

<div><h1> MAIN </h1></div>
<hr>




<c:choose>
<c:when test="${not empty login }" >
	<h3>${sessionScope.userid }</h3>
	<button onclick="location.href='/member/logout' ">로그아웃</button>
	<br><hr><br>
	<button onclick="location.href='/board/list' ">게시판</button>
</c:when> 
<c:otherwise>
<!-- <form action="/login/login" method="post"> -->

<button><a href = "/member/login">로그인</a></button>    
<button><a href = "/member/join">회원가입</a></button>


<form action="<%=response.encodeUrl("/login/login") %>" method="post">
<!-- <form action="/login/login;jsessionid=<%=session.getId() %>" method="post"> -->

   </form>
   


</c:otherwise>


</c:choose>









<jsp:include page = "/WEB-INF/views/layout/footer.jsp" />