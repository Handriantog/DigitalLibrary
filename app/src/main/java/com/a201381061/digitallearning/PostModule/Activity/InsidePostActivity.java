package com.a201381061.digitallearning.PostModule.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a201381061.digitallearning.Model.CommentModel;
import com.a201381061.digitallearning.Model.PostModel;
import com.a201381061.digitallearning.PostModule.Utility.CommentAdapter;
import com.a201381061.digitallearning.PostModule.Utility.FirebaseSeePostUtil;
import com.a201381061.digitallearning.R;
import com.a201381061.digitallearning.Utils.BaseActivity;
import com.a201381061.digitallearning.Utils.SessionController;
import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InsidePostActivity extends BaseActivity {

    private static String ACTIVITY_TAG = "InsidePostActivity";
    private PostModel post;
    private TextView judulPost;
    private TextView isiPost;
    private TextView kategoriPost;
    private TextView usernamePost;
    private EditText editTextKomentar;
    private ImageView imageViewPostKomentar;

    private Toolbar bottomBar;
    private Toolbar toolbar;

    private String COMMENT_URL;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseSeePostUtil util;

    private RecyclerView recyclerView;
    private RecyclerViewHeader recyclerViewHeader;
    private CommentAdapter adapter;
    private List<CommentModel> listComment = new ArrayList<>();

    private String str_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_post);
        getPostData();
        castingElement();
        setUpData();
        setUpDatabase();
        setUpPostButton();
    }


    private void castingElement() {
        judulPost = (TextView) findViewById(R.id.textViewJudulInside);
        kategoriPost = (TextView) findViewById(R.id.textViewKategoriInside);
        isiPost = (TextView) findViewById(R.id.textViewIsiInside);
        usernamePost = (TextView) findViewById(R.id.textViewNamaUserInside);
        editTextKomentar = (EditText) findViewById(R.id.editTextCommentInside);
        imageViewPostKomentar = (ImageView) findViewById(R.id.imageViewReplyInside);
        bottomBar = (Toolbar) findViewById(R.id.bottomBarComment);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCommentInside);
        recyclerViewHeader = (RecyclerViewHeader)findViewById(R.id.recyclerViewHeader);

        setUpToolbar();

    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Read Post");
    }

    private void setUpPostButton() {
        imageViewPostKomentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postComment();
            }
        });
    }

    private void getPostData() {
        post = (PostModel) getIntent().getSerializableExtra("post");
    }

    private void setUpData() {
        Log.e(ACTIVITY_TAG, post.getJudul());
        judulPost.setText(post.getJudul());
        kategoriPost.setText(post.getKategori());
        isiPost.setText(post.getIsi());
    }

    private void setUpDatabase() {

        getCommentURL();

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference(COMMENT_URL);

        getCommentFromDB();

        adapter = new CommentAdapter(listComment, InsidePostActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(InsidePostActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewHeader.attachTo(recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    private void getCommentFromDB() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listComment.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.e(ACTIVITY_TAG, dataSnapshot.getValue().toString());
                    CommentModel allComment = dataSnapshot1.getValue(CommentModel.class);
                    CommentModel comment = new CommentModel();
                    comment.setNamaMahasiswa(allComment.getNamaMahasiswa());
                    comment.setNim(allComment.getNim());
                    comment.setIsiComment(allComment.getIsiComment());

                    listComment.add(comment);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(ACTIVITY_TAG, "Failed to get Data" + databaseError.toException());
            }
        });
    }

    private void getCommentURL() {
        SessionController sessionController = new SessionController(InsidePostActivity.this);
        COMMENT_URL = "post/" + sessionController.getFakultas() + "/" + post.getPostId() + "/" + "comment";
        Log.d(ACTIVITY_TAG, "Comment url = " + COMMENT_URL);
    }

    private void postComment(){
        if(verifyComment()){
            showProgressDialog();
            util = new FirebaseSeePostUtil(InsidePostActivity.this);
            util.addNewComment(str_comment,COMMENT_URL);
        }
    }

    private boolean verifyComment(){
        str_comment = editTextKomentar.getText().toString();
        if(str_comment.equals("")){
            return false;
        }else{
            return true;
        }
    }

    public void commentPosted(){
        hideProgressDialog();
        editTextKomentar.setText("");

        //hide keyboard
        InputMethodManager inputMethodManager =
                (InputMethodManager) this.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                this.getCurrentFocus().getWindowToken(), 0);
    }
}
