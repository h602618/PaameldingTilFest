package no.dat108.oblig4.utils;

public class ValidatorUtil {
    // starte med stor bokstav
    // starte med stor bokstav etter "-" eller " "
    // alltid en liten bokstav etter en stor bokstav
    public static boolean firstName(String input) {
        if (input.length() > 20) return false;
        return input.matches("^\\p{Lu}\\p{Ll}+([\\s-]\\p{Lu}\\p{Ll}+)*$");
    }

    // starte med stor bokstav
    // starte med stor bokstav etter "-"
    // alltid en liten bokstav etter en stor bokstav
    public static boolean lastName(String input) {
        if (input.length() > 20) return false;
        return input.matches("^\\p{Lu}\\p{Ll}+(-\\p{Lu}\\p{Ll}+)*$");
    }

    // 8x tall
    public static boolean phone(String input) {
        return input.matches("\\d{8}");
    }

    // "male" eller "female"
    public static boolean gender(String input) {
        return input.matches("male|female");
    }

    // alle norske bokstaver og tall
    // minst 4 tegn
    public static boolean password(String input) {
        return input.matches("^[\\p{L}\\d]{4,}$");
    }

    // sjekk om 2 teksstreng-passord er like
    public static boolean passowrdsMatch(String pass1, String pass2) {
        return pass1.equals(pass2);
    }
}
