package com.a201381061.digitallearning.HomeModule.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.a201381061.digitallearning.AllMatkulModule.Activity.AllMatkulActivity;
import com.a201381061.digitallearning.Model.FakultasModel;
import com.a201381061.digitallearning.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFakultasFragment extends Fragment {

    private ListView listView;
    private List<String> listFakultas = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dbReference;

    private String LIST_ALL_FAKULTAS_URL = "fakultas";
    private static String FRAGMENT_TAG = "SearchFakultasFragment";

    public SearchFakultasFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_fakultas, container, false);

        //set up recycler view
        setListView(rootView);

        //get all fakultas from database
        setUpDatabase();

        //set click on fakultas
        setListClickListener();

        return rootView;
    }

    private void setListView(View view){
        listView = (ListView)view.findViewById(R.id.listViewFakultas);
    }

    private void setUpDatabase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbReference = firebaseDatabase.getReference(LIST_ALL_FAKULTAS_URL);

        getAllFakultas();

        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,listFakultas);
        listView.setAdapter(adapter);

    }

    private void getAllFakultas() {
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listFakultas.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.e(FRAGMENT_TAG, dataSnapshot.getValue().toString());
                    FakultasModel fakultasModel = dataSnapshot1.getValue(FakultasModel.class);
                    listFakultas.add(fakultasModel.getNama());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(FRAGMENT_TAG, "Failed to get Data" + databaseError.toException());
            }
        });
    }

    private void setListClickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent openFakultas  = new Intent(getActivity(), AllMatkulActivity.class);
                openFakultas.putExtra("fakultas",listFakultas.get(i));
                startActivity(openFakultas);
            }
        });
    }

}
