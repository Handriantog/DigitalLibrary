package com.a201381061.digitallearning.LoginModule.Utility;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.a201381061.digitallearning.LoginModule.Activity.RegisterActivity;
import com.a201381061.digitallearning.LoginModule.Activity.LoginActivity;
import com.a201381061.digitallearning.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by User on 6/18/2017.
 */

public class FirebaseAuthUtil {

    private static String TAG = "FirebaseAuthUtil";
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference firebaseDatabase;

    public FirebaseAuthUtil(){
        mAuth = FirebaseAuth.getInstance();
    }

    public boolean checkIfUserExist(){
        mUser = mAuth.getCurrentUser();
        if(mUser==null){
            return false;
        }else{
            return true;
        }
    }

    public void signInUser(String email, String password, final Activity activity){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG,"Login Success");
                    ((LoginActivity)activity).signInSuccess();
                }else{
                    Log.d(TAG,"Register Failed");
                    Log.e(TAG,task.getException().getMessage());
                    ((LoginActivity)activity).signInFailed();
                }
            }
        });
    }

    public void registerNewUser(final String nama,final String email,final String password,final String kampus, final Activity activity){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG,"Register Success");
                    writeNewUser(task.getResult().getUser().getUid(),nama,email,password,kampus);
                    ((RegisterActivity)activity).registerSuccess();
                }else{
                    Log.d(TAG,"Register Failed");
                    Log.e(TAG,task.getException().getMessage());
                    ((RegisterActivity)activity).registerFailed();
                }
            }
        });
    }

    private void writeNewUser(String id,String nama, String email, String password, String kampus){
        databaseSetup();

        User user = new User(nama,email,password,kampus);
        firebaseDatabase.child("user").child(id).setValue(user);

    }

    private void databaseSetup(){
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
    }


}
