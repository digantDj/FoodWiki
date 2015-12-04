<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FoodWiki</title>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/data.css" />
</head>
<body>
			      <%Map result = (Map)request.getAttribute("result");
				 	List<String> tweets = (List)result.get("status"); 
					List<String> userNames = (List)result.get("userNames"); 
					List<String> screenNames = (List)result.get("screenNames"); 
					List<String> userLocation = (List)result.get("userLocation");
					List<String> business = (List)result.get("business"); 
					List<String> ratings = (List)result.get("ratings"); 
					List<String> location = (List)result.get("locations");%></br></br></br>
			 <div id="logo" align="center"><a href="http://localhost:8080/FoodWiki/"><img border="0" src="images/logo.png"></a></div>
<!-- 
			 <div id="aboutFood"><a href="http://localhost:8080/FoodWiki/"><img id="back" src="images/back.png" title="Search another product"/></a><img id="foodbag" src="images/foodbag.png" align="left"><span id="foodName">Product Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp; Spinach</span></br>
			 <span id="ingredientsList">Contains Ingredients &nbsp;&nbsp;:&nbsp;&nbsp; Sugar,Salt.</span>  -->
			 <div id="aboutFood"><a href="http://localhost:8080/FoodWiki/"><img id="back" src="images/back.png" title="Search another product"/></a>
			 <img id="foodbag" src="images/foodbag.png" align="left"><span id="foodName">Product Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp; <%=request.getParameter("search")%></span>
			 <%if(result.containsKey("ing") && result.get("ing") != ""){%><span id="ingredientsList">Contains Ingredients &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp; <span id="ingredientContents"><%{out.println(result.get("ing"));%></span></span><%}}%>
			<%if(result.containsKey("carbohydratesPer100g")){%><span id="extra"><br/>Carbohydrates Per 100g&nbsp;&nbsp;:&nbsp;&nbsp;<%{out.println(result.get("carbohydratesPer100g"));%></span><%}}%>
			<%if(result.containsKey("vitaminCPer100g")){%><span id="extra"><br/>Vitamin C Per 100g&nbsp;&nbsp;:&nbsp;&nbsp;<%{out.println(result.get("vitaminCPer100g"));%></span><%}}%>
			<%if(result.containsKey("nutritionScoreUkPer100g")){%><span id="extra"><br/>Nutrition Score Uk Per 100g&nbsp;&nbsp;:&nbsp;&nbsp;<%{out.println(result.get("nutritionScoreUkPer100g"));%></span><%}}%>
			<%if(result.containsKey("transFatPer100g")){%><span id="extra"><br/>Trans Fat Per 100g&nbsp;&nbsp;:&nbsp;&nbsp;<%{out.println(result.get("transFatPer100g"));%></span><%}}%>
			<%if(result.containsKey("calciumPer100g")){%><span id="extra"><br/>Calcium Per 100g&nbsp;&nbsp;:&nbsp;&nbsp;<%{out.println(result.get("calciumPer100g"));%></span><%}}%>
			<%if(result.containsKey("vitaminAPer100g")){%><span id="extra"><br/>Vitamin A Per 100g&nbsp;&nbsp;:&nbsp;&nbsp;<%{out.println(result.get("vitaminAPer100g"));%></span><%}}%>
			<%if(result.containsKey("saltPer100g")){%><span id="extra"><br/>Salt Per 100g&nbsp;&nbsp;:&nbsp;&nbsp;<%{out.println(result.get("saltPer100g"));%></span><%}}%>
			<%if(result.containsKey("ironPer100g")){%><span id="extra"><br/>Iron Per 100g&nbsp;&nbsp;:&nbsp;&nbsp;<%{out.println(result.get("ironPer100g"));%></span><%}}%>
			<%if(result.containsKey("sugarsPer100g")){%><span id="extra"><br/>Sugars Per 100g&nbsp;&nbsp;:&nbsp;&nbsp;<%{out.println(result.get("sugarsPer100g"));%></span><%}}%>
			<%if(result.containsKey("saturatedFatPer100g")){%><span id="extra"><br/>Saturated Fat Per 100g&nbsp;&nbsp;:&nbsp;&nbsp;<%{out.println(result.get("saturatedFatPer100g"));%></span><%}}%>
			<%if(result.containsKey("fiberPer100g")){%><span id="extra"><br/>Fiber Per 100g&nbsp;&nbsp;:&nbsp;&nbsp;<%{out.println(result.get("fiberPer100g"));%></span><%}}%>
			<%if(result.containsKey("energyPer100g")){%><span id="extra"><br/>Energy Per 100g&nbsp;&nbsp;:&nbsp;&nbsp;<%{out.println(result.get("energyPer100g"));%></span><%}}%>
			<%if(result.containsKey("nutritionScoreFrPer100g")){%><span id="v"><br/>Nutrition Score Fr Per 100g&nbsp;&nbsp;:&nbsp;&nbsp;<%{out.println(result.get("nutritionScoreFrPer100g"));%></span><%}}%>
			<%if(result.containsKey("proteinsPer100g")){%><span id="extra"><br/>Proteins Per 100g&nbsp;&nbsp;:&nbsp;&nbsp;<%{out.println(result.get("proteinsPer100g"));%></span><%}}%>
			<%if(result.containsKey("fatPer100g")){%><span id="extra"><br/>Fat Per 100g&nbsp;&nbsp;:&nbsp;&nbsp;<%{out.println(result.get("fatPer100g"));%></span><%}}%>
			<%if(result.containsKey("sodiumPer100g")){%><span id="extra"><br/>Sodium Per 100g&nbsp;&nbsp;:&nbsp;&nbsp;<%{out.println(result.get("sodiumPer100g"));%></span><%}}%>
			<%if(result.containsKey("cholesterolPer100g")){%><span id="extra"><br/>Cholesterol Per 100g&nbsp;&nbsp;:&nbsp;&nbsp;<%{out.println(result.get("cholesterolPer100g"));%></span><%}}%>
			 </div>
			 <div id="userSection">
			 <%if(null != tweets && null != userNames && tweets.size() != 0 && userNames.size() != 0){ %>
				 <div id="leftSection">
				 	<span id="leftSectionTitle">How Customers feel about it?</span></br>
				 	<%for(int i=0;i<tweets.size();i++){ %>
					<span id="leftSectionBody"><b><%out.println("</br>" + userNames.get(i));%></b>
					<%if(null != screenNames && screenNames.size() != 0){out.println("@" + screenNames.get(i));}%></br>
					<%if(null != userLocation && screenNames.size() != 0 && userLocation.size() != 0) {%>
					<%out.println(userLocation.get(i));%><br/><%} %>
					"<%out.println(tweets.get(i));%>"</br><%}%></br></span>
				 </div><%} %>
				 <%if(null != business && null != location && business.size() != 0 && location.size() != 0) {%>
				 <div id="rightSection">
				 	<span id="rightSectionTitle">Where to Find it?</span></br>
				 	<%for (int i=0;i<business.size();i++){ %>
				 	<span id="rightSectionBody"><b><%out.println(business.get(i)); %></b><br/><%out.println(location.get(i));%><br/>
				 	<%if(null != ratings && ratings.size() != 0){ %>Rating:
					<%out.println(ratings.get(i));}%><br/><%}%></span>
				 </div><%} %>
			 </div>
</body>
</html>