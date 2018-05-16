<%@taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<custom:pageWithFooterHeader>
    <div class="container white">
        <h2 class="orangeText">Login / Registrierung</h2>
        Hier können Sie sich entweder mit Ihren bereits vorhandenen Kundendaten einloggen oder kostenlos einen neuen Account anlegen!

        <div id="loginArea" class="loginRegisterTeaser">
            <h3>Ich bin bereits Kunde!</h3>
            <div class="loginboxText greyText">Dann geben melden Sie sich bitte mit ihren Login-Daten hier an:</div>
            <form id="loginform">
                <label for="eMail">E-Mail Adresse:</label>
                <input id="eMail"/>
                <label for="password">Passwort:</label>
                <input id="password"/>
                <button type="submit">Anmelden</button>
            </form>
        </div>

        <div id="registerArea" class="loginRegisterTeaser">
            <h3>Ich bin noch kein Kunde!</h3>
            <div class="greyText">Dann registrieren Sie sich kostenlos an und genießen diese Vorteile:</div>
            <ul class="greyText">
                <li>Abschließen von Bestellungen</li>
                <li>Wöchentlicher Newsletter über die besten Deals</li>
                <li>Überblick über die eigene Bestellhistorie</li>
                <li>Personalisierte Vorschläge nach Ihren Bedürfnissen</li>
                <li>Und noch vieles mehr!</li>
            </ul>
            <button type="submit">Registrieren</button>
        </div>
    </div>
</custom:pageWithFooterHeader>