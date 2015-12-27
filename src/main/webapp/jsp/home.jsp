<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<style type="text/css">
#search{
text-align: center;
font-size:28px;
}

.pos_top{
position: relative;
top: 200px;
}
</style>
</head>
<body>
<form action="word" method="get">
<div id="search" class = "pos_top">
word:
<input type="text" name="word" size="40" maxlength="255"/>
<input type="submit" value="search" />
</div><!-- SEARCH -->
</form>
</body>
</html>