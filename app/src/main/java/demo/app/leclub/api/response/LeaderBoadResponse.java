package demo.app.leclub.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import demo.app.leclub.bean.LeaderBoardBean;
import vn.app.base.api.response.BaseResponse;

/**
 * Created by HuongTrung on 01/03/17.
 */

public class LeaderBoadResponse extends BaseResponse {
    @SerializedName("leaderboard")
    public List<LeaderBoardBean> leaderBoardBean = new ArrayList<>();
}
