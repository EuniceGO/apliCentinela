package com.example.centinela_api.interfaces;

import com.example.centinela_api.modelos.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlerta extends JpaRepository<Alerta, Integer> {

}
