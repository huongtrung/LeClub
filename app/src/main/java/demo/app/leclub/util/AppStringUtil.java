package demo.app.leclub.util;

/**
 * Created on 7/24/2016.
 */
public class AppStringUtil {

    public static final String HTTTP_PREFIX = "http";

    public static final int NUMBER_CHARACTER_AFTER_CUT = 20;

    public static String getDateString(String date) {
        String[] arrayDate = date.split("-");
        if (arrayDate.length > 2) {
            return arrayDate[2] + "." + arrayDate[1] + "." + arrayDate[0];
        } else if (arrayDate.length > 1) {
            return arrayDate[1] + "." + arrayDate[0];
        } else {
            return date;
        }
    }

    public static String ellipsize(String input, int maxCharacters) {
        if (maxCharacters < 3) {
            return input;
        }
        if (input == null || input.length() < maxCharacters) {
            return input;
        }
        return input.substring(0, maxCharacters) + "...";
    }
}
