<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
    <div class="container orange">
        <div id="header-menu">
            <h1>PC-Store</h1>
        </div>
        <nav>
            <ul>
                <li><a href="/shop/">Startseite</a></li>
                <li><a href="xxxxx">PC-Konfigurator</a></li>
                <li><a href="contact">Kontakt</a></li>
                <c:if test="${not not headerBean.userLoggedIn}">
                    <li><a href="loginRegister">Registrieren</a></li>
                    <li><a href="loginRegister">Anmelden</a></li>
                </c:if>
            </ul>
        </nav>
    </div>
</header>