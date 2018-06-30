<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="slotID" required="true" type="java.lang.String" %>
<%@ attribute name="slotHeadline" required="true" type="java.lang.String"%>
<%@ attribute name="type" required="true" type="java.lang.String"%>
<%@ attribute name="slotContent" required="false" type="beans.ArticleBean"%>


<div class="configSlot white" id="${slotID}" data-type="${type}">
    <div class="configHeadline">${slotHeadline}</div>
    <c:if test="${empty slotContent}">
        <input type="image" src="WebContent/png/add_icon.png" class="configAddPicture" id="statusIcon${articleID}"/>
        <div class="configAddClaim">Artikel hinzufuegen</div>
    </c:if>

</div>