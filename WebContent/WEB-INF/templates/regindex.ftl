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
                <li class="${category.getColor()}"><a href="TopicListServlet/?category=${category.getName()}">${category.getName()}</a></li>
            <#else>
                <li class="blue"><a href="TopicListServlet/?category=Politics">Politics</a></li>
                <li class="green"><a href="TopicListServlet/?category=Environmental">Environmental</a></li>
                <li class="red"><a href="TopicListServlet/?category=Religion">Religion</a></li>
                <li class="grey"><a href="categories.html">Categories</a></li>
                <li class="red"><a href="TopicListServlet/?category=Recent">Recent</a></li>
                <li class="green"><a href="TopicListServlet/?category=Featured">Featured</a></li>
                <li class="blue"><a href="TopicListServlet/?category=Trending">Trending</a></li>
            </#list>
        </ul>
    </div>
    <div id="debate">
        <h1 class="thin" id="debate-title">Developed countries should have a higher obligation to combat climate change than developing countries</h1>
        <a class="gray" href="TopicServlet/?id=1">Join the Debate</a>
    </div>
    <#include "include/footer.ftl">
</body>

</html>
