<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" CONTENT="-1">           
<meta http-equiv="Cache-Control" CONTENT="no-cache">           
<meta http-equiv="Pragma" CONTENT="no-cache">           
<title>音乐推荐网站</title>
<meta name="description" content="">
<meta name="Keywords" content="">

   	<link rel="stylesheet" href="style/css/pintuer.css">
    <script src="style/js/jquery.js"></script>
    <script src="style/js/jquery.lazyload.min.js"></script>
	<script src="style/js/jquery.easing.min.js"></script>
    <script src="style/js/pintuer.js"></script>

	
	
	<link rel="stylesheet" href="style/css/style-out-base.css">

    <script src="style/js/respond.js"></script>
    <link type="image/x-icon" href="#/favicon.ico" rel="shortcut icon" />
    <link href="#/favicon.ico" rel="bookmark icon" />
	 <link rel="stylesheet" href="style/css/style.css">
    <style>
		.demo-nav.fixed.fixed-top{z-index:8;background:#fff;width:100%;padding:0;border-bottom:solid 3px #0a8;-webkit-box-shadow:0 3px 6px rgba(0, 0, 0, .175);box-shadow:0 3px 6px rgba(0, 0, 0, .175);}
    </style>

</head>

<body>

<%@include file="/common/head.jsp"%>


  	

	<!--内容-->


<div id="navx4" class="">
<div class="container">
      <div class="line-small">
      <div class="xl8 " style="margin:0 0 10px 0;padding:0;">
      
      
      <c:forEach items="${channels}" var="g">
      <c:if test="${fn:length(g.musics) ne 0 }">
	      <div class="xl12 cltitle"style="padding: 0 20px">
	      <a href="CreativeServlet?method=yilei&id=${g.id}">${g.name}</a> 
	      </div>
	      <br>
	      <br>
	       <%int i = 1; %>
	      <c:forEach items="${g.musics}" var="s">
	      <%if(i<=4){ %>
 		  <%i++; %>
	      <div class="xl12" style="padding: 0 20px 0 40px">
		      <div class="xl3" style="margin-bottom:10px">
			      <div class="xl12">
			      <a href="MusicServlet?method=content&id=${s.id}"><img alt="" src="upload_image/${s.img }" width="170px" height="170"></a>
			      </div>
			      <div class="xl12"style="margin-top:10px">
			      	 <a href="MusicServlet?method=content&id=${s.id }">${s.name }</a>
			      </div>
		      </div>
	      </div>
	       <%} %>
	      </c:forEach>
	  </c:if></c:forEach> 
	      
      
      </div>
      
      <div class="xl4" style="padding: 20px 0 0 ">
	      <div class="xl12  hotstore">
        	<h2 class="bg-3 text-white padding">热门歌曲</h2>
            <div class="padding bg-content">
            	<ul class="list-media list-underline">
            		<c:forEach items="${musicstuijian}" var="s">
                	<li>
                        <div class="media media-x">
                          <a class="float-left" href="MusicServlet?method=content&id=${s.id }"><img src="upload_image/${s.img }" width="70px" height="70px" class="radius" alt="..."></a>
                          <div class="media-body"><strong>${s.name }</strong>
${s.descp }
				<a class="button button-little border-red swing-hover" href="MusicServlet?method=content&id=${s.id }">查看详情</a></div>
                        </div>
                    </li>
</c:forEach>                

                </ul>
            </div>
            <br />
        </div>
	      
	      </div>
      
      </div>
      <br />

    </div>
    
    <br /><br />
    







</div>
<%@include file="/common/foot.jsp"%>
</body>
</html>
<!--index_ok-->
