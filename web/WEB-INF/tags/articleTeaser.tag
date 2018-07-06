<!-- Autor: Felix Hartmann -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="articleName" required="true" type="java.lang.String" %>
<%@ attribute name="articleID" required="true" type="java.lang.String"%>
<%@ attribute name="articlePrice" required="true" type="java.lang.Double" %>
<%@ attribute name="compStatus" required="false" type="java.lang.Boolean" %>


<div id="article${articleID}" class="articleTeaser white" data-articleID="${articleID}">
    <img src="/shop/image?articleID=${articleID}&teaser=true" class="centeredImage" alt="article teaser image for article ${articleID}"/>
    <div class="teaserName">${articleName}</div>
    <div class="priceLabel right">&euro; ${articlePrice}</div>
    <c:if test="${compStatus}"><button type="submit" class="right" id="addButton${articleID}">Hinzufuegen</button></c:if>
    <c:choose>
    <c:when test="${compStatus}">
        <img src="WebContent/png/check-mark.png" class="icon left" id="statusIcon${articleID}" alt="compatibility status icon for article ${articleID}"/>
        <div class="lightbox greenText">Artikel ist kompatibel mit Ihrer aktuellen Konfiguration!</div>
    </c:when>
    <c:otherwise>
        <img src="WebContent/png/sign-warning-icon.png" class="iconWarning left" id="statusIcon${articleID}" alt="compatibility status icon for article ${articleID}"/>
        <div class="lightbox redText">Artikel ist nicht kompatibel mit Ihrer aktuellen Konfiguration!</div>
    </c:otherwise>
    </c:choose>
</div>