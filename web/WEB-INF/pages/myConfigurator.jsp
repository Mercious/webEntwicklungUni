<%@taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<custom:pageWithFooterHeader pageTitle="Meine Konfiguration">
    <div class="container">
        <div class="headline">Ihre aktuelle Konfiguration</div>

        <custom:configSlot slotID="mainboard" slotHeadline="Mainboard" type="Mainboard"></custom:configSlot>

        <custom:configSlot slotID="cpu" slotHeadline="CPU (Prozessor)" type="CPU"></custom:configSlot>

        <custom:configSlot slotID="gpu" slotHeadline="GPU (Grafikkarte)" type="GPU"></custom:configSlot>

        <custom:configSlot slotID="ram" slotHeadline="RAM (Arbeitsspeicher)" type="RAM"></custom:configSlot>

        <custom:configSlot slotID="psu" slotHeadline="PSU (Netzzeil)" type="PSU"></custom:configSlot>

        <custom:configSlot slotID="case" slotHeadline="Case (Gehäuse)" type="Gehäuse"></custom:configSlot>

    </div>
</custom:pageWithFooterHeader>