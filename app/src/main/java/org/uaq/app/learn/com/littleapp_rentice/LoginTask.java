package org.uaq.app.learn.com.littleapp_rentice;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.Properties;

public class LoginTask extends AsyncTask<Properties,Void,String> {
    private AlertDialog dialog;
    private Context cont;
    public LoginTask(Context context){
        this.cont = context;
    }
    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(cont)
                .setTitle("Prueba")
                .setMessage("Espera...")
                .create();
        dialog.show();
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Properties... properties) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1800);
                }catch (Exception e){

                }
            }
        });
        t.run();
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        dialog.dismiss();
        if(s != null){
            if(s.equals("Ok")){

            }else{
                Toast.makeText(cont,s,Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(cont,"Se produjo un error, intentalo de nuevo",Toast.LENGTH_SHORT).show();
        }
        Intent intent  = new Intent(cont,MainTutor.class);
        cont.startActivity(intent);
        super.onPostExecute(s);
    }
}
