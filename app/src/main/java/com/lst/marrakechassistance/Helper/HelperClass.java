package com.lst.marrakechassistance.Helper;

public class HelperClass {
    String nom , prenom , email ,password;

    public HelperClass(String nom, String prenom, String email , String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;

    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public HelperClass() {
    }
}
