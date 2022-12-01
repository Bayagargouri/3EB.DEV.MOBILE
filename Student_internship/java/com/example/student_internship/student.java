package com.example.student_internship;
import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class student {
    private String nomstu;
    private String mailstu;
    private String comptencesstu;

    public student() {
    }

    public student(String nomstu, String mailstu, String comptencesstu) {
        this.nomstu = nomstu;
        this.mailstu = mailstu;
        this.comptencesstu = comptencesstu;
    }

    public String getNomstu() {
        return nomstu;
    }

    public String getmailstu() {
        return mailstu;
    }

    public String getcomptencesstu() {
        return comptencesstu;
    }
}