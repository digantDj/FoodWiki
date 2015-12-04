<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FoodWiki</title>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/data.css" />
</head>
<body>
			 <div id="logo" align="center"><a href="http://localhost:8080/FoodWiki/"><img border="0" src="images/logo.png"></a></div>
			 <div id="aboutFood"><img id="foodbag" src="images/foodbag.png" align="left"><span id="foodName">Product Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp; Spinach</span></br>
			 <span id="ingredientsList">Contains Ingredients &nbsp;&nbsp;:&nbsp;&nbsp; Sugar,Salt.</span>
			 </div>
			 <div id="userSection">
				 <div id="leftSection">
				 	<span id="leftSectionTitle">What Customers say?</span></br>
				 	<span id="leftSectionBody">Tweet1 is this and that</br>Tweet2Tweet1</br>Tweet2Tweet1</br>Tweet2Tweet1</br>Tweet2</span>
				 </div>
				 <div id="rightSection">
				 	<span id="rightSectionTitle">What to Find it?</span></br>
				 	<span id="rightSectionBody">Restaurant1</span>
				 </div>
			 </div>
			 
<%=request.getAttribute("results")%> 
</body>
</html>