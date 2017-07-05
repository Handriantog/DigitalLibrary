package com.a201381061.digitallearning.PostModule.Utility;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a201381061.digitallearning.Model.CommentModel;
import com.a201381061.digitallearning.Model.PostModel;
import com.a201381061.digitallearning.PostModule.Activity.InsidePostActivity;
import com.a201381061.digitallearning.R;

import android.widget.LinearLayout;
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
        holder.nama.setText(commentModel.getNamaUser());
        holder.isi.setText(commentModel.getIsiComment());

    }


    @Override
    public int getItemCount() {
        return listComment.size();
    }

    /*@Override
    public int getItemViewType(int position) {
        if(.get(position).isHeader)
            return VIEW_TYPES.Header;
        else if(items.get(position).isFooter)
            return VIEW_TYPES.Footer;
        else
            return VIEW_TYPES.Normal;
    }*/

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

    private class VIEW_TYPES {
        public static final int Header = 1;
        public static final int Normal = 2;
    }
}
