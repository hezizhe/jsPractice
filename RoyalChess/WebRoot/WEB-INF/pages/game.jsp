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
    
    
	<link id='theme' rel='stylesheet' href='/humane/themes/bigbox.css'/>
	<script type="text/javascript" src="/localjs/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/humane/humane.js"></script>
    
    <link href="/localcss/game.css" rel="stylesheet">
  </head>
  
  <body>
  	<s:hidden id="status" name="round.status"/>
  	<s:hidden id="rid" name="round.id"/>
  	<s:hidden id="first" name="round.first"/>
    <div style="width:1015px; margin:0 auto;">
		<div style="width:200px; height:670px; float:left; font-size:22px; line-height:40px; background-color:rgba(239,74,76,1.00);">
	        <div style="margin-left:10px;">
	        	<s:iterator value="round.players">
	        		<s:if test="camp==0">
	        			<div style="width:0px; height:90px; margin-left:20px; margin-top:30px; margin-bottom:30px;"><img src="/localimg/red6.png" /></div>
					          用        户：<span id="name"><s:property value="playerName"/></span><br />
					          剩余棋子：<span id="reds"><s:property value="pieceCount"/></span><br />
					          剩余时间：<span id="timered">20</span>秒<br />
					          超时次数：<span id="outtimered"><s:property value="overTimes"/></span>/5<br />
			            <span id="redmove" style="font-size:28px; font-weight:bolder; color:rgba(252,251,251,1.00);"></span><br />
			            <s:if test="playerName == currentPlayer.playerName">
			            	<s:hidden id="playerCamp" name="camp"/>
			            	<input type="button" id="redlose" style="width:180px; background-color:rgba(248,245,245,1.00); margin-top:40px;" value="认输" disabled="disabled" onclick="redlose()" />
			            	<input type="button" id="peace" style="width:180px; background-color:rgba(248,245,245,1.00); margin-top:40px;" value="求和" disabled="disabled" onclick="peace()" />
			            </s:if>
	        		</s:if>
	        	</s:iterator>
	        </div>
	    </div>
	    <div style="width:600px; height:670px; float:left;">
	        <div style="width:600px; height:600px;">
	            <ul>
	                <li id="t1h1" onclick="active(1,1)" style="background-color:#333333"></li>
	                <li id="t1h2" onclick="active(1,2)"></li>
	                <li id="t1h3" onclick="active(1,3)"></li>
	                <li id="t1h4" onclick="active(1,4)"></li>
	                <li id="t1h5" onclick="active(1,5)"></li>
	                <li id="t1h6" onclick="active(1,6)"></li>
	            </ul>
	            <ul>
	                <li id="t2h1" onclick="active(2,1)"></li>
	                <li id="t2h2" onclick="active(2,2)"></li>
	                <li id="t2h3" onclick="active(2,3)"></li>
	                <li id="t2h4" onclick="active(2,4)"></li>
	                <li id="t2h5" onclick="active(2,5)"></li>
	                <li id="t2h6" onclick="active(2,6)"></li>
	            </ul>
	            <ul>
	                <li id="t3h1" onclick="active(3,1)"></li>
	                <li id="t3h2" onclick="active(3,2)"></li>
	                <li id="t3h3" onclick="active(3,3)"></li>
	                <li id="t3h4" onclick="active(3,4)"></li>
	                <li id="t3h5" onclick="active(3,5)"></li>
	                <li id="t3h6" onclick="active(3,6)"></li>
	            </ul>
	            <ul>
	                <li id="t4h1" onclick="active(4,1)"></li>
	                <li id="t4h2" onclick="active(4,2)"></li>
	                <li id="t4h3" onclick="active(4,3)"></li>
	                <li id="t4h4" onclick="active(4,4)"></li>
	                <li id="t4h5" onclick="active(4,5)"></li>
	                <li id="t4h6" onclick="active(4,6)"></li>
	            </ul>
	            <ul>
	                <li id="t5h1" onclick="active(5,1)"></li>
	                <li id="t5h2" onclick="active(5,2)"></li>
	                <li id="t5h3" onclick="active(5,3)"></li>
	                <li id="t5h4" onclick="active(5,4)"></li>
	                <li id="t5h5" onclick="active(5,5)"></li>
	                <li id="t5h6" onclick="active(5,6)"></li>
	            </ul>
	            <ul>
	                <li id="t6h1" onclick="active(6,1)"></li>
	                <li id="t6h2" onclick="active(6,2)"></li>
	                <li id="t6h3" onclick="active(6,3)"></li>
	                <li id="t6h4" onclick="active(6,4)"></li>
	                <li id="t6h5" onclick="active(6,5)"></li>
	                <li id="t6h6" onclick="active(6,6)"></li>
	            </ul>
	        </div>
	        <div style="width:600px; height:70px; background-color:rgba(47,241,141,1.00); text-align:center;">
	        	<br/>
	            <span style="font-size:30px; font-weight:bolder;">第<span id="steps"><s:text name="round.steps"/></span>回合</span>
	        </div>
	    </div>
	    <div style="width:200px; height:670px; float:left; font-size:22px; line-height:40px; background-color:rgba(65,193,239,1.00);">
	    	<div style="margin-left:10px;">
	    		<s:iterator value="round.players">
	        		<s:if test="camp==1">
	        			<div style="width:0px; height:90px; margin-left:20px; margin-top:30px; margin-bottom:30px;"><img src="/localimg/blue6.png" /></div>
					   	用        户：<span id="name"><s:property value="playerName"/></span><br />      
					          剩余棋子：<span id="blues"><s:property value="pieceCount"/></span><br />
					          剩余时间：<span id="timeblue">20</span>秒<br />
					          超时次数：<span id="outtimeblue"><s:property value="overTimes"/></span>/5<br />
			            <span id="bluemove" style="font-size:28px; font-weight:bolder; color:rgba(2,7,231,1.00);"></span><br />
			            <s:if test="playerName == currentPlayer.playerName">
			            	<s:hidden id="playerCamp" name="camp"/>
			            	<input type="button" id="redlose" style="width:180px; background-color:rgba(248,245,245,1.00); margin-top:40px;" value="认输" disabled="disabled" onclick="bluelose()" />
			            	<input type="button" id="peace" style="width:180px; background-color:rgba(248,245,245,1.00); margin-top:40px;" value="求和" disabled="disabled" onclick="peace()" />
			            </s:if>
	        		</s:if>
	        	</s:iterator>
	        </div>
	    </div>
	    <div>
	    	<img src="/localimg/ruler.png" />
	    </div>
	</div> 
    <script type="text/javascript" src="/localjs/game.js"></script>
  </body>
</html>
