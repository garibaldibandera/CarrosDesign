package com.example.carrosdesign;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Random;

public class AgregarCarro extends AppCompatActivity {
    private ArrayList<Integer> fotos;
    private EditText placa, marca, modelo, color, precio;
    private StorageReference storageReference;
    private Uri uri;
    private ImageView foto;
    private static DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

    private String error_placa;
    private String error_marca;
    private String error_modelo;
    private String error_modelo_ano;
    private String error_color;
    private String error_precio;

    Calendar cal= Calendar.getInstance();
    int year= cal.get(Calendar.YEAR);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_carro);

        error_placa=getResources().getString(R.string.error_placa);
        error_marca=getResources().getString(R.string.error_marca);
        error_modelo=getResources().getString(R.string.error_modelo);
        error_modelo_ano=getResources().getString(R.string.error_modelo_ano);
        error_color=getResources().getString(R.string.error_color);
        error_precio=getResources().getString(R.string.error_precio);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        placa=findViewById(R.id.txtPlaca);
        marca=findViewById(R.id.txtMarca);
        modelo=findViewById(R.id.txtModelo);
        color=findViewById(R.id.txtColor);
        precio=findViewById(R.id.txtPrecio);

        fotos=new ArrayList<>();
        fotos.add(R.drawable.carro1);
        fotos.add(R.drawable.carro2);
        fotos.add(R.drawable.carro3);
        fotos.add(R.drawable.carro4);
        fotos.add(R.drawable.carro5);
        fotos.add(R.drawable.carro6);
        fotos.add(R.drawable.carro7);

        storageReference= FirebaseStorage.getInstance().getReference();

     /*   placa.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Toast.makeText(AgregarCarro.this, "Focus Lose", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AgregarCarro.this, "Get Focus", Toast.LENGTH_SHORT).show();
                }

            }
        });*/
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void guardar(View v){
        if(placa.getText().toString().isEmpty()){
            placa.setError(error_placa);
        }else {
            validarplaca(placa.getText().toString());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean validarplaca(String toString) {

        databaseReference.child("Carros").orderByChild("placa").equalTo(toString).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Map of all products which has city equal to mCurrentUserCity
                        Carro carro = dataSnapshot.getValue(Carro.class);
                        if (carro == null) {
                            if(marca.getText().toString().isEmpty()){
                                marca.setError(error_marca);
                            }else if(modelo.getText().toString().isEmpty()){
                                modelo.setError(error_modelo);
                            }else if(Double.parseDouble(modelo.getText().toString())> year+1){
                                modelo.setError(error_modelo_ano);
                            }else if(color.getText().toString().isEmpty()) {
                                color.setError(error_color);
                            }else if(precio.getText().toString().isEmpty()) {
                                precio.setError(error_precio);
                            }else {
                                String id = Datos.getId();
                                carro = new Carro(placa.getText().toString(), marca.getText().toString(), modelo.getText().toString(), color.getText().toString(), precio.getText().toString(), foto_aleatoria(), id);
                                carro.guardar();
                                subir_foto(id, foto_aleatoria());
                                limpiar();
                                hideKeyboard(AgregarCarro.this);
                                Toast.makeText(AgregarCarro.this, getString(R.string.mensaje_guardado_correcto), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(AgregarCarro.this, getString(R.string.error_placa_existente), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        return false;
    }

    public void subir_foto (String id, int foto){
        StorageReference child = storageReference.child(id);
        Uri uri = Uri.parse("android.resourse://"+R.class.getPackage().getName()+"/"+foto);
        UploadTask uploadTask = child.putFile(uri);
    }

    public void limpiar(View v){
        limpiar();
    }

    public int foto_aleatoria(){
        int foto_seleccionada;
        Random r =new Random();
        foto_seleccionada = r.nextInt(this.fotos.size());
        return fotos.get(foto_seleccionada);
    }
    public void limpiar(){
        placa.setText("");
        marca.setText("");
        modelo.setText("");
        color.setText("");
        precio.setText("");
        placa.requestFocus();
    }

    public void onBackPressed(){
        Intent i= new Intent(AgregarCarro.this, MainActivity.class);
        startActivity(i);
    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
 }