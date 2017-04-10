package demo.app.leclub.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuongTrung on 07/02/17.
 */

public class UserProfileBean {
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("photo")
        @Expose
        public String photo;
        @SerializedName("civilite")
        @Expose
        public String civilite;
        @SerializedName("nom")
        @Expose
        public String nom;
        @SerializedName("prenom")
        @Expose
        public String prenom;
        @SerializedName("societe")
        @Expose
        public String societe;
        @SerializedName("titre")
        @Expose
        public String titre;
        @SerializedName("secteurActivite")
        @Expose
        public String secteurActivite;
        @SerializedName("siteWeb")
        @Expose
        public String siteWeb;
        @SerializedName("cProRue")
        @Expose
        public String cProRue;
        @SerializedName("cProComplement")
        @Expose
        public String cProComplement;
        @SerializedName("cProCodePostal")
        @Expose
        public String cProCodePostal;
        @SerializedName("cProVille")
        @Expose
        public String cProVille;
        @SerializedName("cProTelephone")
        @Expose
        public String cProTelephone;
        @SerializedName("cProPortable")
        @Expose
        public String cProPortable;
        @SerializedName("cProEmail")
        @Expose
        public String cProEmail;
        @SerializedName("cPersPortable")
        @Expose
        public String cPersPortable;
        @SerializedName("cPersEmail")
        @Expose
        public String cPersEmail;
        @SerializedName("index")
        @Expose
        public String index;
        @SerializedName("numLicence")
        @Expose
        public String numLicence;
        @SerializedName("membreDe")
        @Expose
        public String membreDe;
        @SerializedName("facebook")
        @Expose
        public String facebook;
        @SerializedName("twitter")
        @Expose
        public String twitter;
        @SerializedName("linkedin")
        @Expose
        public String linkedin;
        @SerializedName("instagram")
        @Expose
        public String instagram;
        @SerializedName("snapchat")
        @Expose
        public String snapchat;
        @SerializedName("whatsapp")
        @Expose
        public String whatsapp;
        @SerializedName("googlePlus")
        @Expose
        public String googlePlus;
        @SerializedName("centreInterets")
        @Expose
        public String centreInterets;
        @SerializedName("dateNaissance")
        @Expose
        public String dateNaissance;
        @SerializedName("dateMembre")
        @Expose
        public String dateMembre;
        @SerializedName("cotisation")
        @Expose
        public String cotisation;
        @SerializedName("abonnement")
        @Expose
        public String abonnement;
        @SerializedName("mdp")
        @Expose
        public String mdp;
        @SerializedName("brut")
        @Expose
        public String brut;
        @SerializedName("net")
        @Expose
        public String net;
        @SerializedName("taillePolo")
        @Expose
        public String taillePolo;
        @SerializedName("parrain")
        @Expose
        public String parrain;
        @SerializedName("memo")
        @Expose
        public String memo;
        @SerializedName("description")
        @Expose
        public String description;
        @SerializedName("statut")
        @Expose
        public String statut;
        @SerializedName("dateRenouvellement")
        @Expose
        public String dateRenouvellement;
        @SerializedName("loginToken")
        @Expose
        public String loginToken;

        public String getFullName(){
             return (prenom != null ? prenom : "") + " " + (nom != null ? nom : "");
        }
}
