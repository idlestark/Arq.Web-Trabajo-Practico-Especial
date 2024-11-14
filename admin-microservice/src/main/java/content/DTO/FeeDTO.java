package content.DTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public class FeeDTO {

    public enum FeeType {
        BASE,
        EXTRA_PAUSE
    }

    private Long id;

    @Enumerated(EnumType.STRING)
    private FeeType tipo;

    private double amount;
    private LocalDate dateStart;
    private LocalDate dateEnd;

    public FeeDTO(){super();}

    public FeeDTO(Long id, FeeType tipo, double amount, LocalDate dateStart, LocalDate dateEnd) {
        this.id = id;
        this.tipo = tipo;
        this.amount = amount;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FeeType getTipo() {
        return tipo;
    }

    public void setTipo(FeeType tipo) {
        this.tipo = tipo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
}
