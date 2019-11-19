<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

				
<script type="text/javascript">
$(document).ready(function() {
	//페이지 첫 접속 시 입력창으로 포커스 이동
	$("input").eq(0).focus();
	
	//패스워드 입력 창에서 엔터 입력 시 form submit
	$("input").eq(1).keydown(function(key) {
		if(key.keyCode == 13) {
			$(this).parents("form").submit();
		}
	})

	//로그인 버튼 클릭 시 form submit
	$("#btnLogin").click(function() {
		$(this).parents("form").submit();
	})
	
	//취소 버튼 누르면 뒤로가기
	$("#btnCancel").click(function() {
		history.go(-1);
	})

});
</script>

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
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit"  id="btnLogin" class="btn btn-primary">Log in</button>
      <button type="button" id="btnCancel" class="btn btn-danger">취소</button>
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