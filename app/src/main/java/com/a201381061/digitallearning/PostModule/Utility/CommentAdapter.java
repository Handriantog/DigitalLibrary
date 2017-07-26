package com.a201381061.digitallearning.PostModule.Utility;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a201381061.digitallearning.Model.CommentModel;
import com.a201381061.digitallearning.R;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyHoder> {

    private List<CommentModel> listComment = new ArrayList<>();
    private Context context;

    public CommentAdapter(List<CommentModel> list, Context context) {
        listComment = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_view_comment, parent, false);
        MyHoder myHoder = new MyHoder(view);

        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        CommentModel commentModel = listComment.get(position);

        Log.e("TAG TES", commentModel.getIsiComment());
        holder.nama.setText(commentModel.getNamaMahasiswa());
        holder.isi.setText(commentModel.getIsiComment());

    }


    @Override
    public int getItemCount() {
        return listComment.size();
    }

    class MyHoder extends RecyclerView.ViewHolder {
        TextView nama, isi;
        CardView cardView;

        public MyHoder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardViewComment);
            nama = (TextView) itemView.findViewById(R.id.textViewNamaComment);
            isi = (TextView) itemView.findViewById(R.id.textViewIsiComment);
        }
    }

}
