package content.DTO;

public class KilometersReportDTO {
    private Long scooterId;
    private double totalKilometers;

    public KilometersReportDTO(Long scooterId, double totalKilometers){
        this.scooterId = scooterId;
        this.totalKilometers = totalKilometers;
    }

    public Long getScooterId() {
        return scooterId;
    }

    public void setScooterId(Long scooterId) {
        this.scooterId = scooterId;
    }

    public double getTotalKilometers() {
        return totalKilometers;
    }

    public void setTotalKilometers(double totalKilometers) {
        this.totalKilometers = totalKilometers;
    }
}
