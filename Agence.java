package com.example.miniprojetv2;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Agence implements Serializable {
    private String nom;
    private String ville;
    private String codePostal;
    private String tel;
    private String fax;
    private String adresse;

    // Constructeur
    public Agence(String nom, String ville, String codePostal, String tel, String fax, String adresse) {
        this.nom = nom;
        this.ville = ville;
        this.codePostal = codePostal;
        this.tel = tel;
        this.fax = fax;
        this.adresse = adresse;
    }

    // Getters et setters pour chaque attribut
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}

