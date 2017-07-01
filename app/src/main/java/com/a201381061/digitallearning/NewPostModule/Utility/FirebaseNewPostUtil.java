package com.a201381061.digitallearning.NewPostModule.Utility;

import android.content.Context;

import com.a201381061.digitallearning.Model.NewPost;
import com.a201381061.digitallearning.Utils.Constant;
import com.a201381061.digitallearning.Utils.SessionController;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by User on 6/29/2017.
 */

public class FirebaseNewPostUtil {

    private static final String TAG = "FirebaseNewPostUtil";
    private DatabaseReference firebaseDatabase;
    private FirebaseUser firebaseUser;
    private Context context;


    public FirebaseNewPostUtil(Context context){
        this.context = context;
    }

    private String getUserId(){
        return firebaseUser.getUid();
    }

    private String getFakultas(){
        SessionController sessionController = new SessionController(context);
        return  sessionController.getFakultas();
    }

    private void databaseSetup(){
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void addNewPost(String judul,String kategori,String isi){
        databaseSetup();

        NewPost newPost = new NewPost(judul,kategori,isi,getUserId());
        String idPost = firebaseDatabase.push().getKey();
        firebaseDatabase.child(new Constant().DB_POST).child(getFakultas()).child(idPost).setValue(newPost);
    }

}
