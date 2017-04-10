package demo.app.leclub.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import demo.app.leclub.bean.NewBean;
import vn.app.base.api.response.BaseResponse;

/**
 * Created by HuongTrung on 16/02/17.
 */

public class NewListResponse extends BaseResponse {
    @SerializedName("news")
    public List<NewBean> news = new ArrayList<>();
}
