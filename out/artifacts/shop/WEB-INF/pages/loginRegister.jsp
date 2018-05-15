<%@taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<custom:pageWithFooterHeader>

        <form id="loginform" action="../LoginServlet" method="post">
            Sie sind bereits Mitglied bei uns? <br> Dann melden Sie sich hier mit ihren Nutzerdaten an!
            <table class="tb">
                <tr>
                    <td><label for="Email">Email:</label></td>
                    <td><input id="Email" name="Email" type="email" size="32"
                               maxlength="32" required="required"
                               placeholder="mustermann@gmail.com"></td>
                </tr>

                <tr>
                    <td><label for="Password">Passwort:</label></td>
                    <td><input id="Password" name="Password" type="password"
                               size="32" maxlength="32" required="required"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><br> <input class="buttom" name="submit" id="submit"
                                    tabindex="5" value="Anmelden" type="submit"><br></td>
                </tr>
            </table>
        </form>


        <form id="registerform" action="loginRegister" enctype="multipart/form-data" method="post">
            <div id="registerClaim"> Sie sind noch kein Mitglied? <br> Dann registrierien Sie sich hier kostenlos! </div>
            <button id="registerStart">Registrieren</button>
            <table class="tb hide" id="registerTable">

                <tr>
                    <td><label for="firstName">Vorname:</label></td>
                    <td><input id="firstName" name="firstName" type="text" size="32"
                               maxlength="32" required="required" placeholder="MAX"
                               title="Nur Buchstaben" pattern="[A-Za-z]+"></td>
                </tr>
                <tr>
                    <td><label for="lastName">Nachname:</label></td>
                    <td><input id="lastName" name="lastName" type="text" size="32"
                               maxlength="32" required="required" placeholder="Mustermann"
                               title="Nur Buchstaben" pattern="[a-zA-Z]+"></td>
                </tr>
                <tr>
                    <td><label for="street">Straße:</label></td>
                    <td><input id="street" name="street" type="text" size="32"
                               maxlength="32" required="required" placeholder="Musterweg"
                               title="Nur Buchstaben" pattern="[a-zA-Z]+"></td>
                </tr>
                <tr>
                    <td><label for="streetNumber">Hausnummer:</label></td>
                    <td><input class="Nr" id="streetNumber" name="streetNumber" type="text" size="6"
                               maxlength="6" required="required" placeholder="5a"
                               title="Keine Sonderzeichen" pattern="[0-9 a-z A-Z]+"></td>
                </tr>
                <tr>
                    <td><label for="postCode">Postleitzahl:</label></td>
                    <td><input class="PLZ" id="postCode" name="postCode" type="text"
                               pattern="[0-9]+" max="5" required="required" placeholder="12345"
                               title="Nur 5 stellige Zahlen"></td>
                </tr>
                <tr>
                    <td><label for="city">Ort:</label></td>
                    <td><input id="city" name="city" type="text" size="32"
                               maxlength="32" required="required" placeholder="Musterburg"
                               title="Nur Buchstaben" pattern="[a-zA-Z]+"></td>
                </tr>
                <tr>
                    <td><label for="phoneNumber">Mobilnummer:</label></td>
                    <td><input id="phoneNumber" name="phoneNumber" type="tel"
                               size="24" maxlength="24" required="required" placeholder="0160..."
                               title="Nur Zahlen" pattern="[0-9]+"></td>
                </tr>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input id="email" name="email" type="email" size="32"
                               maxlength="32" required="required"
                               placeholder="mustermann@gmail.com"></td>
                </tr>
                <tr>
                    <td><label for="password">Passwort:</label></td>
                    <td><input id="password" name="password" type="password"
                               size="32" maxlength="32" required="required"/></td>
                </tr>
                <tr>
                    <td><label for="passwordRepeat">Wiederholen:</label></td>
                    <td><input id="passwordRepeat" name="passwordRepeat"
                               type="password" size="32" maxlength="32" required="required"/></td>
                </tr>
                <tr>
                    <td><label for="birthDate">Geburtsdatum:</label></td>
                    <td><input id="birthDate" name="birthDate" type="date"
                               required="required"/></td>
                </tr>
                <tr>
                    <td><label for="profilePicture">Profilbild:</label></td>
                    <td><input id="profilePicture" name="profilePicture" type="file"
                               accept="image/*" size="32" required="required"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input class="button" name="submit" id="submit"
                               value="Registrierung abschliessen" type="submit"></td>
                </tr>
            </table>
        </form>
</custom:pageWithFooterHeader>