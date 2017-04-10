package demo.app.leclub.api.request;

import com.android.volley.Request;

import java.util.Map;

import demo.app.leclub.api.response.AgendaResponse;
import demo.app.leclub.constants.APIConstants;
import vn.app.base.api.volley.core.ObjectApiRequest;

/**
 * Created by HuongTrung on 20/03/17.
 */

public class AgendaRequest extends ObjectApiRequest<AgendaResponse> {
    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public String getRequestURL() {
        return APIConstants.LECLUB_URL_AGENDA;
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
    public Class<AgendaResponse> getResponseClass() {
        return AgendaResponse.class;
    }

    @Override
    public int getMethod() {
        return Request.Method.GET;
    }
}
