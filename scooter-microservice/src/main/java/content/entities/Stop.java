package content.entities;
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
    private List<Scooter> scooterList;

    public Stop() {
        super();
        this.scooterList = new ArrayList<>();
    }

    public Stop(String name, double latitude, double longitude) {
        super();
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
