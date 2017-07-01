package com.a201381061.digitallearning.Model;

/**
 * Created by User on 6/29/2017.
 */

public class NewPost {

    private String judul;
    private String kategori;
    private String isi;
    private String userId;

    public NewPost(String judul, String kategori, String isi,String userId) {
        this.judul = judul;
        this.kategori = kategori;
        this.isi = isi;
        this.userId = userId;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
