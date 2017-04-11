<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Topics</title>
    <!-- Styling -->
    <#include "include/style.ftl">
    <link rel="stylesheet" href="css/view-topics.css">
    <!-- Scripts -->
    <#include "include/script.ftl">
</head>

<body>
    <#include "include/header.ftl">
    <div id="topics">
        <#if sender == "category">
            <div id="category">
                <a href="topics?category=${category.getName()}">
                    <i class="${category.getIcon()} ${category.getColor()}"></i>
                    <h3>${category.getName()}</h3>
                </a>
                <p>${category.getDescription()}</p><br />
                <a id="back" class="gray" href="categories?">View all Categories</a>
            </div>
        </#if>
        <ul id="topic-list">
            <#list topics as topic>
                <li>
                    <a href="topic?id=${topic.getId()}">
                        <h2>${topic.getTitle()}</h2>
                    </a>
                    <p>${topic.getDescription()}</p>
                    <div class="user-links">
                        <ul>
                            <li><a class="no-decoration" href="user?username=${topic.getUser().getUsername()}">${topic.getUser().getUsername()}</a> | <span class="green no-background">${topic.getUser().getKarma()}<span class="bold">k</span></span></li>
                            <li><a href=""><i class="fa fa-caret-up"></i></a> ${topic.getVote()} <a href=""><i class="fa fa-caret-down"></i></a></li>
                            <li>${topic.getCreatedDate()}</li>
                        </ul>
                    </div>
                </li>
            <#else>
                <h3 class="centerAlign thin">No <span class="bold">topics for this ${sender}</span>!</h3>
            </#list>
        </ul>
    </div>
</body>

</html>
