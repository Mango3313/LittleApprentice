package org.uaq.app.learn.com.littleapp_rentice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Speak extends AppCompatActivity implements View.OnClickListener {

    private int currentIndex = 0;
    private ImageView imageView;
    private TextView textView;
    private Button sigui,prev;
    private RepeatImage execises[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak);

        imageView = findViewById(R.id.imgDraw);
        textView = findViewById(R.id.imgDesc);
        sigui = findViewById(R.id.btnNext);
        prev = findViewById(R.id.btnPrev);
        sigui.setOnClickListener(this);
        prev.setOnClickListener(this);
        Resources res = getResources();
        try {
            execises = new RepeatImage[]{
                    new RepeatImage(res.getDrawable(R.drawable.aligator), "cocodrilo",0,0),
                    new RepeatImage(res.getDrawable(R.drawable.dragon),"dragon",0,0),
                    new RepeatImage(res.getDrawable(R.drawable.paint),"cuadro",0,0),
                    new RepeatImage(res.getDrawable(R.drawable.blocks),"bloques",1,0),
                    new RepeatImage(res.getDrawable(R.drawable.cloudy),"nublado",1,0),
                    new RepeatImage(res.getDrawable(R.drawable.town),"pueblo",1,0)
            };
            setCurrentExer(execises[currentIndex]);
        }catch (Exception e){

        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnNext){
            if(currentIndex < execises.length-1){
                currentIndex++;
                setCurrentExer(execises[currentIndex]);
            }
        }else if(view.getId() == R.id.btnPrev){
            if(currentIndex > 0){
                currentIndex--;
                setCurrentExer(execises[currentIndex]);
            }
        }
    }

    public void setCurrentExer(RepeatImage repeatImage){
        String name = repeatImage.getText();
        String upperString = name.substring(0,1).toUpperCase() + name.substring(1);
        imageView.setImageDrawable(repeatImage.getDrawable());
        textView.setText(upperString);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Speak.this)
                .setTitle("¿Salir?")
                .setMessage("Todos los datos se perderán")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        Dialog dialog = builder.create();
        dialog.show();
    }
}
