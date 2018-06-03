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
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class fragment_reg extends Fragment implements View.OnClickListener{
    private Button btnReg;
    private FirebaseAuth mAuth;
    private TextInputEditText edNom,edApell,edCorr,edContr,edRContr;
    private RadioGroup se;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.lay_regs,container,false);
        mAuth = FirebaseAuth.getInstance();
        btnReg = root.findViewById(R.id.button3);
        edNom = root.findViewById(R.id.editNombre);
        edApell = root.findViewById(R.id.editApellido);
        edCorr = root.findViewById(R.id.editCorreo);
        edContr = root.findViewById(R.id.editContra);
        edRContr = root.findViewById(R.id.editRContra);
        se = root.findViewById(R.id.radioGroup);
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
                    final ProgressDialog progressDialog = new ProgressDialog(getContext());
                    progressDialog.setTitle("Espera....");
                    progressDialog.setMessage("Validando usuario");
                    progressDialog.show();
                    if(cont.equals(rcont)){
                        progressDialog.dismiss();
                        mAuth.createUserWithEmailAndPassword(corr,cont).addOnCompleteListener(getActivity(),new OnCompleteListener<AuthResult>() {
                            @Override public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(edNom.getText().toString().trim()+" "+edApell.getText().toString().trim()).build();
                                    user.updateProfile(profileUpdates);
                                    mAuth.signOut();
                                    getFragmentManager().popBackStack();
                                } else {
                                    FirebaseAuthException e =(FirebaseAuthException) task.getException();
                                    Toast.makeText(getContext(), "Authentication failed:"+e.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else{
                        Toast.makeText(getContext(),"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
                    }
                }

            }else{
                Snackbar.make(getView(),"No tienes conexion a internet",Snackbar.LENGTH_LONG).show();
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
