<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Administrator</title>
    <!-- Styling -->
    <#include "include/style.ftl">
    <link rel="stylesheet" href="css/registered-index.css">
    <link rel="stylesheet" href="css/modal.css">
    <!-- Scripts -->
    <#include "include/script.ftl">
    <script type="text/javascript" src="js/changePassword.js"></script>
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
        <h1 class="thin" id="debate-title">Welcome back ${user.getFirstname()} ${user.getLastname()}</h1>
        <a class="mt-button gray" href="create-category">Create a Category</a>
        <a class="mt-button gray" href="categories">View Categories</a>
        <a class="mt-button gray" href="assign-moderator">Assign Moderator</a>
       
     </div>

	    
    <#include "include/footer.ftl">
    
    
</body>

</html>
