package com.cine.model.domain;

public class Pelicula {
    private int codigo;
    private String nombre;
    private String url;
    private String genero;
    private byte[] imagen;

    public Pelicula(int codigo, String nombre, String url, String genero, byte[] imagen) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.url = url;
        this.genero = genero;
        this.imagen = imagen;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public byte[] getImagen() {
        return imagen;
    } 

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
