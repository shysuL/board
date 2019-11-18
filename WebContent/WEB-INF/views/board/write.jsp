<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page = "/WEB-INF/views/layout/header.jsp" />




<hr>


<article class="container">
 <div class="page-header">
      <div class="col-md-6 col-md-offset-3">
         <h3>게시물 작성</h3>
      </div>
   </div>
   <div class="col-sm-6 col-md-offset-3">

<form  name="join" action="/member/join" method="post">
  <div class="form-group">
    <label for="exampleInputText">제목</label>
    <input type="text" class="form-control" id="userid" name="userid" placeholder="ID를 입력하세요" />
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">패스워드</label>
    <input type="password" class="form-control" id="userpw" name="userpw" onchange = "isSame()" placeholder="password" />
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">패스워드확인</label>
    <input type="password" class="form-control" id="pwdchk" name="pwdchk" onchange = "isSame()"  placeholder="password" />
    &nbsp;&nbsp;<span id="same"></span>
  </div>
  <div class="form-group">
    <label for ="exampleInputText"> 닉네임 </label>
	<input type="text" class="form-control" id = "usernick" name="usernick" placeholder="닉네임"/>	
    <p class="help-block">여기에 블록레벨 도움말 예제</p>
  </div>
  <div class="checkbox">
    <label>
      <input type="checkbox"> 입력을 기억합니다
    </label>
  </div>
  <button type="submit" class="btn btn-default">
  		<%-- <a href = "/member/join"> --%>
  	회원가입
  	<%--</a> --%>
  	</button>
</form>
</div>

</article>






<jsp:include page = "/WEB-INF/views/layout/footer.jsp" />