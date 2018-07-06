<!-- Autor: Felix Hartmann -->

<%@taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<custom:pageWithFooterHeader pageTitle="Meine Konfiguration">
    <div class="container">
        <div class="headline">Ihre aktuelle Konfiguration</div>

        <custom:configSlot slotID="mainboard" slotHeadline="Mainboard" type="Mainboard" slotContent="${currentConfig.items['Mainboard']}"></custom:configSlot>

        <custom:configSlot slotID="cpu" slotHeadline="CPU (Prozessor)" type="CPU" slotContent="${currentConfig.items['CPU']}"></custom:configSlot>

        <custom:configSlot slotID="gpu" slotHeadline="GPU (Grafikkarte)" type="GPU" slotContent="${currentConfig.items['GPU']}"></custom:configSlot>

        <custom:configSlot slotID="ram" slotHeadline="RAM (Arbeitsspeicher)" type="RAM" slotContent="${currentConfig.items['RAM']}"></custom:configSlot>

        <custom:configSlot slotID="hdd" slotHeadline="HDD (Festplatte)" type="HDD" slotContent="${currentConfig.items['HDD']}"></custom:configSlot>

        <custom:configSlot slotID="psu" slotHeadline="PSU (Netzzeil)" type="PSU" slotContent="${currentConfig.items['PSU']}"></custom:configSlot>

        <custom:configSlot slotID="case" slotHeadline="Case (Gehäuse)" type="Case" slotContent="${currentConfig.items['Case']}"></custom:configSlot>

        <div id="totalPrice" class="totalPrice">
            <div class="priceLabel"> <c:choose> <c:when test="${not empty currentConfig}"> &euro; ${currentConfig.totalPrice} </c:when> <c:otherwise>&euro;  0</c:otherwise> </c:choose></div>
            <button type="submit">Konfiguration bestellen</button>
        </div>

    </div>

    <div class="modal" id="articleListModal">
        <div class="modal-content">
            <div class="modal-close">&times;</div>
            <div class="headline">Bitte suchen Sie einen Artikel aus:</div>
            <div id="articleList">
                <!-- Items are added dynamically via javascript - check showPossibleItemList() in shop.js -->
            </div>
        </div>

    </div>


</custom:pageWithFooterHeader>