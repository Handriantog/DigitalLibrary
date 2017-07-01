package com.a201381061.digitallearning.LoginModule.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a201381061.digitallearning.HomeModule.Activity.MainActivity;
import com.a201381061.digitallearning.R;
import com.a201381061.digitallearning.Utils.BaseActivity;
import com.a201381061.digitallearning.LoginModule.Utility.FirebaseAuthUtil;

public class LoginActivity extends BaseActivity {

    private static String ACTIVITY_TAG = "LoginActivity";
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewRegister;
    private String str_email;
    private String str_password;
    private Button buttonLogin;
    private FirebaseAuthUtil fb;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(ACTIVITY_TAG,"STARTED");
        castingElement();
        userLogin();
        userRegister();
    }

    private void castingElement(){
        editTextEmail = (EditText)findViewById(R.id.editTextEmailLogin);
        editTextPassword = (EditText)findViewById(R.id.editTextPasswordLogin);
        textViewRegister = (TextView)findViewById(R.id.textViewRegister);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
    }

    private void userRegister(){
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ACTIVITY_TAG,"UserModel Register");
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void userLogin(){
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
                if(validateForm()){
                    Log.d(ACTIVITY_TAG,"UserModel Login");
                    getLoginData();
                    fb = new FirebaseAuthUtil();
                    fb.signInUser(str_email,str_password,LoginActivity.this);
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

    public void signInFailed(){
        Log.d(ACTIVITY_TAG,"Sign In Failed");
        hideProgressDialog();
        Toast.makeText(LoginActivity.this,"Login Gagal",Toast.LENGTH_SHORT).show();
    }

    private boolean validateForm(){
        if(editTextEmail.getText().toString().equals("")||editTextPassword.getText().toString().equals("")){
            return false;
        }else{
            return true;
        }
    }

    private void getLoginData(){
        str_email = editTextEmail.getText().toString();
        str_password = editTextPassword.getText().toString();
    }

    public void registerSucced(){
        finish();
    }


}
