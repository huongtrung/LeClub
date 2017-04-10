package demo.app.leclub.api.request;

import com.android.volley.Request;

import java.util.HashMap;
import java.util.Map;

import demo.app.leclub.api.response.SearchListResponse;
import demo.app.leclub.constants.APIConstants;
import vn.app.base.api.volley.core.ObjectApiRequest;

/**
 * Created by HuongTrung on 17/02/17.
 */

public class SearchListRequest extends ObjectApiRequest<SearchListResponse> {
    private String keyword;

    public SearchListRequest(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public String getRequestURL() {
        return APIConstants.LECLUB_URL_SEARCH;
    }

    @Override
    public boolean isRequiredAccessToken() {
        return false;
    }

    @Override
    public Map<String, String> getRequestParams() {
        Map<String, String> params = new HashMap<>();
        params.put(APIConstants.SEARCH_TEXT, keyword);
        return params;
    }

    @Override
    public Class<SearchListResponse> getResponseClass() {
        return SearchListResponse.class;
    }

    @Override
    public int getMethod() {
        return Request.Method.GET;
    }
}
