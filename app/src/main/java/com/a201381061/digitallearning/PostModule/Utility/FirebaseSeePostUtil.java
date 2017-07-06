package com.a201381061.digitallearning.PostModule.Utility;

import android.content.Context;
import android.util.Log;

import com.a201381061.digitallearning.Model.CommentModel;
import com.a201381061.digitallearning.Model.NewPostModel;
import com.a201381061.digitallearning.NewPostModule.Activity.NewPostActivity;
import com.a201381061.digitallearning.PostModule.Activity.InsidePostActivity;
import com.a201381061.digitallearning.Utils.Constant;
import com.a201381061.digitallearning.Utils.SessionController;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by User on 7/4/2017.
 */

public class FirebaseSeePostUtil {

    private static final String TAG = "FirebaseSeePostUtil";
    private DatabaseReference firebaseDatabase;
    private FirebaseUser firebaseUser;
    private Context context;


    public FirebaseSeePostUtil(Context context){
        this.context = context;
    }

    private String getUserId(){
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return firebaseUser.getUid();
    }

    private String getUserName(){
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return firebaseUser.getDisplayName();
    }

    private void databaseSetup(){
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void addNewComment(String isi,String url){
        databaseSetup();

        Log.e(TAG,getUserId());

        CommentModel comment = new CommentModel(getUserId(),getUserName(),isi);

        String idComment = firebaseDatabase.push().getKey();
        firebaseDatabase.child(url).child(idComment).setValue(comment, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                ((InsidePostActivity)context).commentPosted();
            }
        });
    }

}
