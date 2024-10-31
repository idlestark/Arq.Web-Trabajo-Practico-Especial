package com.example.microservicioScooter.repository;

import com.example.microservicioScooter.entity.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.List;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {

    @Query("SELECT COUNT(m) FROM Monopatin m WHERE m.disponible = true AND m.enMantenimiento = false")
    long countByDisponibleTrueAndEnMantenimientoFalse();

    @Query("SELECT COUNT(m) FROM Monopatin m WHERE m.enMantenimiento = true")
    long countByEnMantenimientoTrue();

    @Query("SELECT m FROM Monopatin m WHERE " +
            "(6371 * acos(cos(radians(:latitud)) * cos(radians(m.latitud)) * " +
            "cos(radians(m.longitud) - radians(:longitud)) + sin(radians(:latitud)) * " +
            "sin(radians(m.latitud)))) < :radio")
    List<Monopatin> findMonopatinesCercanos(@Param("latitud") double latitud,
                                            @Param("longitud") double longitud,
                                            @Param("radio") double radio);

}
