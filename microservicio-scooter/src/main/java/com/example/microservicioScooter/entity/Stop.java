package com.example.microservicioMonopatin.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private double latitud;
    private double longitud;

    @OneToMany(mappedBy = "parada")
    private List<Scooter> Scooters;



    public Parada() {
        super();
        this.Scooters = new ArrayList<>();
    }

    public Stop(String nombre, String direccion, Double latitud, Double longitud) {
        super();
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }
    public Long getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
    public Double getLatitud() {
        return latitud;
    }
    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public List<Scooter> getScooters() {
        return Scooters;
    }
}