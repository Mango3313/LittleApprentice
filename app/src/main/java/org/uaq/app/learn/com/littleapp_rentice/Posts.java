package org.uaq.app.learn.com.littleapp_rentice;

public class Posts {
    public String aciertos;
    public String errores;
    public String tiempo;
    public String fecha;
    public String UUID;
    public Posts(String UUID, String aciertos,String errores,String tiempo,String fecha) {
        this.UUID = UUID;
        this.aciertos = aciertos;
        this.errores = errores;
        this.tiempo = tiempo;
        this.fecha = fecha;
    }
    public String getAciertos() {
        return aciertos;
    }

    public String getErrores() {
        return errores;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTiempo() {
        return tiempo;
    }

    public String getUUID() {
        return UUID;
    }

    public void setAciertos(String aciertos) {
        this.aciertos = aciertos;
    }

    public void setErrores(String errores) {
        this.errores = errores;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}
