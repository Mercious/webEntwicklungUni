<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="slotID" required="true" type="java.lang.String" %>
<%@ attribute name="slotHeadline" required="true" type="java.lang.String"%>

<div class="configSlot white" id="${slotID}">
    <div class="configHeadline">${slotHeadline}</div>
    <img src="WebContent/png/add_icon.png" class="configAddPicture" id="statusIcon${articleID}"/>
    <div class="configAddClaim">Artikel hinzufuegen</div>

</div>