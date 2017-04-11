<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Categories</title>
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
                        <i class="${category.getIcon()} ${category.getColor()}"></i>
                        <h3>${category.getName()}</h3>
                    </a>
                    <p>${category.getDescription()}</p>
                    <#if nonadmin == false>
                        <br />
                        <a id="back" class="mt-button gray" href="edit-category?id=${category.getId()}&name=${category.getName()}&description=${category.getDescription()}&icon=${category.getIcon()}&color=${category.getColor()}">Edit Category</a>
                    </#if>
                </li>
            <#else>
                <h3 class="centerAlign thin">No <span class="bold">categories</span>!</h3>
            </#list>
        </ul>
    </div>
</body>

</html>
