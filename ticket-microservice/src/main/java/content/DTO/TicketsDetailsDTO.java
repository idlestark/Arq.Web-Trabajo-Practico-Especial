package content.DTO;

public class TicketsDetailsDTO {
    private Long ticketId;
    private Long tripId;
    private Double feeBase;
    private Double feeExtra;
    private Long timeUse;
    private Long timePaused;

    public TicketsDetailsDTO(Long ticketId, Long timePaused, Long timeUse, Double feeExtra, Double feeBase, Long tripId) {
        this.ticketId = ticketId;
        this.timePaused = timePaused;
        this.timeUse = timeUse;
        this.feeExtra = feeExtra;
        this.feeBase = feeBase;
        this.tripId = tripId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Double getFeeBase() {
        return feeBase;
    }

    public void setFeeBase(Double feeBase) {
        this.feeBase = feeBase;
    }

    public Double getFeeExtra() {
        return feeExtra;
    }

    public void setFeeExtra(Double feeExtra) {
        this.feeExtra = feeExtra;
    }

    public Long getTimeUse() {
        return timeUse;
    }

    public void setTimeUse(Long timeUse) {
        this.timeUse = timeUse;
    }

    public Long getTimePaused() {
        return timePaused;
    }

    public void setTimePaused(Long timePaused) {
        this.timePaused = timePaused;
    }
}
