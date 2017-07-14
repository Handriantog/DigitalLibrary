package com.a201381061.digitallearning.LoginModule.Utility;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.a201381061.digitallearning.LoginModule.Activity.LoginActivity;
import com.a201381061.digitallearning.Model.UserModel;
import com.a201381061.digitallearning.Utils.SessionController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by UserModel on 6/18/2017.
 */

public class FirebaseSignInUtil {

    private static String TAG = "FirebaseSignInUtil";
    private DatabaseReference getLoginData;
    private FirebaseDatabase firebaseDatabase;
    private Context context;

    public FirebaseSignInUtil(Context context) {
        this.context = context;
    }

    private void databaseSetup() {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public void userSignIn(final String nim,final String password){
        databaseSetup();
        getLoginData = firebaseDatabase.getReference("user");
        getLoginData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(nim)){
                    Log.d(TAG,"Data exist");
                    UserModel userModel = dataSnapshot.child(nim).child("data").getValue(UserModel.class);
                    if(userModel.getPassword().equals(password)){
                        createNewUserCredential(userModel,nim);
                        ((LoginActivity)context).signInSuccess();
                    }else{
                        ((LoginActivity)context).signInFailed("Password Salah");
                    }
                }else{
                    ((LoginActivity)context).signInFailed("NIM Tidak Terdaftar");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void createNewUserCredential(UserModel model,String nim){
        SessionController sessionController = new SessionController(context);
        sessionController.createLoginSession(model.getNama(),nim,model.getJurusan()
        ,model.getFakultas(),model.getAngkatan(),model.getSemester());
    }




}
