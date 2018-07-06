<!-- Autor: Felix Hartmann -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="slotID" required="true" type="java.lang.String" %>
<%@ attribute name="slotHeadline" required="true" type="java.lang.String" %>
<%@ attribute name="type" required="true" type="java.lang.String" %>
<%@ attribute name="slotContent" required="false" type="beans.ArticleBean" %>


<div class="configSlot white" id="${slotID}" data-type="${type}">
    <div class="configHeadline">${slotHeadline}</div>
    <!-- Keep the elements in the page so that the javascript can toggle them dynamically depending on user-input without
        having to reload the page -->
    <c:choose>
        <c:when test="${empty slotContent}">
            <div id="addItemArea${type}">
                <input type="image" src="WebContent/png/add_icon.png" class="configAddPicture"
                       id="addIcon${type}"/>
                <div class="configAddClaim">Artikel hinzufuegen</div>
            </div>
        </c:when>
        <c:otherwise>
            <div id="addItemArea${type}" class="hide">
                <input type="image" src="WebContent/png/add_icon.png" class="configAddPicture"
                       id="addIcon${type}"/>
                <div class="configAddClaim">Artikel hinzufuegen</div>
            </div>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${empty slotContent}">
            <div id="itemDetailsArea${type}" class="hide">
                <img src="/shop/image?articleID=${slotContent.articleID}&teaser=true" class="centeredImage"/>
                <div class="teaserName">${slotContent.articleName}</div>
                <div class="priceLabel right">&euro; ${slotContent.price}</div>
                <img src="WebContent/png/delete_icon.png" class="deleteIcon left" id="deleteIcon${type}"/>
            </div>
        </c:when>
        <c:otherwise>
            <div id="itemDetailsArea${type}">
                <img src="/shop/image?articleID=${slotContent.articleID}&teaser=true" class="centeredImage"/>
                <div class="teaserName">${slotContent.articleName}</div>
                <div class="priceLabel right">&euro; ${slotContent.price}</div>
                <img src="WebContent/png/delete_icon.png" class="deleteIcon left" id="deleteIcon${type}"/>
            </div>
        </c:otherwise>
    </c:choose>


</div>