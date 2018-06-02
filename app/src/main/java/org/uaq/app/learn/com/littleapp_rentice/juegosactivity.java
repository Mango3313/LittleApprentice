package org.uaq.app.learn.com.littleapp_rentice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class juegosactivity extends AppCompatActivity implements View.OnClickListener{

    private Button mol,speak,repeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegosactivity);
        setTitle("¡Vamos a jugar!");

        mol = findViewById(R.id.btnRehil);
        mol.setOnClickListener(this);
        speak = findViewById(R.id.btnSpeak);
        speak.setOnClickListener(this);
        repeat = findViewById(R.id.btnRepeat);
        repeat.setOnClickListener(this);

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
            case R.id.btnRepeat:
                break;
        }
    }
}
