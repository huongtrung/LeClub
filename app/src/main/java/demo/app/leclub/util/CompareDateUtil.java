package demo.app.leclub.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import vn.app.base.util.StringUtil;

/**
 * Created on 9/11/2016.
 */
public class CompareDateUtil {

    static SimpleDateFormat actualitesFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    static SimpleDateFormat avantagesFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public static Date getActualitesDate(String date) {
        if (!StringUtil.checkStringValid(date)) {
            return null;
        }
        try {
            return actualitesFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date getAvantagesDate(String date) {
        if (!StringUtil.checkStringValid(date)) {
            return null;
        }
        try {
            return avantagesFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
