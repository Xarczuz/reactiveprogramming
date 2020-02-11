package reactive;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

public class PasswordHash {
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final int SALT_LENGTH = 8;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    public static Optional<PasswordAndSalt> hashFirstPassword(String password) {
        String salt = generateSalt();
        return hashPassword(password, salt).map(s -> new PasswordAndSalt(s, salt));
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static Optional<String> hashPassword(String password, String salt) {
        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);
        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.ofNullable((Base64.getEncoder().encodeToString(securePassword)));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword() " + ex.getMessage());
            return Optional.empty();
        } finally {
            spec.clearPassword();
        }
    }
}
