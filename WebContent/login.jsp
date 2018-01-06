<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>权限管理</title>
      <link rel="stylesheet" href="css/style.css">
</head>

<body>

  <link href='https://fonts.googleapis.com/css?family=Open+Sans:700,600' rel='stylesheet' type='text/css'>

<form method="post" action="${pageContext.request.contextPath }/UserServlet?method=login" id="login">
<div class="box">
<h1>后台登录</h1>

<input type="email" name="username" value="admin" onFocus="field_focus(this, 'email');" onblur="field_blur(this, 'email');" class="email" />
  
<input type="password" name="password" value="admin" onFocus="field_focus(this, 'email');" onblur="field_blur(this, 'email');" class="email" />
  
<a href="javascript:document:login.submit();"><div class="btn">登录</div></a> <!-- End Btn -->

<a href="#"><div id="btn2">关闭</div></a> <!-- End Btn2 -->
  
</div> <!-- End Box -->
  
</form>

<p>Forgot your password? <u style="color:#f1c40f;">Click Here!</u></p>
  
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script>
  
  

    <script  src="js/index.js"></script>




</body>

</html>
