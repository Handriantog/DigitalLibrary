package com.a201381061.digitallearning.AllMateriModule.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a201381061.digitallearning.AllMateriModule.Utility.AllMateriAdapter;
import com.a201381061.digitallearning.Model.CommentModel;
import com.a201381061.digitallearning.Model.MateriModel;
import com.a201381061.digitallearning.Model.MatkulModel;
import com.a201381061.digitallearning.Model.PostModel;
import com.a201381061.digitallearning.PostModule.Activity.InsidePostActivity;
import com.a201381061.digitallearning.PostModule.Utility.CommentAdapter;
import com.a201381061.digitallearning.PostModule.Utility.FirebaseSeePostUtil;
import com.a201381061.digitallearning.R;
import com.a201381061.digitallearning.Utils.SessionController;
import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        getSupportActionBar().setTitle(kodeMatkul);
    }

    private void getMatkulData() {
        MatkulModel matkul = (MatkulModel) getIntent().getSerializableExtra("matkul");
        kodeMatkul = matkul.getKodeMatkul();
    }

    private void setUpDatabase() {

        getListMateriURL();

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference(LIST_MATERI_URL);

        getAllMateriFromDB();

        adapter = new AllMateriAdapter(listMateri,AllMateriActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AllMateriActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewHeader.attachTo(recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    private void getListMateriURL() {
        SessionController sessionController = new SessionController(AllMateriActivity.this);
        LIST_MATERI_URL = "matkul/" + sessionController.getFakultas() + "/" + kodeMatkul + "/materi";
        Log.d(ACTIVITY_TAG, "URL = " + LIST_MATERI_URL);
    }

    private void getAllMateriFromDB(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listMateri.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
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

            }
        });
    }
}
