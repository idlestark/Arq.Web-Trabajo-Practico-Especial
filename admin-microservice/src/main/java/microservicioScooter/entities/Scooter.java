package com.example.microservicioMonopatin.entity;
import jakarta.persistence.*;

@Entity
public class Scooter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double battery;
    private boolean availeable;
    private boolean underMaintenance;
    private double latitude;
    private double longitude;
    private double kilometers;
    private double timeUsed;
    @ManyToOne
    @JoinColumn(name = "stopId", nullable = false)
    private Stop stop;

    public Scooter(Stop stop, double timeUsed, double kilometers, double longitude, double latitude, boolean underMaintenance, boolean availeable, double battery, Long id) {
        this.stop = stop;
        this.timeUsed = timeUsed;
        this.kilometers = kilometers;
        this.longitude = longitude;
        this.latitude = latitude;
        this.underMaintenance = underMaintenance;
        this.availeable = availeable;
        this.battery = battery;
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public double getBattery() {
        return battery;
    }
    public boolean isAvaileable() {
        return availeable;
    }
    public boolean isUnderMaintenance() {
        return underMaintenance;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public double getKilometers() {
        return kilometers;
    }
    public double getTimeUsed() {
        return timeUsed;
    }
    public Stop getStop() {
        return stop;
    }
    public Scooter() {
        super();
    }

}