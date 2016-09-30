<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="initial-scale=1.0, minimum-scale=1.0, maximum-scale=2.0, user-scalable=no, width=device-width" name="viewport">
<title>word</title>
<style type="text/css">
a:link { color: #800020; text-decoration: none; font-weight: bold; }

#search{
text-align: center;
font-size:2em;
}

#search input{
	height:2em;
}

#content{
text-align: left;
font-size:1.5em;
}

.pos_top{
position: relative;
top: 3em;
}
</style>
<script type="text/javascript">
function postCEInsert()
{
var url = "insertCE";
var word = document.getElementById("word").innerHTML;
var parent = document.getElementById("newWordParent").value;
var cetymology = document.getElementById("newWordCetymology").value;
var postStr ="word=" + word + "&parent=" + parent + "&cetymology=" + cetymology;
var xmlhttp;
if (parent.length==0 || cetymology.length == 0)
  { 
  return;
  }
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.open("POST", url, true);
xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
xmlhttp.send(postStr);
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
  {
	  document.getElementById("result").innerHTML=xmlhttp.responseText;
	  document.getElementById("newWordParent").value = "";
	  document.getElementById("newWordCetymology").value = "";
	  document.getElementById("parent").innerHTML=parent;
	  document.getElementById("cetymology").innerHTML=cetymology;
  }
 };
}

function postCEUpdate()
{
var url = "updateCE";
var word = document.getElementById("word").innerHTML;
var parent = document.getElementById("newWordParent").value;
var cetymology = document.getElementById("newWordCetymology").value;
var postStr ="word=" + word + "&parent=" + parent + "&cetymology=" + cetymology;
var xmlhttp;
if (parent.length==0 || cetymology.length == 0)
  { 
  return;
  }
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.open("POST", url, true);
xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
xmlhttp.send(postStr);
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
  {
	  document.getElementById("result").innerHTML=xmlhttp.responseText;
	  document.getElementById("newWordParent").value = "";
	  document.getElementById("newWordCetymology").value = "";
	  document.getElementById("parent").innerHTML=parent;
	  document.getElementById("cetymology").innerHTML=cetymology;
  }
 };
}
</script>
</head>
<body onload = "document.getElementById('searchWord').focus();">
<form action="word" method="get">
<div id = "search" class = "pos_top">
word:
<input type="text" name="word" id="searchWord"/>
<input type="submit" value="search"/>
</div><!-- SEARCH -->
</form>

<br/>
<br/>
<div id="content" class = "pos_top">
<p>
<span id="word">${word}</span>

<br/>
<span id="phonetic">${phonetic}</span>
<br/>

<span id="phonetic">${meanings}</span>
<br>

双亲单词：<span id="parent">${cetymology.parent}</span>
<br>
词源详解：<span id="cetymology">${cetymology.cetymology}</span>
<br>
<br>

<c:forEach items="${eetymologies}" var="eetymology">
${eetymology.word}
<br/>
${eetymology.eetymology}
<br/>
<br/>
</c:forEach>
</p>

<form>
<div>
<input type='hidden' id = 'searchWord' name='searchWord' />
双亲单词：
<input type='text' id = 'newWordParent' name='newWordParent' />
<br/>
<br/>
词源详解：
<textarea id = "newWordCetymology"  name = "newWordCetymology" rows='5'></textarea>
<input type="button" onclick="postCEInsert()" value='提交'> 
<input type="button" onclick="postCEUpdate()" value='修改'> 
</div>
</form>
<p>结果：<span id="result"></span></p>
</div><!-- CONTENT-->
</body>
</html>