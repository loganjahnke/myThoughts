<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <title>Create Post</title>
        <!-- Styling -->
        <#include "include/style.ftl">
        <link rel="stylesheet" href="css/registered-index.css">
        <!-- <link rel="stylesheet" href="css/text.css"> -->
        <link rel="stylesheet" href="css/create.css">
        <!-- Scripts -->
        <#include "include/script.ftl">
    </head>

    <body>
        <#include "include/header.ftl">
        <div id="content">
            <form action="create-topic" method="POST">
                <h2>Create Debate Topic</h2>
                <input class ="input1" type="text" placeholder="Title" name="debateTitle"><br />
                <textarea name="debateDescription" placeholder="Description"></textarea><br />

                <h3 id="select-title">Select the Categories</h3>
                <p>Hold control/command to select multiple.</p>
                <select name="category" id="select-multiple" multiple>
                    <#list allCategories as category>
                        <option value="${category.getName()}">${category.getName()}</option>
                    </#list>
                </select><br />
                <input class="mt-button gray" type="submit" value="Enter" name="submit">
            </form>
        </div>
        <#include "include/footer.ftl">
    </body>

</html>
