<!-- Autor: Serkan Altay -->
<%@tag description="Generic Page that has header and footer" pageEncoding="UTF-8"%>
<%@taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ attribute name="pageTitle" required="true" type="java.lang.String" %>

<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
        <meta name="description" content="PC-Store Unternehmen">
        <meta name="keywords" content="PC-Store">
        <meta name="author" content="Serkan Altay, Felix Hartmann">
        <title>${pageTitle}</title>
        <link rel="stylesheet" type="text/css" href="WebContent/css/style.css">
    </head>
    <body>
        <custom:header/>

        <div id="body">
            <jsp:doBody/>
        </div>

        <custom:footer/>
    </body>
</html>