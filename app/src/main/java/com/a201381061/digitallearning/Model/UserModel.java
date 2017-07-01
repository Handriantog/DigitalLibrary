package com.a201381061.digitallearning.Model;

/**
 * Created by UserModel on 6/21/2017.
 */

public class UserModel {

    private String nama;
    private String email;
    private String password;
    private String kampus;
    private String fakultas;

    public UserModel() {
    }

    public UserModel(String nama, String email, String password, String kampus, String fakultas) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.kampus = kampus;
        this.fakultas = fakultas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKampus() {
        return kampus;
    }

    public void setKampus(String kampus) {
        this.kampus = kampus;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }
}
