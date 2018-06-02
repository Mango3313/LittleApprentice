package org.uaq.app.learn.com.littleapp_rentice;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Properties;

public class fragment_login extends Fragment {
    private TextView regClic;
    private Button btnLogIn;
    private TextInputEditText editUsuario, editContraseña;
    private Context context;
    private Properties props;
    private FirebaseAuth mAuth;
    @Override
    public void onAttach(Context context) {
        context = context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentManager fm = getFragmentManager();
        props = new Properties();
        mAuth = FirebaseAuth.getInstance();
        final View root = inflater.inflate(R.layout.lay_login,container,false);
        regClic = (TextView)root.findViewById(R.id.textView5);
        btnLogIn = (Button) root.findViewById(R.id.button);
        editContraseña = root.findViewById(R.id.editText2);
        editUsuario = root.findViewById(R.id.editText);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnNetwork()){
                    //new LoginTask(getContext()).execute();
                    String usuario = editUsuario.getText().toString();
                    String contraseña = editContraseña.getText().toString();
                    if(usuario!=null && contraseña!=null){
                    mAuth.signInWithEmailAndPassword(usuario,contraseña).addOnCompleteListener(getActivity(),new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(getActivity(),MainTutor.class);
                                startActivity(intent);
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });
                    }else{
                        Toast.makeText(getContext(),"Por favor llene los campos",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Snackbar.make(root,"No tienes conexion a internet",Snackbar.LENGTH_LONG).show();
                }
            }
        });
        regClic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment frag = new fragment_reg();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction1 = fm.beginTransaction();
                transaction1.setCustomAnimations(R.animator.transaction_start_in,R.animator.transaction_start_out,R.animator.transaction_return_in,R.animator.transaction_return_out);
                transaction1.addToBackStack("login");
                transaction1.replace(R.id.logcontainer,frag,"reg");
                transaction1.commit();

            }
        });
        return root;
    }
    public boolean isOnNetwork(){
        boolean status = false;
        try{
            ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(getContext().CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if(netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED){
                status = true;
            }else{
                netInfo = connectivityManager.getActiveNetworkInfo();
                if(netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED)
                    status = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            status = false;
        }
        return status;
    }
}
