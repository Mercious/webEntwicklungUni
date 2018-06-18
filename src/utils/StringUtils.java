package utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class StringUtils {
    public static boolean isNotEmptyOrNull(final String s) {
        return s != null && !s.isEmpty();
    }
    public static boolean areNotEmptyOrNull(final String... strings) {
        for(String s : strings) {
            if (!isNotEmptyOrNull(s)) {
                return false;
            }
        }
        return true;
    }

    public static String hashPassword(final String password) {
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), "someSalt".getBytes(), 65536, 256);
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return new String(secretKeyFactory.generateSecret(keySpec).getEncoded());

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // LOG some ERROR
            return "";
        }
    }
}
