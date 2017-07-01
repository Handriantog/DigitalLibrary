package com.a201381061.digitallearning.HomeModule.Utility;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by UserModel on 6/23/2017.
 */

public class FirebaseHomeUtil {

    private static String TAG = "FirebaseHomeUtil";
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference firebaseDatabase;

    public FirebaseHomeUtil(){
        mAuth = FirebaseAuth.getInstance();
    }

    public FirebaseUser getFirebaseUser(){
        mUser = mAuth.getCurrentUser();
        return mUser;
    }

    public String getFirebaseUserName(){
        String name;
        name = mAuth.getCurrentUser().getDisplayName();
        return name;
    }


    public void signOut(){
        mAuth.signOut();
    }

}
