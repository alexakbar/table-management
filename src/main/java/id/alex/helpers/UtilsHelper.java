package id.alex.helpers;

public class UtilsHelper {

    public static boolean isEmpty(String text){
        return text == null || text.isBlank();
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.equalsIgnoreCase("");
    }
}
