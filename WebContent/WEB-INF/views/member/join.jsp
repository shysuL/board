<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page = "/WEB-INF/views/layout/header.jsp" />



<form name="join" action="/member/join" method="post">
<li>
	<label for = "text"> 아이디 </label>
	<input type="text" id = "userid" name="userid" />	
</li>
<li>
	<label for = "password"> 패스워드 </label>
	<input type="password" id = "userpwd" name="userpwd" />	
</li>
<li>
	<label for = "password"> 패스워드 확인 </label>
	<input type="password" id = "pwdchk" name="pwdchk" />	
</li>

<li>
	<label for = "text"> 닉네임 </label>
	<input type="text" id = "usernick" name="usernick" />	
</li>

<button type="submit">회원가입</button>
</form>


<jsp:include page = "/WEB-INF/views/layout/footer.jsp" />