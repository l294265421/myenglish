<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyEnglish</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/newhome.css" />
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.3.js"></script>
<script type="text/javascript">
function setParmsValue(parm, parmsValue) {  
}    
$(function(){  
var spans= $("li > a > span").click(function(){  
     $(this).addClass("selected");  
     $(this).parent().parent().siblings().children().children().removeClass("selected");           
 });  
});  
</script>
</head>
<body>

<div id="header_index"><h1><a href="/index.html" title="w3school 在线教程">w3school 在线教程</a></h1></div>

<div id="navfirst">
<ul id="menu">
<li id="h"><a href="/h.asp" title="HTML 系列教程">HTML 系列教程</a></li>
<li id="b"><a href="/b.asp" title="浏览器脚本教程">浏览器脚本</a></li>
<li id="s"><a href="/s.asp" title="服务器脚本教程">服务器脚本</a></li>
<li id="d"><a href="/d.asp" title="ASP.NET 教程">ASP.NET 教程</a></li>
<li id="x"><a href="/x.asp" title="XML 系列教程">XML 系列教程</a></li>
<li id="m"><a href="/ws.asp" title="Web Services 系列教程">Web Services 系列教程</a></li>
<li id="w"><a href="/w.asp" title="建站手册">建站手册</a></li>
</ul>
</div>

    <ul id="style">  
                <li><strong>选择类型AAA：</strong></li>  
                <li><a><span>全部 </span></a></li>  
                <li><a><span>选项AAAAA</span></a></li>  
                <li><a><span>选项BBBBB</span></a></li>  
            </ul>  
 <img alt="" src="<%=request.getContextPath()%>/resources/image/2.jpg" onerror="this.src='<%=request.getContextPath()%>/resources/image/2.jpg'">
<script type="text/javascript"> 
</script>
</body>
</html>