package com.a201381061.digitallearning.LoginModule.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a201381061.digitallearning.HomeModule.Activity.MainActivity;
import com.a201381061.digitallearning.LoginModule.Utility.FirebaseAuthUtil;
import com.a201381061.digitallearning.R;
import com.a201381061.digitallearning.Utils.BaseActivity;

public class RegisterActivity extends BaseActivity {

    private EditText editTextNama;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextKampus;
    private Button buttonRegister;
    private String ACTIVITY_TAG = "RegisterActivity";
    private FirebaseAuthUtil fb;
    private String str_nama;
    private String str_email;
    private String str_password;
    private String str_kampus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        castingElement();
        userRegister();
    }

    private void castingElement(){
        editTextNama = (EditText)findViewById(R.id.editTextNamaRegister);
        editTextEmail = (EditText)findViewById(R.id.editTextEmailRegister);
        editTextPassword = (EditText)findViewById(R.id.editTextPasswordRegister);
        editTextKampus = (EditText)findViewById(R.id.editTextLokasiKampusRegister);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);
    }

    private void userRegister(){
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ACTIVITY_TAG,"User Register");
                showProgressDialog();
                if(validateForm()){
                    getAllFormData();
                    fb = new FirebaseAuthUtil();
                    fb.registerNewUser(str_nama,str_email,str_password,str_kampus,RegisterActivity.this);
                }
            }
        });
    }

    public void registerSuccess(){
        hideProgressDialog();
        Toast.makeText(getApplicationContext(),"Registrasi Berhasil",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
    }

    public void registerFailed(){
        hideProgressDialog();
    }

    private boolean validateForm(){
        if(editTextNama.getText().toString().equals("")||
                editTextEmail.getText().toString().equals("")||
                editTextPassword.getText().toString().equals("")||
                editTextKampus.getText().toString().equals("")){
            Toast.makeText(RegisterActivity.this,"Form Belum Lengkap",Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    private void getAllFormData(){
        str_nama = editTextNama.getText().toString();
        str_email = editTextEmail.getText().toString();
        str_password = editTextPassword.getText().toString();
        str_kampus = editTextKampus.getText().toString();
    }
}
