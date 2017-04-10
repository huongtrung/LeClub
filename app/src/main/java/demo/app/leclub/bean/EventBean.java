package demo.app.leclub.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HuongTrung on 23/03/17.
 */

public class EventBean {
    @SerializedName("id")
    public String id;
    @SerializedName("createdAt")
    public String createdAt;
    @SerializedName("type")
    public String type;
    @SerializedName("image")
    public String image;
    @SerializedName("date")
    public String date;
    @SerializedName("texte")
    public String texte;
    @SerializedName("lien")
    public String lien;
    @SerializedName("fonctionnalite")
    public String fonctionnalite;
    @SerializedName("adresse")
    public String adresse;
    @SerializedName("event_status")
    public String eventStatus;
}
