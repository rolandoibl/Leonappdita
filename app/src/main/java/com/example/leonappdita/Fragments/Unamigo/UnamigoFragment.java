package com.example.leonappdita.Fragments.Unamigo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.leonappdita.MapsActivity;
import com.example.leonappdita.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;


public class UnamigoFragment extends Fragment {

    Button btnIngresarUA;
    Button btnRegistrarUA;
    Fragment fragmentSeleccionado;
    EditText edtCorreoUA;
    EditText edtPasswordUA;
    private FirebaseAuth mAuth;
    String correo;
    String password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_unamigo,container,false);
        btnIngresarUA = v.findViewById(R.id.btnIngresarUA);
        btnRegistrarUA = v.findViewById(R.id.btnRegistrarUA);
        edtCorreoUA = v.findViewById(R.id.edtCorreoUA);
        edtPasswordUA = v.findViewById(R.id.edtPasswordUA);
        //Inicializando Authentification
        mAuth = FirebaseAuth.getInstance();

        btnIngresarUA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Verificacion()){
                    correo = edtCorreoUA.getText().toString();
                    password = edtPasswordUA.getText().toString();
                    mAuth.signInWithEmailAndPassword(correo,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                //Email inexistente o desactivado o contraseña inválida
                                if(task.getException() instanceof FirebaseAuthInvalidUserException || task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                                    Toast.makeText(getContext(),"Correo/contraseña inválida",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Intent intent = new Intent(getContext(), MapsActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });

        btnRegistrarUA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSeleccionado = new RegistroFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragContentMain,fragmentSeleccionado).commit();
            }
        });
        return v;
    }

    private boolean Verificacion(){
        if(edtCorreoUA.getText().toString().equals("") || edtPasswordUA.getText().toString().equals("")){
            Toast.makeText(getContext(), "Algún campo está vacío", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
