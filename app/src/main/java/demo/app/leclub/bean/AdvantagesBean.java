package demo.app.leclub.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HuongTrung on 17/02/17.
 */

public class AdvantagesBean implements Parcelable {
    @SerializedName("id")
    public String id;
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
    @SerializedName("createdAt")
    public String createdAt;
    @SerializedName("titre")
    public String titre;
    @SerializedName("shortDescription")
    public String shortDescription;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.type);
        parcel.writeString(this.image);
        parcel.writeString(this.date);
        parcel.writeString(this.texte);
        parcel.writeString(this.lien);
        parcel.writeString(this.createdAt);
        parcel.writeString(this.titre);
        parcel.writeString(this.shortDescription);
    }

    protected AdvantagesBean(Parcel in) {
        this.id = in.readString();
        this.type = in.readString();
        this.image = in.readString();
        this.date = in.readString();
        this.texte = in.readString();
        this.lien = in.readString();
        this.createdAt = in.readString();
        this.titre = in.readString();
        this.shortDescription = in.readString();
    }

    private static final Parcelable.Creator<AdvantagesBean> CREATOR = new ClassLoaderCreator<AdvantagesBean>() {
        @Override
        public AdvantagesBean createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new AdvantagesBean(parcel);
        }

        @Override
        public AdvantagesBean createFromParcel(Parcel parcel) {
            return null;
        }

        @Override
        public AdvantagesBean[] newArray(int i) {
            return new AdvantagesBean[i];
        }
    };
}
