package demo.app.leclub.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuongTrung on 17/02/17.
 */

public class SearchBean {
    @SerializedName("news")
    public List<NewBean> news = new ArrayList<>();
    @SerializedName("members")
    public List<MemberBean> members = new ArrayList<>();
    @SerializedName("advantages")
    public List<AdvantagesBean> advantages = new ArrayList<>();

    public List<SearchResultBean> getNewList() {
        List<SearchResultBean> data = new ArrayList<>();
        for (NewBean item : news) {
            data.add(new SearchResultBean(item));
        }
        return data;
    }

    public List<SearchResultBean> getMemberList() {
        List<SearchResultBean> data = new ArrayList<>();
        for (MemberBean item : members) {
            data.add(new SearchResultBean(item));
        }
        return data;
    }

    public List<SearchResultBean> getAdvantagesList() {
        List<SearchResultBean> data = new ArrayList<>();
        for (AdvantagesBean item : advantages) {
            data.add(new SearchResultBean(item));
        }
        return data;
    }
}
