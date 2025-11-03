package com.example.centinela_api.interfaces;

import com.example.centinela_api.modelos.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReporte extends JpaRepository<Reporte, Integer> {

}
