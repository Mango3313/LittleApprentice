package org.uaq.app.learn.com.littleapp_rentice;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainTutor extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseAuth mAuth;
    private TextView nombre,add;
    private String user,addres,sexo;
    private DatabaseReference rootRef;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseRecyclerAdapter adapter;
    private ArrayList<Posts> posts;
    private ActividadAdapter adapter1;
    private RecyclerView recyclerView;

    @Override
    protected void onStart() {
        super.onStart();/*
        mAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        user = currentUser.getDisplayName();
        addres = currentUser.getEmail();
        nombre.setText(user);
        add.setText(addres);
        */
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuth != null) {
            mAuth.removeAuthStateListener(authStateListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");
        setContentView(R.layout.activity_main_tutor);
        mAuth = FirebaseAuth.getInstance();
        //recyclerView = findViewById(R.id.recyclerView);
        fragmentManager = getSupportFragmentManager();
        Fragment sun = new SunsetFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.sunsetcontainer,sun);
        fragmentTransaction.commit();
        //posts = new ArrayList<>();
        //posts.add(new Posts("2222","2","2","2","2"));
        //for(Posts p:posts){
          //  Log.d("F",""+p.aciertos);
        //}
        /*adapter1 = new ActividadAdapter(posts);
        recyclerView.setAdapter(adapter1);
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if(currentUser != null){
                    Log.d("LOGS","Inicio sesion");
                }else{
                    Log.d("LOGS","No inicio sesion");
                }
            }
        };
        mAuth.addAuthStateListener(authStateListener);
        String uid = mAuth.getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("datos_users");
        DatabaseReference postsRef = rootRef.child(uid).child("posts");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String UUID = ds.child("UUID").getValue(String.class);
                    String aciertos = ds.child("aciertos").getValue(String.class);
                    String errores = ds.child("errores").getValue(String.class);
                    String fecha = ds.child("fecha").getValue(String.class);
                    String tiempo = ds.child("tiempo").getValue(String.class);
                    Posts p = new Posts(UUID,aciertos,errores,fecha,tiempo);
                    posts.add(p);
                }
                Log.d("SIZE",""+posts.size());
                adapter1 = new ActividadAdapter(posts);
                Log.d("SIZE",""+adapter1.getItemCount());
                recyclerView.setAdapter(adapter1 );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        recyclerView.setAdapter(adapter1);
        postsRef.addListenerForSingleValueEvent(eventListener);
        Log.d("NUMS",""+posts.size());
        */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hv = navigationView.getHeaderView(0);
        nombre = hv.findViewById(R.id.usrName);
        nombre.setText(mAuth.getCurrentUser().getDisplayName());
        add = hv.findViewById(R.id.usrAdd);
        add.setText(mAuth.getCurrentUser().getEmail());
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_tutor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            LayoutInflater inflater = getLayoutInflater();
            View web = inflater.inflate(R.layout.dialog_infoslegal,null);
            WebView webView = web.findViewById(R.id.webLegals);
            webView.loadUrl("file:///android_asset/flaticon.html");
            AlertDialog.Builder builder = new AlertDialog.Builder(MainTutor.this);
            builder.setTitle("Creditos")
                    .setView(web)
                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            Dialog dialog = builder.create();
            dialog.show();
            return true;
        }else if(id == R.id.licencia){
            LayoutInflater inflater = getLayoutInflater();
            View web = inflater.inflate(R.layout.dialog_infoslegal,null);
            WebView webView = web.findViewById(R.id.webLegals);
            webView.loadUrl("file:///android_asset/apachecommons.html");
            AlertDialog.Builder builder = new AlertDialog.Builder(MainTutor.this);
            builder.setTitle("Licencia")
                    .setView(web)
                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            Dialog dialog = builder.create();
            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction transaction =fragmentManager.beginTransaction();
        if (id == R.id.nav_juegos) {
            Intent gameIntent = new Intent(MainTutor.this,juegosactivity.class);
            startActivity(gameIntent);
        } else if (id == R.id.nav_perfil) {
        } else if (id == R.id.nav_comentarios) {
            try {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "gta321.jaff@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Comentarios sobre la app");
                startActivity(Intent.createChooser(emailIntent, null));
            } catch (Exception e) {

            }
        }else if(id == R.id.nav_logout){
            mAuth.signOut();
            finish();
        } else if (id == R.id.nav_info) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainTutor.this);
            builder.setTitle("Información")
                    .setMessage("Versión: 0.8.1 BETA \nAutores: Aplicaciones de bajo presupuesto")
                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            Dialog dialog = builder.create();
            dialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
class PostsHolder extends RecyclerView.ViewHolder{
    public TextView fecha;
    public TextView tiempo;
    public RatingBar aciertos;
    public RatingBar errores;

    public PostsHolder(View view){
        super(view);
        fecha = view.findViewById(R.id.card_date);
        tiempo = view.findViewById(R.id.card_tiempo);
        aciertos = view.findViewById(R.id.card_aciertos);
        errores = view.findViewById(R.id.card_errores);
    }
}