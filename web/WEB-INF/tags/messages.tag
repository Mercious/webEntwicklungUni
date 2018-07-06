<!-- Autor: Serkan Altay -->
<%@tag description="Generic Page that has header and footer" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty errorMessages}">
    <div class="errorMessage">Es sind Fehler aufgetreten:</div>
    <c:forEach items="${errorMessages}" var="item">
        <div class="errorMessage">&nbsp;&nbsp;&nbsp;&nbsp;- ${item}</div>
    </c:forEach>
</c:if>

<c:if test="${not empty successMessages}">
    <c:forEach items="${successMessages}" var="successItem">
        <div class="successMessage">${successItem}</div>
    </c:forEach>
</c:if>


