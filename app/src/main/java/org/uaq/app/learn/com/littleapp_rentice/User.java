package org.uaq.app.learn.com.littleapp_rentice;

public class User {
    public String uid;
    public String sexo;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String UUID, String sexo) {
        this.uid = UUID;
        this.sexo = sexo;
    }
}
