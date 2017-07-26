package com.a201381061.digitallearning.Model;

/**
 * Created by User on 7/4/2017.
 */

public class CommentModel {

    private String nim;
    private String namaMahasiswa;
    private String isiComment;

    public CommentModel() {
    }

    public CommentModel(String nim, String namaMahasiswa, String isiComment) {
        this.nim = nim;
        this.namaMahasiswa = namaMahasiswa;
        this.isiComment = isiComment;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public String getIsiComment() {
        return isiComment;
    }

    public void setIsiComment(String isiComment) {
        this.isiComment = isiComment;
    }
}
