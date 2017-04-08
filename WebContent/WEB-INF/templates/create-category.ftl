<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<title>Create a Category</title>
	<!-- Styling -->
	<#include "include/style.ftl">
	<link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/fontawesome-iconpicker.css">
	<link rel="stylesheet" href="css/create.css">
	<!-- Scripts -->
	<#include "include/script.ftl">
</head>

<body>
	<#include "include/header.ftl">
	<div id="content">
		<form name="create" action="create-category" method="POST">
			<i id="preview" class="iconpicker-component result-icon red"></i>
			<button id="changer" onclick="colorCheck()" type="button" class="icp icp-dd btn btn-primary dropdown-toggle" data-selected="fa-car" data-toggle="dropdown">
				<span class="caret"></span>
				<span class="sr-only">Toggle Dropdown</span>
			</button>
			<div class="dropdown-menu"></div>
			<br />

			<input type="text" placeholder="name" name="name" />
			<br />

			<textarea name="description" placeholder="description"></textarea>
			<br />

			<select id="color" name="color">
				<option class="red" value="red">Red</option>
				<option class="blue" value="blue">Blue</option>
				<option class="green" value="green">Green</option>
				<option class="grey" value="grey">Grey</option>
			</select>
			<input id="icon" type="text" name="icon" value="fa-bars" hidden />
			<br />

			<input class="btn" type="button" onclick="validate()" value="Create Category" />
		</form>
	</div>
	<script src="//code.jquery.com/jquery-2.2.1.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="js/fontawesome-iconpicker.min.js"></script>
	<script>
		function colorCheck() {
			document.getElementById("preview").className = "iconpicker-component result-icon " + document.getElementById("color").value;
		}

		function validate() {
			document.getElementById("icon").value = document.getElementById("preview").children[0].className;
			document.create.submit();
		}

		$(function() {
			$('.icp-dd').iconpicker({
				placement:'bottom',
			});
		});
	</script>
</body>

</html>
