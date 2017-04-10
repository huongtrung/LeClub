package demo.app.leclub.manager;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import demo.app.leclub.bean.UserProfileBean;
import vn.app.base.constant.AppConstant;
import vn.app.base.util.SharedPrefUtils;
import vn.app.base.util.StringUtil;

/**
 * Created by HuongTrung on 10/02/17.
 */

public class UserManager {
    private static Gson gson = new Gson();

    public static final String USER_DATA = "USER_DATA";

    public static void saveCurrentUser(UserProfileBean userProfileBean) {
        String userData = gson.toJson(userProfileBean, UserProfileBean.class);
        SharedPrefUtils.putString(USER_DATA, userData);
    }

    public static UserProfileBean getCurrentUser() {
        String userData = SharedPrefUtils.getString(USER_DATA, null);
        if (StringUtil.checkStringValid(userData)) {
            try {
                return gson.fromJson(userData, UserProfileBean.class);
            } catch (JsonSyntaxException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static void clearUserData() {
        SharedPrefUtils.removeKey(USER_DATA);
        SharedPrefUtils.removeKey(AppConstant.ACCESS_TOKEN);
    }
}
