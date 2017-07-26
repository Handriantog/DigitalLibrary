package com.a201381061.digitallearning.AllMatkulModule.Utility;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.a201381061.digitallearning.Model.MatkulModel;
import com.a201381061.digitallearning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/18/2017.
 */

public class MatkulListViewAdapter extends ArrayAdapter<MatkulModel> {

    private List<MatkulModel> listMatkul = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private NameFilter filter;


    public MatkulListViewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<MatkulModel> objects) {
        super(context, resource, objects);
        this.context = context;
        listMatkul = objects;
    }

    public void setListMatkul(List<MatkulModel> listMatkul){
        this.listMatkul = listMatkul;
    }

    @Override
    public int getCount() {
        return listMatkul.size();
    }

    @Override
    public MatkulModel getItem(int i) {
        return listMatkul.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view==null)
        v = inflater.inflate(R.layout.list_view_matkul, null);

        TextView kode = (TextView)v.findViewById(R.id.textViewKodeMatkulList);
        TextView nama = (TextView)v.findViewById(R.id.textViewNamaMatkulList);

        MatkulModel matkul = listMatkul.get(i);
        kode.setText(matkul.getKodeMatkul());
        nama.setText(matkul.getNama_matkul());

        return v;
    }


    @NonNull
    @Override
    public Filter getFilter() {
        if (filter == null){
            filter  = new NameFilter();
        }
        return filter;
    }

    private class NameFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            Log.e("DATA FILTER",constraint.toString());
            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if(constraint != null && constraint.toString().length() > 0)
            {
                ArrayList<MatkulModel> filteredItems = new ArrayList<>();

                for(int i = 0, l = listMatkul.size(); i < l; i++)
                {
                    MatkulModel nameList = listMatkul.get(i);
                    if(nameList.getNama_matkul().toLowerCase().contains(constraint))
                        filteredItems.add(nameList);
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            }
            else
            {
                synchronized(this)
                {
                    result.values = listMatkul;
                    result.count = listMatkul.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
                List<MatkulModel> filterList = (ArrayList<MatkulModel>)results.values;
                notifyDataSetChanged();
                clear();
                for(int i = 0, l = filterList.size(); i < l; i++)
                    add(filterList.get(i));
                notifyDataSetInvalidated();
        }
    }

}
