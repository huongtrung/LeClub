package demo.app.leclub.api.request;

import com.android.volley.Request;

import java.util.Map;

import demo.app.leclub.api.response.LeaderBoadResponse;
import demo.app.leclub.constants.APIConstants;
import vn.app.base.api.volley.core.ObjectApiRequest;

/**
 * Created by HuongTrung on 01/03/17.
 */

public class LeaderBoardRequest extends ObjectApiRequest<LeaderBoadResponse> {
    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public String getRequestURL() {
        return APIConstants.LECLUB_URL_LEADERBOARD;
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
    public Class<LeaderBoadResponse> getResponseClass() {
        return LeaderBoadResponse.class;
    }

    @Override
    public int getMethod() {
        return Request.Method.GET;
    }
}
