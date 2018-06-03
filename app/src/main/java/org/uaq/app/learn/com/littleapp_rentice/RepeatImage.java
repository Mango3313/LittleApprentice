package org.uaq.app.learn.com.littleapp_rentice;

import android.graphics.drawable.Drawable;

import java.util.List;

public class RepeatImage {
    private Drawable drawable;
    private String text;
    private int time;
    private int category;
    public RepeatImage(Drawable dr,String drtext,int cat,int ti){
        this.drawable = dr;
        this.text = drtext;
        this.category = cat;
        this.time = ti;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public String getText() {
        return text;
    }

    public int getCategory() {
        return category;
    }

    public int getTime() {
        return time;
    }
}
