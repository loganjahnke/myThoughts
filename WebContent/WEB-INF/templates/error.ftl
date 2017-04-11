<html>

<head>
    <title>Uh-Oh</title>
    <#include "include/style.ftl">
    <link rel="stylesheet" href="css/error.css">
    <#include "include/script.ftl">
</head>

<body>
    <#include "include/header.ftl">
    <div id="error">
        <h2>${reason}</h2>
        <p>Back to the <a class="green no-background" href="${window}"> main window</a></p>
    </div>
    <#include "include/footer.ftl">
</body>

</html>
