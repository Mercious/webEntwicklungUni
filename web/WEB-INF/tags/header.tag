<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
    <div class="container orange">
        <div class="leftNav">
            <c:if test="${not empty currentUser}">
                <form action="/shop/logout" method="post">
                    <img src="WebContent/png/userIcon.png" class="left"/>
                    <div class="inlineBlock headerUserName left">${currentUser.firstName},<br>${currentUser.lastName}</div>
                    <input type="submit" id="logoutButton" class="logoutButton inlineBlock left" value="Ausloggen"/>
                </form>
            </c:if>
            <form action="/shop/search" method="get">
                <input type="text" class="searchBar" placeholder="Suchebegriff eingeben ..." name="searchTerm"/>
                <input type="submit" style="display:none"/>
            </form>
        </div>
        <nav>
            <ul>
                <li><a href="/shop/">Startseite</a></li>
                <li><a href="configurator">PC-Konfigurator</a></li>
                <li><a href="contact">Kontakt</a></li>
                <c:if test="${empty currentUser}">
                    <li><a href="loginRegister">Registrieren/Login</a></li>
                </c:if>
            </ul>
        </nav>
    </div>
</header>