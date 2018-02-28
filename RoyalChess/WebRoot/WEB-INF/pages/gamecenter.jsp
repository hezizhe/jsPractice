<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
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
	
	<script type="text/javascript" src="/localjs/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/humane/humane.js"></script>
    <link href="/bootstrap-3.3.7/docs/dist/css/bootstrap.min.css" rel="stylesheet">

    <link href="/bootstrap-3.3.7/docs/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="navbar.css" rel="stylesheet">
    <script src="/bootstrap-3.3.7/docs/assets/js/ie-emulation-modes-warning.js"></script>
    
  </head>
  
  <body>
     <div class="container">

      <!-- Static navbar -->
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
             <a class="navbar-brand" href="#"><s:property value="currentPlayer.playerName"/></a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            
            <ul class="nav navbar-nav navbar-right">
              <li><a href="">退出游戏</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>

      <div class="jumbotron">
        <h2>游戏大厅</h2>
        <br>
        <p>
          <s:hidden id="record" name="record"/>
          <s:iterator value="roundList">
            <s:if test="players.size()>=2">
              <a class="btn btn-lg btn-primary" disabled="disabled" role="button">战场<s:property value="id"/><br><span style="color:red;"><strong>=战斗中=</strong></span></a>
            </s:if><s:else>
        	  <a class="btn btn-lg btn-primary" id="<s:property value="id"/>" role="button">战场<s:property value="id"/><br>（<s:property value="players.size()"/>/2） &raquo;</a>
        	</s:else>
          </s:iterator>
        </p>
        <p><a class="btn btn-lg btn-primary" href="javascript:addround();" role="button">开辟一个新战场 &raquo;</a></p>
      </div>

    </div>
    <script type="text/javascript" src="/localjs/gamecenter.js"></script>
  </body>
</html>
