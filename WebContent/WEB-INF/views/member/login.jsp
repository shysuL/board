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
</style type="text/css">




<br>
<br>
<br>
<br>
<h1>+ + 로그인 + +</h1>
<hr>
<div class="container">
<form class="form-inline"
	action="<%=response.encodeUrl("/member/login") %>" method="post">
<fieldset style="width: 280px;">
<div class="form-group">
<legend> 로그인하기 </legend>
</div>
<table class=class = "table table-hover table-striped table table-condensed">
<div class="form-group">
<tr class="success">
	<th style = "width:50%"></th>
	<th style = "width:50%"></th>
	
</tr>

<tr class="success">
<td><label for="userid">아이디</label></td><td>

<input type="text" id="userid" name="userid" size="20"
placeholder="아이디 입력" /></td>
</tr>
</div>
<div class="form-group">
<tr>
<td><label for="userpw">비밀번호</label></td>
<td><input type="password" id="userpw" name="userpw" size="20"
placeholder="비밀번호 입력" /></td>
</tr>
</div>
<div class="form-group">
<tr>
<tr>
<td></td>
<td> <div class="inp_chk"> <!-- 체크시 checked 추가 -->
          <input type="checkbox" id="keepLogin" class="inp_radio"  name="keepLogin">
          <label for="keepLogin" class="lab_g">
<span class="img_top ico_check"></span>
<span class="txt_lab">체크</span>
  </label>
        </div></td>
</tr>
<td></td>
<td><button>로그인</button>
<button><a href = "/member/join">회원가입</a></button></td>
</tr>
</div>
			</table>

		</fieldset>
	</form>
</div>
<a href="/main"><button>메인으로가기</button></a>





<jsp:include page="/WEB-INF/views/layout/footer.jsp" />