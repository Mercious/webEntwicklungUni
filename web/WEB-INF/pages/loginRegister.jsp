<%@taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<custom:pageWithFooterHeader pageTitle="Login/Registrierung">
    <div class="container">
        <custom:errorMessages/>
    </div>
    <div class="container white">
        <h2 class="orangeText">Login / Registrierung</h2>
        <div>Hier können Sie sich entweder mit Ihren bereits vorhandenen Kundendaten einloggen oder kostenlos einen neuen Account anlegen!</div>

        <div id="loginArea" class="loginRegisterTeaser">
            <h3>Ich bin bereits Kunde!</h3>
            <div class="loginboxText greyText">Dann geben melden Sie sich bitte mit ihren Login-Daten hier an:</div>
            <form id="loginform" action="/shop/loginRegister" method="post">
                <label for="eMail">E-Mail Adresse:</label>
                <input id="eMail" name="eMail" required pattern=".{4,}"/>
                <label for="password">Passwort:</label>
                <input id="password" name="password" type="password" required pattern=".{6,}"/>
                <button type="submit">Anmelden</button>
            </form>
        </div>

        <div class="loginRegisterTeaser">
            <h3>Ich bin noch kein Kunde!</h3>
            <div id="registerPitch">
                <div class="greyText">Dann registrieren Sie sich kostenlos an und genießen diese Vorteile:</div>
                    <ul class="greyText">
                     <li>Abschließen von Bestellungen</li>
                        <li>Wöchentlicher Newsletter über die besten Deals</li>
                        <li>Überblick über die eigene Bestellhistorie</li>
                        <li>Personalisierte Vorschläge nach Ihren Bedürfnissen</li>
                        <li>Und noch vieles mehr!</li>
                    </ul>
                <button id="beginRegisterButton">Registrierung beginnen</button>
            </div>
            <form id="registerForm" action="/shop/register" method="post">
                <label for="eMailRegister">E-Mail:</label>
                <input type="text" id="eMailRegister" name="eMail"/>
                <label for="passwordRegister">Passwort:</label>
                <input type="password" id="passwordRegister" name="password"/>
                <label for="passwordRepeat">Passwort wiederholen:</label>
                <input type="password" name="passwordRepeat" id="passwordRepeat"/>
                <button type="submit">Registrieren</button>
                <label for="firstName">Vorname</label>
                <input type="text" id="firstName" name="firstName"/>
                <label for="lastName">Nachname</label>
                <input type="text" id="lastName" name="lastName"/>
            </form>
        </div>
    </div>
</custom:pageWithFooterHeader>