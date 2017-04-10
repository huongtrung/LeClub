package demo.app.leclub.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HuongTrung on 20/03/17.
 */

public class AgendaBean implements Parcelable{
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


    protected AgendaBean(Parcel in) {
        id = in.readString();
        createdAt = in.readString();
        type = in.readString();
        image = in.readString();
        date = in.readString();
        texte = in.readString();
        lien = in.readString();
        fonctionnalite = in.readString();
        adresse = in.readString();
    }

    public static final Creator<AgendaBean> CREATOR = new Creator<AgendaBean>() {
        @Override
        public AgendaBean createFromParcel(Parcel in) {
            return new AgendaBean(in);
        }

        @Override
        public AgendaBean[] newArray(int size) {
            return new AgendaBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.id);
        dest.writeString(this.createdAt);
        dest.writeString(this.type);
        dest.writeString(this.image);
        dest.writeString(this.date);
        dest.writeString(this.texte);
        dest.writeString(this.lien);
        dest.writeString(this.fonctionnalite);
        dest.writeString(this.adresse);
    }
}
