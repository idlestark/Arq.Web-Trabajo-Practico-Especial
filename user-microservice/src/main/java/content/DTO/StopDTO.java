package content.DTO;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class StopDTO {

    private String name;
    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "stops")
    private List<ScooterDTO> Scooters;


    public StopDTO() {
        super();
        this.Scooters = new ArrayList<>();
    }

    public StopDTO(String name, Double latitude, Double longitude) {
        super();
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<ScooterDTO> getScooters() {
        return Scooters;
    }

    public void setScooters(List<ScooterDTO> scooters) {
        Scooters = scooters;
    }
}
