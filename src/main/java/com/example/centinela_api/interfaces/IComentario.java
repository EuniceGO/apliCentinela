package com.example.centinela_api.interfaces;

import com.example.centinela_api.modelos.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComentario extends JpaRepository<Comentario, Integer> {

}
