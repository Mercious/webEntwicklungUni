<!-- Autor: Serkan Altay -->

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:pageWithFooterHeader pageTitle="Admin Area">
    <div class="container">
        <custom:messages/>
    </div>

    <div class="hide" id="categoryValues">
        <c:forEach items="${categoryValues}" var="categoryValue">
            <div>${categoryValue}</div>
        </c:forEach>
    </div>

    <div class="container">
        <div class="headline">Artikelpflege</div>
        <div class="adminArea white">
            <label for="articleIDList" class="adminAreaLabel">Artikel ausw&auml;hlen:</label>
            <form action="/shop/adminArea" method="get" class="inlineBlock">
                <select id="articleIDList" onchange="this.form.submit()" name="articleID">
                    <c:if test="${empty currentSelection}">
                        <option selected>Bitte auswählen</option>
                    </c:if>
                    <c:forEach items="${articleIDList}" var="articleID">
                        <c:choose>
                            <c:when test="${articleID == currentSelection.articleID}">
                                <option selected>${articleID}</option>
                            </c:when>
                            <c:otherwise>
                                <option>${articleID}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </form>
            <button class="newArticleButton inlineBlock" id="newArticleButton">Neuer Artikel</button>

            <form class="adminAreaForm" id="adminAreaForm" action="/shop/adminArea" method="post"
                  enctype="multipart/form-data">
                <label for="articleID" class="adminAreaLabel">Artikel-ID:</label>
                <input type="text" id="articleID" name="articleID" readonly value="${currentSelection.articleID}"
                       class="shortInput">

                <label for="category" class="adminAreaLabel">Kategorie:</label>
                <input type="text" id="category" readonly value="${currentSelection.type}"
                       class="shortInput"/>

                <label for="articleName" class="adminAreaLabel" id="articleNameLabel">Artikelname:</label>
                <input type="text" id="articleName" name="articleName" value="${currentSelection.articleName}"
                       class="longInput"/>

                <label for="GPUSlot" class="adminAreaLabel">GPU Slot:</label>
                <input type="text" id="GPUSlot" name="GPUSlot" value="${currentSelection.GPU_Slot}" class="longInput"/>

                <label for="CPUSlot" class="adminAreaLabel">CPU Slot:</label>
                <input type="text" id="CPUSlot" name="CPUSlot" value="${currentSelection.CPU_Slot}" class="longInput"/>

                <label for="RAMSlot" class="adminAreaLabel">RAM Slot:</label>
                <input type="text" id="RAMSlot" name="RAMSlot" value="${currentSelection.RAM_Slot}" class="longInput"/>

                <label for="price" class="adminAreaLabel">Preis:</label>
                <input type="number" step="0.01" id="price" name="price" value="${currentSelection.price}"
                       class="shortInput"/>

                <c:if test="${not empty currentSelection}">
                    <label for="currentPicture" id="currentPictureLabel">Produktbild:</label>
                    <img src="/shop/image?articleID=${currentSelection.articleID}&teaser=true" id="currentPicture">
                </c:if>

                <label for="newPicture">Neues Produktbild:</label>
                <input type="file" id="newPicture" name="newPicture">

                <button type="submit">Speichern</button>

            </form>
        </div>
    </div>
</custom:pageWithFooterHeader>