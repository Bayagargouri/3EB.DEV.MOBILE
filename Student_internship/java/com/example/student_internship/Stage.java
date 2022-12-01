package com.example.student_internship;

import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class Stage {
    private String nom;
    private String post;
    private String comptences;

    public Stage() {
    }

    public Stage(String nom, String post, String comptences) {
        this.nom = nom;
        this.post = post;
        this.comptences = comptences;
    }

    public String getnom() {

        return nom;
    }

    public String getpost() {

        return post;
    }

    public String getcomptences() {

        return comptences;
    }
}