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
    <script src="js/view-topics.js"></script>
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
                <a id="back" class="mt-button gray" href="categories?">View all Categories</a>
            </div>
        </#if>
        <ul id="topic-list">
            <#list topics as topic>
            	<#if (!visitor && nonadmin && user.doesAgree(topic))>
                <li id="topic-${topic.getId()}" class="green-topic">
                <#elseif (!visitor && nonadmin && user.doesDisagree(topic))>
                <li id="topic-${topic.getId()}" class="red-topic">
                <#else>
                <li id="topic-${topic.getId()}">
                </#if>
                    <a href="topic?id=${topic.getId()}">
                        <h2>${topic.getTitle()}</h2>
                    </a>
                    <p>${topic.getDescription()}</p>
                    <div class="user-links">
                        <ul>
                            <li>
                                <a class="no-decoration" href="user?username=${topic.getUser().getUsername()}">${topic.getUser().getUsername()}</a>
                                |
                                <span class="green no-background">${topic.getUser().getKarma()}<span class="bold">k</span></span>
                            </li>
                            <li>
                                <#if !visitor && nonadmin && user.doesLike(topic)>
                                    <span class="bold green no-background" id="upvote-${topic.getId()}"><a class="fake-link" onclick="upvote(${topic.getId()})"><i class="fa fa-caret-up"></i></a></span>
                                    <span id="count-${topic.getId()}">${topic.getVote()}</span>
                                    <span class="no-background" id="downvote-${topic.getId()}"><a class="fake-link" onclick="downvote(${topic.getId()})"><i class="fa fa-caret-down"></i></a></span>
                                <#elseif !visitor && nonadmin && user.doesDislike(topic)>
                                    <span class="no-background" id="upvote-${topic.getId()}"><a class="fake-link" onclick="upvote(${topic.getId()})"><i class="fa fa-caret-up"></i></a></span>
                                    <span id="count-${topic.getId()}">${topic.getVote()}</span>
                                    <span class="bold red no-background" id="downvote-${topic.getId()}"><a class="fake-link" onclick="downvote(${topic.getId()})"><i class="fa fa-caret-down"></i></a></span>
                                <#elseif !visitor>
                                    <span class="no-background" id="upvote-${topic.getId()}"><a class="fake-link" onclick="upvote(${topic.getId()})"><i class="fa fa-caret-up"></i></a></span>
                                    <span id="count-${topic.getId()}">${topic.getVote()}</span>
                                    <span class="no-background" id="downvote-${topic.getId()}"><a class="fake-link" onclick="downvote(${topic.getId()})"><i class="fa fa-caret-down"></i></a></span>
                                <#else>
                                    <span class="no-background" id="upvote-${topic.getId()}"><a class="fake-link" onclick="requestLogin()"><i class="fa fa-caret-up"></i></a></span>
                                    <span id="count-${topic.getId()}">${topic.getVote()}</span>
                                    <span class="no-background" id="downvote-${topic.getId()}"><a class="fake-link" onclick="requestLogin()"><i class="fa fa-caret-down"></i></a></span>
                                </#if>
                            </li>
                            <li>${topic.getCreatedDate()}</li>
                            <#if !visitor && (nonadmin == false || user.isModerator())>
                                <#if !topic.containsCategory("Featured")>
                                    <li id="feature-${topic.getId()}"><button onclick="feature(${topic.getId()})" class="mt-button-tiny"><i class="fa fa-star"></i> Feature</button></li>
                                <#else>
                                    <li id="unfeature-${topic.getId()}"><button onclick="unfeature(${topic.getId()})" class="mt-button-tiny"><i class="fa fa-times"></i> Remove Feature</button></li>
                                </#if>
                                <li><button onclick="doDelete(${topic.getId()})" class="mt-button-tiny"><i class="fa fa-trash"></i> Delete</button></li>
                            </#if>
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
