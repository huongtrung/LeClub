package demo.app.leclub.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HuongTrung on 28/02/17.
 */

public class LeaderBoardBean {
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

    public String getFullName() {
        return (prenom != null ? prenom : "") + " " + (nom != null ? nom : "");
    }
}
