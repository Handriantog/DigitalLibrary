package com.a201381061.digitallearning.Model;

/**
 * Created by UserModel on 7/1/2017.
 */

public class PostModel {

    private String judul;
    private String kategori;
    private String isi;

    public PostModel() {
    }

    public PostModel(String judul, String kategori, String isi) {
        this.judul = judul;
        this.kategori = kategori;
        this.isi = isi;
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
