<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="initial-scale=1.0, minimum-scale=1.0, maximum-scale=2.0, user-scalable=no, width=device-width" name="viewport">
<title>首页</title>
<style type="text/css">
#search{
text-align: center;
font-size:2em;
}

#search input{
	height:2em;
}

.pos_top{
position: relative;
top: 3em;
}
</style>
</head>
<body onload = "document.getElementById('searchWord').focus();">
<form action="word" method="get">
<div id = "search" class = "pos_top">
word:
<input type="text" name="word" id="searchWord"/>
<input type="submit" value="search"/>
</div><!-- SEARCH -->
</form>
</body>
</html>