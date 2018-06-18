<%@taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<custom:pageWithFooterHeader pageTitle="Suche">
    <div class="container">
        <div class="container headline">Ihre Suchergebnise</div>
        <c:forEach items="${articles}" var="article">
            <custom:articleTeaser articleName="${article.articleName}" articleID="${article.articleID}" articlePrice="${article.price}"/>
        </c:forEach>
    </div>
</custom:pageWithFooterHeader>