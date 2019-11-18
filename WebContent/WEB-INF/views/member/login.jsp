<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

				


<style type="text/css">
h1 {
	text-align: center;
}
.container{
	display: flex;
	justify-content:center;
	align-items : center;
}
table, th {
	text-align: center;
	float: center;
}

form {
	text-align: center;
	float: center;
}

div {
	text-align: center;
	float: center;
}
</style >




<br>
<br>
<br>
<br>


<article class="container">

<form class="form-horizontal" action="<%=response.encodeUrl("/member/login") %>" method="post">
<fieldset style="width: 320px;">
  <div class="form-group">
    <label for="inputText" class="col-sm-3 control-label">아이디</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="userid" name = "userid" placeholder="Email">
    </div>
  </div><br>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-3 control-label">비밀번호</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="userpw" name = "userpw"  placeholder="Password">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox"> Remember me
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-0 col-sm-10">
      <button type="submit" class="btn btn-default">Log in</button>
    </div>
 
  </div>
<div class="form-group">
    <div class="col-sm-offset-0 col-sm-10">
      <button class="btn btn-default"><a href = "/member/join">회원가입</a></button>
<button class="btn btn-default"><a href="/main">메인으로가기</a></button>
  </div>
  </div>
  </fieldset>
</form>

</article>

  <br><br><br>
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />