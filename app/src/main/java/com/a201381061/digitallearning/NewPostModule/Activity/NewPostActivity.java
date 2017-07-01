package com.a201381061.digitallearning.NewPostModule.Activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a201381061.digitallearning.NewPostModule.Utility.FirebaseNewPostUtil;
import com.a201381061.digitallearning.R;
import com.a201381061.digitallearning.Utils.BaseActivity;

public class NewPostActivity extends BaseActivity {

    private EditText editTextJudul;
    private EditText editTextKategori;
    private EditText editTextIsi;
    private TextView textViewNamaFile;
    private Button buttonPilihFile;
    private Button buttonPost;

    private TextInputLayout inputLayoutJudul;
    private TextInputLayout inputLayoutKategori;
    private TextInputLayout inputLayoutIsi;

    private String str_judul;
    private String str_kategori;
    private String str_isi;

    private FirebaseNewPostUtil newPostUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        castingElement();
        postMateri();
    }

    private void castingElement() {
        editTextJudul = (EditText) findViewById(R.id.editTextJudulPost);
        editTextKategori = (EditText) findViewById(R.id.editTextKategoriPost);
        editTextIsi = (EditText) findViewById(R.id.editTextIsiPost);
        textViewNamaFile = (TextView) findViewById(R.id.textViewNamaFilePost);
        buttonPilihFile = (Button) findViewById(R.id.buttonInsertFilePost);
        buttonPost = (Button) findViewById(R.id.buttonPost);

        inputLayoutJudul = (TextInputLayout) findViewById(R.id.inputLayoutJudulPost);
        inputLayoutKategori = (TextInputLayout) findViewById(R.id.inputLayoutKategoriPost);
        inputLayoutIsi = (TextInputLayout) findViewById(R.id.inputLayoutIsiPost);

        editTextListener();
    }

    private void editTextListener() {
        editTextJudul.addTextChangedListener(new MyEditTextListener(editTextJudul));
        editTextKategori.addTextChangedListener(new MyEditTextListener(editTextKategori));
        editTextIsi.addTextChangedListener(new MyEditTextListener(editTextIsi));
    }

    private void postMateri(){
        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog();
                newPostUtil = new FirebaseNewPostUtil(NewPostActivity.this);
                newPostUtil.addNewPost(str_judul,str_kategori,str_isi);
            }
        });
    }

    private boolean validateJudul() {
        str_judul = editTextJudul.getText().toString();
        if (str_judul.equals("")) {
            inputLayoutJudul.setError("Masukkan Judul");
            return false;
        } else {
            inputLayoutJudul.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateKategori() {
        str_kategori = editTextKategori.getText().toString();
        if (str_kategori.equals("")) {
            inputLayoutKategori.setError("Masukkan Kategori");
            return false;
        } else {
            inputLayoutKategori.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateIsi() {
        str_isi = editTextIsi.getText().toString();
        if (str_isi.equals("")) {
            inputLayoutIsi.setError("Masukkan Isi PostModel");
            return false;
        } else {
            inputLayoutIsi.setErrorEnabled(false);
            return true;
        }
    }


    private class MyEditTextListener implements TextWatcher {

        private View view;

        private MyEditTextListener(View view) {
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
                case R.id.editTextJudulPost:
                    validateJudul();
                    break;
                case R.id.editTextKategoriPost:
                    validateKategori();
                    break;
                case R.id.editTextIsiPost:
                    validateIsi();
                    break;
            }
        }
    }

    public void postSelesai(){
        hideProgressDialog();
        Toast.makeText(NewPostActivity.this,"PostModel Berhasil",Toast.LENGTH_SHORT).show();
        finish();
    }
}
