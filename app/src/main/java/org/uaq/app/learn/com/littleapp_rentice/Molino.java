package org.uaq.app.learn.com.littleapp_rentice;

import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;

public class Molino extends AppCompatActivity {

    private ImageView molino;
    private MediaRecorder mRecorder = null;
    private ProgressBar progressBar;
    private RotateAnimation anFast,anMid,anLow,anIni;
    boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_molino);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(32767);

        anIni = new RotateAnimation(0.0f,360.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        anIni.setRepeatCount(Animation.INFINITE);
        anIni.setDuration(800);
        anIni.setInterpolator(new LinearInterpolator());
        anFast = new RotateAnimation(0.0f,360.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        anFast.setDuration(400);
        anFast.setRepeatCount(Animation.INFINITE);
        anFast.setInterpolator(new LinearInterpolator());

        anMid = new RotateAnimation(0.0f,360.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        anMid.setDuration(800);
        anMid.setRepeatCount(Animation.INFINITE);
        anMid.setInterpolator(new LinearInterpolator());


        anLow = new RotateAnimation(0.0f,360.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        anLow.setDuration(900);
        anLow.setRepeatCount(Animation.INFINITE);
        anLow.setInterpolator(new LinearInterpolator());
        molino = findViewById(R.id.imageView4);
        molino.startAnimation(anIni);

        flag = true;
        final Handler handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                final Double val = bundle.getDouble("AMP",0.0d);
                Log.d("VAL",""+val);
                anIni.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        if(val < 100 && val >0){
                            molino.setAnimation(anLow);
                        }else if(val > 100 && val < 5000){
                            molino.setAnimation(anMid);
                        }else if(val > 20000){
                            molino.setAnimation(anFast);
                        }
                    }
                });
                anLow.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        if(val < 100 && val >0){
                            molino.setAnimation(anLow);
                        }else if(val > 100 && val < 5000){
                            molino.setAnimation(anMid);
                        }else if(val > 20000){
                            molino.setAnimation(anFast);
                        }
                    }
                });
                anMid.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        if(val < 100 && val >0){
                            molino.setAnimation(anLow);
                        }else if(val > 100 && val < 5000){
                            molino.setAnimation(anMid);
                        }else if(val > 20000){
                            molino.setAnimation(anFast);
                        }
                    }
                });
                anFast.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        if(val < 100 && val >0){
                            molino.setAnimation(anLow);
                        }else if(val > 100 && val < 5000){
                            molino.setAnimation(anMid);
                        }else if(val > 20000){
                            molino.setAnimation(anFast);
                        }
                    }
                });
                Log.d("LOG",""+val);
                progressBar.setProgress(val.intValue());
            }
        };
        try {
            start();
        }catch (Exception e){
            stop();
        }
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (flag) {
                        //bundle.putDouble("Amp", getAmplitude());
                        Message msj = handler.obtainMessage();
                        Bundle bundle = new Bundle();
                        bundle.putDouble("AMP",getAmplitude());
                        msj.setData(bundle);
                        handler.sendMessage(msj);
                        Thread.sleep(250);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        //Animation an1 = new RotateAnimation(0.0f,360.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        //an1.setDuration(0);
        //an1.setRepeatCount(Animation.INFINITE);
        //an1.setInterpolator(new LinearInterpolator());
        //molino.startAnimation(an1);
    }
    public void start() throws IOException {
        if (mRecorder == null) {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile("/dev/null");
            mRecorder.prepare();
            mRecorder.start();
        }
    }

    public void stop() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }

    public double getAmplitude() {
        if (mRecorder != null)
            return  mRecorder.getMaxAmplitude();
        else
            return 0;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        flag = false;
        stop();
    }
}
