package beans;

import java.io.Serializable;

// Autor: Serkan Altay
public class HeaderBean implements Serializable {
    private boolean userLoggedIn;


    public boolean getUserLoggedIn() {
        return userLoggedIn;
    }

    public void setUserLoggedIn(boolean userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }
}
