<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page = "/WEB-INF/views/layout/header.jsp" />

<style type="text/css">
#btnCancle{
	justify-content:right;
 	align-items : right; 
 	text-align: right; 
}

</style>

<script>
function isSame(){
	var pw = document.twin.userpw.value;
	var confirmPW = document.twin.pwdchk.value;
	if(pw.length < 6 || pw.length > 16){
		window.alert('비밀번호는 6글자 이상, 16글자 이하만 이용 가능합니다.');
		document.getElementById('userpw').value= document.getElementById('pwdchk').value='';
		document.getElementById('same').innerHTML='';
	}
	if(document.getElementById('userpw').value!='' & document.getElementById('pwdchk').value!=''){
		if(document.getElementById('userpw').value == document.getElementById('pwdchk').value){
		
			document.getElementById('same').innerHTML = '비밀번호가 일치합니다.';
			document.getElementById('same').style.color = 'blue';
	}else{
		document.getElementById('same').innerHTML = '비밀번호가 일치하지 않습니다.';
		document.getElementById('same').style.color='red';
	}	
}
}	
$(document).ready(function() {
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		location.href = "/main";
// 		$(location).attr("href", "/main");
		// 		history.go(-1);
	});
});
</script>

<article class="container">
 <div class="page-header">
      <div class="col-md-6 col-md-offset-3">
         <h3>회원가입</h3>
      </div>
   </div>
   <div class="col-sm-6 col-md-offset-3">

<form  name="join" action="/member/join" method="post">
  <div class="form-group">
    <label for="exampleInputText">아이디</label>
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
    
  </div>
  <div class="checkbox">
    
  </div>
  <button type="submit" class="btn btn-default">
  		<%-- <a href = "/member/join"> --%>
  	회원가입
  	<%--</a> --%>
  	</button>
  	<button  type="button" id = "btnCancel" class="btn btn-danger">취소</button>
</form>
</div>

</article>



<jsp:include page = "/WEB-INF/views/layout/footer.jsp" />