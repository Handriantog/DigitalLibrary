package com.a201381061.digitallearning.AllMateriModule.Utility;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.a201381061.digitallearning.Model.MateriModel;
import com.a201381061.digitallearning.R;
import com.a201381061.digitallearning.Utils.SessionController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class AllMateriAdapter extends RecyclerView.Adapter<AllMateriAdapter.MyHoder> {

    private List<MateriModel> listMateri = new ArrayList<>();
    private Context context;
    private int jumlahMateri;
    private String fakultas;
    private String kodeMatkul;

    private static String TAG = "AllMateriAdapter";

    public AllMateriAdapter(List<MateriModel> list, Context context,String fakultas,String kodeMatkul) {
        listMateri = list;
        this.context = context;
        this.fakultas = fakultas;
        this.kodeMatkul = kodeMatkul;
    }

    @Override
    public AllMateriAdapter.MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_view_materi_kuliah, parent, false);
        AllMateriAdapter.MyHoder myHoder = new AllMateriAdapter.MyHoder(view);

        return myHoder;
    }

    @Override
    public void onBindViewHolder(AllMateriAdapter.MyHoder holder, int position) {
        final MateriModel myMateriList = listMateri.get(position);

        Log.e("TAG TES", Integer.toString(myMateriList.getJumlah_materi()));
        int minggu = position+1;
        holder.mingguKe.setText("Materi Minggu "+ minggu);

        jumlahMateri = myMateriList.getJumlah_materi();
        for(int i=0;i<jumlahMateri;i++){
            if(i==0){
                holder.materi1.setText(myMateriList.getNama_materi1());
            }else if(i==1){
                holder.materi2.setVisibility(View.VISIBLE);
                holder.materi2.setText(myMateriList.getNama_materi2());
            }else if(i==2){
                holder.materi3.setVisibility(View.VISIBLE);
                holder.materi3.setText(myMateriList.getNama_materi3());
            }else if(i==3){
                holder.materi4.setVisibility(View.VISIBLE);
                holder.materi4.setText(myMateriList.getNama_materi4());
            }
        }

        setMateriLink(holder,myMateriList);
    }

    private void setMateriLink(AllMateriAdapter.MyHoder holder,MateriModel model){
        if(jumlahMateri==1){
            setMateri1Link(holder,model);
        }else if(jumlahMateri==2){
            setMateri1Link(holder,model);
            setMateri2Link(holder,model);
        }else if(jumlahMateri==3){
            setMateri1Link(holder,model);
            setMateri2Link(holder,model);
            setMateri3Link(holder,model);
        }else if(jumlahMateri==4){
            setMateri1Link(holder,model);
            setMateri2Link(holder,model);
            setMateri3Link(holder,model);
            setMateri4Link(holder,model);
        }
    }

    private String getFileLocation(){
        String fileLocation = Environment.getExternalStorageDirectory()
                + "/DigitalLibrary/"+new SessionController(context).getFakultas()+"/"+kodeMatkul+"/";
        return fileLocation;
    }

    public boolean checkIfFileExist(String fileLocation){
        File file = new File(fileLocation);
        String loc = file.getAbsolutePath();
        Log.d("CHECK WOI",loc);
        if(file.exists()){
            Log.d(TAG,"File Exist");
            return true;
        }else{
            Log.d(TAG,"File not exist");
            return false;
        }
    }

    private void openSavedFile(String fileLocation){
        File file = new File(fileLocation);

        MimeTypeMap myMime = MimeTypeMap.getSingleton();
        Intent newIntent = new Intent(Intent.ACTION_VIEW);
        String mimeType = myMime.getMimeTypeFromExtension(fileExt(fileLocation).substring(1));
        newIntent.setDataAndType(Uri.fromFile(file),mimeType);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(newIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No handler for this type of file.", Toast.LENGTH_LONG).show();
        }
    }

    private String fileExt(String url) {
        if (url.indexOf("?") > -1) {
            url = url.substring(0, url.indexOf("?"));
        }
        if (url.lastIndexOf(".") == -1) {
            return null;
        } else {
            String ext = url.substring(url.lastIndexOf(".") + 1);
            if (ext.indexOf("%") > -1) {
                ext = ext.substring(0, ext.indexOf("%"));
            }
            if (ext.indexOf("/") > -1) {
                ext = ext.substring(0, ext.indexOf("/"));
            }
            return ext.toLowerCase();

        }
    }

    private void setMateri1Link(AllMateriAdapter.MyHoder holder,final MateriModel model){
        holder.materi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfFileExist(getFileLocation()+model.getNama_materi1())){
                    openSavedFile(getFileLocation()+model.getNama_materi1());
                }else{
                    createDialog(model,1);
                }
            }
        });
    }

    private void setMateri2Link(AllMateriAdapter.MyHoder holder,final MateriModel model){
        holder.materi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfFileExist(getFileLocation()+model.getNama_materi2())){
                    openSavedFile(getFileLocation()+model.getNama_materi2());
                }else{
                    createDialog(model,2);
                }
            }
        });
    }

    private void setMateri3Link(AllMateriAdapter.MyHoder holder,final MateriModel model){
        holder.materi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfFileExist(getFileLocation()+model.getNama_materi3())){
                    openSavedFile(getFileLocation()+model.getNama_materi3());
                }else{
                    createDialog(model,3);
                }
            }
        });
    }

    private void setMateri4Link(AllMateriAdapter.MyHoder holder,final MateriModel model){
        holder.materi4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfFileExist(getFileLocation()+model.getNama_materi4())){
                    openSavedFile(getFileLocation()+model.getNama_materi4());
                }else{
                    createDialog(model,4);
                }
            }
        });
    }

    private void createDialog(final MateriModel model, final int nomorMateri){
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.MyDialogTheme);
        builder.setTitle("Download File Confirmation");
        builder.setMessage("Download File?");

        String positiveText = "Ya";
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"Downloading File",Toast.LENGTH_SHORT).show();
                        if(nomorMateri==1){
                            new DownloadFileFromURL(context,model.getNama_materi1(),fakultas,kodeMatkul).execute(model.getUrl_file1());
                        }else if(nomorMateri==2){
                            new DownloadFileFromURL(context,model.getNama_materi2(),fakultas,kodeMatkul).execute(model.getUrl_file2());
                        }else if(nomorMateri==3){
                            new DownloadFileFromURL(context,model.getNama_materi3(),fakultas,kodeMatkul).execute(model.getUrl_file3());
                        }else if(nomorMateri==4){
                            new DownloadFileFromURL(context,model.getNama_materi4(),fakultas,kodeMatkul).execute(model.getUrl_file4());
                        }
                        dialog.dismiss();
                    }
                });

        String negativeText = "Batal";
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return listMateri.size();
    }

    class MyHoder extends RecyclerView.ViewHolder {
        TextView mingguKe, materi1,materi2,materi3,materi4;
        CardView cardView;

        public MyHoder(View itemView) {
            super(itemView);
            mingguKe = (TextView)itemView.findViewById(R.id.textViewMingguCard);
            materi1 = (TextView)itemView.findViewById(R.id.textViewMateri1Card);
            materi2 = (TextView)itemView.findViewById(R.id.textViewMateri2Card);
            materi3 = (TextView)itemView.findViewById(R.id.textViewMateri3Card);
            materi4 = (TextView)itemView.findViewById(R.id.textViewMateri4Card);
            cardView = (CardView) itemView.findViewById(R.id.cardViewMateriKuliah);
        }
    }


}
