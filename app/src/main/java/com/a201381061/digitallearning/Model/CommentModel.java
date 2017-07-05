package com.a201381061.digitallearning.Model;

/**
 * Created by User on 7/4/2017.
 */

public class CommentModel {

    private String idUser;
    private String namaUser;
    private String isiComment;

    public CommentModel() {
    }

    public CommentModel(String idUser, String namaUser, String isiComment) {
        this.idUser = idUser;
        this.namaUser = namaUser;
        this.isiComment = isiComment;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getIsiComment() {
        return isiComment;
    }

    public void setIsiComment(String isiComment) {
        this.isiComment = isiComment;
    }
}
