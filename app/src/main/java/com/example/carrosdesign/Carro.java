package com.example.carrosdesign;

public class Carro {
    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private String precio;
    private String id;
    private int foto;

    public Carro (String placa, String marca, String modelo, String color, String precio, int foto){
        this.placa=placa;
        this.marca=marca;
        this.modelo=modelo;
        this.color=color;
        this.precio=precio;
        this.foto = foto;
    }
    public Carro (){

    }

    public Carro (String placa, String marca, String modelo, String color, String precio, int foto, String id){
        this.placa=placa;
        this.marca=marca;
        this.modelo=modelo;
        this.color=color;
        this.precio=precio;
        this.foto=foto;
        this.id= id;
    }

    public String getPlaca() { return placa; }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public void guardar() {Datos.guardar(this);}

    public void eliminar(){
        Datos.eliminar(this);
    }

}
