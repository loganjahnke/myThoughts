<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>myThoughts</title>
    <!-- Styling -->
    <#include "include/style.ftl">
    <link rel="stylesheet" href="css/registered-index.css">
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
        <h1 class="thin" id="debate-title">${featured.getTitle()}</h1>
        <a class="mt-button gray" href="topic?id=${featured.getId()}">Join the Debate</a>
        <a class="mt-button gray" href="categories?">View all Categories</a>
        
        <div>
        <form action="user" method="post">
	   		<button class="mt-button-tiny gray" type="submit" name="createdTopics">See topics you created</button>
	   		<button class="mt-button-tiny gray" type="submit" name="changePswd">Change Your Password</button>
	        <button class="mt-button-tiny gray" type="submit" name="commentedTopics">See topics you commented on</button>
	    </form>
	    </div>
    </div>
    
    
    <#include "include/footer.ftl">
</body>

</html>
