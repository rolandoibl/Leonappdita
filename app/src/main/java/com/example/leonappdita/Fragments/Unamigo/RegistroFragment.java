package com.example.leonappdita.Fragments.Unamigo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.leonappdita.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;


public class RegistroFragment extends Fragment {

    private static final int PICK_FOTO = 1;
    Button regBtnRegistrarse;
    Fragment fragmentSeleccionado;
    EditText regEdtCorreo;
    EditText regEdtCorreo2;
    EditText regEdtNombre;
    EditText regEdtCarrera;
    EditText regEdtNumero;
    Button regBtnElegirFoto;
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    private StorageReference mStorageRef;
    String nombre;
    String carrera;
    String numCuenta;
    String correo;
    String imagen="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_registro,container,false);
        regBtnRegistrarse = v.findViewById(R.id.regBtnRegistrarse);
        regEdtCorreo = v.findViewById(R.id.regEdtCorreo);
        regEdtCorreo2 = v.findViewById(R.id.regEdtCorreo2);
        regEdtNombre = v.findViewById(R.id.regEdtNombre);
        regEdtCarrera = v.findViewById(R.id.regEdtCarrera);
        regEdtNumero = v.findViewById(R.id.regEdtNumero);
        regBtnElegirFoto=v.findViewById(R.id.regBtnElegirFoto);
        regBtnElegirFoto.setOnClickListener(onClickAgregarImagen);
        db=FirebaseFirestore.getInstance();
        mStorageRef= FirebaseStorage.getInstance().getReference();

        //Inicializando Authentification
        mAuth = FirebaseAuth.getInstance();

        regBtnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSeleccionado = new UnamigoFragment();
                if(Verificacion()){
                    String tmp = regEdtCorreo.getText().toString();

                    //Este método verifica si ya existe un email registrado para no dejarle registrar de nuevo
                    mAuth.signInWithEmailAndPassword(tmp,"x6m8abh0192xc_a").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                if(task.getException() instanceof FirebaseAuthInvalidUserException){
                                    //Agrega el nuevo registro a la base de datos para ser revisada y regresa a UNAMigo
                                    AgregarRegistro();
                                }
                                else if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                                    Toast.makeText(getContext(),"Este correo ya se ha registrado antes",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getContext(),"Revisa tus datos",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;

    }

    private void AgregarRegistro() {
        nombre=regEdtNombre.getText().toString();
        carrera=regEdtCarrera.getText().toString();
        numCuenta=regEdtNumero.getText().toString();
        correo=regEdtCorreo.getText().toString();
        Solicitante solicitante=new Solicitante(nombre,carrera,numCuenta,correo,imagen);
        db.collection("Registros").document().set(solicitante).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragContentMain,fragmentSeleccionado).commit();
                Toast.makeText(getContext(),"Tu registro será verificado, en máximo 3 días en el correo proporcionado te haremos llegar tus datos de acceso",Toast.LENGTH_LONG).show();
            }
        });
    }

    //Aquí se verifican si los datos proporcionados para el registro no están vacíos y los correos sean iguales
    private boolean Verificacion(){
        if(regEdtNombre.getText().toString().equals("")||regEdtNumero.getText().toString().equals("")||regEdtCarrera.getText().toString().equals("")||regEdtCorreo.getText().toString().equals("")||regEdtCorreo2.getText().toString().equals("")){
            Toast.makeText(getContext(),"Algún campo está vacío",Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            if(regEdtCorreo.getText().toString().equals(regEdtCorreo2.getText().toString())){
                if(imagen.equals("")){
                    Toast.makeText(getContext(), "Falta subir una foto de tu credencial", Toast.LENGTH_SHORT).show();
                    return false;
                }
                else{
                    return true;
                }
            }
            else{
                Toast.makeText(getContext(),"Los correos no coinciden",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }
    View.OnClickListener onClickAgregarImagen= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AbrirGaleria();
        }
    };

    private void AbrirGaleria() {
        Intent intentGaleria=new Intent();
        intentGaleria.setType("image/*");
        intentGaleria.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intentGaleria,"Selecciona una imagen"),PICK_FOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_FOTO&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            Uri filePath=data.getData();
            final StorageReference filepathr=mStorageRef.child("registrosEntrantes").child(filePath.getLastPathSegment());
            filepathr.putFile(filePath).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw new Exception();
                    }
                    return filepathr.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getContext(), "Se subió correctamente el archivo", Toast.LENGTH_SHORT).show();
                        Uri downloadlink=task.getResult();
                        imagen = downloadlink.toString();
                    }
                }
            });
        }
    }
}
