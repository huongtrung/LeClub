package demo.app.leclub.api.request;

import com.android.volley.Request;

import java.util.HashMap;
import java.util.Map;

import demo.app.leclub.bean.UserProfileBean;
import demo.app.leclub.constants.APIConstants;
import vn.app.base.api.response.BaseResponse;
import vn.app.base.api.volley.core.ObjectApiRequest;

/**
 * Created by HuongTrung on 23/03/17.
 */

public class EventConfirmRequest extends ObjectApiRequest<BaseResponse> {
    UserProfileBean userProfileBean;
    String eventId;
    int eventStatus;
    String date;

    public EventConfirmRequest(UserProfileBean userProfileBean, String eventId, int eventStatus, String date) {
        this.userProfileBean = userProfileBean;
        this.eventId = eventId;
        this.eventStatus = eventStatus;
        this.date = date;
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public String getRequestURL() {
        return APIConstants.LECLUB_URL_CONFIRM_EVENT;
    }

    @Override
    public boolean isRequiredAccessToken() {
        return false;
    }

    @Override
    public Map<String, String> getRequestParams() {
        Map<String, String> params = new HashMap<>();
        params.put(APIConstants.EVENT_ID, eventId);
        params.put(APIConstants.USER_ID, userProfileBean.id);
        params.put(APIConstants.PRENOM, userProfileBean.prenom);
        params.put(APIConstants.NOM, userProfileBean.nom);
        params.put(APIConstants.EMAIL, userProfileBean.cPersEmail);
        params.put(APIConstants.EVENT_STATUS, String.valueOf(eventStatus));
        params.put(APIConstants.DATE, date);
        return params;
    }

    @Override
    public Class<BaseResponse> getResponseClass() {
        return BaseResponse.class;
    }

    @Override
    public int getMethod() {
        return Request.Method.POST;
    }
}
