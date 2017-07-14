package com.a201381061.digitallearning.Model;

/**
 * Created by User on 7/12/2017.
 */

public class MateriModel {

    private int jumlah_materi;
    private String nama_materi1;
    private String nama_materi2;
    private String nama_materi3;
    private String nama_materi4;
    private String url_file1;
    private String url_file2;
    private String url_file3;
    private String url_file4;

    public MateriModel() {
    }

    public MateriModel(int jumlah_materi, String nama_materi1, String nama_materi2, String nama_materi3, String nama_materi4, String url_file1, String url_file2, String url_file3, String url_file4) {
        this.jumlah_materi = jumlah_materi;
        this.nama_materi1 = nama_materi1;
        this.nama_materi2 = nama_materi2;
        this.nama_materi3 = nama_materi3;
        this.nama_materi4 = nama_materi4;
        this.url_file1 = url_file1;
        this.url_file2 = url_file2;
        this.url_file3 = url_file3;
        this.url_file4 = url_file4;
    }

    public int getJumlah_materi() {
        return jumlah_materi;
    }

    public void setJumlah_materi(int jumlah_materi) {
        this.jumlah_materi = jumlah_materi;
    }

    public String getUrl_file1() {
        return url_file1;
    }

    public void setUrl_file1(String url_file1) {
        this.url_file1 = url_file1;
    }

    public String getUrl_file2() {
        return url_file2;
    }

    public void setUrl_file2(String url_file2) {
        this.url_file2 = url_file2;
    }

    public String getUrl_file3() {
        return url_file3;
    }

    public void setUrl_file3(String url_file3) {
        this.url_file3 = url_file3;
    }

    public String getUrl_file4() {
        return url_file4;
    }

    public void setUrl_file4(String url_file4) {
        this.url_file4 = url_file4;
    }

    public String getNama_materi1() {
        return nama_materi1;
    }

    public void setNama_materi1(String nama_materi1) {
        this.nama_materi1 = nama_materi1;
    }

    public String getNama_materi2() {
        return nama_materi2;
    }

    public void setNama_materi2(String nama_materi2) {
        this.nama_materi2 = nama_materi2;
    }

    public String getNama_materi3() {
        return nama_materi3;
    }

    public void setNama_materi3(String nama_materi3) {
        this.nama_materi3 = nama_materi3;
    }

    public String getNama_materi4() {
        return nama_materi4;
    }

    public void setNama_materi4(String nama_materi4) {
        this.nama_materi4 = nama_materi4;
    }
}
