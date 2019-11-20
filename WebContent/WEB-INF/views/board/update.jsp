<%@page import="java.util.List"%>
<%@page import="web.dto.Board"%>
<%@page import="web.dto.Boardfile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% Board board = (Board) request.getAttribute("board"); %>
<% Boardfile boardfile = (Boardfile) request.getAttribute("boardfile"); %>


<!-- 스마트에디터2 라이브러리 -->
<script type = "text/javascript" src = "/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>


<script type="text/javascript">
// <form>이 submit되면
// 스마트 에디터 내용을 <textarea>반영해주는 함수
function submitContents(elClickedObj) {
	// 에디터의 내용이 textarea에 적용된다.
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);

	try {
		elClickedObj.form.submit(); // <form> submit 수행
	} catch(e) {}
}
</script>

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/board/list");
	});
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
	});

	//삭제버튼 동작
	$("#btnDelete").click(function() {
	});
	
});
<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnWrite").click(function() {

		// 스마트에디터의 내용을 <textarea>에 적용
		submitContents( $("#btnWrite") );
		
		// form submit
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
});
</script>

<style type = "text/css">
table{
	border-collapse: collapse;
	border:1px solid #ccc;
}
td{
	border-top: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	padding : 5px 10px;
	background: #ccf;
}
td:hover{
	background: ivory;
}     
</style>


<jsp:include page = "/WEB-INF/views/layout/header.jsp" />
<div class="container">
<h1>게시글 수정</h1>
<hr>

<form  name="update" action="/board/update" method="get">
<table class="table table-bordered">
<tr>
<td class="info">글번호</td><td colspan="3">${board.boardno }</td>
</tr>

<tr>
<td class="info">제목</td><td colspan="3">
<input type="text" class="form-control" id="title" name="title" value="${board.title }" />

</td>
</tr>

<tr>
<td class="info">아이디</td><td>${board.id }</td>
<td class="info">닉네임</td><td>[ 추후 추가 ]</td>
</tr>

<tr>
<td class="info">조회수</td><td>${board.hit }</td>
<td class="info">추천수</td><td>[ 추후 추가 ]</td>
</tr>

<tr>
<td class="info">작성일</td><td colspan="3">${board.writtendate }</td>
</tr>
<tr>
<td class="info">첨부파일</td><td colspan="3">${boardfile.storedname}</td>
</tr>

<tr><td class="info"  colspan="4">본문</td></tr>

<tr><td colspan="4">
<textarea rows ="20" cols = "20" class="form-control" id="content" name="content">${board.content }</textarea>
</td></tr>

</table>
</form>
<div class="text-center">	
	<button type = "submit" class="btn btn-info">수정 완료</button>
	<button type="button" id="btnCancel" class="btn btn-danger" >취소</button>
</div>


</div><!-- .container -->

<jsp:include page = "/WEB-INF/views/layout/footer.jsp" />


<!-- 스마트 에디터 적용 코드 -->
<!-- <textarea>태그에 스마트에디터의 스킨을 입히는 코드 -->
<script type = "text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef : oEditors,
	elPlaceHolder: "content", // 에디터가 적용되는 <textarea>의 id
	sSkinURI: "/resources/se2/SmartEditor2Skin.html", // 에디터의 스킨
	fCreator: "createSeditor2"
	
})
</script>



