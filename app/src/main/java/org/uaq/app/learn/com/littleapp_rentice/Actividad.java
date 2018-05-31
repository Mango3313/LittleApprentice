package org.uaq.app.learn.com.littleapp_rentice;

public class Actividad {
    private String nomActividad;
    private String nomRalizo;
    private int tipActividad;
    private int rateActividad;
    public Actividad(String nombre,String realizador,int tipo,int rate){
        this.nomActividad = nombre;
        this.nomRalizo = realizador;
        this.tipActividad = tipo;
        this.rateActividad = rate;
    }
    public String getActividad(){
        return nomActividad;
    }
    public String getRealizador(){
        return nomRalizo;
    }
    public int getTipoActividad(){
        return tipActividad;
    }
    public int getRateActividad(){
        return rateActividad;
    }
}
