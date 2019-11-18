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

<form  name="join" action="/board/write" method="post"
 enctype="multipart/form-data">
  <div class="form-group">
    <label for="exampleInputText">제목</label>
    <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요" />
  </div>
  <div class="form-group">
    <label for="exampleInputText">상세내용</label>
    <textarea rows ="20" cols = "20" class="form-control" id="content" name="content" ></textarea>
  </div>
  <div class="form-group">
    <label for="exampleInputFile">첨부파일</label>
    <input type="file" class="form-control" id = "upfile" name = "upfile" />
  </div>
 
 
 
  <button type="submit" class="btn btn-default" id="btnWrite">	작성완료 	</button>
</form>
</div>

</article>






<jsp:include page = "/WEB-INF/views/layout/footer.jsp" />