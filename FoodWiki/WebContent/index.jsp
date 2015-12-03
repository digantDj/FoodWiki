<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FoodWiki</title>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<link rel="stylesheet" href="assets/css/foodwiki.css" />
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
		<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  		<script>
  $(function() {
	  var foodNamesString;
	  $.ajax({
          url : 'GetFoodNames',
          async: false,
          success : function(responseText) {
              foodNamesString = responseText;
          }
      });
	  
    var availableTags = foodNamesString.split(',');
    console.log(availableTags);
    //availableTags = ["cars","scooter"];
    $("#search").autocomplete({
      source: availableTags
    });
  });
  </script>
  
</head>
<body>	
	<!-- Header -->
			<header id="header">
				<h1>Welcome to Food Wiki</h1>
				<p>A semantic food wiki implementation.</p>
			</header>
			
	<!-- Search Form -->		
	<div class="ui-widget">
	<form id="foodwiki-form" action="Home" method="post">
		<input type="text" id="search"  name="search" placeholder="Search for a food product or dish">
		<input type="submit" value="SEARCH"/>
	</form>
	</div>
	<!-- Footer -->
			<footer id="footer">
				<ul class="icons">
					<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
					<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
					<li><a href="#" class="icon fa-github"><span class="label">GitHub</span></a></li>
					<li><a href="#" class="icon fa-envelope-o"><span class="label">Email</span></a></li>
				</ul>
				<ul class="copyright">
					<li>&copy; FoodWiki.</li><li>Web Semantics Project 2015 - SER 594 ( Team 14 )</li>
				</ul>
			</footer>

	<!-- Scripts -->
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>
</body>
</html>