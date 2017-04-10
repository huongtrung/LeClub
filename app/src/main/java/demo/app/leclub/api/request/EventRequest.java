package demo.app.leclub.api.request;

import com.android.volley.Request;

import java.util.HashMap;
import java.util.Map;

import demo.app.leclub.api.response.EventResponse;
import demo.app.leclub.constants.APIConstants;
import vn.app.base.api.volley.core.ObjectApiRequest;

/**
 * Created by HuongTrung on 23/03/17.
 */

public class EventRequest extends ObjectApiRequest<EventResponse> {
    private String userId;
    private String eventId;

    public EventRequest(String userId, String eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public String getRequestURL() {
        return APIConstants.LECLUB_URL_EVENT_DETAIL;
    }

    @Override
    public boolean isRequiredAccessToken() {
        return false;
    }

    @Override
    public Map<String, String> getRequestParams() {
        Map<String, String> params = new HashMap<>();
        params.put(APIConstants.USER_ID, userId);
        params.put(APIConstants.EVENT_ID, eventId);
        return params;
    }

    @Override
    public Class<EventResponse> getResponseClass() {
        return EventResponse.class;
    }

    @Override
    public int getMethod() {
        return Request.Method.POST;
    }
}
