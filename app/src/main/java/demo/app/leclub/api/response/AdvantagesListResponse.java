package demo.app.leclub.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import demo.app.leclub.bean.AdvantagesBean;
import vn.app.base.api.response.BaseResponse;

/**
 * Created by HuongTrung on 20/02/17.
 */

public class AdvantagesListResponse extends BaseResponse {
    @SerializedName("advantages")
    public List<AdvantagesBean> advantages = new ArrayList<>();
}
