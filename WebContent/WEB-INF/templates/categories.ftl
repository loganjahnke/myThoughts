<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>myThoughts</title>
    <!-- Styling -->
    <#include "include/style.ftl">
    <link rel="stylesheet" href="css/categories.css">
    <!-- Scripts -->
    <#include "include/script.ftl">
</head>

<body>
    <#include "include/header.ftl">
    <div id="categories">
        <ul id="category-list">
            <#list categories as category>
                <li>
                    <a href="topics?category=${category.getName()}">
                        <i class="fa fa-${category.getIcon()} ${category.getColor()}"></i>
                        <h3>${category.getName()}</h3>
                    </a>
                    <p>${category.getDescription()}</p>
                </li>
            <#else>
                <h3 class="centerAlign thin">No <span class="bold">categories</span>!</h3>
            </#list>
        </ul>
    </div>
</body>

</html>
