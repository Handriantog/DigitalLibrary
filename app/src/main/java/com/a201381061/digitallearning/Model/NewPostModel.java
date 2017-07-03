package com.a201381061.digitallearning.Model;

/**
 * Created by UserModel on 6/29/2017.
 */

public class NewPostModel {

    private String judul;
    private String isi;
    private String userId;
    private String kategori;
    private int upvote;
    private int report;

    public NewPostModel(String judul, String isi, String userId,String kategori,int upvote,int report) {
        this.judul = judul;
        this.isi = isi;
        this.userId = userId;
        this.kategori = kategori;
        this.upvote = upvote;
        this.report = report;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getUpvote() {
        return upvote;
    }

    public void setUpvote(int upvote) {
        this.upvote = upvote;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }
}
