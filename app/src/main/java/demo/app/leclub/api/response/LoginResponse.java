package demo.app.leclub.api.response;

import demo.app.leclub.bean.UserProfileBean;
import vn.app.base.api.response.BaseResponse;

/**
 * Created by HuongTrung on 07/02/17.
 */

public class LoginResponse extends BaseResponse {
    public String token;
    public UserProfileBean userProfile;
}
