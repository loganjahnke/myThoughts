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
        <h1>myThoughts Exception was caught</h1>
        <h3>Cannot fulfill the request.</h3>
        <h4>Reason: </h4>
        <h5>${reason}</h5>
        <p>Back to the <a class="green no-background" href="${window}"> main window</a></p>
    </div>
    <#include "include/footer.ftl">
</body>

</html>
