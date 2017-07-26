package com.a201381061.digitallearning.AllMatkulModule.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.a201381061.digitallearning.AllMateriModule.Activity.AllMateriActivity;
import com.a201381061.digitallearning.AllMatkulModule.Utility.MatkulListViewAdapter;
import com.a201381061.digitallearning.Model.MatkulModel;
import com.a201381061.digitallearning.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllMatkulActivity extends AppCompatActivity {

    private EditText searchBar;
    private ListView listView;
    private List<MatkulModel> listMatkul = new ArrayList<>();
    private List<MatkulModel> originalList = new ArrayList<>();
    private MatkulListViewAdapter adapter;

    private String fakultas;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private String ALL_MATKUL_URL;

    private static String ACTIVITY_TAG = "AllMatkulActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_matkul);

        getFakultas();
        getAllMatkulURL();
        castingElement();
        setUpDatabase();
        setUpSearchBar();

    }

    private void getFakultas(){
        fakultas = getIntent().getStringExtra("fakultas");
    }

    private void castingElement(){
        searchBar = (EditText)findViewById(R.id.editTextSearchMatkul);
        listView = (ListView)findViewById(R.id.listViewMatkul);
    }

    private void getAllMatkulURL(){
        ALL_MATKUL_URL = "matkul/" + fakultas;
        Log.e("URL",ALL_MATKUL_URL);
    }

    private void setUpDatabase(){
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference(ALL_MATKUL_URL);

        getAllMatkulData();

        adapter = new MatkulListViewAdapter(AllMatkulActivity.this,R.layout.list_view_matkul,listMatkul);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);

    }

    private void getAllMatkulData(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listMatkul.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.e(ACTIVITY_TAG, dataSnapshot.getValue().toString());
                    MatkulModel matkul = dataSnapshot1.getValue(MatkulModel.class);
                    MatkulModel matkulModel = new MatkulModel();
                    matkulModel.setKodeMatkul(dataSnapshot1.getKey());
                    matkulModel.setNama_matkul(matkul.getNama_matkul());

                    listMatkul.add(matkulModel);
                }
                originalList = listMatkul;
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setUpSearchBar(){
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")){
                    adapter.setListMatkul(originalList);
                    adapter.notifyDataSetChanged();
                }else{
                    adapter.getFilter().filter(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchBar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                return false;
            }
        });
    }


}
