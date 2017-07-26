package com.a201381061.digitallearning.HomeModule.Utility;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a201381061.digitallearning.Model.PostModel;
import com.a201381061.digitallearning.PostModule.Activity.InsidePostActivity;
import com.a201381061.digitallearning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/17/2017.
 */

public class UserPostAdapter extends RecyclerView.Adapter<UserPostAdapter.MyHoder>{

    private List<PostModel> listPost = new ArrayList<>();
    private Context context;

    public UserPostAdapter(List<PostModel> list, Context context) {
        listPost = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_view_post, parent, false);
        MyHoder myHoder = new MyHoder(view);

        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        final PostModel myPostList = listPost.get(position);

        Log.e("TAG TES",myPostList.getJudul());
        holder.judul.setText(myPostList.getJudul());
        holder.kategori.setText(myPostList.getKategori());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, InsidePostActivity.class);
                i.putExtra("post",myPostList);
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listPost.size();
    }

    class MyHoder extends RecyclerView.ViewHolder {
        TextView judul, kategori, poin;
        CardView cardView;

        public MyHoder(View itemView) {
            super(itemView);
            judul = (TextView) itemView.findViewById(R.id.textViewJudulCard);
            kategori = (TextView) itemView.findViewById(R.id.textViewKategoriCard);
            poin = (TextView) itemView.findViewById(R.id.textViewJumlahPoinCard);
            cardView = (CardView) itemView.findViewById(R.id.cardViewPost);
        }
    }
}
