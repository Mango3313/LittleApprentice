package org.uaq.app.learn.com.littleapp_rentice;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Words extends AppCompatActivity implements View.OnClickListener {
    private RepeatImage[] execises;
    private char[] chars;
    private char[] chDif;
    private int[] pchar;
    private ImageView curr;
    private Button btnpr,btnnex;
    private TextView tC1,tC2,tC3,tC4,tC5,tC6;
    private TextView tA1,tA2,tA3,tA4,tA5,tA6;
    private char[] ans;
    private int[] anChV;
    private int currentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        Resources res = getResources();
        execises = new RepeatImage[]{
                new RepeatImage(res.getDrawable(R.drawable.dragon),"dragón",0,0),
                new RepeatImage(res.getDrawable(R.drawable.paint),"cuadro",0,0),
                new RepeatImage(res.getDrawable(R.drawable.town),"pueblo",1,0)
        };
        CharView charView[] = new CharView[9];
        anChV = new int[3];
        curr = findViewById(R.id.imageView5);
        btnnex = findViewById(R.id.btnNextW);
        btnnex.setOnClickListener(this);
        btnpr = findViewById(R.id.btnPrevW);
        btnpr.setOnClickListener(this);
        tC1 = findViewById(R.id.textWC1);
        tC2 = findViewById(R.id.textWC2);
        tC3 = findViewById(R.id.textWC3);
        tC4 = findViewById(R.id.textWC4);
        tC5 = findViewById(R.id.textWC5);
        tC6 = findViewById(R.id.textWC6);
        tA1 = findViewById(R.id.textAn1);
        tA1.setOnClickListener(this);
        tA2 = findViewById(R.id.textAn2);
        tA2.setOnClickListener(this);
        tA3 = findViewById(R.id.textAn3);
        tA3.setOnClickListener(this);
        tA4 = findViewById(R.id.textAn4);
        tA4.setOnClickListener(this);
        tA5 = findViewById(R.id.textAn5);
        tA5.setOnClickListener(this);
        tA6 = findViewById(R.id.textAn6);
        tA6.setOnClickListener(this);
        init();
        String word = execises[currentIndex].getText();
        chars = getChars(word);
        pchar = getPositions();
        changePositions(chars,pchar);
        changeAnswers();
        ans = answerChars();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnPrevW){
            if(currentIndex>0){
                currentIndex--;
                updateImage();
                changeAnswers();
                ans = answerChars();
            }
        }else if(id == R.id.btnNextW){
            if(currentIndex<execises.length-1){
                currentIndex++;
                updateImage();
                changeAnswers();
                ans = answerChars();
            }
        }else if(id == R.id.textAn1){
            TextView one = findViewById(R.id.textAn1);
            if(tA1.getText().charAt(0) == ans[3]){
               getViewId(anChV[0],one.getText().toString());
            }
            else if(tA1.getText().charAt(0) == ans[4]){
                getViewId(anChV[1],one.getText().toString());
            }
            else if(tA1.getText().charAt(0) == ans[5]){
                getViewId(anChV[2],one.getText().toString());
            }
            else{
                tA1.setBackgroundColor(Color.RED);
            }
        }else if(id == R.id.textAn2){
            TextView one = findViewById(R.id.textAn2);
            if(tA2.getText().charAt(0) == ans[3]){
                getViewId(anChV[0],one.getText().toString());
            }
            else if(tA2.getText().charAt(0) == ans[4]){
                getViewId(anChV[1],one.getText().toString());
            }
            else if(tA2.getText().charAt(0) == ans[5]){
                getViewId(anChV[2],one.getText().toString());
            }
            else{
                tA2.setBackgroundColor(Color.RED);
            }
        }else if(id == R.id.textAn2){
            TextView one = findViewById(R.id.textAn3);
            Log.d("CHARAT",""+tA3.getText().charAt(0));
            Log.d("CHARAT",""+ans[0]);
            if(tA3.getText().charAt(0) == ans[3]){
                getViewId(anChV[0],one.getText().toString());
            }
            else if(tA3.getText().charAt(0) == ans[4]){
                getViewId(anChV[1],one.getText().toString());

            }
            else if(tA3.getText().charAt(0) == ans[5]){
                getViewId(anChV[2],one.getText().toString());
            }
            else{
                tA3.setBackgroundColor(Color.RED);
            }
        }else if(id == R.id.textAn4){
            TextView one = findViewById(R.id.textAn4);
            Log.d("CHARAT",""+tA3.getText().charAt(0));
            Log.d("CHARAT",""+ans[0]);
            if(tA4.getText().charAt(0) == ans[3]){
                getViewId(anChV[0],one.getText().toString());
            }
            else if(tA4.getText().charAt(0) == ans[4]){
                getViewId(anChV[1],one.getText().toString());
            }
            else if(tA4.getText().charAt(0) == ans[5]){
                getViewId(anChV[2],one.getText().toString());
            }
            else{
                tA4.setBackgroundColor(Color.RED);
            }
        }else if(id == R.id.textAn5){
            TextView one = findViewById(R.id.textAn5);
            Log.d("CHARAT",""+tA3.getText().charAt(0));
            Log.d("CHARAT",""+ans[1]);
            if(tA5.getText().charAt(0) == ans[3]){
                getViewId(anChV[0],one.getText().toString());
            }
            else if(tA5.getText().charAt(0) == ans[4]){
                getViewId(anChV[1],one.getText().toString());
            }
            else if(tA5.getText().charAt(0) == ans[5]){
                getViewId(anChV[2],one.getText().toString());
            }
            else{
                tA5.setBackgroundColor(Color.RED);
            }
        }else if(id == R.id.textAn6){
            TextView one = findViewById(R.id.textAn6);
            Log.d("CHARAT",""+tA3.getText().charAt(0));
            Log.d("CHARAT",""+ans[2]);
            if(tA6.getText().charAt(0) == ans[3]){
                getViewId(anChV[0],one.getText().toString());
            }
            else if(tA6.getText().charAt(0) == ans[4]){
                getViewId(anChV[1],one.getText().toString());
            }
            else if(tA6.getText().charAt(0) == ans[5]){
                getViewId(anChV[2],one.getText().toString());
            }
            else{
                tA6.setBackgroundColor(Color.RED);
            }
        }
    }
    public void updateImage(){
        clear();
        String word = execises[currentIndex].getText();
        curr.setImageDrawable(execises[currentIndex].getDrawable());
        chars = getChars(word);
        pchar = getPositions();
        changePositions(chars,pchar);
    }
    public void init(){
        currentIndex = 0;
        curr.setImageDrawable(execises[currentIndex].getDrawable());
    }
    public void transition(View viewOne,View viewTwo){
        TranslateAnimation animation = new TranslateAnimation(0, viewTwo.getX()-viewOne.getX(),0 , viewTwo.getY()-viewOne.getY());
        animation.setRepeatMode(0);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        viewOne.startAnimation(animation);
    }
    public void clear(){
        tC1.setText("__");
        tC2.setText("__");
        tC3.setText("__");
        tC4.setText("__");
        tC5.setText("__");
        tC6.setText("__");
        tA1.setText("__");
        tA1.setBackgroundColor(Color.TRANSPARENT);
        tA2.setText("__");
        tA2.setBackgroundColor(Color.TRANSPARENT);
        tA3.setText("__");
        tA3.setBackgroundColor(Color.TRANSPARENT);
        tA4.setText("__");
        tA4.setBackgroundColor(Color.TRANSPARENT);
        tA5.setText("__");
        tA5.setBackgroundColor(Color.TRANSPARENT);
        tA6.setText("__");
        tA6.setBackgroundColor(Color.TRANSPARENT);
    }
    public char[] answerChars(){
        Random r = new Random();
        char[] thechosedones = new char[6];
        char[] dif = new char[3];
        int p =0;
        for(char k:chars){
            if(k == chars[pchar[0]] || k == chars[pchar[1]] || k == chars[pchar[2]]){

            }else{
                dif[p] = k;
                p++;
            }
        }
        String abc = "abcdefghijqlmnñopqrstuvwxyz";
        abc = abc.replaceAll(""+chars[0],"");
        abc = abc.replaceAll(""+chars[1],"");
        abc = abc.replaceAll(""+chars[2],"");
        abc = abc.replaceAll(""+chars[3],"");
        abc = abc.replaceAll(""+chars[4],"");
        abc = abc.replaceAll(""+chars[5],"");
        for(int i =0;i<3;i++){
            thechosedones[i] = abc.charAt(r.nextInt(abc.length()));
        }
        for(int j = 3;j<6;j++){
            thechosedones[j] = dif[j-3];
        }
        return thechosedones;
    }

    public char[] getChars(String word){
        char[] c = new char[6];
        for(int i =0;i<c.length;i++){
            c[i] = word.charAt(i);
        }
        return c;
    }
    public int[] getPositions(){
        int pos[] = new int[3];
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<5; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<3; i++) {
            pos[i] = list.get(i);
        }
        return  pos;
    }
    public void getViewId(int p,String texto){
        switch(p){
            case 0:
                tC1.setText(texto);
                break;
            case 1:
                tC2.setText(texto);
                break;
            case 2:
                tC3.setText(texto);
                break;
            case 3:
                tC4.setText(texto);
                break;
            case 4:
                tC5.setText(texto);
                break;
            case 5:
                tC6.setText(texto);
                break;
        }
    }
    public void changePositions(char[] word,int[] positions){
        for(int i:positions){
            switch (i){
                case 0:
                    tC1.setText(""+word[i]);
                    break;
                case 1:
                    tC2.setText(""+word[i]);
                    break;
                case 2:
                    tC3.setText(""+word[i]);
                    break;
                case 3:
                    tC4.setText(""+word[i]);
                    break;
                case 4:
                    tC5.setText(""+word[i]);
                    break;
                case 5:
                    tC6.setText(""+word[i]);
                    break;
            }
        }
    }
    public void changeAnswers(){
        char[] ca = answerChars();
        for(int i = 0;i<ca.length;i++){
            switch (i){
                case 0:
                    tA1.setText(""+ca[i]);
                    break;
                case 1:
                    tA2.setText(""+ca[i]);
                    break;
                case 2:
                    tA3.setText(""+ca[i]);
                    break;
                case 3:
                    tA4.setText(""+ca[i]);
                    break;
                case 4:
                    tA5.setText(""+ca[i]);
                    break;
                case 5:
                    tA6.setText(""+ca[i]);
                    break;
            }
        }
    }
}
