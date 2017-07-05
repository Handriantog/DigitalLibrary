package com.a201381061.digitallearning.HomeModule.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a201381061.digitallearning.HomeModule.Utility.RecyclerViewAdapter;
import com.a201381061.digitallearning.Model.PostModel;
import com.a201381061.digitallearning.R;
import com.a201381061.digitallearning.Utils.SessionController;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewPost;
    private RecyclerViewAdapter adapter;
    private String LIST_POST_URL;
    private List<PostModel> postModelList = new ArrayList<>();
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dbReference;
    private String FRAGMENT_TAG = "HomeFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //casting recycler view
        recyclerViewSetup(rootView);

        //mendapat list post url dari fakultas
        getAllListURL();

        //mengambil data dari database
        setUpDatabase();
        return rootView;
    }

    private void recyclerViewSetup(View view) {
        recyclerViewPost = (RecyclerView) view.findViewById(R.id.recyclerViewPost);
    }

    private void setUpDatabase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbReference = firebaseDatabase.getReference(LIST_POST_URL);

        getAllPostData();

        adapter = new RecyclerViewAdapter(postModelList, getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewPost.setLayoutManager(layoutManager);
        recyclerViewPost.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPost.setAdapter(adapter);

    }

    private void getAllPostData() {
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postModelList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.e(FRAGMENT_TAG, dataSnapshot.getValue().toString());
                    PostModel post = dataSnapshot1.getValue(PostModel.class);
                    PostModel isiPost = new PostModel();
                    isiPost.setJudul(post.getJudul());
                    isiPost.setKategori(post.getKategori());
                    isiPost.setIsi(post.getIsi());
                    isiPost.setPostId(dataSnapshot1.getKey());

                    postModelList.add(isiPost);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(FRAGMENT_TAG, "Failed to get Data" + databaseError.toException());
            }
        });
    }


    private void getAllListURL() {
        SessionController sessionController = new SessionController(getActivity());
        LIST_POST_URL = "post/" + sessionController.getFakultas();
    }
}
