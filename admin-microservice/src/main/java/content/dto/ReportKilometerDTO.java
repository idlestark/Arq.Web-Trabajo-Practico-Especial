package content.dto;

public class ReportKilometerDTO {
    private Long scooterId;
    private double kilometers;
    private double pauseTime;

    public ReportKilometerDTO(Long scooterId, double kilometers, double pauseTime) {
        this.scooterId = scooterId;
        this.kilometers = kilometers;
        this.pauseTime = pauseTime;
    }

    public Long getScooterId() { return scooterId; }

    public void setScooterId(Long scooterId) { this.scooterId = scooterId; }

    public double getKilometers() { return kilometers; }

    public void setKilometers(double kilometers) { this.kilometers = kilometers; }

    public double getPauseTime() { return pauseTime; }

    public void setPauseTime(double pauseTime) { this.pauseTime = pauseTime; }
}
