package com.a201381061.digitallearning.Model;

import java.io.Serializable;

/**
 * Created by User on 7/12/2017.
 */

public class MatkulModel implements Serializable{

    private String kodeMatkul;
    private String dosen;
    private String nama_matkul;
    private int sks;

    public MatkulModel() {
    }

    public MatkulModel(String kodeMatkul, String dosen, String nama_matkul, int sks) {
        this.kodeMatkul = kodeMatkul;
        this.dosen = dosen;
        this.nama_matkul = nama_matkul;
        this.sks = sks;
    }

    public String getKodeMatkul() {
        return kodeMatkul;
    }

    public void setKodeMatkul(String kodeMatkul) {
        this.kodeMatkul = kodeMatkul;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public String getNama_matkul() {
        return nama_matkul;
    }

    public void setNama_matkul(String nama_matkul) {
        this.nama_matkul = nama_matkul;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }
}
