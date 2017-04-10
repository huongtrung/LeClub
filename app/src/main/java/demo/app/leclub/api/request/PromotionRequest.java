package demo.app.leclub.api.request;

import com.android.volley.Request;

import java.util.Map;

import demo.app.leclub.api.response.PromotionResponse;
import demo.app.leclub.constants.APIConstants;
import vn.app.base.api.volley.core.ObjectApiRequest;

/**
 * Created by HuongTrung on 10/04/17.
 */

public class PromotionRequest extends ObjectApiRequest<PromotionResponse> {
    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public String getRequestURL() {
        return APIConstants.LECLUB_URL_AD;
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
    public Class<PromotionResponse> getResponseClass() {
        return PromotionResponse.class;
    }

    @Override
    public int getMethod() {
        return Request.Method.GET;
    }
}
