package com.a201381061.digitallearning.NewPostModule.Utility;

import android.content.Context;
import android.util.Log;

import com.a201381061.digitallearning.Model.NewPostModel;
import com.a201381061.digitallearning.NewPostModule.Activity.NewPostActivity;
import com.a201381061.digitallearning.Utils.Constant;
import com.a201381061.digitallearning.Utils.SessionController;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by UserModel on 6/29/2017.
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
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
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

        NewPostModel newPost = new NewPostModel(judul,isi,getUserId());
        String idPost = firebaseDatabase.push().getKey();
        Log.e("ID POST",idPost);
        firebaseDatabase.child(new Constant().DB_POST).child(getFakultas()).child(kategori).child(idPost).setValue(newPost, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                ((NewPostActivity)context).postSelesai();
            }
        });
    }

}
