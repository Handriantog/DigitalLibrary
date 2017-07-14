package com.a201381061.digitallearning.LoginModule.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a201381061.digitallearning.HomeModule.Activity.MainActivity;
import com.a201381061.digitallearning.LoginModule.Utility.FirebaseSignInUtil;
import com.a201381061.digitallearning.R;
import com.a201381061.digitallearning.Utils.BaseActivity;

public class LoginActivity extends BaseActivity {

    private static String ACTIVITY_TAG = "LoginActivity";
    private EditText editTextNim;
    private EditText editTextPassword;
    private String str_nim;
    private String str_password;
    private Button buttonLogin;
    private FirebaseSignInUtil fb;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(ACTIVITY_TAG,"STARTED");
        castingElement();
        userLogin();
    }

    private void castingElement(){
        editTextNim = (EditText)findViewById(R.id.editTextNimLogin);
        editTextPassword = (EditText)findViewById(R.id.editTextPasswordLogin);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
    }

    private void userLogin(){
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
                if(validateForm()){
                    Log.d(ACTIVITY_TAG,"UserModel Login");
                    getLoginData();
                    fb = new FirebaseSignInUtil(LoginActivity.this);
                    fb.userSignIn(str_nim,str_password);
                }else{
                    hideProgressDialog();
                    Toast.makeText(LoginActivity.this,"Form Tidak Lengkap",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signInSuccess(){
        Log.d(ACTIVITY_TAG,"Sign In Success");
        hideProgressDialog();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    public void signInFailed(String message){
        Log.d(ACTIVITY_TAG,"Sign In Failed");
        hideProgressDialog();
        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    private boolean validateForm(){
        if(editTextNim.getText().toString().equals("")||editTextPassword.getText().toString().equals("")){
            return false;
        }else{
            return true;
        }
    }

    private void getLoginData(){
        str_nim = editTextNim.getText().toString();
        str_password = editTextPassword.getText().toString();
    }



}
