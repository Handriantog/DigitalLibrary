package com.a201381061.digitallearning.LoginModule.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a201381061.digitallearning.HomeModule.Activity.MainActivity;
import com.a201381061.digitallearning.LoginModule.Utility.FirebaseAuthUtil;
import com.a201381061.digitallearning.R;
import com.a201381061.digitallearning.Utils.BaseActivity;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RegisterActivity extends BaseActivity {

    private EditText editTextNama;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextKampus;
    private NiceSpinner spinnerFakultas;
    private Button buttonRegister;

    private TextInputLayout inputLayoutNama;
    private TextInputLayout inputLayoutEmail;
    private TextInputLayout inputLayoutPassword;
    private TextInputLayout inputLayoutKampus;

    private String ACTIVITY_TAG = "RegisterActivity";
    private FirebaseAuthUtil fb;
    private String str_nama;
    private String str_email;
    private String str_password;
    private String str_kampus;
    private String str_fakultas = "Ilmu Komputer";


    List<String> FAKULTAS = new LinkedList<>(Arrays.asList("Ilmu Komputer", "Ekonomi", "Psikologi", "Hukum"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        castingElement();
        userRegister();
    }

    private void castingElement() {
        editTextNama = (EditText) findViewById(R.id.editTextNamaRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmailRegister);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordRegister);
        editTextKampus = (EditText) findViewById(R.id.editTextLokasiKampusRegister);
        spinnerFakultas = (NiceSpinner) findViewById(R.id.spinnerFakultasRegister);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        inputLayoutNama = (TextInputLayout) findViewById(R.id.inputLayoutNamaRegister);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.inputLayoutEmailRegister);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.inputLayoutPasswordRegister);
        inputLayoutKampus = (TextInputLayout) findViewById(R.id.inputLayoutKampusRegister);

        setUpSpinner();
        inputLayoutListener();
    }

    private void userRegister() {
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ACTIVITY_TAG, "User Register");
                if (validateForm()) {
                    showProgressDialog();
                    fb = new FirebaseAuthUtil();
                    fb.registerNewUser(str_nama, str_email, str_password, str_kampus, str_fakultas, RegisterActivity.this);
                } else {
                    Toast.makeText(RegisterActivity.this, "Form Belum Lengkap", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void registerSuccess() {
        hideProgressDialog();
        Toast.makeText(getApplicationContext(), "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(RegisterActivity.this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    public void registerFailed() {
        hideProgressDialog();
    }

    private boolean validateForm() {
        if (validateNama() && validateEmail() && validatePassword() && validateKampus() && validateFakultas()) {
            return true;
        } else {
            return false;
        }
    }

    private void setUpSpinner() {
        spinnerFakultas.attachDataSource(FAKULTAS);
        spinnerFakultas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                str_fakultas = FAKULTAS.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validateNama() {
        str_nama = editTextNama.getText().toString();
        if (str_nama.equals("")) {
            inputLayoutNama.setError("Masukkan Nama");
            requestFocus(inputLayoutNama);
            return false;
        } else {
            inputLayoutNama.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        str_email = editTextEmail.getText().toString();
        if (str_email.equals("") || !isValidEmail(str_email)) {
            inputLayoutEmail.setError("Masukkan Email yang benar");
            requestFocus(inputLayoutEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        str_password = editTextPassword.getText().toString();
        if (str_password.equals("")) {
            inputLayoutPassword.setError("Masukkan Password");
            requestFocus(inputLayoutPassword);
            return false;
        } else if (str_password.length() < 6) {
            inputLayoutPassword.setError("Password Kurang dari 6 Karakter");
            requestFocus(inputLayoutPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateKampus() {
        str_kampus = editTextKampus.getText().toString();
        if (str_kampus.equals("")) {
            inputLayoutKampus.setError("Masukkan Lokasi Kampus");
            requestFocus(inputLayoutKampus);
            return false;
        } else {
            inputLayoutKampus.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFakultas() {
        if (str_fakultas == null) {
            Toast.makeText(RegisterActivity.this, "Pilih Fakultas", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }


    private void inputLayoutListener() {
        editTextNama.addTextChangedListener(new MyTextWatcher(editTextNama));
        editTextEmail.addTextChangedListener(new MyTextWatcher(editTextEmail));
        editTextPassword.addTextChangedListener(new MyTextWatcher(editTextPassword));
        editTextKampus.addTextChangedListener(new MyTextWatcher(editTextKampus));
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.editTextNamaRegister:
                    validateNama();
                    break;
                case R.id.editTextEmailRegister:
                    validateEmail();
                    break;
                case R.id.editTextPasswordRegister:
                    validatePassword();
                    break;
                case R.id.editTextLokasiKampusRegister:
                    validateKampus();
                    break;
            }
        }
    }
}
