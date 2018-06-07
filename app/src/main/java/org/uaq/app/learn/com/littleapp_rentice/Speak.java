package org.uaq.app.learn.com.littleapp_rentice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class Speak extends AppCompatActivity implements View.OnClickListener {

    private int currentIndex = 0;
    private ImageView imageView;
    private TextView textView;
    private Button sigui,prev,speak;
    private RelativeLayout play;
    private int aciertos=0,errores=0;
    private Timer timer;
    private RepeatImage execises[];
    private ArrayList res;
    private String TAG = "LISTEN_RESULTS";
    private SpeechRecognizer sr;
    public int seconds = 00;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference ref;
    private MediaPlayer player;

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference();
    }
    public void writeData(String UUID,String aciertos,String errores,String tiempo,String fecha){
        User user = new User(UUID,aciertos,errores,tiempo,fecha);
        ref.child("datos_users")
                .child(currentUser.getUid()).child("posts").child(UUID).setValue(user)
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"La aplicacion se ha registrado con exito",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Intentalo de nuevo",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak);
        final Handler handler = new Handler();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seconds+=1;
            }
        }, 0, 1000);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        res = new ArrayList<>();
        imageView = findViewById(R.id.imgDraw);
        textView = findViewById(R.id.imgDesc);
        sigui = findViewById(R.id.btnNext);
        speak = findViewById(R.id.textResult);
        play = findViewById(R.id.btnPlay);

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
                LayoutInflater inflater = getLayoutInflater();
                View toV = inflater.inflate(R.layout.toast_great,null);
                String str = new String();
                ArrayList data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                for (int i = 0; i < data.size(); i++)
                {
                    str += data.get(i);
                }
                if( data.get(0).equals(execises[currentIndex].getText())){
                    aciertos++;
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM,0,0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(toV);
                    toast.show();
                }else{
                    errores++;
                }
                if(currentIndex < execises.length-1){
                    currentIndex++;
                    setCurrentExer(execises[currentIndex]);
                }else{
                    try {
                        timer.cancel();
                        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        Date today = Calendar.getInstance().getTime();
                        String todayFor =  format.format(today);
                        writeData(UUID.randomUUID().toString(), "" + aciertos, "" + errores, "" + seconds, todayFor);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                /**
                if(currentIndex == execises.length-1){
                    try {
                        timer.cancel();
                        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        Date today = Calendar.getInstance().getTime();
                        String todayFor =  format.format(today);
                        writeData(UUID.randomUUID().toString(), "" + aciertos, "" + errores, "" + seconds, todayFor);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    if( data.get(0).equals(execises[currentIndex].getText())){
                        aciertos++;
                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM,0,0);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(toV);
                        toast.show();
                    }else{
                        errores++;
                    }
                }
                 **/
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        speak.setOnClickListener(this);
        play.setOnClickListener(this);
        Resources res = getResources();
        try {
            execises = new RepeatImage[]{
                    new RepeatImage(res.getDrawable(R.drawable.aligator), "cocodrilo",R.raw.cocodrilo),
                    new RepeatImage(res.getDrawable(R.drawable.dragon),"dragón",R.raw.dragon),
                    new RepeatImage(res.getDrawable(R.drawable.paint),"cuadro",R.raw.cuadro),
                    new RepeatImage(res.getDrawable(R.drawable.blocks),"bloques",R.raw.bloques),
                    new RepeatImage(res.getDrawable(R.drawable.cloudy),"nublado",R.raw.nublado),
                    new RepeatImage(res.getDrawable(R.drawable.town),"pueblo",R.raw.pueblo)
            };
            setCurrentExer(execises[currentIndex]);
        }catch (Exception e){

        }
        player = MediaPlayer.create(getApplicationContext(),execises[currentIndex].getRaw());
    }

    @Override
    public void onClick(View view) {
        /**if(view.getId() == R.id.btnNext){
            if(currentIndex < execises.length-1){
                currentIndex++;
                setCurrentExer(execises[currentIndex]);
            }
        }else if(view.getId() == R.id.btnPrev){
            if(currentIndex > 0){
                currentIndex--;
                setCurrentExer(execises[currentIndex]);
            }
        }else **/
        if(view.getId() == R.id.btnPlay){
            switch (execises[currentIndex].getRaw()){
                case R.raw.cocodrilo:
                    player = MediaPlayer.create(getApplicationContext(),R.raw.cocodrilo);
                    player.start();
                    break;
                case R.raw.cuadro:
                    player = MediaPlayer.create(getApplicationContext(),R.raw.cuadro);
                    player.start();
                    break;
                case R.raw.bloques:
                    player = MediaPlayer.create(getApplicationContext(),R.raw.bloques);
                    player.start();
                    break;
                case R.raw.dragon:
                    player = MediaPlayer.create(getApplicationContext(),R.raw.dragon);
                    player.start();
                    break;
                case R.raw.nublado:
                    player = MediaPlayer.create(getApplicationContext(),R.raw.nublado);
                    player.start();
                    break;
                case R.raw.pueblo:
                    player = MediaPlayer.create(getApplicationContext(),R.raw.pueblo);
                    player.start();
                    break;
            }
        }else if(view.getId() == R.id.textResult){
            //TextView tx = findViewById(R.id.showTime);
            //tx.setText("0:0");
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"voice.recognition.test");

            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,5);
            sr.startListening(intent);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sr.stopListening();
        sr.destroy();
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