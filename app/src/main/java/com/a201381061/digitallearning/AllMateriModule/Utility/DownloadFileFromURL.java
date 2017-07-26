package com.a201381061.digitallearning.AllMateriModule.Utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.a201381061.digitallearning.AllMateriModule.Activity.AllMateriActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by User on 7/15/2017.
 */

public class DownloadFileFromURL extends AsyncTask<String,String,String>{


    // Progress Dialog
    private ProgressDialog pDialog;

    // Progress dialog type (0 - for Horizontal progress bar)
    private static final int progress_bar_type = 0;

    private Context context;

    private String namaFile;

    private String fakultas;

    private String kodeMatkul;

    private String fileLocation;

    private static String TAG = "DownloadFileFromURL";

    public DownloadFileFromURL(Context context,String namaFile,String fakultas,String kodeMatkul) {
        this.context = context;
        this.fakultas = fakultas;
        this.kodeMatkul = kodeMatkul;
        this.namaFile = namaFile;
    }

    private void showDialog(int id){
        switch (id) {
            case progress_bar_type:
                pDialog = new ProgressDialog(context);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
        }
    }

    private void dismissDialog(){
        pDialog.dismiss();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        showDialog(progress_bar_type);
    }

    /**
     * Downloading file in background thread
     * */
    @Override
    protected String doInBackground(String... f_url) {
        int count;
        try {
            Log.d(TAG,"Download Started");

            URL url = new URL(f_url[0]);
            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            int lenghtOfFile = conection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            //check if folder already exist, if not, create
            String folder_main = "/DigitalLibrary/"+fakultas+"/"+kodeMatkul+"/";

            File folder = new File(Environment.getExternalStorageDirectory(), folder_main);
            if (!folder.exists()) {
                Log.d(TAG,"Creating Folder");
                folder.mkdirs();
            }else{
                Log.d(TAG,"Folder Exist");
            }

            fileLocation = folder_main + namaFile;

            // Output stream to write file
            OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory()
                    + fileLocation);

            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                // After this onProgressUpdate will be called
                publishProgress(""+(int)((total*100)/lenghtOfFile));

                // writing data to file
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return null;
    }

    /**
     * Updating progress bar
     * */
    protected void onProgressUpdate(String... progress) {
        // setting progress percentage
        pDialog.setProgress(Integer.parseInt(progress[0]));
    }

    /**
     * After completing background task
     * Dismiss the progress dialog
     * **/
    @Override
    protected void onPostExecute(String file_url) {
        // dismiss the dialog after the file was downloaded
        dismissDialog();

        Log.d(TAG,"Finish");

        ((AllMateriActivity)context).openDownloadedFile(fileLocation);
    }
}
