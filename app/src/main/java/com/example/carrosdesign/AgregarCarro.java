package com.example.carrosdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.storage.StorageReference;

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

        placa=findViewById(R.id.txtPlaca);
        marca=findViewById(R.id.txtMarca);
        modelo=findViewById(R.id.txtModelo);
        color=findViewById(R.id.txtColor);
        precio=findViewById(R.id.txtPrecio);

        //foto= findViewById(R.id.imgFotoSeleccionada);
        fotos=new ArrayList<>();
        fotos.add(R.drawable.carro1);
        fotos.add(R.drawable.carro2);
        fotos.add(R.drawable.carro3);
        fotos.add(R.drawable.carro4);
        fotos.add(R.drawable.carro5);
        fotos.add(R.drawable.carro6);
        fotos.add(R.drawable.carro7);

    }

    public void guardar(View v){
        String plac, marc, model, colo, preci, id;
        int foto;
        Carro carro;

        plac=placa.getText().toString();
        marc=marca.getText().toString();
        model=modelo.getText().toString();
        colo=color.getText().toString();
        preci=precio.getText().toString();
        foto = foto_aleatoria();
        id=Datos.getId();
        carro=new Carro(plac, marc, model, colo, preci, foto, id);
        carro.guardar();
    }
    public int foto_aleatoria(){
        int foto_seleccionada;
        Random r =new Random();
        foto_seleccionada = r.nextInt(this.fotos.size());
        return fotos.get(foto_seleccionada);
    }
}