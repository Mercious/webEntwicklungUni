package beans;

import java.io.Serializable;
import java.util.Date;

public class UserRegisterBean implements Serializable {
    private String firstName;
    private String lastName;
    private String street;
    private String stretNumber;
    private String postCode;
    private String city;
    private String phoneNumber;
    private String eMail;
    private String password;
    private Date birthDate;
    private byte[] profilePicture;
}
