package org.uaq.app.learn.com.littleapp_rentice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class fragment_data extends Fragment {
    private FirebaseAuth mAut;
    private FirebaseUser currentUser;
    private FirebaseAuth.AuthStateListener authStateListener;
    @Override
    public void onStart() {
        super.onStart();
        mAut.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAut != null) {
            mAut.removeAuthStateListener(authStateListener);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAut = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(currentUser != null){
                }else{
                }
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data,container,false);
        //RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        final ArrayList<User> usersPost = new ArrayList<>();
        String uid = currentUser.getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference postsRef = rootRef.child("data_usr").child(uid).child("post");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String UUID = ds.child("UUID").getValue(String.class);
                    String aciertos = ds.child("aciertos").getValue(String.class);
                    String errores = ds.child("errores").getValue(String.class);
                    String tiempo = ds.child("tiempo").getValue(String.class);
                    String fecha = ds.child("fecha").getValue(String.class);
                    usersPost.add(new User(UUID,aciertos,errores,tiempo,fecha));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        postsRef.addListenerForSingleValueEvent(eventListener);
        //recyclerView.setAdapter(new ActividadAdapter(usersPost));
        return view;
    }
}
