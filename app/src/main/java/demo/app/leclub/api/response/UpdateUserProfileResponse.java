package demo.app.leclub.api.response;

import com.google.gson.annotations.SerializedName;

import demo.app.leclub.bean.UserProfileBean;
import vn.app.base.api.response.BaseResponse;

/**
 * Created by HuongTrung on 24/02/17.
 */

public class UpdateUserProfileResponse extends BaseResponse {
    @SerializedName("updated_profile")
    public UserProfileBean updatedProfile;
}
