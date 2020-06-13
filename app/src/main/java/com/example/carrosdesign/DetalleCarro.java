package com.example.carrosdesign;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetalleCarro extends AppCompatActivity {
    private Carro c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_carro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ImageView foto;
        TextView placa, marca, modelo, color, precio;
        Bundle bundle;
        Intent intent;
        String plac, marc, model, colo, preci, id;
        int fot;
        StorageReference storageReference;

        foto = findViewById(R.id.imgFotoDetalle);
        placa = findViewById(R.id.lblPlacaDetalle);
        marca = findViewById(R.id.lblMarcaDetalle);
        modelo = findViewById(R.id.lblModeloDetalle);
        color = findViewById(R.id.lblColorDetalle);
        precio = findViewById(R.id.lblPrecioDetalle);

        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        fot = bundle.getInt("foto");
        id = bundle.getString("id");
        plac = bundle.getString("placa");
        marc = bundle.getString("marca");
        model = bundle.getString("modelo");
        colo = bundle.getString("color");
        preci = bundle.getString("precio");

        storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child(id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(foto);
            }
        });
        foto.setImageResource(fot);
        placa.setText(plac);
        marca.setText(marc);
        modelo.setText(model);
        color.setText(colo);
        precio.setText(preci);

        c = new Carro(plac, marc, model, colo, preci, 0, id);
    }

    public void onBackPressed(){
       // finish();
        Intent intent = new Intent(DetalleCarro.this,MainActivity.class);
        startActivity(intent);
    }

    public void eliminar(View v){
        String positivo, negativo;
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo_eliminar_carro);
        builder.setMessage(R.string.pregunta_mensaje_eliminar);
        positivo=getString(R.string.opcion_si);
        negativo=getString(R.string.opcion_no);

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                c.eliminar();
                onBackPressed();
            }
        });

        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}