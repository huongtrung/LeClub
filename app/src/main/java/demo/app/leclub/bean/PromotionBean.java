package demo.app.leclub.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuongTrung on 10/04/17.
 */

public class PromotionBean {
    @SerializedName("id")
    public String id;
    @SerializedName("image")
    public String image;
    @SerializedName("url")
    public String url;
    @SerializedName("createdAt")
    public String createdAt;
}
