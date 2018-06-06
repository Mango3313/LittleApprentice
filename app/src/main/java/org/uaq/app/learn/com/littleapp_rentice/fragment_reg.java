package org.uaq.app.learn.com.littleapp_rentice;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class fragment_reg extends Fragment implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener{
    private Button btnReg;
    private FirebaseAuth mAuth;
    private TextInputEditText edNom,edApell,edCorr,edContr,edRContr;
    private GoogleApiClient googleClient;
    private DatabaseReference reference;
    public static final String TAG_DATA = "nom";
    private boolean registry = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        reference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }
    public void writeSexo(String UID,String sexo){
        /**User user = new User(UID,sexo);
        /reference.child("datos_usr").child(UID).setValue(user).addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Log.d("BD","Success");
                }else{
                    Log.d("BD","Error"+task.getException());
                }
            }
        });
         **/
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.lay_regs,container,false);
        btnReg = root.findViewById(R.id.button3);
        edNom = root.findViewById(R.id.editNombre);
        edApell = root.findViewById(R.id.editApellido);
        edCorr = root.findViewById(R.id.editCorreo);
        edContr = root.findViewById(R.id.editContra);
        edRContr = root.findViewById(R.id.editRContra);
        btnReg.setOnClickListener(this);
        return root;
    }
    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.button3){
            if(isOnNetwork()){
                String nom,apell,corr,cont,rcont;
                nom = edNom.getText().toString().trim();
                apell = edApell.getText().toString().trim();
                corr = edCorr.getText().toString().trim();
                cont = edContr.getText().toString().trim();
                rcont = edRContr.getText().toString().trim();
                Log.d("EMAIL","daft");
                if(nom.isEmpty() || apell.isEmpty() || corr.isEmpty() || cont.isEmpty() || rcont.isEmpty()){
                    Toast.makeText(getContext(),"Varios campos estan vacios",Toast.LENGTH_SHORT).show();
                }else{
                    btnReg.setEnabled(false);
                    final ProgressDialog progressDialog = new ProgressDialog(getContext());
                    progressDialog.setTitle("Espera....");
                    progressDialog.setMessage("Registrando usuario");
                    progressDialog.show();
                    if(cont.equals(rcont)){
                        progressDialog.dismiss();
                        mAuth.createUserWithEmailAndPassword(corr,cont).addOnCompleteListener(getActivity(),new OnCompleteListener<AuthResult>() {
                            @Override public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    final FirebaseUser user = mAuth.getCurrentUser();
                                    user.sendEmailVerification()
                                            .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(getContext(),"Email the verificacion " +
                                                                "enviado a "+user.getEmail(),Toast.LENGTH_SHORT).show();
                                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                              .setDisplayName(edNom.getText().toString().trim()+" "+edApell.getText().toString().trim()).build();
                                                        user.updateProfile(profileUpdates);
                                                        getFragmentManager().popBackStack();
                                                    }else{
                                                        FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                                        Toast.makeText(getContext(),"Error:  " +
                                                                e.getMessage(),Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                } else {
                                    btnReg.setEnabled(true);
                                    FirebaseAuthException e =(FirebaseAuthException) task.getException();
                                    Toast.makeText(getContext(), "Authentication failed:"+e.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else{
                        Toast.makeText(getContext(),"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
                        btnReg.setEnabled(true);
                    }
                }

            }else{
                Snackbar.make(getView(),"No tienes conexion a internet",Snackbar.LENGTH_LONG).show();
                btnReg.setEnabled(true);
            }
        }
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
