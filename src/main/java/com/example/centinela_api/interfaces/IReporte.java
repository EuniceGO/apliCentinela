package com.example.centinela_api.interfaces;

import com.example.centinela_api.modelos.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IReporte extends JpaRepository<Reporte, Integer> {

	@Query("SELECT r.tipo, COUNT(r) FROM Reporte r GROUP BY r.tipo")
	java.util.List<Object[]> countByTipoGroup();

	@Query("SELECT r.estado, COUNT(r) FROM Reporte r GROUP BY r.estado")
	java.util.List<Object[]> countByEstadoGroup();

	List<Reporte> findByFechaHoraBetween(LocalDateTime start, LocalDateTime end);

}
