package com.a201381061.digitallearning.Model;

/**
 * Created by UserModel on 6/29/2017.
 */

public class NewPostModel {

    private String judul;
    private String isi;
    private String userId;

    public NewPostModel(String judul, String isi, String userId) {
        this.judul = judul;
        this.isi = isi;
        this.userId = userId;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
