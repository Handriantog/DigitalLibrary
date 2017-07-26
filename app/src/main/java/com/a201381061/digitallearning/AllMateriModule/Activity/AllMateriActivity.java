package com.a201381061.digitallearning.AllMateriModule.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.TextView;
import android.widget.Toast;

import com.a201381061.digitallearning.AllMateriModule.Utility.AllMateriAdapter;
import com.a201381061.digitallearning.Model.MateriModel;
import com.a201381061.digitallearning.Model.MatkulModel;
import com.a201381061.digitallearning.R;
import com.a201381061.digitallearning.Utils.SessionController;
import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AllMateriActivity extends AppCompatActivity {

    private static String ACTIVITY_TAG = "AllMateriActivity";
    private MateriModel materi;
    private TextView textViewJudulMatkul;

    private Toolbar toolbar;

    private String LIST_MATERI_URL;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private RecyclerView recyclerView;
    private RecyclerViewHeader recyclerViewHeader;
    private AllMateriAdapter adapter;
    private List<MateriModel> listMateri = new ArrayList<>();

    private String kodeMatkul;
    private String judulMatkul;

    private SessionController sessionController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_materi);
        getMatkulData();
        castingElement();
        setUpDatabase();

    }

    private void castingElement() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMateri);
        recyclerViewHeader = (RecyclerViewHeader) findViewById(R.id.recyclerViewHeader);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpToolbar();
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(kodeMatkul + "  " + judulMatkul);
    }

    private void getMatkulData() {
        MatkulModel matkul = (MatkulModel) getIntent().getSerializableExtra("matkul");
        kodeMatkul = matkul.getKodeMatkul();
        judulMatkul = matkul.getNama_matkul();
    }

    private void setUpDatabase() {

        Log.d(ACTIVITY_TAG,"Set Up Database");

        getListMateriURL();

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference(LIST_MATERI_URL);

        getAllMateriFromDB();

        adapter = new AllMateriAdapter(listMateri,AllMateriActivity.this,sessionController.getFakultas(),kodeMatkul);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AllMateriActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewHeader.attachTo(recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    private void getListMateriURL() {
        sessionController = new SessionController(AllMateriActivity.this);
        LIST_MATERI_URL = "matkul/" + sessionController.getFakultas() + "/" + kodeMatkul + "/materi";
        Log.d(ACTIVITY_TAG, "URL = " + LIST_MATERI_URL);
    }

    private void getAllMateriFromDB(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(ACTIVITY_TAG,"Data Get");
                listMateri.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Log.d(ACTIVITY_TAG,dataSnapshot.getChildren().toString());
                    MateriModel materiModel = dataSnapshot1.getValue(MateriModel.class);
                    MateriModel materi = new MateriModel();
                    materi.setJumlah_materi(materiModel.getJumlah_materi());
                    for(int i=0;i<materi.getJumlah_materi();i++){
                        if(i==0){
                            materi.setNama_materi1(materiModel.getNama_materi1());
                            materi.setUrl_file1(materiModel.getUrl_file1());
                        }else if(i==1){
                            materi.setNama_materi2(materiModel.getNama_materi2());
                            materi.setUrl_file2(materiModel.getUrl_file2());
                        }else if(i==2){
                            materi.setNama_materi3(materiModel.getNama_materi3());
                            materi.setUrl_file3(materiModel.getUrl_file3());
                        }else if(i==3){
                            materi.setNama_materi4(materiModel.getNama_materi4());
                            materi.setUrl_file4(materiModel.getUrl_file4());
                        }
                    }
                    listMateri.add(materi);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(ACTIVITY_TAG,databaseError.toString());
            }
        });
    }

    public void openDownloadedFile(String fileLocation){
        if(checkIfFileExist(fileLocation)){
            openSavedFile(fileLocation);
        }else{
            Toast.makeText(AllMateriActivity.this,"File Not Exist",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkIfFileExist(String fileLocation){
        File file = new File(Environment.getExternalStorageDirectory()+fileLocation);
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

    private void openSavedFile(String fileLocation){
        String fileLoc = Environment.getDataDirectory()+fileLocation;
        File file = new File(Environment.getExternalStorageDirectory()+fileLocation);

        MimeTypeMap myMime = MimeTypeMap.getSingleton();
        Intent newIntent = new Intent(Intent.ACTION_VIEW);
        String mimeType = myMime.getMimeTypeFromExtension(fileExt(fileLoc).substring(1));
        newIntent.setDataAndType(Uri.fromFile(file),mimeType);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(newIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(AllMateriActivity.this, "No handler for this type of file.", Toast.LENGTH_LONG).show();
        }
    }

    private String fileExt(String url) {
        if (url.indexOf("?") > -1) {
            url = url.substring(0, url.indexOf("?"));
        }
        if (url.lastIndexOf(".") == -1) {
            return null;
        } else {
            String ext = url.substring(url.lastIndexOf(".") + 1);
            if (ext.indexOf("%") > -1) {
                ext = ext.substring(0, ext.indexOf("%"));
            }
            if (ext.indexOf("/") > -1) {
                ext = ext.substring(0, ext.indexOf("/"));
            }
            return ext.toLowerCase();

        }
    }
}
