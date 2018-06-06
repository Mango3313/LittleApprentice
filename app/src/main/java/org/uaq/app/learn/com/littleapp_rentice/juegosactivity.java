package org.uaq.app.learn.com.littleapp_rentice;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class juegosactivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout mol,speak,words;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegosactivity);
        setTitle("¡Vamos a jugar!");

        mol = findViewById(R.id.btnRehil);
        mol.setOnClickListener(this);
        speak = findViewById(R.id.btnSpeak);
        speak.setOnClickListener(this);
        //words = findViewById(R.id.btnWords);
        //words.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(juegosactivity.this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnRehil:
                Intent startRehil = new Intent(juegosactivity.this,Molino.class);
                startActivity(startRehil);
                break;
            case R.id.btnSpeak:
                Intent startSpeak = new Intent(juegosactivity.this,Speak.class);
                startActivity(startSpeak);
                break;
            //case R.id.btnWords:
              //  Intent startWords = new Intent(juegosactivity.this,Words.class);
                //startActivity(startWords);
                //break;
        }
    }
}
