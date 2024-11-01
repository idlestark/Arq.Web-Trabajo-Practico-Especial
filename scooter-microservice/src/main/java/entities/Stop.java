package entities;
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

    public Stop (Long id, String name, double latitude, double longitude, List<Scooter> scooters) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.scooters = new ArrayList<>();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public List<Scooter> getScooters() { return scooters; }

}