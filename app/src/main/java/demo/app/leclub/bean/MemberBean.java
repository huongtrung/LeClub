package demo.app.leclub.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HuongTrung on 17/02/17.
 */

public class MemberBean implements Parcelable {
    @SerializedName("id")
    public String id;
    @SerializedName("photo")
    public String photo;
    @SerializedName("civilite")
    public String civilite;
    @SerializedName("nom")
    public String nom;
    @SerializedName("prenom")
    public String prenom;
    @SerializedName("societe")
    public String societe;
    @SerializedName("titre")
    public String titre;
    @SerializedName("secteurActivite")
    public String secteurActivite;
    @SerializedName("siteWeb")
    public String siteWeb;
    @SerializedName("cProRue")
    public String cProRue;
    @SerializedName("cProComplement")
    public String cProComplement;
    @SerializedName("cProCodePostal")
    public String cProCodePostal;
    @SerializedName("cProVille")
    public String cProVille;
    @SerializedName("cProTelephone")
    public String cProTelephone;
    @SerializedName("cProPortable")
    public String cProPortable;
    @SerializedName("cProEmail")
    public String cProEmail;
    @SerializedName("cPersPortable")
    public String cPersPortable;
    @SerializedName("cPersEmail")
    public String cPersEmail;
    @SerializedName("index")
    public String index;
    @SerializedName("numLicence")
    public String numLicence;
    @SerializedName("membreDe")
    public String membreDe;
    @SerializedName("facebook")
    public String facebook;
    @SerializedName("twitter")
    public String twitter;
    @SerializedName("linkedin")
    public String linkedin;
    @SerializedName("instagram")
    public String instagram;
    @SerializedName("snapchat")
    public String snapchat;
    @SerializedName("whatsapp")
    public String whatsapp;
    @SerializedName("googlePlus")
    public String googlePlus;
    @SerializedName("centreInterets")
    public String centreInterets;
    @SerializedName("dateNaissance")
    public String dateNaissance;
    @SerializedName("dateMembre")
    public String dateMembre;
    @SerializedName("cotisation")
    public String cotisation;
    @SerializedName("abonnement")
    public String abonnement;
    @SerializedName("mdp")
    public String mdp;
    @SerializedName("brut")
    public String brut;
    @SerializedName("net")
    public String net;
    @SerializedName("taillePolo")
    public String taillePolo;
    @SerializedName("parrain")
    public String parrain;
    @SerializedName("memo")
    public String memo;
    @SerializedName("description")
    public String description;
    @SerializedName("statut")
    public String statut;
    @SerializedName("dateRenouvellement")
    public String dateRenouvellement;
    @SerializedName("loginToken")
    public String loginToken;

