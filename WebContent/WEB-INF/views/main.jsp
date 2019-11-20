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

.page-header{
	justify-content: center;
 	align-items : center; 
 	text-align: center; 
}
.container{
	justify-content:right;
 	align-items : right; 
 	text-align: right; 
}

</style>

<div class="page-header"><h1> MAIN </h1></div>
<div class = "container">
 
      <div class="col-md-6 col-md-offset-3">
</div>




<%--
<c:choose>
<c:when test="${not empty login }" >
	<h3>${sessionScope.usernick }</h3>
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
</div>
 --%>
<!-- 비로그인상태 -->
<c:if test="${not login }">
<strong>로그인이 필요합니다</strong><br>
<button class="btn btn-default" onclick='location.href="/member/login";'>로그인</button>
<button class="btn btn-default" onclick='location.href="/member/join";'>회원가입</button>
</c:if>

<!-- 로그인상태 -->
<c:if test="${login }">
<strong>${usernick } 님, 환영합니다</strong><br>
<button class="btn btn-default" onclick='location.href="/board/list";'>게시판 가기</button>
<button class="btn btn-default" onclick='location.href="/member/logout";'>로그아웃</button>
</c:if>
</div>









<jsp:include page = "/WEB-INF/views/layout/footer.jsp" />