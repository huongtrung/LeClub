package demo.app.leclub.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import demo.app.leclub.bean.AgendaBean;
import vn.app.base.api.response.BaseResponse;

/**
 * Created by HuongTrung on 20/03/17.
 */

public class AgendaResponse extends BaseResponse {
    @SerializedName("agenda")
    public List<AgendaBean> agenda = new ArrayList<>();
}
