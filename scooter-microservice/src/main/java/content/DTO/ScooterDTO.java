package content.DTO;

public class ScooterDTO {
    private Long stopId;
    private double battery;
    private double latitude;
    private double longitude;
    private double kilometers;
    private double timeUsed;
    private double baseFee;
    private double extraFeePause;


    public ScooterDTO(Long stopId, double battery, double latitude, double longitude, double kilometers, double timeUsed, double baseFee, double extraFeePause) {
        this.stopId = stopId;
        this.battery = battery;
        this.latitude = latitude;
        this.longitude = longitude;
        this.kilometers = kilometers;
        this.timeUsed = timeUsed;
        this.baseFee = baseFee;
        this.extraFeePause = extraFeePause;
    }

    public ScooterDTO() {
        super();
    }

    //GETTERS
    public Long getStopId() { return stopId; }

    public double getTimeUsed() { return timeUsed; }

    public double getKilometers() { return kilometers; }

    public double getLongitude() { return longitude; }

    public double getLatitude() { return latitude; }

    public double getBattery() { return battery; }

    public double getBaseFee() { return baseFee; }


    //SETTERS
    public void setStopId(Long stopId) { this.stopId = stopId; }

    public void setBattery(double battery) { this.battery = battery; }

    public void setLatitude(double latitude) { this.latitude = latitude; }

    public void setKilometers(double kilometers) { this.kilometers = kilometers; }

    public void setLongitude(double longitude) { this.longitude = longitude; }

    public void setTimeUsed(double timeUsed) { this.timeUsed = timeUsed; }

    public void setBaseFee(double baseFee) { this.baseFee = baseFee; }

    public double getExtraFeePause() { return extraFeePause; }

    public void setExtraFeePause(double extraFeePause) { this.extraFeePause = extraFeePause; }
}
