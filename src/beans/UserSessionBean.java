package beans;

import java.io.Serializable;

public class UserSessionBean implements Serializable {
    private String eMail;
    private String firstName;
    private String lastName;

    public UserSessionBean() {
        eMail = "";
        firstName = "";
        lastName = "";
    }

    public UserSessionBean(final String firstName, final String lastName, final String eMail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = lastName;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
