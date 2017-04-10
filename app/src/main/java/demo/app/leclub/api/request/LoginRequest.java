package demo.app.leclub.api.request;

import com.android.volley.Request;

import java.util.HashMap;
import java.util.Map;

import demo.app.leclub.api.response.LoginResponse;
import demo.app.leclub.constants.APIConstants;
import vn.app.base.api.volley.core.ObjectApiRequest;

/**
 * Created by HuongTrung on 07/02/17.
 */

public class LoginRequest extends ObjectApiRequest<LoginResponse> {
    private String userName;
    private String password;

    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean isRequiredAuthorization() {
        return false;
    }

    @Override
    public String getRequestURL() {
        return APIConstants.LECLUB_URL_lOGIN;
    }

    @Override
    public boolean isRequiredAccessToken() {
        return false;
    }

    @Override
    public Map<String, String> getRequestParams() {
        Map<String, String> params = new HashMap<>();
        params.put(APIConstants.USERNAME, userName);
        params.put(APIConstants.PASSWORD, password);
        return params;
    }

    @Override
    public Class<LoginResponse> getResponseClass() {
        return LoginResponse.class;
    }

    @Override
    public int getMethod() {
        return Request.Method.POST;
    }
}
