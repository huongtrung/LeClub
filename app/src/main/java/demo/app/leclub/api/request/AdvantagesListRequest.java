package demo.app.leclub.api.request;

import com.android.volley.Request;

import java.util.Map;

import demo.app.leclub.api.response.AdvantagesListResponse;
import demo.app.leclub.constants.APIConstants;
import vn.app.base.api.volley.core.ObjectApiRequest;

/**
 * Created by HuongTrung on 20/02/17.
 */

public class AdvantagesListRequest extends ObjectApiRequest<AdvantagesListResponse> {

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public String getRequestURL() {
        return APIConstants.LECLUB_URL_ADVANTAGES;
    }

    @Override
    public boolean isRequiredAccessToken() {
        return false;
    }

    @Override
    public Map<String, String> getRequestParams() {
        return null;
    }

    @Override
    public Class<AdvantagesListResponse> getResponseClass() {
        return AdvantagesListResponse.class;
    }

    @Override
    public int getMethod() {
        return Request.Method.GET;
    }
}
