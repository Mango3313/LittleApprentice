package org.uaq.app.learn.com.littleapp_rentice;

import android.graphics.drawable.Drawable;

import java.util.List;

public class RepeatImage {
    private Drawable drawable;
    private String text;
    private int raw;
    public RepeatImage(Drawable dr,String drtext,int raw){
        this.drawable = dr;
        this.text = drtext;
        this.raw= raw;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public String getText() {
        return text;
    }

    public int getRaw() {
        return raw;
    }

}
