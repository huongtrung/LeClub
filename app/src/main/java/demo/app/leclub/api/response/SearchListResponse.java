package demo.app.leclub.api.response;

import com.google.gson.annotations.SerializedName;

import demo.app.leclub.bean.SearchBean;
import vn.app.base.api.response.BaseResponse;

/**
 * Created by HuongTrung on 17/02/17.
 */

public class SearchListResponse extends BaseResponse {
    @SerializedName("search")
    public SearchBean search;
}
