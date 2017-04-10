package demo.app.leclub.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HuongTrung on 16/02/17.
 */

public class NewBean implements Parcelable {
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

    protected NewBean(Parcel in) {
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

    private static final Parcelable.Creator<NewBean> CREATOR = new ClassLoaderCreator<NewBean>() {
        @Override
        public NewBean createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new NewBean(parcel);
        }

        @Override
        public NewBean createFromParcel(Parcel parcel) {
            return null;
        }

        @Override
        public NewBean[] newArray(int i) {
            return new NewBean[i];
        }
    };
}
