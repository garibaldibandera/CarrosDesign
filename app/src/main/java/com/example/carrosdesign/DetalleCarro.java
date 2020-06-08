package com.example.carrosdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class DetalleCarro extends AppCompatActivity {
    private Carro c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_carro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ImageView foto;
    }
}