    public String getFullName() {
        return (prenom != null ? prenom : "") + " " + (nom != null ? nom : "");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.photo);
        dest.writeString(this.civilite);
        dest.writeString(this.nom);
        dest.writeString(this.prenom);
        dest.writeString(this.societe);
        dest.writeString(this.titre);
        dest.writeString(this.secteurActivite);
        dest.writeString(this.siteWeb);
        dest.writeString(this.cProRue);
        dest.writeString(this.cProComplement);
        dest.writeString(this.cProCodePostal);
        dest.writeString(this.cProVille);
        dest.writeString(this.cProTelephone);
        dest.writeString(this.cProPortable);
        dest.writeString(this.cProEmail);
        dest.writeString(this.cPersPortable);
        dest.writeString(this.cPersEmail);
        dest.writeString(this.index);
        dest.writeString(this.numLicence);
        dest.writeString(this.membreDe);
        dest.writeString(this.facebook);
        dest.writeString(this.twitter);
        dest.writeString(this.linkedin);
        dest.writeString(this.instagram);
        dest.writeString(this.snapchat);
        dest.writeString(this.whatsapp);
        dest.writeString(this.googlePlus);
        dest.writeString(this.centreInterets);
        dest.writeString(this.dateNaissance);
        dest.writeString(this.dateMembre);
        dest.writeString(this.cotisation);
        dest.writeString(this.abonnement);
        dest.writeString(this.mdp);
        dest.writeString(this.brut);
        dest.writeString(this.net);
        dest.writeString(this.taillePolo);
        dest.writeString(this.parrain);
        dest.writeString(this.memo);
        dest.writeString(this.description);
        dest.writeString(this.statut);
        dest.writeString(this.dateRenouvellement);
        dest.writeString(this.loginToken);
    }

    public MemberBean(LeaderBoardBean leaderboardBean) {
        this.id = leaderboardBean.id;
        this.photo = leaderboardBean.photo;
        this.civilite = leaderboardBean.civilite;
        this.nom = leaderboardBean.nom;
        this.prenom = leaderboardBean.prenom;
        this.societe = leaderboardBean.societe;
        this.titre = leaderboardBean.titre;
        this.secteurActivite = leaderboardBean.secteurActivite;
        this.siteWeb = leaderboardBean.siteWeb;
        this.cProRue = leaderboardBean.cProRue;
        this.cProComplement = leaderboardBean.cProComplement;
        this.cProCodePostal = leaderboardBean.cProCodePostal;
        this.cProVille = leaderboardBean.cProVille;
        this.cProTelephone = leaderboardBean.cProTelephone;
        this.cProPortable = leaderboardBean.cProPortable;
        this.cProEmail = leaderboardBean.cProEmail;
        this.cPersPortable = leaderboardBean.cPersPortable;
        this.cPersEmail = leaderboardBean.cPersEmail;
        this.index = leaderboardBean.index;
        this.numLicence = leaderboardBean.numLicence;
        this.membreDe = leaderboardBean.membreDe;
        this.facebook = leaderboardBean.facebook;
        this.twitter = leaderboardBean.twitter;
        this.linkedin = leaderboardBean.linkedin;
        this.instagram = leaderboardBean.instagram;
        this.snapchat = leaderboardBean.snapchat;
        this.whatsapp = leaderboardBean.whatsapp;
        this.googlePlus = leaderboardBean.googlePlus;
        this.centreInterets = leaderboardBean.centreInterets;
        this.dateNaissance = leaderboardBean.dateNaissance;
        this.dateMembre = leaderboardBean.dateMembre;
        this.cotisation = leaderboardBean.cotisation;
        this.abonnement = leaderboardBean.abonnement;
        this.mdp = leaderboardBean.mdp;
        this.brut = leaderboardBean.brut;
        this.net = leaderboardBean.net;
        this.taillePolo = leaderboardBean.taillePolo;
        this.parrain = leaderboardBean.parrain;
        this.memo = leaderboardBean.memo;
        this.description = leaderboardBean.description;
        this.statut = leaderboardBean.statut;
        this.dateRenouvellement = leaderboardBean.dateRenouvellement;
    }

    private MemberBean(Parcel in) {
        this.id = in.readString();
        this.photo = in.readString();
        this.civilite = in.readString();
        this.nom = in.readString();
        this.prenom = in.readString();
        this.societe = in.readString();
        this.titre = in.readString();
        this.secteurActivite = in.readString();
        this.siteWeb = in.readString();
        this.cProRue = in.readString();
        this.cProComplement = in.readString();
        this.cProCodePostal = in.readString();
        this.cProVille = in.readString();
        this.cProTelephone = in.readString();
        this.cProPortable = in.readString();
        this.cProEmail = in.readString();
        this.cPersPortable = in.readString();
        this.cPersEmail = in.readString();
        this.index = in.readString();
        this.numLicence = in.readString();
        this.membreDe = in.readString();
        this.facebook = in.readString();
        this.twitter = in.readString();
        this.linkedin = in.readString();
        this.instagram = in.readString();
        this.snapchat = in.readString();
        this.whatsapp = in.readString();
        this.googlePlus = in.readString();
        this.centreInterets = in.readString();
        this.dateNaissance = in.readString();
        this.dateMembre = in.readString();
        this.cotisation = in.readString();
        this.abonnement = in.readString();
        this.mdp = in.readString();
        this.brut = in.readString();
        this.net = in.readString();
        this.taillePolo = in.readString();
        this.parrain = in.readString();
        this.memo = in.readString();
        this.description = in.readString();
        this.statut = in.readString();
        this.dateRenouvellement = in.readString();
        this.loginToken = in.readString();
    }

    public static final Parcelable.Creator<MemberBean> CREATOR = new Parcelable.Creator<MemberBean>() {
        @Override
        public MemberBean createFromParcel(Parcel source) {
            return new MemberBean(source);
        }

        @Override
        public MemberBean[] newArray(int size) {
            return new MemberBean[size];
        }
    };
}
