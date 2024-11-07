package service;
import client.ClientTrip;
import entities.Scooter;
import repository.ScooterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ScooterService {
    private final ScooterRepository scooterRepository;
    private final ViajeClient viajeClient;

    @Transactional(readOnly = true)
    public List<Scooter> findAll() {
        return scooterRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Scooter findById(Long id) {
        return ScooterRepository.findById(id).orElse(null);
    }

    @Transactional
    public Scooter save(Scooter scooter) {
        return ScooterRepository.save(scooter);
    }

    @Transactional
    public Scooter update(Scooter monopatin) {
        return ScooterRepository.save(monopatin);
    }

    @Transactional
    public void delete(Long id) {
        ScooterRepository.deleteById(id);
    }

    public Map<String, Long> obtenerEstadoMonopatines() {
        long enOperacion = ScooterRepository.countByDisponibleTrueAndEnMantenimientoFalse();
        long enMantenimiento = ScooterRepository.countByEnMantenimientoTrue();
        return Map.of("En Operación", enOperacion, "En Mantenimiento", enMantenimiento);
    }


    public List<Scooter> obtenerMonopatinesCercanos(double latitud, double longitud, double radio) {
        return ScooterRepository.findMonopatinesCercanos(latitud, longitud, radio);
    }

    public List<Scooter> obtenerMonopatinesConMasViajes(int minViajes, int anio) {
        return viajeClient.obtenerMonopatinesConMasViajes(minViajes, anio);
    }
}