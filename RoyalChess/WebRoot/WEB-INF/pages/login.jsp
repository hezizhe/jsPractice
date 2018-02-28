<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>皇家战棋</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="/bootstrap-3.3.7/docs/dist/css/bootstrap.min.css" rel="stylesheet">

    <link href="/bootstrap-3.3.7/docs/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <link href="/bootstrap-3.3.7/docs/examples/signin/signin.css" rel="stylesheet">
    
	<link id='theme' rel='stylesheet' href='/humane/themes/bigbox.css'/>
	<script type="text/javascript" src="/localjs/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/humane/humane.js"></script>
    
  </head>
  
  <body>
     <div class="container">
      <form class="form-signin" onsubmit="return validateLoginUserFunc();" action="gamecenter" method="post">
        <h2 class="form-signin-heading">用户登录</h2>
        <label for="inputEmail" class="sr-only">用户名</label>
        <input type="text" id="playerName" class="form-control" placeholder="用户名" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="密码" required>
        <div class="checkbox">
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit" id="login">登录</button>
      </form>
    </div>
    <script type="text/javascript" src="/localjs/login.js"></script>
  </body>
</html>
