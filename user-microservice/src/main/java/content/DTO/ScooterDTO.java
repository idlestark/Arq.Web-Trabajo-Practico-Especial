package content.DTO;
import content.DTO.StopDTO;
import jakarta.persistence.*;

public class ScooterDTO {

    private double battery;
    private boolean available;
    private boolean inMaintenance;
    private double latitude;
    private double longitude;
    private double kilometersTraveled;
    private double timeUse;

    @ManyToOne
    private StopDTO stop;


    public ScooterDTO() {
        super();
    }

    public ScooterDTO(Double latitude, Double longitude, Double battery, double kilometersTraveled, double timeUse, StopDTO stop) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.battery = battery;
        this.available = true;
        this.inMaintenance = false;
        this.kilometersTraveled = kilometersTraveled;
        this.timeUse = timeUse;
        this.stop = stop;
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