package org.uaq.app.learn.com.littleapp_rentice;

public class User {
    public String aciertos;
    public String errores;
    public String tiempo;
    public String fecha;
    public String UUID;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String UUID, String aciertos, String errores, String tiempo, String fecha) {
        this.UUID = UUID;
        this.aciertos = aciertos;
        this.errores = errores;
        this.tiempo = tiempo;
        this.fecha = fecha;
    }
}