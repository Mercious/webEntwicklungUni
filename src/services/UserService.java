package services;

import beans.UserRegisterBean;
import daos.UserDAO;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;


@Stateless
public class UserService {
    @Inject
    private UserDAO userDAO;

    public boolean loginUser(final String userName, final String password) {
        String savedPassword = this.userDAO.getPasswordForUser(userName);
        return hashPassword(password).equals(savedPassword);
    }

    public boolean registerUser(final UserRegisterBean userRegisterBean) {
        return false;
    }

    private String hashPassword(final String password) {
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), "someSalt".getBytes(), 1000);
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return new String(secretKeyFactory.generateSecret(keySpec).getEncoded());

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // LOG some ERROR
            return "";
        }
    }
}
