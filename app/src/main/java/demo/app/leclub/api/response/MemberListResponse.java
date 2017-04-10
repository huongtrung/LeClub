package demo.app.leclub.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import demo.app.leclub.bean.MemberBean;
import vn.app.base.api.response.BaseResponse;

/**
 * Created by HuongTrung on 17/02/17.
 */

public class MemberListResponse extends BaseResponse {
    @SerializedName("members")
    public List<MemberBean> members = new ArrayList<>();
}
