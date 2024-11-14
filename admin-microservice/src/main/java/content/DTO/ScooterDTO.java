package content.DTO;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ScooterDTO {

    private Long id;

    private double battery;
    private boolean available;
    private boolean inMaintenance;
    private double latitude;
    private double longitude;
    private double kilometersTraveled;
    private double timeUse;

    @ManyToOne
    @JoinColumn(name = "stop_id", nullable = false)
    private StopDTO stop;


    public ScooterDTO() {
        super();
    }

    public ScooterDTO(Long id, double battery, boolean available, boolean inMaintenance, double latitude, double longitude, double timeUse, double kilometersTraveled, StopDTO stop) {
        this.id = id;
        this.battery = battery;
        this.available = available;
        this.inMaintenance = inMaintenance;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeUse = timeUse;
        this.kilometersTraveled = kilometersTraveled;
        this.stop = stop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBattery() {
        return battery;
    }

    public void setBattery(double battery) {
        this.battery = battery;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isInMaintenance() {
        return inMaintenance;
    }

    public void setInMaintenance(boolean inMaintenance) {
        this.inMaintenance = inMaintenance;
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

    public double getKilometersTraveled() {
        return kilometersTraveled;
    }

    public void setKilometersTraveled(double kilometersTraveled) {
        this.kilometersTraveled = kilometersTraveled;
    }

    public double getTimeUse() {
        return timeUse;
    }

    public void setTimeUse(double timeUse) {
        this.timeUse = timeUse;
    }

    public StopDTO getStop() {
        return stop;
    }

    public void setStop(StopDTO stop) {
        this.stop = stop;
    }
}
