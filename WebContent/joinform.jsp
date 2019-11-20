<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Checkout example Â· Bootstrap</title>

    <!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    <!-- Custom styles for this template -->
    <link href="https://getbootstrap.com/docs/4.3/examples/checkout/form-validation.css" rel="stylesheet">
  </head>
  <body class="bg-light">
    <div class="container">
  <div class="py-5 text-center">

    <h2>회 원 가 입</h2>
    <p class="lead">안녕하세요 방캅스입니다.</p>
  </div>

  
    <div class="col-md-8 order-md-1">
      <h4 class="mb-3">회원정보 입력</h4>
      <form class="needs-validation" novalidate>
        <div class="row">
          <div class="mb-3">
            <label for="firstName">닉네임</label>
            <input type="text" class="form-control" id="firstName" placeholder="" value="" required>
            <div class="invalid-feedback">
              	닉네임을 입력하세요.
            </div>
          </div>
          <div class=" mb-3">
            <label for="lastName"></label>
            <button type="button" class="btn btn-primary  btn-block" id="nickcheck"  required>중복확인</button>
            <div class="invalid-feedback">
              Valid last name is required.
            </div>
          </div>
        </div>

        <div class="mb-3">
          <label for="username">Username</label>
          <div class="input-group">
            <div class="input-group-prepend">
            
            </div>
            <input type="text" class="form-control" id="username" placeholder="Username" required>
            <div class="invalid-feedback" style="width: 100%;">
              이름을 입력하세요.
            </div>
          </div>
        </div>

<div class="row">
        <div class="mb-3">
          <label for="email">Email <span class="text-muted">(ID)</span></label>
          <input type="email" class="form-control" id="userid"  name="userid" placeholder="">
          <div class="invalid-feedback">
            이메일을 입력 후 인증해주세요.
          </div>
          </div>
          <div class=" mb-3">
            <label for="lastName"></label>
            <button type="button" class="btn btn-primary  btn-block" id="emailcheck"  required>확인</button>
            <div class="invalid-feedback">
              Valid last name is required.
            </div>
          </div>
          
        </div>

        <div class="mb-3">
          <label for="password">Password</label>
          <input type="password" class="form-control" id="password" name ="password" placeholder="" required>
          
        </div>

		<div class="mb-3">
          <label for="password">PasswordCheck</label>
          <input type="password" class="form-control" id="pwchk" name="pwchk" placeholder="" required>
          <div class="invalid-feedback">
            비밀번호를 확인해주세요.
          </div>
        </div>
        
       
           
      


				<div class="row">
					<div class=" mb-2">

						<label for="level"> Phone</label> <select name="level"
							class="form-control">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="019">019</option>
						</select>
					</div>
					<div class=" mb-2">
						<label for="firstName"></label> <input type="text"
							class="form-control" id="firstName" placeholder="" value=""
							required>
						<div class="invalid-feedback">Valid first name is required.
						</div>
					</div>
					<div class=" mb-2">
						<label for="lastName"></label> <input type="text"
							class="form-control" id="lastName" placeholder="" value=""
							required>
						<div class="invalid-feedback">Valid last name is required.</div>
					</div>
					
					<div class="mb-3">
            <label for="lastName"></label>
            <button type="button" class="btn btn-primary  btn-block" id="phonecheck"  required>휴대전화 인증</button>
            <div class="invalid-feedback">
              Valid last name is required.
            </div>
          </div>
          
				</div>
				
				
          
				

<div class="row">
          <div class="mb-3">
           <label for="text">인증번호 </label>
            <input type="text" class="form-control" id="code" placeholder="" >
            <div class="invalid-feedback">
              	인증번호를 입력하세요.
            </div>
          </div>
          <div class=" mb-3">
            <label for="lastName"></label>
            <button type="button" class="btn btn-primary  btn-block" id="codecheck"  required>중복확인</button>
            <div class="invalid-feedback">
              Valid last name is required.
            </div>
          </div>
        </div>
        
       
          


				<hr class="mb-4">
        
        <button class="btn btn-primary btn-lg btn-block" type="submit">회원가입</button>
      </form>
    </div>
  </div>
<br><br>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
      <script>window.jQuery || document.write('<script src="/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script><script src="https://getbootstrap.com/docs/4.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
        <script src="https://getbootstrap.com/docs/4.3/examples/checkout/form-validation.js"></script></body>