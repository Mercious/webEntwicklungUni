<!-- Autor: Serkan Altay -->
<%@taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<custom:pageWithFooterHeader pageTitle="Suche">
    <div class="container">
        <div class="headline">Ihre Suchergebnise</div>
        <c:forEach items="${articles}" var="article">
            <custom:articleTeaser articleName="${article.articleName}" articleID="${article.articleID}" articlePrice="${article.price}" compStatus="${article.compatibilityStatus}"/>
        </c:forEach>
    </div>
</custom:pageWithFooterHeader>