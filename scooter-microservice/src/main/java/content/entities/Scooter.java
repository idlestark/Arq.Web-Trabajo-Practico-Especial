package content.entities;
import jakarta.persistence.*;

@Entity
public class Scooter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double battery;
    private boolean available;
    private boolean underMaintenance;
    private double latitude;
    private double longitude;
    private double kilometers;
    private double timeUsed;
    private double baseFee;
    private double extraFeePause;
    @ManyToOne
    @JoinColumn(name = "stop_id", nullable = false)
    private Stop stop;


    public Scooter(double battery, double latitude, double longitude,double kilometers, double timeUsed, Stop stop, double baseFee, double extraFeePause) {
        this.battery = battery;
        this.latitude = latitude;
        this.longitude = longitude;
        this.underMaintenance = false;
        this.available = true;
        this.kilometers = kilometers;
        this.timeUsed = timeUsed;
        this.stop = stop;
        this.baseFee = baseFee;
        this.extraFeePause = extraFeePause;
    }

    public Scooter() {
        super();
    }

    //GETTERS
    public Long getId() { return id; }

    public Stop getStop() { return stop; }

    public double getTimeUsed() { return timeUsed; }

    public double getKilometers() { return kilometers; }

    public double getLongitude() { return longitude; }

    public double getLatitude() { return latitude; }

    public boolean isUnderMaintenance() { return underMaintenance; }

    public boolean isAvailable() { return available; }

    public double getBattery() { return battery; }

    public double getBaseFee() { return baseFee; }

    public double getExtraFeePause() { return extraFeePause; }

    //SETTERS
    public void setId(Long id) { this.id = id; }

    public void setBattery(double battery) { this.battery = battery; }

    public void setAvailability(boolean available) { this.available = available; }

    public void setUnderMaintenance(boolean underMaintenance) { this.underMaintenance = underMaintenance; }

    public void setLatitude(double latitude) { this.latitude = latitude; }

    public void setKilometers(double kilometers) { this.kilometers = kilometers; }

    public void setLongitude(double longitude) { this.longitude = longitude; }

    public void setTimeUsed(double timeUsed) { this.timeUsed = timeUsed; }

    public void setStop(Stop stop) { this.stop = stop; }

    public void setBaseFee(double baseFee) { this.baseFee = baseFee; }

    public void setExtraFeePause(double extraFeePause) { this.extraFeePause = extraFeePause; }
}
