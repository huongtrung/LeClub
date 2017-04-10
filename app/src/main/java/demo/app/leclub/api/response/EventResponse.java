package demo.app.leclub.api.response;

import com.google.gson.annotations.SerializedName;

import demo.app.leclub.bean.EventBean;
import vn.app.base.api.response.BaseResponse;

/**
 * Created by HuongTrung on 23/03/17.
 */

public class EventResponse extends BaseResponse {
    @SerializedName("event")
    public EventBean event;
}
