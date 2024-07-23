package main.utilPackage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Encryption {

    public static byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    public static String combineSaltAndHash(byte[] salt, String hashedPassword) {
        return Base64.getEncoder().encodeToString(salt) + ":" + hashedPassword;
    }

    public static byte[] extractSalt(String stored) {
        String salt = stored.split(":")[0];
        return Base64.getDecoder().decode(salt);
    }

    public static String extractHashedPassword(String stored) {
        return stored.split(":")[1];
    }

    // verifie un mdp entre en clair par un user avec un mdp stocker en DB
    public static boolean verifyPassword(String inputPassword, String storedPassword) throws NoSuchAlgorithmException {
        byte[] extractedSalt = extractSalt(storedPassword);
        String hashedInputPassword = hashPassword(inputPassword, extractedSalt);
        String extractedHashedPassword = extractHashedPassword(storedPassword);
        return hashedInputPassword.equals(extractedHashedPassword);
    }

    public static String createDBPassword(String password) throws NoSuchAlgorithmException {
        byte [] salt = generateSalt();
        String hashedPassword = hashPassword(password, salt);
        return combineSaltAndHash(salt,  hashedPassword);
    }
}
