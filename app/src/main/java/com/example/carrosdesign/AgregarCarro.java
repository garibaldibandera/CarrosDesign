package com.example.carrosdesign;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.material.snackbar.Snackbar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_carro);

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
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void guardar(View v){
        String plac, marc, model, colo, preci, id;
        int foto;
        Carro carro;
        InputMethodManager imp=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (validar()) {
            plac = placa.getText().toString();
            marc = marca.getText().toString();
            model = modelo.getText().toString();
            colo = color.getText().toString();
            preci = precio.getText().toString();
            foto = foto_aleatoria();
            id = Datos.getId();
            carro = new Carro(plac, marc, model, colo, preci, foto, id);
            carro.guardar();
            subir_foto(id, foto);
            limpiar();
            imp.hideSoftInputFromWindow(placa.getWindowToken(), 0);
            Snackbar.make(v, getString(R.string.mensaje_guardado_correcto), Snackbar.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean validar(){
        String error_placa, error_marca, error_modelo, error_modelo_ano, error_color, error_precio;
        error_placa=getResources().getString(R.string.error_placa);
        error_marca=getResources().getString(R.string.error_marca);
        error_modelo=getResources().getString(R.string.error_modelo);
        error_modelo_ano=getResources().getString(R.string.error_modelo_ano);
        error_color=getResources().getString(R.string.error_color);
        error_precio=getResources().getString(R.string.error_precio);
        Calendar cal= Calendar.getInstance();
        int year= cal.get(Calendar.YEAR);

        if(placa.getText().toString().isEmpty()){
            placa.setError(error_placa);
            return false;
        }else if(marca.getText().toString().isEmpty()){
            marca.setError(error_marca);
            return false;
        }else if(modelo.getText().toString().isEmpty()){
            modelo.setError(error_modelo);
            return false;
        }else if(Double.parseDouble(modelo.getText().toString())> year+1){
            modelo.setError(error_modelo_ano);
            return false;
        }else if(color.getText().toString().isEmpty()) {
            color.setError(error_color);
            return false;
        }else if(precio.getText().toString().isEmpty()) {
            precio.setError(error_precio);
            return false;
        }
        return true;
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
        finish();
        Intent i= new Intent(AgregarCarro.this, MainActivity.class);
        startActivity(i);
    }
 }