package id.alex.helpers;

public class UtilsHelper {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.equalsIgnoreCase("");
    }
}
