<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Users</title>
    <!-- Styling -->
    <#include "include/style.ftl">
    <link rel="stylesheet" href="css/view-topics.css">
    <!-- Scripts -->
    <#include "include/script.ftl">
    <script src="js/view-users.js"></script>
</head>

<body>
    <#include "include/header.ftl">
    <div id="topics">
        <ul id="topic-list">
            <#list users as u>
            	<li id="user-${u.getId()}">
                    <a href="user?username=${u.getUsername()}">
                        <h2>${u.getFirstname()} ${u.getLastname()}</h2>
                    </a>
                    <div class="user-links">
                        <ul>
                            <li>
                                <a class="no-decoration" href="user-view?username=${u.getUsername()}">${u.getUsername()}</a>
                                |
                                <span class="green no-background">${u.getKarma()}<span class="bold">k</span></span>
                            </li>
                            <li>${u.getEmail()}</li>
                            <li>${u.getCreatedDate()}</li>
                            <li id="toggle-${u.getId()}"><a onclick="toggle(${u.getId()})" class="mt-button-tiny">
                            <#if u.isModerator() == false>
                                <i class="fa fa-male"></i> Assign Moderator
                            <#else>
                                <i class="fa fa-times"></i> Remove Moderator
                            </#if>
                            </a></li>
                        </ul>
                    </div>
                </li>
            <#else>
                <h3 class="centerAlign thin">No <span class="bold">users in system</span>!</h3>
            </#list>
        </ul>
    </div>
</body>

</html>
