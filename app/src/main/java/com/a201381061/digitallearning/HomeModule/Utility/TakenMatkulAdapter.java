package com.a201381061.digitallearning.HomeModule.Utility;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a201381061.digitallearning.AllMateriModule.Activity.AllMateriActivity;
import com.a201381061.digitallearning.Model.MatkulModel;
import com.a201381061.digitallearning.Model.PostModel;
import com.a201381061.digitallearning.PostModule.Activity.InsidePostActivity;
import com.a201381061.digitallearning.R;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class TakenMatkulAdapter extends RecyclerView.Adapter<TakenMatkulAdapter.MyHoder> {

    private List<MatkulModel> listTakenMatkul = new ArrayList<>();
    private Context context;

    public TakenMatkulAdapter(List<MatkulModel> list, Context context) {
        listTakenMatkul = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_view_taken_matkul, parent, false);
        MyHoder myHoder = new MyHoder(view);

        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        final MatkulModel myMatkulList = listTakenMatkul.get(position);

        Log.e("TAG TES", myMatkulList.getKodeMatkul());
        holder.namaMatkul.setText(myMatkulList.getNama_matkul());
        holder.kodeMatkul.setText(myMatkulList.getKodeMatkul());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AllMateriActivity.class);
                i.putExtra("matkul", myMatkulList);
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listTakenMatkul.size();
    }

    class MyHoder extends RecyclerView.ViewHolder {
        TextView namaMatkul, kodeMatkul;
        CardView cardView;

        public MyHoder(View itemView) {
            super(itemView);
            namaMatkul = (TextView) itemView.findViewById(R.id.textViewNamaMatkulCard);
            kodeMatkul = (TextView) itemView.findViewById(R.id.textViewKodeMatkulCard);
            cardView = (CardView) itemView.findViewById(R.id.cardViewPost);
        }
    }

}