package com.a201381061.digitallearning.HomeModule.Utility;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a201381061.digitallearning.Model.PostModel;
import com.a201381061.digitallearning.R;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UserModel on 7/1/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHoder> {

    List<PostModel> listPost = new ArrayList<>();
    Context context;

    public RecyclerViewAdapter(List<PostModel> list, Context context) {
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
        PostModel myPostList = listPost.get(position);

        Log.e("TAG TES",myPostList.getJudul());
        holder.judul.setText(myPostList.getJudul());
        holder.kategori.setText(myPostList.getKategori());
    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }

    class MyHoder extends RecyclerView.ViewHolder {
        TextView judul, kategori, poin;


        public MyHoder(View itemView) {
            super(itemView);
            judul = (TextView) itemView.findViewById(R.id.textViewJudulCard);
            kategori = (TextView) itemView.findViewById(R.id.textViewKategoriCard);
            poin = (TextView) itemView.findViewById(R.id.textViewJumlahPoinCard);

        }
    }

}