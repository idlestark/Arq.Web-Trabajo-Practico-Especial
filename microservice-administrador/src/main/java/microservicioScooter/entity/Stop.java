package com.example.microservicioMonopatin.entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    @OneToMany(mappedBy = "stop")
    private List<Scooter> scooters;

    public Stop() {
        super();
    }

    public Stop(String name, Double latitude, Double longitude) {
        super();
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.scooters = new ArrayList<>();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public List<Scooter> getScooters() { return scooters; }

}