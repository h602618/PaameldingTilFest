package no.dat108.oblig4.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class LoginUtil {
    public static void loggUtBruker(HttpSession session) {
        session.invalidate();
    }

    public static void loggInnBruker(HttpServletRequest request, Integer phone) {
        loggUtBruker(request.getSession());

        HttpSession session = request.getSession();
        session.setAttribute("phone", phone);
    }

    public static boolean erBrukerInnlogget(HttpSession session) {
        return session != null && session.getAttribute("phone") != null;
    }

    public static String generateSalt() {
        SecureRandom sr;
        byte[] salt = new byte[16];
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
            sr.nextBytes(salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return DatatypeConverter.printHexBinary(salt);
    }

    public static String generateHash(String password, String salt) {
        if (password == null || salt == null) {
            throw new IllegalArgumentException();
        }

        char[] passChar = password.toCharArray();
        byte[] saltBytes = DatatypeConverter.parseHexBinary(salt);

        byte[] keyHash = null;
        try {
            PBEKeySpec pks = new PBEKeySpec(passChar, saltBytes, 1000, 256);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            keyHash = skf.generateSecret(pks).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return DatatypeConverter.printHexBinary(keyHash);
    }

    public static boolean validate(String password, String passwordHash, String salt) {
        if (passwordHash == null) {
            throw new IllegalArgumentException();
        }

        return passwordHash.equals(generateHash(password, salt));
    }
}
