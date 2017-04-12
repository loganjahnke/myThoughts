<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Create Post</title>
    <!-- Styling -->
    <#include "include/style.ftl">
    <link rel="stylesheet" href="css/registered-index.css">
    <link rel="stylesheet" href="css/text.css">
    <!-- Scripts -->
    <#include "include/script.ftl">
</head>

<body>
    <#include "include/header.ftl">
    <div id="topicsMenu">
        <ul>
            <#list categories as category>
                <li class="${category.getColor()}"><a href="topics?category=${category.getName()}">${category.getName()}</a></li>
            <#else>
                <li class="blue"><a href="topics?category=Politics">Politics</a></li>
                <li class="green"><a href="topics?category=Environmental">Environmental</a></li>
                <li class="red"><a href="topics?category=Religion">Religion</a></li>
                <li class="grey"><a href="categories?">Categories</a></li>
                <li class="red"><a href="topics?category=Recent">Recent</a></li>
                <li class="green"><a href="topics?category=Featured">Featured</a></li>
                <li class="blue"><a href="topics?category=Trending">Trending</a></li>
            </#list>
        </ul>
    </div>
    <div id="debate">
    	<form action="InsertPostServlet" method="post">
 		<input class ="input1" type="text" placeholder="Debate Title" name="debateTitle"><br>
 		<input class ="input" type="text" name="debateDescription" placeholder="Debate Description"><br>
 		
 		<pre class="selectT">Select the Category</pre>
		 <select name="category" class="select">
			<option value="Politics">Politics</option>
		    <option value="Environmental">Environmental</option>
		    <option value="Religion">Religion</option>
			<option value="Music">Music</option>
			<option value="Movies">Movies</option>
			<option value="State Politics">State Politics</option>
			<option value="Soccer">Soccer</option>
			<option value="Space Exploration">Space Exploration</option>
			<option value="Coffee Shops">Coffee Shops</option>
		  </select>
        <input class="gray" type="submit" value="Enter" name="submit">
        </form>
    </div>
    <#include "include/footer.ftl">
</body>

</html>
