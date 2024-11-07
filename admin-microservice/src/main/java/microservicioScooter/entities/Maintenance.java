package microservicioScooter.entities;

import jakarta.persistence.*;
import org.intellij.lang.annotations.Identifier;

import java.time.LocalDateTime;

@Entity
public class Maintenance {

    @Id
    @GemeratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullblade = false)
    private Long monopatinId;

    private String descripcion;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public Maintenance(){
        super();
    }

    public Maintenance(Long monopatinId, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        super();
        this.monopatinId = monopatinId;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMonopatinId() {
        return monopatinId;
    }

    public void setMonopatinId(Long monopatinId) {
        this.monopatinId = monopatinId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
}
