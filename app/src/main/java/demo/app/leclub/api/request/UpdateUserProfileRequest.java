package demo.app.leclub.api.request;

import com.android.volley.Request;

import java.util.HashMap;
import java.util.Map;

import demo.app.leclub.api.response.UpdateUserProfileResponse;
import demo.app.leclub.constants.APIConstants;
import vn.app.base.api.volley.core.ObjectApiRequest;

/**
 * Created by HuongTrung on 24/02/17.
 */

public class UpdateUserProfileRequest extends ObjectApiRequest<UpdateUserProfileResponse> {
    String value;
    String label;

    public UpdateUserProfileRequest(String label, String value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public String getRequestURL() {
        return APIConstants.LECLUB_URL_UPDATE_PROFILE;
    }

    @Override
    public boolean isRequiredAccessToken() {
        return false;
    }

    @Override
    public Map<String, String> getRequestParams() {
        Map<String,String> params = new HashMap<>();
        params.put(APIConstants.KEY,label);
        params.put(APIConstants.VALUE,value);
        return params;
    }

    @Override
    public Class<UpdateUserProfileResponse> getResponseClass() {
        return UpdateUserProfileResponse.class;
    }

    @Override
    public int getMethod() {
        return Request.Method.POST;
    }
}
