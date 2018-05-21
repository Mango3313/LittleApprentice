package org.uaq.app.learn.com.littleapp_rentice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Properties;

public class fragment_login extends Fragment {
    private TextView regClic;
    private Button btnLogIn;
    private Context context;
    private Properties props;
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
        View root = inflater.inflate(R.layout.lay_login,container,false);
        regClic = (TextView)root.findViewById(R.id.textView5);
        btnLogIn = (Button) root.findViewById(R.id.button);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoginTask(getContext()).execute();
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
}
