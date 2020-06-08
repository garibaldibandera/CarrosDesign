package com.example.carrosdesign;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab;
        RecyclerView lstCarros;
        final ArrayList<Carro> carros;
        LinearLayoutManager llm;
        final AdaptadorCarro adapter;


        lstCarros=findViewById(R.id.lstCarro);
        carros =new ArrayList<>();
        llm=new LinearLayoutManager(this);
        adapter= new AdaptadorCarro(carros, this);

        llm.setOrientation(RecyclerView.VERTICAL);
        lstCarros.setLayoutManager(llm);
        lstCarros.setAdapter(adapter);

        fab =findViewById(R.id.btnAgregar);
        //fab.setOnClickListener(this);
        //fab = findViewById(R.id.btnAgregar);

    }
    public void agregar (View v){
        Intent i;
        i=new Intent (MainActivity.this,AgregarCarro.class);
        startActivity(i);
        finish();

    }
}
