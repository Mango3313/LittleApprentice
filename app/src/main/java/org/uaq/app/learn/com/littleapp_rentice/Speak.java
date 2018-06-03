package org.uaq.app.learn.com.littleapp_rentice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Speak extends AppCompatActivity implements View.OnClickListener {

    private int currentIndex = 0;
    private ImageView imageView;
    private TextView textView;
    private Button sigui,prev,speak;
    private ImageView play;
    private RepeatImage execises[];
    private Timer timer;
    private ArrayList res;
    private String TAG = "LISTEN_RESULTS";
    private SpeechRecognizer sr;
    public int seconds = 00;
    public int minutes = 00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak);

        res = new ArrayList<>();
        imageView = findViewById(R.id.imgDraw);
        textView = findViewById(R.id.imgDesc);
        sigui = findViewById(R.id.btnNext);
        prev = findViewById(R.id.btnPrev);
        play = findViewById(R.id.btnPlay);
        speak = findViewById(R.id.textResult);

        sr = SpeechRecognizer.createSpeechRecognizer(Speak.this);
        sr.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                Toast.makeText(getApplicationContext(),"DONE",Toast.LENGTH_SHORT).show();
                String str = new String();
                Log.d(TAG, "onResults " + bundle);
                ArrayList data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                for (int i = 0; i < data.size(); i++)
                {
                    Log.d(TAG, "result " + data.get(i));
                    str += data.get(i);
                }
                if( data.get(0).equals(execises[currentIndex].getText())){
                    Toast.makeText(getApplicationContext(),"CORRECTO",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        speak.setOnClickListener(this);
        sigui.setOnClickListener(this);
        prev.setOnClickListener(this);
        play.setOnClickListener(this);
        timer = new Timer();
        Resources res = getResources();
        try {
            execises = new RepeatImage[]{
                    new RepeatImage(res.getDrawable(R.drawable.aligator), "cocodrilo",0,0),
                    new RepeatImage(res.getDrawable(R.drawable.dragon),"dragón",0,0),
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
        }else if(view.getId() == R.id.btnPlay){
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView tx = findViewById(R.id.showTime);
                            tx.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
                            seconds += 1;

                            if(seconds == 60)
                            {
                                tx.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));

                                seconds=0;
                                minutes=minutes+1;

                            }
                        }
                    });
                }
            }, 0, 1000);
        }else if(view.getId() == R.id.textResult){
            timer.cancel();
            timer = new Timer();
            TextView tx = findViewById(R.id.showTime);
            tx.setText("0:0");
            seconds = 0;
            minutes = 0;

            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"voice.recognition.test");

            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,5);
            sr.startListening(intent);

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