package com.a201381061.digitallearning.Model;

/**
 * Created by User on 6/21/2017.
 */

public class User {

    private String nama;
    private String email;
    private String password;
    private String kampus;

    public User() {
    }

    public User(String nama, String email, String password, String kampus) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.kampus = kampus;
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
}
