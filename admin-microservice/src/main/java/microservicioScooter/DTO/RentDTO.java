package microservicioScooter.DTO;

import java.time.LocalDateTime;

public class RentDTO {
    private Long id;
    private Long monopatinId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private double kilometrosRecorridos;
    private boolean enCurso;

    @OneToMany(mappedBy = "viajeDTO", cascade = CascadeType.ALL)
}
