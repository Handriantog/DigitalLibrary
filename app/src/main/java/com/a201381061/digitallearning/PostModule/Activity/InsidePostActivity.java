package com.a201381061.digitallearning.PostModule.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.a201381061.digitallearning.Model.PostModel;
import com.a201381061.digitallearning.R;

public class InsidePostActivity extends AppCompatActivity {

    private static String ACTIVITY_TAG = "InsidePostActivity";
    private PostModel post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_post);
        getPostData();
        Log.e("Judul",post.getJudul());
    }

    private void getPostData(){
        post = (PostModel)getIntent().getSerializableExtra("post");
    }
}
