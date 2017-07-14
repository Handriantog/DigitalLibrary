package com.a201381061.digitallearning.Model;

/**
 * Created by UserModel on 6/21/2017.
 */

public class UserModel {

    private String nim;
    private String nama;
    private String password;
    private String fakultas;
    private String jurusan;
    private int semester;
    private int angkatan;

    public UserModel() {
    }

    public UserModel(String nama, String password, String fakultas, String jurusan, int semester, int angkatan,String nim) {
        this.nama = nama;
        this.password = password;
        this.fakultas = fakultas;
        this.jurusan = jurusan;
        this.semester = semester;
        this.angkatan = angkatan;
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(int angkatan) {
        this.angkatan = angkatan;
    }
}
