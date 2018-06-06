package org.uaq.app.learn.com.littleapp_rentice;

import android.view.View;

public class CharView {
    private View posView,ansView;
    private String value;
    private int posI,ansI;
    public CharView(String val,int posIni,int posAns,View ansView,View posView){
        this.value = val;
        this.posI = posIni;
        this.ansI = posAns;
        this.posView = posView;
        this.ansView = ansView;
    }

    public void setAnsI(int ansI) {
        this.ansI = ansI;
    }

    public void setAnsView(View ansView) {
        this.ansView = ansView;
    }

    public void setPosI(int posI) {
        this.posI = posI;
    }

    public void setPosView(int pos) {
        this.posView = posView;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getAnsI() {
        return ansI;
    }

    public View getAnsView() {
        return ansView;
    }

    public int getPosI() {
        return posI;
    }

    public View getPosView() {
        return posView;
    }
}
