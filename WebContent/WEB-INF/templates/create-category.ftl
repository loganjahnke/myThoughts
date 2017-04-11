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
		<#if update == false>
		<form name="create" action="create-category" method="POST">
		<#else>
		<form name="create" action="edit-category" method="POST">
		</#if>
			<i id="preview" class="iconpicker-component result-icon ${category.getColor()}"></i>
			<button id="changer" onclick="colorCheck()" type="button" class="icp icp-dd btn btn-primary dropdown-toggle" data-selected="${category.getIconWithoutFirstFA()}" data-toggle="dropdown">
				<span class="caret"></span>
				<span class="sr-only">Toggle Dropdown</span>
			</button>
			<div class="dropdown-menu"></div>
			<br />

			<input type="text" placeholder="name" name="name" value="${category.getName()}" />
			<br />

			<textarea name="description" placeholder="description">${category.getDescription()}</textarea>
			<br />

			<select id="color" name="color">
			<#if category.getColor() == "red">
				<option class="red" value="red" selected>Red</option>
				<option class="blue" value="blue">Blue</option>
				<option class="green" value="green">Green</option>
				<option class="grey" value="grey">Grey</option>
			<#elseif category.getColor() == "blue">
				<option class="red" value="red">Red</option>
				<option class="blue" value="blue" selected>Blue</option>
				<option class="green" value="green">Green</option>
				<option class="grey" value="grey">Grey</option>
			<#elseif category.getColor() == "green">
				<option class="red" value="red">Red</option>
				<option class="blue" value="blue">Blue</option>
				<option class="green" value="green" selected>Green</option>
				<option class="grey" value="grey">Grey</option>
			<#elseif category.getColor() == "grey">
				<option class="red" value="red">Red</option>
				<option class="blue" value="blue">Blue</option>
				<option class="green" value="green">Green</option>
				<option class="grey" value="grey" selected>Grey</option>
			</#if>
			</select>

			<input id="icon" type="text" name="icon" value="fa-bars" hidden />
			<input type="text" name="id" value="${category.getId()}" hidden />
			<input id="uod" type="text" name="updateORdelete" value="update" hidden />
			<br />
			<br />

			<#if update == false>
			<input class="mt-button grey" type="button" onclick="validate()" value="Create Category" />
			<#else>
			<input style="margin-bottom: 10px;" class="mt-button grey" type="button" name="update" onclick="doUpdate()" value="Edit Category" />
			<input class="mt-button grey" type="button" name="delete" onclick="doDelete()" value="Delete Category" />
			</#if>
		</form>
	</div>
	<script src="//code.jquery.com/jquery-2.2.1.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="js/fontawesome-iconpicker.min.js"></script>
	<script>
		function colorCheck() {
			document.getElementById("preview").className = "iconpicker-component result-icon " + document.getElementById("color").value;
		}

		function doUpdate() {
			document.getElementById("uod").value = "update";
			validate();
		}

		function doDelete() {
			document.getElementById("uod").value = "delete";
			validate();
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
