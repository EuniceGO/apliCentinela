package com.example.centinela_api.interfaces;

import com.example.centinela_api.modelos.Emergencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmergencia extends JpaRepository<Emergencia, Integer> {

}
