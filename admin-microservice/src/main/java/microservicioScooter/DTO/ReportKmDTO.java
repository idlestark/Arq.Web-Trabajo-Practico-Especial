package microservicioScooter.DTO;

public class ReportKmDTO {

private Long monopatinId;
private double kilometrosRecorridos;
private double tiempoPausado;

    public ReportKmDTO(Long monopatinId, double kilometrosRecorridos, double tiempoPausado){
        this.monopatinId = monopatinId;
        this.kilometrosRecorridos = kilometrosRecorridos;
        this.tiempoPausado = tiempoPausado;
    }

    // Getters y setters
    public Long getMonopatinId() {
        return monopatinId;
    }

    public void setMonopatinId(Long monopatinId) {
        this.monopatinId = monopatinId;
    }

    public double getKilometrosRecorridos() {
        return kilometrosRecorridos;
    }

    public double getTiempoPausado() {
        return tiempoPausado;
    }

    public void setTiempoPausado(double tiempoPausado) {
        this.tiempoPausado = tiempoPausado;
    }
}
