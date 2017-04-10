package demo.app.leclub.api.response;

import com.google.gson.annotations.SerializedName;

import demo.app.leclub.bean.PromotionBean;
import vn.app.base.api.response.BaseResponse;

/**
 * Created by HuongTrung on 10/04/17.
 */

public class PromotionResponse extends BaseResponse {
    @SerializedName("promotion")
    public PromotionBean promotion;
}
