package com.example.carrosdesign;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Datos {
    private static String db ="Carros";
    private static DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    private static StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private static ArrayList<Carro> carros=new ArrayList();

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void guardar(Carro p){
        //personas.add(p);
        databaseReference.child(db).child(p.getId()).setValue(p);
    }
    public static ArrayList<Carro> obtener(){
        return carros;
    }

    public static void setPersonas(ArrayList<Carro> carros){
        carros = carros;
    }

    public static void eliminar(Carro p){
        databaseReference.child(db).child(p.getId()).removeValue();
        storageReference.child(p.getId()).delete();
    }
}
